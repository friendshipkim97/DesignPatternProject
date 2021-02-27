package shape;

import java.awt.Point;
import java.awt.geom.GeneralPath;

public class GStar extends GShape{

	GeneralPath path;
	int newX=0;
	int newY=0;
	int newWidth=0;
	int newHeight=0;

	public GStar() {
		this.eDrawingStyle = EDrawingStyle.e2Points;		
		this.shape = new GeneralPath();
	}
	@Override
	public void setOrigin(int x, int y) {
		GeneralPath path = (GeneralPath)this.shape;
		path.moveTo(x + 10, y);
		path.moveTo(x + 30 / 2, y);
		path.lineTo(x + 30 * 3 / 8, y + 30 * 3 / 8);
		path.lineTo(x, y + 30 / 2);
		path.lineTo(x + 30 * 3 / 8, y + 30 * 5 / 8);
		path.lineTo(x + 30 / 2, y + 30);
		path.lineTo(x + 30 * 5 / 8, y + 30 * 5 / 8);
		path.lineTo(x + 30, y + 30 / 2);
		path.lineTo(x + 30 * 5 / 8, y + 30 * 3 / 8);
		path.closePath();

	}

	@Override
	public void setPoint(int x, int y) {
		GeneralPath path = (GeneralPath)this.shape;


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