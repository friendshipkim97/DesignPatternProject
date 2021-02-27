package shape;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class GSelected extends GShape{

	public int x1,y1;
	private BufferedImage image;
	
	private int originX,originY;
	
	
	public GSelected() {
		this.eDrawingStyle = EDrawingStyle.e2Points;		
		this.shape = new Rectangle();
	   setSelected();
	}

	
	@Override
	public void draw(Graphics2D graphics) {
		super.draw(graphics);
		Rectangle bound = this.shape.getBounds();
		graphics.drawImage(this.image, bound.x, bound.y,
				bound.width, bound.height, null);
	}
	
	@Override
	public void setOrigin(int x, int y) {
		//Shape shape = this.shape;
		
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
	public void addPoint(int x, int y) {}
}
