package ANNdroid.src;

import java.util.LinkedList;

import java.io.IOException;

import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.SwingUtilities;
import javax.swing.JPanel;
import java.awt.Container;

import java.awt.BorderLayout;
import java.awt.CardLayout;

public class ANNdroid extends JFrame{

	public static final Toolkit TK = Toolkit.getDefaultToolkit();

	public static final int SCREEN_XPOS = TK.getScreenSize().width/4;
	public static final int SCREEN_YPOS = TK.getScreenSize().height/8;
	//public static final int SCREEN_WIDTH = TK.getScreenSize().width;
	//public static final int SCREEN_HEIGHT = TK.getScreenSize().height;
  public static final int SCREEN_WIDTH = 800;
  public static final int SCREEN_HEIGHT = 600;

	public static Container c;
	public static JPanel framework;

	public static Loader loader;
	public static Saver saver;

	public static LinkedList<User> userList;

	public ANNdroid(){
		super("ANNdroid");
    System.out.println(TK.getScreenSize());
		initialize();

		c = this.getContentPane();
		c.setLayout(new BorderLayout());

		framework = new Framework(new CardLayout());
		c.add(framework);

		this.setLocation(SCREEN_XPOS, SCREEN_YPOS);
		this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void initialize(){
		loader = new Loader();
		saver = new Saver();
		
		try{
			userList = new LinkedList<User>();
			userList = loader.loadUsers("ANNdroid/bin/users.bin");
    }catch(IOException e){
			e.printStackTrace();
		}
	}

	public static void main(String args[]){
    SwingUtilities.invokeLater(()-> new ANNdroid());
	}

}