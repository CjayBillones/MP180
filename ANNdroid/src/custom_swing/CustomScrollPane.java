package ANNdroid.src.custom_swing;

import ANNdroid.src.*;
import ANNdroid.src.events.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CustomScrollPane extends JScrollPane{

	public CustomScrollPane(JComponent component){

		super(component);

		setForeground(Color.WHITE);
		setOpaque(false);
		getViewport().setOpaque(false);
		createVerticalScrollBar();
		addMouseListener(new CustomFieldMouseListener(this));
	}
}