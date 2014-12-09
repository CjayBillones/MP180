package ANNdroid.src.panels;

import ANNdroid.src.ai.search.*;
import ANNdroid.src.ai.search.Search.Directions;

import javax.swing.*;
import java.awt.*;


	public class PlayerGlassPanel extends JPanel{

		Timer timer;
		public int xPos = 600;
		public int yPos = 550;
		public int width;
		public int height;

		public PlayerGlassPanel(int x, int y, int width, int height){
			setLayout(null);
			xPos = x;
			yPos = y;
			this.width = width;
			this.height = height;
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

			x *= width/col;
			y *= height/row;

			for(int i = 0; i < Math.abs(x); i++){
				if(x < 0){
					initX--;
				}else if(x > 0){
					initX++;
				}

				try{
					h.setColor(Color.RED);
					h.fillOval(initX,initY,20,20);
					xPos = initX;
					Thread.sleep(3);
					repaint();
					timer = new Timer(30,new LayoutEx());
					timer.start();
				}catch(InterruptedException e){}
	
			}

			for(int i = 0; i < Math.abs(y); i++){
				if(y < 0){
					initY--;
				}else if(y > 0){
					initY++;
				}
				
				try{
					h.setColor(Color.RED);
					h.fillOval(initX,initY,20,20);
					yPos = initY;
					Thread.sleep(3);
					repaint();
					timer = new Timer(30,new LayoutEx());
					timer.start();
				}catch(InterruptedException e){}
			}

			Point ret = new Point(initX,initY);

			return ret;
		}

		public Point moveOnPath(Directions[] dirs, Search s, int initX , int initY, Graphics g, int row, int col){

			Point point = new Point(initX,initY);

			for(Directions d: dirs){
				point = move(d,s,(int)point.getX(),(int)point.getY(),g,row,col);
			}

			return point;

		}
	}