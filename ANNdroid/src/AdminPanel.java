package ANNdroid.src;

import javax.swing.JPanel;

import java.awt.Dimension;

public class AdminPanel extends JPanel{

	public AdminPanel(JPanel bgPanel){

		setLayout(null);
		setPreferredSize(new Dimension(ANNdroid.SCREEN_WIDTH, ANNdroid.SCREEN_HEIGHT));
		setBounds(0, 0, ANNdroid.SCREEN_WIDTH, ANNdroid.SCREEN_HEIGHT);
	}


}