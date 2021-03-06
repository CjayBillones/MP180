package ANNdroid.src.panels;

import ANNdroid.src.custom_swing.*;
import ANNdroid.src.objects.*;
import ANNdroid.src.events.*;
import ANNdroid.src.util.*;
import ANNdroid.src.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class LoginPanel extends JPanel{

	SoundPlayer bgmx;

	JPanel loginPane;

	JLabel errorLabel;
	JLabel unameLabel;
	JLabel pwordLabel;
	JTextField unameField;
	JPasswordField pwordField;
	JButton loginBtn;
	JButton exitBtn;

	String unameLogin;
	String pwordLogin;

	public LoginPanel(JPanel bgPanel){

		bgmx = new SoundPlayer("pso2_1.mp3", true, 1300);
		bgmx.playSound();

		setLayout(null);
		setPreferredSize(new Dimension(ANNdroid.SCREEN_WIDTH, ANNdroid.SCREEN_HEIGHT));
		setBounds(0, 0, ANNdroid.SCREEN_WIDTH, ANNdroid.SCREEN_HEIGHT);

		loginPane = new GenericPane(getWidth()/2, getHeight()/4, 0);
		loginPane.setBounds(getWidth()/4, getHeight()/3, getWidth()/2, getHeight()/4);
		
		errorLabel = new CustomLabel("", loginPane.getWidth()/2, 20, 0);
		errorLabel.setBounds(loginPane.getWidth()/4, loginPane.getHeight()/3 - 30, loginPane.getWidth()/2, 20);
		errorLabel.setForeground(Color.RED);
		errorLabel.setVisible(false);
		loginPane.add(errorLabel);

		unameLabel = new CustomLabel("username: ", loginPane.getWidth()/5, 20, 1);
		unameLabel.setBounds(errorLabel.getX(), errorLabel.getY() + 25, loginPane.getWidth()/5, 20);
		unameLabel.setForeground(Color.WHITE);
		loginPane.add(unameLabel);

		unameField = new CustomTextField(new Color(0, 29, 60, 0), true, "");
		unameField.setBounds(unameLabel.getX() + unameLabel.getWidth(), unameLabel.getY(), errorLabel.getWidth()-unameLabel.getWidth(), 20);
		loginPane.add(unameField);
		unameField.addFocusListener(new FieldFocusListener(0));
		unameField.addActionListener(event -> {pwordField.requestFocus();});

		pwordLabel = new CustomLabel("password: ", loginPane.getWidth()/5, 20, 1);
		pwordLabel.setBounds(loginPane.getWidth()/4, unameLabel.getY() + 30, loginPane.getWidth()/5, 20);
		pwordLabel.setForeground(Color.WHITE);
		loginPane.add(pwordLabel);

		pwordField = new CustomPasswordField(new Color(0, 29, 60, 0));
		pwordField.setBounds(pwordLabel.getX() + pwordLabel.getWidth(), pwordLabel.getY(), errorLabel.getWidth()-pwordLabel.getWidth(), 20);
		loginPane.add(pwordField);
		pwordField.addFocusListener(new FieldFocusListener(1));
		pwordField.addActionListener(new LoginAction(0));

		loginBtn = new CustomButton("login", errorLabel.getWidth()/2, loginPane.getHeight()/7);
		loginBtn.setBounds(errorLabel.getX(), pwordLabel.getY() + 30, errorLabel.getWidth()/2, loginPane.getHeight()/7);
		loginBtn.setContentAreaFilled(false);
		loginBtn.addActionListener(new LoginAction(1));
		loginPane.add(loginBtn);
		
		exitBtn = new CustomButton("exit", loginBtn.getWidth(), loginPane.getHeight()/7);
		exitBtn.setBounds(loginBtn.getX() + loginBtn.getWidth(), loginBtn.getY(), loginBtn.getWidth(), loginPane.getHeight()/7);
		exitBtn.setContentAreaFilled(false);				
		exitBtn.addActionListener(event->{
				SoundPlayer click = new SoundPlayer("click.mp3", false, 0);
				click.playSound();
			System.exit(1);
		});
		loginPane.add(exitBtn);

		add(loginPane);
		add(bgPanel);
		
	}

	public void resize(){
		loginPane.setBounds(getWidth()/4, getHeight()/3, getWidth()/2, getHeight()/4);
		((GenericPane)loginPane).resize();

		errorLabel.setBounds(loginPane.getWidth()/4, loginPane.getHeight()/3 - 30, loginPane.getWidth()/2, 20);
		((CustomLabel)errorLabel).resize();

		unameLabel.setBounds(errorLabel.getX(), errorLabel.getY() + 25, loginPane.getWidth()/5, 20);
		((CustomLabel)unameLabel).resize();
		
		unameField.setBounds(unameLabel.getX() + unameLabel.getWidth(), unameLabel.getY(), errorLabel.getWidth()-unameLabel.getWidth(), 20);

		pwordLabel.setBounds(loginPane.getWidth()/4, unameLabel.getY() + 30, loginPane.getWidth()/5, 20);
		((CustomLabel)pwordLabel).resize();
	
		pwordField.setBounds(pwordLabel.getX() + pwordLabel.getWidth(), pwordLabel.getY(), errorLabel.getWidth()-pwordLabel.getWidth(), 20);
		
		loginBtn.setBounds(errorLabel.getX(), pwordLabel.getY() + 30, errorLabel.getWidth()/2, loginPane.getHeight()/7);
		((CustomButton)loginBtn).resize();

		exitBtn.setBounds(loginBtn.getX() + loginBtn.getWidth(), loginBtn.getY(), loginBtn.getWidth(), loginPane.getHeight()/7);
		((CustomButton)exitBtn).resize();		
	}

	public void reinitialize(boolean error){
		if(!error) errorLabel.setText("");
		unameField.setText("");
		pwordField.setText("");
		unameLogin = "";
		pwordLogin = "";
	}

	private class FieldFocusListener extends FocusAdapter{

		// mode: 0 - unameField //
		// mode: 1 - pwordField //
		int mode;

		public FieldFocusListener(int mode){
			this.mode = mode;
		}

		public void focusLost(FocusEvent e){
			if(this.mode == 0)
				unameLogin = unameField.getText();
			else if(this.mode == 1){
				pwordLogin = Utilities.hashPassword(String.valueOf(pwordField.getPassword()));
				unameField.requestFocus();
			} 
		}

	}

	private class LoginAction implements ActionListener{

		// mode: 0 - pressed enter on password field //
		// mode: 1 - login button //
		int mode;

		public LoginAction(int mode){
			this.mode = mode;
		}

		public void actionPerformed(ActionEvent e){

			if(this.mode == 0) pwordLogin = Utilities.hashPassword(String.valueOf(pwordField.getPassword()));
			
			User u = Utilities.authenticate(unameLogin);
			
			if(u == null){
				errorLabel.setText("* user does not exist");
				errorLabel.setVisible(true);
				reinitialize(true);
				unameField.requestFocus();
			}
			else if(u != null && !u.getPassword().equals(pwordLogin)){
				errorLabel.setText("* incorrect password");
				errorLabel.setVisible(true);
				reinitialize(true);
				unameField.requestFocus();
			}
			else{
				errorLabel.setVisible(false);
				remove(ANNdroid.bgPanel);

				SoundPlayer loginSound= new SoundPlayer("click.mp3", false, 0);
				loginSound.playSound();

				ANNdroid.simulator.setActiveUser(u);
				if(u instanceof Admin){
					ANNdroid.adminPanel.add(ANNdroid.bgPanel);
					CardLayout c1 = (CardLayout)(ANNdroid.cards.getLayout());
					c1.show(ANNdroid.cards, ANNdroid.ADMINPANEL);
					reinitialize(false);
				}
				else if(u instanceof Teacher){
					ANNdroid.teacherPanel.add(ANNdroid.bgPanel);
					CardLayout c1 = (CardLayout)(ANNdroid.cards.getLayout());
					c1.show(ANNdroid.cards, ANNdroid.TEACHERPANEL);
					reinitialize(false);
				}
				else if(u instanceof Student){
					ANNdroid.studentPanel.add(ANNdroid.bgPanel);
					CardLayout c1 = (CardLayout)(ANNdroid.cards.getLayout());
					c1.show(ANNdroid.cards, ANNdroid.STUDENTPANEL);
					reinitialize(false);

					((StudentPanel)ANNdroid.studentPanel).nameField.setText(u.getFullname());
					((StudentPanel)ANNdroid.studentPanel).usernameField.setText(u.getUsername());
	
					if(!((Student)u).isProfileComplete()){
						((LeftSidePane)((StudentPanel)ANNdroid.studentPanel).leftSidePane).btns[0].setEnabled(false);
						((LeftSidePane)((StudentPanel)ANNdroid.studentPanel).leftSidePane).btns[0].setFocusable(false);
					}
					else ((LeftSidePane)((StudentPanel)ANNdroid.studentPanel).leftSidePane).btns[0].setEnabled(true);

					if(!((Student)u).getNickname().equals("")){
						((StudentPanel)ANNdroid.studentPanel).nickNameField.setText(((Student)u).getNickname());
						((StudentPanel)ANNdroid.studentPanel).nickNameField.setForeground(Color.WHITE);
					}
					if(!((Student)u).getBirthday().equals("")){
						((StudentPanel)ANNdroid.studentPanel).birthdayField.setText(((Student)u).getBirthday());
						((StudentPanel)ANNdroid.studentPanel).birthdayField.setForeground(Color.WHITE);
					}
					if(((Student)u).getAge() != -1){
						((StudentPanel)ANNdroid.studentPanel).ageField.setText(Integer.toString(((Student)u).getAge()));
						((StudentPanel)ANNdroid.studentPanel).ageField.setForeground(Color.WHITE);
					}
					if(!((Student)u).getGender().equals("")){
						((StudentPanel)ANNdroid.studentPanel).genderField.setText(((Student)u).getGender());
						((StudentPanel)ANNdroid.studentPanel).genderField.setForeground(Color.WHITE);
					}
					if(!((Student)u).getRegion().equals("")){
						((StudentPanel)ANNdroid.studentPanel).regionField.setText(((Student)u).getRegion());
						((StudentPanel)ANNdroid.studentPanel).regionField.setForeground(Color.WHITE);
					}

					if(((Student)ANNdroid.simulator.getActiveUser()).getChemistry() != -1){
						((StudentPanel)ANNdroid.studentPanel).chemStr.setSelectedItem(((Student)ANNdroid.simulator.getActiveUser()).getChemistry()+1);
						((StudentPanel)ANNdroid.studentPanel).chemStr.setEnabled(false);
						((StudentPanel)ANNdroid.studentPanel).chemStrField.setText(Integer.toString(((Student)u).getChemistry()));
					}
					else{
						((StudentPanel)ANNdroid.studentPanel).chemStr.setSelectedItem(null);
						((StudentPanel)ANNdroid.studentPanel).chemStr.setEnabled(true);
						((StudentPanel)ANNdroid.studentPanel).chemStrField.setText("n/a");
					}
					if(((Student)ANNdroid.simulator.getActiveUser()).getPhysics() != -1){
						((StudentPanel)ANNdroid.studentPanel).physStr.setSelectedItem(((Student)ANNdroid.simulator.getActiveUser()).getPhysics()+1);
						((StudentPanel)ANNdroid.studentPanel).physStr.setEnabled(false);
						((StudentPanel)ANNdroid.studentPanel).physStrField.setText(Integer.toString(((Student)u).getPhysics()));										
					}
					else{
						((StudentPanel)ANNdroid.studentPanel).physStr.setSelectedItem(null);
						((StudentPanel)ANNdroid.studentPanel).physStr.setEnabled(true);
						((StudentPanel)ANNdroid.studentPanel).physStrField.setText("n/a");				
					}
					if(((Student)ANNdroid.simulator.getActiveUser()).getBiology() != -1){
						((StudentPanel)ANNdroid.studentPanel).bioStr.setSelectedItem(((Student)ANNdroid.simulator.getActiveUser()).getBiology()+1);
						((StudentPanel)ANNdroid.studentPanel).bioStr.setEnabled(false);
						((StudentPanel)ANNdroid.studentPanel).bioStrField.setText(Integer.toString(((Student)u).getBiology()));		
					}
					else{
						((StudentPanel)ANNdroid.studentPanel).bioStr.setSelectedItem(null);
						((StudentPanel)ANNdroid.studentPanel).bioStr.setEnabled(true);
						((StudentPanel)ANNdroid.studentPanel).bioStrField.setText("n/a");
					}

					((StudentPanel)ANNdroid.studentPanel).gamesPlayedField.setText(Integer.toString(((Student)u).getGamesPlayed()));
					((StudentPanel)ANNdroid.studentPanel).gamesWonField.setText(Integer.toString(((Student)u).getGamesWon()));
					((StudentPanel)ANNdroid.studentPanel).gamesDrawField.setText(Integer.toString(((Student)u).getGamesDraw()));
					((StudentPanel)ANNdroid.studentPanel).gamesLostField.setText(Integer.toString(((Student)u).getGamesLost()));						
				}
			}
		}
	}
}