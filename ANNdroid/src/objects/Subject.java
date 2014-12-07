package ANNdroid.src.objects;

import java.io.Serializable;
import java.util.LinkedList;

public class Subject implements Serializable{

	private String name;
	private LinkedList<Question> questions;

	public Subject(String name, LinkedList<Question> questions){
		this.name = name;
		this.questions = questions;
	}

	public String getName(){
		return this.name;
	}

	public LinkedList<Question> getQuestions(){
		return this.questions;
	}

	public void setQuestions(LinkedList<Question> questions){
		this.questions = questions;
	}

}