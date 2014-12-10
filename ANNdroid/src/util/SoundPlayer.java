package ANNdroid.src.util;

import javazoom.jl.decoder.*;
import javazoom.jl.player.*;
import java.io.*;

public class SoundPlayer{

	public Thread play;
	public String path;
	public boolean repeat;
	public boolean pause;
	public String filename;
	public int delay;

	public SoundPlayer(String filename, boolean repeat, boolean pause){
		path = "ANNdroid/resources/sounds/";
		this.filename = filename;
		this.repeat = repeat;
		this.pause = pause;
	}

	public SoundPlayer(String filename, boolean repeat, int delay){
		path = "ANNdroid/resources/sounds/";
		this.filename = filename;
		this.repeat = repeat;
		this.delay = delay;
		pause = false;
	}

	public void playSound(){
		play = new Thread(){
			public void run(){
				Player p;
				try{					
					if(repeat){
						try{
							Thread.sleep(delay);
						}catch(InterruptedException e){}
						while(true){
							if(!pause){
								p = new Player(new BufferedInputStream(new FileInputStream(new File(path + filename))));
								p.play();
							}
							else{
								Thread.sleep(1500);
								p = new Player(new BufferedInputStream(new FileInputStream(new File(path + filename))));
								p.play();
							}
						}
					}
					else{
						p = new Player(new BufferedInputStream(new FileInputStream(new File(path + filename))));					
						p.play();
					}
				}catch(FileNotFoundException e){
					e.printStackTrace();
				}catch(JavaLayerException e){
					e.printStackTrace();
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}			
		};
		play.start();
	}

}