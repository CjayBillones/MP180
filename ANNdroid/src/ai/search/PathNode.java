package ANNdroid.src.ai.search;

import ANNdroid.src.ai.search.Search.Directions;
import javax.swing.tree.*;
import java.awt.Point;
import java.util.*;

class PathNode extends DefaultMutableTreeNode{
	
	public Point location;
	public Directions action;
	public int cost;

	public PathNode(Point p){
		location = p;
		action = null;
		cost = 0;
		this.setParent(null);
	}

	public PathNode(Point p,Directions a,int c){
		location = p;
		action = a;
		cost = c;
		this.setParent(null);
	}

	public PathNode(PathNode parent, Point p,Directions a,int c){
		this(p,a,c);
		this.setParent(parent);
	}

	public Directions[] getPathTo(){
		
		List<PathNode> path = getPathFromRoot();
		Directions[] ret = new Directions[path.size()];
		for(int i = 0; i < ret.length; i++){
			PathNode cur = path.get(i); 	
			ret[i] = cur.action;
		}

		return ret;
	}

	public List<PathNode> getPathFromRoot(){
		List<PathNode> ret = new ArrayList<PathNode>();
		PathNode cur = this;
		while(cur.getParent() != null){
			ret.add(cur);
			cur = (PathNode)cur.getParent();
			
		}
		Collections.reverse(ret);
		return ret;
	}

	public int getTotalCost(){
		List<PathNode> path = getPathFromRoot();
		int ret = 0;
		for(PathNode p: path)
			ret += p.cost;

		return ret;
	}


}