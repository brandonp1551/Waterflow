package finalProject;

import mazeRunner.Coordinate;

public class Node {
	private int x = 0;
	private int y = 0;
	private int waterDepth;
	private int elevation;
	private int level;
	public static final int DEFAULT_DEPTH = 0;
	
	public Node(int a, int b, int elev){
		this (a, b, elev, DEFAULT_DEPTH);
	}
	
	public Node(int a, int b, int elev, int deep){
		setX(a);
		setY(b);
		setElevation(elev);
		setDepth(deep);
		this.level = getDepth() - getElevation();
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
	
	public void setElevation(int elevation){
		this.elevation = elevation;
	}
	
	public int getElevation(){
		return elevation;
	}
	
	public int getLevel(){
		return level;
	}
}
