package ANNdroid.src;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ActionListener;
import java.awt.ComponentOrientation;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JPanel;
import javax.swing.JLayeredPane;
import javax.swing.JButton;

public class AdminPanel extends JPanel{

	private User user;

	protected JButton create;
	protected JButton delete;
	protected JButton edit;
	protected JButton logout;

	protected JPanel changePassPanel;

	public AdminPanel(){
	
		setPreferredSize(new Dimension(ANNdroid.SCREEN_WIDTH, ANNdroid.SCREEN_HEIGHT));
	
		JPanel jp = new JPanel(new GridLayout());
		jp.setPreferredSize(new Dimension(ANNdroid.SCREEN_WIDTH, 50));
		jp.setBounds(0, 0, ANNdroid.SCREEN_WIDTH, 50);

		changePassPanel = new ChangePasswordPanel();

		create = new JButton("Create Teacher Account");
		jp.add(create);
		
		delete = new JButton("Delete Teacher Account");
		jp.add(delete);
		
		edit = new JButton("Change Password");
		edit.addActionListener(event -> {changePassPanel.setVisible(true);});
		jp.add(edit);

		logout = new JButton("Logout");
		/**logout.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Framework.gameState = Framework.GameState.LOG_IN;
				setUser(null);
			}
		});**/
		logout.addActionListener(event -> {
			Framework.gameState = Framework.GameState.LOG_IN;
			setUser(null);
			changePassPanel.setVisible(false);
			((ChangePasswordPanel)changePassPanel).reinitialize();
			((ChangePasswordPanel)changePassPanel).errorLabel.setText("");
		});
		jp.add(logout);
		
		add(jp);
		add(changePassPanel);
	}

	public void setUser(User u){
		this.user = u;
	}

	public User getUser(){
		return this.user;
	}

	class ChangePasswordPanel extends JPanel{

		protected JLabel errorLabel;
		protected JLabel oldPassLabel;
		protected JLabel newPassLabel;
		protected JLabel confirmNewPassLabel;
		protected JPasswordField oldPassField;
		protected JPasswordField newPassField;
		protected JPasswordField confirmNewPassField;
		protected JButton changeBtn;
		protected JButton cancelBtn;

		protected String oldPass = "";
		protected String newPass = "";
		protected String newPassConfirm ="";

		public ChangePasswordPanel(){

			setPreferredSize(new Dimension(ANNdroid.SCREEN_WIDTH, ANNdroid.SCREEN_HEIGHT-50));
			setBounds(0, 50, ANNdroid.SCREEN_WIDTH, ANNdroid.SCREEN_HEIGHT-50);
			setVisible(false);
			setOpaque(false);

			JLayeredPane jlp = new JLayeredPane();
			jlp.setPreferredSize(new Dimension(ANNdroid.SCREEN_WIDTH, ANNdroid.SCREEN_HEIGHT-50));
			jlp.setBounds(0, 0, ANNdroid.SCREEN_WIDTH, ANNdroid.SCREEN_HEIGHT-50);

			errorLabel = new JLabel("");
			errorLabel.setForeground(Color.RED);
			errorLabel.setBounds(260, 130, 280, 25);
			jlp.add(errorLabel);

			oldPassLabel = new JLabel("old password: ");
			oldPassLabel.setForeground(Color.WHITE);
			oldPassLabel.setBounds(260, 160, 90, 20);
			jlp.add(oldPassLabel);

			oldPassField = new JPasswordField("", 15);
			oldPassField.setBounds(350, 160, 200, 25);
			oldPassField.addFocusListener(new FocusListener(){
				
				public void focusGained(FocusEvent e){} //Do nothing

				public void focusLost(FocusEvent e){
					oldPass = String.valueOf(oldPassField.getPassword());
				}
			});
			jlp.add(oldPassField);

			newPassLabel = new JLabel("new password: ");
			newPassLabel.setForeground(Color.WHITE);
			newPassLabel.setBounds(260, 200, 90, 20);
			jlp.add(newPassLabel);
			
			newPassField = new JPasswordField("", 15);
			newPassField.setBounds(350, 200, 200, 25);
			newPassField.addFocusListener(new FocusListener(){
				
				public void focusGained(FocusEvent e){} //Do nothing

				public void focusLost(FocusEvent e){
					newPass = String.valueOf(newPassField.getPassword());
				}
			});
			jlp.add(newPassField);

			confirmNewPassLabel = new JLabel("confirm new password: ");
			confirmNewPassLabel.setForeground(Color.WHITE);
			confirmNewPassLabel.setBounds(260, 240, 140, 20);
			jlp.add(confirmNewPassLabel);
	
			confirmNewPassField = new JPasswordField("", 15);
			confirmNewPassField.setBounds(400, 240, 150, 25);
			confirmNewPassField.addFocusListener(new FocusListener(){
				
				public void focusGained(FocusEvent e){} //Do nothing

				public void focusLost(FocusEvent e){
					newPassConfirm = String.valueOf(confirmNewPassField.getPassword());
				}
			});
			jlp.add(confirmNewPassField);

			changeBtn = new JButton("change");
			changeBtn.setBounds(390, 280, 80, 20);
			changeBtn.addActionListener(event->{
				if(user.getPassword().equals(Utilities.hashPassword(oldPass))){
					if(newPass.equals(newPassConfirm)){
						user.setPassword(Utilities.hashPassword(newPass));
						ANNdroid.saver.saveUsers(ANNdroid.userList, "ANNdroid/bin/users.bin");
						errorLabel.setText("* password has been changed");
						errorLabel.setForeground(Color.YELLOW);
					}
					else{
						errorLabel.setText("* new password and confirmation did not match");
						errorLabel.setForeground(Color.RED);
					}
				}
				else{
					errorLabel.setText("* incorrect old password");
					errorLabel.setForeground(Color.RED);
				}
				reinitialize();
			});
			jlp.add(changeBtn);

			cancelBtn = new JButton("cancel");
			cancelBtn.setBounds(475, 280, 75, 20);
			cancelBtn.addActionListener(event->{
				changePassPanel.setVisible(false);
				errorLabel.setText("");
				reinitialize();
			});
			jlp.add(cancelBtn);

			add(jlp);
		}

		public void reinitialize(){
			oldPass = "";
			newPass = "";
			newPassConfirm = "";
			oldPassField.setText("");
			newPassField.setText("");
			confirmNewPassField.setText("");
		}
	}
}