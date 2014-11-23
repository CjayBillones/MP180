package ANNdroid.src;

import java.io.Serializable;

public class Teacher extends User implements Serializable{

	private static final long serialVersionUID = 1L;

	private String fname;
	private String lname;

	public Teacher(String username, String password, String fname, String lname){
		super(username, password);
		this.fname = fname;
		this.lname = lname;
	}

}