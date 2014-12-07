package ANNdroid.src.custom_swing;

import ANNdroid.src.*;

import java.io.File;

import javax.swing.JButton;
import javax.imageio.ImageIO;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.FontMetrics;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

public class CustomButton extends JButton{

	public BufferedImage originalBGImage;
	public BufferedImage scaledBGImage;

	public CustomButton(String text, int width, int height){

		super(text);

		setLayout(null);
		setPreferredSize(new Dimension(width, height));
		setForeground(Color.WHITE);
		setOpaque(false);

		try{
			originalBGImage = ImageIO.read(new File("ANNdroid/resources/img/button.png"));
		}catch(Exception e){	e.printStackTrace();	}

	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D)g.create();
		g.drawImage(scaledBGImage, 0, 0, getWidth(), getHeight(), null);
		
		FontMetrics fm = g2d.getFontMetrics();

		int x = (getWidth() - fm.stringWidth(getText())) / 2;
		int y = ((getHeight() - fm.getHeight()) / 2) + fm.getAscent();

		g2d.drawString(getText(), x, y);
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