package myClasses;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

/**
 * Generates a topographical map from a data file
 * @author JameyP
 *
 */
public class TopographicalMap {
	//TO DO: fix later
	public Node[][] map = new Node[121][219];
	private int numPoints = 0;
	private Stack<Node> submergedNodes = new Stack<>();
	private Node startNode;
	private Node currentNode;
	private double minLevel = 3901; //lowest level found in the map
	private double maxLevel = 6625; //highest level found in the map
	private double minDepth = 3901; //shallowest body of water
	private double maxDepth = 6625; //deepest body of water
	
	/**
	 * Our constructur to initialize the map; needs work for unknown sizes
	 * @param fname The file to generate the map
	 * @throws IOException
	 */
	public TopographicalMap(String fname) throws IOException{
		Scanner sc = null;
		try {
			sc = new Scanner(new File(fname));
			while ((sc.hasNext())) {
				int[] data = new int[3];
				int x = sc.nextInt();
				int y = sc.nextInt();
				int depth = sc.nextInt();
				data[0] = x;
				data[1] = y;
				data[2] = depth;
				if (data.length == 3){
					map[x-1][y-1] = new Node(x-1, y-1, depth);
				}
				numPoints++;
			}
		} catch (FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		} finally {
			if (sc != null){
				sc.close();
			}
		}
	}
	
	
	/**
	 * Checks N, S, E, W to see if those Nodes are legal and if they are lower
	 * than our current Node
	 * Pushes the lowest neighbor onto lowNodes stack
	 * @param c Our current Node
	 */
	public void getLowestNeighbor(Node c){
		Node lowestNeighbor = c;
		double lowestLevel = c.getLevel();
		if (isLegal(c.getX()-1, c.getY()) && (lowestLevel > map[c.getX()-1][c.getY()].getLevel())){ //west direction
			lowestLevel = map[c.getX()-1][c.getY()].getLevel();
			lowestNeighbor = map[c.getX()-1][c.getY()];
		}
		if (isLegal(c.getX()+1, c.getY()) && (lowestLevel > map[c.getX()+1][c.getY()].getLevel())){ //east direction
			lowestLevel = map[c.getX()+1][c.getY()].getLevel();
			lowestNeighbor = map[c.getX()+1][c.getY()];
		}
		if (isLegal(c.getX(), c.getY()+1) && (lowestLevel > map[c.getX()][c.getY()+1].getLevel())){ //north direction
			lowestLevel = map[c.getX()][c.getY()+1].getLevel();
			lowestNeighbor = map[c.getX()][c.getY()+1];
		}
		if (isLegal(c.getX(), c.getY()-1) && (lowestLevel > map[c.getX()][c.getY()-1].getLevel())){ //south direction
			lowestLevel = map[c.getX()][c.getY()-1].getLevel();
			lowestNeighbor = map[c.getX()][c.getY()-1];
		}
		if (!lowestNeighbor.equals(c)){
			submergedNodes.push(lowestNeighbor);
		}
		currentNode = lowestNeighbor;
	}
	
	//Private utility method to check if in-bounds
	private boolean isLegal(int x, int y){
		return ((0 <= x) && (x < map.length) && (0 <= y) && (y < map[0].length));
	}

	/**
	 * Gets number of points in our map
	 * @return The number of points
	 */
	public int getNumberOfPoints(){
		return numPoints;
	}
	
	/**
	 * @return the width of the map
	 */
	public int getWidth(){
		return map.length;
	}
	
	/**
	 * @return the height of the map
	 */
	public int getHeight(){
		return map[0].length;
	}
	
	/**
	 * Gets the stack of our submerged nodes
	 * @return The stack
	 */
	public Stack<Node> getSubmergedNodes(){
		return submergedNodes;
	}
	
	/**
	 * Sets the starting node
	 * @param n The starting node
	 */
	public void setStartNode(Node n){
		startNode = n;
		currentNode = startNode;
		submergedNodes.push(n);
	}
	
	/**
	 * Gets the start node
	 * @return The start node
	 */
	public Node getStartNode(){
		return startNode;
	}
	
	/**
	 * Gets the current outermost node
	 * @return The node
	 */
	public Node getCurrentNode(){
		return currentNode;
	}
	
	/**
	 * @return returns the lowest level found in the map
	 */
	public double getMinLevel(){
		return minLevel;
	}
	
	/**
	 * @return returns the highest level found in the map
	 */
	public double getMaxLevel(){
		return maxLevel;
	}
	
	/**
	 * @return returns the shallowest amount of water found in the map
	 */
	public double getMinDepth(){
		return minDepth;
	}
	
	/**
	 * @return returns the deepest amount of water found in the map
	 */
	public double getMaxDepth(){
		return maxDepth;
	}
	
	/**
	 * @param x x coordinate of node to return
	 * @param y y coordinate of node to return
	 * @return returns the node at specified coordinates
	 */
	public Node getNode(int x, int y){
		return map[x][y];
	}
	
	/**
	 * Gets the node of the current level which we added first
	 * This enables the water to back up properly in a flood
	 * @return That lowest node
	 */
	public Node getFirstLowest(){
		int n = submergedNodes.size() - 1;
		double lastHeight = submergedNodes.lastElement().getLevel();
		for (int i = n; i > 0; i--){
			if (lastHeight != submergedNodes.elementAt(i-1).getLevel()){
				return submergedNodes.elementAt(i);
			}
		}
		return submergedNodes.elementAt(0);
	}
}
