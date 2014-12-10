package ANNdroid.src.events;

import ANNdroid.src.util.*;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class CustomButtonMouseListener extends MouseAdapter{

	JButton button;

	public CustomButtonMouseListener(){}

	public CustomButtonMouseListener(JButton button){
		this.button = button;
	}

	public void mouseEntered(MouseEvent e){
		if(button.isEnabled()){
			SoundPlayer hover = new SoundPlayer("hover2.mp3", false, 0);
			hover.playSound();
			button.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
		}
	}

	public void mouseExited(MouseEvent e){
		button.setBorder(BorderFactory.createEmptyBorder());
	}

	public void mouseClicked(MouseEvent e){
		if(button.isEnabled()){
			SoundPlayer click = new SoundPlayer("click.mp3", false, 0);
			click.playSound();
		}
	}

}