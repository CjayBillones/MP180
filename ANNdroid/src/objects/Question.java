package ANNdroid.src.objects;

import java.io.Serializable;
import java.util.LinkedList;

public class Question implements Serializable{

	private String question;
	private LinkedList<String> choices;
	private int answer;
	private String difficulty;

	public Question(String question, LinkedList<String> choices, int answer, String difficulty){
		this.question = question;
		this.choices = choices;
		this.answer = answer;
		this.difficulty = difficulty;
	}

	public String getQuestion(){
		return this.question;
	}

	public LinkedList<String> getChoices(){
		return this.choices;
	}

	public int getAnswer(){
		return this.answer;
	}

	public String getAnswerLetter(){
		if(this.answer == 0) return "A";
		else if(this.answer == 1) return "B";
		else if(this.answer == 2) return "C";
		else if(this.answer == 3) return "D";
		else return null;
	}

	public String getDifficulty(){
		return this.difficulty;
	}

	public void setQuestion(String question){
		this.question = question;
	}

	public void setChoices(LinkedList<String> choices){
		this.choices = choices;
	}

	public void setAnswer(int answer){
		this.answer = answer;
	}

	public void setDifficulty(String difficulty){
		this.difficulty = difficulty;
	}

}