package sait.cprg311.test;

import sait.cprg311.enumerations.CompareType;
import sait.cprg311.polygons.Polygon;
import sait.cprg311.polygons.SquarePrism;
import sait.cprg311.util.Sort;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Before;
import org.junit.Test;

/**
 *  @author awassill
 *	@version Nov 9 2015
 *	Class Description: Testing class for the Polygon sorting program.
 */
public class TestSort
{
    ArrayList<Polygon> unsortedPolygons;
    ArrayList<Polygon> sortedPolygons;

    @Before
    public void setUp() throws Exception
    {
        SquarePrism sqp1 = new SquarePrism(CompareType.HEIGHT, 25026.146, 25026.146);
        SquarePrism sqp2 = new SquarePrism(CompareType.HEIGHT, 16853.123, 16853.123);
        SquarePrism sqp3 = new SquarePrism(CompareType.HEIGHT, 36643.532, 36643.532);
        SquarePrism sqp4 = new SquarePrism(CompareType.HEIGHT, 27452.242, 27452.242);
        SquarePrism sqp5 = new SquarePrism(CompareType.HEIGHT, 31149.307, 31149.307);
        SquarePrism sqp6 = new SquarePrism(CompareType.HEIGHT, 41331.134, 41331.134);
        SquarePrism sqp7 = new SquarePrism(CompareType.HEIGHT, 18432.890, 18432.890);

        unsortedPolygons = new ArrayList<Polygon>(7);
        unsortedPolygons.add(sqp1);
        unsortedPolygons.add(sqp2);
        unsortedPolygons.add(sqp3);
        unsortedPolygons.add(sqp4);
        unsortedPolygons.add(sqp5);
        unsortedPolygons.add(sqp6);
        unsortedPolygons.add(sqp7);

        sortedPolygons = new ArrayList<Polygon>(7);
        sortedPolygons.add(sqp6);
        sortedPolygons.add(sqp3);
        sortedPolygons.add(sqp5);
        sortedPolygons.add(sqp4);
        sortedPolygons.add(sqp1);
        sortedPolygons.add(sqp7);
        sortedPolygons.add(sqp2);
    }

    @Test
    public void testBubbleSort()
    {
        Sort.bubbleSort(unsortedPolygons);
        Collections.reverse(unsortedPolygons);
        assertEquals(sortedPolygons,unsortedPolygons);
    }

    @Test
    public void testSelectionSort()
    {
        Sort.selectionSort(unsortedPolygons);
        Collections.reverse(unsortedPolygons);
        assertEquals(sortedPolygons,unsortedPolygons);
    }

    @Test
    public void testInsertionSort()
    {
        Sort.insertionSort(unsortedPolygons);
        Collections.reverse(unsortedPolygons);
        assertEquals(sortedPolygons,unsortedPolygons);
    }

    @Test
    public void testMergeSort()
    {
        Sort.mergeSort(unsortedPolygons);
        Collections.reverse(unsortedPolygons);
        assertEquals(sortedPolygons,unsortedPolygons);
    }

    @Test
    public void testQuickSort()
    {
        Sort.quickSort(unsortedPolygons);
        Collections.reverse(unsortedPolygons);
        assertEquals(sortedPolygons,unsortedPolygons);
    }

    @Test
    public void testShellSort()
    {
        Sort.shellSort(unsortedPolygons);
        Collections.reverse(unsortedPolygons);
        assertEquals(sortedPolygons,unsortedPolygons);
    }
}
