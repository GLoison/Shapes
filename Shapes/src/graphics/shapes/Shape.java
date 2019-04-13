package graphics.shapes;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.Map;
import java.util.TreeMap;

import graphics.shapes.attributes.Attributes; 


public abstract class Shape {

	protected Map <String,Attributes> attributes ;
	
	public Shape() {
		this.attributes=new TreeMap<String,Attributes>();
	}
	
	
	public void addAttributes(Attributes at) {
		String s=at.getld();
		this.attributes.put(s, at);
	}
	
	public Attributes getAttributes(String at) {
		return this.attributes.get(at);
		
	}
	
	public abstract Point getLoc() ;
	public abstract void setLoc(Point pt);
	public abstract void translate(int dx,int dy);
	public abstract Rectangle getBounds();
	public abstract void accept(ShapeVisitor sv);






}
