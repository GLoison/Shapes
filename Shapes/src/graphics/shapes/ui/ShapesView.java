package graphics.shapes.ui;

import java.awt.Graphics;


import graphics.shapes.Shape;
import graphics.ui.Controller;
import graphics.ui.View;

public class ShapesView extends View{
	

	private ShapeDraftman draft;
	

	public ShapesView(Object model) {
		super(model);
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Shape model=(Shape)this.getModel();
		if(model==null) return;
		this.draft=new ShapeDraftman((Graphics)g);
		model.accept(this.draft);
	}
	
	public Controller defaultController(Object model) {
		return new ShapesController((Shape)model);
	}

	
	

}
