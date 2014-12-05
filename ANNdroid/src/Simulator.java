package ANNdroid.src;

import java.io.IOException;
import java.util.LinkedList;

public class Simulator{

	public static enum Level{EASY, INTERMEDIATE, HARD};
	public static LinkedList<User> userList;
	public static LinkedList<Subject> subjectList;
	public static Saver saver;
	public static Loader loader;

	public User active = null;

	public Simulator(){
		loader = new Loader();
		saver = new Saver();

		try{
			userList = new LinkedList<User>();
			userList = loader.loadUsers("ANNdroid/bin/users.bin");

			subjectList = new LinkedList<Subject>();
			subjectList = loader.loadSubjects("ANNdroid/bin/subjects.bin");
		}catch(IOException e){	e.printStackTrace();	}

	}

}