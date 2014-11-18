package ANNdroid.src;

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
					userList.add(u);
				}
				ois.close();
			}catch(IOException e){
				e.printStackTrace();
			}catch(ClassNotFoundException e){
				e.printStackTrace();
			}
		}
		else{
			User u = new Admin("admin", Utilities.hashPassword("password"));
			userList.add(u);
			ANNdroid.saver.saveUsers(userList, "ANNdroid/bin/users.bin");
		}

		return userList;
	}

}