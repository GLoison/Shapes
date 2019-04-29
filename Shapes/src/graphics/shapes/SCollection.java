package graphics.shapes;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;

public class SCollection extends Shape {
	private ArrayList <Shape> collection;
	
	
	
	private Point Loc;
	
	
	public Iterator<Shape> iterator() {
		return collection.iterator();
	}
	
	public SCollection() {
		this.collection=new ArrayList<Shape>();
	}
		
	public void add(Shape sh) {
		collection.add(sh);
	}
	
	@Override
	public Point getLoc() {
		this.Loc=getBounds().getLocation();
		return Loc;
	}

	@Override
	public void setLoc(Point pt) {
		this.Loc=pt;
	}

	@Override
	public void translate(int dx, int dy) {
		for(Iterator<Shape> it=collection.iterator();it.hasNext();){
			it.next().translate(dx, dy);
		}
	}

	@Override
	public Rectangle getBounds() {
		Rectangle bd= collection.get(0).getBounds();
		for(Iterator<Shape> it=collection.iterator();it.hasNext();){
			bd = bd.union(it.next().getBounds());
		}
		return bd;
	}

	@Override
	public void accept(ShapeVisitor sv) {
		sv.visitCollection(this);
		
	}

	@Override
	public void setSize(int dw, int dh) {
		// TODO Auto-generated method stub
		
	}

}
