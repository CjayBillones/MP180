package ANNdroid.src.panels;

import ANNdroid.src.custom_swing.*;
import ANNdroid.src.*;

import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;

public class StudentPanel extends JPanel{

	JPanel leftSidePane;
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
	JLabel newPword;
	JPasswordField newPwordfield;
	JLabel age;
	JTextField ageField;
	JLabel birthday;
	JTextField birthdayField;
	JLabel gender;
	JTextField genderField;
	JLabel region;
	JTextField regionField;

	// Edit Information //
	JLabel subjectStrength;

	JLabel chemistry;

	JLabel biology;

	JLabel physics;

	JLabel confirmPassword;
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

		nameField = new CustomTextField(new Color(0, 29, 60, 0), false);
		nameField.setBounds(name.getX()+name.getWidth(), name.getY(), viewInformation.getWidth()-name.getWidth(), 20);
		profilePane.add(nameField);

		username = new CustomLabel("Username: ", name.getWidth(), 20, 1);
		username.setBounds(name.getX(), name.getY()+30, name.getWidth(), 20);
		profilePane.add(username);

		usernameField = new CustomTextField(new Color(0, 29, 60, 0), false);
		usernameField.setBounds(username.getX()+username.getWidth(), username.getY(), username.getWidth(), 20);
		profilePane.add(usernameField);

		nickName = new CustomLabel("Nickname: ", name.getWidth(), 20, 1);
		nickName.setBounds(usernameField.getX()+usernameField.getWidth(), username.getY(), username.getWidth(), 20);
		profilePane.add(nickName);

		nickNameField = new CustomTextField(new Color(0, 29, 60, 0), false);
		nickNameField.setBounds(nickName.getX()+nickName.getWidth(), nickName.getY(), nickName.getWidth(), 20);
		profilePane.add(nickNameField);

		birthday = new CustomLabel("Birthday: ", name.getWidth(), 20, 1);
		birthday.setBounds(name.getX(), username.getY()+30, username.getWidth(), 20);
		profilePane.add(birthday);

		birthdayField = new CustomTextField(new Color(0, 29, 60, 0), false);
		birthdayField.setBounds(birthday.getX()+birthday.getWidth(), birthday.getY(), usernameField.getWidth(), 20);
		profilePane.add(birthdayField);

		age = new CustomLabel("Age: ", name.getWidth(), 20, 1);
		age.setBounds(nickName.getX(), birthday.getY(), birthday.getWidth(), 20);
		profilePane.add(age);

		ageField = new CustomTextField(new Color(0, 29, 60, 0), false);
		ageField.setBounds(nickNameField.getX(), age.getY(), nickNameField.getWidth(), 20);
		profilePane.add(ageField);

		gender = new CustomLabel("Gender: ", name.getWidth(), 20, 1);
		gender.setBounds(birthday.getX(), birthday.getY()+30, birthday.getWidth(), 20);
		profilePane.add(gender);

		genderField = new CustomTextField(new Color(0, 29, 60, 0), false);
		genderField.setBounds(birthdayField.getX(), gender.getY(), gender.getWidth(), 20);
		profilePane.add(genderField);

		region = new CustomLabel("Region: ", age.getWidth(), 20, 1);
		region.setBounds(age.getX(), gender.getY(), gender.getWidth(), 20);
		profilePane.add(region);

		regionField = new CustomTextField(new Color(0, 29, 60, 0), false);
		regionField.setBounds(ageField.getX(), region.getY(), region.getWidth(), 20);
		profilePane.add(regionField);

		editInformation = new CustomLabel("Edit Information", profilePane.getWidth() - (profilePane.getWidth()/8), 20, 2);
		editInformation.setBounds(profilePane.getWidth()/26, regionField.getY()+30, profilePane.getWidth() - (profilePane.getWidth()/14), 30);
		profilePane.add(editInformation);	

		add(profilePane);
		add(leftSidePane);
	}

	public void resize(){
		leftSidePane.setBounds(0, 0, getWidth()/4 + 60, getHeight());
		((LeftSidePane)leftSidePane).resize();

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

		editInformation.setBounds(profilePane.getWidth()/26, regionField.getY()+30, profilePane.getWidth() - (profilePane.getWidth()/14), 30);
		((CustomLabel)editInformation).resize();
	}

	public void reinitialize(boolean error, boolean changeMode, int state){

		if(state == 0){
			//if(changeMode || !error) errorLabel1.setText("");
		}
		else if(state == 1){
			//if(changeMode || !error) errorLabel2.setText("");
		}
	}

}