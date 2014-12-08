package ANNdroid.arc.maps;

import java.io.File;

import javax.swing.JPanel;
import javax.imageio.ImageIO;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

public class MapPanel extends JPanel{
	
	BufferedImage bg;


	public MapPanel(){
		try{
			bg = ImageIO.read(new File("reso/bg1.jpg"));
		}catch(Exception e){	e.printStackTrace();	}	
	}

	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(bg, 0, 0, getWidth(), getHeight(), null);
	}
}