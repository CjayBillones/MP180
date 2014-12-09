package ANNdroid.src.panels;

import java.io.*;
import java.util.*;

import javax.swing.*;
import javax.imageio.*;

import java.awt.*;
import java.awt.image.*;
import java.awt.geom.*;

public class MapBGPanel extends JPanel{
	
	public BufferedImage bg;
	public BufferedImage scaledbg;

	File map;

	int width;
	int height;
	int row;
	int col;

	BufferedImage wall;
	BufferedImage space;
	BufferedImage bio;
	BufferedImage phys;
	BufferedImage chem;
	BufferedImage start;

	JLabel player = new JLabel("P");
	JButton kingdom = new JButton();
	JButton physics = new JButton();
	JButton biology = new JButton();
	JButton chemistry = new JButton();	

	public MapBGPanel(int width, int height, int row, int col){

		this.row = row;
		this.col = col;
		this.width = width;
		this.height = height;

		setPreferredSize(new Dimension(width, height));

		try{
			map = new File("ANNdroid/bin/map2.txt");
			bg = ImageIO.read(new File("ANNdroid/resources/img/map/bg1.png"));
		}catch(Exception e){	e.printStackTrace();	}

		try{
			wall = ImageIO.read(new File("ANNdroid/resources/img/map/harang2.png"));
			bio = ImageIO.read(new File("ANNdroid/resources/img/map/bio_node2.png"));
			chem = ImageIO.read(new File("ANNdroid/resources/img/map/chem_node2.png"));
			phys = ImageIO.read(new File("ANNdroid/resources/img/map/physics_node2.png"));
			start = ImageIO.read(new File("ANNdroid/resources/img/map/start_node2.png"));
		}catch(Exception e){	e.printStackTrace();	}

		JComponent[] comps = generateMap(map,row,col);

		for(int i = 0; i < (row * col); i++){			
			add(comps[i]);
		}

	}

	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(scaledbg, 0, 0, getWidth(), getHeight(), null);
	}

	// Resizes Background Image to Fit Panel //
	public void resize() {
		double widthScaleFactor = getWidth() / (double)bg.getWidth();
		double heightScaleFactor = getHeight() / (double)bg.getHeight();
		double scaleFactor = (widthScaleFactor > heightScaleFactor)? heightScaleFactor : widthScaleFactor;

		AffineTransform at = new AffineTransform();
		at.scale(scaleFactor, scaleFactor);

		AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
		scaledbg = scaleOp.filter(bg, null);

		repaint();
	}

	public JComponent[] generateMap(File map,int x, int y){
		JComponent[] ret = new JComponent[x*y];

		try{
			Scanner scan = new Scanner(map);
			int i = 0;
			while(scan.hasNext() && i < x){
				String str = scan.nextLine();

				for(int j = 0; j < y; j++){
					switch(str.charAt(j)){
						case '%':{ret[(i * y) + j] = initImage(new JLabel(), wall, false);} break;
						case 'P':{ret[(i * y) + j] = initImage(physics, phys, false);}break;
						case 'C':{ret[(i * y) + j] = initImage(chemistry, chem, false);}break;
						case 'B':{ret[(i * y) + j] = initImage(biology, bio, false);}break;
						case 'K':{ret[(i * y) + j] = initImage(kingdom, start, false);}break;
						default:{ret[(i * y) + j] = new JLabel();} break;
					}
				}
				i++;
			}
		}catch(FileNotFoundException f){
			System.out.println("File Not Found!");
		}

		return ret;
	}

	public JComponent initImage(JComponent yey, BufferedImage vanilla, boolean isSpace){
		double widthScaleFactor = width/this.row / (double)vanilla.getWidth();
		double heightScaleFactor = height/this.row  / (double)vanilla.getHeight();
		double scaleFactor = (widthScaleFactor > heightScaleFactor)? heightScaleFactor : widthScaleFactor;

		AffineTransform at = new AffineTransform();
		at.scale(scaleFactor, scaleFactor);

		AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
		BufferedImage temp = scaleOp.filter(vanilla, null);

		if(isSpace){
			return null;
		}
		else{
			if(yey instanceof JButton){
				JButton j = (JButton)yey; 
				j.setText("");
				j.setIcon(new ImageIcon(temp));
				return j;
			}
			else{
				JLabel j = (JLabel)yey; 
				j.setText("");
				j.setIcon(new ImageIcon(temp));
				return j;
			}
		}
	}	
}