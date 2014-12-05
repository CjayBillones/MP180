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

	// Create Teacher Account Layout //
	JPanel createTeachPane;

	JLabel errorLabel1;
	JLabel fnameLabel;
	JLabel lnameLabel;
	JLabel unameLabel;
	JLabel pwordLabel;
	JTextField fnameField;
	JTextField lnameField;
	JTextField unameField;
	JPasswordField pwordField;
	JButton createBtn;
	JButton cancelBtn1;

	// Create Teacher Values //
	String fname = "";
	String lname = "";
	String uname = "";
	String pword = "";

	// Delete Teacher Account Layout //
	JPanel delTeachPane;

	JLabel errorLabel2;
	JLabel delUnameLabel;
	JLabel adminPwordLabel;
	JTextField delUnameField;
	JPasswordField adminPwordField;
	JButton deleteBtn;
	JButton cancelBtn2;

	// Delete Teacher Values //
	String delUname = "";
	String delPword = "";

	// Change Password Option Layout //
	JPanel changePwordPane;

	JLabel errorLabel3;
	JLabel oldPwordLabel;
	JLabel newPwordLabel;
	JLabel conPwordLabel;
	JPasswordField oldPwordField;
	JPasswordField newPwordField;
	JPasswordField conPwordField;
	JButton confirmBtn;
	JButton cancelBtn3;

	// Change Password Values //
	String oldPword = "";
	String newPword = "";
	String conPword = "";



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
		createAcctBtn.addActionListener(new AdminActionListener(0, 0));

		deleteAcctBtn = new JButton("Delete Teacher Account");
		deleteAcctBtn.setBounds(0, 50, buttonPane.getWidth(), 50);
		buttonPane.add(deleteAcctBtn);
		deleteAcctBtn.addActionListener(new AdminActionListener(0, 1));

		changePwordBtn = new JButton("Change Password");
		changePwordBtn.setBounds(0, 100, buttonPane.getWidth(), 50);
		buttonPane.add(changePwordBtn);
		changePwordBtn.addActionListener(new AdminActionListener(0, 2));

		logoutBtn = new JButton("Logout");
		logoutBtn.setBounds(0, buttonPane.getHeight()-50, buttonPane.getWidth(), 50);
		buttonPane.add(logoutBtn);
		logoutBtn.addActionListener(new AdminActionListener(0, 3));
		logoutBtn.addFocusListener(new FocusListener(){
			public void focusGained(FocusEvent e){}
			public void focusLost(FocusEvent e){
				createAcctBtn.requestFocus();
			}
		});

		// Create Teach Pane Initialization //
		createTeachPane = new JPanel(null);
		createTeachPane.setPreferredSize(new Dimension(getWidth() - leftSidePane.getWidth() - 200, getHeight()-400));
		createTeachPane.setBounds(leftSidePane.getWidth() + 100, getHeight()/3, getWidth() - leftSidePane.getWidth() - 200, getHeight()-400);
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
		fnameField.addFocusListener(new FieldsFocusListener(0, 0));
		fnameField.addActionListener(event -> {lnameField.requestFocus();});

		lnameLabel = new JLabel("Last Name: ", SwingConstants.RIGHT);
		lnameLabel.setBounds(fnameLabel.getX(), fnameLabel.getY() + 30, fnameLabel.getWidth(), 20);
		lnameLabel.setForeground(Color.WHITE);
		lnameLabel.setBackground(Color.BLACK);
		lnameLabel.setOpaque(true);
		createTeachPane.add(lnameLabel);

		lnameField = new JTextField();
		lnameField.setBounds(lnameLabel.getWidth(), lnameLabel.getY(), fnameField.getWidth(), 20);
		createTeachPane.add(lnameField);
		lnameField.addFocusListener(new FieldsFocusListener(0, 1));
		lnameField.addActionListener(event -> {unameField.requestFocus();});

		unameLabel = new JLabel("Username: ", SwingConstants.RIGHT);
		unameLabel.setBounds(fnameLabel.getX(), lnameLabel.getY() + 30, fnameLabel.getWidth(), 20);
		unameLabel.setForeground(Color.WHITE);
		unameLabel.setBackground(Color.BLACK);
		unameLabel.setOpaque(true);
		createTeachPane.add(unameLabel);

		unameField = new JTextField();
		unameField.setBounds(unameLabel.getWidth(), unameLabel.getY(), fnameField.getWidth(), 20);
		createTeachPane.add(unameField);
		unameField.addFocusListener(new FieldsFocusListener(0, 2));
		unameField.addActionListener(event -> {pwordField.requestFocus();});

		pwordLabel = new JLabel("Password: ", SwingConstants.RIGHT);
		pwordLabel.setBounds(fnameLabel.getX(), unameField.getY() + 30, fnameLabel.getWidth(), 20);
		pwordLabel.setForeground(Color.WHITE);
		pwordLabel.setBackground(Color.BLACK);
		pwordLabel.setOpaque(true);
		createTeachPane.add(pwordLabel);

		pwordField = new JPasswordField();
		pwordField.setBounds(fnameLabel.getWidth(), pwordLabel.getY(), fnameField.getWidth(), 20);
		createTeachPane.add(pwordField);
		pwordField.addFocusListener(new FieldsFocusListener(0, 3));

		createBtn = new JButton("Create");
		createBtn.setBounds(createTeachPane.getWidth()/16, pwordLabel.getY() + 30, pwordLabel.getWidth() - createTeachPane.getWidth()/16, createTeachPane.getHeight()/7);
		createTeachPane.add(createBtn);
		createBtn.addActionListener(new AdminActionListener(1, 0));

		cancelBtn1 = new JButton("Cancel");
		cancelBtn1.setBounds(createBtn.getX() + createBtn.getWidth(), createBtn.getY(), createBtn.getWidth(), createBtn.getHeight());
		createTeachPane.add(cancelBtn1);
		cancelBtn1.addActionListener(new AdminActionListener(1, 1));

		errorLabel1 = new JLabel("");
		errorLabel1.setBounds(createTeachPane.getWidth()/16, createTeachPane.getHeight()/4 - 25, createTeachPane.getWidth() - (createTeachPane.getWidth()/8), 20);
		errorLabel1.setForeground(Color.RED);
		errorLabel1.setBackground(Color.BLACK);
		errorLabel1.setOpaque(true);
		createTeachPane.add(errorLabel1);

		// Delete Teacher Pane Initialization //
		delTeachPane = new JPanel(null);
		delTeachPane.setPreferredSize(new Dimension(getWidth() - leftSidePane.getWidth() - 200, getHeight() - 470));
		delTeachPane.setBounds(leftSidePane.getWidth() + 100, getHeight()/3, getWidth() - leftSidePane.getWidth() - 200, getHeight() - 470);
		delTeachPane.setVisible(false);

		delUnameLabel = new JLabel("username: ", SwingConstants.RIGHT);
		delUnameLabel.setBounds(0, delTeachPane.getHeight()/3, delTeachPane.getWidth()/2, 20);
		delUnameLabel.setForeground(Color.WHITE);
		delUnameLabel.setBackground(Color.BLACK);
		delUnameLabel.setOpaque(true);
		delTeachPane.add(delUnameLabel);

		delUnameField = new JTextField();
		delUnameField.setBounds(delUnameLabel.getWidth(), delUnameLabel.getY(), (7*delTeachPane.getWidth())/16, 20);
		delTeachPane.add(delUnameField);
		delUnameField.addFocusListener(new FieldsFocusListener(1, 0));
		delUnameField.addActionListener(event -> {adminPwordField.requestFocus();});

		adminPwordLabel = new JLabel("admin password: ", SwingConstants.RIGHT);
		adminPwordLabel.setBounds(delUnameLabel.getX(), delUnameLabel.getY() + 30, delUnameLabel.getWidth(), 20);
		adminPwordLabel.setForeground(Color.WHITE);
		adminPwordLabel.setBackground(Color.BLACK);
		adminPwordLabel.setOpaque(true);		
		delTeachPane.add(adminPwordLabel);

		adminPwordField = new JPasswordField();
		adminPwordField.setBounds(delUnameField.getX(), delUnameField.getY() + 30, delUnameField.getWidth(), 20);
		delTeachPane.add(adminPwordField);
		delUnameField.addFocusListener(new FieldsFocusListener(1, 1));

		deleteBtn = new JButton("Confirm");
		deleteBtn.setBounds(delTeachPane.getWidth()/16, adminPwordLabel.getY() + 30, adminPwordLabel.getWidth() - delTeachPane.getWidth()/16, delTeachPane.getHeight()/7);
		delTeachPane.add(deleteBtn);
		deleteBtn.addActionListener(new AdminActionListener(2, 0));

		cancelBtn2 = new JButton("Cancel");
		cancelBtn2.setBounds(deleteBtn.getX() + deleteBtn.getWidth(), deleteBtn.getY(), deleteBtn.getWidth(), deleteBtn.getHeight());
		delTeachPane.add(cancelBtn2);
		cancelBtn2.addActionListener(new AdminActionListener(2, 1));		

		errorLabel2 = new JLabel("");
		errorLabel2.setBounds(delTeachPane.getWidth()/16, delTeachPane.getHeight()/3 - 25, deleteBtn.getWidth() + cancelBtn2.getWidth(), 20);
		errorLabel2.setForeground(Color.RED);
		errorLabel2.setBackground(Color.BLACK);
		errorLabel2.setOpaque(true);
		delTeachPane.add(errorLabel2);		

		// Change Password Pane Initialization //
		changePwordPane = new JPanel(null);
		changePwordPane.setPreferredSize(new Dimension(getWidth() - leftSidePane.getWidth() - 200, getHeight() - 430));
		changePwordPane.setBounds(leftSidePane.getWidth() + 100, getHeight()/3, getWidth() - leftSidePane.getWidth() - 200, getHeight() - 430);
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
		oldPwordField.addFocusListener(new FieldsFocusListener(2, 0));
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
		newPwordField.addFocusListener(new FieldsFocusListener(2, 1));
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
		conPwordField.addFocusListener(new FieldsFocusListener(2, 2));
		conPwordField.addActionListener(new AdminActionListener(3, 0));

		confirmBtn = new JButton("Confirm");
		confirmBtn.setBounds(changePwordPane.getWidth()/16, conPwordLabel.getY() + 30, conPwordLabel.getWidth() - changePwordPane.getWidth()/16,changePwordPane.getHeight()/7);
		changePwordPane.add(confirmBtn);
		confirmBtn.addActionListener(new AdminActionListener(3, 1));

		cancelBtn3 = new JButton("Cancel");
		cancelBtn3.setBounds(confirmBtn.getX() + confirmBtn.getWidth(), confirmBtn.getY(), confirmBtn.getWidth(), confirmBtn.getHeight());
		changePwordPane.add(cancelBtn3);
		cancelBtn3.addActionListener(new AdminActionListener(3, 2));

		errorLabel3 = new JLabel("");
		errorLabel3.setBounds(changePwordPane.getWidth()/16, changePwordPane.getHeight()/4 - 25, confirmBtn.getWidth() + cancelBtn3.getWidth(), 20);
		errorLabel3.setForeground(Color.RED);
		errorLabel3.setBackground(Color.BLACK);
		errorLabel3.setOpaque(true);
		changePwordPane.add(errorLabel3);		

		add(leftSidePane);
		add(createTeachPane);
		add(delTeachPane);
		add(changePwordPane);
	}

	public void resize(){
		// Resize Left Side Pane //
		leftSidePane.setBounds(0, 0, getWidth()/4 + 60, getHeight());
		buttonPane.setBounds(30, getHeight()/8, getWidth()/4, getHeight()/2 + getHeight()/4);
		createAcctBtn.setBounds(0, 0, buttonPane.getWidth(), 50);
		deleteAcctBtn.setBounds(0, 50, buttonPane.getWidth(), 50);
		changePwordBtn.setBounds(0, 100, buttonPane.getWidth(), 50);
		logoutBtn.setBounds(0, buttonPane.getHeight()-50, buttonPane.getWidth(), 50);

		// Resize CreateTeach Pane //
		createTeachPane.setBounds(leftSidePane.getWidth() + 100, getHeight()/3, getWidth() - leftSidePane.getWidth() - 200, createTeachPane.getHeight());
		fnameLabel.setBounds(0, createTeachPane.getHeight()/4, createTeachPane.getWidth()/2, 20);
		fnameField.setBounds(fnameLabel.getWidth(), fnameLabel.getY(), (7*createTeachPane.getWidth())/16, 20);
		lnameLabel.setBounds(fnameLabel.getX(), fnameLabel.getY() + 30, fnameLabel.getWidth(), 20);
		lnameField.setBounds(lnameLabel.getWidth(), lnameLabel.getY(), fnameField.getWidth(), 20);
		unameLabel.setBounds(fnameLabel.getX(), lnameLabel.getY() + 30, fnameLabel.getWidth(), 20);
		unameField.setBounds(unameLabel.getWidth(), unameLabel.getY(), fnameField.getWidth(), 20);
		pwordLabel.setBounds(fnameLabel.getX(), unameField.getY() + 30, fnameLabel.getWidth(), 20);
		pwordField.setBounds(fnameLabel.getWidth(), pwordLabel.getY(), fnameField.getWidth(), 20);
		createBtn.setBounds(createTeachPane.getWidth()/16, pwordLabel.getY() + 30, pwordLabel.getWidth() - createTeachPane.getWidth()/16, createTeachPane.getHeight()/7);
		cancelBtn1.setBounds(createBtn.getX() + createBtn.getWidth(), createBtn.getY(), createBtn.getWidth(), createBtn.getHeight());
		errorLabel1.setBounds(createTeachPane.getWidth()/16, createTeachPane.getHeight()/4 - 25, createTeachPane.getWidth() - (createTeachPane.getWidth()/8), 20);
		
		// Resize DelTeach Pane //
		delTeachPane.setBounds(leftSidePane.getWidth() + 100, getHeight()/3, getWidth() - leftSidePane.getWidth() - 200, delTeachPane.getHeight());
		delUnameLabel.setBounds(0, delTeachPane.getHeight()/3, delTeachPane.getWidth()/2, 20);
		delUnameField.setBounds(delUnameLabel.getWidth(), delUnameLabel.getY(), (7*delTeachPane.getWidth())/16, 20);
		adminPwordLabel.setBounds(delUnameLabel.getX(), delUnameLabel.getY() + 30, delUnameLabel.getWidth(), 20);
		adminPwordField.setBounds(delUnameField.getX(), delUnameField.getY() + 30, delUnameField.getWidth(), 20);
		deleteBtn.setBounds(delTeachPane.getWidth()/16, adminPwordLabel.getY() + 30, adminPwordLabel.getWidth() - delTeachPane.getWidth()/16, delTeachPane.getHeight()/7);
		cancelBtn2.setBounds(deleteBtn.getX() + deleteBtn.getWidth(), deleteBtn.getY(), deleteBtn.getWidth(), deleteBtn.getHeight());
		errorLabel2.setBounds(delTeachPane.getWidth()/16, delTeachPane.getHeight()/3 - 25, deleteBtn.getWidth() + cancelBtn2.getWidth(), 20);

		// Resize Change Password Pane //
		changePwordPane.setBounds(leftSidePane.getWidth() + 100, getHeight()/3, getWidth() - leftSidePane.getWidth() - 200, changePwordPane.getHeight());
		oldPwordLabel.setBounds(0, changePwordPane.getHeight()/4, changePwordPane.getWidth()/2, 20);
		oldPwordField.setBounds(oldPwordLabel.getWidth(), oldPwordLabel.getY(), (7*changePwordPane.getWidth())/16, 20);
		newPwordLabel.setBounds(oldPwordLabel.getX(), oldPwordLabel.getY() + 30, oldPwordLabel.getWidth(), 20);
		newPwordField.setBounds(oldPwordField.getX(), newPwordLabel.getY(), oldPwordField.getWidth(), 20);
		conPwordLabel.setBounds(newPwordLabel.getX(), newPwordLabel.getY() + 30, oldPwordLabel.getWidth(), 20);
		conPwordField.setBounds(oldPwordField.getX(), conPwordLabel.getY(), oldPwordField.getWidth(), 20);
		confirmBtn.setBounds(changePwordPane.getWidth()/16, conPwordLabel.getY() + 30, conPwordLabel.getWidth() - changePwordPane.getWidth()/16,changePwordPane.getHeight()/7);
		cancelBtn3.setBounds(confirmBtn.getX() + confirmBtn.getWidth(), confirmBtn.getY(), confirmBtn.getWidth(), confirmBtn.getHeight());		
		errorLabel3.setBounds(changePwordPane.getWidth()/16, changePwordPane.getHeight()/4 - 25, confirmBtn.getWidth() + cancelBtn1.getWidth(), 20);

	}

	public void reinitialize(boolean error, boolean changeMode, int state){

		// state: 0 - Create Account //
		// state: 1 - Delete Account //
		// state: 2 - Change Password //
		if(state == 0){
			if(changeMode || !error) errorLabel1.setText("");

			fname = "";
			lname = "";
			uname = "";
			pword = "";
			fnameField.setText("");
			lnameField.setText("");
			unameField.setText("");
			pwordField.setText("");
		}
		else if(state == 1){
			if(changeMode || !error) errorLabel2.setText("");

			delUname = "";
			delPword = "";
			delUnameField.setText("");
			adminPwordField.setText("");
		}
		else if(state == 2){
			if(changeMode || !error) errorLabel3.setText("");
			
			oldPword = "";
			newPword = "";
			conPword = "";
			oldPwordField.setText("");
			newPwordField.setText("");
			conPwordField.setText("");
		}
	}

	private class FieldsFocusListener extends FocusAdapter{
		// state: 0 - Create Teacher Account //
			// mode: 0 - fname //
			// mode: 1 - lname //
			// mode: 2 - uname //
			// mode: 3 - pword //
		// state: 1 - Delete Teacher Account //
			// mode: 0 - delUname //
			// mode: 1 - delPword //
		// state: 2 - Change Passsword //
			// mode: 0 - old password //
			// mode: 1 - new password //
			// mode: 2 - con password //

		int state;
		int mode;

		public FieldsFocusListener(int state, int mode){
			this.state = state;
			this.mode = mode;
		}

		public void focusLost(FocusEvent e){
			if(this.state == 0){
				if(this.mode == 0) fname = String.valueOf(fnameField.getText());
				else if(this.mode == 1) lname = String.valueOf(lnameField.getText());
				else if(this.mode == 2) uname = String.valueOf(unameField.getText());
				else pword = String.valueOf(pwordField.getPassword());				
			}
			else if(this.state == 1){
				if(this.mode == 0) delUname = String.valueOf(delUnameField.getText());
				else if(this.mode == 1) delPword = String.valueOf(adminPwordField.getPassword());
			}
			else if(this.state == 2){
				if(this.mode == 0) oldPword = Utilities.hashPassword(String.valueOf(oldPwordField.getPassword()));
				else if(this.mode == 1) newPword = String.valueOf(newPwordField.getPassword());
				else conPword = String.valueOf(conPwordField.getPassword());
			}
		}

	}

	private class AdminActionListener implements ActionListener{
		// state: 0 - Left Side Pane Buttons //
			// mode: 0 - Create Teacher Account Pane //
			// mode: 1 - Delete Teacher Account Pane //
			// mode: 2 - Change Password Pane //
			// mode: otherwise - Logout //
		// state: 1 - Create Teacher Account //
			// mode: 0 - create button //
			// mode: 1 - cancel button //
		// state: 2 - Delete Teacher Account //
			// mode: 0 - delete button //
			// mode: 1 - cancel button //
		// state: 3 - Change Password //
			// mode: 0 - pressed enter on con password //
			// mode: 1 - confirm button //
			// mode: 2 - cancel button//

		int state;
		int mode;

		public AdminActionListener(int state, int mode){
			this.state = state;
			this.mode = mode;
		}

		public void actionPerformed(ActionEvent e){
			if(this.state == 0){
				if(this.mode == 0){
					createTeachPane.setVisible(true);
					
					delTeachPane.setVisible(false);
					reinitialize(false, true, 1);

					changePwordPane.setVisible(false);
					reinitialize(false, true, 2);
				}
				else if(this.mode == 1){
					createTeachPane.setVisible(false);
					reinitialize(false, true, 0);

					delTeachPane.setVisible(true);

					changePwordPane.setVisible(false);
					reinitialize(false, true, 2);


				}
				else if(this.mode == 2){
					createTeachPane.setVisible(false);
					reinitialize(false, true, 0);

					delTeachPane.setVisible(false);
					reinitialize(false, true, 1);

					changePwordPane.setVisible(true);

				}
				else{
					createTeachPane.setVisible(false);
					reinitialize(false, true, 0);

					delTeachPane.setVisible(false);
					reinitialize(false, true, 1);					

					changePwordPane.setVisible(false);
					reinitialize(false, true, 2);

					ANNdroid.simulator.active = null;
					ANNdroid.loginPanel.add(ANNdroid.bgPanel);
					remove(ANNdroid.bgPanel);
			
					CardLayout c1 = (CardLayout)(ANNdroid.cards.getLayout());
					c1.show(ANNdroid.cards, ANNdroid.LOGINPANEL);				
				}				
			}
			else if(this.state == 1){
				if(this.mode == 0){
					if(fname.equals("")){
						errorLabel1.setText("* first name must not be blank");
						errorLabel1.setForeground(Color.RED);
					}
					else if(!Utilities.isAlpha(fname)){
						errorLabel1.setText("* first name must only contain letters or space");
						errorLabel1.setForeground(Color.RED);
					}
					else if(lname.equals("")){
						errorLabel1.setText("* last name must not be blank");
						errorLabel1.setForeground(Color.RED);
					}
					else if(!Utilities.isAlpha(lname)){
						errorLabel1.setText("* last name must only contain letters or space");
						errorLabel1.setForeground(Color.RED);
					}
					else if(uname.equals("")){
						errorLabel1.setText("* username must not be blank");
						errorLabel1.setForeground(Color.RED);
					}
					else if(!Utilities.isAlphaNumeric(uname)){
						errorLabel1.setText("* username must only contain letters or digits");
						errorLabel1.setForeground(Color.RED);
					}
					else if(pword.length() < 6){
						errorLabel1.setText("* password needs to be atleast 6 characters");
						errorLabel1.setForeground(Color.RED);
					}
					else if(Utilities.exists(uname) != -1){
						errorLabel1.setText("* username already exists");
						errorLabel1.setForeground(Color.RED);
					}
					else{
						errorLabel1.setText("* successfully created teacher account");
						errorLabel1.setForeground(Color.YELLOW);
						((Admin)ANNdroid.simulator.active).createAcct(new Teacher(uname, Utilities.hashPassword(pword), fname, lname));
					}
					reinitialize(true, false, 0);
				}
				else{
					reinitialize(false, true, 0);
					createTeachPane.setVisible(false);
				}
			}
			else if(this.state == 2){
				if(this.mode == 1){
					reinitialize(false, true, 1);
					delTeachPane.setVisible(false);
				}
				else{
					delPword = String.valueOf(adminPwordField.getPassword());
					if(!ANNdroid.simulator.active.getPassword().equals(Utilities.hashPassword(delPword))){
						errorLabel2.setText("* incorrect password for admin");
						errorLabel2.setForeground(Color.RED);
					}
					else if(Utilities.exists(delUname) == -1){
						errorLabel2.setText("* username does not exist");
						errorLabel2.setForeground(Color.RED);
					}
					else if(Utilities.exists(delUname) != -1){
						errorLabel2.setText("* successfully deleted teacher account");
						errorLabel2.setForeground(Color.YELLOW);
						((Admin)ANNdroid.simulator.active).delAcct(Utilities.exists(delUname));
					}
					reinitialize(true, false, 1);
				}
			}
			else if(this.state == 3){
				if(this.mode == 2){
					reinitialize(false, true, 2);
					changePwordPane.setVisible(false);
				}
				else{
					conPword = String.valueOf(conPwordField.getPassword());
					if(!ANNdroid.simulator.active.getPassword().equals(oldPword)){
						errorLabel3.setText("* old password did not match");
						errorLabel3.setForeground(Color.RED);
					}
					else if(ANNdroid.simulator.active.getPassword().equals(oldPword) && newPword.length() < 6){
						errorLabel3.setText("* new password must be at least 6 characters");
						errorLabel3.setForeground(Color.RED);
					}
					else if(ANNdroid.simulator.active.getPassword().equals(oldPword) && !newPword.equals(conPword)){
						errorLabel3.setText("* new and confirmation password did not match");
						errorLabel3.setForeground(Color.RED);
					}
					else{
						errorLabel3.setText("* successfully changed password");
						errorLabel3.setForeground(Color.YELLOW);

						ANNdroid.simulator.active.setPassword(Utilities.hashPassword(newPword));
						ANNdroid.simulator.saver.saveUsers(Simulator.userList, "ANNdroid/bin/users.bin");
					}
					reinitialize(true, false, 2);
				}
			}
		}
	}
}