/**
 * Created on Dec 9, 2009
 *
 * Project: dataStructureCodeExamples 
 */
package stackImplementationTests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import stackImplementation.*;
import java.util.EmptyStackException;
import utilities.*;

/**
 * StackTest.java
 *
 * @author dwatson
 * @version 1.0
 * 
 * Class Definition: A test class to assure all stack operations work
 * according to the Stack specification.
 */
public class StackTest
{
	//Attributes
	private Stack<Integer> 				stack;
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
		stack = new Stack<Integer>();
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
		stack = null;
		one = null;
		two = null;
		three = null;
		four = null;
		five = null;
	}

	/**
	 * Test method for {@link stackImplementation.Stack#Stack()}.
	 */
	@Test
	public void testStack()
	{
		boolean expected = true;
		boolean actual = stack != null;
		assertEquals("Stack object was not created ",expected,actual);
	}

	/**
	 * Test method for {@link stackImplementation.Stack#clear()}.
	 */
	@Test
	public void testClear()
	{
		stack.push(one);
		stack.push(two);
		stack.clear();
		try
		{
			@SuppressWarnings("unused")
			Object value = stack.pop();
			fail("Pop method failed to throw EmptyStackException correctly.");
		}
		catch(EmptyStackException e)
		{
			assertTrue(true);
		}
		assertEquals("Stack size is incorrect ",0,stack.size());
	}

	/**
	 * Test method for {@link 
	 * stackImplementation.Stack#contains(java.lang.Object)}.
	 */
	@Test
	public void testContains1()
	{
		boolean expected = true;
		stack.push(one);
		stack.push(two);
		stack.push(three);
		boolean actual = stack.contains(two);
		assertEquals("Stack didn't contain the item and should have ",
							expected,actual);
		assertEquals("Stack size is incorrect ",3,stack.size());
	}
	
	/**
	 * Test method for {@link stackImplementation.Stack#contains(java.lang.Object)}.
	 */
	@Test
	public void testContains2()
	{
		boolean expected = false;
		stack.push(one);
		stack.push(two);
		stack.push(three);
		boolean actual = stack.contains(four);
		assertEquals("Stack contained the item and should not have ",
							expected,actual);
		assertEquals("Stack size is incorrect ",3,stack.size());
	}
	
	/**
	 * Test method for {@link 
	 * stackImplementation.Stack#contains(java.lang.Object)}.
	 */
	@Test
	public void testContains3()
	{
		Integer empty = null;
		stack.push(one);
		stack.push(two);
		stack.push(three);
		try
		{
			stack.contains(empty);
			fail("Contains method failed to throw NullPointerException.");
		}
		catch (NullPointerException e)
		{
			assertTrue(true);
		}
		assertEquals("Stack size is incorrect ",3,stack.size());
	}

	/**
	 * Test method for {@link stackImplementation.Stack#equals(utilities.StackADT)}.
	 */
	@Test
	public void testEqualsStackADTOfE1()
	{
		Stack<Integer> stack2 = new Stack<Integer>();
		boolean expected = true;
		stack.push(one);
		stack.push(two);
		stack.push(three);
		
		stack2.push(one);
		stack2.push(two);
		stack2.push(three);
		
		boolean actual = stack.equals(stack2);
		assertEquals("Stacks being compared are not the same and should be ",
							expected,actual);
		assertEquals("Stack size is incorrect ",3,stack.size());
		assertEquals("Stack size is incorrect ",3,stack2.size());
	}
	
	/**
	 * Test method for {@link stackImplementation.Stack#equals(utilities.StackADT)}.
	 */
	@Test
	public void testEqualsStackADTOfE2()
	{
		Stack<Integer> stack2 = new Stack<Integer>();
		boolean expected = false;
		stack.push(two);
		stack.push(one);
		stack.push(three);
		
		stack2.push(one);
		stack2.push(two);
		stack2.push(three);
		
		boolean actual = stack.equals(stack2);
		assertEquals("Stacks being compared are the same and should not be ",
							expected,actual);
		assertEquals("Stack size is incorrect ",3,stack.size());
		assertEquals("Stack size is incorrect ",3,stack2.size());
	}

	/**
	 * Test method for {@link stackImplementation.Stack#isEmpty()}.
	 */
	@Test
	public void testIsEmpty1()
	{
		boolean expected = true;
		
		boolean actual = stack.isEmpty();
		assertEquals("Stack is not empty and should have been ",
							expected,actual);
		assertEquals("Stack size is incorrect ",0,stack.size());
	}
	
	/**
	 * Test method for {@link stackImplementation.Stack#isEmpty()}.
	 */
	@Test
	public void testIsEmpty2()
	{
		boolean expected = false;
		stack.push(one);
		stack.push(two);
		stack.push(three);
		boolean actual = stack.isEmpty();
		assertEquals("Stack shows empty but should not have been ",
							expected,actual);
		assertEquals("Stack size is incorrect ",3,stack.size());
	}

	/**
	 * Test method for {@link stackImplementation.Stack#iterator()}.
	 */
	@Test
	public void testIterator1()
	{
		int expected1 = 111;
		int expected2 = 222;
		
		stack.push(one);
		stack.push(two);
		
		Iterator<Integer> it = stack.iterator();
		int actual1 = it.next();
		int actual2 = it.next();
		
		assertEquals("Stack iterator contained wrong element at position 1 ",
							expected1,actual1);
		assertEquals("Stack iterator contained wrong element at position 2 ",
				expected2,actual2);
		assertEquals("Stack size is incorrect ",2,stack.size());
	}

	/**
	 * Test method for {@link stackImplementation.Stack#iterator()}.
	 */
	@Test
	public void testIterator2()
	{
		int expected1 = 111;
		int expected2 = 222;
		int expected3 = 333;
		int expected4 = 444;
		int expected5 = 555;
		
		stack.push(one);
		stack.push(two);
		stack.push(three);
		stack.push(four);
		stack.push(five);
		
		Iterator<Integer> it = stack.iterator();
		int actual1 = it.next();
		int actual2 = it.next();
		int actual3 = it.next();
		int actual4 = it.next();
		int actual5 = it.next();
		
		assertEquals("Stack iterator contained wrong element at position 1 ",
							expected1,actual1);
		assertEquals("Stack iterator contained wrong element at position 2 ",
				expected2,actual2);
		assertEquals("Stack iterator contained wrong element at position 3 ",
				expected3,actual3);
		assertEquals("Stack iterator contained wrong element at position 4 ",
				expected4,actual4);
		assertEquals("Stack iterator contained wrong element at position 5 ",
				expected5,actual5);
		assertEquals("Stack size is incorrect ",5,stack.size());
	}

	/**
	 * Test method for {@link stackImplementation.Stack#peek()}.
	 */
	@Test
	public void testPeek1()
	{
		int expected1 = 111;
		int expected2 = 222;
		int expected3 = 333;
		int expected4 = 444;
		int expected5 = 555;
		
		stack.push(one);
		stack.push(two);
		stack.push(three);
		stack.push(four);
		stack.push(five);
		assertEquals("Stack size is incorrect ",5,stack.size());
		
		int actual5 = stack.peek();
		assertEquals("Stack peek contained wrong element at position 1 ",
				expected5,actual5);
		
		stack.pop();
		int actual4 = stack.peek();
		assertEquals("Stack peek contained wrong element at position 1 ",
				expected4,actual4);
		assertEquals("Stack size is incorrect ",4,stack.size());
		
		stack.pop();
		int actual3 = stack.peek();
		assertEquals("Stack peek contained wrong element at position 1 ",
				expected3,actual3);
		assertEquals("Stack size is incorrect ",3,stack.size());
		
		stack.pop();
		int actual2 = stack.peek();
		assertEquals("Stack peek contained wrong element at position 1 ",
				expected2,actual2);
		assertEquals("Stack size is incorrect ",2,stack.size());
		
		stack.pop();
		int actual1 = stack.peek();
		assertEquals("Stack peek contained wrong element at position 1 ",
				expected1,actual1);
		assertEquals("Stack size is incorrect ",1,stack.size());
	}
	
	/**
	 * Test method for {@link stackImplementation.Stack#peek()}.
	 */
	@Test
	public void testPeek2()
	{
		try
		{
			@SuppressWarnings("unused")
			Object value = stack.peek();
			fail("Peek method failed to throw EmptyStackException correctly.");
		}
		catch(EmptyStackException e)
		{
			assertTrue(true);
		}
		assertEquals("Stack size is incorrect ",0,stack.size());
	}

	/**
	 * Test method for {@link stackImplementation.Stack#pop()}.
	 */
	@Test
	public void testPop1()
	{
		int expected1 = 111;
		int expected2 = 222;
		int expected3 = 333;
		int expected4 = 444;
		int expected5 = 555;
		
		stack.push(one);
		stack.push(two);
		stack.push(three);
		stack.push(four);
		stack.push(five);
		assertEquals("Stack size is incorrect ",5,stack.size());
		
		int actual5 = stack.pop();
		assertEquals("Stack pop contained wrong element at position 1 ",
				expected5,actual5);	
		assertEquals("Stack size is incorrect ",4,stack.size());
		
		int actual4 = stack.pop();
		assertEquals("Stack pop contained wrong element at position 1 ",
				expected4,actual4);
		assertEquals("Stack size is incorrect ",3,stack.size());
		
		int actual3 = stack.pop();
		assertEquals("Stack pop contained wrong element at position 1 ",
				expected3,actual3);
		assertEquals("Stack size is incorrect ",2,stack.size());		
		
		int actual2 = stack.pop();
		assertEquals("Stack pop contained wrong element at position 1 ",
				expected2,actual2);
		assertEquals("Stack size is incorrect ",1,stack.size());
		
		int actual1 = stack.pop();
		assertEquals("Stack pop contained wrong element at position 1 ",
				expected1,actual1);
		assertEquals("Stack size is incorrect ",0,stack.size());
	}
	
	/**
	 * Test method for {@link stackImplementation.Stack#pop()}.
	 */
	@Test
	public void testPop2()
	{
		try
		{
			@SuppressWarnings("unused")
			Object value = stack.pop();
			fail("Pop method failed to throw EmptyStackException correctly.");
		}
		catch(EmptyStackException e)
		{
			assertTrue(true);
		}
		assertEquals("Stack size is incorrect ",0,stack.size());
	}

	/**
	 * Test method for {@link stackImplementation.Stack#push(java.lang.Object)}.
	 */
	@Test
	public void testPush1()
	{
		stack.push(one);
		int actual = stack.peek();
		assertEquals("Stack pushed wrong element to top of stack ",
				111,actual);	
		assertEquals("Stack size is incorrect ",1,stack.size());
		
		stack.push(two);
		actual = stack.peek();
		assertEquals("Stack pushed wrong element to top of stack ",
				222,actual);	
		assertEquals("Stack size is incorrect ",2,stack.size());
		
		stack.push(three);
		actual = stack.peek();
		assertEquals("Stack pushed wrong element to top of stack ",
				333,actual);	
		assertEquals("Stack size is incorrect ",3,stack.size());
		
		stack.push(four);
		actual = stack.peek();
		assertEquals("Stack pushed wrong element to top of stack ",
				444,actual);	
		assertEquals("Stack size is incorrect ",4,stack.size());
		
		stack.push(five);
		actual = stack.peek();
		assertEquals("Stack pushed wrong element to top of stack ",
				555,actual);
		assertEquals("Stack size is incorrect ",5,stack.size());
	}

	/**
	 * Test method for {@link stackImplementation.Stack#push(java.lang.Object)}.
	 */
	@Test
	public void testPush2()
	{
		Integer empty = null;
		stack.push(one);
		stack.push(two);
		stack.push(three);
		try
		{
			stack.push(empty);
			fail("Contains method failed to throw NullPointerException.");
		}
		catch (NullPointerException e)
		{
			assertTrue(true);
		}
		assertEquals("Stack size is incorrect ",3,stack.size());
	}
	
	/**
	 * Test method for {@link stackImplementation.Stack#search(java.lang.Object)}.
	 */
	@Test
	public void testSearch1()
	{
		int expected = 5;
		stack.push(one);
		stack.push(two);
		stack.push(three);
		stack.push(four);
		stack.push(five);
		assertEquals("Stack size is incorrect ",5,stack.size());
		
		int actual = stack.search(one);
		assertEquals("Value on the bottom of stack is incorrect ",
							expected,actual);
	}
	
	/**
	 * Test method for {@link stackImplementation.Stack#search(java.lang.Object)}.
	 */
	@Test
	public void testSearch2()
	{
		int expected = 1;
		stack.push(one);
		stack.push(two);
		stack.push(three);
		stack.push(four);
		stack.push(five);
		assertEquals("Stack size is incorrect ",5,stack.size());
		
		int actual = stack.search(five);
		assertEquals("Value on the top of stack is incorrect ",
							expected,actual);
	}
	
	/**
	 * Test method for {@link stackImplementation.Stack#search(java.lang.Object)}.
	 */
	@Test
	public void testSearch3()
	{
		int expected = 3;
		stack.push(one);
		stack.push(two);
		stack.push(three);
		stack.push(four);
		stack.push(five);
		assertEquals("Stack size is incorrect ",5,stack.size());
		
		int actual = stack.search(three);
		assertEquals("Value in the middle of stack is incorrect ",
							expected,actual);
	}
	
	/**
	 * Test method for {@link utilities.StackADT#size()}.
	 */
	@Test
	public void testSize()
	{
		stack.push(one);
		stack.push(two);
		stack.push(three);
		stack.push(four);
		stack.push(five);
		assertEquals("Stack size is incorrect ",5,stack.size());
	}

	/**
	 * Test method for {@link stackImplementation.Stack#toArray()}.
	 */
	@Test
	public void testToArray()
	{
		stack.push(one);
		stack.push(two);
		stack.push(three);
		stack.push(four);
		stack.push(five);
		assertEquals("Stack size is incorrect ",5,stack.size());
		
		int expected = 333;
		
		Object[] returned = stack.toArray();
		Integer value = (Integer)returned[2];
		int actual = value.intValue();
		assertEquals("The array was returned correctly ",expected,actual);
	}

	/**
	 * Test method for {@link stackImplementation.Stack#toArray(E[])}.
	 */
	@Test
	public void testToArrayEArray()
	{
		int expectedStart = 0;
		int expectedEnd = 499;
		
		Integer[] test = new Integer[500];
		for (int i = 0; i < 500; i++)
		{
			test[i] = new Integer(i);
			stack.push(new Integer(i));
		}
		
		Integer[] back = new Integer[2];
		
		Object [] temp = stack.toArray(back);
		
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
}
