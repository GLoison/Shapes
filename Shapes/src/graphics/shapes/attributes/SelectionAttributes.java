package graphics.shapes.attributes;

public class SelectionAttributes extends Attributes{

	private boolean selected;
	private static final String id = "Selection";
	@Override
	public String getld() {
		return id;
	}
	
	public boolean isSelected() {
		return selected;
	}
	
	public void select() {
		selected=true;
	}
	
	public void unselect() {
		selected=false;
	}

	public void toggleSelection() {
		if(isSelected()) {
			unselect();
		}
		else select();
	}
}
