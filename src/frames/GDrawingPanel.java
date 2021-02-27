package frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import clone.DeepClone;
import main.GConstants;
import main.GConstants.ECursor;
import main.GConstants.EToolbar;
import shape.GAnchors;
import shape.GGroup;
import shape.GRectangle;
import shape.GShape;
import shape.GShape.EDrawingStyle;
import transfomer.GDrawer;
import transfomer.GMover;
import transfomer.GResizer;
import transfomer.GRotator;
import transfomer.GTransformer;


public class GDrawingPanel extends JPanel implements KeyListener {
	//할 것 : 선택된 도형 지우기, 선택된 도형 테두리 두께 설정, 선택된 도형 테두리 색채우기 
	// attributes
	private static final long serialVersionUID = GConstants.serialVersionUID;
	private enum EDrawingState {
		eIdle, eDrawing, eTransforming
	}
	private EDrawingState eDrawingState;
	public GShape currentShape;
	private Color lineColor, fillColor;
	private int eraserThick;
	private int shapeThick;
	public boolean bUpdated;
	private boolean bDrawing;
	public GShape tempSelectedShape=null;
	private GGroup group;
	private Rectangle tempGroup;

	private int undoCount=0;
	private int redoCount=0;
	private boolean expansion = false;

	public GAnchors anchor;

	JLabel jPointLabel;
	JLabel jToolLabel;
	JTextField area;
	MenuItem item;
	PopupMenu pm;


	// components
	private MouseHandler mouseHandler;
	public Vector<GShape> shapes;
	public Vector<GShape> undoShapes;
	public Vector<GShape> redoShapes;
	private DeepClone deepCloner;

	public int textX;
	public int textY;
	public int textX2;
	public int textY2;
	public String textTemp; 

	// association components

	private Color fillShapeColor;
	private Color penFillShapeColor;
	private boolean setTextArea;
	JButton text;
	JPanel jPanel;
	JLabel shapeLabel;

	// working variables

	private GShape copyShape;
	private GShape currentTool;
	private GTransformer transformer;


	// constructors and initializers
	public GDrawingPanel() { // 컨스트럭트는 메모리가할당됐지만 객체가완전히생성된건아님
		// attributes
		this.setBackground(Color.white);
		this.eDrawingState = EDrawingState.eIdle;

		// components
		jPanel = new JPanel();
		jPointLabel = new JLabel();
		jToolLabel = new JLabel();
		shapeLabel = new JLabel();

		pm = new PopupMenu();
		item = new MenuItem("마우스좌표확인");
		pm.add(item);
		this.add(pm);

		jPointLabel.setText("X:" + "Y: " );
		jPointLabel.setSize(30,30);
		jToolLabel.setText("현재 도구:" );
		jPanel.add(jPointLabel);
		jPanel.add(jToolLabel);
		jPanel.setBackground(Color.white);
		this.setLayout(new BorderLayout());
		this.add(jPanel, BorderLayout.SOUTH);
		this.mouseHandler = new MouseHandler();
		this.addMouseListener(this.mouseHandler);
		this.addMouseMotionListener(this.mouseHandler);

		this.shapes = new Vector<GShape>();
		this.undoShapes = new Vector<GShape>();
		this.redoShapes = new Vector<GShape>();
		group = new GGroup();

		deepCloner = new DeepClone();

		area = new JTextField(10);

		// working variables
		this.currentTool = null;
		this.currentShape = null;
		this.lineColor = null;
		this.fillColor = null;
		this.fillShapeColor=null;
		this.penFillShapeColor=null;
		this.bUpdated = false;
		this.bDrawing=true;

		//this.add(area);

		this.eraserThick = 0;
		this.shapeThick = 0;

		//jPointLabel.addKeyListener(this);
		text = new JButton();
		text.setBorderPainted(false); //버튼테두리설정 
		text.setOpaque(false); //불투명한
		text.setBackground(Color.WHITE);
		text.setSize(100,100);

		text.setText("활성화");
		text.addKeyListener(this);
		jPanel.add(text);
	}

	public void initialize() {
		// set associations
		// set associative attributes
		// initialize components
		this.lineColor = this.getForeground(); // 라인color는 항상 값을 가지고 있어야한다. default는 foreground칼라로 잡아놓은것
		this.fillColor = this.getBackground();
		this.fillShapeColor=this.getForeground();
		this.eraserThick = 20;
		this.shapeThick = 1;
		this.transformer = null;
		text.doClick();
	}

	// setters & getters
	public Vector<GShape> getShapes() {
		return this.shapes;
	}

	@SuppressWarnings("unchecked")
	public void setShapes(Object shapes) {
		this.shapes = (Vector<GShape>) shapes;
		this.repaint(); // 전체를 다시그려라 라는뜻이다.
	}

	public void clearShapes() {
		this.shapes.clear();
		this.repaint();
	}

	public void setCurrentTool(EToolbar eToolBar) {
		this.jToolLabel.setText("현재 도구: " + eToolBar.getToolTipText());
		this.currentTool = eToolBar.getTool();

	}

	public boolean isUpdated() {
		return this.bUpdated;
	}

	public void setUpdated(boolean bUpdated) {
		this.bUpdated = bUpdated;
	}

	public void setLineColor(Color lineColor) {
		this.lineColor = lineColor;
	}

	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;

	}

	// methods
	public void paint(Graphics graphics) {

		Graphics2D graphics2D = (Graphics2D) graphics;

		//		AffineTransform transform = new AffineTransform();
		//		transform.rotate(Math.toRadians(90), EMainFrame.eWidth.getValue()/2,
		//				EMainFrame.eHeight.getValue()/2);

		//		if(expansion==true) {
		//		AffineTransform transform = new AffineTransform();
		//		transform.scale(2,2); //확대되서보이기  select범위까지 셀렉트범위를해줘야함 마우스에 affinetransform을적용 
		//		graphics2D.setTransform(transform);
		//
		//		}
		// user defined drawing
		super.paint(graphics2D);

		for (GShape shape : this.shapes) {
			shape.draw(graphics2D);
		}
	}

	public void setFillShape(Color fillShapeColor) {

		if(fillShapeColor!=null && tempSelectedShape!=null) {
			this.fillShapeColor=fillShapeColor;
			Graphics graphics = this.getGraphics();
			Graphics2D graphics2D = (Graphics2D) graphics;

			currentShape.setFillColor(fillShapeColor);
			currentShape.draw(graphics2D);
		}
	}

	public void setPenFillShape(Color penFillShapeColor) {

		if(penFillShapeColor!=null && tempSelectedShape!=null) {
			this.penFillShapeColor=penFillShapeColor;
			Graphics graphics = this.getGraphics();
			Graphics2D graphics2D = (Graphics2D) graphics;

			currentShape.setLineColor(penFillShapeColor);
			currentShape.draw(graphics2D);
		}
	}


	private void checkCursor(int x, int y) {
		GShape selectedShape = this.onShape(x, y);
		if (selectedShape == null) {
			this.setCursor(ECursor.eDefault.getCursor());
		} else {
			GAnchors.EAnchors eSelectedAnchor = selectedShape.getESelectedAnchor();
			switch(eSelectedAnchor) {
			case NW: this.setCursor(ECursor.eNW.getCursor()); break;
			case NN: this.setCursor(ECursor.eNN.getCursor()); break;
			case NE: this.setCursor(ECursor.eNE.getCursor()); break;
			case EE: this.setCursor(ECursor.eEE.getCursor()); break;
			case SE: this.setCursor(ECursor.eSE.getCursor()); break;
			case SS: this.setCursor(ECursor.eSS.getCursor()); break;
			case SW: this.setCursor(ECursor.eSW.getCursor()); break;
			case WW: this.setCursor(ECursor.eWW.getCursor()); break;
			case RR: this.setCursor(ECursor.eRotate.getCursor()); break;
			case MM: this.setCursor(ECursor.eMove.getCursor()); break;
			default: break;
			}

		}
	}

	private GShape onShape(int x, int y) {
		for (GShape shape : this.shapes) {
			if (shape.contains(x, y)) {
				return shape;
			}
		}
		return null;
	}

	private void setSelected(GShape selectedShape) {
		for (GShape shape : this.shapes) {
			shape.setSelected(false);
		}
		selectedShape.setSelected(true);
		this.repaint();
		tempSelectedShape = selectedShape;

	}

	public void keyPressed(KeyEvent e) {		
		int key = e.getKeyCode();
		if(e.isControlDown() && key == KeyEvent.VK_B) {
			copy();
		}
		else if(e.isControlDown() && key == KeyEvent.VK_V) {
			if(copyShape!=null) {
				paste();
			}
		}
		else if(e.isControlDown() && key == KeyEvent.VK_X) {
			cut();
			copy();
		}
	}

	private void initTransforming(GShape shape, int x, int y) {
		if(shape ==null) {
			this.bDrawing = true;
			// drawing 
			this.currentShape = this.currentTool.clone();
			this.currentShape.setLineColor(this.lineColor);
			this.currentShape.setFillColor(this.fillColor); // fillcolor가 있으면 들어가고 없으면 안들어갈것이다.
			this.currentShape.setEraserThick(eraserThick);
			this.currentShape.setShapeThick(shapeThick);
			this.transformer = new GDrawer(this.currentShape); 
			if(setTextArea==true) {
				//this.remove(area);
				setTextAreaPoint(x,y);
			}
		}
		else {
			this.bDrawing = false;
			// moving, resizing, rotating
			this.currentShape = shape;
			// transforming
			switch(shape.getESelectedAnchor()) {
			case MM:
				this.transformer = new GMover(this.currentShape); 	

				break;
			case RR:
				this.transformer = new GRotator(this.currentShape); 
				break;	
			default:
				this.transformer = new GResizer(this.currentShape, shape.getESelectedAnchor());
				break;
			}
		}
		Graphics2D g2D = (Graphics2D)this.getGraphics();
		this.transformer.initTransforming(g2D, x, y);
	}

	private void keepTransforming(int x, int y, Rectangle tempGroup2) {
		Graphics2D g2D = (Graphics2D)this.getGraphics();
		this.transformer.keepTransforming(g2D, x, y, tempGroup2);

	}

	private void finishTransforming(int x, int y) {
		if(setTextArea==true) {
			//setTextArea=false;
			setTextFinishPoint(x,y);
			System.out.println("X : "+x + "Y: " + y);
		
			area = new JTextField();
			area.setBackground(Color.white);
			area.setBounds(Math.min(textX,x), Math.min(y,textY), Math.abs(x-textX) , Math.abs(y-textY));
			this.setLayout(null);
			this.add(area);

			setTextArea=false;
		}
		if(currentTool.getSelected()==true) {
			int i=0;
			for(GShape shape : shapes) {
				if(shape.getShape().intersects(currentShape.getShape().getBounds())) {
					shape.setSelected(true); 
					i++;
				}
			}
			repaint();
		}
		else {
			Graphics2D g2D = (Graphics2D)this.getGraphics();
			this.transformer.finishTransforming(g2D, x, y);
			this.bUpdated = true;
			setSelected(this.currentShape);
			if(this.bDrawing) {
				this.shapes.add(this.currentShape);
				this.undoShapes.add(this.currentShape);
				this.redoShapes.add(this.currentShape);
			}
		}
	}

	void keepMDrawing(int x, int y) {
		Graphics2D g2D = (Graphics2D)this.getGraphics();

		// erase shape
		this.currentShape.draw(g2D);
		// resize cursor
		this.currentShape.setPoint(x, y);
		// draw shape
		this.currentShape.draw(g2D);
	}

	void continueTransforming(int x, int y) {
		Graphics2D g2D = (Graphics2D)this.getGraphics();
		this.transformer.continueTransforming(g2D, x, y);

	}

	void addShapes(int x, int y) {

		this.shapes.add(this.currentShape);


	}

	void addCurrentShape() {
		this.currentShape = this.currentTool.clone();
	}

	public void setEraserThick(int eraserThick) {
		this.eraserThick = eraserThick;

	}

	public void setShapeThick(int shapeThick) {
		this.shapeThick = shapeThick;

	}

	public void addTextArea() {
		setTextArea=true;
	}

	private void setTextAreaPoint(int x, int y) {
		this.textX=x;
		this.textY=y;
	}
	private void setTextFinishPoint(int x, int y) {
		this.textX2=x;
		this.textY2=y;
	}

	private String getText() {
		return textTemp;
	}

	private int getTextAreaX() {
		return textX;
	}
	private int getTextAreaY() {
		return textY;
	}

	private void textFinish() {
		setTextArea=false;
		//currentShape.setText(area.getText(), textX, textY+((textY2-textY)/2));
		Graphics2D g2D = (Graphics2D)this.getGraphics();
		//this.currentShape.draw(g2D);
//		g2D.drawString(area.getText(), textX, textY+((textY2-textY)/2));
//		
//		System.out.println(area.getText());
		shapes.remove(currentShape);
		//this.remove(area);
		repaint();
		
	}
	
public void addTextAreaFinish() {
	textFinish();
		
	}

	// inner class
	class MouseHandler implements MouseMotionListener, MouseListener {

		@Override
		public void mouseClicked(MouseEvent event) {

			//textFinish();

			if (event.getClickCount() == 1) {
				this.mouse1Clicked(event);
			} else if (event.getClickCount() == 2) {
				this.mouse2Clicked(event);
			}

		}

		// n point drawing
		private void mouse1Clicked(MouseEvent event) {
			int x = event.getX();
			int y = event.getY();
			GShape shape = onShape(x, y);
			if (shape == null) {
				if (currentTool.getEDrawingStyle() == EDrawingStyle.eNPoints && eDrawingState == EDrawingState.eIdle) {
					initTransforming(null, x, y);
					eDrawingState = EDrawingState.eDrawing;
				}

			} else {
				setSelected(shape);

			}
			if (currentTool.getEDrawingStyle() == EDrawingStyle.eNPoints && eDrawingState == EDrawingState.eDrawing) {
				continueTransforming(x, y);
			}

		}

		private void mouse2Clicked(MouseEvent event) {
			if (currentTool.getEDrawingStyle() == EDrawingStyle.eNPoints && eDrawingState == EDrawingState.eDrawing) {
				int x = event.getX();
				int y = event.getY();
				finishTransforming(x, y);
				eDrawingState = EDrawingState.eIdle;
			}
		}

		@Override
		public void mouseMoved(MouseEvent event) {

			int x = event.getX();
			int y = event.getY();

			jPointLabel.setText("X: " + x +" " + "Y: " + y );

			checkCursor(x, y);

			if (currentTool.getEDrawingStyle() == EDrawingStyle.eNPoints && eDrawingState == EDrawingState.eDrawing) {
				keepTransforming(x, y, tempGroup);
			}
		}

		// 2 point drawing
		@Override
		public void mousePressed(MouseEvent event) {

			int x = event.getX();
			int y = event.getY();
			GShape shape = onShape(x, y);



			if (eDrawingState == EDrawingState.eIdle) { // 무조건 idle상태에서 시작해야함
				if (shape == null) {
					if (currentTool.getEDrawingStyle() == EDrawingStyle.e2Points
							|| currentTool.getEDrawingStyle() == EDrawingStyle.eMPoints
							|| currentTool.getEDrawingStyle() == EDrawingStyle.eEPoints) {
						initTransforming(null, x, y);
						eDrawingState = EDrawingState.eDrawing;

					}
				} else {
					initTransforming(shape, x, y);
					eDrawingState = EDrawingState.eTransforming;

				}

			}
		}

		@Override
		public void mouseDragged(MouseEvent event) {
			int x = event.getX();
			int y = event.getY();

			if (eDrawingState == EDrawingState.eTransforming) {
				keepTransforming(x, y, tempGroup);

			} else if (eDrawingState == EDrawingState.eDrawing) {
				if (currentTool.getEDrawingStyle() == EDrawingStyle.e2Points) {

					keepTransforming(x, y,tempGroup);
				} else if (currentTool.getEDrawingStyle() == EDrawingStyle.eMPoints) {
					continueTransforming(x, y);
					keepTransforming(x, y,tempGroup);

				} else if (currentTool.getEDrawingStyle() == EDrawingStyle.eEPoints) {
					continueTransforming(x, y);
					keepTransforming(x, y,tempGroup);
				}

			}

		}

		@Override
		public void mouseReleased(MouseEvent event) {

			if(event.isPopupTrigger()){
				pm.show(jPanel, event.getX(), event.getY());
				JOptionPane.showMessageDialog(jPanel, "X좌표 : " + event.getX()+ "Y좌표 : " + event.getY());
			}

			int x = event.getX();
			int y = event.getY();

			if (eDrawingState == EDrawingState.eTransforming) {
				finishTransforming(x, y);
				eDrawingState = EDrawingState.eIdle;
			} else if (eDrawingState == EDrawingState.eDrawing) {
				if (currentTool.getEDrawingStyle() == EDrawingStyle.e2Points) {

					System.out.println("확인");
					
					finishTransforming(x, y);
				
					eDrawingState = EDrawingState.eIdle;
				} else if (currentTool.getEDrawingStyle() == EDrawingStyle.eMPoints
						|| currentTool.getEDrawingStyle() == EDrawingStyle.eEPoints
						|| currentTool.getEDrawingStyle() == EDrawingStyle.eSPoints) {
					finishTransforming(x, y);

					eDrawingState = EDrawingState.eIdle;
				}
			}

		}

		@Override
		public void mouseEntered(MouseEvent event) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent event) {
			// TODO Auto-generated method stub

		}

	}

	public void setExpansion() {
		expansion=true;

	}

	public void addImage(File imageFile) {
		GRectangle imageRectangle = new GRectangle(imageFile);
		this.shapes.add(imageRectangle);
		this.repaint();
	}


	public void copy() {
		this.copyShape = (GShape) this.deepCloner.clone(this.tempSelectedShape); // 원본을 바꾸면 안되니까


	}

	public void paste() {
		this.shapes.add((GShape) this.deepCloner.clone(this.copyShape));
		this.repaint();

	}

	public void undo() {

		if(shapes.size()>0) {
			undoCount = shapes.size()-1;
		}
		if(undoCount>=0&&shapes.size()>0) {
			this.shapes.remove(undoCount);
			this.repaint();
		}

	}

	public void redo() {

		if(undoCount<redoShapes.size()&&shapes.size()<redoShapes.size()) {
			this.shapes.add(undoCount, redoShapes.get(undoCount));
			this.repaint();
			undoCount++;
		}


	}

	public void cut() {
		if(tempSelectedShape!=null) {
			undoCount = this.shapes.indexOf(tempSelectedShape);
			this.shapes.removeElement(tempSelectedShape);
			repaint();

		}

	}

	public void group() {
		Graphics2D g2D = (Graphics2D)this.getGraphics();
		for(int i=shapes.size(); i>0; i--) {
			GShape check = shapes.get(i-1);
			if(check.isbSelected()==true) {
				check.setSelected(false);
				group.addshape(((GShape) this.deepCloner.clone(check)));
				shapes.remove(check);
			}
		}
		shapes.add(group);
		group.setSelected(true);
		tempGroup=group.getBounds();
		setTempGroup(tempGroup);

		repaint();

	}

	public void setTempGroup(Rectangle tempGroup2) {
		this.tempGroup=tempGroup2;
	}

	public void unGroup() {
		Vector<GShape> gShapes = new Vector<GShape>();
		for(int i=shapes.size(); i>0; i--) {
			GShape check = shapes.get(i-1);
			if(check instanceof GShape && check.isbSelected()) {
				for(GShape shape : group.getGroupShapes()) {
					shape.setSelected(true);
					gShapes.add(shape);
				}
				shapes.remove(check);
			}
		}
		shapes.addAll(gShapes);
		repaint();
	}

	public void print() {

		PrinterJob job = PrinterJob.getPrinterJob();
		job.setPrintable(new MyPrintable());
		try{
			job.print();
		}catch(PrinterException pe){};
	}
	class MyPrintable implements Printable{
		public int print(Graphics g, PageFormat pf, int index){
			if(index == 0){
				g.translate((int)(pf.getImageableX()), (int)(pf.getImageableY()));
				jPanel.paint(g);
				return Printable.PAGE_EXISTS;
			}
			return Printable.NO_SUCH_PAGE;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void keyReleased(KeyEvent e) {}

	


}


