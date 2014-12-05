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

public class TeacherPanel extends JPanel{

	// Left Side Pane Layout //
	JPanel leftSidePane;
	JPanel buttonPane;

	JButton createAcctBtn;
	JButton deleteAcctBtn;
	JButton manageSubjects;
	JButton viewStats;
	JButton acctSettings;
	JButton logoutBtn;

	// Create Student Pane Shit //
	JPanel createStudentPane;

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
	JPanel delStudentPane;

	JLabel errorLabel2;
	JLabel delUnameLabel;
	JLabel teachPwordLabel;
	JTextField delUnameField;
	JPasswordField teachPwordField;
	JButton deleteBtn;
	JButton cancelBtn2;

	// Delete Teacher Values //
	String delUname = "";
	String delPword = "";	


	public TeacherPanel(){
		setLayout(null);

		setPreferredSize(new Dimension(ANNdroid.SCREEN_WIDTH, ANNdroid.SCREEN_HEIGHT));
		setBounds(0, 0, ANNdroid.SCREEN_WIDTH, ANNdroid.SCREEN_HEIGHT);

		// Left Side Pane Layout Initialization //
		String labels[] = {"Create Student Account", "Delete Student Account", "Manage Subjects", "Statistics", "Account Settings", "Logout"};

		leftSidePane = new LeftSidePane(this, 1, 6, labels);
		leftSidePane.setPreferredSize(new Dimension(getWidth()/4 + 60, getHeight()));
		leftSidePane.setBounds(0, 0, getWidth()/4 + 60, getHeight());
		leftSidePane.setBackground(Color.BLACK);		

		// Create Student Pane //
		createStudentPane = new JPanel(null);
		createStudentPane.setPreferredSize(new Dimension(getWidth() - leftSidePane.getWidth() - 200, getHeight()-400));
		createStudentPane.setBounds(leftSidePane.getWidth() + 100, getHeight()/3, getWidth() - leftSidePane.getWidth() - 200, getHeight()-400);
		createStudentPane.setVisible(false);

		fnameLabel = new JLabel("First Name: ", SwingConstants.RIGHT);
		fnameLabel.setBounds(0, createStudentPane.getHeight()/4, createStudentPane.getWidth()/2, 20);
		fnameLabel.setForeground(Color.WHITE);
		fnameLabel.setBackground(Color.BLACK);
		fnameLabel.setOpaque(true);
		createStudentPane.add(fnameLabel);

		fnameField = new JTextField();
		fnameField.setBounds(fnameLabel.getWidth(), fnameLabel.getY(), (7*createStudentPane.getWidth())/16, 20);
		createStudentPane.add(fnameField);
		fnameField.addFocusListener(new FieldsFocusListener(0, 0));
		fnameField.addActionListener(event -> {lnameField.requestFocus();});

		lnameLabel = new JLabel("Last Name: ", SwingConstants.RIGHT);
		lnameLabel.setBounds(fnameLabel.getX(), fnameLabel.getY() + 30, fnameLabel.getWidth(), 20);
		lnameLabel.setForeground(Color.WHITE);
		lnameLabel.setBackground(Color.BLACK);
		lnameLabel.setOpaque(true);
		createStudentPane.add(lnameLabel);

		lnameField = new JTextField();
		lnameField.setBounds(lnameLabel.getWidth(), lnameLabel.getY(), fnameField.getWidth(), 20);
		createStudentPane.add(lnameField);
		lnameField.addFocusListener(new FieldsFocusListener(0, 1));
		lnameField.addActionListener(event -> {unameField.requestFocus();});

		unameLabel = new JLabel("Username: ", SwingConstants.RIGHT);
		unameLabel.setBounds(fnameLabel.getX(), lnameLabel.getY() + 30, fnameLabel.getWidth(), 20);
		unameLabel.setForeground(Color.WHITE);
		unameLabel.setBackground(Color.BLACK);
		unameLabel.setOpaque(true);
		createStudentPane.add(unameLabel);

		unameField = new JTextField();
		unameField.setBounds(unameLabel.getWidth(), unameLabel.getY(), fnameField.getWidth(), 20);
		createStudentPane.add(unameField);
		unameField.addFocusListener(new FieldsFocusListener(0, 2));
		unameField.addActionListener(event -> {pwordField.requestFocus();});

		pwordLabel = new JLabel("Password: ", SwingConstants.RIGHT);
		pwordLabel.setBounds(fnameLabel.getX(), unameField.getY() + 30, fnameLabel.getWidth(), 20);
		pwordLabel.setForeground(Color.WHITE);
		pwordLabel.setBackground(Color.BLACK);
		pwordLabel.setOpaque(true);
		createStudentPane.add(pwordLabel);

		pwordField = new JPasswordField();
		pwordField.setBounds(fnameLabel.getWidth(), pwordLabel.getY(), fnameField.getWidth(), 20);
		createStudentPane.add(pwordField);
		pwordField.addFocusListener(new FieldsFocusListener(0, 3));

		createBtn = new JButton("Create");
		createBtn.setBounds(createStudentPane.getWidth()/16, pwordLabel.getY() + 30, pwordLabel.getWidth() - createStudentPane.getWidth()/16, createStudentPane.getHeight()/7);
		createStudentPane.add(createBtn);
		createBtn.addActionListener(new TeacherActionListener(0, 0));

		cancelBtn1 = new JButton("Cancel");
		cancelBtn1.setBounds(createBtn.getX() + createBtn.getWidth(), createBtn.getY(), createBtn.getWidth(), createBtn.getHeight());
		createStudentPane.add(cancelBtn1);
		cancelBtn1.addActionListener(new TeacherActionListener(0, 1));

		errorLabel1 = new JLabel("");
		errorLabel1.setBounds(createStudentPane.getWidth()/16, createStudentPane.getHeight()/4 - 25, createStudentPane.getWidth() - (createStudentPane.getWidth()/8), 20);
		errorLabel1.setForeground(Color.RED);
		errorLabel1.setBackground(Color.BLACK);
		errorLabel1.setOpaque(true);
		createStudentPane.add(errorLabel1);

		// Delete Teacher Pane Initialization //
		delStudentPane = new JPanel(null);
		delStudentPane.setPreferredSize(new Dimension(getWidth() - leftSidePane.getWidth() - 200, getHeight() - 470));
		delStudentPane.setBounds(leftSidePane.getWidth() + 100, getHeight()/3, getWidth() - leftSidePane.getWidth() - 200, getHeight() - 470);
		delStudentPane.setVisible(false);

		delUnameLabel = new JLabel("username: ", SwingConstants.RIGHT);
		delUnameLabel.setBounds(0, delStudentPane.getHeight()/3, delStudentPane.getWidth()/2, 20);
		delUnameLabel.setForeground(Color.WHITE);
		delUnameLabel.setBackground(Color.BLACK);
		delUnameLabel.setOpaque(true);
		delStudentPane.add(delUnameLabel);

		delUnameField = new JTextField();
		delUnameField.setBounds(delUnameLabel.getWidth(), delUnameLabel.getY(), (7*delStudentPane.getWidth())/16, 20);
		delStudentPane.add(delUnameField);
		delUnameField.addFocusListener(new FieldsFocusListener(1, 0));
		delUnameField.addActionListener(event -> {teachPwordField.requestFocus();});

		teachPwordLabel = new JLabel("teacher password: ", SwingConstants.RIGHT);
		teachPwordLabel.setBounds(delUnameLabel.getX(), delUnameLabel.getY() + 30, delUnameLabel.getWidth(), 20);
		teachPwordLabel.setForeground(Color.WHITE);
		teachPwordLabel.setBackground(Color.BLACK);
		teachPwordLabel.setOpaque(true);		
		delStudentPane.add(teachPwordLabel);

		teachPwordField = new JPasswordField();
		teachPwordField.setBounds(delUnameField.getX(), delUnameField.getY() + 30, delUnameField.getWidth(), 20);
		delStudentPane.add(teachPwordField);
		delUnameField.addFocusListener(new FieldsFocusListener(1, 1));

		deleteBtn = new JButton("Confirm");
		deleteBtn.setBounds(delStudentPane.getWidth()/16, teachPwordLabel.getY() + 30, teachPwordLabel.getWidth() - delStudentPane.getWidth()/16, delStudentPane.getHeight()/7);
		delStudentPane.add(deleteBtn);
		deleteBtn.addActionListener(new TeacherActionListener(1, 0));

		cancelBtn2 = new JButton("Cancel");
		cancelBtn2.setBounds(deleteBtn.getX() + deleteBtn.getWidth(), deleteBtn.getY(), deleteBtn.getWidth(), deleteBtn.getHeight());
		delStudentPane.add(cancelBtn2);
		cancelBtn2.addActionListener(new TeacherActionListener(1, 1));		

		errorLabel2 = new JLabel("");
		errorLabel2.setBounds(delStudentPane.getWidth()/16, delStudentPane.getHeight()/3 - 25, deleteBtn.getWidth() + cancelBtn2.getWidth(), 20);
		errorLabel2.setForeground(Color.RED);
		errorLabel2.setBackground(Color.BLACK);
		errorLabel2.setOpaque(true);
		delStudentPane.add(errorLabel2);		

		add(leftSidePane);
		add(createStudentPane);
		add(delStudentPane);
	}

	public void resize(){
		// Resize Left Side Pane //
		leftSidePane.setBounds(0, 0, getWidth()/4 + 60, getHeight());
		((LeftSidePane)leftSidePane).resize();

		// Resizing Create Account //
		createStudentPane.setBounds(leftSidePane.getWidth() + 100, getHeight()/4, getWidth() - leftSidePane.getWidth() - 200, createStudentPane.getHeight());
		fnameLabel.setBounds(0, createStudentPane.getHeight()/4, createStudentPane.getWidth()/2, 20);
		lnameLabel.setBounds(fnameLabel.getX(), fnameLabel.getY() + 30, fnameLabel.getWidth(), 20);
		unameLabel.setBounds(fnameLabel.getX(), lnameLabel.getY() + 30, fnameLabel.getWidth(), 20);
		pwordLabel.setBounds(fnameLabel.getX(), unameField.getY() + 30, fnameLabel.getWidth(), 20);
		fnameField.setBounds(fnameLabel.getWidth(), fnameLabel.getY(), (7*createStudentPane.getWidth())/16, 20);
		lnameField.setBounds(lnameLabel.getWidth(), lnameLabel.getY(), fnameField.getWidth(), 20);
		unameField.setBounds(unameLabel.getWidth(), unameLabel.getY(), fnameField.getWidth(), 20);
		pwordField.setBounds(pwordLabel.getWidth(), pwordLabel.getY(), fnameField.getWidth(), 20);
		createBtn.setBounds(createStudentPane.getWidth()/16, pwordLabel.getY() + 30, pwordLabel.getWidth() - createStudentPane.getWidth()/16, createStudentPane.getHeight()/7);
		cancelBtn1.setBounds(createBtn.getX() + createBtn.getWidth(), createBtn.getY(), createBtn.getWidth(), createBtn.getHeight());
		errorLabel1.setBounds(createStudentPane.getWidth()/16, createStudentPane.getHeight()/4 - 25, createStudentPane.getWidth() - (createStudentPane.getWidth()/8), 20);

		// Resizing Delete Account //
		delStudentPane.setBounds(leftSidePane.getWidth() + 100, getHeight()/3, getWidth() - leftSidePane.getWidth() - 200, getHeight() - 470);
		delUnameLabel.setBounds(0, delStudentPane.getHeight()/3, delStudentPane.getWidth()/2, 20);
		delUnameField.setBounds(delUnameLabel.getWidth(), delUnameLabel.getY(), (7*delStudentPane.getWidth())/16, 20);
		teachPwordLabel.setBounds(delUnameLabel.getX(), delUnameLabel.getY() + 30, delUnameLabel.getWidth(), 20);
		teachPwordField.setBounds(delUnameField.getX(), delUnameField.getY() + 30, delUnameField.getWidth(), 20);
		deleteBtn.setBounds(delStudentPane.getWidth()/16, teachPwordLabel.getY() + 30, teachPwordLabel.getWidth() - delStudentPane.getWidth()/16, delStudentPane.getHeight()/7);
		cancelBtn2.setBounds(deleteBtn.getX() + deleteBtn.getWidth(), deleteBtn.getY(), deleteBtn.getWidth(), deleteBtn.getHeight());
		errorLabel2.setBounds(delStudentPane.getWidth()/16, delStudentPane.getHeight()/3 - 25, deleteBtn.getWidth() + cancelBtn2.getWidth(), 20);		
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
			teachPwordField.setText("");
		}
	}

	private class FieldsFocusListener extends FocusAdapter{
		// state: 0 - Create Student Account //
			// mode: 0 - fname //
			// mode: 1 - lname //
			// mode: 2 - uname //
			// mode: 3 - pword //
		// state: 1 - Delete Student Account //
			// mode: 0 - delUname //
			// mode: 1 - delPword //

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
				else if(this.mode == 1) delPword = String.valueOf(teachPwordField.getPassword());
			}
		}

	}

	private class TeacherActionListener implements ActionListener{

		// state: 0 - Create Student Pane//
			// mode: 0 - create btn//
			// mode: 1 - cancel btn//
		// state: 1 - Delete Student Pane //

		int state;
		int mode;

		public TeacherActionListener(int state, int mode){
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
						errorLabel1.setText("* successfully created student account");
						errorLabel1.setForeground(Color.YELLOW);
						((Teacher)ANNdroid.simulator.active).createAcct(new Student(uname, Utilities.hashPassword(pword), fname, lname));
					}
					reinitialize(true, false, 0);
				}
				else{
					reinitialize(false, true, 0);
					createStudentPane.setVisible(false);
				}
			}
			else if(this.state == 1){
				if(this.mode == 1){
					reinitialize(false, true, 1);
					delStudentPane.setVisible(false);
				}
				else{
					delPword = String.valueOf(teachPwordField.getPassword());
					if(!ANNdroid.simulator.active.getPassword().equals(Utilities.hashPassword(delPword))){
						errorLabel2.setText("* incorrect password for teacher");
						errorLabel2.setForeground(Color.RED);
					}
					else if(Utilities.exists(delUname) == -1){
						errorLabel2.setText("* username does not exist");
						errorLabel2.setForeground(Color.RED);
					}
					else if(Utilities.exists(delUname) != -1){
						errorLabel2.setText("* successfully deleted student account");
						errorLabel2.setForeground(Color.YELLOW);
						((Teacher)ANNdroid.simulator.active).delAcct(Utilities.exists(delUname));
					}
					reinitialize(true, false, 1);
				}
			}	
		}
	}
}