package ANNdroid.src.panels;

import ANNdroid.src.custom_swing.*;
import ANNdroid.src.ai.search.*;
import ANNdroid.src.objects.*;
import ANNdroid.src.util.*;
import ANNdroid.src.*;

import java.util.*;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class StudentPanel extends JPanel{

	public static String[] GENDERS = {"Male", "Female"};
	public static String[] MONTHS = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
	public static String[] REGIONS = {
		"Ilocos Region", "Cagayan Valley", "Central Luzon",
		"CALABARZON", "MIMAROPA", "Bicol Region",
		"Western Visayas", "Central Visayas", "Eastern Visayas",
		"Zamboanga Peninsula", "Northern Mindanao", "Davao Region",
		"SOCCKSARGEN", "Caraga", "Cordillera Administrative Region",
		"Autonomous Region for Muslim Mindanao", "National Capital Region"
	};
	public static Integer[] STRENGTH = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

	JPanel leftSidePane;
	JPanel mapPane;
	JPanel profilePane;

	// Edit Profile Pane //

	JLabel errorLabel2;
	JLabel viewInformation;
	JLabel editInformation;

	// View Information //
	JLabel name;
	JTextField nameField;
	JLabel nickName;
	JTextField nickNameField;
	JLabel username;
	JTextField usernameField;
	JLabel age;
	JTextField ageField;
	JLabel birthday;
	JTextField birthdayField;
	JLabel gender;
	JTextField genderField;
	JLabel region;
	JTextField regionField;

	// Edit Information //
	JLabel nicknameEdit;
	JTextField nicknameEditField;

	JLabel genderEdit;
	JComboBox<String> genderChoice;
	String selectedGender = null;

	JLabel ageEdit;
	JTextField ageEditField;

	JLabel birthdayEdit;
	JComboBox<String> month;
	JComboBox<String> day;
	String selectedMonth = null;
	String selectedDay = null;

	JLabel regionEdit;
	JComboBox<String> regionChoice;
	String selectedRegion = null;

	JLabel subjectStrength;
	JLabel chemistry;
	JComboBox<Integer> chemStr;
	int selectedChemStr = -1;
	JLabel biology;
	JComboBox<Integer> bioStr;
	int selectedBioStr = -1;
	JLabel physics;
	JComboBox<Integer> physStr;
	int selectedPhysStr = -1;

	JLabel oldPword;
	JLabel newPword;
	JLabel confirmPassword;
	JPasswordField oldField;
	JPasswordField newField;
	JPasswordField confirmPasswordField;

	String oldp="";
	String newp="";
	String conp="";

	JButton change;
	JButton cancelbtn;


	public StudentPanel(){
		setLayout(null);

		setPreferredSize(new Dimension(ANNdroid.SCREEN_WIDTH, ANNdroid.SCREEN_HEIGHT));
		setBounds(0, 0, ANNdroid.SCREEN_WIDTH, ANNdroid.SCREEN_HEIGHT);
	
		String labels[] = {"Play", "Instructions", "Statistics", "Account Settings", "Options", "Logout"};

		leftSidePane = new LeftSidePane(this, 2, 6, labels);
		leftSidePane.setPreferredSize(new Dimension(getWidth()/4 + 60, getHeight()));
		leftSidePane.setBounds(0, 0, getWidth()/4 + 60, getHeight());
		leftSidePane.setBackground(Color.BLACK);

		// Map Pane //
		mapPane = new MapBGPanel(getWidth()-leftSidePane.getWidth(), getHeight(), 10, 12);
		mapPane.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
		mapPane.setLayout(new GridLayout(10,12,2,2));
		mapPane.setBounds(leftSidePane.getX()+leftSidePane.getWidth(), 0, getWidth()-leftSidePane.getWidth(), getHeight());
		mapPane.setVisible(false);

		// Profile Pane //
		profilePane = new GenericPane(getWidth() - leftSidePane.getWidth() - 200, getHeight()/3, 1);
		profilePane.setBounds(leftSidePane.getWidth() + 100, getHeight()/4, getWidth() - leftSidePane.getWidth() - 200, getHeight()/3);
		profilePane.setVisible(false);

		errorLabel2 = new CustomLabel("", profilePane.getWidth() - (profilePane.getWidth()/8), 20, 0);
		errorLabel2.setBounds(profilePane.getWidth()/26, profilePane.getHeight()/16, profilePane.getWidth() - (profilePane.getWidth()/8), 40);
		profilePane.add(errorLabel2);	

		viewInformation = new CustomLabel("View Information", profilePane.getWidth() - (profilePane.getWidth()/8), 20, 2);
		viewInformation.setBounds(profilePane.getWidth()/26, profilePane.getHeight()/6, profilePane.getWidth() - (profilePane.getWidth()/14), 30);
		profilePane.add(viewInformation);	

		name = new CustomLabel("Full Name: ", profilePane.getWidth()/6, 20, 1);
		name.setBounds(viewInformation.getX(), viewInformation.getY()+40, getWidth()/6, 20);
		profilePane.add(name);

		nameField = new CustomTextField(new Color(0, 29, 60, 0), false, "");
		nameField.setBounds(name.getX()+name.getWidth(), name.getY(), viewInformation.getWidth()-name.getWidth(), 20);
		profilePane.add(nameField);

		username = new CustomLabel("Username: ", name.getWidth(), 20, 1);
		username.setBounds(name.getX(), name.getY()+30, name.getWidth(), 20);
		profilePane.add(username);

		usernameField = new CustomTextField(new Color(0, 29, 60, 0), false, "");
		usernameField.setBounds(username.getX()+username.getWidth(), username.getY(), username.getWidth(), 20);
		profilePane.add(usernameField);

		nickName = new CustomLabel("Nickname: ", name.getWidth(), 20, 1);
		nickName.setBounds(usernameField.getX()+usernameField.getWidth(), username.getY(), username.getWidth(), 20);
		profilePane.add(nickName);

		nickNameField = new CustomTextField(new Color(0, 29, 60, 0), false, "n/a");
		nickNameField.setBounds(nickName.getX()+nickName.getWidth(), nickName.getY(), nickName.getWidth(), 20);
		profilePane.add(nickNameField);

		birthday = new CustomLabel("Birthday: ", name.getWidth(), 20, 1);
		birthday.setBounds(name.getX(), username.getY()+30, username.getWidth(), 20);
		profilePane.add(birthday);

		birthdayField = new CustomTextField(new Color(0, 29, 60, 0), false, "n/a");
		birthdayField.setBounds(birthday.getX()+birthday.getWidth(), birthday.getY(), usernameField.getWidth(), 20);
		profilePane.add(birthdayField);

		age = new CustomLabel("Age: ", name.getWidth(), 20, 1);
		age.setBounds(nickName.getX(), birthday.getY(), birthday.getWidth(), 20);
		profilePane.add(age);

		ageField = new CustomTextField(new Color(0, 29, 60, 0), false, "n/a");
		ageField.setBounds(nickNameField.getX(), age.getY(), nickNameField.getWidth(), 20);
		profilePane.add(ageField);

		gender = new CustomLabel("Gender: ", name.getWidth(), 20, 1);
		gender.setBounds(birthday.getX(), birthday.getY()+30, birthday.getWidth(), 20);
		profilePane.add(gender);

		genderField = new CustomTextField(new Color(0, 29, 60, 0), false, "n/a");
		genderField.setBounds(birthdayField.getX(), gender.getY(), gender.getWidth(), 20);
		profilePane.add(genderField);

		region = new CustomLabel("Region: ", age.getWidth(), 20, 1);
		region.setBounds(age.getX(), gender.getY(), gender.getWidth(), 20);
		profilePane.add(region);

		regionField = new CustomTextField(new Color(0, 29, 60, 0), false, "n/a");
		regionField.setBounds(ageField.getX(), region.getY(), region.getWidth(), 20);
		profilePane.add(regionField);

		editInformation = new CustomLabel("Edit Information", profilePane.getWidth() - (profilePane.getWidth()/8), 20, 2);
		editInformation.setBounds(profilePane.getWidth()/26, regionField.getY()+30, profilePane.getWidth() - (profilePane.getWidth()/14), 30);
		profilePane.add(editInformation);	

		nicknameEdit = new CustomLabel("Nickname: ", profilePane.getWidth()/3, 20, 1);
		nicknameEdit.setBounds(editInformation.getX(), editInformation.getY()+40, profilePane.getWidth()/6, 20);
		profilePane.add(nicknameEdit);

		nicknameEditField = new CustomTextField(new Color(0, 29, 60, 0), true, "");
		nicknameEditField.setBounds(nicknameEdit.getX()+nicknameEdit.getWidth(), nicknameEdit.getY(), nicknameEdit.getWidth(), 20);
		profilePane.add(nicknameEditField);

		genderEdit = new CustomLabel("Gender: ", nicknameEdit.getWidth(), 20, 1);
		genderEdit.setBounds(nicknameEdit.getX()+(nicknameEdit.getWidth()*2), nicknameEdit.getY(), nicknameEdit.getWidth(), 20);
		profilePane.add(genderEdit);

		genderChoice = new CustomComboBox<String>(GENDERS);
		genderChoice.setBounds(genderEdit.getX()+genderEdit.getWidth(), genderEdit.getY(), genderEdit.getWidth(), 20);
		genderChoice.setSelectedItem(null);
		profilePane.add(genderChoice);
		((JLabel)genderChoice.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		genderChoice.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JComboBox<?> cb = (JComboBox<?>)e.getSource();
				selectedGender = (String)cb.getSelectedItem();
			}
		});

		ageEdit = new CustomLabel("Age: ", genderEdit.getWidth(), 20, 1);
		ageEdit.setBounds(genderChoice.getX()+genderChoice.getWidth(), genderChoice.getY(), genderChoice.getWidth(), 20);
		profilePane.add(ageEdit);

		ageEditField = new CustomTextField(new Color(0, 29, 60, 0), true, "");
		ageEditField.setBounds(ageEdit.getX()+ageEdit.getWidth(), ageEdit.getY(), ageEdit.getWidth()-ageEdit.getWidth()/6, 20);
		profilePane.add(ageEditField);

		birthdayEdit = new CustomLabel("Birthday: ", genderEdit.getWidth()*2, 20, 1);
		birthdayEdit.setBounds(nicknameEdit.getX(), nicknameEdit.getY()+30, genderEdit.getWidth()*2, 20);
		profilePane.add(birthdayEdit);

		month = new CustomComboBox<String>(MONTHS);
		month.setBounds(birthdayEdit.getX()+birthdayEdit.getWidth(), birthdayEdit.getY(), birthdayEdit.getWidth(), 20);
		month.setSelectedItem(null);
		profilePane.add(month);
		((JLabel)month.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

		day = new CustomComboBox<String>();
		day.setBounds(month.getX()+month.getWidth(), month.getY(), ageEdit.getWidth()+ageEditField.getWidth(), 20);
		day.setSelectedItem(null);
		((JLabel)day.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		day.setEnabled(false);
		profilePane.add(day);

		month.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Calendar cal = Calendar.getInstance();
				JComboBox<?> cb = (JComboBox<?>)e.getSource();
				selectedMonth = (String)cb.getSelectedItem();
				day.setEnabled(selectedMonth != null);
				if(selectedMonth != null){
					int calMonth = 0;
					switch(selectedMonth){
						case "January":
							calMonth = Calendar.JANUARY;
							break;
						case "February":
							calMonth = Calendar.FEBRUARY;
							break;
						case "March":
							calMonth = Calendar.MARCH;
							break;
						case "April":
							calMonth = Calendar.APRIL;
							break;
						case "May":
							calMonth = Calendar.MAY;
							break;
						case "June":
							calMonth = Calendar.JUNE;
							break;
						case "July":
							calMonth = Calendar.JULY;
							break;
						case "August":
							calMonth = Calendar.AUGUST;
							break;
						case "September":
							calMonth = Calendar.SEPTEMBER;
							break;
						case "October":
							calMonth = Calendar.OCTOBER;
							break;
						case "November":
							calMonth = Calendar.NOVEMBER;
							break;
						case "December":
							calMonth = Calendar.DECEMBER;
							break;
					}
					day.removeAllItems();
					cal.setTime(new Date());
          cal.set(Calendar.MONTH, calMonth);					
					int min = cal.getActualMinimum(Calendar.DATE);
					int max = cal.getActualMaximum(Calendar.DATE);

					if(selectedMonth == "February" && (((cal.YEAR % 4 == 0) && (cal.YEAR % 100 != 0) || (cal.YEAR % 400 == 0)))) max++;

					for(int days = min; days <= max; days++){
						day.addItem(Integer.toString(days));
					}
					day.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e){
							JComboBox<?> cbd = (JComboBox<?>)e.getSource();
							selectedDay = (String)cbd.getSelectedItem();						
						}
					});
				}
			}
		});

		regionEdit = new CustomLabel("Region: ", birthdayEdit.getWidth(), 20, 1);
		regionEdit.setBounds(birthdayEdit.getX(), birthdayEdit.getY()+30, birthdayEdit.getWidth(), 20);
		profilePane.add(regionEdit);

		regionChoice = new CustomComboBox<String>(REGIONS);
		regionChoice.setBounds(regionEdit.getX()+regionEdit.getWidth(), regionEdit.getY(), month.getWidth()+day.getWidth(), 20);
		regionChoice.setSelectedItem(null);
		profilePane.add(regionChoice);
		((JLabel)regionChoice.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		regionChoice.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JComboBox<?> cb = (JComboBox<?>)e.getSource();
				selectedRegion = (String)cb.getSelectedItem();
			}
		});

		subjectStrength = new CustomLabel("Subject Strength: ", username.getWidth(), 20, 1);
		subjectStrength.setBounds(regionEdit.getX(), regionEdit.getY()+30, username.getWidth(), 20);
		profilePane.add(subjectStrength);

		chemistry = new CustomLabel("Chemistry: ", profilePane.getWidth()/6, 20, 1);
		chemistry.setBounds(subjectStrength.getX()+subjectStrength.getWidth(), subjectStrength.getY(), profilePane.getWidth()/7, 20);
		profilePane.add(chemistry);

		chemStr = new CustomComboBox<Integer>(STRENGTH);
		chemStr.setBounds(chemistry.getX()+chemistry.getWidth(), chemistry.getY(), profilePane.getWidth()/9-chemistry.getWidth()/6, 20);
		profilePane.add(chemStr);
		chemStr.setSelectedItem(null);
		((JLabel)chemStr.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		chemStr.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JComboBox<?> cb = (JComboBox<?>)e.getSource();
				if(cb.getSelectedItem() != null) selectedChemStr = (int)cb.getSelectedItem();
			}
		});

		physics = new CustomLabel("Physics: ", chemistry.getWidth(), 20, 1);
		physics.setBounds(chemStr.getX()+chemStr.getWidth(), chemStr.getY(), chemistry.getWidth(), 20);
		profilePane.add(physics);

		physStr = new CustomComboBox<Integer>(STRENGTH);
		physStr.setBounds(physics.getX()+physics.getWidth(), physics.getY(), chemStr.getWidth(), 20);
		profilePane.add(physStr);
		physStr.setSelectedItem(null);
		((JLabel)physStr.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		physStr.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JComboBox<?> cb = (JComboBox<?>)e.getSource();
				if(cb.getSelectedItem() != null) selectedPhysStr = (int)cb.getSelectedItem();
			}
		});

		biology = new CustomLabel("Biology: ", chemistry.getWidth(), 20, 1);
		biology.setBounds(physStr.getX()+physStr.getWidth(), physStr.getY(), chemistry.getWidth(), 20);
		profilePane.add(biology);

		bioStr = new CustomComboBox<Integer>(STRENGTH);
		bioStr.setBounds(biology.getX()+biology.getWidth(), biology.getY(), chemStr.getWidth(), 20);
		profilePane.add(bioStr);
		bioStr.setSelectedItem(null);
		((JLabel)bioStr.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		bioStr.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JComboBox<?> cb = (JComboBox<?>)e.getSource();
				if(cb.getSelectedItem() != null) selectedBioStr = (int)cb.getSelectedItem();
			}
		});

		oldPword = new CustomLabel("old pword: ", profilePane.getWidth()/4, 20, 1);
		oldPword.setBounds(nicknameEdit.getX(), subjectStrength.getY()+30, profilePane.getWidth()/6, 20);
		profilePane.add(oldPword);

		oldField = new CustomPasswordField(new Color(0, 29, 60, 0));
		oldField.setBounds(oldPword.getX()+oldPword.getWidth(), oldPword.getY(), oldPword.getWidth(), 20);
		profilePane.add(oldField);

		newPword = new CustomLabel("new pword: ", oldPword.getWidth(), 20, 1);
		newPword.setBounds(oldField.getX()+oldField.getWidth(), oldField.getY(), oldPword.getWidth(),20);
		profilePane.add(newPword);

		newField = new CustomPasswordField(new Color(0, 29, 60, 0));
		newField.setBounds(newPword.getX()+newPword.getWidth(), newPword.getY(), newPword.getWidth(), 20);
		profilePane.add(newField);

		confirmPassword = new CustomLabel("Confirmation Password: ", oldPword.getWidth(), 20, 1);
		confirmPassword.setBounds(oldPword.getX(), oldPword.getY()+27, oldPword.getWidth()*2, 20);
		profilePane.add(confirmPassword);

		confirmPasswordField = new CustomPasswordField(new Color(0, 29, 60, 0));
		confirmPasswordField.setBounds(oldPword.getX()+oldPword.getWidth()*2, confirmPassword.getY(), oldPword.getWidth()*2, 20);
		profilePane.add(confirmPasswordField);

		change = new CustomButton("Confirm", profilePane.getWidth()/3, 20);
		change.setBounds(newField.getX()+newField.getWidth()+5, newField.getY(), profilePane.getWidth()/4, 20);
		profilePane.add(change);
		change.addActionListener(e->{

			User u = ANNdroid.simulator.getActiveUser();

			if(!u.getPassword().equals(Utilities.hashPassword(String.valueOf(confirmPasswordField.getPassword())))){
				errorLabel2.setText("* wrong confirmation password. supply correct password to apply changes");
				reinitialize(true, false, 1);
			}
			else{
				boolean err = false;
				try{

					if(!ageEditField.getText().equals("")){
						int new_age = Integer.parseInt(ageEditField.getText());
						if(new_age < 1 || new_age > 100){
							errorLabel2.setForeground(Color.RED);
							errorLabel2.setText("* age field cannot be less than 1 or greater than 100");
							err = true;
						}
					}

					if(!String.valueOf(oldField.getPassword()).equals("") && !String.valueOf(newField.getPassword()).equals("")){
						if(!u.getPassword().equals(Utilities.hashPassword(String.valueOf(oldField.getPassword())))){
							errorLabel2.setForeground(Color.RED);
							errorLabel2.setText("* old password did not match");
							err = true;
						}
						else{
							if(String.valueOf(newField.getPassword()).length() < 6){
									errorLabel2.setForeground(Color.RED);
									errorLabel2.setText("* password must be at least 6 characters long");
									err = true;
							}
						}
					}
					else if(String.valueOf(oldField.getPassword()).equals("") && !String.valueOf(newField.getPassword()).equals("") || (!String.valueOf(oldField.getPassword()).equals("") && String.valueOf(newField.getPassword()).equals(""))){
						errorLabel2.setForeground(Color.RED);
						errorLabel2.setText("* supply both old and new password to change password or leave both blank");
						err = true;
					}

					if(!err){
						if(!ageEditField.getText().equals("")) ((Student)u).setAge(Integer.parseInt(ageEditField.getText()));
						if(!String.valueOf(newField.getPassword()).equals("")) ((Student)u).setPassword(Utilities.hashPassword(String.valueOf(newField.getPassword())));
						if(!nicknameEditField.getText().equals("")) ((Student)u).setNickname(nicknameEditField.getText());
						if(selectedGender != null) ((Student)u).setGender(selectedGender);
						if(selectedMonth != null) ((Student)u).setMonth(selectedMonth);
						if(selectedDay != null) ((Student)u).setDay(selectedDay);
						if(selectedRegion != null) ((Student)u).setRegion(selectedRegion);
						if(selectedChemStr != -1) ((Student)u).setChemistry(selectedChemStr-1);
						if(selectedPhysStr != -1) ((Student)u).setPhysics(selectedPhysStr-1);
						if(selectedBioStr != -1) ((Student)u).setBiology(selectedBioStr-1);
						errorLabel2.setForeground(Color.YELLOW);
						errorLabel2.setText("Successfully Edited Profile");

						if(!((Student)u).isProfileComplete()){
							((LeftSidePane)leftSidePane).btns[0].setEnabled(false);
							((LeftSidePane)leftSidePane).btns[0].setFocusable(false);
						}
						else ((LeftSidePane)leftSidePane).btns[0].setEnabled(true);



						ANNdroid.simulator.saver.saveUsers(ANNdroid.simulator.userList, "ANNdroid/bin/users.bin");
						reinitialize(true, false, 1);		
					}
					else{
						reinitialize(true, false, 1);
					}
				}catch(NumberFormatException nfe){
					errorLabel2.setForeground(Color.RED);
					errorLabel2.setText("* age field must be a number");
					reinitialize(true, false, 1);
				}
			}
		});

		cancelbtn = new CustomButton("Cancel", change.getWidth(), 20);
		cancelbtn.setBounds(change.getX(), change.getY()+27, change.getWidth(), 20);
		profilePane.add(cancelbtn);
		cancelbtn.addActionListener(e->{
			reinitialize(false, true, 1);
			profilePane.setVisible(false);
		});

		add(mapPane);
		add(profilePane);
		add(leftSidePane);
	}

	public void resize(){
		leftSidePane.setBounds(0, 0, getWidth()/4 + 60, getHeight());
		((LeftSidePane)leftSidePane).resize();

		mapPane.setBounds(leftSidePane.getX()+leftSidePane.getWidth(), 0, getWidth()-leftSidePane.getWidth(), getHeight());
		((MapBGPanel)mapPane).resize();

		// Profile Pane //
		profilePane.revalidate();
		profilePane.setBounds(leftSidePane.getWidth(), getHeight()/6, getWidth()-leftSidePane.getWidth()-30, getHeight()/2 + getHeight()/4);
		((GenericPane)profilePane).resize();	

		errorLabel2.setBounds(profilePane.getWidth()/26, profilePane.getHeight()/16, profilePane.getWidth() - (profilePane.getWidth()/8), 40);
		((CustomLabel)errorLabel2).resize();	

		viewInformation.setBounds(profilePane.getWidth()/26, profilePane.getHeight()/6, profilePane.getWidth() - (profilePane.getWidth()/14), 30);
		((CustomLabel)viewInformation).resize();	

		name.setBounds(viewInformation.getX(), viewInformation.getY()+40, getWidth()/6, 20);
		((CustomLabel)name).resize();	

		nameField.setBounds(name.getX()+name.getWidth(), name.getY(), viewInformation.getWidth()-name.getWidth(), 20);

		username.setBounds(name.getX(), name.getY()+30, profilePane.getWidth()/5+profilePane.getWidth()/30, 20);
		((CustomLabel)username).resize();

		usernameField.setBounds(username.getX()+username.getWidth(), username.getY(), username.getWidth(), 20);

		nickName.setBounds(usernameField.getX()+usernameField.getWidth(), username.getY(), username.getWidth(), 20);
		((CustomLabel)nickName).resize();

		nickNameField.setBounds(nickName.getX()+nickName.getWidth(), nickName.getY(), nickName.getWidth(), 20);

		birthday.setBounds(name.getX(), username.getY()+30, username.getWidth(), 20);
		((CustomLabel)birthday).resize();

		birthdayField.setBounds(birthday.getX()+birthday.getWidth(), birthday.getY(), usernameField.getWidth(), 20);

		age.setBounds(nickName.getX(), birthday.getY(), birthday.getWidth(), 20);
		((CustomLabel)age).resize();

		ageField.setBounds(nickNameField.getX(), age.getY(), nickNameField.getWidth(), 20);

		gender.setBounds(birthday.getX(), birthday.getY()+30, birthday.getWidth(), 20);
		((CustomLabel)gender).resize();

		genderField.setBounds(birthdayField.getX(), gender.getY(), gender.getWidth(), 20);

		region.setBounds(age.getX(), gender.getY(), gender.getWidth(), 20);
		((CustomLabel)region).resize();

		regionField.setBounds(ageField.getX(), region.getY(), region.getWidth(), 20);

		// Edit Information //
		editInformation.setBounds(profilePane.getWidth()/26, regionField.getY()+30, profilePane.getWidth() - (profilePane.getWidth()/14), 30);
		((CustomLabel)editInformation).resize();

		nicknameEdit.setBounds(editInformation.getX(), editInformation.getY()+40, profilePane.getWidth()/6, 20);
		((CustomLabel)nicknameEdit).resize();

		nicknameEditField.setBounds(nicknameEdit.getX()+nicknameEdit.getWidth(), nicknameEdit.getY(), nicknameEdit.getWidth(), 20);

		genderEdit.setBounds(nicknameEdit.getX()+(nicknameEdit.getWidth()*2), nicknameEdit.getY(), nicknameEdit.getWidth(), 20);
		((CustomLabel)genderEdit).resize();

		genderChoice.setBounds(genderEdit.getX()+genderEdit.getWidth(), genderEdit.getY(), genderEdit.getWidth(), 20);

		ageEdit.setBounds(genderChoice.getX()+genderChoice.getWidth(), genderChoice.getY(), genderChoice.getWidth(), 20);
		((CustomLabel)ageEdit).resize();

		ageEditField.setBounds(ageEdit.getX()+ageEdit.getWidth(), ageEdit.getY(), ageEdit.getWidth()/2, 20);
		
		birthdayEdit.setBounds(nicknameEdit.getX(), nicknameEdit.getY()+30, genderEdit.getWidth()*2, 20);
		((CustomLabel)birthdayEdit).resize();

		month.setBounds(birthdayEdit.getX()+birthdayEdit.getWidth(), birthdayEdit.getY(), birthdayEdit.getWidth(), 20);

		day.setBounds(month.getX()+month.getWidth(), month.getY(), ageEdit.getWidth()+ageEditField.getWidth(), 20);

		regionEdit.setBounds(birthdayEdit.getX(), birthdayEdit.getY()+30, birthdayEdit.getWidth(), 20);
		((CustomLabel)regionEdit).resize();

		regionChoice.setBounds(regionEdit.getX()+regionEdit.getWidth(), regionEdit.getY(), month.getWidth()+day.getWidth(), 20);

		subjectStrength.setBounds(regionEdit.getX(), regionEdit.getY()+30, username.getWidth(), 20);
		((CustomLabel)subjectStrength).resize();

		chemistry.setBounds(subjectStrength.getX()+subjectStrength.getWidth(), subjectStrength.getY(), profilePane.getWidth()/7, 20);
		((CustomLabel)chemistry).resize();

		chemStr.setBounds(chemistry.getX()+chemistry.getWidth(), chemistry.getY(), profilePane.getWidth()/9-chemistry.getWidth()/6, 20);

		physics.setBounds(chemStr.getX()+chemStr.getWidth(), chemStr.getY(), chemistry.getWidth(), 20);
		((CustomLabel)physics).resize();

		physStr.setBounds(physics.getX()+physics.getWidth(), physics.getY(), chemStr.getWidth(), 20);

		biology.setBounds(physStr.getX()+physStr.getWidth(), physStr.getY(), chemistry.getWidth(), 20);
		((CustomLabel)biology).resize();

		bioStr.setBounds(biology.getX()+biology.getWidth(), biology.getY(), chemStr.getWidth(), 20);

		oldPword.setBounds(nicknameEdit.getX(), subjectStrength.getY()+30, profilePane.getWidth()/6, 20);
		((CustomLabel)oldPword).resize();

		oldField.setBounds(oldPword.getX()+oldPword.getWidth(), oldPword.getY(), oldPword.getWidth(), 20);

		newPword.setBounds(oldField.getX()+oldField.getWidth(), oldField.getY(), oldPword.getWidth(),20);
		((CustomLabel)newPword).resize();

		newField.setBounds(newPword.getX()+newPword.getWidth(), newPword.getY(), newPword.getWidth(), 20);

		confirmPassword.setBounds(oldPword.getX(), oldPword.getY()+27, oldPword.getWidth()*2, 20);
		((CustomLabel)confirmPassword).resize();

		confirmPasswordField.setBounds(oldPword.getX()+oldPword.getWidth()*2, confirmPassword.getY(), oldPword.getWidth()*2, 20);

		change.setBounds(newField.getX()+newField.getWidth()+5, newField.getY(), profilePane.getWidth()/4, 20);
		((CustomButton)change).resize();

		cancelbtn.setBounds(change.getX(), change.getY()+27, change.getWidth(), 20);
		((CustomButton)cancelbtn).resize();

	}

	public void reinitialize(boolean error, boolean changeMode, int state){

		if(state == 0){
			//if(changeMode || !error) errorLabel1.setText("");
		}
		else if(state == 1){
			if(changeMode || !error) errorLabel2.setText("");

			User u = ANNdroid.simulator.getActiveUser(); 

			if(!((Student)u).getNickname().equals("")){
				nickNameField.setText(((Student)u).getNickname());
				nickNameField.setForeground(Color.WHITE);
			}
			if(!((Student)u).getBirthday().equals("")){
				birthdayField.setText(((Student)u).getBirthday());
				birthdayField.setForeground(Color.WHITE);
			}
			if(((Student)u).getAge() != -1){
				ageField.setText(Integer.toString(((Student)u).getAge()));
				ageField.setForeground(Color.WHITE);
			}
			if(!((Student)u).getGender().equals("")){
				genderField.setText(((Student)u).getGender());
				genderField.setForeground(Color.WHITE);
			}
			if(!((Student)u).getRegion().equals("")){
				regionField.setText(((Student)u).getRegion());
				regionField.setForeground(Color.WHITE);
			}

			// Edit Information //
			nicknameEditField.setText("");
			genderChoice.setSelectedItem(null);
			selectedGender = null;
			ageEditField.setText("");
			month.setSelectedItem(null);
			day.setSelectedItem(null);
			selectedMonth = null;
			selectedDay = null;
			regionChoice.setSelectedItem(null);
			selectedRegion = null;
			chemStr.setSelectedItem(null);
			selectedChemStr = -1;
			bioStr.setSelectedItem(null);
			selectedBioStr = -1;
			physStr.setSelectedItem(null);
			selectedPhysStr = -1;
			oldField.setText("");
			newField.setText("");
			confirmPasswordField.setText("");
			oldp="";
			newp="";
			conp="";
		}
	}

}