package menus;
import main.GConstants;

public class GEditMenu extends GMenu {
	// attributes
	private static final long serialVersionUID = GConstants.serialVersionUID;
	
	// components
	
	public GEditMenu(String name) {
		super(name);
		
		this.setMnemonic('E');
		for(int i=7; i<14; i++) {
		this.add(menuItems.get(i));
	}
	}

	public void initialize() {
	}
	
	public void undo() {
		this.drawingPanel.undo();
	}
	
	public void redo() {
		this.drawingPanel.redo();
	}
	
	public void cut() {
		this.drawingPanel.cut();
	}
	
	public void group() {
		this.drawingPanel.group();
		
	}
	
	public void unGroup() {
		this.drawingPanel.unGroup();
	}
	
	public void copy() { this.drawingPanel.copy();	}
	public void paste() {	this.drawingPanel.paste();}
}
