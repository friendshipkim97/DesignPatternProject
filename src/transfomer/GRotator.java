package transfomer;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.util.Vector;

import shape.GGroup;
import shape.GShape;
import shape.GShape.EDrawingStyle;

public class GRotator extends GTransformer {

	private int centerX, centerY, startX, startY, endX, endY;
	private AffineTransform affineTransform;
	Vector<GShape> shapes;
	
	public GRotator(GShape shape) {
		super(shape);
		
		this.affineTransform = new AffineTransform();
		if(shape.getEDrawingStyle()==EDrawingStyle.eGStyle) {
			shapes = new Vector<GShape>();
			for(GShape tempShape : ((GGroup) shape).getGroupShapes()) {
				shapes.add(tempShape);
			}
			
		}
	}

	public void initTransforming(Graphics2D g2D, int x, int y) {
		centerX = (int)this.shape.getBounds().getCenterX();
		centerY = (int)this.shape.getBounds().getCenterY();
		this.startX = x;
		this.startY = y;
	}

	@SuppressWarnings("unlikely-arg-type")
	public void keepTransforming(Graphics2D g2D, int x, int y, Rectangle tempGroup) {
		g2D.setXORMode(g2D.getBackground());

		this.shape.draw(g2D);
		
		///////////////////////////////////////////////////
		this.endX = x;
		this.endY = y;
		double startAngle = Math.toDegrees(Math.atan2(this.centerX-this.centerY, this.centerY-this.startY));
		double endAngle = Math.toDegrees(Math.atan2(this.centerX-this.endY, this.centerY-this.endY));
		double rotationAngle = startAngle-endAngle;
		if (rotationAngle < 0) {
			rotationAngle += 360;
		}
		this.affineTransform.setToRotation(Math.toRadians(rotationAngle), this.centerX, this.centerY);
		this.shape.setShape(this.affineTransform.createTransformedShape(this.shape.getShape()));
        ///////////////////////////////////////////////////
		this.startX = this.endX;
		this.startY = this.endY;
		this.shape.draw(g2D);
	}

	public void finishTransforming(Graphics2D g2D, int x, int y) {

	}

	@Override
	public void continueTransforming(Graphics2D g2d, int x, int y) {
		// TODO Auto-generated method stub
		
	}
}
