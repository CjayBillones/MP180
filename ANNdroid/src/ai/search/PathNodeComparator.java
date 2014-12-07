package ANNdroid.src.ai.search;

import java.util.Comparator;
import java.awt.Point;

public class PathNodeComparator implements Comparator<PathNode>{

	Point target;

	public PathNodeComparator(Point target){
		this.target = target;
	}

	@Override
	public int compare(PathNode a, PathNode b){

		int a_cost = a.getTotalCost() + manhattanHeuristic(a.location,target);
		int b_cost = b.getTotalCost() + manhattanHeuristic(b.location,target);

		return a_cost - b_cost;

	}


	public int manhattanHeuristic(Point start, Point end){
		
		int x1 = (int)start.getX();
		int x2 = (int)end.getX();
		int y1 = (int)start.getY();
		int y2 = (int)start.getY();

		return Math.abs(x1 - x2) + Math.abs(y1 - y2);

	}


}