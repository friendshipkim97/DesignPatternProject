package shape;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import frames.GToolBar;

public class GEraser extends GShape implements Cloneable {

	private static final long serialVersionUID = 1L;
	
	
	public GEraser() {
		this.eDrawingStyle = EDrawingStyle.eEPoints;

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
		
		 ((Graphics2D) graphics).setStroke(new BasicStroke(1,BasicStroke.CAP_ROUND,0));
		 
		 graphics.setColor(Color.white);
		 graphics.fillOval(x1, y1, eraserThick, eraserThick);
	}
		
}
//
//	@Override
//	public void move(int dx, int dy) {
//		// TODO Auto-generated method stub
//		
//	}
}
