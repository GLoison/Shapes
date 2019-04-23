package graphics.shapes.attributes;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;

import graphics.shapes.ui.ShapeDraftman;



public class FontAttributes extends Attributes{

	private static final String id = "Font";
	
	@Override
	public String getld() {
		return id;
	}
	public Graphics g;
	public Font font;
	public Color fontColor;
	
	public FontAttributes() {
		this.fontColor=Color.BLACK;
	}
	
	public Rectangle getBounds(String rect) {
		//System.out.println(font);
		FontMetrics fm= ShapeDraftman.graph.getFontMetrics();
		int h=fm.getHeight();
		int w= fm.stringWidth(rect);
		Rectangle bd = new Rectangle(0,0,w,h);
		return bd;
	}


}
