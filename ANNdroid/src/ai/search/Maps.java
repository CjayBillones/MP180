package ANNdroid.src.ai.search;	

import javax.swing.*;
import java.awt.GridLayout;
import java.util.*;
import java.awt.Point;
import java.io.*;


class Maps{
	public static void main(String args[]){
		File map = new File("ANNdroid/src/ai/search/map2.txt");
		LayoutEx l = new LayoutEx(map,10,12,1100,500,1240,720);
		l.setVisible(true);
		//LayoutEx.PlayerGlassPanel glass = (LayoutEx.PlayerGlassPanel)l.getGlassPane();

	}
}
