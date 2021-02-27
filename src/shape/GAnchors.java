package shape;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Arc2D;
import java.awt.geom.Arc2D.Double;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import java.util.Vector;

public class GAnchors implements Serializable{

	final private int ANCHOR_W = 10;
	final private int ANCHOR_H = 10;
	final private int ANCHORS_RRHEIGHT=40;

	public enum EAnchors{
		NW, NN, NE, EE, SE, SS, SW, WW, RR, MM
	}

	private Vector<Ellipse2D> anchors;
	public Vector<Arc2D> rotateArcs;
	public Vector<Line2D> rotateLines;

	public GAnchors() {
		this.anchors = new Vector<Ellipse2D>(); 
		this.rotateArcs = new Vector<Arc2D>();
		this.rotateLines = new Vector<Line2D>();

		for(int i=0; i<EAnchors.values().length-2; i++) { //WW까지만 가고 RR은 아래에서 
			Ellipse2D anchor = new Ellipse2D.Double();
			this.anchors.add(anchor);
		}
		Arc2D arc = new Arc2D.Double();
		Line2D line1 = new Line2D.Double();
		Line2D line2 = new Line2D.Double();
		Line2D line3 = new Line2D.Double();
		this.rotateArcs.add(arc);
		this.rotateLines.add(line1);
		this.rotateLines.add(line2);
		this.rotateLines.add(line3);

	}

	private void setCoordinae(EAnchors eAnchor, Ellipse2D anchor, Rectangle bounds, Arc2D arc, Line2D line1, Line2D line2, Line2D line3) {
		int x = 0;
		int y = 0; 
		int w = ANCHOR_W;
		int h = ANCHOR_H;

		switch(eAnchor) {		
		case NW:
			x = bounds.x;
			y = bounds.y;
			x = x - w/2;
			y = y - h/2;
			anchor.setFrame(x,y,w,h);
			break;
		case NN:
			x = bounds.x + bounds.width/2;
			y = bounds.y;
			x = x - w/2;
			y = y - h/2;
			anchor.setFrame(x,y,w,h);
			break;
		case NE:
			x = bounds.x + bounds.width;
			y = bounds.y;
			x = x - w/2;
			y = y - h/2;
			anchor.setFrame(x,y,w,h);
			break;
		case EE:
			x = bounds.x + bounds.width;
			y = bounds.y + bounds.height/2;
			x = x - w/2;
			y = y - h/2;
			anchor.setFrame(x,y,w,h);
			break;
		case SE:
			x = bounds.x + bounds.width;
			y = bounds.y + bounds.height;
			x = x - w/2;
			y = y - h/2;
			anchor.setFrame(x,y,w,h);
			break;
		case SS:
			x = bounds.x + bounds.width/2;
			y = bounds.y + bounds.height;
			x = x - w/2;
			y = y - h/2;
			anchor.setFrame(x,y,w,h);
			break;
		case SW:
			x = bounds.x;
			y = bounds.y + bounds.height;
			x = x - w/2;
			y = y - h/2;
			anchor.setFrame(x,y,w,h);
			break;
		case WW:
			x = bounds.x;
			y = bounds.y + bounds.height/2;
			x = x - w/2;
			y = y - h/2;
			anchor.setFrame(x,y,w,h);
			break;
		case RR:{
			setArc(arc, bounds.x+(bounds.width/2)-7, (int)bounds.y-40, 20, 20);
			setLine1(line1, bounds.x+(bounds.width/2), (int)bounds.y);
			setLine2(line2, bounds.x+(bounds.width/2)-2, (int)bounds.y-3);
			setLine3(line3, bounds.x+(bounds.width/2), (int)bounds.y);
			break;
		}
		default:
			break;
		}
		
	}
	
	public void setBounds(Rectangle bounds) {

		for(int i=0; i<EAnchors.values().length-2; i++) {
			Ellipse2D anchor = this.anchors.get(i);	
			this.setCoordinae(EAnchors.values()[i], anchor, bounds, null, null, null, null);
		}
		Arc2D arc = this.rotateArcs.get(0);
		Line2D line1 = this.rotateLines.get(0);
		Line2D line2 = this.rotateLines.get(1);
		Line2D line3 = this.rotateLines.get(2);
		this.setCoordinae(EAnchors.values()[8], null, bounds, arc, line1, line2, line3);

	}

	public void draw(Graphics2D graphics2d) {
		for(Ellipse2D anchor : this.anchors){
			Color penColor = graphics2d.getColor();
			graphics2d.setColor(graphics2d.getBackground());
			graphics2d.fill(anchor);
			graphics2d.setColor(penColor);
			graphics2d.draw(anchor);
		}
		for(Arc2D arc : this.rotateArcs) {
			Color penColor = graphics2d.getColor();
			graphics2d.setColor(graphics2d.getBackground());
			graphics2d.fill(arc);
			graphics2d.setColor(penColor);
			graphics2d.draw(arc);
		}
		for(Line2D line : this.rotateLines) {
			Color penColor = graphics2d.getColor();
			graphics2d.setColor(graphics2d.getBackground());
			graphics2d.fill(line);
			graphics2d.setColor(penColor);
			graphics2d.draw(line);
		}
	}
	
	

	public EAnchors contains(int x, int y) {
		for(int i=0; i<EAnchors.values().length-2; i++) {
			if(this.anchors.get(i).contains(x,y)) {
				return EAnchors.values()[i];
			}
		}
		if(this.rotateArcs.get(0).contains(x,y)) {
			return EAnchors.values()[8];
		}
		return null;
	}

	public void setArc(Arc2D arc, int x, int y, int w, int h) {

		arc.setArc(x, y, w, h, Math.PI*1.5 ,Math.PI*100, Arc2D.OPEN);

	}

	public void setLine1(Line2D line1, int x, int y) {
		line1.setLine(x+13, y-30, x, y-35); //직선
	}
	public void setLine2(Line2D line2, int x, int y) {
		line2.setLine(x+3, y, x+3, y-15); //직선

	}
	public void setLine3(Line2D line3, int x, int y) {
		line3.setLine(x+13, y-30, x+18, y-40); //직선

	}
}
