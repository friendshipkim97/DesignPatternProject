package frames;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.thehowtotutorial.splashscreen.JSplash;

import main.GConstants;
import net.infonode.properties.util.PropertyChangeListener;

public class GMainFrame extends JFrame{
	// attributes
	private static final long serialVersionUID = GConstants.serialVersionUID;

	// components
	private GMenuBar menuBar;
	private GToolBar toolBar;
	private GDrawingPanel drawingPanel;

	PropertyChangeListener propertyListener;
	public GMainFrame() {
		super();
		try {
			UIManager.setLookAndFeel("net.infonode.gui.laf.InfoNodeLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JSplash jSplash = new JSplash(GMainFrame.class.getResource("그림판.jpg"),
				true, true, false, "V1", null, Color.RED, Color.black);
		jSplash.splashOn();
		jSplash.setProgress(20, "Init");
		try {
			Thread.sleep(1000);		
			jSplash.setProgress(20, "Init");
			Thread.sleep(100);
			jSplash.setProgress(40, "Loading");
			Thread.sleep(1000);
			jSplash.setProgress(60, "Applying Configs");
			Thread.sleep(1000);
			jSplash.setProgress(80, "Starting Program");
			Thread.sleep(1000);
			jSplash.splashOff();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



		// initialize attributes
		this.setTitle("그림판");
		this.setSize(GConstants.EMainFrame.eWidth.getValue(), 
				GConstants.EMainFrame.eHeight.getValue());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());

		ImageIcon img = new ImageIcon("data/그림판.jpg");
		this.setIconImage(img.getImage());

		// create,register components
		WindowActionHandler windowActionHandler = new WindowActionHandler();
		this.addWindowListener(windowActionHandler);

		this.menuBar = new GMenuBar();
		menuBar.setBackground(Color.lightGray);
		this.setJMenuBar(this.menuBar);

		this.toolBar = new GToolBar();
		toolBar.setBackground(Color.lightGray);
		toolBar.setFloatable(false);
		this.add(this.toolBar, BorderLayout.NORTH);

		this.drawingPanel = new GDrawingPanel();
		this.add(this.drawingPanel, BorderLayout.CENTER);

		//		propertyListener = new PropertyChangeListener();
		//		this.addPropertyChangeListener(propertyListener);

        
	}



	public void initialize() {
		// set associations
		this.toolBar.setAssociation(this.drawingPanel);	
		this.menuBar.setAssociation(this.drawingPanel);	
		//this.mousePoints.setAssociation(this.drawingPanel);
		// initialize associative attributes

		// initialize components
		this.menuBar.initialize();
		this.toolBar.initialize();		
		//this.mousePoints.initialize();
		this.drawingPanel.initialize();	
	}

	public class WindowActionHandler extends WindowAdapter{

		@Override
		public void windowClosing(WindowEvent e) {
			menuBar.doCheckSave();
			//			menuBar.getFileMenu().checkSave();
		}

	}

	//	public class PropertyListener implements PropertyChangeListener {
	//
	//		public void propertyChanged(Property drawingPanel, Object arg1, Object arg2, Object arg3) {
	//			if()) {
	//				
	//			}
	//			
	//		}
	//
	//
	//	}
}
