package ANNdroid.src.ai.search;	

import javax.swing.*;
import java.awt.GridLayout;
import java.util.*;
import java.awt.Point;
import java.io.*;


class Maps{
	public static void main(String args[]){
		File map = new File("ANNdroid/bin/map.txt");
		LayoutEx l = new LayoutEx(map,9,9,1100,500,1240,720);
		l.setVisible(true);
		//LayoutEx.PlayerGlassPanel glass = (LayoutEx.PlayerGlassPanel)l.getGlassPane();

	}
}
