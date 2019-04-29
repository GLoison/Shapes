package graphics.shapes.ui;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import graphics.shapes.SCollection;
import graphics.shapes.Shape;
import graphics.shapes.attributes.SelectionAttributes;
import graphics.ui.Controller;

public class ShapesController extends Controller {
	
	private ArrayList <Shape> selec;
	private HashSet<Shape> selecHash;
	private Point MP;

	
	public ShapesController(Object newModel) {
		super(newModel);
		this.selec=new ArrayList<Shape>() ;
		this.selecHash=new HashSet<Shape>();
		// TODO Auto-generated constructor stub
	}
	


	public Shape getTarget(MouseEvent e) {
		SCollection col= (SCollection) getModel();
		for(Iterator<Shape> it=col.iterator();it.hasNext();){
			Shape sh = it.next();
			Rectangle bd=sh.getBounds();
			if(bd.contains(e.getPoint())) {
				return sh;}
			}
		return null;
	}
	
	public void translateSelected(int x, int y) {
		selecHash.addAll(selec);
		selec =new ArrayList<Shape>(selecHash);
		if(selec!=null) {
			for(Iterator<Shape> it=selec.iterator();it.hasNext();){
				Shape sh = it.next();
				if (((SelectionAttributes) sh.getAttributes("Selection")).isSelected()) {
					int dx= x-MP.x;
					int dy= y-MP.y;
					sh.translate(dx,dy);
				}		
			}
			MP= new Point(x,y);
		}
	}
	
	private void unselectAll() {
		for(Iterator<Shape> it=selec.iterator();it.hasNext();){
			((SelectionAttributes) it.next().getAttributes("Selection")).unselect();
		}
		selec.clear();
	}
	
	private void changeSize(Shape sh,MouseEvent e) {
		int dw = e.getPoint().x-MP.x;
		int dh = e.getPoint().y-MP.y;
		sh.setSize(dw, dh);
		MP= new Point(e.getPoint().x,e.getPoint().y);
	}
	
	@Override
	public void mousePressed(MouseEvent e)
	{
		MP=e.getPoint();
		Shape sh=getTarget(e);
		if(sh!=null) {
			((SelectionAttributes) sh.getAttributes("Selection")).select();
			selec.add(sh);
		}	
		super.getView().repaint();
	}
	


	@Override
	public void mouseReleased(MouseEvent e)
	{
		Shape sh=getTarget(e);
		if(sh!=null) {
			selec.remove(sh);
			((SelectionAttributes) sh.getAttributes("Selection")).unselect();
		}
		super.getView().repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		
		if(e.isShiftDown()) {
			Shape sh=getTarget(e);
			if(sh!=null) {
				selec.add(sh);
				((SelectionAttributes) sh.getAttributes("Selection")).toggleSelection();
			}	
		}
		else {
			unselectAll();
		}
		super.getView().repaint();
	}
	
	

	@Override
	public void mouseMoved(MouseEvent evt)
	{
	}
	
	@Override
	public void mouseDragged(MouseEvent evt)
	{
		Shape s =selec.get(0);
		if(((SelectionAttributes) s.getAttributes("Selection")).isSelected()){
			Rectangle r1 =new Rectangle(s.getLoc().x-15, s.getLoc().y-15,20,20);
			Rectangle r2 =new Rectangle(s.getLoc().x+s.getBounds().width-5, s.getLoc().y+s.getBounds().height-5, 20,20);
			if(r2.contains(evt.getPoint())) {
				int dw = evt.getPoint().x-MP.x;
				int dh = evt.getPoint().y-MP.y;
				s.setSize(dw, dh);
				MP= new Point(evt.getPoint().x,evt.getPoint().y);
			}
			if(r1.contains(evt.getPoint())) {
				s.setLoc(evt.getPoint());
				int dw = MP.x-evt.getPoint().x;
				int dh = MP.y-evt.getPoint().y;
				s.setSize(dw, dh);
				MP= new Point(evt.getPoint().x-5,evt.getPoint().y-5);
			}
		}
		
		Rectangle bd = selec.get(0).getBounds();
		for(Iterator<Shape> it=selec.iterator();it.hasNext();){
			bd = bd.union(it.next().getBounds());
		}
		if(bd.contains(evt.getPoint())) {
			translateSelected(evt.getPoint().x,evt.getPoint().y);
		}
		
		super.getView().repaint();
	}
	

}
