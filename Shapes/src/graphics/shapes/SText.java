package graphics.shapes;

import java.awt.Point;
import java.awt.Rectangle;

import graphics.shapes.attributes.FontAttributes;

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
		this.loc=pt;
	}

	@Override
	public void translate(int dx, int dy) {
		this.loc.translate(dx, dy);
	}

	@Override
	public Rectangle getBounds() {
		Rectangle ft = ((FontAttributes) getAttributes("Font")).getBounds(text);
		return new Rectangle(loc.x,loc.y-ft.height,ft.width,ft.height);
		
	}

	@Override
	public void accept(ShapeVisitor sv) {
		sv.visitText(this);
		
	}



}
