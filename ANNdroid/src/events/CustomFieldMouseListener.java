package ANNdroid.src.events;

import ANNdroid.src.*;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class CustomFieldMouseListener extends MouseAdapter{

	JComponent component;

	public CustomFieldMouseListener(){}

	public CustomFieldMouseListener(JComponent component){
		this.component = component;
	}

	public void mouseEntered(MouseEvent e){
		component.setCursor(ANNdroid.cursor);
	}


}