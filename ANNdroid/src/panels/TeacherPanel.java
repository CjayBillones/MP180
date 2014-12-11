package ANNdroid.src.panels;

import ANNdroid.src.custom_swing.*;
import ANNdroid.src.objects.*;
import ANNdroid.src.events.*;
import ANNdroid.src.util.*;
import ANNdroid.src.*;

import java.util.LinkedList;

import javax.swing.*;
import javax.swing.text.DefaultCaret;

import java.awt.*;
import java.awt.event.*;

public class TeacherPanel extends JPanel{

	public static String[] SUBJECTS = {"Chemistry", "Physics", "Biology"};
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

	JPanel manageSubjectPane;
	JLabel errorLabel3;

	// Add Question //
	JPanel addQuestionPane;

	JLabel subjectLabel;
	JComboBox<String> subjChoices;
	String selectedSubject = null;

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
	JComboBox<String> diffField;
	JLabel answerLabel;
	JComboBox<String> answerField;

	int selectedChoice = -1;
	String selectedDifficulty = null;

	JButton addBtn;
	JButton cancelBtn3;

	// View Question //
	JPanel viewQuestionPane;

	JLabel viewSubjectLabel;
	JLabel questionNumberLabel;
	JComboBox<String> viewSubjChoices;
	JComboBox<Integer> viewQuestionChoices;
	String viewSelectedSubject = null;
	int viewSelectedQuestion = -1;

	JLabel viewqLabel;
	public JScrollPane viewjp;
	JTextArea viewqField;

	JLabel viewfchoiceLabel;
	JLabel viewschoiceLabel;
	JLabel viewtchoiceLabel;
	JLabel viewfthchoiceLabel;

	JTextField viewfchoiceField;
	JTextField viewschoiceField;
	JTextField viewtchoiceField;
	JTextField viewfthchoiceField;

	JLabel viewdiffLabel;
	JTextField viewdiffField;
	JLabel viewanswerLabel;
	JTextField viewanswerField;

	JButton delQuestionButton;
	JButton cancelBtn4;

	// Stats Panel //
	JLabel header_stat;
	JPanel statPane;

	JComboBox<String> studentbox;
	JLabel gamesPlayed;
	JLabel gamesWon;
	JLabel gamesDraw;
	JLabel gamesLost;
	JLabel chemStrLabel;
	JLabel physStrLabel;
	JLabel bioStrLabel;

	public JTextField gamesPlayedField;
	public JTextField gamesWonField;
	public JTextField gamesDrawField;
	public JTextField gamesLostField;
	public JTextField bioStrField;
	public JTextField chemStrField;
	public JTextField physStrField;

	// Headers //
	int headerheight = 50;
	JLabel header_create;
	JLabel header_delete;
	//JLabel header_stud;
	JLabel header_subj;

	public TeacherPanel(){

		setLayout(null);
		setPreferredSize(new Dimension(ANNdroid.SCREEN_WIDTH, ANNdroid.SCREEN_HEIGHT));
		setBounds(0, 0, ANNdroid.SCREEN_WIDTH, ANNdroid.SCREEN_HEIGHT);

		InputMap im;
		ActionMap am;

		// Left Side Pane Layout Initialization //
		String labels[] = {"Create Student Account", "Delete Student Account", "View Students", "Manage Subjects", "Account Settings", "Logout"};

		leftSidePane = new LeftSidePane(this, 1, 6, labels);
		leftSidePane.setPreferredSize(new Dimension(getWidth()/4 + 60, getHeight()));
		leftSidePane.setBounds(0, 0, getWidth()/4 + 60, getHeight());
		leftSidePane.setBackground(Color.BLACK);		

		// Stats Panel //
		statPane = new GenericPane(getWidth()-leftSidePane.getWidth()-30, getHeight()/4, 1);
		statPane.setBounds(leftSidePane.getWidth(), getHeight()*3/16, getWidth()-leftSidePane.getWidth()-30, getHeight()*2/3);
		statPane.setVisible(false);
		add(statPane);

		gamesPlayed = new CustomLabel("Games Played: ", statPane.getWidth()/4, 20, 1);
		gamesPlayed.setBounds(statPane.getWidth()/26, statPane.getHeight()/14, statPane.getWidth()/4, 20);
		statPane.add(gamesPlayed);

		studentbox = new CustomComboBox<String>();
		studentbox.setBounds(gamesPlayed.getX(), gamesPlayed.getY()-40, gamesPlayed.getWidth(), 20);
		statPane.add(studentbox);

		for(User u : ANNdroid.simulator.userList){
			if(u instanceof Student){
				studentbox.addItem(u.getFullname());
			}
		}
		studentbox.setSelectedItem(null);

		((JLabel)studentbox.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);		
		studentbox.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JComboBox<?> cb = (JComboBox<?>)e.getSource();

				for(User u : ANNdroid.simulator.userList){
					if(u.getFullname().equals(cb.getSelectedItem())){
							gamesPlayedField.setText(Integer.toString(((Student)u).getGamesPlayed()));
							gamesWonField.setText(Integer.toString(((Student)u).getGamesWon()));
							gamesDrawField.setText(Integer.toString(((Student)u).getGamesDraw()));
							gamesLostField.setText(Integer.toString(((Student)u).getGamesLost()));
							if(((Student)u).getChemistry() != -1)
								chemStrField.setText(Integer.toString(((Student)u).getChemistry()));
							else
								chemStrField.setText("n/a");
							if(((Student)u).getPhysics() != -1)
								physStrField.setText(Integer.toString(((Student)u).getPhysics()));										
							else
								physStrField.setText("n/a");
							if(((Student)u).getBiology() != -1)
								bioStrField.setText(Integer.toString(((Student)u).getBiology()));		
							else
								bioStrField.setText("n/a");
					}
				}
			}
		});	

		gamesWon = new CustomLabel("Games Won: ", statPane.getWidth()/4, 20, 1);
		gamesWon.setBounds(gamesPlayed.getX(), gamesPlayed.getY()+30, statPane.getWidth()/4, 20);
		statPane.add(gamesWon);

		gamesDraw = new CustomLabel("Games Draw: ", statPane.getWidth()/4, 20, 1);
		gamesDraw.setBounds(gamesPlayed.getX(), gamesWon.getY()+30, statPane.getWidth()/4, 20);
		statPane.add(gamesDraw);

		gamesLost = new CustomLabel("Games Lost:", statPane.getWidth()/4, 20, 1);
		gamesLost.setBounds(gamesPlayed.getX(), gamesDraw.getY()+30, statPane.getWidth()/4, 20);
		statPane.add(gamesLost);

		chemStrLabel = new CustomLabel("Chemistry Strength: ", statPane.getWidth()/4, 20, 1);
		chemStrLabel.setBounds(gamesPlayed.getX(), gamesLost.getY()+30, statPane.getWidth()/4, 20);
		statPane.add(chemStrLabel);

		physStrLabel = new CustomLabel("Physics Strength: ", statPane.getWidth()/4, 20, 1);
		physStrLabel.setBounds(gamesPlayed.getX(), chemStrLabel.getY()+30, statPane.getWidth()/4, 20);
		statPane.add(physStrLabel);

		bioStrLabel = new CustomLabel("Biology Strength: ", statPane.getWidth()/4, 20, 1);
		bioStrLabel.setBounds(gamesPlayed.getX(), physStrLabel.getY()+30, statPane.getWidth()/4, 20);
		statPane.add(bioStrLabel);

		gamesPlayedField = new CustomTextField(new Color(0, 29, 60, 0), false, "");
		gamesPlayedField.setBounds(gamesPlayed.getX()+gamesPlayed.getWidth(), gamesPlayed.getY(), gamesPlayed.getWidth(), 20);
		statPane.add(gamesPlayedField);

		gamesWonField = new CustomTextField(new Color(0, 29, 60, 0), false, "");
		gamesWonField.setBounds(gamesPlayedField.getX(), gamesWon.getY(), gamesPlayedField.getWidth(), 20);
		statPane.add(gamesWonField);

		gamesDrawField = new CustomTextField(new Color(0, 29, 60, 0), false, "");
		gamesDrawField.setBounds(gamesPlayedField.getX(), gamesDraw.getY(), gamesPlayedField.getWidth(), 20);
		statPane.add(gamesDrawField);

		gamesLostField = new CustomTextField(new Color(0, 29, 60, 0), false, "");
		gamesLostField.setBounds(gamesPlayedField.getX(), gamesLost.getY(), gamesPlayedField.getWidth(), 20);
		statPane.add(gamesLostField);

		bioStrField = new CustomTextField(new Color(0, 29, 60, 0), false, "");
		bioStrField.setBounds(gamesPlayedField.getX(), bioStrLabel.getY(), gamesPlayedField.getWidth(), 20);
		statPane.add(bioStrField);

		chemStrField = new CustomTextField(new Color(0, 29, 60, 0), false, "");
		chemStrField.setBounds(gamesPlayedField.getX(), chemStrLabel.getY(), gamesPlayedField.getWidth(), 20);
		statPane.add(chemStrField);

		physStrField = new CustomTextField(new Color(0, 29, 60, 0), false, "");
		physStrField.setBounds(gamesPlayedField.getX(), physStrLabel.getY(), gamesPlayedField.getWidth(), 20);
		statPane.add(physStrField);		

		header_stat = new CustomLabel("Statistics", statPane.getWidth(), headerheight, 2);
		header_stat.setBounds(statPane.getX(), statPane.getY()-headerheight, statPane.getWidth(), headerheight);
		header_stat.setVisible(false);
		
		add(header_stat);


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

		fnameField = new CustomTextField(new Color(0, 29, 60, 0), true, "");
		fnameField.setBounds(fnameLabel.getWidth()+createStudentPane.getWidth()/16, fnameLabel.getY(), (7*createStudentPane.getWidth())/16, 20);
		createStudentPane.add(fnameField);
		fnameField.addFocusListener(new FieldsFocusListener(0, 0));
		fnameField.addActionListener(event -> {lnameField.requestFocus();});

		lnameLabel = new CustomLabel("Last Name: ", fnameLabel.getWidth(), 20, 1);
		lnameLabel.setBounds(fnameLabel.getX(), fnameLabel.getY()+30, fnameLabel.getWidth(), 20);
		createStudentPane.add(lnameLabel);		

		lnameField = new CustomTextField(new Color(0, 29, 60, 0), true, "");
		lnameField.setBounds(fnameField.getX(), fnameField.getY()+30, fnameField.getWidth(), 20);
		createStudentPane.add(lnameField);
		lnameField.addFocusListener(new FieldsFocusListener(0, 1));
		lnameField.addActionListener(event -> {unameField.requestFocus();});

		unameLabel = new CustomLabel("Username: ", lnameLabel.getWidth(), 20, 1);
		unameLabel.setBounds(lnameLabel.getX(), lnameLabel.getY()+30, lnameLabel.getWidth(), 20);
		createStudentPane.add(unameLabel);

		unameField = new CustomTextField(new Color(0, 29, 60, 0), true, "");
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

		delUnameField = new CustomTextField(new Color(0, 29, 60, 0), true, "");
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
		manageSubjectsButtonPane.setBounds(leftSidePane.getWidth(), getHeight()/8+20, getWidth()-leftSidePane.getWidth()-30, getHeight()/10);
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

		subjChoices = new CustomComboBox<String>(SUBJECTS);
		subjChoices.setBounds(subjectLabel.getX()+subjectLabel.getWidth(), subjectLabel.getY(), addQuestionPane.getWidth()/4, 20);
		subjChoices.setSelectedItem(null);
		addQuestionPane.add(subjChoices);
		((JLabel)subjChoices.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);		
		subjChoices.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JComboBox<?> cb = (JComboBox<?>)e.getSource();
				selectedSubject = (String)cb.getSelectedItem();
				requestFocusInWindow();
			}
		});	

		qLabel = new CustomLabel("Question: ", subjectLabel.getWidth()/4, 20, 1);
		qLabel.setBounds(subjectLabel.getX(), subjectLabel.getY() + 40, subjectLabel.getWidth()/4, 20);
		addQuestionPane.add(qLabel);

		qField = new CustomTextArea(new Color(0, 29, 60, 0), "", 3, 50);
		qField.setBounds(0, 0, subjChoices.getWidth()*3+15, 40);
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
		jp.setBounds(subjChoices.getX(), subjChoices.getY()+30, subjChoices.getWidth()*3+15, 40);
		addQuestionPane.add(jp);

		DefaultCaret caret = (DefaultCaret)qField.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

		fchoiceLabel = new CustomLabel("Choice A: ", qLabel.getWidth(), 20, 1);
		fchoiceLabel.setBounds(qLabel.getX(), qLabel.getY() + 40, qLabel.getWidth(), 20);
		addQuestionPane.add(fchoiceLabel);

		fchoiceField = new CustomTextField(new Color(0, 29, 60, 0), true, "");
		fchoiceField.setBounds(subjChoices.getX(), fchoiceLabel.getY(), subjChoices.getWidth(), 20);
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

		schoiceField = new CustomTextField(new Color(0, 29, 60, 0), true, "");
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

		tchoiceField = new CustomTextField(new Color(0, 29, 60, 0), true, "");
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

		fthchoiceField = new CustomTextField(new Color(0, 29, 60, 0), true, "");
		fthchoiceField.setBounds(tchoiceField.getX(), fthchoiceLabel.getY(), tchoiceField.getWidth(), 20);
		addQuestionPane.add(fthchoiceField);
		fthchoiceField.setFocusTraversalKeysEnabled(false);

		im = fthchoiceField.getInputMap(JComponent.WHEN_FOCUSED);
		am = fthchoiceField.getActionMap();

		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "tab");
		am.put("tab", new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				subjChoices.requestFocus();
			}
		});		

		answerLabel = new CustomLabel("Answer: ", subjectLabel.getWidth(), 20, 1);
		answerLabel.setBounds(schoiceLabel.getX(), schoiceLabel.getY()+30, subjectLabel.getWidth(), 20);
		addQuestionPane.add(answerLabel);

		answerField = new CustomComboBox<String>(CHOICES);
		answerField.setBounds(answerLabel.getX()+answerLabel.getWidth(), answerLabel.getY(), answerLabel.getWidth(), 20);
		answerField.setSelectedItem(null);
		addQuestionPane.add(answerField);
		((JLabel)answerField.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);		
		answerField.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JComboBox<?> cb = (JComboBox<?>)e.getSource();
				selectedChoice = cb.getSelectedIndex();
				requestFocusInWindow();
			}
		});

		diffLabel = new CustomLabel("Difficulty: ", answerLabel.getWidth(), 20, 1);
		diffLabel.setBounds(addQuestionPane.getWidth()/2, answerLabel.getY(), answerLabel.getWidth(), 20);
		addQuestionPane.add(diffLabel);

		diffField = new CustomComboBox<String>(DIFFICULTY);
		diffField.setBounds(diffLabel.getX()+diffLabel.getWidth(), diffLabel.getY(), diffLabel.getWidth(), 20);
		diffField.setSelectedItem(null);
		addQuestionPane.add(diffField);
		((JLabel)diffField.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);		
		diffField.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JComboBox<?> cb = (JComboBox<?>)e.getSource();
				selectedDifficulty = (String)cb.getSelectedItem();
				requestFocusInWindow();
			}
		});

		addBtn = new CustomButton("Add", addQuestionPane.getWidth()/2, 30);
		addBtn.setBounds(answerLabel.getX(), answerLabel.getY()+30, addQuestionPane.getWidth()/2, 30);
		addBtn.addActionListener(e->{			
			if(selectedSubject == null){
				errorLabel3.setText("* choose a subject");
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
			else if(selectedChoice == -1){
				errorLabel3.setText("* supply answer");
				errorLabel3.setForeground(Color.RED);				
			}
			else if(selectedDifficulty == null){
				errorLabel3.setText("* choose a difficulty");
				errorLabel3.setForeground(Color.RED);
			}
			else{

				Subject s = Simulator.subjectList.get(Utilities.subjectExists(selectedSubject));

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
			SoundPlayer click = new SoundPlayer("click.mp3", false, 0);
			click.playSound();			
		});
		addQuestionPane.add(cancelBtn3);

		// View Subject Question //
		viewQuestionPane = new JPanel(null);
		viewQuestionPane.setBounds(manageSubjectPane.getWidth()/16+5, manageSubjectPane.getHeight()/5, manageSubjectPane.getWidth() - manageSubjectPane.getWidth()/7, manageSubjectPane.getHeight()-manageSubjectPane.getHeight()/4-5);
		viewQuestionPane.setOpaque(false);
		viewQuestionPane.setVisible(false);
		manageSubjectPane.add(viewQuestionPane);

		viewSubjectLabel = new CustomLabel("Subject: ", viewQuestionPane.getWidth()/6, 20, 1);
		viewSubjectLabel.setBounds(0, 0, viewQuestionPane.getWidth()/6, 20);
		viewQuestionPane.add(viewSubjectLabel);

		viewSubjChoices = new CustomComboBox<String>(SUBJECTS);
		viewSubjChoices.setBounds(viewSubjectLabel.getX()+viewSubjectLabel.getWidth(), viewSubjectLabel.getY(), viewQuestionPane.getWidth()/4, 20);
		viewSubjChoices.setSelectedItem(null);
		viewQuestionPane.add(viewSubjChoices);
		((JLabel)viewSubjChoices.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);		

		viewqLabel = new CustomLabel("Question: ", viewSubjectLabel.getWidth()/4, 20, 1);
		viewqLabel.setBounds(viewSubjectLabel.getX(), viewSubjectLabel.getY() + 40, viewSubjectLabel.getWidth()/4, 20);
		viewQuestionPane.add(viewqLabel);

		viewqField = new CustomTextArea(new Color(0, 29, 60, 0), "", 3, 50);
		viewqField.setBounds(0, 0, viewSubjChoices.getWidth()*3+15, 40);
		viewqField.setLineWrap(true);
		viewqField.setEditable(false);
		
		viewjp = new CustomScrollPane(viewqField);
		viewjp.setBounds(viewSubjChoices.getX(), viewSubjChoices.getY()+30, viewSubjChoices.getWidth()*3+15, 40);
		viewQuestionPane.add(viewjp);

		questionNumberLabel = new CustomLabel("Question Number: ", viewSubjectLabel.getWidth(), 20, 1);
		questionNumberLabel.setBounds(viewSubjChoices.getX()+viewSubjChoices.getWidth(), viewSubjChoices.getY(), viewSubjectLabel.getWidth()*2, 20);
		viewQuestionPane.add(questionNumberLabel);

		viewQuestionChoices = new CustomComboBox<Integer>();
		viewQuestionChoices.setBounds(questionNumberLabel.getX()+questionNumberLabel.getWidth(), viewSubjectLabel.getY(), viewQuestionPane.getWidth()/4, 20);
		viewQuestionChoices.setSelectedItem(null);
		viewQuestionChoices.setEnabled(false);
		viewQuestionPane.add(viewQuestionChoices);
		((JLabel)viewQuestionChoices.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		viewSubjChoices.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JComboBox<?> cb = (JComboBox<?>)e.getSource();
				viewSelectedSubject = (String)cb.getSelectedItem();
				viewQuestionChoices.setEnabled(viewSelectedSubject != null);
				if(viewSelectedSubject != null){
					int numOfQuestions=0;
					switch(viewSelectedSubject){
						case "Chemistry":
							numOfQuestions = Simulator.subjectList.get(Utilities.subjectExists(viewSelectedSubject)).getQuestions().size();
							break;
						case "Physics":
							numOfQuestions = Simulator.subjectList.get(Utilities.subjectExists(viewSelectedSubject)).getQuestions().size();
							break;
						case "Biology":
							numOfQuestions = Simulator.subjectList.get(Utilities.subjectExists(viewSelectedSubject)).getQuestions().size();
							break;
					}
					viewQuestionChoices.removeAllItems();
					int min = 1;
					int max = numOfQuestions;
					for(int item = min; item <= max; item++){
						viewQuestionChoices.addItem(item);
					}
					viewSubjChoices.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e){
							JComboBox<?> cb = (JComboBox<?>)e.getSource();
							viewSelectedQuestion = cb.getSelectedIndex();
							requestFocusInWindow();
						}
					});
				}
				requestFocusInWindow();
			}
		});
		viewQuestionChoices.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JComboBox<?> cb = (JComboBox<?>)e.getSource();
				viewSelectedQuestion = cb.getSelectedIndex();
				requestFocusInWindow();
			
				if(viewSelectedSubject != null){
					Subject s = Simulator.subjectList.get(Utilities.subjectExists(viewSelectedSubject));
					Question q;
					
					if(viewSelectedQuestion != -1){
						q = s.getQuestions().get(viewSelectedQuestion);
						viewqField.setText(q.getQuestion());
						viewfchoiceField.setText(q.getChoices().get(0));
						viewschoiceField.setText(q.getChoices().get(1));
						viewtchoiceField.setText(q.getChoices().get(2));
						viewfthchoiceField.setText(q.getChoices().get(3));
						viewanswerField.setText(q.getAnswerLetter());
						viewdiffField.setText(q.getDifficulty());
					}
				}	
			}			
		});

		viewfchoiceLabel = new CustomLabel("Choice A: ", viewqLabel.getWidth(), 20, 1);
		viewfchoiceLabel.setBounds(viewqLabel.getX(), viewqLabel.getY() + 40, viewqLabel.getWidth(), 20);
		viewQuestionPane.add(viewfchoiceLabel);

		viewfchoiceField = new CustomTextField(new Color(0, 29, 60, 0), false, "");
		viewfchoiceField.setBounds(viewSubjChoices.getX(), viewfchoiceLabel.getY(), viewSubjChoices.getWidth(), 20);
		viewQuestionPane.add(viewfchoiceField);

		viewschoiceLabel = new CustomLabel("Choice B: ", viewfchoiceLabel.getWidth(), 20, 1);
		viewschoiceLabel.setBounds(viewfchoiceLabel.getX(), viewfchoiceLabel.getY() + 30, viewfchoiceLabel.getWidth(), 20);
		viewQuestionPane.add(viewschoiceLabel);

		viewschoiceField = new CustomTextField(new Color(0, 29, 60, 0), false, "");
		viewschoiceField.setBounds(viewfchoiceField.getX(), viewschoiceLabel.getY(), viewfchoiceField.getWidth(), 20);
		viewQuestionPane.add(viewschoiceField);

		viewtchoiceLabel = new CustomLabel("Choice C: ", viewschoiceLabel.getWidth(), 20, 1);
		viewtchoiceLabel.setBounds(viewQuestionPane.getWidth()/2, viewfchoiceLabel.getY(), viewschoiceLabel.getWidth(), 20);
		viewQuestionPane.add(viewtchoiceLabel);

		viewtchoiceField = new CustomTextField(new Color(0, 29, 60, 0), false, "");
		viewtchoiceField.setBounds(viewtchoiceLabel.getX()+viewtchoiceLabel.getWidth(), viewtchoiceLabel.getY(), viewfchoiceField.getWidth(), 20);
		viewQuestionPane.add(viewtchoiceField);

		viewfthchoiceLabel = new CustomLabel("Choice D: ", viewtchoiceLabel.getWidth(), 20, 1);
		viewfthchoiceLabel.setBounds(viewtchoiceLabel.getX(), viewschoiceLabel.getY(), viewtchoiceLabel.getWidth(), 20);
		viewQuestionPane.add(viewfthchoiceLabel);

		viewfthchoiceField = new CustomTextField(new Color(0, 29, 60, 0), false, "");
		viewfthchoiceField.setBounds(viewtchoiceField.getX(), viewschoiceLabel.getY(), viewschoiceField.getWidth(), 20);
		viewQuestionPane.add(viewfthchoiceField);

		viewanswerLabel = new CustomLabel("Answer: ", viewSubjectLabel.getWidth(), 20, 1);
		viewanswerLabel.setBounds(viewschoiceLabel.getX(), viewschoiceLabel.getY()+30, viewSubjectLabel.getWidth(), 20);
		viewQuestionPane.add(viewanswerLabel);

		viewanswerField = new CustomTextField(new Color(0, 29, 60, 0), false, "");
		viewanswerField.setBounds(viewanswerLabel.getX()+viewanswerLabel.getWidth(), viewanswerLabel.getY(), viewanswerLabel.getWidth(), 20);
		viewQuestionPane.add(viewanswerField);

		viewdiffLabel = new CustomLabel("Difficulty: ", viewanswerLabel.getWidth(), 20, 1);
		viewdiffLabel.setBounds(viewQuestionPane.getWidth()/2, viewanswerLabel.getY(), viewanswerLabel.getWidth(), 20);
		viewQuestionPane.add(viewdiffLabel);

		viewdiffField = new CustomTextField(new Color(0, 29, 60, 0), false, "");
		viewdiffField.setBounds(viewdiffLabel.getX()+viewdiffLabel.getWidth(), viewdiffLabel.getY(), viewdiffLabel.getWidth(), 20);
		viewQuestionPane.add(viewdiffField);

		delQuestionButton = new CustomButton("Delete", viewQuestionPane.getWidth()/2, 30);
		delQuestionButton.setBounds(viewanswerLabel.getX(), viewanswerLabel.getY()+30, viewQuestionPane.getWidth()/2, 30);
		delQuestionButton.addActionListener(e->{			
			if(viewSelectedSubject != null && viewSelectedQuestion != -1){
					Subject s = Simulator.subjectList.get(Utilities.subjectExists(viewSelectedSubject));
					Question q = s.getQuestions().get(viewSelectedQuestion);
					
					s.getQuestions().remove(q);
					errorLabel3.setForeground(Color.YELLOW);
					errorLabel3.setText("* successfully deleted question");
					Simulator.saver.saveSubjects(Simulator.subjectList, "ANNdroid/bin/subjects.bin");					
			}
			else{
				errorLabel3.setForeground(Color.RED);
				errorLabel3.setText("* select question to delete");
			}
			reinitializeViewQuestionPane();
			reinitialize(true, false, 2);
		});
		viewQuestionPane.add(delQuestionButton);

		cancelBtn4 = new CustomButton("Cancel", viewQuestionPane.getWidth()/2, 30);
		cancelBtn4.setBounds(viewdiffLabel.getX(), viewdiffLabel.getY()+30, viewQuestionPane.getWidth()/2, 30);
		cancelBtn4.addActionListener(e->{
			reinitializeViewQuestionPane();
			reinitialize(false, true, 2);
			viewQuestionPane.setVisible(false);
			SoundPlayer click = new SoundPlayer("click.mp3", false, 0);
			click.playSound();
		});
		viewQuestionPane.add(cancelBtn4);


		// Manage Subjects Buttons
		addQuestion = new CustomButton("Add Question", manageSubjectsButtonPane.getWidth()/2, manageSubjectsButtonPane.getHeight()/2);
		addQuestion.setBounds(manageSubjectsButtonPane.getWidth()/14, 20, manageSubjectsButtonPane.getWidth()/3-25, manageSubjectsButtonPane.getHeight()/2);
		addQuestion.addActionListener(e -> {
			addQuestionPane.setVisible(true);

			viewQuestionPane.setVisible(false);
			reinitializeViewQuestionPane();
			reinitialize(false, true, 2);
		});
		manageSubjectsButtonPane.add(addQuestion);

		viewQuestions = new CustomButton("Search/Delete Question", addQuestion.getWidth(), addQuestion.getHeight());
		viewQuestions.setBounds(addQuestion.getX() + addQuestion.getWidth(), addQuestion.getY(), addQuestion.getWidth(), addQuestion.getHeight());
		viewQuestions.addActionListener(e->{
			addQuestionPane.setVisible(false);
			reinitialize(false, true, 2);

			viewQuestionPane.setVisible(true);
		});
		manageSubjectsButtonPane.add(viewQuestions);

		// Headers //
		header_create = new CustomLabel("Create Student", createStudentPane.getWidth(), headerheight, 2);
		header_create.setBounds(createStudentPane.getX(), createStudentPane.getY()-headerheight, createStudentPane.getWidth(), headerheight);
		header_create.setVisible(false);
		add(header_create);

		header_delete = new CustomLabel("Delete Student", delStudentPane.getWidth(), headerheight, 2);
		header_delete.setBounds(delStudentPane.getX(), delStudentPane.getY()-headerheight, delStudentPane.getWidth(), headerheight);
		header_delete.setVisible(false);
		add(header_delete);


		header_subj = new CustomLabel("Manage Subjects", manageSubjectPane.getWidth(), headerheight, 2);
		header_subj.setBounds(manageSubjectPane.getX(), manageSubjectsButtonPane.getY()-headerheight, manageSubjectPane.getWidth(), headerheight);
		header_subj.setVisible(false);
		add(header_subj);

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
		manageSubjectsButtonPane.setBounds(leftSidePane.getWidth(), getHeight()/8+50, getWidth()-leftSidePane.getWidth()-30, getHeight()/10);
		((GenericPane)manageSubjectsButtonPane).resize();

		addQuestion.setBounds(manageSubjectsButtonPane.getWidth()/25, 20, manageSubjectsButtonPane.getWidth()/2-20, manageSubjectsButtonPane.getHeight()/2);
		((CustomButton)addQuestion).resize();

		viewQuestions.setBounds(addQuestion.getX() + addQuestion.getWidth(), addQuestion.getY(), addQuestion.getWidth(), addQuestion.getHeight());
		((CustomButton)viewQuestions).resize();		

		manageSubjectPane.setBounds(manageSubjectsButtonPane.getX(), manageSubjectsButtonPane.getY()+ manageSubjectsButtonPane.getHeight(), getWidth()-leftSidePane.getWidth()-30, getHeight()/2);
		((GenericPane)manageSubjectPane).resize();

		errorLabel3.setBounds(manageSubjectPane.getWidth()/26, manageSubjectPane.getHeight()/16, manageSubjectPane.getWidth() - (manageSubjectPane.getWidth()/8), 30);
		((CustomLabel)errorLabel3).resize();

		addQuestionPane.setBounds(manageSubjectPane.getWidth()/16+5, manageSubjectPane.getHeight()/8, manageSubjectPane.getWidth() - manageSubjectPane.getWidth()/7, manageSubjectPane.getHeight()-manageSubjectPane.getHeight()/4-5);
		addQuestionPane.revalidate();
		
		subjectLabel.setBounds(0, addQuestionPane.getHeight()/6, addQuestionPane.getWidth()/5, 20);
		((CustomLabel)subjectLabel).resize();

		subjChoices.setBounds(subjectLabel.getX()+subjectLabel.getWidth(), subjectLabel.getY(), addQuestionPane.getWidth()/4, 20);

		answerLabel.setBounds(schoiceLabel.getX(), schoiceLabel.getY()+30, subjectLabel.getWidth(), 20);
		((CustomLabel)answerLabel).resize();

		answerField.setBounds(answerLabel.getX()+answerLabel.getWidth(), answerLabel.getY(), answerLabel.getWidth(), 20);

		qLabel.setBounds(subjectLabel.getX(), subjectLabel.getY() + 40, subjectLabel.getWidth(), 20);
		((CustomLabel)qLabel).resize();

		qField.setBounds(0, 0, subjChoices.getWidth()*3+15, 40);
		jp.setBounds(subjChoices.getX(), subjChoices.getY()+30, subjChoices.getWidth()*3, 40);

		fchoiceLabel.setBounds(qLabel.getX(), qLabel.getY() + 40, qLabel.getWidth(), 20);
		((CustomLabel)fchoiceLabel).resize();

		fchoiceField.setBounds(subjChoices.getX(), fchoiceLabel.getY(), subjChoices.getWidth(), 20);

		schoiceLabel.setBounds(fchoiceLabel.getX(), fchoiceLabel.getY() + 30, fchoiceLabel.getWidth(), 20);
		((CustomLabel)schoiceLabel).resize();

		schoiceField.setBounds(fchoiceField.getX(), schoiceLabel.getY(), fchoiceField.getWidth(), 20);

		tchoiceLabel.setBounds(addQuestionPane.getWidth()/2, fchoiceLabel.getY(), schoiceLabel.getWidth(), 20);
		((CustomLabel)tchoiceLabel).resize();

		tchoiceField.setBounds(tchoiceLabel.getX()+tchoiceLabel.getWidth(), tchoiceLabel.getY(), fchoiceField.getWidth(), 20);

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

		// View Subject Pane //
		viewQuestionPane.setBounds(manageSubjectPane.getWidth()/16+5, manageSubjectPane.getHeight()/5, manageSubjectPane.getWidth() - manageSubjectPane.getWidth()/7, manageSubjectPane.getHeight()-manageSubjectPane.getHeight()/4-5);

		viewSubjectLabel.setBounds(0, 0, viewQuestionPane.getWidth()/5, 20);
		((CustomLabel)viewSubjectLabel).resize();

		viewSubjChoices.setBounds(viewSubjectLabel.getX()+viewSubjectLabel.getWidth(), viewSubjectLabel.getY(), viewQuestionPane.getWidth()/4, 20);

		viewqLabel.setBounds(viewSubjectLabel.getX(), viewSubjectLabel.getY() + 40, viewSubjectLabel.getWidth(), 20);
		((CustomLabel)viewqLabel).resize();

		viewqField.setBounds(0, 0, viewSubjChoices.getWidth()*3+15, 40);
		viewjp.setBounds(viewSubjChoices.getX(), viewSubjChoices.getY()+30, viewSubjChoices.getWidth()*3+15, 40);
		
		questionNumberLabel.setBounds(viewSubjChoices.getX()+viewSubjChoices.getWidth(), viewSubjChoices.getY(), viewSubjectLabel.getWidth()*2, 20);
		((CustomLabel)questionNumberLabel).resize();

		viewQuestionChoices.setBounds(questionNumberLabel.getX()+questionNumberLabel.getWidth(), viewSubjectLabel.getY(), questionNumberLabel.getWidth()/3, 20);

		viewfchoiceLabel.setBounds(viewqLabel.getX(), viewqLabel.getY() + 40, viewqLabel.getWidth(), 20);
		((CustomLabel)viewfchoiceLabel).resize();
		viewfchoiceField.setBounds(viewSubjChoices.getX(), viewfchoiceLabel.getY(), viewSubjChoices.getWidth(), 20);

		viewschoiceLabel.setBounds(viewfchoiceLabel.getX(), viewfchoiceLabel.getY() + 30, viewfchoiceLabel.getWidth(), 20);
		((CustomLabel)viewschoiceLabel).resize();
		viewschoiceField.setBounds(viewfchoiceField.getX(), viewschoiceLabel.getY(), viewfchoiceField.getWidth(), 20);

		viewtchoiceLabel.setBounds(viewQuestionPane.getWidth()/2, viewfchoiceLabel.getY(), viewschoiceLabel.getWidth(), 20);
		((CustomLabel)viewtchoiceLabel).resize();
		viewtchoiceField.setBounds(viewtchoiceLabel.getX()+viewtchoiceLabel.getWidth(), viewtchoiceLabel.getY(), viewschoiceField.getWidth(), 20);

		viewfthchoiceLabel.setBounds(viewtchoiceLabel.getX(), viewschoiceLabel.getY(), viewtchoiceLabel.getWidth(), 20);
		((CustomLabel)viewfthchoiceLabel).resize();
		viewfthchoiceField.setBounds(viewtchoiceField.getX(), viewschoiceLabel.getY(), viewschoiceField.getWidth(), 20);

		viewanswerLabel.setBounds(viewschoiceLabel.getX(), viewschoiceLabel.getY()+30, viewSubjectLabel.getWidth(), 20);
		((CustomLabel)viewanswerLabel).resize();
		viewanswerField.setBounds(viewanswerLabel.getX()+viewanswerLabel.getWidth(), viewanswerLabel.getY(), viewanswerLabel.getWidth(), 20);

		viewdiffLabel.setBounds(viewQuestionPane.getWidth()/2, viewanswerLabel.getY(), viewanswerLabel.getWidth(), 20);
		((CustomLabel)viewdiffLabel).resize();
		viewdiffField.setBounds(viewdiffLabel.getX()+viewdiffLabel.getWidth(), viewdiffLabel.getY(), viewdiffLabel.getWidth(), 20);

		delQuestionButton.setBounds(viewanswerLabel.getX(), viewanswerLabel.getY()+30, viewQuestionPane.getWidth()/2, 30);
		((CustomButton)delQuestionButton).resize();
		
		cancelBtn4.setBounds(viewdiffLabel.getX(), viewdiffLabel.getY()+30, viewQuestionPane.getWidth()/2, 30);
		((CustomButton)cancelBtn4).resize();

		// Stats Pane Resize //
		statPane.setBounds(leftSidePane.getWidth(), getHeight()*3/16, getWidth() - leftSidePane.getWidth() - 200, getHeight()*2/3);
		((GenericPane)statPane).resize();

		gamesPlayed.setBounds(statPane.getWidth()/26, statPane.getHeight()/7, statPane.getWidth()/4, 20);
		((CustomLabel)gamesPlayed).resize();

		studentbox.setBounds(gamesPlayed.getX(), gamesPlayed.getY()-40, gamesPlayed.getWidth(), 20);		

		gamesWon.setBounds(gamesPlayed.getX(), gamesPlayed.getY()+50, statPane.getWidth()/4, 20);
		((CustomLabel)gamesWon).resize();

		gamesDraw.setBounds(gamesPlayed.getX(), gamesWon.getY()+50, statPane.getWidth()/4, 20);
		((CustomLabel)gamesDraw).resize();

		gamesLost.setBounds(gamesPlayed.getX(), gamesDraw.getY()+50, statPane.getWidth()/4, 20);
		((CustomLabel)gamesLost).resize();

		chemStrLabel.setBounds(gamesPlayed.getX(), gamesLost.getY()+50, statPane.getWidth()/4, 20);
		((CustomLabel)chemStrLabel).resize();

		physStrLabel.setBounds(gamesPlayed.getX(), chemStrLabel.getY()+50, statPane.getWidth()/4, 20);
		((CustomLabel)physStrLabel).resize();

		bioStrLabel.setBounds(gamesPlayed.getX(), physStrLabel.getY()+50, statPane.getWidth()/4, 20);
		((CustomLabel)bioStrLabel).resize();

		gamesPlayedField.setBounds(gamesPlayed.getX()+gamesPlayed.getWidth(), gamesPlayed.getY(), gamesPlayed.getWidth(), 20);

		gamesWonField.setBounds(gamesPlayedField.getX(), gamesWon.getY(), gamesPlayedField.getWidth(), 20);

		gamesDrawField.setBounds(gamesPlayedField.getX(), gamesDraw.getY(), gamesPlayedField.getWidth(), 20);

		gamesLostField.setBounds(gamesPlayedField.getX(), gamesLost.getY(), gamesPlayedField.getWidth(), 20);

		bioStrField.setBounds(gamesPlayedField.getX(), bioStrLabel.getY(), gamesPlayedField.getWidth(), 20);

		chemStrField.setBounds(gamesPlayedField.getX(), chemStrLabel.getY(), gamesPlayedField.getWidth(), 20);

		physStrField.setBounds(gamesPlayedField.getX(), physStrLabel.getY(), gamesPlayedField.getWidth(), 20);

		header_stat.setBounds(statPane.getX(), statPane.getY()-headerheight, statPane.getWidth(), headerheight);

		header_create.setBounds(createStudentPane.getX(), createStudentPane.getY()-headerheight, createStudentPane.getWidth(), headerheight);
		((CustomLabel)header_create).resize();

		header_delete.setBounds(createStudentPane.getX(), delStudentPane.getY()-headerheight, delStudentPane.getWidth(), headerheight);
		((CustomLabel)header_delete).resize();
		
		//header_stud.setBounds(createStudentPane.getX(), delStudentPane.getY()-headerheight, delStudentPane.getWidth(), headerheight);
		//((CustomLabel)header_stud).resize();
		
		header_subj.setBounds(manageSubjectPane.getX(), manageSubjectsButtonPane.getY()-headerheight, manageSubjectPane.getWidth(), headerheight);
		((CustomLabel)header_subj).resize();

	}

	public void reinitializeViewQuestionPane(){
		viewSubjChoices.setSelectedItem(null);
		viewQuestionChoices.setSelectedItem(null);
		viewSelectedSubject = null;
		viewSelectedQuestion = -1;		

		viewqField.setText("");
		viewfchoiceField.setText("");
		viewschoiceField.setText("");
		viewtchoiceField.setText("");
		viewfthchoiceField.setText("");
		viewanswerField.setText("");
		viewdiffField.setText("");		
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
			
			selectedChoice = -1;
			selectedSubject = null;
			selectedDifficulty = null;

			subjChoices.setSelectedItem(null);
			answerField.setSelectedItem(null);
			diffField.setSelectedItem(null);
			qField.setText("");
			fchoiceField.setText("");
			schoiceField.setText("");
			tchoiceField.setText("");
			fthchoiceField.setText("");
			}
			gamesPlayedField.setText("");
			gamesWonField.setText("");
			gamesDrawField.setText("");
			gamesLostField.setText("");
			chemStrField.setText("");
			physStrField.setText("");										
			bioStrField.setText("");	
			studentbox.setSelectedItem(null);		
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
						Student s = new Student(uname, Utilities.hashPassword(pword), fname, lname);
						((Teacher)ANNdroid.simulator.active).createAcct(new Student(uname, Utilities.hashPassword(pword), fname, lname));
						studentbox.addItem(s.getFullname());
					}
					reinitialize(true, false, 0);
				}
				else{
					SoundPlayer click = new SoundPlayer("click.mp3", false, 0);
					click.playSound();					
					reinitialize(false, true, 0);
					createStudentPane.setVisible(false);
				}
			}
			else if(this.state == 1){
				if(this.mode == 1){
					SoundPlayer click = new SoundPlayer("click.mp3", false, 0);
					click.playSound();					
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