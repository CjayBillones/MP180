package ANNdroid.src;

import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LeftSidePane extends JPanel{

	// source: 0 - Admin Panel //
	// source: 1 - Teacher Panel //
	// source: 2 - Student Panel //

	int source;
	JPanel sourcePanel;
	int btnPane_ht;
	int btnPane_wdt;
	JButton[] btns;
	JPanel buttonPane;
	
	public LeftSidePane(JPanel sourcePanel, int source, int buttons, String[] labels){
		
		super(null);

		this.source = source;
		this.sourcePanel = sourcePanel;

		btnPane_ht = sourcePanel.getHeight();
		btnPane_wdt = sourcePanel.getWidth();

		buttonPane = new JPanel(null);
		buttonPane.setPreferredSize(new Dimension(btnPane_wdt/4, btnPane_ht/2));
		buttonPane.setBounds(30, btnPane_ht/8, btnPane_wdt/4, btnPane_ht/2 + btnPane_ht/4);
	
		btns = new JButton[buttons];
				
		for(int ac = 0, bc = 0; ac < buttons; ac++, bc+=50){

			if(buttons-2 == ac) bc = buttonPane.getHeight()-100;

			btns[ac] = new JButton(labels[ac]);
			btns[ac].setBounds(0, bc, btnPane_wdt/4, 50);
			btns[ac].addActionListener(new ButtonActions(ac));
			buttonPane.add(btns[ac]);
		}
	
		add(buttonPane);
	}

	public void resize(){
		btnPane_ht = sourcePanel.getHeight();
		btnPane_wdt = sourcePanel.getWidth();		
		buttonPane.setBounds(30, btnPane_ht/8, btnPane_wdt/4, btnPane_ht/2 + btnPane_ht/4);

		for(int ac = 0, bc = 0; ac < btns.length; ac++, bc+=50){
			if(btns.length-2 == ac) bc = buttonPane.getHeight()-100;
			btns[ac].setBounds(0, bc, btnPane_wdt/4, 50);
		}		
	}

	private class ButtonActions implements ActionListener{

		int mode;

		public ButtonActions(int mode){
			this.mode = mode;
		}

		public void actionPerformed(ActionEvent e){
			if(source == 0){
				if(this.mode == 0){
					((AdminPanel)ANNdroid.adminPanel).createTeachPane.setVisible(true);
					
					((AdminPanel)ANNdroid.adminPanel).delTeachPane.setVisible(false);
					((AdminPanel)ANNdroid.adminPanel).reinitialize(false, true, 1);

					((AdminPanel)ANNdroid.adminPanel).changePwordPane.setVisible(false);
					((AdminPanel)ANNdroid.adminPanel).reinitialize(false, true, 2);
				}
				else if(this.mode == 1){
					((AdminPanel)ANNdroid.adminPanel).createTeachPane.setVisible(false);
					((AdminPanel)ANNdroid.adminPanel).reinitialize(false, true, 0);

					((AdminPanel)ANNdroid.adminPanel).delTeachPane.setVisible(true);

					((AdminPanel)ANNdroid.adminPanel).changePwordPane.setVisible(false);
					((AdminPanel)ANNdroid.adminPanel).reinitialize(false, true, 2);


				}
				else if(this.mode == 2){
					((AdminPanel)ANNdroid.adminPanel).createTeachPane.setVisible(false);
					((AdminPanel)ANNdroid.adminPanel).reinitialize(false, true, 0);

					((AdminPanel)ANNdroid.adminPanel).delTeachPane.setVisible(false);
					((AdminPanel)ANNdroid.adminPanel).reinitialize(false, true, 1);

					((AdminPanel)ANNdroid.adminPanel).changePwordPane.setVisible(true);

				}
				else if(this.mode == 3){

				}
				else{
					((AdminPanel)ANNdroid.adminPanel).createTeachPane.setVisible(false);
					((AdminPanel)ANNdroid.adminPanel).reinitialize(false, true, 0);

					((AdminPanel)ANNdroid.adminPanel).delTeachPane.setVisible(false);
					((AdminPanel)ANNdroid.adminPanel).reinitialize(false, true, 1);					

					((AdminPanel)ANNdroid.adminPanel).changePwordPane.setVisible(false);
					((AdminPanel)ANNdroid.adminPanel).reinitialize(false, true, 2);

					ANNdroid.simulator.active = null;
					ANNdroid.loginPanel.add(ANNdroid.bgPanel);
					((AdminPanel)ANNdroid.adminPanel).remove(ANNdroid.bgPanel);
			
					CardLayout c1 = (CardLayout)(ANNdroid.cards.getLayout());
					c1.show(ANNdroid.cards, ANNdroid.LOGINPANEL);				
				}
			}
			else if(source == 1){
				if(this.mode == 0){
					((TeacherPanel)ANNdroid.teacherPanel).createStudentPane.setVisible(true);

					((TeacherPanel)ANNdroid.teacherPanel).delStudentPane.setVisible(false);
					((TeacherPanel)ANNdroid.teacherPanel).reinitialize(false, true, 1);
				}
				else if(this.mode == 1){
					((TeacherPanel)ANNdroid.teacherPanel).createStudentPane.setVisible(false);
					((TeacherPanel)ANNdroid.teacherPanel).reinitialize(false, true, 0);

					((TeacherPanel)ANNdroid.teacherPanel).delStudentPane.setVisible(true);
				}
				else if(this.mode == 2){
					((TeacherPanel)ANNdroid.teacherPanel).createStudentPane.setVisible(false);
					((TeacherPanel)ANNdroid.teacherPanel).reinitialize(false, true, 0);

					((TeacherPanel)ANNdroid.teacherPanel).delStudentPane.setVisible(false);
					((TeacherPanel)ANNdroid.teacherPanel).reinitialize(false, true, 1);					
				}
				else if(this.mode == 3){
					((TeacherPanel)ANNdroid.teacherPanel).createStudentPane.setVisible(false);
					((TeacherPanel)ANNdroid.teacherPanel).reinitialize(false, true, 0);
					
					((TeacherPanel)ANNdroid.teacherPanel).delStudentPane.setVisible(false);
					((TeacherPanel)ANNdroid.teacherPanel).reinitialize(false, true, 1);
				}
				else if(this.mode == 4){
					((TeacherPanel)ANNdroid.teacherPanel).createStudentPane.setVisible(false);
					((TeacherPanel)ANNdroid.teacherPanel).reinitialize(false, true, 0);
					
					((TeacherPanel)ANNdroid.teacherPanel).delStudentPane.setVisible(false);
					((TeacherPanel)ANNdroid.teacherPanel).reinitialize(false, true, 1);
				}
				else{
					((TeacherPanel)ANNdroid.teacherPanel).createStudentPane.setVisible(false);
					((TeacherPanel)ANNdroid.teacherPanel).reinitialize(false, true, 0);

					((TeacherPanel)ANNdroid.teacherPanel).delStudentPane.setVisible(false);
					((TeacherPanel)ANNdroid.teacherPanel).reinitialize(false, true, 1);

					ANNdroid.simulator.active = null;
					ANNdroid.loginPanel.add(ANNdroid.bgPanel);
					((TeacherPanel)ANNdroid.teacherPanel).remove(ANNdroid.bgPanel);
			
					CardLayout c1 = (CardLayout)(ANNdroid.cards.getLayout());
					c1.show(ANNdroid.cards, ANNdroid.LOGINPANEL);								
				}
			}else if(source == 2){
				if(mode == 0){

				}
				else if(mode == 1){

				}
				else if(mode == 2){

				}
				else if(mode == 3){

				}
				else if(mode == 4){

				}
				else{
					ANNdroid.simulator.active = null;
					ANNdroid.loginPanel.add(ANNdroid.bgPanel);
					((StudentPanel)ANNdroid.studentPanel).remove(ANNdroid.bgPanel);
			
					CardLayout c1 = (CardLayout)(ANNdroid.cards.getLayout());
					c1.show(ANNdroid.cards, ANNdroid.LOGINPANEL);						
				}
			}
		}

	}
}