package ANNdroid.src.objects;

import java.util.LinkedList;
import java.util.Random;

import ANNdroid.src.ai.aimaker.King;
import ANNdroid.src.ai.aimaker.King.*;

public class GameMaster{

	private LinkedList<Subject> subjects;

	public GameMaster(LinkedList<Subject> subj){
		this.subjects = subj;
	}

	public Question getRandomQuestion(int index){
		LinkedList<Question> questions = subjects.get(index).getQuestions();
		Random r = new Random();
		if(questions.size() == 0)
			throw new NullPointerException();
		int ind = r.nextInt(questions.size());
		Question ret = questions.get(ind);
		questions.remove(ind); 
		return ret;
	}

	public Boolean checkAnswer(Question q, int user_answer){
		return q.getAnswer() == user_answer;
	}

	public int getAnsFromKing(King king, Question quest, Domain domain, int p_bracket){
		Random r = new Random();
		int diceRoll = r.nextInt(100);
		String dif = quest.getDifficulty();
		int kingLuck = ((king.get_king_bracket(domain,Difficulty.valueOf(dif),p_bracket) + 1) * 10) - 5;
		if(diceRoll <= kingLuck)
			return quest.getAnswer();
		else
			return r.nextInt(4);
	}

	public int getAnsFromKing(King king, Question quest, String subject, int p_bracket){
		return getAnsFromKing(king,quest,Domain.valueOf(subject.toUpperCase()),p_bracket);
	}

}