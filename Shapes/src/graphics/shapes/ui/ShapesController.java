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
	public static boolean r1;
	public static boolean r2;
	public static boolean r3;
	public static boolean r4;
	private boolean pressed;

	
	public ShapesController(Object newModel) {
		super(newModel);
		this.selec=new ArrayList<Shape>() ;
		//this.click=new ArrayList<Shape>();
		this.selecHash=new HashSet<Shape>();
		ShapesController.r1=false;
		ShapesController.r2=false;
		ShapesController.r3=false;
		ShapesController.r4=false;
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
		selecHash.clear();
		for(Iterator<Shape> it=selec.iterator();it.hasNext();){
			Shape sh = it.next();
			int dx= x-MP.x;
			int dy= y-MP.y;
			sh.translate(dx,dy);		
		}
		MP= new Point(x,y);
	}
	
	private void unselectAll() {
		for(Iterator<Shape> it=selec.iterator();it.hasNext();){
			((SelectionAttributes) it.next().getAttributes("Selection")).unselect();
		}
		selec.clear();
	}
	
//	private void changeSize(Shape sh,MouseEvent e) {
//		int dw = e.getPoint().x-MP.x;
//		int dh = e.getPoint().y-MP.y;
//		sh.setSize(dw, dh);
//		MP= new Point(e.getPoint().x,e.getPoint().y);
//	}
	
	@Override
	public void mousePressed(MouseEvent e)
	{
		MP=e.getPoint();
		Shape sh=getTarget(e);
		if(sh!=null) {
			((SelectionAttributes) sh.getAttributes("Selection")).select();
			selec.add(sh);
			this.pressed=true;
			
		}
		if(!selec.isEmpty()) {
		Shape s =selec.get(0);
		if(((SelectionAttributes) s.getAttributes("Selection")).isSelected()){
			Rectangle r1 =new Rectangle(s.getBounds().x-10, s.getBounds().y-10,10,10);
			Rectangle r2 =new Rectangle(s.getBounds().x+s.getBounds().width, s.getBounds().y+s.getBounds().height,10,10);
			Rectangle r3 =new Rectangle(s.getBounds().x+s.getBounds().width, s.getBounds().y-10, 10,10);
			Rectangle r4 =new Rectangle(s.getBounds().x-10, s.getBounds().y+s.getBounds().height, 10,10);
			if(r1.contains(e.getPoint())) {
				ShapesController.r1=true;
				System.out.println("grandi");

			}
			if(r2.contains(e.getPoint())) {
				ShapesController.r2=true;
				System.out.println("grandi");
			}
			if(r3.contains(e.getPoint())) {
				ShapesController.r3=true;
				System.out.println("grandi");

			}
			if(r4.contains(e.getPoint())) {
				ShapesController.r4=true;
				System.out.println("grandi");
			}
		}
		}
		else{
			selec.clear();
			unselectAll();
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
			this.pressed=false;
		}
		ShapesController.r1=false;
		ShapesController.r2=false;
		ShapesController.r3=false;
		ShapesController.r4=false;
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
		if(r2) {
			if(evt.getPoint().x <= selec.get(0).getBounds().x) {
				ShapesController.r2=false;
				ShapesController.r4=true;
			}
			else if(evt.getPoint().y <= selec.get(0).getBounds().y) {
				ShapesController.r2=false;
				ShapesController.r3=true;
			}
			else {
				int dw = evt.getPoint().x-MP.x;
				int dh = evt.getPoint().y-MP.y;
				selec.get(0).setSize(dw, dh);
				MP= new Point(evt.getPoint().x,evt.getPoint().y);
				System.out.println("r2");
			}
			
		}
		if(r1) {
			if(evt.getPoint().x >= selec.get(0).getBounds().x+selec.get(0).getBounds().width) {
				ShapesController.r1=false;
				ShapesController.r3=true;
				System.out.println("dans t");
			}
			else if(evt.getPoint().y >= selec.get(0).getBounds().y+selec.get(0).getBounds().height) {
				ShapesController.r1=false;
				ShapesController.r2=true;
				System.out.println("dans h");
			}
			else {
				int dw = MP.x-evt.getPoint().x;
				int dh = MP.y-evt.getPoint().y;
				Point pt = new Point(evt.getPoint().x+5,evt.getPoint().y+5);
				selec.get(0).setLoc(pt);
				selec.get(0).setSize(dw, dh);
				MP= new Point(evt.getPoint().x,evt.getPoint().y);
				System.out.println("r1");

			}
		}
		if(r4) {
			if(evt.getPoint().x >= selec.get(0).getBounds().x+selec.get(0).getBounds().width) {
				ShapesController.r4=false;
				ShapesController.r2=true;
			}
			else if(evt.getPoint().y <= selec.get(0).getBounds().y) {
				ShapesController.r4=false;
				ShapesController.r1=true;
			}
			else {
				int dw = MP.x-evt.getPoint().x;
				int dh = evt.getPoint().y-MP.y;
				Point pt = new Point(evt.getPoint().x+5,selec.get(0).getLoc().y);
				selec.get(0).setLoc(pt);
				selec.get(0).setSize(dw, dh);
				MP= new Point(evt.getPoint().x,evt.getPoint().y);
				System.out.println("r4");
			}
		}
		if(r3) {
			if(evt.getPoint().x <= selec.get(0).getBounds().x) {
				ShapesController.r3=false;
				ShapesController.r1=true;
			}
			else if(evt.getPoint().y >= selec.get(0).getBounds().y+selec.get(0).getBounds().height) {
				ShapesController.r3=false;
				ShapesController.r2=true;
			}
			else {
				int dw = evt.getPoint().x-MP.x;
				int dh = MP.y-evt.getPoint().y;
				Point pt = new Point(selec.get(0).getLoc().x,evt.getPoint().y+5);
				selec.get(0).setLoc(pt);
				selec.get(0).setSize(dw, dh);
				MP= new Point(evt.getPoint().x,evt.getPoint().y);
				System.out.println("r3");
			}
		}
		if(pressed) {
			translateSelected(evt.getPoint().x,evt.getPoint().y);
		}
		else if (!r1 && !r2 && !r3 && !r4){
			unselectAll();
		}
		
		super.getView().repaint();
	}
	

}
