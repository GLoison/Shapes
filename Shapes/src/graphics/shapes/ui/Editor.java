package graphics.shapes.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;

import graphics.shapes.SCircle;
import graphics.shapes.SCollection;
import graphics.shapes.SRectangle;
import graphics.shapes.SText;
import graphics.shapes.STriangle;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.FontAttributes;
import graphics.shapes.attributes.ImageAttributes;
import graphics.shapes.attributes.SelectionAttributes;

public class Editor extends JFrame
{
	ShapesView sview;
	SCollection model;
	
	
	public Editor() throws IOException
	{	
		super("Shapes Editor");

		this.addWindowListener(new java.awt.event.WindowAdapter()
		{
			public void windowClosing(java.awt.event.WindowEvent evt)
			{
				System.exit(0);
			}
		});
		
		this.buildModel();
		
		this.sview = new ShapesView(this.model);
		this.sview.setPreferredSize(new Dimension(300,300));
		this.getContentPane().add(this.sview, java.awt.BorderLayout.CENTER);
	}

	
	private void buildModel() throws IOException
	{
		
		this.model = new SCollection();
		this.model.addAttributes(new SelectionAttributes());
		
		SRectangle r = new SRectangle(new Point(10,10),50,100);
		r.addAttributes(new ColorAttributes(false,false,Color.BLUE,Color.RED));
		r.addAttributes(new SelectionAttributes());
		r.addAttributes(new ImageAttributes(new File("Snow Ball.jpg"),true,100));
		this.model.add(r);
		
		SRectangle e = new SRectangle(new Point(10,10),50,100);
		e.addAttributes(new ColorAttributes(true,true,Color.BLUE,Color.RED));
		e.addAttributes(new SelectionAttributes());
		e.addAttributes(new ImageAttributes(new File("Snow Ball.jpg"),false,100));
		this.model.add(e);
		
		SCircle c = new SCircle(new Point(100,100),10);
		c.addAttributes(new ColorAttributes(true,false,Color.BLUE,Color.RED));
		c.addAttributes(new SelectionAttributes());
		this.model.add(c);
		
		SText t= new SText(new Point(100,100),"hello");
		t.addAttributes(new ColorAttributes(true,true,Color.YELLOW,Color.BLUE));
		t.addAttributes(new FontAttributes());
		t.addAttributes(new SelectionAttributes());
		this.model.add(t);
		
		STriangle tr= new STriangle(new Point(10,10),new Point(25,50),new Point(40,30));
		tr.addAttributes(new ColorAttributes(true,true,Color.BLUE,Color.RED));
		tr.addAttributes(new SelectionAttributes());
		this.model.add(tr);
		
		SCollection sc = new SCollection();
		sc.addAttributes(new SelectionAttributes());
		r= new SRectangle(new Point(20,30),30,30);
		r.addAttributes(new ColorAttributes(true,false,Color.MAGENTA,Color.BLUE));
		r.addAttributes(new SelectionAttributes());
		r.addAttributes(new ImageAttributes(new File("IMG_1581.jpg"),false,100));
		sc.add(r);
		c = new SCircle(new Point(150,100),20);
		c.addAttributes(new ColorAttributes(false,true,Color.BLUE,Color.DARK_GRAY));
		c.addAttributes(new SelectionAttributes());
		sc.add(c);
		this.model.add(sc);
	}
	
	public static void main(String[] args) throws IOException
	{
		Editor self = new Editor();
		self.pack();
		self.setVisible(true);
	}
}
