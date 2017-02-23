package sait;

import domain.DLL;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * TEST
 * Created by 645111 on 2015-11-20.
 */
public class DLLTests
{
    DLL<String> list = new DLL<>();

    @Before
    public void setUp()
    {
        list.add("0");
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");

        System.out.println("Before");
        for (int i = 0; i < list.size(); i++)
        {
            System.out.println(list.get(i));
        }
    }

    @After
    public void tearDown()
    {
        System.out.println("After");
        for (int i = 0; i < list.size(); i++)
        {
            System.out.println(list.get(i));
        }

        list.clear();
    }

    @Test
    public void testSize()
    {
        Assert.assertEquals(7, list.size());
    }

    @Test
    public void testClear()
    {
        list.clear();
        Assert.assertEquals(0, list.size());
    }

    @Test
    public void testAddAtPos()
    {
        list.add(3,"20");
        Assert.assertEquals("20", list.get(3));
    }

    @Test
    public void testAdd()
    {
        list.add("7");
        Assert.assertEquals("7" ,list.get(7));
    }

    @Test
    public void testAddAll()
    {
        DLL<String> list2 = new DLL<>();
        list2.add("7");
        list2.add("8");
        list2.add("9");
        Assert.assertEquals(true, list.addAll(list2));
        Assert.assertEquals("7", list.get(7));
        Assert.assertEquals("9", list.get(9));
        Assert.assertEquals(list2.get(2), list.get(9));
    }

    @Test
    public void testGet()
    {
        Assert.assertEquals("5", list.get(5));
    }

    @Test
    public void testRemoveAtPos()
    {
        Assert.assertEquals(list.get(2), list.remove(2));
        Assert.assertNotEquals("2", list.get(2));
    }

    @Test
    public void testRemoveElement()
    {
        Assert.assertEquals(list.get(2), list.remove("2"));
        Assert.assertNotEquals("2", list.get(2));
    }

    @Test
    public void testSet()
    {
        Assert.assertEquals(list.get(2), list.set(2,"50"));
        Assert.assertEquals("50", list.get(2));
    }

    @Test
    public void testIsEmpty()
    {
        list.clear();
        Assert.assertEquals(true, list.isEmpty());
        DLL<String> list2 = new DLL<>();
        Assert.assertEquals(true, list2.isEmpty());
    }

    @Test
    public void testContains()
    {
        Assert.assertEquals(true, list.contains("5"));
        Assert.assertEquals(false, list.contains("70"));
    }

    @Test
    public void testToPassedArray()
    {
        String[] num = new String[7];
        String[] compare = new String[7];

        for(int i = 0; i < compare.length; i++)
        {
            compare[i] = Integer.toString(i);
        }

        list.toArray(num);

        Assert.assertArrayEquals(num, compare);
    }

    @Test
    public void testToArray()
    {
        String[] num = new String[7];
        for(int i = 0; i < num.length; i++)
        {
            num[i] = Integer.toString(i);
        }
        Assert.assertArrayEquals(num, list.toArray());
    }
}
