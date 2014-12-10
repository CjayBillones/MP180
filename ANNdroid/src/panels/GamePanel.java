package ANNdroid.src.panels;

import ANNdroid.src.*;
import ANNdroid.src.custom_swing.*;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class GamePanel extends JPanel{

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

	JFrame parent;


	public GamePanel(JFrame parent){

		this.parent = parent;

		setLayout(new BorderLayout());

		king_answer = new JLabel("");
		pHP = new JLabel("Player HP:" + 5);
		kHP = new JLabel("King HP:" + 5,SwingConstants.LEFT);
		question = new JTextArea(5,5);
		question.setLineWrap(true);
		question.setWrapStyleWord(true);
		Font font = question.getFont();
		float size = font.getSize() + 5.0f;
		question.setFont( font.deriveFont(size) );

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

		add(centralPanel,BorderLayout.CENTER);
		add(leftPanel,BorderLayout.LINE_START);
		add(rightPanel,BorderLayout.LINE_END);

		question.setEditable(false);

	

	}

	public void resize(){
		
	}

}