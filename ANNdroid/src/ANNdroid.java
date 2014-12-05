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
	public final static String TEACHERPANEL = "teacher";
	public final static String STUDENTPANEL = "student";

	// Cards/Panels //
	public static JPanel bgPanel;
	public static JPanel loginPanel;
	public static JPanel adminPanel;
	public static JPanel teacherPanel;
	public static JPanel studentPanel;

	public ANNdroid(){

		super("ANNdroid");
		simulator = new Simulator();

		// Frame Properties //
		//setUndecorated(true);
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
		adminPanel = new AdminPanel();
		teacherPanel = new TeacherPanel();
		studentPanel = new StudentPanel();

		// Add Cards/Panel to Cards Container //
		cards = new JPanel(new CardLayout());
		cards.add(loginPanel, LOGINPANEL);
		cards.add(adminPanel, ADMINPANEL);
		cards.add(teacherPanel, TEACHERPANEL);
		cards.add(studentPanel, STUDENTPANEL);
	
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
			adminPanel.setBounds(0, 0, getWidth(), getHeight());
			teacherPanel.setBounds(0, 0, getWidth(), getHeight());
			studentPanel.setBounds(0, 0, getWidth(), getHeight());
			((BackgroundPanel)bgPanel).resize(); // Resizes the bgImage //
			((LoginPanel)loginPanel).resize();
			((AdminPanel)adminPanel).resize();
			((TeacherPanel)teacherPanel).resize();
			((StudentPanel)studentPanel).resize();
		}
	}
}

