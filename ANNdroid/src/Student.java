package ANNdroid.src;

public class Student extends User{

	String fname;
	String lname;

	public Student(String username, String password, String fname, String lname){
		super(username, password);
		this.fname = fname;
		this.lname = lname;
	}


}