package transfomer;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Vector;

import shape.GGroup;
import shape.GShape;
import shape.GShape.EDrawingStyle;


public class GMover extends GTransformer{


	private static final long serialVersionUID = 1L;
	Vector<GShape> shapes;

	public GMover(GShape shape) {
		super(shape);
		if(shape.getEDrawingStyle()==EDrawingStyle.eGStyle) {
			shapes = new Vector<GShape>();
			for(GShape tempShape : ((GGroup) shape).getGroupShapes()) {
				shapes.add(tempShape);
			}

		}

	}

	public void initTransforming(Graphics2D g2D, int x, int y) {
		this.shape.initTransforming(x, y);
	}
	public void keepTransforming(Graphics2D g2D, int x, int y, Rectangle tempGroup) {
		g2D.setXORMode(g2D.getBackground());
		this.shape.draw(g2D);
		this.shape.keepTransforming(x, y, shapes, shape, tempGroup);
		this.shape.draw(g2D);
	}
	public void finishTransforming(Graphics2D g2D, int x, int y) {
		this.shape.finishTransforming(x, y);
	}

	@Override
	public void continueTransforming(Graphics2D g2d, int x, int y) {
		// TODO Auto-generated method stub

	}


}
