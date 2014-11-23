package ANNdroid.src;

import java.io.Serializable;

public class Admin extends User implements Serializable{

	private static final long serialVersionUID = 1L;

	private String firstName, lastName;

	public Admin(String username, String password, String fname, String lname){
		super(username, password);
		this.firstName = fname;
		this.lastName = lname;
	}

	public Admin(String username, String password){
		super(username, password);
		this.firstName = "";
		this.lastName = "";
	}
}