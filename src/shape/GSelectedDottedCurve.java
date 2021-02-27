package shape;

import java.awt.Point;
import java.awt.geom.GeneralPath;

public class GSelectedDottedCurve extends GShape{

	GeneralPath path;
	int newX=0;
	int newY=0;
	int newWidth=0;
	int newHeight=0;
	
	public GSelectedDottedCurve() {
		this.eDrawingStyle = EDrawingStyle.e2Points;		
		this.shape = new GeneralPath();
	}
	@Override
	public void setOrigin(int x, int y) {
		GeneralPath path = (GeneralPath)this.shape;
		path.moveTo(x, y);
       
	}

	@Override
	public void setPoint(int x, int y) {
		GeneralPath path = (GeneralPath)this.shape;
     
		path.moveTo(x, y);
		path.lineTo(x-1, y-1);
		path.closePath();
	}

	@Override
	public void addPoint(int x, int y) {
		
	}
	
public boolean contains(int x, int y) {
		
		boolean bContains = false;
		
		this.eSelectedAnchor = null;
		if(this.bSelected) {
			this.eSelectedAnchor =this.anchors.contains(x,y);
		}
		if(this.eSelectedAnchor ==  null) {
	    if(this.shape.getBounds().contains(new Point(x, y))) {
	    	this.eSelectedAnchor = GAnchors.EAnchors.MM;
	    	bContains=true;
	    }
		} else {
			bContains = true;
		}
		
		return bContains;
	}

}
