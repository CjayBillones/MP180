package ANNdroid.src;

import java.io.*;

public class SDataSL{
	
	public static StudentData load(){
		StudentData ret = null;
		try{
			FileInputStream file =  new FileInputStream("ANNdroid/bin/students.bin");
			ObjectInputStream in = new ObjectInputStream(file);

			ret = (StudentData) in.readObject();
			in.close();
			file.close();

		}catch(IOException e){
			e.printStackTrace();
		}catch(ClassNotFoundException c){
			c.printStackTrace();
		}
		return ret;
	}

	public static void save(StudentData sd){
		try{

			File dir = new File("ANNdroid/bin");

			if(!dir.exists()){
				try{
					dir.mkdir();
				}catch(SecurityException e){
					e.printStackTrace();
				}
			}

			File sd_file = new File("ANNdroid/bin/students.bin");

			if(!sd_file.exists())
				sd_file.createNewFile();

			FileOutputStream file = new FileOutputStream(sd_file);
			ObjectOutputStream out = new ObjectOutputStream(file);
			System.out.println(sd);
			out.writeObject(sd);
			out.close();
			file.close();

		}catch(IOException e){
			e.printStackTrace();
		}
	}

}