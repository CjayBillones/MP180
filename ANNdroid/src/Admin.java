package ANNdroid.src;

import java.io.Serializable;

public class Admin extends AdminController implements Serializable{

	private static final long serialVersionUID = 1L;

	public Admin(String uname, String pword){
		super(uname, pword);
	}

}