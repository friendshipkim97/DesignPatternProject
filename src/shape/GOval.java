package shape;

import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

public class GOval extends GShape implements Cloneable {

	private static final long serialVersionUID = 1L;
	
	public int x1,y1;

	public GOval() {
		this.eDrawingStyle = EDrawingStyle.e2Points;		
		this.shape = new Ellipse2D.Float();
	}
	
	@Override
	public void setOrigin(int x, int y) {
		Ellipse2D ellipse = (Ellipse2D)this.shape;
		x1=x;
		y1=y;
		ellipse.setFrame(x, y, 0, 0);
	}

	@Override
	public void setPoint(int x, int y) {
		Ellipse2D ellipse = (Ellipse2D)this.shape;
		int newX = Math.min(x1, x);
		int newY = Math.min(y1, y);
		int newWidth = Math.abs(x1 - x);
		int newHeight = Math.abs(y1 - y);
		ellipse.setFrame(newX, newY, newWidth, newHeight);
	}
	
	@Override
	public void addPoint(int x, int y) {
	}


}
