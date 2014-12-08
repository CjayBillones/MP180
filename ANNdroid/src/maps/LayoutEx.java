package ANNdroid.arc.maps;

import ANNdroid.src.ai.search.*;
import ANNdroid.src.ai.search.Search.*;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.GridLayout;
import java.awt.*;
import java.util.*;
import java.awt.Point;
import java.io.*;
import java.awt.event.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

public class LayoutEx extends JFrame implements ActionListener{


	private int x;
	private int y;
	private String cur = "K"; 
	private int col;
	private int row;

	BufferedImage wall;
	BufferedImage space;
	BufferedImage bio;
	BufferedImage phys;
	BufferedImage chem;
	BufferedImage start;

	JButton kingdom = new JButton();
	JButton physics = new JButton();
	JButton biology = new JButton();
	JButton chemistry = new JButton();

	PlayerGlassPanel pGlass;
//	JPanel pan = new JPanel();

	File map;
	Search s;

	public LayoutEx(){

	}

	public LayoutEx(File file,int row, int col, int x, int y){
		this.x = x;
		this.y = y;
		this.row = row;
		this.col = col;
		JPanel pan = new MapPanel();
		pGlass =  new PlayerGlassPanel(x,y);
		this.setGlassPane(pGlass);
		JPanel glass = (JPanel) this.getGlassPane();
		map = file;
		s = new Search(map,row,col);

		pan.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
		pan.setLayout(new GridLayout(row,col,2,2));

		try{
			wall = ImageIO.read(new File("reso/harang2.png"));
			//space = ImageIO.read(new File("reso/space.png"));
			bio = ImageIO.read(new File("reso/bio_node2.png"));
			chem = ImageIO.read(new File("reso/chem_node2.png"));
			phys = ImageIO.read(new File("reso/physics_node2.png"));
			start = ImageIO.read(new File("reso/start_node2.png"));
			//bg = ImageIO.read(new File("reso/neuromancer1.jpg"));
		}catch(Exception e){	e.printStackTrace();	}

		JComponent[] comps = generateMap(map,row,col);

		for(int i = 0; i < (row * col); i++){			
			pan.add(comps[i]);
		}

		add(pan);

		kingdom.setContentAreaFilled(false);
		physics.setContentAreaFilled(false);
		biology.setContentAreaFilled(false);
		chemistry.setContentAreaFilled(false);

		kingdom.addActionListener(this);
		physics.addActionListener(this);
		biology.addActionListener(this);
		chemistry.addActionListener(this);

		glass.setVisible(true);
		
		setSize(1240,720);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


	}

	public JComponent[] generateMap(File map,int x, int y){
		JComponent[] ret = new JComponent[x*y];

		try{
			Scanner scan = new Scanner(map);
			int i = 0;
			while(scan.hasNext() && i < x){
				String str = scan.nextLine();

				for(int j = 0; j < y; j++){
					System.out.println((i * y) + j);
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

		//initImage(ret);
		return ret;
	}

	public JComponent initImage(JComponent yey, BufferedImage vanilla, boolean isSpace){
		double widthScaleFactor = 1240/this.row / (double)vanilla.getWidth();
		double heightScaleFactor = 720/this.row  / (double)vanilla.getHeight();
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

	public void actionPerformed(ActionEvent evt){
		Point p = new Point(x,y);
		Object src = evt.getSource();
		PlayerGlassPanel glass = pGlass;
		if(src == kingdom){
			p = glass.moveOnPath(s.findPath(cur,"K"), s, x, y, glass.getGraphics(),row,col);
			cur = "K";
			x = (int)p.getX();
			y = (int)p.getY();
		}else if(src == physics){
			p = glass.moveOnPath(s.findPath(cur,"P"), s, x, y, glass.getGraphics(),row,col);
			cur = "P";
			x = (int)p.getX();
			y = (int)p.getY();
		}
		else if(src == biology){
			p = glass.moveOnPath(s.findPath(cur,"B"), s, x, y, glass.getGraphics(),row,col);
			cur = "B";
			x = (int)p.getX();
			y = (int)p.getY();
		}else if(src == chemistry){
			p = glass.moveOnPath(s.findPath(cur,"C"), s, x, y, glass.getGraphics(),row,col);
			cur = "C";
			x = (int)p.getX();
			y = (int)p.getY();
		}
	}


	public class PlayerGlassPanel extends JPanel{

		Timer timer;
		int xPos = 600;
		int yPos = 550;

		public PlayerGlassPanel(int x, int y ){
			xPos = x;
			yPos = y;
		}

		public void paint(Graphics g){
			Graphics2D h = (Graphics2D)g;
			setOpaque(false);
			h.setColor(Color.RED);
			h.fillOval(xPos,yPos,20,20);
		}

		public Point move(Directions d, Search s, int initX , int initY,Graphics g, int row, int col){
			int x = (int)s.dir_vectors.get(d).getX();
			int y = (int)s.dir_vectors.get(d).getY();
			Graphics2D h = (Graphics2D)g;

			x *= 1240/col;
			y *= 720/row;

			for(int i = 0; i < Math.abs(x); i++){
				if(x < 0){
					initX--;
				}else if(x > 0){
					initX++;
				}

		
				h.setColor(Color.RED);
				h.fillOval(initX,initY,20,20);
				xPos = initX;
				repaint();
				timer = new Timer(30,new LayoutEx());
				timer.start();
	
			}

			for(int i = 0; i < Math.abs(y); i++){
				if(y < 0){
					initY--;
				}else if(y > 0){
					initY++;
				}

				h.setColor(Color.RED);
				h.fillOval(initX,initY,20,20);
				yPos = initY;
				repaint();
				timer = new Timer(30,new LayoutEx());
				timer.start();


		

			}

			Point ret = new Point(initX,initY);

			return ret;
		}

		public Point moveOnPath(Directions[] dirs, Search s, int initX , int initY,Graphics g, int row, int col){

			Point point = new Point(initX,initY);

			for(Directions d: dirs){
				point = move(d,s,(int)point.getX(),(int)point.getY(),g,row,col);
			}

			return point;

		}
	}
}

