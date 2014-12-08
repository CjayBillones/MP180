package ANNdroid.src.panels;

import ANNdroid.src.custom_swing.*;
import ANNdroid.src.objects.*;
import ANNdroid.src.util.*;
import ANNdroid.src.*;

import java.util.LinkedList;

import javax.swing.*;
import javax.swing.text.DefaultCaret;

import java.awt.*;
import java.awt.event.*;

public class TeacherPanel extends JPanel{

	public static String[] CHOICES = {"A", "B", "C", "D"};
	public static String[] DIFFICULTY = {"EASY", "INTERMEDIATE", "HARD"};

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
	JButton addQuestion;
	JButton viewQuestions;
	JButton deleteQuestion;

	JPanel manageSubjectPane;
	JLabel errorLabel3;

	JPanel addQuestionPane;

	JLabel subjectLabel;
	JTextField subjectField;

	JLabel qLabel;
	public JScrollPane jp;
	JTextArea qField;

	JLabel fchoiceLabel;
	JLabel schoiceLabel;
	JLabel tchoiceLabel;
	JLabel fthchoiceLabel;

	JTextField fchoiceField;
	JTextField schoiceField;
	JTextField tchoiceField;
	JTextField fthchoiceField;

	JLabel diffLabel;
	JComboBox<Object> diffField;
	JLabel answerLabel;
	JComboBox<Object> answerField;

	int selectedChoice = 0;
	String selectedDifficulty = "EASY";

	JButton addBtn;
	JButton cancelBtn3;

	public TeacherPanel(){

		setLayout(null);
		setPreferredSize(new Dimension(ANNdroid.SCREEN_WIDTH, ANNdroid.SCREEN_HEIGHT));
		setBounds(0, 0, ANNdroid.SCREEN_WIDTH, ANNdroid.SCREEN_HEIGHT);

		InputMap im;
		ActionMap am;

		// Left Side Pane Layout Initialization //
		String labels[] = {"Create Student Account", "Delete Student Account", "View Students", "Manage Subjects", "Statistics", "Edit Profile", "Logout"};

		leftSidePane = new LeftSidePane(this, 1, 7, labels);
		leftSidePane.setPreferredSize(new Dimension(getWidth()/4 + 60, getHeight()));
		leftSidePane.setBounds(0, 0, getWidth()/4 + 60, getHeight());
		leftSidePane.setBackground(Color.BLACK);		

		// Create Student Pane //
		createStudentPane = new GenericPane(getWidth() - leftSidePane.getWidth() - 200, getHeight()/3, 1);
		createStudentPane.setBounds(leftSidePane.getWidth() + 100, getHeight()/4, getWidth() - leftSidePane.getWidth() - 200, getHeight()/3);
		createStudentPane.setVisible(false);

		errorLabel1 = new CustomLabel("", createStudentPane.getWidth() - (createStudentPane.getWidth()/8), 20, 0);
		errorLabel1.setBounds(createStudentPane.getWidth()/26, createStudentPane.getHeight()/16, createStudentPane.getWidth() - (createStudentPane.getWidth()/8), 20);
		createStudentPane.add(errorLabel1);

		fnameLabel = new CustomLabel("First Name: ", createStudentPane.getWidth()/2 - createStudentPane.getWidth()/16, 20, 1);
		fnameLabel.setBounds(createStudentPane.getWidth()/16, errorLabel1.getY() + 30, createStudentPane.getWidth()/2 - createStudentPane.getWidth()/16, 20);
		createStudentPane.add(fnameLabel);		

		fnameField = new CustomTextField(new Color(0, 29, 60, 0));
		fnameField.setBounds(fnameLabel.getWidth()+createStudentPane.getWidth()/16, fnameLabel.getY(), (7*createStudentPane.getWidth())/16, 20);
		createStudentPane.add(fnameField);
		fnameField.addFocusListener(new FieldsFocusListener(0, 0));
		fnameField.addActionListener(event -> {lnameField.requestFocus();});

		lnameLabel = new CustomLabel("Last Name: ", fnameLabel.getWidth(), 20, 1);
		lnameLabel.setBounds(fnameLabel.getX(), fnameLabel.getY()+30, fnameLabel.getWidth(), 20);
		createStudentPane.add(lnameLabel);		

		lnameField = new CustomTextField(new Color(0, 29, 60, 0));
		lnameField.setBounds(fnameField.getX(), fnameField.getY()+30, fnameField.getWidth(), 20);
		createStudentPane.add(lnameField);
		lnameField.addFocusListener(new FieldsFocusListener(0, 1));
		lnameField.addActionListener(event -> {unameField.requestFocus();});

		unameLabel = new CustomLabel("Username: ", lnameLabel.getWidth(), 20, 1);
		unameLabel.setBounds(lnameLabel.getX(), lnameLabel.getY()+30, lnameLabel.getWidth(), 20);
		createStudentPane.add(unameLabel);

		unameField = new CustomTextField(new Color(0, 29, 60, 0));
		unameField.setBounds(lnameField.getX(), lnameField.getY()+30, lnameField.getWidth(), 20);
		createStudentPane.add(unameField);
		unameField.addFocusListener(new FieldsFocusListener(0, 2));
		unameField.addActionListener(event -> {pwordField.requestFocus();});

		pwordLabel = new CustomLabel("Password: ", unameLabel.getWidth(), 20, 1);
		pwordLabel.setBounds(unameLabel.getX(), unameLabel.getY()+30, unameLabel.getWidth(), 20);
		createStudentPane.add(pwordLabel);

		pwordField = new CustomPasswordField(new Color(0, 29, 60, 0));
		pwordField.setBounds(unameField.getX(), unameField.getY()+30, unameField.getWidth(), 20);
		createStudentPane.add(pwordField);
		pwordField.addFocusListener(new FieldsFocusListener(0, 3));
		pwordField.setFocusTraversalKeysEnabled(false);

		im = pwordField.getInputMap(JComponent.WHEN_FOCUSED);
		am = pwordField.getActionMap();

		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "tab");
		am.put("tab", new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				fnameField.requestFocus();
			}
		});

		createBtn = new CustomButton("Create", pwordLabel.getWidth() - createStudentPane.getWidth()/16, createStudentPane.getHeight()/7);
		createBtn.setBounds(createStudentPane.getWidth()/2-(pwordLabel.getWidth() - createStudentPane.getWidth()/16), pwordLabel.getY() + 30, pwordLabel.getWidth() - createStudentPane.getWidth()/16, createStudentPane.getHeight()/7);
		createStudentPane.add(createBtn);
		createBtn.addActionListener(new TeacherActionListener(0, 0));

		cancelBtn1 = new CustomButton("Cancel", createBtn.getWidth(), createBtn.getHeight());
		cancelBtn1.setBounds(createBtn.getX() + createBtn.getWidth(), createBtn.getY(), createBtn.getWidth(), createBtn.getHeight());
		createStudentPane.add(cancelBtn1);
		cancelBtn1.addActionListener(new TeacherActionListener(0, 1));

		// Delete Teacher Pane Initialization //
		delStudentPane = new GenericPane(getWidth() - leftSidePane.getWidth() - 200, getHeight()/5, 1);
		delStudentPane.setBounds(leftSidePane.getWidth() + 100, getHeight()/3, getWidth() - leftSidePane.getWidth() - 200, getHeight()/5);
		delStudentPane.setVisible(false);	

		errorLabel2 = new CustomLabel("", delStudentPane.getWidth() - (delStudentPane.getWidth()/8), 20, 0);
		errorLabel2.setBounds(delStudentPane.getWidth()/26, delStudentPane.getHeight()/16, delStudentPane.getWidth() - (delStudentPane.getWidth()/8), 20);
		delStudentPane.add(errorLabel2);	

		delUnameLabel = new CustomLabel("Username: ", delStudentPane.getWidth()/2, 20, 1);
		delUnameLabel.setBounds(errorLabel2.getX(), errorLabel2.getY()+30, delStudentPane.getWidth()/2 - delStudentPane.getWidth()/16, 20);
		delStudentPane.add(delUnameLabel);

		delUnameField = new CustomTextField(new Color(0, 29, 60, 0));
		delUnameField.setBounds(delUnameLabel.getWidth()+delStudentPane.getWidth()/16, delUnameLabel.getY(), (7*delStudentPane.getWidth())/16, 20);
		delStudentPane.add(delUnameField);
		delUnameField.addFocusListener(new FieldsFocusListener(1, 0));
		delUnameField.addActionListener(event -> {teachPwordField.requestFocus();});

		teachPwordLabel = new CustomLabel("Teacher Password: ", delUnameLabel.getWidth(), 20, 1);
		teachPwordLabel.setBounds(delUnameLabel.getX(), delUnameLabel.getY() + 30, delUnameLabel.getWidth(), 20);
		delStudentPane.add(teachPwordLabel);

		teachPwordField = new CustomPasswordField(new Color(0, 29, 60, 0));
		teachPwordField.setBounds(delUnameField.getX(), delUnameField.getY() + 30, delUnameField.getWidth(), 20);
		delStudentPane.add(teachPwordField);
		delStudentPane.add(teachPwordField);
		teachPwordField.addFocusListener(new FieldsFocusListener(1, 1));
		teachPwordField.setFocusTraversalKeysEnabled(false);

		im = teachPwordField.getInputMap(JComponent.WHEN_FOCUSED);
		am = teachPwordField.getActionMap();

		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "tab");
		am.put("tab", new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				delUnameField.requestFocus();
			}
		});

		deleteBtn = new CustomButton("Confirm", teachPwordLabel.getWidth() - delStudentPane.getWidth()/16, delStudentPane.getHeight()/7);
		deleteBtn.setBounds(delStudentPane.getWidth()/2 - (teachPwordLabel.getWidth() - delStudentPane.getWidth()/16), teachPwordLabel.getY() + 28, teachPwordLabel.getWidth() - delStudentPane.getWidth()/16, delStudentPane.getHeight()/7);
		delStudentPane.add(deleteBtn);
		deleteBtn.addActionListener(new TeacherActionListener(1, 0));

		cancelBtn2 = new CustomButton("Cancel", deleteBtn.getWidth(), deleteBtn.getHeight());
		cancelBtn2.setBounds(deleteBtn.getX() + deleteBtn.getWidth(), deleteBtn.getY(), deleteBtn.getWidth(), deleteBtn.getHeight());
		delStudentPane.add(cancelBtn2);
		cancelBtn2.addActionListener(new TeacherActionListener(1, 1));	

		// Manage Subjects Pane //
		manageSubjectsButtonPane = new GenericPane(getWidth()-leftSidePane.getWidth()-30, getHeight() - 180, 1);
		manageSubjectsButtonPane.setBounds(leftSidePane.getWidth(), getHeight()/8, getWidth()-leftSidePane.getWidth()-30, getHeight()/10);
		manageSubjectsButtonPane.setVisible(false);

		manageSubjectPane = new GenericPane(getWidth()-leftSidePane.getWidth()-30, getHeight()-(manageSubjectsButtonPane.getHeight())*3, 1);
		manageSubjectPane.setBounds(manageSubjectsButtonPane.getX(), manageSubjectsButtonPane.getY()+ manageSubjectsButtonPane.getHeight(), getWidth()-leftSidePane.getWidth()-30, getHeight()-(manageSubjectsButtonPane.getHeight())*3);
		manageSubjectPane.setVisible(false);

		errorLabel3 = new CustomLabel("", manageSubjectPane.getWidth() - (manageSubjectPane.getWidth()/8), 20, 0);
		errorLabel3.setBounds(manageSubjectPane.getWidth()/26, manageSubjectPane.getHeight()/16, manageSubjectPane.getWidth() - (manageSubjectPane.getWidth()/8), 20);
		manageSubjectPane.add(errorLabel3);		

		addQuestionPane = new JPanel(null);
		addQuestionPane.setBounds(manageSubjectPane.getWidth()/16+5, manageSubjectPane.getHeight()/5, manageSubjectPane.getWidth() - manageSubjectPane.getWidth()/7, manageSubjectPane.getHeight()-manageSubjectPane.getHeight()/4-5);
		addQuestionPane.setOpaque(false);
		addQuestionPane.setVisible(false);
		manageSubjectPane.add(addQuestionPane);

		subjectLabel = new CustomLabel("Subject: ", addQuestionPane.getWidth()/6, 20, 1);
		subjectLabel.setBounds(0, 0, addQuestionPane.getWidth()/6, 20);
		addQuestionPane.add(subjectLabel);

		subjectField = new CustomTextField(new Color(0, 29, 60, 0));
		subjectField.setBounds(subjectLabel.getX()+subjectLabel.getWidth(), subjectLabel.getY(), addQuestionPane.getWidth()/4, 20);
		addQuestionPane.add(subjectField);

		qLabel = new CustomLabel("Question: ", subjectLabel.getWidth()/4, 20, 1);
		qLabel.setBounds(subjectLabel.getX(), subjectLabel.getY() + 40, subjectLabel.getWidth()/4, 20);
		addQuestionPane.add(qLabel);

		qField = new CustomTextArea(new Color(0, 29, 60, 0), "", 3, 50);
		qField.setBounds(0, 0, subjectField.getWidth()*3+15, 40);
		qField.setLineWrap(true);
		qField.setFocusTraversalKeysEnabled(false);

		im = qField.getInputMap(JComponent.WHEN_FOCUSED);
		am = qField.getActionMap();

		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "tab");
		am.put("tab", new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				fchoiceField.requestFocus();
			}
		});		

		jp = new CustomScrollPane(qField);
		jp.setBounds(subjectField.getX(), subjectField.getY()+30, subjectField.getWidth()*3+15, 40);
		addQuestionPane.add(jp);

		DefaultCaret caret = (DefaultCaret)qField.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

		fchoiceLabel = new CustomLabel("Choice A: ", qLabel.getWidth(), 20, 1);
		fchoiceLabel.setBounds(qLabel.getX(), qLabel.getY() + 40, qLabel.getWidth(), 20);
		addQuestionPane.add(fchoiceLabel);

		fchoiceField = new CustomTextField(new Color(0, 29, 60, 0));
		fchoiceField.setBounds(subjectField.getX(), fchoiceLabel.getY(), subjectField.getWidth(), 20);
		addQuestionPane.add(fchoiceField);
		fchoiceField.setFocusTraversalKeysEnabled(false);

		im = fchoiceField.getInputMap(JComponent.WHEN_FOCUSED);
		am = fchoiceField.getActionMap();

		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "tab");
		am.put("tab", new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				schoiceField.requestFocus();
			}
		});		

		schoiceLabel = new CustomLabel("Choice B: ", fchoiceLabel.getWidth(), 20, 1);
		schoiceLabel.setBounds(fchoiceLabel.getX(), fchoiceLabel.getY() + 30, fchoiceLabel.getWidth(), 20);
		addQuestionPane.add(schoiceLabel);

		schoiceField = new CustomTextField(new Color(0, 29, 60, 0));
		schoiceField.setBounds(fchoiceField.getX(), schoiceLabel.getY(), fchoiceField.getWidth(), 20);
		addQuestionPane.add(schoiceField);
		schoiceField.setFocusTraversalKeysEnabled(false);

		im = schoiceField.getInputMap(JComponent.WHEN_FOCUSED);
		am = schoiceField.getActionMap();

		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "tab");
		am.put("tab", new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				tchoiceField.requestFocus();
			}
		});

		tchoiceLabel = new CustomLabel("Choice C: ", schoiceLabel.getWidth(), 20, 1);
		tchoiceLabel.setBounds(addQuestionPane.getWidth()/2, fchoiceLabel.getY(), schoiceLabel.getWidth(), 20);
		addQuestionPane.add(tchoiceLabel);

		tchoiceField = new CustomTextField(new Color(0, 29, 60, 0));
		tchoiceField.setBounds(tchoiceLabel.getX()+tchoiceLabel.getWidth(), tchoiceLabel.getY(), tchoiceLabel.getWidth(), 20);
		addQuestionPane.add(tchoiceField);
		tchoiceField.setFocusTraversalKeysEnabled(false);

		im = tchoiceField.getInputMap(JComponent.WHEN_FOCUSED);
		am = tchoiceField.getActionMap();

		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "tab");
		am.put("tab", new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				fthchoiceField.requestFocus();
			}
		});		

		fthchoiceLabel = new CustomLabel("Choice D: ", tchoiceLabel.getWidth(), 20, 1);
		fthchoiceLabel.setBounds(tchoiceLabel.getX(), schoiceLabel.getY(), tchoiceLabel.getWidth(), 20);
		addQuestionPane.add(fthchoiceLabel);		

		fthchoiceField = new CustomTextField(new Color(0, 29, 60, 0));
		fthchoiceField.setBounds(tchoiceField.getX(), fthchoiceLabel.getY(), tchoiceField.getWidth(), 20);
		addQuestionPane.add(fthchoiceField);
		fthchoiceField.setFocusTraversalKeysEnabled(false);

		im = fthchoiceField.getInputMap(JComponent.WHEN_FOCUSED);
		am = fthchoiceField.getActionMap();

		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "tab");
		am.put("tab", new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				subjectField.requestFocus();
			}
		});		

		answerLabel = new CustomLabel("Answer: ", subjectLabel.getWidth(), 20, 1);
		answerLabel.setBounds(schoiceLabel.getX(), schoiceLabel.getY()+30, subjectLabel.getWidth(), 20);
		addQuestionPane.add(answerLabel);

		answerField = new CustomComboBox<Object>(CHOICES);
		answerField.setBounds(answerLabel.getX()+answerLabel.getWidth(), answerLabel.getY(), answerLabel.getWidth(), 20);
		addQuestionPane.add(answerField);
		((JLabel)answerField.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);		
		answerField.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JComboBox<?> cb = (JComboBox<?>)e.getSource();
				selectedChoice = cb.getSelectedIndex();
				answerField.setFocusable(false);
				requestFocusInWindow();
			}
		});

		diffLabel = new CustomLabel("Difficulty: ", answerLabel.getWidth(), 20, 1);
		diffLabel.setBounds(addQuestionPane.getWidth()/2, answerLabel.getY(), answerLabel.getWidth(), 20);
		addQuestionPane.add(diffLabel);

		diffField = new CustomComboBox<Object>(DIFFICULTY);
		diffField.setBounds(diffLabel.getX()+diffLabel.getWidth(), diffLabel.getY(), diffLabel.getWidth(), 20);
		addQuestionPane.add(diffField);
		((JLabel)diffField.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);		
		diffField.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JComboBox<?> cb = (JComboBox<?>)e.getSource();
				selectedDifficulty = (String)cb.getSelectedItem();
				answerField.setFocusable(false);
				requestFocusInWindow();
			}
		});	

		addBtn = new CustomButton("Add", addQuestionPane.getWidth()/2, 30);
		addBtn.setBounds(answerLabel.getX(), answerLabel.getY()+30, addQuestionPane.getWidth()/2, 30);
		addBtn.addActionListener(e->{			
			if(Utilities.subjectExists(subjectField.getText()) == -1){
				errorLabel3.setText("* subject does not exist");
				errorLabel3.setForeground(Color.RED);
			}
			else if(qField.getText().length() < 10){
				errorLabel3.setText("* question must at least be 10 characters");
				errorLabel3.setForeground(Color.RED);
			}
			else if(qField.getText().contains("\n")){
				errorLabel3.setText("* do not press enter in the question field");
				errorLabel3.setForeground(Color.RED);
			}
			else if(fchoiceField.getText().equals("") || schoiceField.getText().equals("") || tchoiceField.getText().equals("") || fthchoiceField.getText().equals("")){
				errorLabel3.setText("* all field choices must be filled out");
				errorLabel3.setForeground(Color.RED);
			}	
			else{

				Subject s = Simulator.subjectList.get(Utilities.subjectExists(subjectField.getText()));

				LinkedList<String> choices = new LinkedList<String>();
					choices.add(fchoiceField.getText());
					choices.add(schoiceField.getText());
					choices.add(tchoiceField.getText());
					choices.add(fthchoiceField.getText());

				Question q = new Question(qField.getText(), choices, selectedChoice, selectedDifficulty);
				LinkedList<Question> questionList = s.getQuestions();
				questionList.add(q);
				s.setQuestions(questionList);

				errorLabel3.setText("* successfully added the question!");
				errorLabel3.setForeground(Color.YELLOW);
				Simulator.saver.saveSubjects(Simulator.subjectList, "ANNdroid/bin/subjects.bin");
			}
			reinitialize(true, false, 2);
		});
		addQuestionPane.add(addBtn);

		cancelBtn3 = new CustomButton("Cancel", addQuestionPane.getWidth()/2, 30);
		cancelBtn3.setBounds(diffLabel.getX(), diffLabel.getY()+30, addQuestionPane.getWidth()/2, 30);
		cancelBtn3.addActionListener(e->{
			reinitialize(false, true, 2);
			addQuestionPane.setVisible(false);
		});
		addQuestionPane.add(cancelBtn3);

		// Manage Subjects Buttons
		addQuestion = new CustomButton("Add Question", manageSubjectsButtonPane.getWidth()/3-20, manageSubjectsButtonPane.getHeight()/2);
		addQuestion.setBounds(manageSubjectsButtonPane.getWidth()/14, 20, manageSubjectsButtonPane.getWidth()/3-25, manageSubjectsButtonPane.getHeight()/2);
		addQuestion.addActionListener(e -> {
			addQuestionPane.setVisible(true);
			subjectField.requestFocus();
		});
		manageSubjectsButtonPane.add(addQuestion);

		viewQuestions = new CustomButton("View Questions", addQuestion.getWidth(), addQuestion.getHeight());
		viewQuestions.setBounds(addQuestion.getX() + addQuestion.getWidth(), addQuestion.getY(), addQuestion.getWidth(), addQuestion.getHeight());
		viewQuestions.addActionListener(e->{
			addQuestionPane.setVisible(false);
		});
		manageSubjectsButtonPane.add(viewQuestions);

		deleteQuestion = new CustomButton("Delete Question", addQuestion.getWidth(), addQuestion.getHeight());
		deleteQuestion.setBounds(viewQuestions.getX() + viewQuestions.getWidth(), viewQuestions.getY(), viewQuestions.getWidth(), viewQuestions.getHeight());
		deleteQuestion.addActionListener(e->{
			addQuestionPane.setVisible(false);
		});		
		manageSubjectsButtonPane.add(deleteQuestion);


		add(leftSidePane);
		add(createStudentPane);
		add(delStudentPane);
		add(manageSubjectsButtonPane);
		add(manageSubjectPane);
	}

	public void resize(){
		// Resize Left Side Pane //
		leftSidePane.setBounds(0, 0, getWidth()/4 + 60, getHeight());
		((LeftSidePane)leftSidePane).resize();

		// Resizing Create Account //
		createStudentPane.setBounds(leftSidePane.getWidth() + 100, getHeight()/4, getWidth() - leftSidePane.getWidth() - 200, getHeight()/3);
		((GenericPane)createStudentPane).resize();

		errorLabel1.setBounds(createStudentPane.getWidth()/26, createStudentPane.getHeight()/16, createStudentPane.getWidth() - (createStudentPane.getWidth()/8), 20);
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
		delStudentPane.setBounds(leftSidePane.getWidth() + 100, getHeight()/3, getWidth() - leftSidePane.getWidth() - 200, getHeight()/5);
		((GenericPane)delStudentPane).resize();

		errorLabel2.setBounds(delStudentPane.getWidth()/26, delStudentPane.getHeight()/16, delStudentPane.getWidth() - (delStudentPane.getWidth()/8), 20);
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

		addQuestion.setBounds(manageSubjectsButtonPane.getWidth()/8, 20, manageSubjectsButtonPane.getWidth()/4, manageSubjectsButtonPane.getHeight()/2);
		((CustomButton)addQuestion).resize();

		viewQuestions.setBounds(addQuestion.getX() + addQuestion.getWidth(), addQuestion.getY(), addQuestion.getWidth(), addQuestion.getHeight());
		((CustomButton)viewQuestions).resize();		

		deleteQuestion.setBounds(viewQuestions.getX() + viewQuestions.getWidth(), viewQuestions.getY(), viewQuestions.getWidth(), viewQuestions.getHeight());
		((CustomButton)deleteQuestion).resize();

		manageSubjectPane.setBounds(manageSubjectsButtonPane.getX(), manageSubjectsButtonPane.getY()+ manageSubjectsButtonPane.getHeight(), getWidth()-leftSidePane.getWidth()-30, getHeight()-(manageSubjectsButtonPane.getHeight())*3);
		((GenericPane)manageSubjectPane).resize();

		errorLabel3.setBounds(manageSubjectPane.getWidth()/26, manageSubjectPane.getHeight()/16, manageSubjectPane.getWidth() - (manageSubjectPane.getWidth()/8), 20);
		((CustomLabel)errorLabel3).resize();

		addQuestionPane.setBounds(manageSubjectPane.getWidth()/16+5, manageSubjectPane.getHeight()/5, manageSubjectPane.getWidth() - manageSubjectPane.getWidth()/7, manageSubjectPane.getHeight()-manageSubjectPane.getHeight()/4-5);

		subjectLabel.setBounds(0, addQuestionPane.getHeight()/6, addQuestionPane.getWidth()/4, 20);
		((CustomLabel)subjectLabel).resize();

		subjectField.setBounds(subjectLabel.getX()+subjectLabel.getWidth(), subjectLabel.getY(), addQuestionPane.getWidth()/4, 20);		

		answerLabel.setBounds(schoiceLabel.getX(), schoiceLabel.getY()+30, subjectLabel.getWidth(), 20);
		((CustomLabel)answerLabel).resize();

		answerField.setBounds(answerLabel.getX()+answerLabel.getWidth(), answerLabel.getY(), answerLabel.getWidth(), 20);

		qLabel.setBounds(subjectLabel.getX(), subjectLabel.getY() + 40, subjectLabel.getWidth(), 20);
		((CustomLabel)qLabel).resize();

		qField.setBounds(0, 0, subjectField.getWidth()*3+15, 40);
		jp.setBounds(subjectField.getX(), subjectField.getY()+30, subjectField.getWidth()*3, 40);

		fchoiceLabel.setBounds(qLabel.getX(), qLabel.getY() + 40, qLabel.getWidth(), 20);
		((CustomLabel)fchoiceLabel).resize();

		fchoiceField.setBounds(subjectField.getX(), fchoiceLabel.getY(), subjectField.getWidth(), 20);

		schoiceLabel.setBounds(fchoiceLabel.getX(), fchoiceLabel.getY() + 30, fchoiceLabel.getWidth(), 20);
		((CustomLabel)schoiceLabel).resize();

		schoiceField.setBounds(fchoiceField.getX(), schoiceLabel.getY(), fchoiceField.getWidth(), 20);

		tchoiceLabel.setBounds(addQuestionPane.getWidth()/2, fchoiceLabel.getY(), schoiceLabel.getWidth(), 20);
		((CustomLabel)tchoiceLabel).resize();

		tchoiceField.setBounds(tchoiceLabel.getX()+tchoiceLabel.getWidth(), tchoiceLabel.getY(), tchoiceLabel.getWidth(), 20);

		fthchoiceLabel.setBounds(tchoiceLabel.getX(), schoiceLabel.getY(), tchoiceLabel.getWidth(), 20);
		((CustomLabel)fthchoiceLabel).resize();

		fthchoiceField.setBounds(tchoiceField.getX(), fthchoiceLabel.getY(), tchoiceField.getWidth(), 20);

		diffLabel.setBounds(addQuestionPane.getWidth()/2, answerLabel.getY(), answerLabel.getWidth(), 20);
		((CustomLabel)diffLabel).resize();

		diffField.setBounds(diffLabel.getX()+diffLabel.getWidth(), diffLabel.getY(), diffLabel.getWidth(), 20);

		addBtn.setBounds(answerLabel.getX(), answerLabel.getY()+30, addQuestionPane.getWidth()/2, 30);
		((CustomButton)addBtn).resize();

		cancelBtn3.setBounds(diffLabel.getX(), diffLabel.getY()+30, addQuestionPane.getWidth()/2, 30);
		((CustomButton)cancelBtn3).resize();
	}

	public void reinitialize(boolean error, boolean changeMode, int state){

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
			if(changeMode || !error) errorLabel3.setText("");
			
			selectedChoice = 0;
			selectedDifficulty = "EASY";

			answerField.setSelectedIndex(0);
			diffField.setSelectedIndex(0);
			qField.setText("");
			fchoiceField.setText("");
			schoiceField.setText("");
			tchoiceField.setText("");
			fthchoiceField.setText("");
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