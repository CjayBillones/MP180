package ANNdroid.src.util;

import ANNdroid.src.ai.aimaker.*;
import ANNdroid.src.objects.*;
import ANNdroid.src.*;

import java.util.LinkedList;
import java.lang.ClassNotFoundException;

import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Loader{
	
	public LinkedList<User> loadUsers(String fileName) throws IOException{
		
		File file = new File(fileName);
		LinkedList<User> userList = new LinkedList<User>();

		if(file.exists()){
			try{
				FileInputStream fis = new FileInputStream(file);
				ObjectInputStream ois = new ObjectInputStream(fis);

				int size = ois.readInt();

				for(int ac = 0; ac < size; ac++){
					User u = (User)ois.readObject();

					if(u instanceof Admin)	System.out.println(u.getUsername() + " - Admin");
					else if(u instanceof Teacher)	System.out.println(u.getUsername() + " - Teacher");
					else if(u instanceof Student) System.out.println(u.getUsername() + " - Student");
					userList.add(u);
				}
				ois.close();
			}catch(IOException e){	e.printStackTrace();
			}catch(ClassNotFoundException e){	e.printStackTrace();}
		}
		else{
			User u = new Admin("admin", Utilities.hashPassword("password"));
			userList.add(u);
			ANNdroid.simulator.saver.saveUsers(userList, "ANNdroid/bin/users.bin");
		}
		return userList;
	}
	
	public LinkedList<Subject> loadSubjects(String fileName) throws IOException{
		
		File file = new File(fileName);
		LinkedList<Subject> subjectList = new LinkedList<Subject>();

		if(file.exists()){
			try{
				FileInputStream fis = new FileInputStream(file);
				ObjectInputStream ois = new ObjectInputStream(fis);

				int size = ois.readInt();

				for(int ac = 0; ac < size; ac++){
					Subject s = (Subject)ois.readObject();

					subjectList.add(s);

					System.out.println(s.getName() + " " + Integer.toString(s.getQuestions().size()));
				}
				ois.close();
			}catch(IOException e){	e.printStackTrace();
			}catch(ClassNotFoundException e){	e.printStackTrace();}
		}
		else
			throw new FileNotFoundException(fileName);
		return subjectList;
	}
	
	public LinkedList<King> loadKings(String fileName) throws IOException{
		
		File file = new File(fileName);
		LinkedList<King> kingList = new LinkedList<King>();

		if(file.exists()){
			try{
				FileInputStream fis = new FileInputStream(file);
				ObjectInputStream ois = new ObjectInputStream(fis);

				int size = ois.readInt();

				for(int ac = 0; ac < size; ac++){
					King s = (King)ois.readObject();

					kingList.add(s);
					System.out.println(s.name);

				}
				ois.close();
			}catch(IOException e){	e.printStackTrace();
			}catch(ClassNotFoundException e){	e.printStackTrace();}
		}
		else
			throw new FileNotFoundException(fileName);
		return kingList;
	}
	
}