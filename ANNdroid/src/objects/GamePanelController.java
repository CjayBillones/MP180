package ANNdroid.src.objects;

import java.util.*;

import ANNdroid.src.*;
import ANNdroid.src.ANNdroid;
import ANNdroid.src.panels.GamePanel;
import ANNdroid.src.ai.aimaker.King.*;
import ANNdroid.src.ai.aimaker.King;

import java.awt.event.*;

public class GamePanelController{

	private static final int DEFAULT_ROUNDS = 5;
	private static final int DEFAULT_HP = 3;

	private Simulator sim;
	private GameMaster gm;
	private GamePanel gPanel;
	private King king;
	private Question curQuestion;
	private StudentData sD;
	private Student student;

	private String subject;

	private int player_hp;
	private int king_hp;
	private int rounds;
	private int p_bracket;

	public GamePanelController(GamePanel panel,King k ,String sub, int p_hp, int k_hp, int n_rounds){

		subject = sub.toUpperCase();

		sim = ANNdroid.simulator;
		gm = new GameMaster(sim.subjectList);

		sD = SDataSL.load();
		System.out.println(sD.studentData);
		System.out.println(((Student)sim.active).getUsername());
		p_bracket = (sim.active == null)?5:(int)sD.studentData.get(((Student)sim.active).getUsername()).get(Categories.valueOf(subject));
		king = k;
		student = (Student)sim.active;
		gPanel = panel;
		player_hp = p_hp;
		king_hp = k_hp;
		rounds = n_rounds;

		for(int i = 0; i < 4; i++)
			gPanel.choiceButtons[i].addActionListener(new ChoiceButtonActionListener());

	}

	public GamePanelController(GamePanel panel,King k, String sub,int p_hp, int k_hp){
		this(panel,k,sub,p_hp,k_hp,DEFAULT_ROUNDS);
	}

	public GamePanelController(GamePanel panel,King k, String sub){
		this(panel,k,sub,DEFAULT_HP,DEFAULT_HP);			
	}

	public void getQuestion(){
		if(king_hp <= 0 || player_hp <= 0 || rounds <= 0){
			if(king_hp == player_hp){
				gPanel.questionPanel.remove(gPanel.choicesPanel);
				gPanel.question.setText("Draw!");
			
			}
			else{
				Boolean winner = (player_hp - king_hp > 0)?true:false;
				showWinner(winner);
			}
		} 
		else
			try{

				curQuestion = gm.getRandomQuestion(Domain.valueOf(subject).ordinal());
				displayQuestion(curQuestion);
				rounds--;
				}catch(NullPointerException n){
				gPanel.questionPanel.remove(gPanel.choicesPanel);
				gPanel.question.setText("No More Questions");
			}
	}

	public void displayQuestion(Question q){

		gPanel.question.setText(q.getQuestion());
		LinkedList<String> choices = q.getChoices();		
		for(int i = 0; i < 4; i++){
			char[] letters = {'a','b','c','d'};
			gPanel.choiceButtons[i].setText(letters[i] + ")" +choices.get(i));			
		}


	}

	public int kingAnswer(){
		int ans = gm.getAnsFromKing(king,curQuestion,subject,p_bracket);
		String choice =  curQuestion.getChoices().get(ans);
		gPanel.king_answer.setText("The King chose: " + choice);
		System.out.println("K:" + gm.checkAnswer(curQuestion,ans));

		return ans;
	}

	public void evalRound(int answer, int k_answer){

		Boolean right = gm.checkAnswer(curQuestion,answer);
		
		if(right)
			king_hp--;
		
		right = gm.checkAnswer(curQuestion,k_answer);

		if(right)
			player_hp--;
	

		gPanel.kHP.setText("King HP:" + king_hp);
		gPanel.pHP.setText("Player HP:" + player_hp);

	}

	public void showWinner(Boolean isPlayer){
		gPanel.questionPanel.remove(gPanel.choicesPanel);
			
		if(isPlayer)
			gPanel.question.setText("You Won!");
		else
			gPanel.question.setText("The King won!");
		
		
	}

	public class ChoiceButtonActionListener implements ActionListener{

		public void actionPerformed(ActionEvent e){

			Object src = e.getSource();
			
			if(src == gPanel.choiceButtons[0]){
				System.out.println("P:" +gm.checkAnswer(curQuestion,0));
				evalRound(0,kingAnswer());
				getQuestion();
			}else if(src == gPanel.choiceButtons[1]){
				System.out.println("P:" +gm.checkAnswer(curQuestion,1));
				evalRound(1,kingAnswer());
				getQuestion();
			}
			else if(src == gPanel.choiceButtons[2]){
				System.out.println("P:" +gm.checkAnswer(curQuestion,2));
				evalRound(2,kingAnswer());
				getQuestion();
			}
			else if(src == gPanel.choiceButtons[3]){
				System.out.println("P:" +gm.checkAnswer(curQuestion,3));
				evalRound(3,kingAnswer());
				getQuestion();
			}

		}	

	}

	public void updateStudent(){

	}

}