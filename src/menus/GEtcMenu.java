package menus;

import javax.swing.JOptionPane;

public class GEtcMenu extends GMenu {

	private static final long serialVersionUID = 1L;

	public GEtcMenu(String name) {
		super(name);
		
		this.setMnemonic('T');
		this.add(menuItems.get(16));
	
	}
	
	public void initialize() {
	}

	public void ProgramInfo() {	
		JOptionPane.showMessageDialog(null, "<���α׷�����>" + "\n\n" +
		"���α׷� �̸� : �׸���" + "\n" + "������ : ������");
	}
}
