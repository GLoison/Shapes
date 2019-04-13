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
	
	public Iterator<Shape> iterator() {
		return selec.iterator();
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
		selec.clear();
		for(Iterator<Shape> it=selec.iterator();it.hasNext();){
			((SelectionAttributes) it.next().getAttributes("Selection")).unselect();
		}
		
	}
	
	@Override
	public void mousePressed(MouseEvent e)
	{
		MP=e.getPoint();
		Shape sh=getTarget(e);
		if(sh!=null) {
			((SelectionAttributes) sh.getAttributes("Selection")).select();
			selec.add(sh);
			System.out.println("select");
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
			System.out.println("unselect");
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
				System.out.println("toggle");
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
		translateSelected(evt.getPoint().x,evt.getPoint().y);
		super.getView().repaint();
	}
	

}
