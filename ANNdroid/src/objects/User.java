package ANNdroid.src.objects;

import java.io.Serializable;

public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	private String uname;
	private String pword;
	private String fname;
	private String lname;

	public User(String uname, String pword, String fname, String lname){
		this.uname = uname;
		this.pword = pword;
		this.fname = fname;
		this.lname = lname;
	}

	public User(String uname, String pword){
		this.uname = uname;
		this.pword = pword;
	}

	public String getUsername(){
		return this.uname;
	}

	public String getPassword(){
		return this.pword;
	}

	public String getFname(){
		return this.fname;
	}

	public String getLname(){
		return this.lname;
	}

	public String getFullname(){
		return this.lname + ", " + this.fname;
	}

	public void setUsername(String uname){
		this.uname = uname;
	}

	public void setPassword(String pword){
		this.pword = pword;
	}

}