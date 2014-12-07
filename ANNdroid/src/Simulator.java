package ANNdroid.src;

import ANNdroid.src.ai.aimaker.*;
import ANNdroid.src.util.*;
import ANNdroid.src.objects.*;

import java.io.IOException;
import java.util.LinkedList;

public class Simulator{

	public static LinkedList<User> userList;
	public static LinkedList<Subject> subjectList;
	public static LinkedList<King> kingList;
	public static Saver saver;
	public static Loader loader;
	public static StudentData studentData;

	public User active = null;

	public Simulator(){


		loader = new Loader();
		saver = new Saver();
		

		try{
			userList = new LinkedList<User>();
			userList = loader.loadUsers("ANNdroid/bin/users.bin");

			subjectList = new LinkedList<Subject>();
			subjectList = loader.loadSubjects("ANNdroid/bin/subjects.bin");

			kingList  = new LinkedList<King>();
			kingList = loader.loadKings("ANNdroid/bin/kings.bin");
			
			studentData = SDataSL.load();
			
		}catch(IOException e){	e.printStackTrace();	}

	}

}