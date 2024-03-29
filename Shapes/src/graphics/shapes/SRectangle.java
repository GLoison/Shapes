package graphics.shapes;

import java.awt.Point;
import java.awt.Rectangle;

import graphics.shapes.attributes.ImageAttributes;

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
	
	public void setSize(int dw, int dh) {
		if(((ImageAttributes) getAttributes("Image")).isImage()){
			((ImageAttributes) getAttributes("Image")).setSize(dw);
		}
		else {
			rect.setSize(rect.width+dw, rect.height+dh);
		}
	}
	
	
	public void translate(int dx,int dy) {
		rect.translate(dx, dy);
	}
	
	public Rectangle getBounds() {
		ImageAttributes i= (ImageAttributes) getAttributes("Image");
		if (i.isImage()) {
			Rectangle bd = new Rectangle(this.getLoc().x,this.getLoc().y,i.getWidth(),(i.getImage().getHeight(null)*i.getWidth())/i.getImage().getWidth(null));
			return bd;
		}
		return rect.getBounds();
	}
	
	public void accept(ShapeVisitor sv) {
		sv.visitRectangle(this);
	}

}
