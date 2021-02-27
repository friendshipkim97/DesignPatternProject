package main;


import shape.GShape;
import shape.GRectangle;
import shape.GOval;

import menus.GMenu;
import menus.GFileMenu;
import menus.GEditMenu;
import menus.GEtcMenu;

import java.awt.Cursor;

import menus.GColorMenu;

import shape.GLine;
import shape.GPolygon;

import shape.GEraser;
import shape.GCurve;
import shape.GDottedCurve;
import shape.GShapeThick;
import shape.GFillShape;
import shape.GTextArea;
import shape.GGroup;
import shape.GPenFillShape;
import shape.GSelected;
import shape.GTriangle;
import shape.GRombus;
import shape.GSelectedDottedCurve;
import shape.GStar;
import shape.GExpansion;
import shape.GTextAreaFinish;

public class GConstants {

	public static final long serialVersionUID = 1L;

	public GConstants() {
	}

	public enum EMainFrame {
		eWidth(1200),
		eHeight(800);

		private int value;		
		private EMainFrame(int value) {
			this.value = value;
		}	
		public int getValue() {
			return this.value;
		}
	}

	public enum EMenubar {
		eFile(new GFileMenu("����")),
		eEdit(new GEditMenu("����")),
		eColor(new GColorMenu("�÷�")),
		eEtc(new GEtcMenu("��Ÿ"));

		private GMenu menu;
		private EMenubar(GMenu menu) {
			this.menu = menu;
		}		
		public GMenu getMenu() {
			return this.menu;
		}
	}


	//	public enum EFileMenu {
	//		eNew("New", "nnew"),
	//		eOpen("����", "open"),
	//	    eImageOpen("�̹��� ����", "ImageOpen"),
	//		eSave("����", "save"),
	//		eSaveAs("�ٸ��̸�����", "saveAs"),
	//		ePrint("����Ʈ", "print"),
	//		eQuit("����", "exit");
	//		
	//		private String title;	
	//		private String actionCommand;
	//		private EFileMenu(String title, String actionCommand) {
	//			this.title = title;
	//			this.actionCommand = actionCommand;
	//		}		
	//		public String getTitle() {
	//			return this.title;
	//		}
	//		public String getActionCommand() {
	//			return this.actionCommand;
	//		}
	//	}

	public enum EFileMenuEtc {
		eisUpdated1("���泻���� ���� �ұ��?"),
		eisUpdated2("���� Ȯ��"),
		eopenTitle("���Ͽ���"),
		esaveTitle("��������"),
		ecurrentDirectory("./");

		private String title;		
		private EFileMenuEtc(String title) {
			this.title = title;
		}		
		public String getTitle() {
			return this.title;
		}
	}

	//	public enum EEditMenu {
	//		eUndo("�ǵ�����", "undo"),
	//		eRedo("�ٽý���", "redo"),
	//		eCopy("����", "copy"),
	//		eCut("�ڸ���", "cut"),
	//		ePaste("�ٿ��ֱ�", "paste"),
	//		eGroup("�׷�", "group"),
	//		eUnGroup("�׷� ����","unGroup");
	//		
	//		private String title;		
	//		private String actionCommand;
	//		private EEditMenu(String title, String actionCommand) {
	//			this.title = title;
	//			this.actionCommand=actionCommand;
	//		}		
	//		public String getTitle() {
	//			return this.title;
	//		}
	//		public String getActionCommand() {
	//			return this.actionCommand;
	//		}
	//	}
	//	
	//	public enum EColorMenu {
	//		eLineColor("���� ��", "setLineColor"),
	//		eFillColor("ä��� ��", "setFillColor");
	//		
	//		private String title;		
	//		private String actionCommand;
	//		private EColorMenu(String title, String actionCommand) {
	//			this.title = title;
	//			this.actionCommand=actionCommand;
	//		}		
	//		public String getTitle() {
	//			return this.title;
	//		}
	//		public String getActionCommand() {
	//			return this.actionCommand;
	//		}
	//	}	

	public enum EMenu{
		eNew("New", "nnew", 'N'),
		eOpen("����", "open", 'O'),
		eImageOpen("�̹��� ����", "imageOpen", 'I'),
		eSave("����", "save", 'S'),
		eSaveAs("�ٸ��̸�����", "saveAs", 'A'),
		ePrint("����Ʈ", "print", 'P'),
		eQuit("����", "exit", 'Q'),
		eUndo("�ǵ�����", "undo", 'U'),
		eRedo("�ٽý���", "redo", 'R'),
		eCopy("����", "copy", 'Y'),
		eCut("�ڸ���", "cut", 'C'),
		ePaste("�ٿ��ֱ�", "paste", 'P'),
		eGroup("�׷�", "group", 'G'),
		eUnGroup("�׷� ����","unGroup", 'U'),
		eLineColor("���� ��", "setLineColor", 'L'),
		eFillColor("ä��� ��", "setFillColor", 'F'),
		eProgramInfo("���α׷� ����", "ProgramInfo", 'P');

		private String title;	
		private String actionCommand;
		private char key;
		private EMenu(String title, String actionCommand, char key) {
			this.title = title;
			this.actionCommand = actionCommand;
			this.key=key;
		}		
		public String getTitle() {
			return this.title;
		}
		public String getActionCommand() {
			return this.actionCommand;
		}
		public char getKey() {
			return this.key;
		}
	}

	public enum EToolbar {
		eRectangle("�׸�", new GRectangle(), "data/�׸�.png", "�׸�׸���"),
		eOval("��", new GOval(), "data/��.png", "���׸���"),
		eLine("����", new GLine(), "data/����.png", "�����׸���"),
		ePolygon("�ٰ���", new GPolygon(), "data/�ٰ���.png", "�ٰ����׸���"),
		eCurve("�", new GCurve(), "data/�.png", "��׸���"),
		eDottedLine("����", new GDottedCurve(), "data/����.png", "�����׸���"),
		eEraser("���찳", new GEraser(), "data/���찳.png", "���찳"),
		eShapeThick("�����β�����", new GShapeThick(), "data/�β�.png", "��β�����"),
		eFillShape("�� ��ä���", new GFillShape(), "data/���ä���.png", "�� ��ä���"),
		ePenFillShape("�׵θ���ä���", new GPenFillShape(), "data/�׵θ���ä���.png", "�׵θ���ä���"),
		eTextArea("�ؽ�Ʈ����", new GTextArea(), "data/�ؽ�Ʈ����.png", "�ؽ�Ʈ����"),
		eTextAreaFinish("�ؽ�Ʈ���ڿϷ��ϱ�", new GTextAreaFinish(), "data/�ؽ�Ʈ����.png", "�ؽ�Ʈ���ڿϷ��ϱ�"),
		eSelected("����", new GSelected(), "data/����.png", "����"),
		eTriangle("�ﰢ��", new GTriangle(), "data/�ﰢ��.png", "�ﰢ���׸���"),
		eRombus("Ʈ��", new GRombus(), "data/������.png", "������׸���"),
		eSelectedDottedCurve("����", new GSelectedDottedCurve(), "data/���õ�����.png", "�����׸���"),
		eStar("��", new GStar(), "data/���׸���.png", "���׸���");

		private String title;
		private GShape tool;
		private String path;
		private String toolTipText;

		private EToolbar(String title, GShape tool, String path, String toolTipText) {
			this.title = title;
			this.tool = tool;
			this.path = path;
			this.toolTipText=toolTipText;
		}		
		public String getTitle() {
			return this.title;
		}
		public GShape getTool() {
			return this.tool;
		}
		public String getPath() {
			return this.path;
		}
		public String getToolTipText() {
			return this.toolTipText;
		}
	}

	//	public enum EToolbarImage() {
	//		eRectangle("�׸�", new GRectangle()),
	//		eOval("��", new GOval()),
	//		eLine("����", new GLine()),
	//		ePolygon("�ٰ���", new GPolygon()),
	//		eCurve("�", new GCurve()),
	//		eDottedLine("����", new GDottedCurve()),
	//		eEraser("���찳", new GEraser()),
	//		eShapeThick("�����β�����", new GShapeThick());
	//		
	//		private String title;
	//		private GShape tool;
	//		
	//		private EToolbar(String title, GShape tool) {
	//			this.title = title;
	//			this.tool = tool;
	//		}		
	//		public String getTitle() {
	//			return this.title;
	//		}
	//		public GShape getTool() {
	//			return this.tool;
	//		}
	//	}

	public final static int MAXPOINTS = 100;

	public enum ECursor {
		eDefault(new Cursor(Cursor.DEFAULT_CURSOR)),
		eMove(new Cursor(Cursor.MOVE_CURSOR)),
		eRotate(new Cursor(Cursor.HAND_CURSOR)), // �߰�
		eEE(new Cursor(Cursor.E_RESIZE_CURSOR)),
		eWW(new Cursor(Cursor.W_RESIZE_CURSOR)),
		eNN(new Cursor(Cursor.N_RESIZE_CURSOR)),
		eSS(new Cursor(Cursor.S_RESIZE_CURSOR)),
		eNE(new Cursor(Cursor.NE_RESIZE_CURSOR)),
		eSE(new Cursor(Cursor.SE_RESIZE_CURSOR)),		
		eNW(new Cursor(Cursor.NW_RESIZE_CURSOR)),
		eSW(new Cursor(Cursor.SW_RESIZE_CURSOR));

		private Cursor cursor;

		private ECursor(Cursor cursor) {
			this.cursor = cursor;
		}		
		public Cursor getCursor() {
			return this.cursor;
		}
	}
}
