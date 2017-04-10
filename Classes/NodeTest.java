package finalProject;

import static org.junit.Assert.*;

import org.junit.Test;

public class NodeTest {
	
	
	Node a = new Node(10, 5, 3, 2);
	Node b = new Node(10, 5, 3, 4);
	Node c = new Node(10, 5, 3);
	
	@Test
	public void createBasics() {
		Node a = new Node(10, 5, 3, 2);
		assertTrue(a.getDepth() == 2);
		System.out.println(a);
		Node b = new Node(10, 5, 3, 4);
		assertFalse(a.equals(b));
		Node c = new Node(10, 5, 3);
		assertTrue(c.getDepth() == 0);
		assertFalse(a.equals(c));
		System.out.println(b);
		System.out.println(c);
	}

}
