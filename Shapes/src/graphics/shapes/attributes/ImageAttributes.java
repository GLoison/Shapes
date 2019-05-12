package graphics.shapes.attributes;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageAttributes extends Attributes {
	private boolean image;
	private static final String id = "Image";
	@Override
	public String getld() {
		// TODO Auto-generated method stub
		return id;
	}
	
	private Image img;
	private int width;
	
	public ImageAttributes(File fl,boolean i,int width) throws IOException {
		this.img=ImageIO.read(fl);
		this.image=i;
		this.width=width;
	}
	
	public Image getImage() {
		return img;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public boolean isImage() {
		return image;
	}
	
	public void setSize(int dw) {
		this.width=this.width+dw;
	}
	
}
