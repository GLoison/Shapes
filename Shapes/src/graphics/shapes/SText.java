package graphics.shapes;

import java.awt.Point;
import java.awt.Rectangle;

import graphics.shapes.attributes.FontAttributes;
import graphics.shapes.ui.ShapesController;

public class SText extends Shape {
	
	private String text;
	private Point loc;
	
	public SText(Point point, String string) {
		this.loc=point;
		this.text=string;
	}

	public String getText() {
		return this.text;
	}
	
	public void setText(String txt) {
		this.text=txt;
	}
		
	@Override
	public Point getLoc() {
		return this.loc;
	}

	@Override
	public void setLoc(Point pt) {
		Rectangle ft = ((FontAttributes) getAttributes("Font")).getBounds(text);
		if(!ShapesController.r4)
			this.loc=new Point(pt.x,pt.y+ft.y);
		else
			this.loc=new Point(pt.x,pt.y);
			
	}

	@Override
	public void translate(int dx, int dy) {
		this.loc.translate(dx, dy);
	}

	@Override
	public Rectangle getBounds() {
		Rectangle ft = ((FontAttributes) getAttributes("Font")).getBounds(text);
		return new Rectangle(loc.x,loc.y-ft.y,ft.width,ft.height);
		
	}

	@Override
	public void accept(ShapeVisitor sv) {
		sv.visitText(this);
		
	}

	@Override
	public void setSize(int dw, int dh) {
		// TODO Auto-generated method stub
		double d =(Math.sqrt(Math.pow(dw,2)+Math.pow(dh,2))/2);
		double c=(Math.sqrt(Math.pow(d,2)/2));
		float s=(float) (c*0.75);
		if(dw<0 || dh<0) {
			((FontAttributes) getAttributes("Font")).setFont(-s);
		}
		else {
			((FontAttributes) getAttributes("Font")).setFont(s);
		}
	}



}
