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
		//this.font= new Font("Helvetica",Font.BOLD,50);
	}
	
	public void setFont(Float s) {
	//	System.out.println(ShapeDraftman.graph.getFont());new Font(ft.getName(),ft.getStyle(),(float)s) ;
		Font ft = ShapeDraftman.graph.getFont();
		this.font= ft.deriveFont(ft.getSize()+s);
	//	ShapeDraftman.graph.setFont(this.font);
		//	System.out.println(font);
	}
	
	public Rectangle getBounds(String rect) {
		//System.out.println(font);
		
		FontMetrics fm= ShapeDraftman.graph.getFontMetrics();
		int h=fm.getHeight();
		int w= fm.stringWidth(rect);
		int a=fm.getAscent();
		Rectangle bd = new Rectangle(0,a,w,h);
		return bd;
	}
}
