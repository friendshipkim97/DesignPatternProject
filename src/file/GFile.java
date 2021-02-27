package file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.JFileChooser;
import javax.swing.JLabel;

public class GFile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	public File file;
	JFileChooser filechooser;
	public JLabel jlabel;
	public Object object;


	public GFile() {
		this.file=null;
		
	}
	

	public Object readObject(File file) {
		
		try {
			this.file=file;

			FileInputStream fileInputStream = new FileInputStream(this.file);
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
			Object object = objectInputStream.readObject();
			fileInputStream.close();
			objectInputStream.close();
			
			
			return object;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //비트로시리얼라이즈 하는 것이다. 
           catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	
	}
	
	public void saveObject(Object object, File file) {
		
		
		this.file=file;
		
		try {
		FileOutputStream fileOutputStream = new FileOutputStream(this.file);
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
	    objectOutputStream.writeObject(object);
		objectOutputStream.close();
	
	}
	catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public File getFile() {
	return this.file;
}


}



