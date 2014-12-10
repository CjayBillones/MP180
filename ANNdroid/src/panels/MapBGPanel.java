package ANNdroid.src.panels;

import ANNdroid.src.objects.Categories;
import ANNdroid.src.ANNdroid;
import ANNdroid.src.*;
import ANNdroid.src.ai.search.Search;
import ANNdroid.src.ai.search.Search.*;
import ANNdroid.src.panels.GamePanel;
import ANNdroid.src.panels.PlayerGlassPanel;
import ANNdroid.src.objects.GamePanelController;

import java.io.*;
import java.util.*;

import javax.swing.Timer;
import javax.swing.*;
import javax.imageio.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.geom.*;

public class MapBGPanel extends JPanel{
	
	public BufferedImage bg;
	public BufferedImage scaledbg;
	private String cur = "K"; 

	File map;

	int width;
	int height;
	int row;
	int col;
	int x;
	int y;

	BufferedImage wall;
	BufferedImage space;
	BufferedImage bio;
	BufferedImage phys;
	BufferedImage chem;
	BufferedImage start;

	JPanel defPan = new JPanel();
	JLabel player = new JLabel("P");
	JButton kingdom = new JButton();
	JButton physics = new JButton();
	JButton biology = new JButton();
	JButton chemistry = new JButton();	
	JFrame parent;

	PlayerGlassPanel pGlass;
	GamePanel gamePanel;
	Search s;
	StudentPanel stud_pan;
	GamePanelController gpc;

	public MapBGPanel(int width, int height, int row, int col, JFrame parent, StudentPanel stud_pan){

		defPan.setLayout(new CardLayout());
		this.stud_pan = stud_pan;
		this.row = row;
		this.col = col;
		this.width = width;
		this.height = height;

		gamePanel = new GamePanel(parent);

		gpc = new GamePanelController(gamePanel,ANNdroid.simulator.kingList.get(0),"NULL",3,3,5); 

		kingdom.addActionListener(new MapActionListener());
		physics.addActionListener(new MapActionListener());
		chemistry.addActionListener(new MapActionListener());
		biology.addActionListener(new MapActionListener());

		setPreferredSize(new Dimension(width, height));

		try{
			map = new File("ANNdroid/bin/map.txt");
			s = new Search(map,row,col);
			bg = ImageIO.read(new File("ANNdroid/resources/img/map/bg1.png"));
		}catch(Exception e){	e.printStackTrace();	}

		try{
			wall = ImageIO.read(new File("ANNdroid/resources/img/map/harang2.png"));
			bio = ImageIO.read(new File("ANNdroid/resources/img/map/bio_node2.png"));
			chem = ImageIO.read(new File("ANNdroid/resources/img/map/chem_node2.png"));
			phys = ImageIO.read(new File("ANNdroid/resources/img/map/physics_node2.png"));
			start = ImageIO.read(new File("ANNdroid/resources/img/map/start_node2.png"));
		}catch(Exception e){	e.printStackTrace();	}

		this.parent = parent;
		JComponent[] comps = generateMap(map,row,col);
		

		for(int i = 0; i < (row * col); i++){			
			add(comps[i]);
		}

	}

	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(scaledbg, 0, 0, getWidth(), getHeight(), null);
	}

	public void setGlassPane(){
		pGlass =  new PlayerGlassPanel(this.x,this.y,this.width,this.height);
		pGlass.setOpaque(false);
		parent.setGlassPane(pGlass);
	}

	public void resetGlassPane(){
		JPanel newGlass = new JPanel();
		newGlass.setOpaque(false);
		parent.setGlassPane(newGlass);
		
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

		pGlass =  new PlayerGlassPanel(this.x,this.y,this.width,this.height);
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
						case 'K':{
							ret[(i * y) + j] = initImage(kingdom, start, false);
							double cell_w = (double)this.width / (double)this.col;
							double cell_h = (double)this.height/ (double)this.row ;
							this.x = (int)(((double)j * cell_w) + (cell_w * 0.5)) + stud_pan.leftSidePane.getWidth();
							this.y = (int)(((double)i * cell_h) + (cell_h * 0.5)) ; 
							System.out.println(this.y);
						}break;
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

	public class MapActionListener implements ActionListener{
		

		public void actionPerformed(ActionEvent evt){
			Point p = new Point(x,y);
			Object src = evt.getSource();
			PlayerGlassPanel glass = pGlass;
			CardLayout cl = (CardLayout)defPan.getLayout();
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
				gpc.setSubject("Physics");
				gpc.setKing(ANNdroid.simulator.kingList.get(2));
				gpc.getQuestion();
				gamePanel.setSize(width + stud_pan.leftSidePane.getWidth(),height + stud_pan.leftSidePane.getHeight());
				gamePanel.setVisible(true);
				parent.setVisible(false);
			}
			else if(src == biology){
				p = glass.moveOnPath(s.findPath(cur,"B"), s, x, y, glass.getGraphics(),row,col);
				cur = "B";
				x = (int)p.getX();
				y = (int)p.getY();
				gpc.setSubject("Biology");
				gpc.setKing(ANNdroid.simulator.kingList.get(0));
				gpc.getQuestion();
				gamePanel.setSize(width + stud_pan.leftSidePane.getWidth(),height + stud_pan.leftSidePane.getHeight());
				gamePanel.setVisible(true);
				parent.setVisible(false);
			}else if(src == chemistry){
				System.out.println(glass);
				p = glass.moveOnPath(s.findPath(cur,"C"), s, x, y, glass.getGraphics(),row,col);
				cur = "C";
				x = (int)p.getX();
				y = (int)p.getY();
				gpc.setSubject("Chemistry");
				gpc.setKing(ANNdroid.simulator.kingList.get(1));
				gpc.getQuestion();
				gamePanel.setSize(width + stud_pan.leftSidePane.getWidth(),height + stud_pan.leftSidePane.getHeight());
				gamePanel.setVisible(true);
				parent.setVisible(false);
			}
		}
	}

	private class ResizeListener extends ComponentAdapter{
		public void componentResized(ComponentEvent e){
			PlayerGlassPanel glass = pGlass;
			Graphics2D h = (Graphics2D)glass.getGraphics();
			int oldW = width;
			int oldH = height;
			width = getWidth();
			height = getHeight();
			double newX = (double)width * ((double)glass.xPos/(double)oldW);
			double newY = (double)height * ((double)glass.yPos/(double)oldH);
			glass.xPos = (int)newX;
			glass.yPos = (int)newY;
			x = glass.xPos;
			y = glass.yPos;
			glass.repaint();
		}
	}


}