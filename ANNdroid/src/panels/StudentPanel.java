package ANNdroid.src.panels;

import ANNdroid.src.custom_swing.*;
import ANNdroid.src.*;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;

public class StudentPanel extends JPanel{

	JPanel leftSidePane;

	public StudentPanel(){
		setLayout(null);

		setPreferredSize(new Dimension(ANNdroid.SCREEN_WIDTH, ANNdroid.SCREEN_HEIGHT));
		setBounds(0, 0, ANNdroid.SCREEN_WIDTH, ANNdroid.SCREEN_HEIGHT);
	
		String labels[] = {"Play", "Instructions", "Statistics", "Account Settings", "Options", "Logout"};

		leftSidePane = new LeftSidePane(this, 2, 6, labels);
		leftSidePane.setPreferredSize(new Dimension(getWidth()/4 + 60, getHeight()));
		leftSidePane.setBounds(0, 0, getWidth()/4 + 60, getHeight());
		leftSidePane.setBackground(Color.BLACK);

		add(leftSidePane);
	}

	public void resize(){
		leftSidePane.setBounds(0, 0, getWidth()/4 + 60, getHeight());
		((LeftSidePane)leftSidePane).resize();
	}

}