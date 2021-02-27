package shape;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D.Double;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.plaf.basic.BasicTextUI;

import shape.GShape.EDrawingStyle;

public class GRectangle extends GShape implements Cloneable {

	private static final long serialVersionUID = 1L;
	
	public int x1,y1;
	private BufferedImage image;
	
	private int originX,originY;
	

	public GRectangle() {
		this.eDrawingStyle = EDrawingStyle.e2Points;		
		this.shape = new Rectangle();
	
	}
	
	public GRectangle(File imageFile) {
		this.eDrawingStyle = EDrawingStyle.e2Points;	
		try {
			this.image = ImageIO.read(imageFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.shape = new Rectangle();
		this.setOrigin(0, 0);
		this.setPoint(this.image.getWidth(), this.image.getHeight());
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

//	@Override
//	public void move(int dx, int dy) {
//		Rectangle rectangle = (Rectangle)this.shape;
//		rectangle.translate(dx, dy);
//	rectangle.setLocation(rectangle.x + dx, rectangle.y + dy);
//		AffineTransform at = new AffineTransform();
//		at.translate(dx, dy);
//		this.shape = at.createTransformedShape(this.shape);
		
	
	
}
