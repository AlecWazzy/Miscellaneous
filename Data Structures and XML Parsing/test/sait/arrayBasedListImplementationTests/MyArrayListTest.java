/**
 * MyArrayListTest
 */
package arrayBasedListImplementationTests;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import referenceBasedListImplementation.DLL;
import utilities.Iterator;
import arrayBasedListImplementation.MyArrayList;

/**
 * @author dwatson
 * @version 1.0
 * Jun 30, 2008
 *
 * Class Description: Array based implementation of the List ADT defined
 * in the CPRG 311 Assignment 4.
 * 
 */
public class MyArrayListTest
{
	//Attributes
	private MyArrayList<Integer> 		myList;
	private Integer 					one;
	private Integer 					two;
	private Integer 					three;
	private Integer 					four;
	private Integer 					five;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		myList = new MyArrayList<Integer>();
		one = new Integer(111);
		two = new Integer(222);
		three = new Integer(333);
		four = new Integer(444);
		five = new Integer(555);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception
	{
		myList = null;
		one = null;
		two = null;
		three = null;
		four = null;
		five = null;
	}
	
	@Test
	public void testListConstructor()
	{
		boolean expected = true;
		boolean actual = myList != null;
		assertEquals("List was not created ",expected ,actual);
	}

	/**
	 * Test method for {@link arrayBasedListImplementation.MyArrayList#add(int, java.lang.Object)}.
	 */
	@Test
	public void testAddIntE_ForBooleanReturn()
	{
		boolean expectedBoolean = true;
		boolean actualBoolean = myList.add(0,one);
		int expectedInteger = 111;
		int actualInteger = (myList.get(0)).intValue();

		assertEquals("Element not added to the correct position in the list ",
						expectedBoolean ,actualBoolean);
		assertEquals("Element added was not correct ",
						expectedInteger ,actualInteger);
		assertEquals("Size was not updated correctly ", 1,myList.size());
	}
	
	/**
	 * Test method for {@link arrayBasedListImplementation.MyArrayList#add(int, java.lang.Object)}.
	 */
	@Test
	public void testAddIntE_ForIndexOutOfBoundsException1()
	{
		myList.add(0,one);
		myList.add(0,two);
		myList.add(0,three);
		
		try
		{
			myList.add(4, four);
			fail("Add method failed to throw IndexOutOfBoundsException.");
		}
		catch (IndexOutOfBoundsException e)
		{
			assertTrue(true);
		}
	}
	
	/**
	 * Test method for {@link arrayBasedListImplementation.MyArrayList#add(int, java.lang.Object)}.
	 */
	@Test
	public void testAddIntE_ForIndexOutOfBoundsException2()
	{
		myList.add(0,one);
		myList.add(0,two);
		myList.add(0,three);
		
		try
		{
			myList.add(-4, four);
			fail("Add method failed to throw IndexOutOfBoundsException.");
		}
		catch (IndexOutOfBoundsException e)
		{
			assertTrue(true);
		}
	}
	
	/**
	 * Test method for {@link arrayBasedListImplementation.MyArrayList#add(java.lang.Object)}.
	 */
	@Test
	public void testAddIntE_ForItemAddedCorrectly1()
	{
		int expected = 111;
		
		myList.add(0, one);
		int actual = (myList.get(0)).intValue();
		assertEquals("Element added was not in the correct position ",
							expected ,actual);
		assertEquals("Size was not updated correctly ", 1,myList.size());
	}
	
	/**
	 * Test method for {@link arrayBasedListImplementation.MyArrayList#add(java.lang.Object)}.
	 */
	@Test
	public void testAddIntE_ForItemAddedCorrectly2()
	{
		int expected = 222;
		
		myList.add(0, one);
		myList.add(1, two);
				
		int actual = (myList.get(1)).intValue();
		assertEquals("Element added was not in the correct position ",
							expected ,actual);
		assertEquals("Size was not updated correctly ", 2,myList.size());
	}
	
	/**
	 * Test method for {@link arrayBasedListImplementation.MyArrayList#add(java.lang.Object)}.
	 */
	@Test
	public void testAddIntE_ForItemAddedCorrectly3()
	{
		int expected = 333;
		
		myList.add(0, one);
		myList.add(1, two);
		myList.add(2, three);
		
		int actual = (myList.get(2)).intValue();
		assertEquals("Element added was not in the correct position ",
							expected ,actual);
		assertEquals("Size was not updated correctly ", 3,myList.size());
	}
	
	/**
	 * Test method for {@link arrayBasedListImplementation.MyArrayList#add(java.lang.Object)}.
	 */
	@Test
	public void testAddIntE_ForItemAddedCorrectly4()
	{
		int expected = 111;
		
		myList.add(0, three);
		myList.add(0, two);
		myList.add(0, one);
		
		int actual = (myList.get(0)).intValue();
		assertEquals("Element added was not in the correct position ",
							expected ,actual);
		assertEquals("Size was not updated correctly ", 3,myList.size());
	}
	
	/**
	 * Test method for {@link arrayBasedListImplementation.MyArrayList#add(java.lang.Object)}.
	 */
	@Test
	public void testAddIntE_ForItemAddedCorrectly5()
	{
		int expected = 333;
		myList.add(one);
		myList.add(two);
		myList.add(four);
		myList.add(2, three);
		int actual = (myList.get(2)).intValue();
		assertEquals("Element added was not in the correct position ",
							expected ,actual);
		assertEquals("Size was not updated correctly ", 4,myList.size());
	}
	
	/**
	 * Test method for {@link arrayBasedListImplementation.MyArrayList#add(java.lang.Object)}.
	 */
	@Test
	public void testAddE_ForBooleanReturn()
	{
		boolean expected = true;
		boolean actual = myList.add(one);
		assertEquals("Element was not added to the end of the list correctly ",
							expected ,actual);
		assertEquals("Size was not updated correctly ", 1,myList.size());
	}
	
	/**
	 * Test method for {@link arrayBasedListImplementation.MyArrayList#add(java.lang.Object)}.
	 */
	@Test
	public void testAddE_ForNullException()
	{
		one = null;
		
		try
		{
			myList.add(one);
			fail("Add method failed to throw NullPointerException correctly.");
		}
		catch (NullPointerException e)
		{
			assertTrue(true);
		}
	}
	
	/**
	 * Test method for {@link arrayBasedListImplementation.MyArrayList#add(java.lang.Object)}.
	 */
	@Test
	public void testAddE_ForItemAddedCorrectly()
	{
		int expected = 111;
		myList.add(one);
		int actual = (myList.get(0)).intValue();
		assertEquals("Element added was not correct ",expected ,actual);
		assertEquals("Size was not updated correctly ", 1,myList.size());
	}
	
	/**
	 * Test method for {@link arrayBasedListImplementation.MyArrayList#addAll(utilities.List)}.
	 */
	@Test
	public void testAddAll_ForBooleanReturn()
	{
		boolean expected = true;
		DLL<Integer> intArray = new DLL<Integer>();
		intArray.add(three);
		intArray.add(four);
		intArray.add(five);
		
		myList.add(one);
		myList.add(two);
		boolean actual = myList.addAll(intArray);
		assertEquals("Element was not added to the end of the list correctly ",
							expected ,actual);
		assertEquals("Size was not updated correctly ", 5,myList.size());
	}
	
	/**
	 * Test method for {@link arrayBasedListImplementation.MyArrayList#addAll(utilities.List)}.
	 */
	@Test
	public void testAddAll__ForNullException()
	{
		DLL<Integer> intArray = null;
		
		myList.add(one);
		myList.add(two);
		try
		{
			myList.addAll(intArray);
			fail("Add method failed to throw NullPointerException correctly.");
		}
		catch (NullPointerException e)
		{
			assertTrue(true);
		}
	}
	
	/**
	 * Test method for {@link arrayBasedListImplementation.MyArrayList#addAll(utilities.List)}.
	 */
	@Test
	public void testAddAll_ForItemsAddedCorrectly1()
	{
		int expected = 333;
		DLL<Integer> intArray = new DLL<Integer>();
		intArray.add(three);
		intArray.add(four);
		intArray.add(five);
		
		myList.add(one);
		myList.add(two);
		
		myList.addAll(intArray);
		Integer value = myList.get(2);
		int actual = value.intValue();
		assertEquals("Elements were not added to the end of list correctly ",
							expected ,actual);
		assertEquals("Size was not updated correctly ", 5,myList.size());
	}
	
	/**
	 * Test method for {@link arrayBasedListImplementation.MyArrayList#addAll(utilities.List)}.
	 */
	@Test
	public void testAddAll_ForItemsAddedCorrectly2()
	{
		int expected = 444;
		DLL<Integer> intArray = new DLL<Integer>();
		intArray.add(three);
		intArray.add(four);
		intArray.add(five);
		
		myList.add(one);
		myList.add(two);
		
		myList.addAll(intArray);
		Integer value = myList.get(3);
		int actual = value.intValue();
		assertEquals("Elements were not added to the end of list correctly ",
							expected ,actual);
		assertEquals("Size was not updated correctly ", 5,myList.size());
	}

	/**
	 * Test method for {@link arrayBasedListImplementation.MyArrayList#clear()}.
	 */
	@Test
	public void testClear1()
	{
		myList.add(one);
		myList.add(two);
		myList.add(three);
		myList.add(four);
		
		int expected = 0;
		myList.clear();
		int actual = myList.size();
		assertEquals("List was not cleared correctly ",expected ,actual);
	}
	
	/**
	 * Test method for {@link arrayBasedListImplementation.MyArrayList#clear()}.
	 */
	@Test
	public void testClear2()
	{
		myList.add(one);
		myList.add(two);
		myList.add(three);
		myList.add(four);
		
		myList.clear();
		try
		{
			myList.get(4);
			fail("Get method failed to throw IndexOutOfBoundsException.");
		}
		catch (IndexOutOfBoundsException e)
		{
			assertTrue(true);
		}
	}

	/**
	 * Test method for {@link arrayBasedListImplementation.MyArrayList#contains(java.lang.Object)}.
	 */
	@Test
	public void testContains_ForTrue()
	{
		myList.add(one);
		myList.add(two);
		myList.add(three);
		myList.add(four);
		
		boolean expected = true;
		boolean actual = myList.contains(three);
		assertEquals("Element was not found in the current list ",
							expected ,actual);
	}
	
	/**
	 * Test method for {@link arrayBasedListImplementation.MyArrayList#contains(java.lang.Object)}.
	 */
	@Test
	public void testContains_ForFalse()
	{
		myList.add(one);
		myList.add(two);
		myList.add(three);
		myList.add(four);
		
		boolean expected = false;
		boolean actual = myList.contains(five);
		assertEquals("Element found in current list and should not ",
							expected ,actual);
	}

	/**
	 * Test method for {@link arrayBasedListImplementation.MyArrayList#get(int)}.
	 */
	@Test
	public void testGetInt1()
	{
		myList.add(one);
		myList.add(two);
		myList.add(three);
		myList.add(four);
		
		int expected = 333;
		Integer value = myList.get(2);
		int actual = value.intValue();
		assertEquals("Correct item not retrieved from list ",expected ,actual);
		assertEquals("Size was not updated correctly ", 4,myList.size());
	}
	
	/**
	 * Test method for {@link arrayBasedListImplementation.MyArrayList#get(int)}.
	 */
	@Test
	public void testGetInt2()
	{
		myList.add(one);
		myList.add(two);
		myList.add(three);
		myList.add(four);
		
		int expected = 111;
		Integer value = myList.get(0);
		int actual = value.intValue();
		assertEquals("Correct item not retrieved from list ",expected ,actual);
		assertEquals("Size was not updated correctly ", 4,myList.size());
	}
	
	/**
	 * Test method for {@link arrayBasedListImplementation.MyArrayList#get(int)}.
	 */
	@Test
	public void testGetInt3()
	{
		myList.add(one);
		myList.add(two);
		myList.add(three);
		myList.add(four);
		
		int expected = 444;
		Integer value = myList.get(3);
		int actual = value.intValue();
		assertEquals("Correct item not retrieved from list ",expected ,actual);
		assertEquals("Size was not updated correctly ", 4,myList.size());
	}
	
	/**
	 * Test method for {@link arrayBasedListImplementation.MyArrayList#get(int)}.
	 */
	@Test
	public void testGetInt_ForIndexOutOfBoundsException1()
	{
		myList.add(one);
		myList.add(two);
		myList.add(three);
		
		try
		{
			myList.get(4);
			fail("Get method failed to throw IndexOutOfBoundsException.");
		}
		catch (IndexOutOfBoundsException e)
		{
			assertTrue(true);
		}
	}
	
	/**
	 * Test method for {@link arrayBasedListImplementation.MyArrayList#get(int)}.
	 */
	@Test
	public void testGetInt_ForIndexOutOfBoundsException2()
	{
		myList.add(one);
		myList.add(two);
		myList.add(three);
		
		try
		{
			myList.get(-4);
			fail("Get method failed to throw IndexOutOfBoundsException.");
		}
		catch (IndexOutOfBoundsException e)
		{
			assertTrue(true);
		}
	}

	/**
	 * Test method for {@link arrayBasedListImplementation.MyArrayList#isEmpty()}.
	 */
	@Test
	public void testIsEmpty_ForTrue()
	{
		boolean expected = true;
		boolean actual = myList.isEmpty();
		assertEquals("List is not indicated as empty ",expected ,actual);
	}
	
	/**
	 * Test method for {@link arrayBasedListImplementation.MyArrayList#isEmpty()}.
	 */
	@Test
	public void testIsEmpty_ForFalse()
	{
		myList.add(one);
		boolean expected = false;
		boolean actual = myList.isEmpty();
		assertEquals("List indicated as empty when it's not ",expected ,actual);
	}

	/**
	 * Test method for {@link arrayBasedListImplementation.MyArrayList#iterator()}.
	 */
	@Test
	public void testIterator()
	{
		myList.add(one);
		myList.add(two);
		myList.add(three);
		myList.add(four);
		myList.add(five);
		
		Iterator<Integer> it =  myList.iterator();
		Integer value = null;
		int expected = 555;
		while(it.hasNext())
		{
			value = it.next();
		}
		int actual = value.intValue();
		assertEquals("Iterator returned incorrect value ",expected ,actual);
		assertEquals("Size was not updated correctly ", 5,myList.size());
	}

	/**
	 * Test method for {@link arrayBasedListImplementation.MyArrayList#remove(int)}.
	 */
	@Test
	public void testRemoveInt1()
	{		
		Integer expected = null;
		Integer actual = myList.remove(0);
		
		assertEquals("Removal from empty list was not handle properly ",
							expected ,actual);
		assertEquals("Size was not updated correctly ", 0,myList.size());
	}
	
	/**
	 * Test method for {@link arrayBasedListImplementation.MyArrayList#remove(int)}.
	 */
	@Test
	public void testRemoveInt2()
	{
		myList.add(one);
		
		int expected = 111;
		Integer value = myList.remove(0);
		int actual = value.intValue();
		assertEquals("Element was not removed correctly ",expected ,actual);
		assertEquals("Size was not updated correctly ", 0,myList.size());
	}
	
	/**
	 * Test method for {@link arrayBasedListImplementation.MyArrayList#remove(int)}.
	 */
	@Test
	public void testRemoveInt3()
	{
		myList.add(one);
		myList.add(two);
		myList.add(three);
		myList.add(four);
		
		int expected1 = 111;
		Integer value = myList.remove(0);
		int actual1 = value.intValue();
		
		int expected2 = 222;
		Integer value2 = myList.get(0);
		int actual2 = value2.intValue();
		
		assertEquals("Element was not removed correctly ",expected1 ,actual1);
		assertEquals("Element was not removed correctly ",expected2 ,actual2);
		assertEquals("Size was not updated correctly ", 3,myList.size());
	}
	
	/**
	 * Test method for {@link arrayBasedListImplementation.MyArrayList#remove(int)}.
	 */
	@Test
	public void testRemoveInt4()
	{
		myList.add(one);
		myList.add(two);
		myList.add(three);
		myList.add(four);
		
		int expected1 = 444;
		Integer value = myList.remove(3);
		int actual1 = value.intValue();
		
		int expected2 = 333;
		Integer value2 = myList.get(2);
		int actual2 = value2.intValue();
		
		assertEquals("Element was not removed correctly ",expected1 ,actual1);
		assertEquals("Element was not removed correctly ",expected2 ,actual2);
		assertEquals("Size was not updated correctly ", 3,myList.size());
	}
	
	/**
	 * Test method for {@link arrayBasedListImplementation.MyArrayList#remove(int)}.
	 */
	@Test
	public void testRemoveInt5()
	{
		myList.add(one);
		myList.add(two);
		myList.add(three);
		myList.add(four);
		myList.add(five);
		
		int expected1 = 333;
		Integer value = myList.remove(2);
		int actual1 = value.intValue();
		
		int expected2 = 444;
		Integer value2 = myList.get(2);
		int actual2 = value2.intValue();
		
		assertEquals("Element was not removed correctly ",expected1 ,actual1);
		assertEquals("Element was not removed correctly ",expected2 ,actual2);
		assertEquals("Size was not updated correctly ", 4,myList.size());
	}
	
	/**
	 * Test method for {@link arrayBasedListImplementation.MyArrayList#remove(int)}.
	 */
	@Test
	public void testRemoveInt_ForIndexOutOfBoundsException1()
	{
		myList.add(one);
		myList.add(two);
		myList.add(three);
		
		try
		{
			myList.remove(4);
			fail("Remove method failed to throw IndexOutOfBoundsException.");
		}
		catch (IndexOutOfBoundsException e)
		{
			assertTrue(true);
		}
	}
	
	/**
	 * Test method for {@link arrayBasedListImplementation.MyArrayList#remove(int)}.
	 */
	@Test
	public void testRemoveInt_ForIndexOutOfBoundsException2()
	{
		myList.add(one);
		myList.add(two);
		myList.add(three);
		
		try
		{
			myList.remove(-4);
			fail("Remove method failed to throw IndexOutOfBoundsException.");
		}
		catch (IndexOutOfBoundsException e)
		{
			assertTrue(true);
		}
	}

	/**
	 * Test method for {@link arrayBasedListImplementation.MyArrayList#remove(E)}.
	 */
	@Test
	public void testRemoveE1()
	{
		Integer expected = null;
		Integer actual = myList.remove(one);
		
		assertEquals("Element was not removed correctly ",expected ,actual);
		assertEquals("Size was not updated correctly ", 0,myList.size());
	}
	
	/**
	 * Test method for {@link arrayBasedListImplementation.MyArrayList#remove(int)}.
	 */
	@Test
	public void testRemoveE2()
	{
		myList.add(one);
		
		int expected = 111;
		Integer value = myList.remove(one);
		int actual = value.intValue();
		assertEquals("Element was not removed correctly ",expected ,actual);
		assertEquals("Size was not updated correctly ", 0,myList.size());
	}
	
	/**
	 * Test method for {@link arrayBasedListImplementation.MyArrayList#remove(int)}.
	 */
	@Test
	public void testRemoveE3()
	{
		myList.add(one);
		myList.add(two);
		myList.add(three);
		myList.add(four);
		
		int expected1 = 111;
		Integer value = myList.remove(one);
		int actual1 = value.intValue();
		
		int expected2 = 222;
		Integer value2 = myList.get(0);
		int actual2 = value2.intValue();
		
		assertEquals("Element was not removed correctly ",expected1 ,actual1);
		assertEquals("Element was not removed correctly ",expected2 ,actual2);
		assertEquals("Size was not updated correctly ", 3,myList.size());
	}
	
	/**
	 * Test method for {@link arrayBasedListImplementation.MyArrayList#remove(int)}.
	 */
	@Test
	public void testRemoveE4()
	{
		myList.add(one);
		myList.add(two);
		myList.add(three);
		myList.add(four);
		
		int expected1 = 444;
		Integer value = myList.remove(four);
		int actual1 = value.intValue();
		
		int expected2 = 333;
		Integer value2 = myList.get(2);
		int actual2 = value2.intValue();
		
		assertEquals("Element was not removed correctly ",expected1 ,actual1);
		assertEquals("Element was not removed correctly ",expected2 ,actual2);
		assertEquals("Size was not updated correctly ", 3,myList.size());
	}
	
	/**
	 * Test method for {@link arrayBasedListImplementation.MyArrayList#remove(int)}.
	 */
	@Test
	public void testRemoveE5()
	{
		myList.add(one);
		myList.add(two);
		myList.add(three);
		myList.add(four);
		myList.add(five);
		
		int expected1 = 333;
		Integer value = myList.remove(three);
		int actual1 = value.intValue();
		
		int expected2 = 444;
		Integer value2 = myList.get(2);
		int actual2 = value2.intValue();
		
		assertEquals("Element was not removed correctly ",expected1 ,actual1);
		assertEquals("Element was not removed correctly ",expected2 ,actual2);
		assertEquals("Size was not updated correctly ", 4,myList.size());
	}
	
	/**
	 * Test method for {@link arrayBasedListImplementation.MyArrayList#remove(E)}.
	 */
	@Test
	public void testRemoveE_ForNullPointerException()
	{
		myList.add(one);
		myList.add(two);
		myList.add(three);
		myList.add(four);
		
		three = null;
		
		try
		{
			myList.remove(three);
			fail("Remove method failed to throwNullPointerException.");
		}
		catch (NullPointerException e)
		{
			assertTrue(true);
		}
	}

	/**
	 * Test method for {@link arrayBasedListImplementation.MyArrayList#set(int, E)}.
	 */
	@Test
	public void testSet1()
	{
		myList.add(one);
		Integer toChange = Integer.valueOf(1111);
		
		int expected1 = 111;
		Integer value = myList.set(0, toChange);
		int actual1 = value.intValue();
		
		int expected2 = 1111;
		Integer value1 = myList.get(0);
		int actual2 = value1.intValue();
		
		assertEquals("Original element not returned correctly ",
							expected1 ,actual1);
		assertEquals("Element was not changed correctly ",expected2 ,actual2);
		assertEquals("Size was not updated correctly ", 1,myList.size());
	}
	
	/**
	 * Test method for {@link arrayBasedListImplementation.MyArrayList#set(int, E)}.
	 */
	@Test
	public void testSet2()
	{
		myList.add(one);
		myList.add(two);
		myList.add(three);
		myList.add(four);
		
		Integer toChange = Integer.valueOf(4444);
		
		int expected1 = 444;
		Integer value = myList.set(3, toChange);
		int actual1 = value.intValue();
		
		int expected2 = 4444;
		Integer value1 = myList.get(3);
		int actual2 = value1.intValue();
		
		assertEquals("Original element was not returned correctly ",
								expected1 ,actual1);
		assertEquals("Element was not changed correctly ",expected2 ,actual2);
		assertEquals("Size was not updated correctly ", 4,myList.size());
	}
	
	/**
	 * Test method for {@link arrayBasedListImplementation.MyArrayList#set(int, E)}.
	 */
	@Test
	public void testSet3()
	{
		myList.add(one);
		myList.add(two);
		myList.add(three);
		myList.add(four);
		myList.add(five);
		
		Integer toChange = Integer.valueOf(3333);
		
		int expected1 = 333;
		Integer value = myList.set(2, toChange);
		int actual1 = value.intValue();
		
		int expected2 = 3333;
		Integer value1 = myList.get(2);
		int actual2 = value1.intValue();
		
		assertEquals("Original element was not returned correctly ",
							expected1,actual1);
		assertEquals("Element was not changed correctly ",expected2,actual2);
		assertEquals("Size was not updated correctly ", 5,myList.size());
	}
	
	/**
	 * Test method for {@link arrayBasedListImplementation.MyArrayList#set(int, E)}.
	 */
	@Test
	public void testSet_ForNullPointerException()
	{
		myList.add(one);
		myList.add(two);
		myList.add(three);
		myList.add(four);
		
		Integer toChange = null;
		
		try
		{
			myList.set(2, toChange);
			fail("Set method failed to throw the NullPointerException.");
		}
		catch (NullPointerException e)
		{
			assertTrue(true);
		}
	}
	
	/**
	 * Test method for {@link arrayBasedListImplementation.MyArrayList#set(int, E)}.
	 */
	@Test
	public void testSet_ForIndexOutOfBoundsException1()
	{
		Integer toChange = Integer.valueOf(3333);
		
		try
		{
			myList.set(0, toChange);
			fail("Set method failed to throw the IndexOutOfBoundsException.");
		}
		catch (IndexOutOfBoundsException e)
		{
			assertTrue(true);
		}
	}
	
	/**
	 * Test method for {@link arrayBasedListImplementation.MyArrayList#set(int, E)}.
	 */
	@Test
	public void testSet_ForIndexOutOfBoundsException2()
	{
		myList.add(one);
		myList.add(two);
		myList.add(three);
		
		Integer toChange = Integer.valueOf(3333);
		
		try
		{
			myList.set(4, toChange);
			fail("Set method failed to throw IndexOutOfBoundsException.");
		}
		catch (IndexOutOfBoundsException e)
		{
			assertTrue(true);
		}
	}
	
	/**
	 * Test method for {@link arrayBasedListImplementation.MyArrayList#set(int, E)}.
	 */
	@Test
	public void testSet_ForIndexOutOfBoundsException3()
	{
		myList.add(one);
		myList.add(two);
		myList.add(three);
		
		Integer toChange = Integer.valueOf(3333);
		
		try
		{
			myList.set(-4, toChange);
			fail("Set method failed to throw IndexOutOfBoundsException.");
		}
		catch (IndexOutOfBoundsException e)
		{
			assertTrue(true);
		}
	}

	/**
	 * Test method for {@link arrayBasedListImplementation.MyArrayList#size()}.
	 */
	@Test
	public void testSize()
	{
		int expected = 5;
		myList.add(one);
		myList.add(two);
		myList.add(three);
		myList.add(four);
		myList.add(five);
		int actual = myList.size();
		assertEquals("Size of the array was returned correctly ",
							expected,actual);
	}
	/**
	 * Test method for {@link arrayBasedListImplementation.MyArrayList#toArray(T[])}.
	 */
	@Test
	public void testToArrayTArray()
	{
		int expectedStart = 0;
		int expectedEnd = 499;
		
		Integer[] test = new Integer[500];
		for (int i = 0; i < 500; i++)
		{
			test[i] = new Integer(i);
			myList.add(new Integer(i));
		}
		
		Integer[] back = new Integer[2];
		
		Object [] temp = myList.toArray(back);
		
		if(temp.length > back.length)
		{
			back = new Integer[temp.length];
		}
		
		for(int i = 0; i < temp.length; i++)
		{
			back[i] = (Integer)temp[i];
		}
		int actualStart = back[0];
		int actualEnd = back[499];
		
		assertEquals("Did not get proper values.", expectedStart,actualStart);
		assertEquals("Did not get proper values.", expectedEnd,actualEnd);
	}

	/**
	 * Test method for {@link arrayBasedListImplementation.MyArrayList#toArray()}.
	 */
	@Test
	public void testToArray1()
	{
		myList.add(one);
		myList.add(two);
		myList.add(three);
		myList.add(four);
		myList.add(five);
		
		int expected = 333;
		
		Object[] returned = myList.toArray();
		Integer value = (Integer)returned[2];
		int actual = value.intValue();
		assertEquals("The array was returned correctly ",expected,actual);
	}
}
