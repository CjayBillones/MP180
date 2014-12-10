package ANNdroid.src.custom_swing;

import ANNdroid.src.*;
import ANNdroid.src.events.*;
import ANNdroid.src.panels.*;

import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import javax.imageio.*;
import java.awt.image.*;
import java.awt.geom.*;

public class CustomTextArea extends JTextArea{

	BufferedImage bgImage = null;
	BufferedImage scaledImage = null;
	String text = null;

	public CustomTextArea(String text, int rows, int columns){
		super(text, rows, columns);
		this.text = text;
		setOpaque(false);
		try{
			bgImage = ImageIO.read(new File("ANNdroid/resources/img/gamescreen/question.png"));
		}catch(Exception e){	e.printStackTrace();	}		
	}

	public CustomTextArea(Color caretColor, String text, int rows, int columns){
		
		super(text, rows, columns);

		setForeground(Color.WHITE);
		setOpaque(false);
		setCaretColor(caretColor);
		setBorder(BorderFactory.createEmptyBorder());
		addMouseListener(new CustomFieldMouseListener(this));
		addFocusListener(new FocusAdapter(){
			public void focusGained(FocusEvent e){
				((TeacherPanel)ANNdroid.teacherPanel).jp.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 1));
			}

			public void focusLost(FocusEvent e){
				((TeacherPanel)ANNdroid.teacherPanel).jp.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
			}
		});
	}

	// Resizes Background Image to Fit Panel //
	public void resize() {
		double widthScaleFactor = getWidth() / (double)bgImage.getWidth();
		double heightScaleFactor = getHeight() / (double)bgImage.getHeight();
		double scaleFactor = (widthScaleFactor > heightScaleFactor)? heightScaleFactor : widthScaleFactor;

		AffineTransform at = new AffineTransform();
		at.scale(scaleFactor, scaleFactor);

		AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
		scaledImage = scaleOp.filter(bgImage, null);

		repaint();
	}	

	public void paintComponent(Graphics g){
		super.paintComponent(g);

		if(scaledImage != null) g.drawImage(scaledImage, 0, 0, getWidth(), getHeight(), null);

		if(text != null){
			Graphics2D g2d = (Graphics2D)g.create();
			FontMetrics fm = g2d.getFontMetrics();

			int x = (getWidth() - fm.stringWidth(getText())) / 2;
			int y = ((getHeight() - fm.getHeight()) / 2) + fm.getAscent();
			this.setForeground(Color.WHITE);
			g2d.drawString(getText(), x, y);
		}

		g.setColor(new Color(255,255,255,128));
	}

}