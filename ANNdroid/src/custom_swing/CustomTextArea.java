package ANNdroid.src.custom_swing;

import ANNdroid.src.*;
import ANNdroid.src.events.*;
import ANNdroid.src.panels.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CustomTextArea extends JTextArea{

	public CustomTextArea(Color caretColor, String text, int rows, int columns){
		
		super("", rows, columns);

		setForeground(Color.WHITE);
		setOpaque(false);
		setCaretColor(caretColor);
		setBorder(BorderFactory.createEmptyBorder());
		addMouseListener(new CustomFieldMouseListener(this));
		addFocusListener(new FocusAdapter(){
			public void focusGained(FocusEvent e){
				((TeacherPanel)ANNdroid.teacherPanel).jp.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 1));
				//((TeacherPanel)ANNdroid.teacherPanel).jp.getViewport().setBorder(BorderFactory.createLineBorder(Color.YELLOW, 1));
			}

			public void focusLost(FocusEvent e){
				((TeacherPanel)ANNdroid.teacherPanel).jp.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
				//((TeacherPanel)ANNdroid.teacherPanel).jp.getViewport().setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
			}
		});
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(new Color(255,255,255,128));
	}

}