package graphics.shapes.attributes;

import java.awt.Color;

public class ColorAttributes extends Attributes{
	private boolean filled;
	private boolean stroked;
	private Color filledColor;
	private Color strokedColor;
	
	
	private static final String id = "Color";
	
	@Override
	public String getld() {
		return id;
	}
	public ColorAttributes(boolean f, boolean s, Color c1, Color c2) {
		this.filled=f;
		this.stroked=s;
		this.filledColor=c1;
		this.strokedColor=c2;
	}
	
	public boolean isFilled() {
		return filled;
	}
	
	public boolean isStroked() {
		return stroked;
	}
	
	public Color getFilledColor() {
		return filledColor;
	}
	
	public Color getStrokedColor() {
		return strokedColor;
	}
	
		
	

}
