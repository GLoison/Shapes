package graphics.shapes;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;


public class STriangle extends Shape {
	
	private Polygon tria;

	public STriangle(Point p1,Point p2,Point p3) {
		int x[] = {p1.x,p2.x,p3.x};
		int y[] = {p1.y,p2.y,p3.y};
		this.tria=new Polygon(x,y,3);
	}
	
	public Polygon getTriangle() {
		return tria;
	}
	
	@Override
	public Point getLoc() {
		// TODO Auto-generated method stub
		return tria.getBounds().getLocation();
	}

	@Override
	public void setLoc(Point pt) {
		// TODO Auto-generated method stub
		this.tria.getBounds().setLocation(pt);
	}

	@Override
	public void translate(int dx, int dy) {
		// TODO Auto-generated method stub
		this.tria.translate(dx, dy);
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return tria.getBounds();
	}

	@Override
	public void accept(ShapeVisitor sv) {
		// TODO Auto-generated method stub
		sv.visitTriangle(this);
	}

	@Override
	public void setSize(int dw, int dh) {
		// TODO Auto-generated method stub
		
	}

}
