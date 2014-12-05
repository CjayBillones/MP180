package ANNdroid.src;

public class AdminController extends User{

	public AdminController(String uname, String pword, String fname, String lname){
		super(uname, pword, fname, lname);
	}

	public AdminController(String uname, String pword){
		super(uname, pword);
	}

	public void createAcct(User u){
		ANNdroid.simulator.userList.add(u);
		ANNdroid.simulator.saver.saveUsers(Simulator.userList, "ANNdroid/bin/users.bin");	
	}

	public void delAcct(int index){
		ANNdroid.simulator.userList.remove(index);
		ANNdroid.simulator.saver.saveUsers(Simulator.userList, "ANNdroid/bin/users.bin");
	}

	public String getUsername(){
		return super.getUsername();
	}


}