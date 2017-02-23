package sait;

import domain.MyArrayList;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * TEST
 * Created by 645111 on 11/13/2015.
 */
public class MyArrayListTests
{
    MyArrayList<Integer> list = new MyArrayList<>(7);

    @Before
    public void setUp()
    {
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);

        System.out.println("Before");
        for(int i = 0; i < list.size(); i++)
        {
            System.out.println(list.get(i));
        }
    }

    @After
    public void tearDown()
    {
        System.out.println("After");
        for(int i = 0; i < list.size(); i++)
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
        Assert.assertEquals(null, list.get(5));
    }

    @Test
    public void testAdd()
    {
        Assert.assertEquals(true,list.add(7));
    }

    @Test
    public void testAddPos()
    {
        list.add(4, 20);
        Assert.assertEquals(Integer.valueOf(20), list.get(4));
        Assert.assertEquals(Integer.valueOf(4), list.get(5));
    }

    @Test
    public void testAddAll()
    {
        MyArrayList<Integer> tempList = new MyArrayList<>();
        tempList.add(7);
        tempList.add(8);
        tempList.add(9);
        list.addAll(tempList);
        Assert.assertEquals(Integer.valueOf(8),list.get(8));
    }

    @Test
    public void testGet()
    {
        Assert.assertEquals(Integer.valueOf(5), list.get(5));
    }

    @Test
    public void testRemovePos()
    {
        list.remove(4);
        Assert.assertEquals(Integer.valueOf(5),list.get(4));
    }

    @Test
    public void testRemove()
    {
        list.remove(4);
        Assert.assertEquals(Integer.valueOf(5),list.get(4));
    }

    @Test
    public void testSet()
    {
        list.set(6,50);
        Assert.assertEquals(Integer.valueOf(50), list.get(6));
    }

    @Test
    public void testIsEmpty()
    {
        list.clear();
        Assert.assertEquals(true, list.isEmpty());
    }

    @Test
    public void testContains()
    {
        Assert.assertEquals(true, list.contains(5));
        Assert.assertEquals(false, list.contains(50));
    }

    @Test
    public void testIndexOf()
    {
        Assert.assertEquals(4, list.indexOf(4));
    }

    @Test
    public void testToPassedArray()
    {
        Integer[] num = new Integer[7];
        Integer[] compare = new Integer[7];

        for(int i = 0; i < compare.length; i++)
        {
            compare[i] = i;
        }

        list.toArray(num);

        Assert.assertArrayEquals(num, compare);
    }

    @Test
    public void testToArray()
    {
        Integer[] num = new Integer[7];
        for(int i = 0; i < num.length; i++)
        {
            num[i] = i;
        }
        Assert.assertArrayEquals(num, list.toArray());
    }
}
