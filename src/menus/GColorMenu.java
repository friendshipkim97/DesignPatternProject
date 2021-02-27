package menus;

import java.awt.Color;

import javax.swing.JColorChooser;

import main.GConstants;
import main.GConstants.EMenu;

public class GColorMenu extends GMenu {
	// attributes
	private static final long serialVersionUID = GConstants.serialVersionUID;

	// components
	//association

	public GColorMenu(String name) {
		super(name);

		this.setMnemonic('C');
		for(int i=14; i<16; i++) {
			this.add(menuItems.get(i));
		}
		}

	public void initialize() {
	}

	public void setLineColor() {

		Color selectedColor = JColorChooser.showDialog(this.drawingPanel,
				EMenu.eLineColor.getTitle(), this.drawingPanel.getForeground());
		this.drawingPanel.setLineColor(selectedColor);


	}
	public void setFillColor() {

		Color selectedColor = JColorChooser.showDialog(this.drawingPanel,
				EMenu.eFillColor.getTitle(), this.drawingPanel.getForeground());
		this.drawingPanel.setFillColor(selectedColor);

	}
	
}
