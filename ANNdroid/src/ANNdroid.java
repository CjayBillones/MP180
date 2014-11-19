package ANNdroid.src;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.Toolkit;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.CardLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;

public class ANNdroid extends JFrame{

	public static final Toolkit TK = Toolkit.getDefaultToolkit();

	public static final int SCREEN_WIDTH = 800;
	public static final int SCREEN_HEIGHT = 600;
	public static final int SCREEN_XPOS = TK.getScreenSize().width/4;
	public static final int SCREEN_YPOS = TK.getScreenSize().height/8;

	public static Simulator simulator;

	// Card Layout //
	public static JPanel cards;
	public final static String LOGINPANEL = "login";
	public final static String ADMINPANEL = "admin";

	// Cards/Panels //
	public static JPanel bgPanel;
	public static JPanel loginPanel;
	public static JPanel adminPanel;

	public ANNdroid(){

		super("ANNdroid");
		simulator = new Simulator();

		// Frame Properties //
		setResizable(true);
		setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		setMinimumSize(new Dimension(800, 600));
		setLocation(SCREEN_WIDTH/3, SCREEN_HEIGHT/8);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		addComponentToPane(this.getContentPane());
		addComponentListener(new ResizeListener());

		// Show Frame //
		pack();
		setVisible(true);
	}

	public void addComponentToPane(Container c){

		// Initialize Cards //
		bgPanel = new BackgroundPanel();
		loginPanel = new LoginPanel(bgPanel);
		adminPanel = new AdminPanel(bgPanel);

		// Add Cards/Panel to Cards Container //
		cards = new JPanel(new CardLayout());
		cards.add(loginPanel, LOGINPANEL);
		cards.add(adminPanel, ADMINPANEL);
	
		c.add(cards);
	}

	public static void main(String args[]){
		SwingUtilities.invokeLater(()->new ANNdroid());
	}

	// Resizes the Components //
	private class ResizeListener extends ComponentAdapter{
		public void componentResized(ComponentEvent e){
			bgPanel.setBounds(0, 0, getWidth(), getHeight()); // Resizes the bgPanel so that the resize() will execute // 
			loginPanel.setBounds(0, 0, getWidth(), getHeight());
			((BackgroundPanel)bgPanel).resize(); // Resizes the bgImage //
			((LoginPanel)loginPanel).resize();
		}
	}
}

