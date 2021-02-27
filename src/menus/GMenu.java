package menus;

import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.Vector;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import frames.GDrawingPanel;
import main.GConstants;

public abstract class GMenu extends JMenu{
	private static final long serialVersionUID = 1L;

	protected Vector<JMenuItem> menuItems;
	protected ActionHandler actionHandler;
	protected GDrawingPanel drawingPanel;

	public GMenu(String name) {
		super(name);

		this.menuItems= new Vector<JMenuItem>();
		this.actionHandler = new ActionHandler();

		for (GConstants.EMenu eMenu: GConstants.EMenu.values()) {
			JMenuItem menuItem = new JMenuItem(eMenu.getTitle());
			menuItem.setAccelerator(KeyStroke.getKeyStroke 
					(eMenu.getKey(), InputEvent.CTRL_DOWN_MASK));
			menuItem.setActionCommand(eMenu.getActionCommand());	
			menuItem.addActionListener(this.actionHandler);
			this.menuItems.add(menuItem);	
		}

	}

	public abstract void initialize();

	public void setAssociation(GDrawingPanel drawingPanel) {

		this.drawingPanel = drawingPanel;


	}

	private void invokeMethod(String methodName) {

		try {
			this.getClass().getMethod(methodName).invoke(this);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected class ActionHandler implements ActionListener{	

		@Override
		public void actionPerformed(ActionEvent event) {
			String methodName = event.getActionCommand();
			invokeMethod(methodName);
		}
	}


}
