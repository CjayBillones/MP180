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



	public AdminPanel(){

		setLayout(null);
		setPreferredSize(new Dimension(ANNdroid.SCREEN_WIDTH, ANNdroid.SCREEN_HEIGHT));
		setBounds(0, 0, ANNdroid.SCREEN_WIDTH, ANNdroid.SCREEN_HEIGHT);


		// Left Side Pane Layout //
		String labels[] = {"Create Teacher Account", "Delete Teacher Account", "Change Password", "Options", "Logout"};

		leftSidePane = new LeftSidePane(this, 0, 5, labels);
		leftSidePane.setPreferredSize(new Dimension(getWidth()/4 + 60, getHeight()));
		leftSidePane.setBounds(0, 0, getWidth()/4 + 60, getHeight());
		leftSidePane.setBackground(Color.BLACK);

		// Create Teach Pane Initialization //
		createTeachPane = new GenericPane(getWidth() - leftSidePane.getWidth() - 200, getHeight()/3+10);
		createTeachPane.setBounds(leftSidePane.getWidth() + 100, getHeight()/4, getWidth() - leftSidePane.getWidth() - 200, getHeight()/3+10);
		createTeachPane.setVisible(false);

		errorLabel1 = new CustomLabel("", createTeachPane.getWidth() - (createTeachPane.getWidth()/8), 20, 0);
		errorLabel1.setBounds(createTeachPane.getWidth()/16, createTeachPane.getHeight()/4, createTeachPane.getWidth() - (createTeachPane.getWidth()/8), 20);
		createTeachPane.add(errorLabel1);

		fnameLabel = new CustomLabel("First Name: ", createTeachPane.getWidth()/2 - createTeachPane.getWidth()/16, 20, 1);
		fnameLabel.setBounds(createTeachPane.getWidth()/16, errorLabel1.getY() + 30, createTeachPane.getWidth()/2 - createTeachPane.getWidth()/16, 20);
		createTeachPane.add(fnameLabel);

		fnameField = new JTextField();
		fnameField.setBounds(fnameLabel.getWidth()+createTeachPane.getWidth()/16, fnameLabel.getY(), (7*createTeachPane.getWidth())/16, 20);
		createTeachPane.add(fnameField);
		fnameField.addFocusListener(new FieldsFocusListener(0, 0));
		fnameField.addActionListener(event -> {lnameField.requestFocus();});

		lnameLabel = new CustomLabel("Last Name: ", fnameLabel.getWidth(), 20, 1);
		lnameLabel.setBounds(fnameLabel.getX(), fnameLabel.getY()+30, fnameLabel.getWidth(), 20);
		createTeachPane.add(lnameLabel);

		lnameField = new JTextField();
		lnameField.setBounds(fnameField.getX(), lnameLabel.getY(), fnameField.getWidth(), 20);
		createTeachPane.add(lnameField);
		lnameField.addFocusListener(new FieldsFocusListener(0, 1));
		lnameField.addActionListener(event -> {unameField.requestFocus();});

		unameLabel = new CustomLabel("Username: ", lnameLabel.getWidth(), 20, 1);
		unameLabel.setBounds(lnameLabel.getX(), lnameLabel.getY()+30, lnameLabel.getWidth(), 20);
		createTeachPane.add(unameLabel);

		unameField = new JTextField();
		unameField.setBounds(lnameField.getX(), unameLabel.getY(), fnameField.getWidth(), 20);
		createTeachPane.add(unameField);
		unameField.addFocusListener(new FieldsFocusListener(0, 2));
		unameField.addActionListener(event -> {pwordField.requestFocus();});

		pwordLabel = new CustomLabel("Password: ", unameLabel.getWidth(), 20, 1);
		pwordLabel.setBounds(unameLabel.getX(), unameLabel.getY()+30, unameLabel.getWidth(), 20);
		createTeachPane.add(pwordLabel);

		pwordField = new JPasswordField();
		pwordField.setBounds(unameField.getX(), pwordLabel.getY(), fnameField.getWidth(), 20);
		createTeachPane.add(pwordField);
		pwordField.addFocusListener(new FieldsFocusListener(0, 3));

		createBtn = new CustomButton("Create", pwordLabel.getWidth() - createTeachPane.getWidth()/16, createTeachPane.getHeight()/7);
		createBtn.setBounds(createTeachPane.getWidth()/2-(pwordLabel.getWidth() - createTeachPane.getWidth()/16), pwordLabel.getY() + 30, pwordLabel.getWidth() - createTeachPane.getWidth()/16, createTeachPane.getHeight()/7);
		createTeachPane.add(createBtn);
		createBtn.addActionListener(new AdminActionListener(0, 0));

		cancelBtn1 = new CustomButton("Cancel", createBtn.getWidth(), createBtn.getHeight());
		cancelBtn1.setBounds(createBtn.getX() + createBtn.getWidth(), createBtn.getY(), createBtn.getWidth(), createBtn.getHeight());
		createTeachPane.add(cancelBtn1);
		cancelBtn1.addActionListener(new AdminActionListener(0, 1));

		// Delete Teacher Pane Initialization //
		delTeachPane = new GenericPane(getWidth() - leftSidePane.getWidth() - 200, getHeight()/4);
		delTeachPane.setBounds(leftSidePane.getWidth() + 100, getHeight()/3, getWidth() - leftSidePane.getWidth() - 200, getHeight()/4);
		delTeachPane.setVisible(false);	

		errorLabel2 = new CustomLabel("", delTeachPane.getWidth() - (delTeachPane.getWidth()/8), 20, 0);
		errorLabel2.setBounds(delTeachPane.getWidth()/16, delTeachPane.getHeight()/4, delTeachPane.getWidth() - (delTeachPane.getWidth()/8), 20);
		delTeachPane.add(errorLabel2);		

		delUnameLabel = new CustomLabel("Username: ", delTeachPane.getWidth()/2, 20, 1);
		delUnameLabel.setBounds(errorLabel2.getX(), errorLabel2.getY()+30, delTeachPane.getWidth()/2 - delTeachPane.getWidth()/16, 20);
		delTeachPane.add(delUnameLabel);

		delUnameField = new JTextField();
		delUnameField.setBounds(delUnameLabel.getWidth()+delTeachPane.getWidth()/16, delUnameLabel.getY(), (7*delTeachPane.getWidth())/16, 20);
		delTeachPane.add(delUnameField);
		delUnameField.addFocusListener(new FieldsFocusListener(1, 0));
		delUnameField.addActionListener(event -> {adminPwordField.requestFocus();});

		adminPwordLabel = new CustomLabel("Admin Password: ", delUnameLabel.getWidth(), 20, 1);
		adminPwordLabel.setBounds(delUnameLabel.getX(), delUnameLabel.getY() + 30, delUnameLabel.getWidth(), 20);
		delTeachPane.add(adminPwordLabel);

		adminPwordField = new JPasswordField();
		adminPwordField.setBounds(delUnameField.getX(), delUnameField.getY() + 30, delUnameField.getWidth(), 20);
		delTeachPane.add(adminPwordField);
		delUnameField.addFocusListener(new FieldsFocusListener(1, 1));

		deleteBtn = new CustomButton("Confirm", adminPwordLabel.getWidth() - delTeachPane.getWidth()/16, delTeachPane.getHeight()/7);
		deleteBtn.setBounds(delTeachPane.getWidth()/2 - (adminPwordLabel.getWidth() - delTeachPane.getWidth()/16), adminPwordLabel.getY() + 28, adminPwordLabel.getWidth() - delTeachPane.getWidth()/16, delTeachPane.getHeight()/7);
		delTeachPane.add(deleteBtn);
		deleteBtn.addActionListener(new AdminActionListener(1, 0));

		cancelBtn2 = new CustomButton("Cancel", deleteBtn.getWidth(), deleteBtn.getHeight());
		cancelBtn2.setBounds(deleteBtn.getX() + deleteBtn.getWidth(), deleteBtn.getY(), deleteBtn.getWidth(), deleteBtn.getHeight());
		delTeachPane.add(cancelBtn2);
		cancelBtn2.addActionListener(new AdminActionListener(1, 1));			

		// Change Password Pane Initialization //
		changePwordPane = new GenericPane(getWidth() - leftSidePane.getWidth() - 200, getHeight()/3);
		changePwordPane.setBounds(leftSidePane.getWidth() + 100, getHeight()/3-50, getWidth() - leftSidePane.getWidth() - 200, getHeight()/3);
		changePwordPane.setVisible(false);

		errorLabel3 = new CustomLabel("", changePwordPane.getWidth() - (changePwordPane.getWidth()/8), 20, 0);
		errorLabel3.setBounds(changePwordPane.getWidth()/16, changePwordPane.getHeight()/5, changePwordPane.getWidth() - (changePwordPane.getWidth()/8), 20);
		changePwordPane.add(errorLabel3);

		oldPwordLabel = new CustomLabel("Old Password: ", changePwordPane.getWidth()/2- changePwordPane.getWidth()/16, 20, 1);
		oldPwordLabel.setBounds(errorLabel3.getX(), errorLabel3.getY()+30, changePwordPane.getWidth()/2- changePwordPane.getWidth()/16, 20);
		changePwordPane.add(oldPwordLabel);

		oldPwordField = new JPasswordField();
		oldPwordField.setBounds(oldPwordLabel.getWidth()+changePwordPane.getWidth()/16, oldPwordLabel.getY(), (7*changePwordPane.getWidth())/16, 20);
		changePwordPane.add(oldPwordField);
		oldPwordField.addFocusListener(new FieldsFocusListener(2, 0));
		oldPwordField.addActionListener(event -> {newPwordField.requestFocus();});

		newPwordLabel = new CustomLabel("New Password: ", oldPwordLabel.getWidth(), 20, 1);
		newPwordLabel.setBounds(oldPwordLabel.getX(), oldPwordLabel.getY() + 30, oldPwordLabel.getWidth(), 20);
		changePwordPane.add(newPwordLabel);

		newPwordField = new JPasswordField();
		newPwordField.setBounds(oldPwordField.getX(), oldPwordField.getY() + 30, oldPwordField.getWidth(), 20);
		changePwordPane.add(newPwordField);
		newPwordField.addFocusListener(new FieldsFocusListener(2, 1));
		newPwordField.addActionListener(event -> {conPwordField.requestFocus();});

		conPwordLabel = new CustomLabel("Confirm New Password: ", oldPwordLabel.getWidth(), 20, 1);
		conPwordLabel.setBounds(newPwordLabel.getX(), newPwordLabel.getY() + 30, oldPwordLabel.getWidth(), 20);
		changePwordPane.add(conPwordLabel);		

		conPwordField = new JPasswordField();
		conPwordField.setBounds(oldPwordField.getX(), newPwordField.getY() + 30, oldPwordField.getWidth(), 20);
		changePwordPane.add(conPwordField);
		conPwordField.addFocusListener(new FieldsFocusListener(2, 2));

		confirmBtn = new CustomButton("Confirm", conPwordLabel.getWidth() - changePwordPane.getWidth()/16,changePwordPane.getHeight()/7);
		confirmBtn.setBounds(changePwordPane.getWidth()/2 - (conPwordLabel.getWidth() - changePwordPane.getWidth()/16), conPwordLabel.getY() + 30, conPwordLabel.getWidth() - changePwordPane.getWidth()/16, changePwordPane.getHeight()/7);
		changePwordPane.add(confirmBtn);
		confirmBtn.addActionListener(new AdminActionListener(2, 1));

		cancelBtn3 = new CustomButton("Cancel", confirmBtn.getWidth(), confirmBtn.getHeight());
		cancelBtn3.setBounds(confirmBtn.getX() + confirmBtn.getWidth(), confirmBtn.getY(), confirmBtn.getWidth(), confirmBtn.getHeight());
		changePwordPane.add(cancelBtn3);
		cancelBtn3.addActionListener(new AdminActionListener(2, 2));

		add(leftSidePane);
		add(createTeachPane);
		add(delTeachPane);
		add(changePwordPane);
	}

	public void resize(){
		// Resize Left Side Pane //
		leftSidePane.setBounds(0, 0, getWidth()/4 + 60, getHeight());
		((LeftSidePane)leftSidePane).resize();

		// Resize CreateTeach Pane //
		createTeachPane.setBounds(leftSidePane.getWidth() + 100, getHeight()/4, getWidth() - leftSidePane.getWidth() - 200, getHeight()/3+10);
		((GenericPane)createTeachPane).resize();

		errorLabel1.setBounds(createTeachPane.getWidth()/16, createTeachPane.getHeight()/4 - 25, createTeachPane.getWidth() - (createTeachPane.getWidth()/8), 20);
		((CustomLabel)errorLabel1).resize();

		fnameLabel.setBounds(createTeachPane.getWidth()/16, errorLabel1.getY() + 30, createTeachPane.getWidth()/2 - createTeachPane.getWidth()/16, 20);
		((CustomLabel)fnameLabel).resize();

		fnameField.setBounds(fnameLabel.getWidth()+createTeachPane.getWidth()/16, fnameLabel.getY(), (7*createTeachPane.getWidth())/16, 20);
		
		lnameLabel.setBounds(fnameLabel.getX(), fnameLabel.getY()+30, fnameLabel.getWidth(), 20);
		((CustomLabel)lnameLabel).resize();

		lnameField.setBounds(fnameField.getX(), lnameLabel.getY(), fnameField.getWidth(), 20);

		unameLabel.setBounds(lnameLabel.getX(), lnameLabel.getY()+30, lnameLabel.getWidth(), 20);
		((CustomLabel)unameLabel).resize();

		unameField.setBounds(lnameField.getX(), unameLabel.getY(), fnameField.getWidth(), 20);
		
		pwordLabel.setBounds(unameLabel.getX(), unameLabel.getY()+30, unameLabel.getWidth(), 20);
		((CustomLabel)pwordLabel).resize();

		pwordField.setBounds(unameField.getX(), pwordLabel.getY(), fnameField.getWidth(), 20);
		
		createBtn.setBounds(createTeachPane.getWidth()/2-(pwordLabel.getWidth() - createTeachPane.getWidth()/16), pwordLabel.getY() + 30, pwordLabel.getWidth() - createTeachPane.getWidth()/16, createTeachPane.getHeight()/7);
		((CustomButton)createBtn).resize();

		cancelBtn1.setBounds(createBtn.getX() + createBtn.getWidth(), createBtn.getY(), createBtn.getWidth(), createBtn.getHeight());
		((CustomButton)cancelBtn1).resize();
		
		// Resize DelTeach Pane //
		delTeachPane.setBounds(leftSidePane.getWidth() + 100, getHeight()/3, getWidth() - leftSidePane.getWidth() - 200, getHeight()/4);
		((GenericPane)delTeachPane).resize();

		errorLabel2.setBounds(delTeachPane.getWidth()/16, delTeachPane.getHeight()/4, delTeachPane.getWidth() - (delTeachPane.getWidth()/8), 20);
		((CustomLabel)errorLabel2).resize();

		delUnameLabel.setBounds(errorLabel2.getX(), errorLabel2.getY()+30, delTeachPane.getWidth()/2 - delTeachPane.getWidth()/16, 20);
		((CustomLabel)delUnameLabel).resize();

		delUnameField.setBounds(delUnameLabel.getWidth()+delTeachPane.getWidth()/16, delUnameLabel.getY(), (7*delTeachPane.getWidth())/16, 20);
		
		adminPwordLabel.setBounds(delUnameLabel.getX(), delUnameLabel.getY() + 30, delUnameLabel.getWidth(), 20);
		((CustomLabel)adminPwordLabel).resize();

		adminPwordField.setBounds(delUnameField.getX(), delUnameField.getY() + 30, delUnameField.getWidth(), 20);
		
		deleteBtn.setBounds(delTeachPane.getWidth()/2 - (adminPwordLabel.getWidth() - delTeachPane.getWidth()/16), adminPwordLabel.getY() + 28, adminPwordLabel.getWidth() - delTeachPane.getWidth()/16, delTeachPane.getHeight()/7);
		((CustomButton)deleteBtn).resize();

		cancelBtn2.setBounds(deleteBtn.getX() + deleteBtn.getWidth(), deleteBtn.getY(), deleteBtn.getWidth(), deleteBtn.getHeight());
		((CustomButton)cancelBtn2).resize();

		// Resize Change Password Pane //
		changePwordPane.setBounds(leftSidePane.getWidth() + 100, getHeight()/3-50, getWidth() - leftSidePane.getWidth() - 200, getHeight()/3);
		((GenericPane)changePwordPane).resize();

		errorLabel3.setBounds(changePwordPane.getWidth()/16, changePwordPane.getHeight()/5, changePwordPane.getWidth() - (changePwordPane.getWidth()/8), 20);
		((CustomLabel)errorLabel3).resize();

		oldPwordLabel.setBounds(errorLabel3.getX(), errorLabel3.getY()+30, changePwordPane.getWidth()/2- changePwordPane.getWidth()/16, 20);
		((CustomLabel)oldPwordLabel).resize();

		oldPwordField.setBounds(oldPwordLabel.getWidth()+changePwordPane.getWidth()/16, oldPwordLabel.getY(), (7*changePwordPane.getWidth())/16, 20);

		newPwordLabel.setBounds(oldPwordLabel.getX(), oldPwordLabel.getY() + 30, oldPwordLabel.getWidth(), 20);
		((CustomLabel)newPwordLabel).resize();

		newPwordField.setBounds(oldPwordField.getX(), oldPwordField.getY() + 30, oldPwordField.getWidth(), 20);

		conPwordLabel.setBounds(newPwordLabel.getX(), newPwordLabel.getY() + 30, oldPwordLabel.getWidth(), 20);
		((CustomLabel)conPwordLabel).resize();

		conPwordField.setBounds(oldPwordField.getX(), newPwordField.getY() + 30, oldPwordField.getWidth(), 20);

		confirmBtn.setBounds(changePwordPane.getWidth()/2 - (conPwordLabel.getWidth() - changePwordPane.getWidth()/16), conPwordLabel.getY() + 30, conPwordLabel.getWidth() - changePwordPane.getWidth()/16, changePwordPane.getHeight()/7);
		((CustomButton)confirmBtn).resize();

		cancelBtn3.setBounds(confirmBtn.getX() + confirmBtn.getWidth(), confirmBtn.getY(), confirmBtn.getWidth(), confirmBtn.getHeight());
		((CustomButton)cancelBtn3).resize();
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
			else if(this.state == 1){
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
			else if(this.state == 2){
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