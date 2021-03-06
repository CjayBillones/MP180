package ANNdroid.src.custom_swing;

import ANNdroid.src.*;
import ANNdroid.src.events.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CustomTextField extends JTextField{

	public CustomTextField(Color caretColor, boolean editable, String text){

		super(text);

		setForeground(Color.WHITE);
		setOpaque(false);
		setCaretColor(caretColor);
		addMouseListener(new CustomFieldMouseListener(this));
		addFocusListener(new FocusAdapter(){
			public void focusGained(FocusEvent e){
				setBorder(BorderFactory.createLineBorder(Color.YELLOW, 1));
			}

			public void focusLost(FocusEvent e){
				setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
			}
		});

		if(!editable){

			if(getText().equals("n/a")) setForeground(Color.RED);

			setFocusable(false);
			setEditable(false);
			setBorder(BorderFactory.createEmptyBorder());
		}
	}

}