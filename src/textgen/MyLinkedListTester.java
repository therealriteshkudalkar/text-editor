package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH = 10;

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		
	}

	
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
	}
	
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer) 21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		
		// TODO: Add more tests here
		try {
			a = emptyList.remove(0);
			fail("Check out of bound");
		} catch (IndexOutOfBoundsException e) {

		}

		try {
			a = list1.remove(-1);
			fail("Check out of bound");
		} catch (IndexOutOfBoundsException e) {

		}

		try {
			a = list1.remove(5);
			fail("Check out of bound");
		} catch (IndexOutOfBoundsException e) {

		}
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
        // TODO: implement this test
		try {
			list1.add(null);
			fail("Check null pointer exception");
		} catch (NullPointerException e) {

		}
		list1.add(77);
		assertEquals("Add: check if added element is correct", (Integer) 77, list1.get(3));
		// Change to list1.get(2) after implementing remove method
	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		// TODO: implement this test
		assertEquals("Size: Check if the size of the linked list is correct", 3, list1.size);
		assertEquals("Size: Check size of an empty list", 0, emptyList.size);
		assertEquals("Size: Check size of long list", LONG_LIST_LENGTH, longerList.size);
	}

	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
        // TODO: implement this test
		try {
			list1.add(2, null);
			fail("Check null pointer exception");
		} catch (NullPointerException e) {

		}
		try {
			list1.add(-1, 45);
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {

		}

		try {
			list1.add(20, 45);
			fail("Check out of bound");
		} catch (IndexOutOfBoundsException e) {
			
		}

		list1.add(2, 99);
		assertEquals("AddAtIndex: check if element is added at correct postion", (Integer)99, list1.get(2));

		//System.out.println(list1);
		list1.add(4, 66);
		assertEquals("AddAtIndex: check if element is added at the end position", (Integer) 66, list1.get(4));
	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
	    // TODO: implement this test
		try {
			list1.add(2, null);
			fail("Check null pointer exception");
		} catch (NullPointerException e) {

		}
		try {
			list1.set(-1, 65);
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {

		}
		try {
			list1.set(21, 65);
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {

		}

		//System.out.println(list1);
		list1.set(2, 90);
		assertEquals("Set: Check if the element at specified index is correctly set", (Integer) 90, list1.get(2));
	    
	}

	// TODO: Optionally add more test methods.
}
