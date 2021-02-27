package transfomer;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.Vector;

import shape.GAnchors.EAnchors;
import shape.GShape.EDrawingStyle;
import shape.GGroup;
import shape.GRectangle;
import shape.GShape;

public class GResizer extends GTransformer {
	
	private EAnchors tempAnchor;
	Vector<GShape> shapes;
	
	public GResizer(GShape shape, EAnchors eAnchors) {
		super(shape);
		tempAnchor=eAnchors;	
		if(shape.getEDrawingStyle()==EDrawingStyle.eGStyle) {
			shapes = new Vector<GShape>();
			for(GShape tempShape : ((GGroup) shape).getGroupShapes()) {
				shapes.add(tempShape);
			}
		}
	}

	@Override
	public void initTransforming(Graphics2D g2d, int x, int y) {
		this.shape.initTransforming(x, y);

	}

	@Override
	public void keepTransforming(Graphics2D g2d, int x, int y, Rectangle
			
			
tempGroup) {	
		g2d.setXORMode(g2d.getBackground());
		this.shape.draw(g2d);
		this.shape.keepResizeTransforming(x, y, tempAnchor, shapes, shape);
		this.shape.draw(g2d);
			
	}

	@Override
	public void finishTransforming(Graphics2D g2d, int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void continueTransforming(Graphics2D g2d, int x, int y) {
		// TODO Auto-generated method stub
		
	}

}
