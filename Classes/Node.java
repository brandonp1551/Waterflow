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
	
	/**
	 * Constructor which sets depth to DEFAULT_DEPTH
	 * @param a The x coordinate
	 * @param b The y coordinate
	 * @param elev The elevation
	 */
	public Node(int a, int b, int elev){
		this (a, b, elev, DEFAULT_DEPTH);
	}
	
	/**
	 * Constructor to make the Node
	 * @param a The x coordinate
	 * @param b The y coordinate
	 * @param elev The elevation
	 * @param deep The desired initial water level
	 */
	public Node(int a, int b, int elev, int deep){
		setX(a);
		setY(b);
		this.elevation = elev;
		setDepth(deep);
	}
	
	/**
	 * Overrides object's equals methods to see if Nodes have same attributes
	 * @param obj The object we are checking our Node for equality against
	 * @return True if Object obj and this object have the same x, y, depth, and elevation values
	 */
	@Override
	public boolean equals(Object obj){
		if (obj instanceof Node) {
			Node c1 = (Node) obj;
			return (this.getX() == c1.getX() && this.getY() == c1.getY()) 
					&& this.getDepth() == c1.getDepth() && this.getElevation() == c1.getElevation();
		} else
			return false;
	}
	
	/**
	 * Returns a string containing the x, y location, the elevation, and the depth of our Node
	 * @return String The string describing the Node
	 */
	@Override
	public String toString(){
		return "At location (" + getX() + "," + getY() + ") we have an elevation of " + getElevation()+" and a water depth of " + getDepth();
	}
	
	/**
	 * Gets the Node's x coordinate
	 * @return x The value of the x-attribute
	 */
	public int getX(){
		return x;
	}
	
	/**
	 * Gets the Node's y coordinate
	 * @return y The value of the y-attribute
	 */
	public int getY(){
		return y;
	}
	
	//Sets the x-coordinate
	private void setX(int x){
		this.x = x;
	}
	
	//Sets the y-coordinate
	private void setY(int y){
		this.y = y;
	}
	
	/**
	 * Sets the water depth to some level
	 * @param depth The depth of water being set to
	 */
	public void setDepth(int depth){
		this.waterDepth = depth; 
	}
	
	/**
	 * Gets the depth of water at our Node
	 * @return waterDepth The depth of water
	 */
	public int getDepth(){
		return waterDepth;
	}
	
	/**
	 * Gets the elevation at our Node
	 * @return elevation The elevation attribute of our Node
	 */
	public int getElevation(){
		return elevation;
	}
	
	/**
	 * Gets the net level of the water and elevation
	 * @return level The elevation plus water depth for our Node
	 */
	public int getLevel(){
		int level = elevation + waterDepth;
		return level;
	}
}
