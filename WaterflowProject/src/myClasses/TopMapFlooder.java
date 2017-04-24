package myClasses;

import java.io.IOException;

/**
 * Floods the map until it is full; if fills up, currently doesn't continue to work
 * @author JameyP
 *
 */
public class TopMapFlooder {
	public static void main(String[] args) throws IOException{
		TopographicalMap m = new TopographicalMap("src/dataSets/furmanMap.txt");
		m.setStartNode(m.map[50][50]);
		m.getStartNode().incrementLevel();
		System.out.println(m.getStartNode().getLevel());
		for (int i = 0; i < 10000; i++){
			m.getLowestNeighbor(m.getCurrentNode());
			m.getFirstLowest().incrementLevel();
		}
		System.out.println(m.getSubmergedNodes());
		System.out.println(m.getCurrentNode());
	}
}
