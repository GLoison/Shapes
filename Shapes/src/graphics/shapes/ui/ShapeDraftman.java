package graphics.shapes.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Iterator;

import graphics.shapes.SCircle;
import graphics.shapes.SCollection;
import graphics.shapes.SRectangle;
import graphics.shapes.SText;
import graphics.shapes.STriangle;
import graphics.shapes.Shape;
import graphics.shapes.ShapeVisitor;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.FontAttributes;
import graphics.shapes.attributes.ImageAttributes;
import graphics.shapes.attributes.SelectionAttributes;

public class ShapeDraftman implements ShapeVisitor{
	
	public static Graphics graph;
	
	public ColorAttributes DEFAULTCOLORATTRIBUTES;
	
	public ShapeDraftman(Graphics g) {
		ShapeDraftman.graph=g;
	}

	
	@Override
	public void visitRectangle(SRectangle rect) {
		
		ColorAttributes c = (ColorAttributes) rect.getAttributes("Color");
		SelectionAttributes s= (SelectionAttributes) rect.getAttributes("Selection");
		ImageAttributes i= (ImageAttributes) rect.getAttributes("Image");
		
		if (c.isStroked()) {
			ShapeDraftman.graph.setColor(c.getStrokedColor());
			ShapeDraftman.graph.drawRect(rect.getLoc().x, rect.getLoc().y, rect.getRect().width,rect.getRect().height);
		}
		
		
		if (c.isFilled()) {
			ShapeDraftman.graph.setColor(c.getFilledColor());
			ShapeDraftman.graph.fillRect(rect.getLoc().x+1, rect.getLoc().y+1, rect.getRect().width-1,rect.getRect().height-1);
		}
		
		if (i.isImage()) {
			ShapeDraftman.graph.drawImage(i.getImage(), rect.getLoc().x, rect.getLoc().y,rect.getBounds().width,rect.getBounds().height, null);
		}
		
		if(s.isSelected()) {
			ShapeDraftman.graph.setColor(Color.BLACK);
			ShapeDraftman.graph.drawRect(rect.getLoc().x-10, rect.getLoc().y-10, 10,10);
			ShapeDraftman.graph.drawRect(rect.getLoc().x+rect.getBounds().width, rect.getLoc().y+rect.getBounds().height, 10,10);
			ShapeDraftman.graph.drawRect(rect.getLoc().x+rect.getBounds().width, rect.getLoc().y-10, 10,10);
			ShapeDraftman.graph.drawRect(rect.getLoc().x-10, rect.getLoc().y+rect.getBounds().height, 10,10);
		}
		
	}

	@Override
	public void visitCircle(SCircle circle) {
		ColorAttributes c = (ColorAttributes) circle.getAttributes("Color");
		SelectionAttributes s= (SelectionAttributes) circle.getAttributes("Selection");
		
		if (c.isFilled()) {
			ShapeDraftman.graph.setColor(c.getFilledColor());
			ShapeDraftman.graph.fillOval(circle.getLoc().x, circle.getLoc().y, circle.getRadius()*2, circle.getRadius()*2);
		}
		if (c.isStroked()) {
			ShapeDraftman.graph.setColor(c.getStrokedColor());
			ShapeDraftman.graph.drawOval(circle.getLoc().x, circle.getLoc().y, circle.getRadius()*2, circle.getRadius()*2);
		}
		if(s.isSelected()) {
			ShapeDraftman.graph.setColor(Color.BLACK);
			ShapeDraftman.graph.drawRect(circle.getLoc().x-10, circle.getLoc().y-10, 10,10);
			ShapeDraftman.graph.drawRect(circle.getBounds().x+circle.getBounds().width, circle.getBounds().y+circle.getBounds().height, 10,10);
			ShapeDraftman.graph.drawRect(circle.getLoc().x+circle.getBounds().width, circle.getLoc().y-10, 10,10);
			ShapeDraftman.graph.drawRect(circle.getLoc().x-10, circle.getLoc().y+circle.getBounds().height, 10,10);
		}
	}

	@Override
	public void visitText(SText txt) {
		ColorAttributes c = (ColorAttributes) txt.getAttributes("Color");
		FontAttributes f=(FontAttributes) txt.getAttributes("Font");
		SelectionAttributes s= (SelectionAttributes) txt.getAttributes("Selection");
		
		ShapeDraftman.graph.setFont(f.font);
			
		if (c.isStroked()) {
			ShapeDraftman.graph.setColor(c.getStrokedColor());
			ShapeDraftman.graph.drawRect(txt.getBounds().x, txt.getBounds().y, txt.getBounds().width,txt.getBounds().height);
		}
		
		
		if (c.isFilled()) {
			ShapeDraftman.graph.setColor(c.getFilledColor());
			ShapeDraftman.graph.fillRect(txt.getBounds().x+1, txt.getBounds().y+1, txt.getBounds().width-1,txt.getBounds().height-1);
		}
		
		ShapeDraftman.graph.setColor(f.fontColor);
		ShapeDraftman.graph.drawString(txt.getText(), txt.getLoc().x, txt.getLoc().y);

		if(s.isSelected()) {
			ShapeDraftman.graph.setColor(Color.BLACK);
			ShapeDraftman.graph.drawRect(txt.getBounds().x-10, txt.getBounds().y-10, 10,10);
			ShapeDraftman.graph.drawRect(txt.getBounds().x+txt.getBounds().width, txt.getBounds().y+txt.getBounds().height, 10,10);
			ShapeDraftman.graph.drawRect(txt.getBounds().x+txt.getBounds().width, txt.getBounds().y-10, 10,10);
			ShapeDraftman.graph.drawRect(txt.getBounds().x-10, txt.getBounds().y+txt.getBounds().height, 10,10);
		}
	}

	@Override
	public void visitCollection(SCollection collec) {
		SelectionAttributes s= (SelectionAttributes) collec.getAttributes("Selection");
		
		for(Iterator<Shape> it=collec.iterator();it.hasNext();){
			it.next().accept(this);
		}
		
		if(s.isSelected()) {
			ShapeDraftman.graph.setColor(Color.BLACK);
			ShapeDraftman.graph.drawRect(collec.getBounds().x-10, collec.getBounds().y-10, 10,10);
			ShapeDraftman.graph.drawRect(collec.getLoc().x+collec.getBounds().width, collec.getLoc().y+collec.getBounds().height, 10,10);
			ShapeDraftman.graph.drawRect(collec.getLoc().x+collec.getBounds().width, collec.getLoc().y-10, 10,10);
			ShapeDraftman.graph.drawRect(collec.getLoc().x-10, collec.getLoc().y+collec.getBounds().height, 10,10);
		}

	}


	@Override
	public void visitTriangle(STriangle tria) {
		// TODO Auto-generated method stub
		ColorAttributes c = (ColorAttributes) tria.getAttributes("Color");
		SelectionAttributes s= (SelectionAttributes) tria.getAttributes("Selection");
		
		if (c.isStroked()) {
			ShapeDraftman.graph.setColor(c.getStrokedColor());
			ShapeDraftman.graph.drawPolygon(tria.getTriangle());
		}
		
		
		if (c.isFilled()) {
			ShapeDraftman.graph.setColor(c.getFilledColor());
			ShapeDraftman.graph.fillPolygon(tria.getTriangle());
		}
		
		if(s.isSelected()) {
			ShapeDraftman.graph.setColor(Color.BLACK);
			ShapeDraftman.graph.drawRect(tria.getBounds().x-10, tria.getBounds().y-10, 10,10);
			ShapeDraftman.graph.drawRect(tria.getLoc().x+tria.getBounds().width, tria.getLoc().y+tria.getBounds().height, 10,10);
			ShapeDraftman.graph.drawRect(tria.getLoc().x+tria.getBounds().width, tria.getLoc().y-10, 10,10);
			ShapeDraftman.graph.drawRect(tria.getLoc().x-10, tria.getLoc().y+tria.getBounds().height, 10,10);
		}
	}
}
