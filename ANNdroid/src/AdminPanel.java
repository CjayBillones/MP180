package ANNdroid.src;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.CardLayout;
import java.awt.event.FocusEvent;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusListener;
import java.awt.event.ActionListener;

public class AdminPanel extends JPanel{

	// Left Side Pane Layout //
	JPanel leftSidePane;
	JPanel buttonPane;

	JButton createAcctBtn;
	JButton deleteAcctBtn;
	JButton changePwordBtn;
	JButton logoutBtn;

	// Change Password Option Layout //
	JPanel changePwordPane;

	JLabel errorLabel;
	JLabel oldPwordLabel;
	JLabel newPwordLabel;
	JLabel conPwordLabel;
	JPasswordField oldPwordField;
	JPasswordField newPwordField;
	JPasswordField conPwordField;
	JButton confirmBtn;
	JButton cancelBtn1;

	// For Change Password //
	String oldPword = "";
	String newPword = "";
	String conPword = "";

	// Create Teacher Account Option Layout //
	JPanel createTeachPane;

	JLabel responseLabel;
	JLabel fnameLabel;
	JLabel lnameLabel;
	JLabel unameLabel;
	JLabel pwordLabel;
	JTextField fnameField;
	JTextField lnameField;
	JTextField unameField;
	JPasswordField pwordField;
	JButton createBtn;

	// Create Teacher Values //
	String fname;
	String lname;
	String uname;
	String pword;

	public AdminPanel(JPanel bgPanel){

		setLayout(null);
		setPreferredSize(new Dimension(ANNdroid.SCREEN_WIDTH, ANNdroid.SCREEN_HEIGHT));
		setBounds(0, 0, ANNdroid.SCREEN_WIDTH, ANNdroid.SCREEN_HEIGHT);

		// Left Side Pane Layout Initialization //
		leftSidePane = new JPanel(null);
		leftSidePane.setPreferredSize(new Dimension(getWidth()/4 + 60, getHeight()));
		leftSidePane.setBounds(0, 0, getWidth()/4 + 60, getHeight());
		leftSidePane.setBackground(Color.BLACK);

		buttonPane = new JPanel(null);
		buttonPane.setPreferredSize(new Dimension(getWidth()/4, getHeight()/2));
		buttonPane.setBounds(30, getHeight()/8, getWidth()/4, getHeight()/2 + getHeight()/4);
		leftSidePane.add(buttonPane);

		createAcctBtn = new JButton("Create Teacher Account");
		createAcctBtn.setBounds(0, 0, buttonPane.getWidth(), 50);
		buttonPane.add(createAcctBtn);
		createAcctBtn.addActionListener(new LeftSidePaneButtonsListener(0));

		deleteAcctBtn = new JButton("Delete Teacher Account");
		deleteAcctBtn.setBounds(0, 50, buttonPane.getWidth(), 50);
		buttonPane.add(deleteAcctBtn);
		deleteAcctBtn.addActionListener(new LeftSidePaneButtonsListener(1));

		changePwordBtn = new JButton("Change Password");
		changePwordBtn.setBounds(0, 100, buttonPane.getWidth(), 50);
		buttonPane.add(changePwordBtn);
		changePwordBtn.addActionListener(new LeftSidePaneButtonsListener(2));

		logoutBtn = new JButton("Logout");
		logoutBtn.setBounds(0, buttonPane.getHeight()-50, buttonPane.getWidth(), 50);
		buttonPane.add(logoutBtn);
		logoutBtn.addActionListener(new LeftSidePaneButtonsListener(3));
		logoutBtn.addFocusListener(new FocusListener(){
			public void focusGained(FocusEvent e){}
			public void focusLost(FocusEvent e){
				createAcctBtn.requestFocus();
			}
		});

		// Change Password Pane Initialization //
		changePwordPane = new JPanel(null);
		changePwordPane.setPreferredSize(new Dimension(getWidth() - leftSidePane.getWidth() - 200, getHeight()-430));
		changePwordPane.setBounds(leftSidePane.getWidth() + 100, getHeight()/3, getWidth() - leftSidePane.getWidth() - 200, getHeight()-430);
		changePwordPane.setVisible(false);

		oldPwordLabel = new JLabel("Old Password: ", SwingConstants.RIGHT);
		oldPwordLabel.setBounds(0, changePwordPane.getHeight()/4, changePwordPane.getWidth()/2, 20);
		oldPwordLabel.setForeground(Color.WHITE);
		oldPwordLabel.setBackground(Color.BLACK);
		oldPwordLabel.setOpaque(true);
		changePwordPane.add(oldPwordLabel);

		oldPwordField = new JPasswordField();
		oldPwordField.setBounds(oldPwordLabel.getWidth(), oldPwordLabel.getY(), (7*changePwordPane.getWidth())/16, 20);
		changePwordPane.add(oldPwordField);
		oldPwordField.addFocusListener(new ChangePasswordFieldsFocusListener(0));
		oldPwordField.addActionListener(event -> {newPwordField.requestFocus();});

		newPwordLabel = new JLabel("New Password: ", SwingConstants.RIGHT);
		newPwordLabel.setBounds(oldPwordLabel.getX(), oldPwordLabel.getY() + 30, oldPwordLabel.getWidth(), 20);
		newPwordLabel.setForeground(Color.WHITE);
		newPwordLabel.setBackground(Color.BLACK);
		newPwordLabel.setOpaque(true);
		changePwordPane.add(newPwordLabel);

		newPwordField = new JPasswordField();
		newPwordField.setBounds(oldPwordField.getX(), oldPwordField.getY() + 30, oldPwordField.getWidth(), 20);
		changePwordPane.add(newPwordField);
		newPwordField.addFocusListener(new ChangePasswordFieldsFocusListener(1));
		newPwordField.addActionListener(event -> {conPwordField.requestFocus();});

		conPwordLabel = new JLabel("Confirm New Password: ", SwingConstants.RIGHT);
		conPwordLabel.setBounds(newPwordLabel.getX(), newPwordLabel.getY() + 30, oldPwordLabel.getWidth(), 20);
		conPwordLabel.setForeground(Color.WHITE);
		conPwordLabel.setBackground(Color.BLACK);
		conPwordLabel.setOpaque(true);
		changePwordPane.add(conPwordLabel);		

		conPwordField = new JPasswordField();
		conPwordField.setBounds(oldPwordField.getX(), newPwordField.getY() + 30, oldPwordField.getWidth(), 20);
		changePwordPane.add(conPwordField);
		conPwordField.addFocusListener(new ChangePasswordFieldsFocusListener(2));
		conPwordField.addActionListener(new ChangePasswordActionListener(0));

		confirmBtn = new JButton("Confirm");
		confirmBtn.setBounds(changePwordPane.getWidth()/16, conPwordLabel.getY() + 30, conPwordLabel.getWidth() - changePwordPane.getWidth()/16,changePwordPane.getHeight()/7);
		changePwordPane.add(confirmBtn);
		confirmBtn.addActionListener(new ChangePasswordActionListener(1));

		cancelBtn1 = new JButton("Cancel");
		cancelBtn1.setBounds(confirmBtn.getX() + confirmBtn.getWidth(), confirmBtn.getY(), confirmBtn.getWidth(), confirmBtn.getHeight());
		changePwordPane.add(cancelBtn1);
		cancelBtn1.addActionListener(new ChangePasswordActionListener(2));

		errorLabel = new JLabel("");
		errorLabel.setBounds(changePwordPane.getWidth()/16, changePwordPane.getHeight()/4 - 25, confirmBtn.getWidth() + cancelBtn1.getWidth(), 20);
		errorLabel.setForeground(Color.RED);
		errorLabel.setBackground(Color.BLACK);
		errorLabel.setOpaque(true);
		changePwordPane.add(errorLabel);		

		// Create Admin Pane Initialization //
		createTeachPane = new JPanel(null);
		createTeachPane.setPreferredSize(new Dimension(getWidth() - leftSidePane.getWidth() - 200, getHeight()-430));
		createTeachPane.setBounds(leftSidePane.getWidth() + 100, getHeight()/3, getWidth() - leftSidePane.getWidth() - 200, getHeight()-430);
		createTeachPane.setVisible(false);

		fnameLabel = new JLabel("First Name: ", SwingConstants.RIGHT);
		fnameLabel.setBounds(0, createTeachPane.getHeight()/4, createTeachPane.getWidth()/2, 20);
		fnameLabel.setForeground(Color.WHITE);
		fnameLabel.setBackground(Color.BLACK);
		fnameLabel.setOpaque(true);
		createTeachPane.add(fnameLabel);

		fnameField = new JTextField();
		fnameField.setBounds(fnameLabel.getWidth(), fnameLabel.getY(), (7*createTeachPane.getWidth())/16, 20);
		createTeachPane.add(fnameField);
		fnameField.addFocusListener(new CreateTeachFieldsFocusListener(0));
		fnameField.addActionListener(event -> {lnameField.requestFocus();});

		lnameLabel = new JLabel("Username: ", SwingConstants.RIGHT);
		lnameLabel.setBounds(fnameLabel.getX(), fnameLabel.getY() + 30, fnameLabel.getWidth(), 20);
		lnameLabel.setForeground(Color.WHITE);
		lnameLabel.setBackground(Color.BLACK);
		lnameLabel.setOpaque(true);
		createTeachPane.add(lnameLabel);

		lnameField = new JTextField();
		lnameField.setBounds(lnameLabel.getWidth(), lnameLabel.getY(), fnameField.getWidth(), 20);
		createTeachPane.add(lnameField);
		lnameField.addFocusListener(new CreateTeachFieldsFocusListener(1));
		lnameField.addActionListener(event -> {unameField.requestFocus();});

		unameLabel = new JLabel("Username: ", SwingConstants.RIGHT);
		unameLabel.setBounds(fnameLabel.getX(), lnameField.getY() + 30, fnameLabel.getWidth(), 20);
		unameLabel.setForeground(Color.WHITE);
		unameLabel.setBackground(Color.BLACK);
		unameLabel.setOpaque(true);
		createTeachPane.add(unameLabel);

		unameField = new JTextField();
		unameField.setBounds(unameLabel.getWidth(), unameLabel.getY(), fnameField.getWidth(), 20);
		createTeachPane.add(unameField);
		unameField.addFocusListener(new CreateTeachFieldsFocusListener(2));
		unameField.addActionListener(event -> {pwordField.requestFocus();});

		pwordLabel = new JLabel("Password: ", SwingConstants.RIGHT);
		unameLabel.setBounds(fnameLabel.getX(), unameField.getY() + 30, fnameLabel.getWidth(), 20);
		pwordLabel.setForeground(Color.WHITE);
		pwordLabel.setBackground(Color.BLACK);
		pwordLabel.setOpaque(true);
		createTeachPane.add(pwordLabel);

		pwordField = new JPasswordField();
		pwordField.setBounds(oldPwordField.getWidth(), pwordLabel.getY() + 30, oldPwordField.getWidth(), 20);
		createTeachPane.add(pwordField);
		pwordField.addFocusListener(new ChangePasswordFieldsFocusListener(3));
		pwordField.addActionListener(new CreateTeachActionListener());

		createBtn = new JButton("Create");
		createBtn.setBounds(createTeachPane.getWidth()/16, pwordLabel.getY() + 30, pwordLabel.getWidth() - createTeachPane.getWidth()/16, createTeachPane.getHeight()/7);
		createTeachPane.add(createBtn);
		createBtn.addActionListener(new CreateTeachActionListener());

		responseLabel = new JLabel("");
		responseLabel.setBounds(createTeachPane.getWidth()/16, createTeachPane.getHeight()/4 - 25, createBtn.getWidth(), 20);
		responseLabel.setForeground(Color.RED);
		responseLabel.setBackground(Color.BLACK);
		responseLabel.setOpaque(true);
		createTeachPane.add(responseLabel);

		add(leftSidePane);
		add(changePwordPane);
		add(createTeachPane);
	}

	public void resize(){
		// Resize Left Side Pane //
		leftSidePane.setBounds(0, 0, getWidth()/4 + 60, getHeight());
		buttonPane.setBounds(30, getHeight()/8, getWidth()/4, getHeight()/2 + getHeight()/4);
		createAcctBtn.setBounds(0, 0, buttonPane.getWidth(), 50);
		deleteAcctBtn.setBounds(0, 50, buttonPane.getWidth(), 50);
		changePwordBtn.setBounds(0, 100, buttonPane.getWidth(), 50);
		logoutBtn.setBounds(0, buttonPane.getHeight()-50, buttonPane.getWidth(), 50);
		
		// Resize Change Password Pane //
		changePwordPane.setBounds(leftSidePane.getWidth() + 100, getHeight()/3, getWidth() - leftSidePane.getWidth() - 200, changePwordPane.getHeight());
		oldPwordLabel.setBounds(0, changePwordPane.getHeight()/4, changePwordPane.getWidth()/2, 20);
		oldPwordField.setBounds(oldPwordLabel.getWidth(), oldPwordLabel.getY(), (7*changePwordPane.getWidth())/16, 20);
		newPwordLabel.setBounds(oldPwordLabel.getX(), oldPwordLabel.getY() + 30, oldPwordLabel.getWidth(), 20);
		newPwordField.setBounds(oldPwordField.getX(), newPwordLabel.getY(), oldPwordField.getWidth(), 20);
		conPwordLabel.setBounds(newPwordLabel.getX(), newPwordLabel.getY() + 30, oldPwordLabel.getWidth(), 20);
		conPwordField.setBounds(oldPwordField.getX(), conPwordLabel.getY(), oldPwordField.getWidth(), 20);
		confirmBtn.setBounds(changePwordPane.getWidth()/16, conPwordLabel.getY() + 30, conPwordLabel.getWidth() - changePwordPane.getWidth()/16,changePwordPane.getHeight()/7);
		cancelBtn1.setBounds(confirmBtn.getX() + confirmBtn.getWidth(), confirmBtn.getY(), confirmBtn.getWidth(), confirmBtn.getHeight());		
		errorLabel.setBounds(changePwordPane.getWidth()/16, changePwordPane.getHeight()/4 - 25, confirmBtn.getWidth() + cancelBtn1.getWidth(), 20);
	}

	public void reinitialize(boolean error, boolean changeMode, int state){

		// state: 0 - Create Account //
		// state: 1 - Delete Account //
		// state: 2 - Change Password //
		if(state == 0){
			if(changeMode || !error) responseLabel.setText("");

			fname = "";
			lname = "";
			uname = "";
			pword = "";
			fnameField.setText("");
			lnameField.setText("");
			unameField.setText("");
			pwordField.setText("");
		}
		else if(state == 2){
			if(changeMode || !error) errorLabel.setText("");
			
			oldPword = "";
			newPword = "";
			conPword = "";
			oldPwordField.setText("");
			newPwordField.setText("");
			conPwordField.setText("");
		}
	}

	private class ChangePasswordFieldsFocusListener extends FocusAdapter{
		// mode: 0 - old password //
		// mode: 1 - new password //
		// mode: 2 - con password //

		int mode;

		public ChangePasswordFieldsFocusListener(int mode){
			this.mode = mode;
		}

		public void focusLost(FocusEvent e){
			if(this.mode == 0) oldPword = Utilities.hashPassword(String.valueOf(oldPwordField.getPassword()));
			else if(this.mode == 1) newPword = String.valueOf(newPwordField.getPassword());
			else conPword = String.valueOf(conPwordField.getPassword());
		}

	}

	private class ChangePasswordActionListener implements ActionListener{

		// mode: 0 - pressed enter on con password //
		// mode: 1 - confirm button //
		// mode: 2 - cancel button//

		int mode;

		public ChangePasswordActionListener(int mode){
			this.mode = mode;
		}

		public void actionPerformed(ActionEvent e){

			if(mode == 2){
				reinitialize(false, true, 2);
				changePwordPane.setVisible(false);
			}
			else{
				conPword = String.valueOf(conPwordField.getPassword());
				if(!ANNdroid.simulator.active.getPassword().equals(oldPword)){
					errorLabel.setText("* old password did not match");
					errorLabel.setForeground(Color.RED);
					reinitialize(true, false, 2);
				}
				else if(ANNdroid.simulator.active.getPassword().equals(oldPword) && newPword.length() < 6){
					errorLabel.setText("* new password must be at least 6 characters");
					errorLabel.setForeground(Color.RED);
					reinitialize(true, false, 2);
				}
				else if(ANNdroid.simulator.active.getPassword().equals(oldPword) && !newPword.equals(conPword)){
					errorLabel.setText("* new and confirmation password did not match");
					errorLabel.setForeground(Color.RED);
					reinitialize(true, false, 2);
				}
				else{
					errorLabel.setText("* successfully changed password");
					errorLabel.setForeground(Color.YELLOW);

					ANNdroid.simulator.active.setPassword(Utilities.hashPassword(newPword));
					ANNdroid.simulator.saver.saveUsers(Simulator.userList, "ANNdroid/bin/users.bin");

					reinitialize(true, false, 2);					
				}
			}
		}

	}

	private class CreateTeachFieldsFocusListener extends FocusAdapter{
		// mode: 0 - first name //
		// mode: 1 - last name //
		// mode: 2 - username //
		// mode: 3 - password //

		int mode;

		public CreateTeachFieldsFocusListener(int mode){
			this.mode = mode;
		}

		public void focusLost(FocusEvent e){
			if(this.mode == 0) fname = String.valueOf(fnameField.getText());
			else if(this.mode == 1) lname = String.valueOf(lnameField.getText());
			else if(this.mode == 2) uname = String.valueOf(unameField.getText());
			else pword = String.valueOf(pwordField.getPassword());
		}
	}

	private class CreateTeachActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(pword.length() < 6){
				responseLabel.setText("* password needs to be atleast 6 characters");
				responseLabel.setForeground(Color.RED);
			}
			else{
				responseLabel.setText("* successfully created teacher account");
				responseLabel.setForeground(Color.YELLOW);

				ANNdroid.simulator.userList.add(new Admin(uname, Utilities.hashPassword(pword), fname, lname));
				ANNdroid.simulator.saver.saveUsers(Simulator.userList, "ANNdroid/bin/users.bin");
			}
			reinitialize(true, false, 0);			
		}
	}

	private class LeftSidePaneButtonsListener implements ActionListener{
		// mode: 0 - Create Account //
		// mode: 1 - Delete Account //
		// mode: 2 - Change Password //
		// mode: 3 - Logout

		int mode;

		public LeftSidePaneButtonsListener(int mode){
			this.mode = mode;
		}

		public void actionPerformed(ActionEvent e){
			if(this.mode == 0){
				changePwordPane.setVisible(false);
				reinitialize(false, true, 2);
			}
			else if(this.mode == 1){
				changePwordPane.setVisible(false);
				reinitialize(false, true, 2);
			}
			else if(this.mode == 2){
				changePwordPane.setVisible(true);
			}
			else{
				changePwordPane.setVisible(false);
				reinitialize(false, true, 2);
				
				ANNdroid.simulator.active = null;
				ANNdroid.loginPanel.add(ANNdroid.bgPanel);
				remove(ANNdroid.bgPanel);
		
				CardLayout c1 = (CardLayout)(ANNdroid.cards.getLayout());
				c1.show(ANNdroid.cards, ANNdroid.LOGINPANEL);				
			}
		}
	}

}