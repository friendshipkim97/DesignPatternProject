package main;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import frames.GMainFrame;

public class GMain {

	public static void main(String[] args) {

		GMainFrame mainFrame = new GMainFrame();
		mainFrame.initialize();

		mainFrame.setVisible(true);
	}
}
