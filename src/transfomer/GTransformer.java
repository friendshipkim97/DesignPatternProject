package transfomer;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import shape.GShape;

public abstract class GTransformer {

	protected GShape shape;
	

	public GTransformer(GShape shape) {
		this.shape = shape;
	}

	public abstract void initTransforming(Graphics2D g2D, int x, int y) ;
	public abstract void keepTransforming(Graphics2D g2D, int x, int y, Rectangle tempGroup) ;
	public abstract void finishTransforming(Graphics2D g2D, int x, int y) ;
	public abstract void continueTransforming(Graphics2D g2D, int x, int y);


}
