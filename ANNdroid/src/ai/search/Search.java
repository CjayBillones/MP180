package ANNdroid.src.ai.search;

import java.util.*;
import java.io.*;
import javax.swing.tree.*;
import java.awt.Point;

public class Search{
	
	public Boolean[][] mountains;
	public Map<String,Point> kingdoms;

	public enum Directions{ NORTH, SOUTH, EAST, WEST };
	public final Map<Directions,Point> dir_vectors;

	public Search(File map, int x, int y){

		ArrayList<String> keys = new ArrayList<String>();
		this.kingdoms = new HashMap<String,Point>();
		this.dir_vectors = new EnumMap<Directions,Point>(Directions.class);
		this.mountains = new Boolean[x][y];		
			
		keys.add("P");
		keys.add("C");
		keys.add("B");
		keys.add("K");
				
		dir_vectors.put(Directions.NORTH, new Point(0,-1));
		dir_vectors.put(Directions.SOUTH, new Point(0,1));
		dir_vectors.put(Directions.EAST, new Point(1,0));
		dir_vectors.put(Directions.WEST, new Point(-1,0));


		try{
			Scanner s = new Scanner(map);
			int i = 0;
			while(s.hasNext() && i < x){
				String str = s.nextLine();
				
				for(int j = 0; j < y; j++){
					if(str.charAt(j) == '%')
						mountains[i][j] = true;
					else
						mountains[i][j] = false;
					if(keys.contains(str.substring(j,j+1))){
						kingdoms.put(str.substring(j,j+1), new Point(i,j));
					}
				}
				i++;
			}	
		}catch(FileNotFoundException e){
			System.out.println("File not FOund");
		}				

	}

	public Directions[] findPath(String start, String end){
		Point stPoint = kingdoms.get(start);
		Point enPoint = kingdoms.get(end);

		PathNode startState = new PathNode(stPoint);
		PathNodeComparator comparator = new PathNodeComparator(enPoint);
		PriorityQueue<PathNode> frontiers = new PriorityQueue<PathNode>(10, comparator);
 		List<Point> explored = new ArrayList<Point>();
 		frontiers.add(startState);

 		while(!frontiers.isEmpty()){
 			PathNode cur = (PathNode)frontiers.peek();
 			frontiers.remove(cur);

 			if(!explored.contains(cur.location)){ 
 				explored.add(cur.location);

 				if(cur.location.equals(enPoint))
 					return cur.getPathTo();

 				for(PathNode p: getPassable(cur)){
 					frontiers.add(p);
 				}
 				
 			}
 		}

 		return new Directions[0];

	}

	public ArrayList<PathNode> getPassable(PathNode parent){

		Point p = parent.location;

		ArrayList<PathNode> ret = new ArrayList<PathNode>();
		int x = (int)p.getX();
		int y = (int)p.getY();

		for(Directions d: Directions.values()){
			Point adj = dir_vectors.get(d);
			int i = x + (int)adj.getY();
			int j = y + (int)adj.getX();

			if(!mountains[i][j]){
				Point newPoint = new Point(i,j);
				ret.add(new PathNode(parent,newPoint,d,1));
			}
		}

		return ret;
	}



}