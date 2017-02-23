package sait;

import domain.MyQueue;
import exceptions.EmptyQueueException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * TEST
 * Created by awassill on 2015-11-24.
 */
public class MyQueueTests
{
    MyQueue<Integer> queue = new MyQueue<>();

    @Before
    public void setUp() throws EmptyQueueException
    {
        queue.enqueue(0);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        queue.enqueue(6);

        System.out.println("Before");
        System.out.println(queue.peek());
        System.out.println("Size: " + queue.size());
    }

    @After
    public void tearDown() throws EmptyQueueException
    {
        System.out.println("After");
        System.out.println("Size: " + queue.size());

        queue.dequeueAll();
    }

    @Test
    public void testEnqueue() throws EmptyQueueException
    {
        queue.enqueue(5);
//        Assert.assertEquals((5), queue.enqueue(5));
    }

    @Test
    public void testDequeue() throws EmptyQueueException
    {
        queue.dequeue();
        Assert.assertNotEquals(0, queue.peek());
    }

    @Test
    public void testPeek() throws EmptyQueueException
    {
        Assert.assertEquals(0, queue.peek());
    }

    @Test
    public void testDequeueAll()
    {
        queue.dequeueAll();
        Assert.assertEquals(0, queue.size());
    }

    @Test
    public void testIsEmpty()
    {
        queue.dequeueAll();
        Assert.assertEquals(true, queue.isEmpty());
    }

    @Test
    public void testToArray()
    {
        Integer[] num = new Integer[7];
        for(int i = 0; i < num.length; i++)
        {
            num[i] = i;
        }
        Assert.assertArrayEquals(num, queue.toArray());
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

        queue.toArray(num);

        for (int i = 0; i < num.length; i++)
        {
            if(!num[i].equals(compare[i]))
                Assert.fail("Arrays should be identical");
        }

        assert true;
    }

    @Test
    public void testIsFull()
    {
        Assert.assertEquals(false, queue.isFull());
    }

    @Test
    public void testEquals()
    {

    }

    @Test
    public void testSize()
    {
        Assert.assertEquals(7, queue.size());
    }
}
