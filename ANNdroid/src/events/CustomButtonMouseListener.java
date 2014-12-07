package ANNdroid.src.events;

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
		button.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
	}

	public void mouseExited(MouseEvent e){
		button.setBorder(BorderFactory.createEmptyBorder());
	}

}