package ANNdroid.src;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Dimension;

public class LoginPanel extends JPanel{

	JPanel loginPane;
	JLabel errorLabel;
	JLabel unameLabel;
	JLabel pwordLabel;
	JTextField unameField;
	JPasswordField pwordField;
	JButton loginBtn;
	JButton exitBtn;

	public LoginPanel(JPanel bgPanel){

		setLayout(null);
		setPreferredSize(new Dimension(ANNdroid.SCREEN_WIDTH, ANNdroid.SCREEN_HEIGHT));
		setBounds(0, 0, ANNdroid.SCREEN_WIDTH, ANNdroid.SCREEN_HEIGHT);

		loginPane = new JPanel(null);
		loginPane.setPreferredSize(new Dimension(getWidth()/2, getHeight()/4));
		loginPane.setBounds(getWidth()/4, getHeight()/3, getWidth()/2, getHeight()/4);

		errorLabel = new JLabel("");
		errorLabel.setBounds(loginPane.getWidth()/4, loginPane.getHeight()/3 - 10, loginPane.getWidth()/2, 20);
		errorLabel.setForeground(Color.WHITE);
		errorLabel.setBackground(Color.BLACK);
		errorLabel.setOpaque(true);
		loginPane.add(errorLabel);

		unameLabel = new JLabel("username: ", SwingConstants.CENTER);
		unameLabel.setBounds(errorLabel.getX(), loginPane.getHeight()/3 + 15, loginPane.getWidth()/5, 20);
		unameLabel.setForeground(Color.WHITE);
		unameLabel.setBackground(Color.BLACK);
		unameLabel.setOpaque(true);
		loginPane.add(unameLabel);

		unameField = new JTextField();
		unameField.setBounds(unameLabel.getX() + unameLabel.getWidth(), unameLabel.getY(), errorLabel.getWidth()-unameLabel.getWidth(), 20);
		loginPane.add(unameField);

		pwordLabel = new JLabel("password: ", SwingConstants.CENTER);
		pwordLabel.setBounds(loginPane.getWidth()/4, unameLabel.getY() + 30, loginPane.getWidth()/5, 20);
		pwordLabel.setForeground(Color.WHITE);
		pwordLabel.setBackground(Color.BLACK);
		pwordLabel.setOpaque(true);
		loginPane.add(pwordLabel);

		pwordField = new JPasswordField();
		pwordField.setBounds(pwordLabel.getX() + pwordLabel.getWidth(), pwordLabel.getY(), errorLabel.getWidth()-pwordLabel.getWidth(), 20);
		loginPane.add(pwordField);

		loginBtn = new JButton("login");
		loginBtn.setBounds(errorLabel.getX(), pwordLabel.getY() + 30, errorLabel.getWidth()/2, loginPane.getHeight()/7);
		loginPane.add(loginBtn);

		exitBtn = new JButton("exit");
		exitBtn.setBounds(loginBtn.getX() + loginBtn.getWidth(), pwordLabel.getY() + 30, loginBtn.getWidth(), loginPane.getHeight()/7);
		loginPane.add(exitBtn);
		exitBtn.addActionListener(event->{System.exit(1);});

		add(loginPane);
		add(bgPanel);
		
	}

	public void resize(){
		loginPane.setBounds(getWidth()/4, getHeight()/3, getWidth()/2, getHeight()/4);
		errorLabel.setBounds(loginPane.getWidth()/4, loginPane.getHeight()/3 - 10, loginPane.getWidth()/2, 20);
		unameLabel.setBounds(errorLabel.getX(), loginPane.getHeight()/3 + 15, loginPane.getWidth()/5, 20);
		unameField.setBounds(unameLabel.getX() + unameLabel.getWidth(), unameLabel.getY(), errorLabel.getWidth()-unameLabel.getWidth(), 20);
		pwordLabel.setBounds(loginPane.getWidth()/4, unameLabel.getY() + 30, loginPane.getWidth()/5, 20);
		pwordField.setBounds(pwordLabel.getX() + pwordLabel.getWidth(), pwordLabel.getY(), errorLabel.getWidth()-pwordLabel.getWidth(), 20);
		loginBtn.setBounds(errorLabel.getX(), pwordLabel.getY() + 30, errorLabel.getWidth()/2, loginPane.getHeight()/7);
		exitBtn.setBounds(loginBtn.getX() + loginBtn.getWidth(), pwordLabel.getY() + 30, loginBtn.getWidth(), loginPane.getHeight()/7);
	}
}