package ANNdroid.src.custom_swing;

import ANNdroid.src.events.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CustomPasswordField extends JPasswordField{

	public CustomPasswordField(Color caretColor){
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
	}

}