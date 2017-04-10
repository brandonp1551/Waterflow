package finalProject;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the Node class's public methods and constructor
 * @author James P. McDowell
 *
 */
public class NodeTest {
	
	@Test
	public void testConstructor() {
		Node a = new Node(10, 5, 3, 2);
		Node b = new Node(3, 7, 4);
		System.out.println(a);
		assertTrue(a.getDepth() == 2);
		assertTrue(b.getDepth() == 0);
		assertTrue(a.getElevation() == 3);
		assertTrue(b.getElevation() == 4);
		assertTrue(a.getLevel() == 5);
		assertTrue(b.getLevel() == 4);
		assertTrue(a.getX() == 10);
		assertTrue(b.getX() == 3);
		assertTrue(a.getY() == 5);
		assertTrue(b.getY() == 7);
	}

	@Test
	public void testEquals(){
		Node a = new Node(10, 5, 3, 2);
		Node b = new Node(10, 5, 3, 2);
		assertTrue(a.equals(b));
		
		Node c = new Node(10, 5, 3, 0);
		Node d = new Node(10, 5, 3);
		
		assertFalse(a.equals(c));
		assertTrue(c.equals(d));
		assertFalse(d.equals(a));
	}
	
	@Test
	public void testSetters(){
		Node a = new Node(10, 5, 3, 2);
		a.setDepth(20);
		assertTrue(a.getDepth() == 20);
		assertTrue(a.getLevel() == 23);
	}
}
