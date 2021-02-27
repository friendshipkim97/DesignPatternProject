package shape;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.util.Vector;

public class GCurve extends GShape implements Cloneable{

	public Vector<Point> vPoint;
	
	
	private static final long serialVersionUID = 1L;

	public GCurve() {
		this.eDrawingStyle = EDrawingStyle.eMPoints;
		vPoint = new Vector<Point>();

		this.shape = new Line2D.Float();
		this.xPoints = new int[1000]; 
		this.yPoints = new int[1000]; 
	
	}
	
	
	
	public void setOrigin(int x, int y) {
		
		this.xPoints[this.nPoints] = x;
		this.yPoints[this.nPoints] = y;
		this.addPoint(x, y);
	}
	
	public void setPoint(int x, int y) {
		
		this.xPoints[this.nPoints] = x;
		this.yPoints[this.nPoints] = y;
	}
	
	public void addPoint(int x, int y) {
		this.nPoints++;
		this.xPoints[this.nPoints] = x;
		this.yPoints[this.nPoints] = y;
	}

	
	public void draw(Graphics2D graphics) {
		
		for(int i=0; i<nPoints; i++) {
		int x1 = xPoints[i];
		int y1 = yPoints[i];
		int x2 = xPoints[i+1];
		int y2 = yPoints[i+1];
		
		 ((Graphics2D) graphics).setStroke(new BasicStroke(shapeThick,BasicStroke.CAP_ROUND,0));
		 
		 graphics.setColor(lineColor);
		 graphics.drawLine(x1, y1, x2, y2);
	}
	}


//
//	@Override
//	public void move(int dx, int dy) {
//		// TODO Auto-generated method stub
//		
//	}
	}
