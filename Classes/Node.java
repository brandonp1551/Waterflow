package finalProject;

import mazeRunner.Coordinate;

/**
 * A class to make x-y coordinate pairs with an associated elevation and water level
 * Can return the level, equivalent to elevation - water level
 * @author James P. McDowell
 *
 */
public class Node {
	private int x = 0;
	private int y = 0;
	
	//How much water has accumulated
	private int waterDepth;
	
	//Elevation
	private int elevation;
	public static final int DEFAULT_DEPTH = 0;
	
	public Node(int a, int b, int elev){
		this (a, b, elev, DEFAULT_DEPTH);
	}
	
	public Node(int a, int b, int elev, int deep){
		setX(a);
		setY(b);
		this.elevation = elev;
		setDepth(deep);
	}
	
	@Override
	public boolean equals(Object obj){
		if (obj instanceof Node) {
			Node c1 = (Node) obj;
			return (this.getX() == c1.getX() && this.getY() == c1.getY()) 
					&& this.getDepth() == c1.getDepth() && this.getElevation() == c1.getElevation();
		} else
			return false;
	}
	
	@Override
	public String toString(){
		return "At location (" + getX() + "," + getY() + ") we have an elevation of " + getElevation()+" and a water depth of " + getDepth();
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public void setDepth(int depth){
		this.waterDepth = depth; 
	}
	
	public int getDepth(){
		return waterDepth;
	}
	
	public int getElevation(){
		return elevation;
	}
	
	public int getLevel(){
		return elevation + waterDepth;
	}
}
