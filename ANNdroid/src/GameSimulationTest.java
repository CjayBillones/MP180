package ANNdroid.src;

import ANNdroid.src.ai.aimaker.King;
import ANNdroid.src.ai.aimaker.King.*;

import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.SwingConstants;
import javax.swing.Timer;


import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;


public class GameSimulationTest extends JFrame implements ActionListener{

	Simulator simulator;
	GameMaster gm;

	JLabel curPlayer;
	JLabel question;
	JLabel pHP;
	JLabel kHP;
	JLabel king_answer;

	JPanel centralPanel;
	JPanel questionPanel;
	JPanel choicesPanel;
	JPanel rightPanel;
	JPanel leftPanel;

	JButton[] choiceButtons;

	Question curQuestion;
	String subject;
	King king;
	int p_bracket;

	int player_hp;
	int king_hp;

	int rounds;

	public GameSimulationTest(String subject, King king){

		player_hp = 5;
		king_hp = 5;
		p_bracket = 5;
		rounds = 5;

		this.king = king;
		this.subject = subject.toUpperCase();
		simulator = new Simulator();
		gm = new GameMaster(simulator.subjectList);
		setLayout(new BorderLayout());

		king_answer = new JLabel("");
		pHP = new JLabel("Player HP:" + player_hp);
		kHP = new JLabel("King HP:" + king_hp,SwingConstants.LEFT);
		curPlayer = new JLabel("Your Turn to Answer!",SwingConstants.CENTER);
		question = new JLabel("Question Default String",SwingConstants.LEFT);

		choiceButtons = new JButton[4];

		questionPanel = new JPanel();
		questionPanel.setLayout(new BoxLayout(questionPanel,BoxLayout.PAGE_AXIS));

		centralPanel = new JPanel();
		centralPanel.setLayout(new BoxLayout(centralPanel,BoxLayout.LINE_AXIS));

		choicesPanel = new JPanel();
		choicesPanel.setLayout(new GridLayout(2,2,50,50));

		rightPanel =  new JPanel();
		leftPanel = new JPanel();

		for(int i = 0; i < choiceButtons.length; i++){
			choiceButtons[i] = new JButton("Default String");
			choiceButtons[i].addActionListener(this);
			choicesPanel.add(choiceButtons[i]);
		}

		questionPanel.add(Box.createRigidArea(new Dimension(0,50)));
		questionPanel.add(question);
		questionPanel.add(Box.createRigidArea(new Dimension(0,50)));
		questionPanel.add(choicesPanel);
		questionPanel.add(Box.createRigidArea(new Dimension(0,100)));

		centralPanel.add(Box.createRigidArea(new Dimension(100,50)));
		centralPanel.add(questionPanel);
		centralPanel.add(Box.createRigidArea(new Dimension(100,50)));

		leftPanel.add(pHP);
		rightPanel.setLayout(new BoxLayout(rightPanel,BoxLayout.PAGE_AXIS));
		rightPanel.add(kHP);
		rightPanel.add(king_answer);

		add(curPlayer,BorderLayout.PAGE_START);
		add(centralPanel,BorderLayout.CENTER);
		add(leftPanel,BorderLayout.LINE_START);
		add(rightPanel,BorderLayout.LINE_END);

		setSize(1240,720);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public void actionPerformed(ActionEvent e){
		Object src = e.getSource();
		if(src == choiceButtons[0]){
			System.out.println("P:" +gm.checkAnswer(curQuestion,0));
			evalRound(0,kingAnswer());
			getQuestion();
		}else if(src == choiceButtons[1]){
			System.out.println("P:" +gm.checkAnswer(curQuestion,1));
			evalRound(1,kingAnswer());
			getQuestion();
		}
		else if(src == choiceButtons[2]){
			System.out.println("P:" +gm.checkAnswer(curQuestion,2));
			evalRound(2,kingAnswer());
			getQuestion();
		}
		else if(src == choiceButtons[3]){
			System.out.println("P:" +gm.checkAnswer(curQuestion,3));
			evalRound(3,kingAnswer());
			getQuestion();
		}

	}


	public void getQuestion(){
		if(king_hp <= 0 || player_hp <= 0 || rounds <= 0){
			if(king_hp == player_hp){
				questionPanel.remove(choicesPanel);
				question.setText("Draw!");
			
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
				questionPanel.remove(choicesPanel);
				question.setText("No More Questions");
			}
	}

	public void displayQuestion(Question q){

		question.setText(q.getQuestion());
		LinkedList<String> choices = q.getChoices();		
		for(int i = 0; i < 4; i++){
			choiceButtons[i].setText(choices.get(i));			
		}


	}

	public int kingAnswer(){
		int ans = gm.getAnsFromKing(king,curQuestion,subject,p_bracket);
		String choice =  curQuestion.getChoices().get(ans);
		king_answer.setText("The King chose: " + choice);
		System.out.println("K:" +gm.checkAnswer(curQuestion,ans));

		return ans;
	}

	public void evalRound(int answer, int k_answer){

		Boolean right = gm.checkAnswer(curQuestion,answer);
		
		if(right)
			king_hp--;
		
		right = gm.checkAnswer(curQuestion,k_answer);

		if(right)
			player_hp--;
	

		kHP.setText("King HP:" + king_hp);
		pHP.setText("Player HP:" + player_hp);

	}
	
	public void showWinner(Boolean isPlayer){
		questionPanel.remove(choicesPanel);
			
		if(isPlayer)
			question.setText("You Won!");
		else
			question.setText("The King won!");
		
		
	}

	public static void main(String args[]){
		Simulator s = new Simulator();

		GameSimulationTest gst = new GameSimulationTest("Biology",s.kingList.get(0));

		gst.setVisible(true);
		gst.getQuestion();
	}


}