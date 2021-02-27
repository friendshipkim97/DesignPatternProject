package shape;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class GTextArea extends GShape implements Cloneable {

	private static final long serialVersionUID = 1L;
	
	public int x1,y1;

	public GTextArea() {
		this.eDrawingStyle = EDrawingStyle.e2Points;		
		this.shape = new Rectangle();
	
	}

	@Override
	public void setOrigin(int x, int y) {
		Rectangle rectangle = (Rectangle)this.shape;
		x1=x;
		y1=y;
		rectangle.setLocation(x1, y1);
		rectangle.setSize(0, 0);
	}

	@Override
	public void setPoint(int x, int y) {
		Rectangle rectangle = (Rectangle)this.shape;
		int newX = Math.min(x1, x);
		int newY = Math.min(y1, y);
		int newWidth = Math.abs(x1 - x);
		int newHeight = Math.abs(y1 - y);
		rectangle.setFrame(newX, newY, newWidth, newHeight);
		
	}

	@Override
	public void addPoint(int x, int y) {
	}

//	@Override
//	public void move(int dx, int dy) {
//		Rectangle rectangle = (Rectangle)this.shape;
//		rectangle.translate(dx, dy);
//		//rectangle.setLocation(rectangle.x + dx, rectangle.y + dy);
//		
//	}
}