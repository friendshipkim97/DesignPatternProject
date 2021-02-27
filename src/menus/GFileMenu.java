package menus;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import file.GFile;
import main.GConstants;

public class GFileMenu extends GMenu {
	// attributes
	private static final long serialVersionUID = GConstants.serialVersionUID;
	//association
	
	//working variables
	File directory;
	File file;
	
	public GFileMenu(String name) {
		super(name);

		this.setMnemonic('F');
		for(int i=0; i<7; i++) {
			this.add(menuItems.get(i));
		}
		
		//default directory = current directory
		this.directory = new File("./data");
		this.file=null;
	}

	public void initialize() {
	}
	
	public void imageOpen() {
		JFileChooser fileChooser= new JFileChooser(this.directory);
		int returnValue = fileChooser.showOpenDialog(this.drawingPanel);
   	 
   	 if(returnValue == JFileChooser.APPROVE_OPTION) { 
   		this.drawingPanel.addImage(fileChooser.getSelectedFile());
   	 }
	}
	
	public void checkSave() {
		if(this.drawingPanel.isUpdated()) {
			String[] str = {"OK", "NO", "CANCEL"};

			int reply = JOptionPane.showOptionDialog(this.drawingPanel,
					GConstants.EFileMenuEtc.eisUpdated1.getTitle(),
					GConstants.EFileMenuEtc.eisUpdated2.getTitle(), JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, str, "OK");
			if(reply == JOptionPane.OK_OPTION) {
				this.save();
			}
			else if(reply == JOptionPane.CANCEL_OPTION) {
				JOptionPane.showMessageDialog(this.drawingPanel, "취소했습니다.");
			   			
			}
		}
	}

	public void nnew() {	
		  this.checkSave();
		  
		  this.drawingPanel.clearShapes();
		  this.file=null;
	}
	
	public void open() {
		this.checkSave();
		
		JFileChooser fileChooser= new JFileChooser(this.directory);
		int returnValue = fileChooser.showOpenDialog(this.drawingPanel);
		fileChooser.setDialogTitle(GConstants.EFileMenuEtc.eopenTitle.getTitle());
   	 
   	 if(returnValue == JFileChooser.APPROVE_OPTION) { //창을 취소버튼을 누른경우
   		this.drawingPanel.clearShapes();
   		 this.directory=fileChooser.getCurrentDirectory(); //다시열었을때그위치
   		this.file = fileChooser.getSelectedFile();
   		GFile gFile = new GFile();
   		Object shapes = gFile.readObject(this.file);
        this.drawingPanel.setShapes(shapes);
   	 }
   
   	 }
	
	public void save() {
   
        if(this.file==null) {
        	this.saveAs();
        } else {
        	 GFile gFile = new GFile();
             gFile.saveObject(drawingPanel.getShapes(), this.file);
             this.drawingPanel.setUpdated(false);       
        }
        
        }
	
	public void saveAs() {
		JFileChooser fileChooser= new JFileChooser(this.directory);
		int returnValue = fileChooser.showSaveDialog(this.drawingPanel);
		fileChooser.setDialogTitle(GConstants.EFileMenuEtc.eopenTitle.getTitle());
		
   	 if(returnValue == JFileChooser.APPROVE_OPTION) {
   		 this.directory=fileChooser.getCurrentDirectory(); //다시열었을때그위치
    		this.file = fileChooser.getSelectedFile();
    		this.save();
   	 }
   	 }
		
	public void print() {

		this.drawingPanel.print();
	}
	public void exit() {
		this.checkSave();
       System.exit(0);		
		}

	

}
