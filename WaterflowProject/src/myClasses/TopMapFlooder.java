package myClasses;

import java.io.IOException;

/**
 * Floods the map until it is full; if fills up, currently doesn't continue to work
 * @author JameyP
 *
 */
public class TopMapFlooder {
	public static void main(String[] args) throws IOException{
		TopographicalMap m = new TopographicalMap("C:/Users/tcj/AppData/Roaming/Microsoft/Windows/Start Menu/Programs/Eclipse/Waterflow/WaterflowProject/src/dataSets/Mountain.csv");
		m.setStartNode(m.map[0][0]);
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
