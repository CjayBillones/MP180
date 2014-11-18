package ANNdroid.src;

import java.lang.String;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLayeredPane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.CardLayout;

public class LoginPanel extends JPanel{

	private JLayeredPane jlp;
	private JPanel loginPanel;
	private JLabel errorLabel;
	private JLabel unameLabel;
	private JLabel pwordLabel;
	private JTextField unameField;
	private JPasswordField pwordField;
	private JButton loginBtn;
	private JButton exitBtn;

	private String unameLogin = "";
	private String pwordLogin = "";

	public LoginPanel(){

		setLayout(null);
		setPreferredSize(new Dimension(ANNdroid.SCREEN_WIDTH/2, ANNdroid.SCREEN_HEIGHT/2));
		setBounds(getWidth()/2, getHeight()/3, getWidth(), getHeight());

		//jlp = new JLayeredPane();
		//jlp.setPreferredSize(new Dimension(800, 600));
		//jlp.setBounds(0, 0, 800, 600);

		errorLabel = new JLabel("");
		errorLabel.setBounds(260, 180, 280, 25);
		errorLabel.setForeground(Color.RED);
		errorLabel.setFont(errorLabel.getFont().deriveFont(11f));
		
		unameLabel = new JLabel("username: ");
		unameLabel.setBounds(260, 210, 80, 20);
		unameLabel.setForeground(Color.WHITE);
		
		unameField = new JTextField("", 15);
		unameField.setBounds(340, 210, 200, 25);
		unameField.addFocusListener(new FocusListener(){
			
			public void focusGained(FocusEvent e){} //Do nothing

			public void focusLost(FocusEvent e){
				unameLogin = unameField.getText();
			}
		});

		pwordLabel = new JLabel("password: ");
		pwordLabel.setBounds(260, 250, 80, 20);
		pwordLabel.setForeground(Color.WHITE);

		pwordField = new JPasswordField("" ,15);
		pwordField.setBounds(340, 250, 200, 25);
		pwordField.addFocusListener(new FocusListener(){
			
			public void focusGained(FocusEvent e){} //Do nothing

			public void focusLost(FocusEvent e){
				pwordLogin = String.valueOf(pwordField.getPassword());
			}
		});

		loginBtn = new JButton("login");
		loginBtn.setBounds(400, 290, 70, 20);
		/**loginBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){

				User u = Utilities.authenticate(unameLogin);
				
				if(u == null)
					errorLabel.setText("* user does not exist");
				else if(u != null && !u.getPassword().equals(Utilities.hashPassword(pwordLogin)))
					errorLabel.setText("* incorrect password");
				else{
					Framework.gameState = Framework.GameState.ADMIN;
					((AdminPanel)Framework.adminPanel).setUser(u);
					reinitialize();
				}
			}
		});**/
		loginBtn.addActionListener(event -> { 
			User u = Utilities.authenticate(unameLogin);
			
			if(u == null) errorLabel.setText("* user does not exist");
			else if(u != null && !u.getPassword().equals(Utilities.hashPassword(pwordLogin)))
				errorLabel.setText("* incorrect password");
			else{
				Framework.gameState = Framework.GameState.ADMIN;
				((AdminPanel)Framework.adminPanel).setUser(u);
				reinitialize();
			}
		});

		exitBtn = new JButton("exit");
		exitBtn.setBounds(478, 290, 60, 20);
		/**exitBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(1);
			}
		});**/
		exitBtn.addActionListener(event -> System.exit(1));

		//jlp.add(errorLabel, 0);
		//jlp.add(unameLabel, 0);
		//jlp.add(unameField, 0);
		//jlp.add(pwordLabel, 0);
		//jlp.add(pwordField, 0);
		//jlp.add(loginBtn, 	0);
		//jlp.add(exitBtn,		0);
		//this.add(jlp);
	}

	public void reinitialize(){
		this.unameLogin = "";
		this.pwordLogin = "";
		errorLabel.setText("");
		unameField.setText("");
		pwordField.setText("");
	}
}