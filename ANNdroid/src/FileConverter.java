package ANNdroid.src;

import ANNdroid.src.objects.*;

import java.util.*;
import java.io.*;

public class FileConverter{

	public static LinkedList<Question> loadQuestions(String filename){

		File file = new File(filename);
		LinkedList<Question> questionList = new LinkedList<Question>();

		String question;
		int answer;
		String difficulty;

		if(file.exists()){
			try{
				FileReader fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);

				while((question = br.readLine()) != null){
					LinkedList<String> choices = new LinkedList<String>();
					choices.add(br.readLine());
					choices.add(br.readLine());
					choices.add(br.readLine());
					choices.add(br.readLine());
					answer = Integer.parseInt(br.readLine());
					difficulty = br.readLine();

					Question q = new Question(question, choices, answer, difficulty);
					questionList.add(q);
				}
				br.close();
				fr.close();
			}catch(IOException e){ e.printStackTrace();}
		}
		return questionList;
	}

	public static LinkedList<Subject> loadSubjects(String fileName) throws IOException{
		
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
				}
				ois.close();
			}catch(IOException e){	e.printStackTrace();
			}catch(ClassNotFoundException e){	e.printStackTrace();}
		}
		return subjectList;
	}	

	public static void saveSubjects(LinkedList<Subject> subjectList, String filename) throws NullPointerException{
		
		if(subjectList.size() == 0) throw new NullPointerException(filename);

		File file = new File(filename);

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

	public static void main(String[] args){
		
		LinkedList<Subject> s = new LinkedList<Subject>();
		LinkedList<Question> questions = loadQuestions("ANNdroid/src/bioquestions.txt");
		Subject subject = new Subject("Biology", questions);

		s.add(subject);


		questions = loadQuestions("ANNdroid/src/chemquestions.txt");
		subject = new Subject("Chemistry", questions);
		s.add(subject);

		questions = loadQuestions("ANNdroid/src/physicsquestions.txt");
		subject = new Subject("Physics", questions);
		s.add(subject);

		saveSubjects(s, "ANNdroid/bin/subjects.bin");
		
		/**System.out.println(subject);
		for(int ac = 0; ac < questions.size(); ac++){
			System.out.println(questions.get(ac).getQuestion());
			LinkedList<String> choices = questions.get(ac).choices();
			for(int bc = 0; bc < choices.size(); bc++){
				System.out.println(choices.get(bc));
			}
			System.out.println(questions.get(ac).answer());
			System.out.println(questions.get(ac).getDifficulty());
			System.out.println("");
		}**/
	}



}