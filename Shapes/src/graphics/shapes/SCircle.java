package graphics.shapes;

import java.awt.Point;
import java.awt.Rectangle;

public class SCircle extends Shape{
	
	private double radius;
	private Point loc;
	
	public SCircle(Point point, int i) {
		this.loc=point;
		this.radius=i;
	}

	public int getRadius() {
		return (int) this.radius;
	}
	
	public void setRadius(double d) {
		this.radius=d;
	}
//	public Ellipse2D.Double getEllipse() {
//		Ellipse2D.Double cir= new Ellipse2D.Double(loc.x, loc.y, this.radius*2, this.radius*2);
//		System.out.println(cir.height);
//		return cir;
//	}
	
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
		int wh =(int) (2*this.radius);
		Rectangle rect= new Rectangle(this.loc.x,this.loc.y,wh,wh);
		return rect;
//		return  getEllipse().getBounds2D();
	}

	@Override
	public void accept(ShapeVisitor sv) {
		sv.visitCircle(this);
	}

	@Override
	public void setSize(int dw, int dh) {
		// TODO Auto-generated method stub
		double d =(Math.sqrt(Math.pow(dw,2)+Math.pow(dh,2))/2);
		
		if(dw<0 || dh<0) {
			setRadius(this.radius-d);
		}
		else {
			setRadius(this.radius+d);
		}
	}
	
	

}
