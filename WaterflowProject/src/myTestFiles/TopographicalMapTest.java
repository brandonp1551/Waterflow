package myTestFiles;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import myClasses.Node;
import myClasses.TopographicalMap;

public class TopographicalMapTest {
	@Test
	public void constructorTest() throws IOException{
		TopographicalMap m = new TopographicalMap("C:\\Users\\JameyP\\Desktop\\Waterflow\\WaterflowProject\\src\\dataSets\\Mountain.csv");
		/*
		 * In future: Test illegal file paths
		 * Test size of topographical maps generated
		 * Test numPoints
		 */
	}
	
	@Test
	public void neightborsTest() throws IOException{
		TopographicalMap m = new TopographicalMap("C:\\Users\\JameyP\\Desktop\\Waterflow\\WaterflowProject\\src\\dataSets\\Mountain.csv");
		//Tests for when neighbors are lower than current Node
		m.getLowestNeighbor(m.map[3][5]);
		assertTrue(m.getSubmergedNodes().size() == 1);
		m.getLowestNeighbor(m.map[5][6]);
		assertTrue(m.getSubmergedNodes().size() == 2);
		
		//Tests for when there isn't a neighbor lower than current Node
		m.getLowestNeighbor(m.map[0][0]);
		assertFalse(m.getSubmergedNodes().size() == 3);
	}
	
	@Test
	public void setterTest() throws IOException{
		TopographicalMap m = new TopographicalMap("C:\\Users\\JameyP\\Desktop\\Waterflow\\WaterflowProject\\src\\dataSets\\Mountain.csv");
		m.setStartNode(m.map[3][5]);
	}
}
