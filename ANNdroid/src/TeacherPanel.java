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

	// Manage Subjects //
	JPanel manageSubjectsButtonPane;
	JLabel errorLabel3;
	JButton addQuestion;
	JButton viewQuestions;
	JButton deleteQuestion;

	public TeacherPanel(){
		setLayout(null);

		setPreferredSize(new Dimension(ANNdroid.SCREEN_WIDTH, ANNdroid.SCREEN_HEIGHT));
		setBounds(0, 0, ANNdroid.SCREEN_WIDTH, ANNdroid.SCREEN_HEIGHT);

		// Left Side Pane Layout Initialization //
		String labels[] = {"Create Student Account", "Delete Student Account", "View Students", "Manage Subjects", "Statistics", "Edit Profile", "Logout"};

		leftSidePane = new LeftSidePane(this, 1, 7, labels);
		leftSidePane.setPreferredSize(new Dimension(getWidth()/4 + 60, getHeight()));
		leftSidePane.setBounds(0, 0, getWidth()/4 + 60, getHeight());
		leftSidePane.setBackground(Color.BLACK);		

		// Create Student Pane //
		createStudentPane = new GenericPane(getWidth() - leftSidePane.getWidth() - 200, getHeight()/3+10);
		createStudentPane.setBounds(leftSidePane.getWidth() + 100, getHeight()/4, getWidth() - leftSidePane.getWidth() - 200, getHeight()/3+10);
		createStudentPane.setVisible(false);

		errorLabel1 = new CustomLabel("", createStudentPane.getWidth() - (createStudentPane.getWidth()/8), 20, 0);
		errorLabel1.setBounds(createStudentPane.getWidth()/16, createStudentPane.getHeight()/4-15, createStudentPane.getWidth() - (createStudentPane.getWidth()/8), 20);
		createStudentPane.add(errorLabel1);

		fnameLabel = new CustomLabel("First Name: ", createStudentPane.getWidth()/2 - createStudentPane.getWidth()/16, 20, 1);
		fnameLabel.setBounds(createStudentPane.getWidth()/16, errorLabel1.getY() + 30, createStudentPane.getWidth()/2 - createStudentPane.getWidth()/16, 20);
		createStudentPane.add(fnameLabel);		

		fnameField = new JTextField();
		fnameField.setBounds(fnameLabel.getWidth()+createStudentPane.getWidth()/16, fnameLabel.getY(), (7*createStudentPane.getWidth())/16, 20);
		createStudentPane.add(fnameField);
		fnameField.addFocusListener(new FieldsFocusListener(0, 0));
		fnameField.addActionListener(event -> {lnameField.requestFocus();});

		lnameLabel = new CustomLabel("Last Name: ", fnameLabel.getWidth(), 20, 1);
		lnameLabel.setBounds(fnameLabel.getX(), fnameLabel.getY()+30, fnameLabel.getWidth(), 20);
		createStudentPane.add(lnameLabel);		

		lnameField = new JTextField();
		lnameField.setBounds(fnameField.getX(), fnameField.getY()+30, fnameField.getWidth(), 20);
		createStudentPane.add(lnameField);
		lnameField.addFocusListener(new FieldsFocusListener(0, 1));
		lnameField.addActionListener(event -> {unameField.requestFocus();});

		unameLabel = new CustomLabel("Username: ", lnameLabel.getWidth(), 20, 1);
		unameLabel.setBounds(lnameLabel.getX(), lnameLabel.getY()+30, lnameLabel.getWidth(), 20);
		createStudentPane.add(unameLabel);

		unameField = new JTextField();
		unameField.setBounds(lnameField.getX(), lnameField.getY()+30, lnameField.getWidth(), 20);
		createStudentPane.add(unameField);
		unameField.addFocusListener(new FieldsFocusListener(0, 2));
		unameField.addActionListener(event -> {pwordField.requestFocus();});

		pwordLabel = new CustomLabel("Password: ", unameLabel.getWidth(), 20, 1);
		pwordLabel.setBounds(unameLabel.getX(), unameLabel.getY()+30, unameLabel.getWidth(), 20);
		createStudentPane.add(pwordLabel);

		pwordField = new JPasswordField();
		pwordField.setBounds(unameField.getX(), unameField.getY()+30, unameField.getWidth(), 20);
		createStudentPane.add(pwordField);
		pwordField.addFocusListener(new FieldsFocusListener(0, 3));

		createBtn = new CustomButton("Create", pwordLabel.getWidth() - createStudentPane.getWidth()/16, createStudentPane.getHeight()/7);
		createBtn.setBounds(createStudentPane.getWidth()/2-(pwordLabel.getWidth() - createStudentPane.getWidth()/16), pwordLabel.getY() + 30, pwordLabel.getWidth() - createStudentPane.getWidth()/16, createStudentPane.getHeight()/7);
		createStudentPane.add(createBtn);
		createBtn.addActionListener(new TeacherActionListener(0, 0));

		cancelBtn1 = new CustomButton("Cancel", createBtn.getWidth(), createBtn.getHeight());
		cancelBtn1.setBounds(createBtn.getX() + createBtn.getWidth(), createBtn.getY(), createBtn.getWidth(), createBtn.getHeight());
		createStudentPane.add(cancelBtn1);
		cancelBtn1.addActionListener(new TeacherActionListener(0, 1));

		// Delete Teacher Pane Initialization //
		delStudentPane = new GenericPane(getWidth() - leftSidePane.getWidth() - 200, getHeight()/4);
		delStudentPane.setBounds(leftSidePane.getWidth() + 100, getHeight()/3, getWidth() - leftSidePane.getWidth() - 200, getHeight()/4);
		delStudentPane.setVisible(false);	

		errorLabel2 = new CustomLabel("", delStudentPane.getWidth() - (delStudentPane.getWidth()/8), 20, 0);
		errorLabel2.setBounds(delStudentPane.getWidth()/16, delStudentPane.getHeight()/4-15, delStudentPane.getWidth() - (delStudentPane.getWidth()/8), 20);
		delStudentPane.add(errorLabel2);	

		delUnameLabel = new CustomLabel("Username: ", delStudentPane.getWidth()/2, 20, 1);
		delUnameLabel.setBounds(errorLabel2.getX(), errorLabel2.getY()+30, delStudentPane.getWidth()/2 - delStudentPane.getWidth()/16, 20);
		delStudentPane.add(delUnameLabel);

		delUnameField = new JTextField();
		delUnameField.setBounds(delUnameLabel.getWidth()+delStudentPane.getWidth()/16, delUnameLabel.getY(), (7*delStudentPane.getWidth())/16, 20);
		delStudentPane.add(delUnameField);
		delUnameField.addFocusListener(new FieldsFocusListener(1, 0));
		delUnameField.addActionListener(event -> {teachPwordField.requestFocus();});

		teachPwordLabel = new CustomLabel("Teacher Password: ", delUnameLabel.getWidth(), 20, 1);
		teachPwordLabel.setBounds(delUnameLabel.getX(), delUnameLabel.getY() + 30, delUnameLabel.getWidth(), 20);
		delStudentPane.add(teachPwordLabel);

		teachPwordField = new JPasswordField();
		teachPwordField.setBounds(delUnameField.getX(), delUnameField.getY() + 30, delUnameField.getWidth(), 20);
		delStudentPane.add(teachPwordField);
		delStudentPane.add(teachPwordField);
		delUnameField.addFocusListener(new FieldsFocusListener(1, 1));

		deleteBtn = new CustomButton("Confirm", teachPwordLabel.getWidth() - delStudentPane.getWidth()/16, delStudentPane.getHeight()/7);
		deleteBtn.setBounds(delStudentPane.getWidth()/2 - (teachPwordLabel.getWidth() - delStudentPane.getWidth()/16), teachPwordLabel.getY() + 28, teachPwordLabel.getWidth() - delStudentPane.getWidth()/16, delStudentPane.getHeight()/7);
		delStudentPane.add(deleteBtn);
		deleteBtn.addActionListener(new TeacherActionListener(1, 0));

		cancelBtn2 = new CustomButton("Cancel", deleteBtn.getWidth(), deleteBtn.getHeight());
		cancelBtn2.setBounds(deleteBtn.getX() + deleteBtn.getWidth(), deleteBtn.getY(), deleteBtn.getWidth(), deleteBtn.getHeight());
		delStudentPane.add(cancelBtn2);
		cancelBtn2.addActionListener(new TeacherActionListener(1, 1));	

		// Manage Subjects Pane //
		manageSubjectsButtonPane = new GenericPane(getWidth()-leftSidePane.getWidth(), getHeight() - 180);
		manageSubjectsButtonPane.setBounds(leftSidePane.getWidth(), getHeight()/8, getWidth()-leftSidePane.getWidth()-30, getHeight()/10);
		manageSubjectsButtonPane.setVisible(false);

		addQuestion = new CustomButton("Add Question", manageSubjectsButtonPane.getWidth()/3-20, manageSubjectsButtonPane.getHeight()/2);
		addQuestion.setBounds(manageSubjectsButtonPane.getWidth()/14, 20, manageSubjectsButtonPane.getWidth()/3-25, manageSubjectsButtonPane.getHeight()/2);
		manageSubjectsButtonPane.add(addQuestion);

		viewQuestions = new CustomButton("View Questions", addQuestion.getWidth(), addQuestion.getHeight());
		viewQuestions.setBounds(addQuestion.getX() + addQuestion.getWidth(), addQuestion.getY(), addQuestion.getWidth(), addQuestion.getHeight());
		manageSubjectsButtonPane.add(viewQuestions);

		deleteQuestion = new CustomButton("Delete Question", addQuestion.getWidth(), addQuestion.getHeight());
		deleteQuestion.setBounds(viewQuestions.getX() + viewQuestions.getWidth(), viewQuestions.getY(), viewQuestions.getWidth(), viewQuestions.getHeight());
		manageSubjectsButtonPane.add(deleteQuestion);

		add(leftSidePane);
		add(createStudentPane);
		add(delStudentPane);
		add(manageSubjectsButtonPane);
	}

	public void resize(){
		// Resize Left Side Pane //
		leftSidePane.setBounds(0, 0, getWidth()/4 + 60, getHeight());
		((LeftSidePane)leftSidePane).resize();

		// Resizing Create Account //
		createStudentPane.setBounds(leftSidePane.getWidth() + 100, getHeight()/4, getWidth() - leftSidePane.getWidth() - 200, getHeight()/3+30);
		((GenericPane)createStudentPane).resize();

		errorLabel1.setBounds(createStudentPane.getWidth()/16, createStudentPane.getHeight()/4-15, createStudentPane.getWidth() - (createStudentPane.getWidth()/8), 20);
		((CustomLabel)errorLabel1).resize();

		fnameLabel.setBounds(createStudentPane.getWidth()/16, errorLabel1.getY() + 30, createStudentPane.getWidth()/2 - createStudentPane.getWidth()/16, 20);
		((CustomLabel)fnameLabel).resize();

		fnameField.setBounds(fnameLabel.getWidth()+createStudentPane.getWidth()/16, fnameLabel.getY(), (7*createStudentPane.getWidth())/16, 20);

		lnameLabel.setBounds(fnameLabel.getX(), fnameLabel.getY()+30, fnameLabel.getWidth(), 20);
		((CustomLabel)lnameLabel).resize();

		lnameField.setBounds(fnameField.getX(), fnameField.getY()+30, fnameField.getWidth(), 20);

		unameLabel.setBounds(lnameLabel.getX(), lnameLabel.getY()+30, lnameLabel.getWidth(), 20);
		((CustomLabel)unameLabel).resize();

		unameField.setBounds(lnameField.getX(), lnameField.getY()+30, lnameField.getWidth(), 20);

		pwordLabel.setBounds(unameLabel.getX(), unameLabel.getY()+30, unameLabel.getWidth(), 20);
		((CustomLabel)pwordLabel).resize();

		pwordField.setBounds(unameField.getX(), unameField.getY()+30, unameField.getWidth(), 20);

		createBtn.setBounds(createStudentPane.getWidth()/2-(pwordLabel.getWidth() - createStudentPane.getWidth()/16), pwordLabel.getY() + 30, pwordLabel.getWidth() - createStudentPane.getWidth()/16, createStudentPane.getHeight()/7);
		((CustomButton)createBtn).resize();

		cancelBtn1.setBounds(createBtn.getX() + createBtn.getWidth(), createBtn.getY(), createBtn.getWidth(), createBtn.getHeight());
		((CustomButton)cancelBtn1).resize();

		// Resizing Delete Account //
		delStudentPane.setBounds(leftSidePane.getWidth() + 100, getHeight()/3, getWidth() - leftSidePane.getWidth() - 200, getHeight()/4);
		((GenericPane)delStudentPane).resize();

		errorLabel2.setBounds(delStudentPane.getWidth()/16, delStudentPane.getHeight()/4-15, delStudentPane.getWidth() - (delStudentPane.getWidth()/8), 20);
		((CustomLabel)errorLabel2).resize();

		delUnameLabel.setBounds(errorLabel2.getX(), errorLabel2.getY()+30, delStudentPane.getWidth()/2 - delStudentPane.getWidth()/16, 20);
		((CustomLabel)delUnameLabel).resize();

		delUnameField.setBounds(delUnameLabel.getWidth()+delStudentPane.getWidth()/16, delUnameLabel.getY(), (7*delStudentPane.getWidth())/16, 20);
		
		teachPwordLabel.setBounds(delUnameLabel.getX(), delUnameLabel.getY() + 30, delUnameLabel.getWidth(), 20);
		((CustomLabel)teachPwordLabel).resize();

		teachPwordField.setBounds(delUnameField.getX(), delUnameField.getY() + 30, delUnameField.getWidth(), 20);
		
		deleteBtn.setBounds(delStudentPane.getWidth()/2 - (teachPwordLabel.getWidth() - delStudentPane.getWidth()/16), teachPwordLabel.getY() + 28, teachPwordLabel.getWidth() - delStudentPane.getWidth()/16, delStudentPane.getHeight()/7);
		((CustomButton)deleteBtn).resize();

		cancelBtn2.setBounds(deleteBtn.getX() + deleteBtn.getWidth(), deleteBtn.getY(), deleteBtn.getWidth(), deleteBtn.getHeight());
		((CustomButton)cancelBtn2).resize();
		
		// Manage Subjects //
		manageSubjectsButtonPane.setBounds(leftSidePane.getWidth(), getHeight()/8, getWidth()-leftSidePane.getWidth()-30, getHeight()/10);
		((GenericPane)manageSubjectsButtonPane).resize();

		addQuestion.setBounds(manageSubjectsButtonPane.getWidth()/14, 20, manageSubjectsButtonPane.getWidth()/3-24, manageSubjectsButtonPane.getHeight()/2);
		((CustomButton)addQuestion).resize();

		viewQuestions.setBounds(addQuestion.getX() + addQuestion.getWidth(), addQuestion.getY(), addQuestion.getWidth(), addQuestion.getHeight());
		((CustomButton)viewQuestions).resize();		

		deleteQuestion.setBounds(viewQuestions.getX() + viewQuestions.getWidth(), viewQuestions.getY(), viewQuestions.getWidth(), viewQuestions.getHeight());
		((CustomButton)deleteQuestion).resize();		
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
		else if(state == 2){
			//if(changeMode || !error) errorLabel3.setText("");
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