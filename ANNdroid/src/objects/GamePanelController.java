package ANNdroid.src.objects;

import java.util.*;

import ANNdroid.src.*;
import ANNdroid.src.ANNdroid;
import ANNdroid.src.panels.GamePane;
import ANNdroid.src.panels.*;
import ANNdroid.src.ai.aimaker.King.*;
import ANNdroid.src.ai.aimaker.King;
import ANNdroid.src.custom_swing.*;
import java.awt.event.*;
import javax.swing.*;

public class GamePanelController{

	private static final int DEFAULT_ROUNDS = 5;
	private static final int DEFAULT_HP = 3;

	private Simulator sim;
	private GameMaster gm;
	private MapBGPanel map;
	private JPanel gPanel;
	private King king;
	private Question curQuestion;
	private StudentData sD;
	private Student student;

	private String subject;

	private int player_hp;
	private int king_hp;
	private int rounds;
	private int p_bracket;

	private int def_hp;
	private int def_round;

	private double n_questions;
	private double n_answered;

	public GamePanelController(JPanel panel,King k ,String sub, int p_hp, int k_hp, int n_rounds,MapBGPanel map ){

		subject = sub.toUpperCase();
		def_hp = p_hp;
		sim = ANNdroid.simulator;
		gm = new GameMaster(sim.subjectList);
		this.map = map;

		sD = SDataSL.load();
		p_bracket = (sim.active == null)?5:(int)sD.studentData.get(((Student)sim.active).getUsername()).get(Categories.valueOf(subject));
		king = k;
		student = (Student)sim.active;
		gPanel = panel;
		player_hp = p_hp;
		king_hp = k_hp;
		rounds = n_rounds;
		def_round = rounds;

		System.out.println(rounds + " " + player_hp);

		n_questions = 0;
		n_answered = 0;

	for(int i = 0; i < 4; i++)
			((GamePane)gPanel).choiceButtons[i].addActionListener(new ChoiceButtonActionListener());		
	}

	public void getQuestion(){
		for(int i = 0; i < 4 ; i++){
			((GamePane)gPanel).choiceButtons[i].setVisible(true);
		}

		((GamePane)gPanel).kHP.setText("King HP:" + king_hp);
		((GamePane)gPanel).pHP.setText("Player HP:" + player_hp);
		if(king_hp <= 0 || player_hp <= 0 || rounds < 0){
			if(king_hp == player_hp){
				((GamePane)gPanel).choicesPanel.setVisible(false);
				((CustomTextArea)((GamePane)gPanel).question).setText("Draw!");
				student.gamesDraw(student.getGamesDraw() + 1);
				updateStudent();
				reset();
			}
			else{
				Boolean winner = (player_hp - king_hp > 0)?true:false;
				showWinner(winner);
			}
			((StudentPanel)ANNdroid.studentPanel).chemStrField.setText(Integer.toString(((Student)student).getChemistry()));
			((StudentPanel)ANNdroid.studentPanel).physStrField.setText(Integer.toString(((Student)student).getPhysics()));										
			((StudentPanel)ANNdroid.studentPanel).bioStrField.setText(Integer.toString(((Student)student).getBiology()));					
			((StudentPanel)ANNdroid.studentPanel).gamesPlayedField.setText(Integer.toString(((Student)student).getGamesPlayed()));
			((StudentPanel)ANNdroid.studentPanel).gamesWonField.setText(Integer.toString(((Student)student).getGamesWon()));
			((StudentPanel)ANNdroid.studentPanel).gamesDrawField.setText(Integer.toString(((Student)student).getGamesDraw()));
			((StudentPanel)ANNdroid.studentPanel).gamesLostField.setText(Integer.toString(((Student)student).getGamesLost()));						

		} 
		else
			try{
				n_questions++;
				curQuestion = gm.getRandomQuestion(Domain.valueOf(subject).ordinal());
				displayQuestion(curQuestion);
				rounds--;
				}catch(NullPointerException n){
				n.printStackTrace();
					((CustomTextArea)((GamePane)gPanel).question).setText("No more questions");
			}
	}

	public void displayQuestion(Question q){
		((CustomTextArea)((GamePane)gPanel).question).setText(q.getQuestion());
		((CustomTextArea)((GamePane)gPanel).question).repaint();
		LinkedList<String> choices = q.getChoices();		
		for(int i = 0; i < 4; i++){
			char[] letters = {'a','b','c','d'};
			((GamePane)gPanel).choiceButtons[i].setText(letters[i] + ")" +choices.get(i)); 			
		}


	}

	public int kingAnswer(){
		int ans = gm.getAnsFromKing(king,curQuestion,subject,p_bracket);
		String choice =  curQuestion.getChoices().get(ans);
		((GamePane)gPanel).king_answer.setText("The King chose: " + choice);
		System.out.println("K:" + gm.checkAnswer(curQuestion,ans));

		return ans;
	}

	public void evalRound(int answer, int k_answer){

		Boolean right = gm.checkAnswer(curQuestion,answer);
		
		
		if(right){
			king_hp--;
			n_answered++;
		}
		
		right = gm.checkAnswer(curQuestion,k_answer);

		if(right)
			player_hp--;
	

		((GamePane)gPanel).kHP.setText("King HP:" + king_hp);
		((GamePane)gPanel).pHP.setText("Player HP:" + player_hp);
		System.out.println(player_hp + " " + king_hp + " round" + rounds);

	}

	public void showWinner(Boolean isPlayer){
			
		if(isPlayer){
			((CustomTextArea)((GamePane)gPanel).question).setText("You Won!");
			student.setGamesWon(student.getGamesWon() + 1);
		}
		else{
			((CustomTextArea)((GamePane)gPanel).question).setText("The King Won!");
			student.setGamesLost(student.getGamesLost() + 1);
		}

		for(int i = 0; i < 4 ; i++){
			((GamePane)gPanel).choiceButtons[i].setVisible(false);
		}

		updateStudent();
		reset();
		
	}

	public class ChoiceButtonActionListener implements ActionListener{

		public void actionPerformed(ActionEvent e){

			Object src = e.getSource();
			
			if(src == ((GamePane)gPanel).choiceButtons[0]){
				System.out.println("P:" +gm.checkAnswer(curQuestion,0));
				evalRound(0,kingAnswer());
				getQuestion();
			}else if(src == ((GamePane)gPanel).choiceButtons[1]){
				System.out.println("P:" +gm.checkAnswer(curQuestion,1));
				evalRound(1,kingAnswer());
				getQuestion();
			}
			else if(src == ((GamePane)gPanel).choiceButtons[2]){
				System.out.println("P:" +gm.checkAnswer(curQuestion,2));
				evalRound(2,kingAnswer());
				getQuestion();
			}
			else if(src == ((GamePane)gPanel).choiceButtons[3]){
				System.out.println("P:" +gm.checkAnswer(curQuestion,3));
				evalRound(3,kingAnswer());
				getQuestion();
			}

		}	

	}

	public void reset(){
		king_hp = def_hp;
		player_hp = def_hp;
		rounds = def_round;
	}

	public void setSubject(String subject){
		this.subject = subject.toUpperCase();
	}

	public void setKing(King k2){
		student = (Student)sim.active;
		king = k2;
		System.out.println(k2.name);
	}

	public void updateStudent(){
		String username = sim.active.getUsername();
		int oldBracket = (int)sim.studentData.studentData.get(username).get(Categories.valueOf(subject));
		double new_accurac = (((double)(((oldBracket + 1) * 10) - 5)) + ((n_answered/n_questions) * 100)) / 2;
		System.out.println(new_accurac);
		int newBracket = 0;

		if(new_accurac<= 10){
			newBracket = 0;
		}else if(new_accurac <= 20){
			newBracket = 1;
		}else if(new_accurac <= 30){
			newBracket = 2;
		}else if(new_accurac <= 40){
			newBracket = 3;
		}else if(new_accurac <= 50){
			newBracket = 4;
		}else if(new_accurac <= 60){
			newBracket = 5;
		}else if(new_accurac <= 70){
			newBracket = 6;
		}else if(new_accurac <= 80){
			newBracket = 7;
		}else if(new_accurac <= 90){
			newBracket = 8;
		}else if(new_accurac<= 100){
			newBracket = 9;
		}

		if(subject.equals("BIOLOGY")){
			student.setBiology(newBracket);
		}else if(subject.equals("CHEMISTRY")){
			student.setChemistry(newBracket);
		}else if(subject.equals("PHYSICS")){
			student.setPhysics(newBracket);
		}

		student.setGamesPlayed(student.getGamesPlayed() + 1);

		sim.studentData.update((Student)sim.active,Categories.valueOf(subject),newBracket);
		ANNdroid.simulator.saver.saveUsers(ANNdroid.simulator.userList, "ANNdroid/bin/users.bin");
		System.out.println(sim.studentData.studentData.get(username).get(Categories.valueOf(subject)));
	}

}