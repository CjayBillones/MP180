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

	public void createAcct(String uname, String pword, String fname, String lname){
		ANNdroid.simulator.userList.add(new Student(uname, pword, fname, lname));
		ANNdroid.simulator.saver.saveUsers(Simulator.userList, "ANNdroid/bin/users.bin");		
	}

	public void delAcct(int index){
		ANNdroid.simulator.userList.remove(index);
		ANNdroid.simulator.saver.saveUsers(Simulator.userList, "ANNdroid/bin/users.bin");
	}
}