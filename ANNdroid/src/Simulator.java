package ANNdroid.src;

import java.io.IOException;
import java.util.LinkedList;

public class Simulator{

	public static LinkedList<User> userList;
	public static Saver saver;
	public static Loader loader;

	public User active = null;

	public Simulator(){
		loader = new Loader();
		saver = new Saver();

		try{
			userList = new LinkedList<User>();
			userList = loader.loadUsers("Anndroid/bin/users.bin");
		}catch(IOException e){	e.printStackTrace();	}

	}

}