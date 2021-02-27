package transfomer;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import shape.GShape;

public class GDrawer extends GTransformer {

	public GDrawer(GShape shape) {
		super(shape);
		
	}
	 public void initTransforming(Graphics2D g2, int x, int y) {
	    	this.shape.setOrigin(x, y);

	
		}
	    public void keepTransforming(Graphics2D g2D, int x, int y, Rectangle tempGroup) {
	    	g2D.setXORMode(g2D.getBackground());
			this.shape.draw(g2D);
			this.shape.setPoint(x, y);
			this.shape.draw(g2D);
		}
	    public void finishTransforming(Graphics2D g2, int x, int y) {
			//this.shape(x, y);
	    	

		}
	    public void continueTransforming(Graphics2D g2, int x, int y) {
			this.shape.addPoint(x, y);
	    }
		

}
