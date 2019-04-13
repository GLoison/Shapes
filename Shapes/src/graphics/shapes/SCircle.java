package graphics.shapes;

import java.awt.Point;
import java.awt.Rectangle;

public class SCircle extends Shape{
	
	private int radius;
	private Point loc;
	
	public SCircle(Point point, int i) {
		this.loc=point;
		this.radius=i;
	}

	public int getRadius() {
		return this.radius;
	}
	
	public void setRadius(int rad) {
		this.radius=rad;
	}
	
	@Override
	public Point getLoc() {
		return this.loc;
	}

	@Override
	public void setLoc(Point pt) {
		this.loc=pt;
	}

	@Override
	public void translate(int dx, int dy) {
		this.loc.translate(dx, dy);
	}

	@Override
	public Rectangle getBounds() {
		Rectangle rect= new Rectangle(this.loc.x,this.loc.y,2*this.radius,2*this.radius);
		return rect;
	}

	@Override
	public void accept(ShapeVisitor sv) {
		sv.visitCircle(this);
	}
	
	

}
