package shape;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.GeneralPath;

import shape.GShape.EDrawingStyle;

public class GTriangle extends GShape{

	GeneralPath path;
	int newX=0;
	int newY=0;
	int newWidth=0;
	int newHeight=0;
	
	public GTriangle() {
		this.eDrawingStyle = EDrawingStyle.e2Points;		
		this.shape = new GeneralPath();
	}
	@Override
	public void setOrigin(int x, int y) {
		
		GeneralPath path = (GeneralPath)this.shape;
		
		newWidth=100; newHeight=100;
		path.moveTo(x + newWidth / 2, y);
		path.lineTo(x, y + newHeight);
		path.lineTo(x + newWidth, y + newHeight);
		path.closePath();
	}

	@Override
	public void setPoint(int x, int y) {
		
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
