package shape;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.lang.reflect.InvocationTargetException;
import java.util.Vector;

import shape.GShape.EDrawingStyle;


public class GGroup extends GRectangle {
	
	private static final long serialVersionUID = 1L;
	private Vector<GShape> gShapes;
	

	public GGroup() {		
		this.eDrawingStyle = EDrawingStyle.eGStyle;
		this.gShapes = new  Vector<GShape>();
	
	}
	
	public Vector<GShape> getGroupShapes() {
		return gShapes;
	}
	
	public void draw(Graphics2D graphics) {
		Rectangle unionRectangle = new Rectangle();
		for(GShape gShape: this.gShapes) {
			gShape.draw(graphics);
			unionRectangle.union(gShape.getBounds()); //unionRectangle 은 자식들의 집합 
		}
		this.shape = unionRectangle;
		Graphics2D graphics2D = (Graphics2D)graphics;
		 ((Graphics2D) graphics).setStroke(new BasicStroke(shapeThick,BasicStroke.CAP_ROUND,0));
		
		 if (this.getFillColor() != null) {
				if(textTemp!=null) {
					graphics2D.drawString(textTemp, textX, textY);
				}
				graphics2D.setColor(this.getFillColor());
				graphics2D.fill(this.shape);

			}
			if (this.lineColor !=null) {
				if(textTemp!=null) {
					graphics2D.drawString(textTemp, textX, textY);
				}
				graphics2D.setColor(lineColor);
				graphics2D.draw(this.shape);
			}
			if(this.isbSelected()) {
				this.anchors.draw(graphics2D);
			}
	}

	public void setLineColor(Color lineColor) {
		for(GShape gShape: this.gShapes) {
			gShape.setLineColor(lineColor);;
		}
	}
	public void setFillColor(Color fillColor) {
		for(GShape gShape: this.gShapes) {
			gShape.setLineColor(fillColor);;
		}
	}
	
	public void setEraserThick(int eraserThick) {
		for(GShape gShape: this.gShapes) {
			gShape.setEraserThick(eraserThick);
		}
	}
	public void setShapeThick(int shapeThick) {
		for(GShape gShape: this.gShapes) {
			gShape.setShapeThick(shapeThick);
		}
	}
	
	public GShape clone() {
		try {
			return this.getClass().getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean contains(int x, int y) {

		boolean bContains = false;

		this.eSelectedAnchor = null;
		if(this.isbSelected()) {
			this.eSelectedAnchor =this.anchors.contains(x,y);
		}
		if(this.eSelectedAnchor ==  null) {
			for(GShape gShape: this.gShapes) {
				if(gShape.contains(x,y)) {
					this.eSelectedAnchor = GAnchors.EAnchors.MM;
					bContains=true;
					break;
				}
			}
		} else {
			bContains = true;
		}

		return bContains;
	}
	
	public void addshape(GShape shape) {
		gShapes.add(shape);
		if(gShapes.size() == 1) {
			this.shape = shape.getShape().getBounds();
		} else {
			this.shape = this.shape.getBounds().createUnion(shape.getShape().getBounds());
		}
	}
	
	public void setSelected(boolean bSelected) {
		this.bSelected=bSelected;
		if(this.isbSelected())
			this.anchors.setBounds(this.shape.getBounds());
	}
	
	public Rectangle getBounds() {

		return this.shape.getBounds();
	}

}
