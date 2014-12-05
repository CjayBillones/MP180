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

		loginPane = new JPanel(null);
		loginPane.setPreferredSize(new Dimension(getWidth()/2, getHeight()/4));
		loginPane.setBounds(getWidth()/4, getHeight()/3, getWidth()/2, getHeight()/4);

		errorLabel = new JLabel("");
		errorLabel.setBounds(loginPane.getWidth()/4, loginPane.getHeight()/3 - 30, loginPane.getWidth()/2, 20);
		errorLabel.setForeground(Color.RED);
		errorLabel.setBackground(Color.BLACK);
		errorLabel.setOpaque(true);
		loginPane.add(errorLabel);

		unameLabel = new JLabel("username: ", SwingConstants.CENTER);
		unameLabel.setBounds(errorLabel.getX(), loginPane.getHeight()/3, loginPane.getWidth()/5, 20);
		unameLabel.setForeground(Color.WHITE);
		unameLabel.setBackground(Color.BLACK);
		unameLabel.setOpaque(true);
		loginPane.add(unameLabel);

		unameField = new JTextField();
		unameField.setBounds(unameLabel.getX() + unameLabel.getWidth(), unameLabel.getY(), errorLabel.getWidth()-unameLabel.getWidth(), 20);
		loginPane.add(unameField);
		unameField.addFocusListener(new FieldFocusListener(0));
		unameField.addActionListener(event -> {pwordField.requestFocus();});

		pwordLabel = new JLabel("password: ", SwingConstants.CENTER);
		pwordLabel.setBounds(loginPane.getWidth()/4, unameLabel.getY() + 30, loginPane.getWidth()/5, 20);
		pwordLabel.setForeground(Color.WHITE);
		pwordLabel.setBackground(Color.BLACK);
		pwordLabel.setOpaque(true);
		loginPane.add(pwordLabel);

		pwordField = new JPasswordField();
		pwordField.setBounds(pwordLabel.getX() + pwordLabel.getWidth(), pwordLabel.getY(), errorLabel.getWidth()-pwordLabel.getWidth(), 20);
		loginPane.add(pwordField);
		pwordField.addFocusListener(new FieldFocusListener(1));
		pwordField.addActionListener(new LoginAction(0));

		loginBtn = new JButton("login");
		loginBtn.setBounds(errorLabel.getX(), pwordLabel.getY() + 30, errorLabel.getWidth()/2, loginPane.getHeight()/7);
		loginPane.add(loginBtn);
		loginBtn.addActionListener(new LoginAction(1));

		exitBtn = new JButton("exit");
		exitBtn.setBounds(loginBtn.getX() + loginBtn.getWidth(), pwordLabel.getY() + 30, loginBtn.getWidth(), loginPane.getHeight()/7);
		loginPane.add(exitBtn);
		exitBtn.addActionListener(event->{System.exit(1);});

		add(loginPane);
		add(bgPanel);
		
	}

	public void resize(){
		loginPane.setBounds(getWidth()/4, getHeight()/3, getWidth()/2, getHeight()/4);
		errorLabel.setBounds(loginPane.getWidth()/4, loginPane.getHeight()/3 - 30, loginPane.getWidth()/2, 20);
		unameLabel.setBounds(errorLabel.getX(), loginPane.getHeight()/3 , loginPane.getWidth()/5, 20);
		unameField.setBounds(unameLabel.getX() + unameLabel.getWidth(), unameLabel.getY(), errorLabel.getWidth()-unameLabel.getWidth(), 20);
		pwordLabel.setBounds(loginPane.getWidth()/4, unameLabel.getY() + 30, loginPane.getWidth()/5, 20);
		pwordField.setBounds(pwordLabel.getX() + pwordLabel.getWidth(), pwordLabel.getY(), errorLabel.getWidth()-pwordLabel.getWidth(), 20);
		loginBtn.setBounds(errorLabel.getX(), pwordLabel.getY() + 30, errorLabel.getWidth()/2, loginPane.getHeight()/7);
		exitBtn.setBounds(loginBtn.getX() + loginBtn.getWidth(), pwordLabel.getY() + 30, loginBtn.getWidth(), loginPane.getHeight()/7);
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