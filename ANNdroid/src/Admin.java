package ANNdroid.src;

import java.io.Serializable;

public class Admin extends User implements Serializable{

	private static final long serialVersionUID = 1L;

	public Admin(String username, String password){
		super(username, password);
	}

}