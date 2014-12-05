package ANNdroid.src;

import java.io.File;

import javax.swing.JPanel;
import javax.imageio.ImageIO;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

public class BackgroundPanel extends JPanel{

	public BufferedImage originalBGImage;
	public BufferedImage scaledBGImage;

	public BackgroundPanel(){
		
		setPreferredSize(new Dimension(ANNdroid.SCREEN_WIDTH, ANNdroid.SCREEN_HEIGHT));
		setBounds(0, 0, ANNdroid.SCREEN_WIDTH, ANNdroid.SCREEN_HEIGHT);
		setBackground(Color.BLACK);

		try{
			originalBGImage = ImageIO.read(new File("ANNdroid/resources/img/bg.png"));
		}catch(Exception e){	e.printStackTrace();	}

	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(scaledBGImage, 0, 0, getWidth(), getHeight(), null);
	}

	// Resizes Background Image to Fit Panel //
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