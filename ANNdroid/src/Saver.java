package ANNdroid.src;

import java.util.LinkedList;
import java.lang.NullPointerException;

import java.io.File;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Saver{
	public void saveUsers(LinkedList<User> userList, String fileName)throws NullPointerException{

		if(userList.size() == 0)
			throw new NullPointerException(fileName);

		File file = new File(fileName);

		try{
			
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			if(!file.exists()) file.createNewFile();

			oos.writeInt(userList.size());

			for(int ac = 0; ac < userList.size(); ac++){
				oos.writeObject(userList.get(ac));
			}
			oos.close();
		}catch(IOException e){	e.printStackTrace();	}

	}

	public void saveSubjects(LinkedList<Subject> subjectList, String fileName) throws NullPointerException{

		if(subjectList.size() == 0)
				throw new NullPointerException(fileName);

		File file = new File(fileName);

		try{
			
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			if(!file.exists()) file.createNewFile();

			oos.writeInt(subjectList.size());

			for(int ac = 0; ac < subjectList.size(); ac++){
				oos.writeObject(subjectList.get(ac));
			}
			oos.close();
		}catch(IOException e){	e.printStackTrace();	}		

	}
}