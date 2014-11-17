package ANNdroid.src;

import java.io.File;

import javax.swing.JPanel;
import javax.imageio.ImageIO;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.event.ComponentListener;
import java.awt.event.ComponentEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class Framework extends JPanel{

	public static int frameWidth;
	public static int frameHeight;
	public static final long secInNanoSec = 1000000000L;
	public static final long milisecInNanoSec = 1000000L;

	private final int GAME_FPS = 60;

	public static final String LOGIN = "login";
	public static final String ADMIN = "admin";

	public static BufferedImage originalBGImage;
	public static BufferedImage scaledBGImage;

	public static Framework framework;
	public static JPanel loginPanel;
	public static JPanel adminPanel;

	public static enum GameState{LOG_IN, ADMIN};
	public static GameState gameState;

	public Framework(CardLayout c){
		super(c);

		this.framework = this;
		gameState = GameState.LOG_IN;
		initialize();

		Thread gameThread = new Thread(()->GameLoop());
		gameThread.start();
	}

	public void initialize(){
		
		this.setPreferredSize(new Dimension(ANNdroid.SCREEN_WIDTH, ANNdroid.SCREEN_HEIGHT));
		this.setBounds(0, 0, ANNdroid.SCREEN_WIDTH, ANNdroid.SCREEN_HEIGHT);
		this.setBackground(Color.BLACK);
		//this.bgImage = ANNdroid.TK.getImage("ANNdroid/resources/mainbg.jpg");
		
		try{
			originalBGImage = ImageIO.read(new File("ANNdroid/resources/mainbg.jpg"));
		}catch(Exception e){
			e.printStackTrace();
		}

		addComponentListener(new ComponentListener(){
		
			public void componentResized(ComponentEvent e) {
					resize();
			}

			public void componentHidden(ComponentEvent e) {}
			public void componentMoved(ComponentEvent e) {}
			public void componentShown(ComponentEvent e) {}			
		});

		loginPanel = new LoginPanel();
		loginPanel.setOpaque(false);

		adminPanel = new AdminPanel();
		adminPanel.setOpaque(false);

		this.add(loginPanel, LOGIN);
		this.add(adminPanel, ADMIN);
	}

	private void GameLoop(){
		
		long beginTime ;
		CardLayout c1 = (CardLayout)(this.getLayout());
		
		while(true){
			beginTime = System.nanoTime();
			
			switch(gameState){
				case LOG_IN:
					c1.show(this, this.LOGIN);
				break;
				case ADMIN:
					c1.show(this, this.ADMIN);
				break;
			}
			
		}
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(scaledBGImage, 0, 0, getWidth(), getHeight(), null);
	}

	public void resize() {
		double widthScaleFactor = getWidth() / (double)originalBGImage.getWidth();
		double heightScaleFactor = getHeight() / (double)originalBGImage.getHeight();
		double scaleFactor = (widthScaleFactor > heightScaleFactor)? heightScaleFactor : widthScaleFactor;

		AffineTransform at = new AffineTransform();
		at.scale(scaleFactor, scaleFactor);

		AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
		scaledBGImage = scaleOp.filter(originalBGImage, null);

		repaint();
	}  

}