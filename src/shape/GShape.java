package shape;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Vector;

import shape.GAnchors.EAnchors;
import shape.GShape.EDrawingStyle;




public abstract class GShape implements Serializable {

	/**
	 * 
	 */
	// attributes
	private static final long serialVersionUID = 1L;

	protected int eraserThick;
	protected int shapeThick;

	protected int xPoints[];
	protected int yPoints[];	
	protected int nPoints;

	protected double groupX;
	protected double groupY;
	protected double groupW;
	protected double groupH;

	private GGroup tempGroup;

	public enum EDrawingStyle {
		e2Points, eNPoints, eMPoints, eEPoints, eSPoints, eGStyle
	}

	protected EDrawingStyle eDrawingStyle;
	public int tMoveX, tMoveY;
	protected Color lineColor;

	private Color fillColor;


	protected GAnchors anchors;
	protected GAnchors.EAnchors eSelectedAnchor;
	public String textTemp;
	public int textX;
	public int textY;

	private int sx,sy,ex,ey;
	public boolean groupSelected=false;

	protected Shape shape;

	//working variables
	protected boolean bSelected;

	public GShape() {

		this.lineColor =null;
		this.fillColor =null;
		this.eraserThick=40;
		this.shapeThick=1;
		textTemp=null;

		bSelected=false;
		this.anchors= new GAnchors();

		this.nPoints=0;
	}
	public Shape getShape() {	return this.shape;}	
	public void setShape(Shape shape) { this.shape=shape;}

	public EDrawingStyle getEDrawingStyle() { return this.eDrawingStyle; }

	public GAnchors.EAnchors getESelectedAnchor(){ 
		return this.eSelectedAnchor;
	}

	public void setSelected(boolean bSelected) {
		this.bSelected=bSelected;
		if(this.isbSelected())
			this.anchors.setBounds(this.shape.getBounds());
	}

	public void draw(Graphics2D graphics) {
		Graphics2D graphics2D = (Graphics2D)graphics;
		((Graphics2D) graphics).setStroke(new BasicStroke(shapeThick,BasicStroke.CAP_ROUND,0));

		if (this.fillColor != null) {
			if(textTemp!=null) {
				graphics2D.drawString(textTemp, textX, textY);
				System.out.println("확인정말");
			
			}
			graphics2D.setColor(this.fillColor);
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
		this.lineColor = lineColor;
	}
	public void setFillColor(Color fillColor) {

		this.fillColor = fillColor;
	}

	public void setEraserThick(int eraserThick) {
		this.eraserThick = eraserThick;
	}
	public void setShapeThick(int shapeThick) {
		this.shapeThick = shapeThick;
	}

	public void setText(String a, int x, int y) {
		textTemp=a;
		textX=x;
		textY=y;
		
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
			if(this.shape.contains(new Point(x, y))) {
				this.eSelectedAnchor = GAnchors.EAnchors.MM;
				bContains=true;
			}
		} else {
			bContains = true;
		}

		return bContains;
	}

	public Rectangle getBounds() {
		return this.shape.getBounds();
	}

	public void initTransforming(int x, int y) {

		this.tMoveX = x;
		this.tMoveY = y;

		sx=(int) this.shape.getBounds().getMinX();
		sy=(int) this.shape.getBounds().getMinY();
	}

	public void keepTransforming(int x, int y, Vector<GShape> shapes, GShape shape, Rectangle tempGroup) {
		this.move(x-this.tMoveX, y-this.tMoveY, shapes, shape, tempGroup); //전점에서 이만큼움직인것 tMoveX는 전점, x는현재점
		this.tMoveX = x;
		this.tMoveY = y;
	}

	public void keepResizeTransforming(int x, int y, EAnchors tempAnchor, Vector<GShape> shapes, GShape shape) {
		this.resize(x, y, tempAnchor, shapes, shape); //전점에서 이만큼움직인것 tMoveX는 전점, x는현재점
	}


	public void finishTransforming(int x, int y) {
		this.tMoveX = x;
		this.tMoveY = y;
	}


	public void move(int dx, int dy, Vector<GShape> shapes, GShape shape, Rectangle tempGroup) {
		AffineTransform at = new AffineTransform();
		at.setToTranslation(dx, dy);
		if(this.eDrawingStyle==eDrawingStyle.eGStyle) {
			GShape temp;
			for(int i=0; i < shapes.size(); i++) {
				temp = shapes.get(i);
				temp.setShape(at.createTransformedShape(temp.getShape()));
			}
			shape.setShape(at.createTransformedShape(shape.getShape()));
			tempGroup.setBounds((int)tempGroup.getBounds().getX()+dx, (int)tempGroup.getBounds().getY()+dy, (int)tempGroup.getBounds().getWidth(), (int)tempGroup.getBounds().getHeight());
			this.anchors.setBounds(tempGroup);
		}

		at.translate(dx, dy);
		this.shape = at.createTransformedShape(this.shape);	


	}

	public void resize(int x, int y, EAnchors tempAnchor, Vector<GShape> shapes, GShape shape) {

		EAnchors Anchor = tempAnchor;
		AffineTransform at = new AffineTransform();
		Point2D.Double factor = new Point2D.Double();
		Point save = new Point();
		double width = this.shape.getBounds().width;
		double height = this.shape.getBounds().height;
		save.x = -sx; save.y = -sy;
		at.setToTranslation(save.x, save.y); 
		this.shape = at.createTransformedShape(this.shape);	

		switch(Anchor) {
		case NW:
			factor.x = (sx - x + width) / (width);
			factor.y = (sy - y + height) / (height);
			save.x = x; save.y = y; sx = x; sy = y;
			break;
		case NN:		
			factor.x=1;
			factor.y = (sy-y + height)/height;
			save.x = sx; save.y = y; sy = y;
			break;	
		case NE:
			factor.x = (x - sx) / (width);
			factor.y = (sy - y + height) / (height);
			save.x = sx; save.y = y;	sy = y;
			break;
		case EE: 
			factor.x = (x - sx) / (width);
			factor.y = 1;
			save.x = sx; save.y = sy;
			break;
		case SE: 
			factor.x = (x - sx) / (width);
			factor.y = (y - sy) / (height);
			save.x = sx; save.y = sy;
			break;
		case SS: 
			factor.x = 1;
			factor.y = (y - sy) / (height);
			save.x = sx; save.y = sy;
			break;
		case SW: 
			factor.x =(sx - x + width) / (width);
			factor.y = (y - sy) / (height);
			save.x = x; save.y = sy; sx = x; 
			break;
		case WW: 
			factor.x = (sx - x + width) / (width);
			factor.y = 1;
			save.x = x; save.y = sy; sx = x;
			break;
		}

		at.setToScale(factor.x, factor.y);
		this.shape = at.createTransformedShape(this.shape);	
		at.setToTranslation(save.x, save.y);
		this.shape = at.createTransformedShape(this.shape);	
	}
	public abstract void setOrigin(int x, int y);	
	public abstract void setPoint(int x, int y);	
	public abstract void addPoint(int x, int y);
	public boolean getSelected() {

		return groupSelected;
	}
	public void setSelected() {

		this.groupSelected=true;
	}

	public boolean isbSelected() {
		return bSelected;
	}

	public void setbSelected(boolean bSelected2) {
		this.bSelected=true;
	}

	public Color getFillColor() {
		return this.fillColor;
	}

	public void setGGroup(GGroup tempGroup) {
		this.tempGroup=tempGroup;
	}
}

