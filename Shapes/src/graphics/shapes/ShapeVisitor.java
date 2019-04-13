package graphics.shapes;

public interface ShapeVisitor {

	public void visitRectangle(SRectangle rect);
	public void visitCircle(SCircle circle);
	public void visitText(SText txt);
	public void visitCollection(SCollection collec);
}
