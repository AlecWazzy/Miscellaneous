package sait;

import domain.MyStack;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * TEST
 * Created by Alec on 2015-11-24.
 */
public class MyStackTests
{
    MyStack<Integer> stack = new MyStack<>();

    @Before
    public void setUp()
    {
        stack.push(0);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.push(6);

        System.out.println("Before");
        System.out.println(stack.peek());
        System.out.println("Size: " + stack.size());
    }

    @After
    public void tearDown()
    {
        System.out.println("After");
        System.out.println("Size: " + stack.size());

        stack.clear();
    }

    @Test
    public void testPush()
    {
        stack.push(5);
        Assert.assertEquals(Integer.valueOf(5), stack.peek());
    }

    @Test
    public void testPop()
    {
        stack.pop();
        Assert.assertNotEquals(Integer.valueOf(6), stack.peek());
    }

    @Test
    public void testPeek()
    {
        Assert.assertEquals(Integer.valueOf(6), stack.peek());
    }

    @Test
    public void testClear()
    {
        stack.clear();
        Assert.assertEquals(0, stack.size());
    }

    @Test
    public void testIsEmpty()
    {
        stack.clear();
        Assert.assertEquals(true, stack.isEmpty());
    }

    @Test
    public void testToArray()
    {
        Integer[] num = new Integer[7];
        for(int i = 0; i < num.length; i++)
        {
            num[i] = i;
        }
        Assert.assertArrayEquals(num, stack.toArray());
    }

    @Test
    public void testToPassedArray()
    {
        Integer[] num = new Integer[7];
        Integer[] compare = new Integer[7];

        for (int i = 0; i < compare.length; i++)
        {
            compare[i] = i;
        }

        stack.toArray(num);

        for (int i = 0; i < num.length; i++)
        {
            if(!num[i].equals(compare[i]))
                Assert.fail("Arrays should be identical");
        }

        assert true;
    }

    @Test
    public void testContains()
    {
        Assert.assertEquals(true, stack.contains(5));
    }

    @Test
    public void testSearch()
    {
        Assert.assertEquals(2, stack.search(5));
        Assert.assertEquals(-1, stack.search(20));
    }

    @Test
    public void testEquals()
    {
        MyStack<Integer> compare = new MyStack<>();
        for (int i = 0; i < 7; i++)
        {
            compare.push(i);
        }

        Assert.assertEquals(true, stack.equals(compare));
    }

    @Test
    public void testSize()
    {
        Assert.assertEquals(7, stack.size());
    }
}
