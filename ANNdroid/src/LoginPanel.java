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
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel{

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

		setLayout(null);
		setPreferredSize(new Dimension(ANNdroid.SCREEN_WIDTH, ANNdroid.SCREEN_HEIGHT));
		setBounds(0, 0, ANNdroid.SCREEN_WIDTH, ANNdroid.SCREEN_HEIGHT);

		loginPane = new LoginPane(getWidth()/2, getHeight()/4);
		loginPane.setBounds(getWidth()/4, getHeight()/3, getWidth()/2, getHeight()/4);		
		
		errorLabel = new CustomLabel("", loginPane.getWidth()/2, 20, 0);
		errorLabel.setBounds(loginPane.getWidth()/4, loginPane.getHeight()/3 - 30, loginPane.getWidth()/2, 20);
		errorLabel.setForeground(Color.RED);
		loginPane.add(errorLabel);

		unameLabel = new CustomLabel("username: ", loginPane.getWidth()/5, 20, 1);
		unameLabel.setBounds(errorLabel.getX(), loginPane.getHeight()/3, loginPane.getWidth()/5, 20);
		unameLabel.setForeground(Color.WHITE);
		loginPane.add(unameLabel);

		unameField = new JTextField();
		unameField.setBounds(unameLabel.getX() + unameLabel.getWidth(), unameLabel.getY(), errorLabel.getWidth()-unameLabel.getWidth(), 20);
		loginPane.add(unameField);
		unameField.addFocusListener(new FieldFocusListener(0));
		unameField.addActionListener(event -> {pwordField.requestFocus();});

		pwordLabel = new CustomLabel("password: ", loginPane.getWidth()/5, 20, 1);
		pwordLabel.setBounds(loginPane.getWidth()/4, unameLabel.getY() + 30, loginPane.getWidth()/5, 20);
		pwordLabel.setForeground(Color.WHITE);
		loginPane.add(pwordLabel);

		pwordField = new JPasswordField();
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
		exitBtn.addActionListener(event->{System.exit(1);});
		loginPane.add(exitBtn);

		add(loginPane);
		add(bgPanel);
		
	}

	public void resize(){
		loginPane.setBounds(getWidth()/4, getHeight()/3, getWidth()/2, getHeight()/4);
		((LoginPane)loginPane).resize();

		errorLabel.setBounds(loginPane.getWidth()/4, loginPane.getHeight()/3 - 30, loginPane.getWidth()/2, 20);
		((CustomLabel)errorLabel).resize();

		unameLabel.setBounds(errorLabel.getX(), loginPane.getHeight()/3, loginPane.getWidth()/5, 20);
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
			if(this.mode == 0) unameLogin = unameField.getText();
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
				reinitialize(true);
				unameField.requestFocus();
			}
			else if(u != null && !u.getPassword().equals(pwordLogin)){
				errorLabel.setText("* incorrect password");
				reinitialize(true);
				unameField.requestFocus();
			}
			else{

				remove(ANNdroid.bgPanel);

				ANNdroid.simulator.active = u;
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
				}
			}
		}
	}
}