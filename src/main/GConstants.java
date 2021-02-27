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
		eFile(new GFileMenu("파일")),
		eEdit(new GEditMenu("편집")),
		eColor(new GColorMenu("컬러")),
		eEtc(new GEtcMenu("기타"));

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
	//		eOpen("열기", "open"),
	//	    eImageOpen("이미지 열기", "ImageOpen"),
	//		eSave("저장", "save"),
	//		eSaveAs("다른이름으로", "saveAs"),
	//		ePrint("프린트", "print"),
	//		eQuit("종료", "exit");
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
		eisUpdated1("변경내용을 저장 할까요?"),
		eisUpdated2("변경 확인"),
		eopenTitle("파일열기"),
		esaveTitle("파일저장"),
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
	//		eUndo("되돌리기", "undo"),
	//		eRedo("다시실행", "redo"),
	//		eCopy("복사", "copy"),
	//		eCut("자르기", "cut"),
	//		ePaste("붙여넣기", "paste"),
	//		eGroup("그룹", "group"),
	//		eUnGroup("그룹 해제","unGroup");
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
	//		eLineColor("라인 색", "setLineColor"),
	//		eFillColor("채우기 색", "setFillColor");
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
		eOpen("열기", "open", 'O'),
		eImageOpen("이미지 열기", "imageOpen", 'I'),
		eSave("저장", "save", 'S'),
		eSaveAs("다른이름으로", "saveAs", 'A'),
		ePrint("프린트", "print", 'P'),
		eQuit("종료", "exit", 'Q'),
		eUndo("되돌리기", "undo", 'U'),
		eRedo("다시실행", "redo", 'R'),
		eCopy("복사", "copy", 'Y'),
		eCut("자르기", "cut", 'C'),
		ePaste("붙여넣기", "paste", 'P'),
		eGroup("그룹", "group", 'G'),
		eUnGroup("그룹 해제","unGroup", 'U'),
		eLineColor("라인 색", "setLineColor", 'L'),
		eFillColor("채우기 색", "setFillColor", 'F'),
		eProgramInfo("프로그램 정보", "ProgramInfo", 'P');

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
		eRectangle("네모", new GRectangle(), "data/네모.png", "네모그리기"),
		eOval("원", new GOval(), "data/원.png", "원그리기"),
		eLine("라인", new GLine(), "data/라인.png", "직선그리기"),
		ePolygon("다각형", new GPolygon(), "data/다각형.png", "다각형그리기"),
		eCurve("곡선", new GCurve(), "data/곡선.png", "곡선그리기"),
		eDottedLine("점선", new GDottedCurve(), "data/점선.png", "점선그리기"),
		eEraser("지우개", new GEraser(), "data/지우개.png", "지우개"),
		eShapeThick("도형두께수정", new GShapeThick(), "data/두께.png", "펜두께수정"),
		eFillShape("면 색채우기", new GFillShape(), "data/면색채우기.png", "면 색채우기"),
		ePenFillShape("테두리색채우기", new GPenFillShape(), "data/테두리색채우기.png", "테두리색채우기"),
		eTextArea("텍스트상자", new GTextArea(), "data/텍스트상자.png", "텍스트상자"),
		eTextAreaFinish("텍스트상자완료하기", new GTextAreaFinish(), "data/텍스트상자.png", "텍스트상자완료하기"),
		eSelected("선택", new GSelected(), "data/선택.png", "선택"),
		eTriangle("삼각형", new GTriangle(), "data/삼각형.png", "삼각형그리기"),
		eRombus("트리", new GRombus(), "data/마름모.png", "마름모그리기"),
		eSelectedDottedCurve("점선", new GSelectedDottedCurve(), "data/선택된점선.png", "점선그리기"),
		eStar("별", new GStar(), "data/별그리기.png", "별그리기");

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
	//		eRectangle("네모", new GRectangle()),
	//		eOval("원", new GOval()),
	//		eLine("라인", new GLine()),
	//		ePolygon("다각형", new GPolygon()),
	//		eCurve("곡선", new GCurve()),
	//		eDottedLine("점선", new GDottedCurve()),
	//		eEraser("지우개", new GEraser()),
	//		eShapeThick("도형두께수정", new GShapeThick());
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
		eRotate(new Cursor(Cursor.HAND_CURSOR)), // 추가
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
