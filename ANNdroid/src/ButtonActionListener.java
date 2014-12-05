package ANNdroid.src;

import javax.swing.JButton;
import java.awt.event.ActionEvent;

public class ButtonActionListener extends JButton{

	int source;
	int mode;
	int state;

	public ButtonActionListener(int source, int mode, int state){
		this.source = source;
		this.mode = mode;
		this.state = state;
	}


	public void actionPerformed(ActionEvent e){
		if(source == 0){
			
		}
	}


}