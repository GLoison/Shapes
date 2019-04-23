package graphics.shapes;

import java.awt.Point;
import java.awt.Rectangle;

public class SRectangle extends Shape {

	private  Rectangle rect;
	
	public SRectangle(Point loc,int width,int height) {
		this.rect=new Rectangle(loc.x,loc.y,width,height);
	}
	
	public Rectangle getRect() {
		return rect;
	}
	
	public Point getLoc() {
		return rect.getLocation();
	}
	
	public void setLoc(Point pt) {
		rect.setLocation(pt);
	}
	
	public void translate(int dx,int dy) {
		rect.translate(dx, dy);
	}
	
	public Rectangle getBounds() {
		return rect.getBounds();
	}
	
	public void accept(ShapeVisitor sv) {
		sv.visitRectangle(this);
	}
}
