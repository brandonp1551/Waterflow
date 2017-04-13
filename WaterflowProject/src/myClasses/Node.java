package myClasses;

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
		private double waterDepth;
		
		//Elevation
		private double elevation;
		public static final double DEFAULT_DEPTH = 0;
		
		/**
		 * Constructor which sets depth to DEFAULT_DEPTH
		 * @param a The x coordinate
		 * @param b The y coordinate
		 * @param elev The elevation
		 */
		public Node(int a, int b, double elev){
			this (a, b, elev, DEFAULT_DEPTH);
		}
		
		/**
		 * Constructor to make the Node
		 * @param a The x coordinate
		 * @param b The y coordinate
		 * @param elev The elevation
		 * @param deep The desired initial water level
		 */
		public Node(int a, int b, double elev, double deep){
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
			return "At location (" + getX() + "," + getY() + ") we have an elevation of " + getElevation()+" and a net level of " + getLevel();
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
		public void setDepth(double depth){
			this.waterDepth = depth; 
		}
		
		/**
		 * Gets the depth of water at our Node
		 * @return waterDepth The depth of water
		 */
		public double getDepth(){
			return waterDepth;
		}
		
		/**
		 * Gets the elevation at our Node
		 * @return elevation The elevation attribute of our Node
		 */
		public double getElevation(){
			return elevation;
		}
		
		/**
		 * Gets the net level of the water and elevation
		 * @return level The elevation plus water depth for our Node
		 */
		public double getLevel(){
			double level = elevation + waterDepth;
			return level;
		}
		
		/**
		 * Increments the water level
		 */
		public void incrementLevel(){
			waterDepth++;
		}
	}
