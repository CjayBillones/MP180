package ANNdroid.src;

import java.io.Serializable;

public class Teacher extends AdminController implements Serializable{

	private static final long serialVersionUID = 1L;

	public Teacher(String username, String password, String fname, String lname){
		super(username, password, fname, lname);
	}

}