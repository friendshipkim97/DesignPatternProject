package frames;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import main.GConstants;
import main.GConstants.EMenu;
import shape.GEraser;

public class GToolBar extends JToolBar {
	// attributes
	private static final long serialVersionUID = GConstants.serialVersionUID;

	// components
	private ActionHadler actionHadler;
	private Vector<JButton> buttons;

	// associations
	GDrawingPanel drawingPanel;


	public GToolBar() {
		super();
		// create components
		this.actionHadler = new ActionHadler();

		this.buttons = new Vector<JButton>();	

		for (GConstants.EToolbar eTool: GConstants.EToolbar.values()) {
			ImageIcon originIcon = new ImageIcon(eTool.getPath());  
			Image originImg = originIcon.getImage(); 
			Image changedImg= originImg.getScaledInstance(25, 25, Image.SCALE_SMOOTH );
			ImageIcon Icon = new ImageIcon(changedImg);
			JButton button = new JButton(Icon);
			button.setToolTipText(eTool.getToolTipText());
			button.setBackground(Color.white);
			button.setActionCommand(eTool.toString());
			button.addActionListener(this.actionHadler);
			this.buttons.add(button);
			this.add(button);

		}		

	}

	public void setAssociation(GDrawingPanel drawingPanel) {
		this.drawingPanel = drawingPanel;		
	}
	public void initialize() {
		// set associations

		// set associative attributes
		this.buttons.get(0).doClick();

		// initialize components
	}


	class ActionHadler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			drawingPanel.setCurrentTool(GConstants.EToolbar.valueOf(event.getActionCommand()));

			if(event.getActionCommand()=="eEraser") {
				setEraser();
			}
			else if(event.getActionCommand()=="eShapeThick") {
				setShapeThick();
			}
			else if(event.getActionCommand()=="eFillShape") {
				setFillShape();
			}
			else if(event.getActionCommand()=="ePenFillShape") {
				setPenFillShape();
			}
			else if(event.getActionCommand()=="eTextArea") {
				addTextArea();
			}
			else if(event.getActionCommand()=="eTextAreaFinish") {
				addTextAreaFinish();
			}
		}

	


	}
	private void addTextAreaFinish() {
		this.drawingPanel.addTextAreaFinish();
	}

	private void setExpansion() {
		this.drawingPanel.setExpansion();
	}

	public void addTextArea() {
		this.drawingPanel.addTextArea();
	}

	public void setFillShape() {
		Color selectedColor = JColorChooser.showDialog(this.drawingPanel,
				"색 채우기", this.drawingPanel.getForeground());
		this.drawingPanel.setFillShape(selectedColor);
	}

	public void setPenFillShape() {
		Color selectedColor = JColorChooser.showDialog(this.drawingPanel,
				"테두리 색 채우기", this.drawingPanel.getForeground());
		this.drawingPanel.setPenFillShape(selectedColor);
	}


	public void setEraser() {
		JFrame eraser = new JFrame("지우개");
		eraser.setVisible(true);
		eraser.setSize(300,200);
		eraser.setLocation(250,250);
		eraser.setLocationRelativeTo(null);
		JPanel textPanel = new JPanel();

		JLabel jLabel = new JLabel("값 직접입력");
		jLabel.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel.setVerticalAlignment(SwingConstants.CENTER);
		eraser.add(jLabel,BorderLayout.NORTH);		
		JTextField textField = new JTextField(2);

		JSlider eraserSize = new JSlider(JSlider.HORIZONTAL, 0, 100, 10);
		eraserSize.setMajorTickSpacing(10);
		eraserSize.setMinorTickSpacing(1);
		eraserSize.setPaintTicks(true);
		eraserSize.setPaintLabels(true);

		textPanel.add(jLabel, BorderLayout.NORTH);
		textPanel.add(textField, BorderLayout.SOUTH);

		eraser.add(eraserSize, BorderLayout.CENTER);
		eraser.add(textPanel, BorderLayout.SOUTH);

		eraserSize.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {

				JSlider source = (JSlider)e.getSource();
				int temp = source.getValue();		
				drawingPanel.setEraserThick(temp);
				if(!source.getValueIsAdjusting()) {
					//temp = (int)source.getValue();
				}
			}
		});

		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTextField source = (JTextField)e.getSource();
				String a = source.getText();
				int tempInt = Integer.parseInt(a);
				drawingPanel.setEraserThick(tempInt);
				source.setText(""); 
			}
		});
	}

	public void setShapeThick() {

		JFrame shapeThick = new JFrame("도형두께수정");
		shapeThick.setVisible(true);
		shapeThick.setSize(300,200);
		shapeThick.setLocation(250,250);
		shapeThick.setLocationRelativeTo(null);

		JPanel textPanel = new JPanel();

		JLabel jLabel = new JLabel("값 직접입력");
		jLabel.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel.setVerticalAlignment(SwingConstants.CENTER);
		shapeThick.add(jLabel,BorderLayout.NORTH);		
		JTextField textField = new JTextField(2);

		JSlider setThick = new JSlider(JSlider.HORIZONTAL, 0, 30, 2);
		setThick.setMajorTickSpacing(10);
		setThick.setMinorTickSpacing(1);
		setThick.setPaintTicks(true);
		setThick.setPaintLabels(true);

		textPanel.add(jLabel, BorderLayout.NORTH);
		textPanel.add(textField, BorderLayout.SOUTH);

		shapeThick.add(setThick, BorderLayout.CENTER);
		shapeThick.add(textPanel, BorderLayout.SOUTH);

		setThick.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {

				JSlider source = (JSlider)e.getSource();
				int temp = source.getValue();		
				drawingPanel.setShapeThick(temp);
				if(!source.getValueIsAdjusting()) {
					//temp = (int)source.getValue();
				}
			}
		});
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTextField source = (JTextField)e.getSource();
				String a = source.getText();
				int tempInt = Integer.parseInt(a);
				drawingPanel.setShapeThick(tempInt);
				source.setText(""); 
			}
		});
	}
}
