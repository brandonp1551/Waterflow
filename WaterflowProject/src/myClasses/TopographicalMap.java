package myClasses;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

/**
 * Generates a topographical map from a data file
 * @author JameyP
 *
 */
public class TopographicalMap {
	//TO DO: fix later
	public Node[][] map = new Node[10][10];
	private int numPoints = 0;
	private Stack<Node> submergedNodes = new Stack<>();
	private Node startNode;
	private Node currentNode;
	
	/**
	 * Our constructur to initialize the map; needs work for unknown sizes
	 * @param fname The file to generate the map
	 * @throws IOException
	 */
	public TopographicalMap(String fname) throws IOException{
		BufferedReader br = null;
		String line = "";
		String splitBy = ",";
		try {
			br = new BufferedReader(new FileReader(fname));
			br.readLine();
			while ((line = br.readLine()) != null) {
				String[] data = line.split(splitBy);
				int x = Integer.parseInt(data[0]);
				int y = Integer.parseInt(data[1]);
				double depth = Double.parseDouble(data[2]);
				if (data.length == 3){
					map[x-1][y-1] = new Node(x-1, y-1, depth);
				} else {
					double waterLevel = Double.parseDouble(data[3]);
					map[x-1][y-1] = new Node(x-1, y-1, depth, waterLevel);
				}
				numPoints++;
			}
		} catch (FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		} finally {
			if (br != null){
				try {
					br.close();
				} catch (IOException e){
					e.printStackTrace();
				}
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
	 * Gets the node of the current level which we added first
	 * This enables the water to back up properly in a flood
	 * @return That lowest node
	 */
	public Node getFirstLowest(){
		int n = submergedNodes.size()-1;
		Node firstLowest = submergedNodes.lastElement();
		while (firstLowest.getLevel() == submergedNodes.elementAt(n-1).getLevel()){
			if (n>1){
				n--;
				break;
			}
			firstLowest = submergedNodes.elementAt(n);
		}
		return firstLowest;
	}
}