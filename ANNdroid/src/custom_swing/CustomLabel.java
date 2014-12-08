package ANNdroid.src.custom_swing;

import ANNdroid.src.*;

import java.io.File;

import javax.swing.*;
import javax.imageio.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.geom.*;

public class CustomLabel extends JLabel{

	public BufferedImage originalBGImage;
	public BufferedImage scaledBGImage;

	public CustomLabel(String text, int width, int height, int mode){
		
		super(text, SwingConstants.CENTER);

		if(mode == 1) setForeground(Color.WHITE);
		else setForeground(Color.RED);
		
		setPreferredSize(new Dimension(width, height));
		setOpaque(false);

		try{
			if(mode == 0)	originalBGImage = ImageIO.read(new File("ANNdroid/resources/img/full.png"));
			else if(mode == 1) originalBGImage = ImageIO.read(new File("ANNdroid/resources/img/label.png"));
			else originalBGImage = ImageIO.read(new File("ANNdroid/resources/img/field.png"));
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