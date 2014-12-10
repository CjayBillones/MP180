package ANNdroid.src.panels;

import ANNdroid.src.*;
import ANNdroid.src.custom_swing.*;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class GamePane extends JPanel{

	public JPanel bgPanel;

	public JButton quitButton;

	public JTextArea question;
	public JLabel pHP;
	public JLabel kHP;
	public JLabel king_answer;

	public JPanel centralPanel;
	public JPanel questionPanel;
	public JPanel choicesPanel;
	public JPanel rightPanel;
	public JPanel leftPanel;

	public JButton[] choiceButtons;
	
	//JFrame parent;


	public GamePane(){
		
		setLayout(null);

		quitButton = new CustomButton("Quit", getWidth()/4, 40);
		quitButton.setBounds(getWidth()/2 - quitButton.getWidth()/2, 0,getWidth()/4, 40);
		add(quitButton);
		quitButton.addActionListener(e->{
			((GamePane)ANNdroid.gamePanel).remove(ANNdroid.bgPanel);			
			((StudentPanel)ANNdroid.studentPanel).add(ANNdroid.bgPanel);
			((MapBGPanel)((StudentPanel)ANNdroid.studentPanel).mapPane).pGlass.setVisible(true);

			((StudentPanel)ANNdroid.studentPanel).mapBgmx.play.resume();
			((StudentPanel)ANNdroid.studentPanel).gameBgmx.play.stop();

			CardLayout c1 = (CardLayout)(ANNdroid.cards.getLayout());
			c1.show(ANNdroid.cards, ANNdroid.STUDENTPANEL);			
		});

		
		//this.parent = parent;

		//setLayout(new BorderLayout());

		king_answer = new JLabel("");
		pHP = new JLabel("Player HP:" + 5);
		kHP = new JLabel("King HP:" + 5,SwingConstants.LEFT);

		//question = new JTextArea(20,255);
		question = new CustomTextArea("Default String", 20, 255);
		question.setLineWrap(true);
		question.setBounds(0, quitButton.getY() + 50, getWidth(), getHeight()/2);
		add(question);

		Font font = question.getFont();
		float size = font.getSize() + 5.0f;
		question.setFont( font.deriveFont(size) );

		choiceButtons = new CustomButton[4];
		String[] files = {"topleft.png", "topright.png", "botleft.png", "botright.png"};

		int x = 0, y = question.getY()+question.getHeight()+30;
		
		for(int i = 0; i < choiceButtons.length; i++){
			choiceButtons[i] = new CustomButton("", getWidth()/2, 50, files[i]);
			choiceButtons[i].setBounds(x, y, getWidth()/2, 50);
			x+=choiceButtons[i].getWidth();

			if(i == 1){
				x = 0;
				y +=choiceButtons[i].getHeight()+ 30;
			}
			add(choiceButtons[i]);
		};

		question.setEditable(false);
	}

	public void resize(){

		quitButton.setBounds(getWidth()/2 - quitButton.getWidth()/2, 0,getWidth()/3, 40);
		((CustomButton)quitButton).resize();

		question.setBounds(0, quitButton.getY() + 50, getWidth(), getHeight()/2);
		((CustomTextArea)question).resize();

		int x = 0, y = question.getY()+question.getHeight()+30;

		for(int i = 0; i < choiceButtons.length; i++){
			choiceButtons[i].setBounds(x, y, getWidth()/2, 50);
			((CustomButton)choiceButtons[i]).resize();
			x+=choiceButtons[i].getWidth();

			if(i == 1){
				x = 0;
				y +=choiceButtons[i].getHeight()+ 30;
			}
			add(choiceButtons[i]);
		};
	}


}