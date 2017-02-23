package sait.cprg311.application;

import sait.cprg311.enumerations.CompareType;
import sait.cprg311.enumerations.PolygonType;
import sait.cprg311.enumerations.SortType;
import sait.cprg311.polygons.*;
import sait.cprg311.util.Sort;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *  @author awassill
 *	@version Nov 9 2015
 *	Class Description: Application class for the Polygon sorting program.
 *  Takes in the file name, inputted comparison and sort types specified by the user at execution time.
 *  Reads in the text file of random figures that adds them to an ArrayList.
 *  The first value in the data file contains the number of figures in that file.
 *  Figure values are separated by spaces.
 *  The application then invokes the sorting methods to re-arrange the figures according to the compare type from the largest to smallest.
 *  The application prints the time it took to sort the array for each sorting algorithm.
 *  It then prints the first and last value and every thousandth value in between from the ArrayList of figures.
 */
public class PolygonApplication
{
    /**
     * The main method to run the Polygon application.
     * @param fileString the text file in which the figures are stored
     * @param compareType the comparison type specified by the user
     * @param sortType the sort type specified by the user
     */
    public void run(String fileString, CompareType compareType, SortType sortType)
    {
        try
        {
            File file = new File("res/" + fileString);
            Scanner sc = new Scanner(file);

            ArrayList<Polygon> polygons = new ArrayList<Polygon>(sc.nextInt());
            System.out.println("Reading file " + fileString + "......");

            long start, stop;
            start = System.currentTimeMillis();
            polygons = buildPolygonList(sc, compareType, polygons);
            stop = System.currentTimeMillis();
            double readTime = (double)(stop - start) / 1000;
            System.out.println("\nRead time for " + fileString + ": " + readTime + " seconds.\n");

            System.out.println("Sorting figures by " + compareType + " using " + sortType + " SORT......\n");
            double sortTime = sortPolygons(polygons, sortType);

            printResults(polygons, sortType, compareType, sortTime);

            System.out.println("\nRun complete.");
            sc.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Method to build the ArrayList of figures from the text file.
     * @param sc the Scanner object which reads in values from the file
     * @param compareType the comparison type specified by the user
     * @param polygons the ArrayList of figures instantiated using the size number at the begining of the text file
     * @return the built ArrayList of figures read in from the file
     */
    private ArrayList<Polygon> buildPolygonList(Scanner sc, CompareType compareType, ArrayList<Polygon> polygons)
    {
        while(sc.hasNext())
        {
            String polygonString = sc.next();
            double height;
            double value;
            PolygonType polygonType = PolygonType.getPolygonType(polygonString);

            switch (polygonType)
            {
                case CYLINDER:
                    height = sc.nextDouble();
                    value = sc.nextDouble();
                    Cylinder cylinder = new Cylinder(compareType, height, value);
                    polygons.add(cylinder);
                    break;
                case CONE:
                    height = sc.nextDouble();
                    value = sc.nextDouble();
                    Cone cone = new Cone(compareType, height, value);
                    polygons.add(cone);
                    break;
                case SQUAREPRISM:
                    height = sc.nextDouble();
                    value = sc.nextDouble();
                    SquarePrism squarePrism = new SquarePrism(compareType, height, value);
                    polygons.add(squarePrism);
                    break;
                case TRIANGULARPRISM:
                    height = sc.nextDouble();
                    value = sc.nextDouble();
                    TriangularPrism triangularPrism = new TriangularPrism(compareType, height, value);
                    polygons.add(triangularPrism);
                    break;
                case PENTAGONALPRISM:
                    height = sc.nextDouble();
                    value = sc.nextDouble();
                    PentagonalPrism pentagonalPrism = new PentagonalPrism(compareType, height, value);
                    polygons.add(pentagonalPrism);
                    break;
                case OCTAGONALPRISM:
                    height = sc.nextDouble();
                    value = sc.nextDouble();
                    OctagonalPrism octagonalPrism = new OctagonalPrism(compareType, height, value);
                    polygons.add(octagonalPrism);
                    break;
                case PYRAMID:
                    height = sc.nextDouble();
                    value = sc.nextDouble();
                    Pyramid pyramid = new Pyramid(compareType, height, value);
                    polygons.add(pyramid);
                    break;
            }
        }

        return polygons;
    }

    /**
     * Method to sort the ArrayList of figures and return the time it took in seconds.
     * The ArrayList is reversed to descending order using the Collections utility class.
     * @param polygons the ArrayList of figures to sort
     * @param sortType the sort type specified by the user
     * @return the time it took in seconds to sort the ArrayList of figures using the user specified sorting algorithm
     */
    public double sortPolygons(ArrayList<Polygon> polygons, SortType sortType)
    {
        long start, stop;

        start = System.currentTimeMillis();
        stop = 0;

        switch (sortType)
        {
            case BUBBLE:
                Sort.bubbleSort(polygons);
                stop = System.currentTimeMillis();
                Collections.reverse(polygons);
                break;
            case SELECTION:
                Sort.selectionSort(polygons);
                stop = System.currentTimeMillis();
                Collections.reverse(polygons);
                break;
            case INSERTION:
                Sort.insertionSort(polygons);
                stop = System.currentTimeMillis();
                Collections.reverse(polygons);
                break;
            case MERGE:
                Sort.mergeSort(polygons);
                stop = System.currentTimeMillis();
                Collections.reverse(polygons);
                break;
            case QUICK:
                Sort.quickSort(polygons);
                stop = System.currentTimeMillis();
                Collections.reverse(polygons);
                break;
            case SHELL:
                Sort.shellSort(polygons);
                stop = System.currentTimeMillis();
                Collections.reverse(polygons);
                break;
        }

        return (double)(stop - start) / 1000;
    }

    /**
     * Method to print the results of the sort, as well as the time taken for the sort
     * @param polygons the sorted the ArrayList of figures
     * @param sortType the sort type specified by the user
     * @param compareType the comparison type specified by the user
     * @param sortTime the time it took, in seconds, to sort the ArrayList of figures
     */
    public void printResults(ArrayList<Polygon> polygons, SortType sortType, CompareType compareType, double sortTime)
    {
        switch (compareType)
        {
            case VOLUME:

                System.out.println(polygons.get(0).getVolume());
                for(int i = 1000; i < polygons.size() - 1; i = i + 1000)
                {
                    System.out.println(polygons.get(i).getVolume());
                }
                System.out.println(polygons.get(polygons.size() - 1).getVolume());

                break;
            case BASE_AREA:

                System.out.println(polygons.get(0).getBaseArea());
                for(int i = 1000; i < polygons.size() - 1; i = i + 1000)
                {
                    System.out.println(polygons.get(i).getBaseArea());
                }
                System.out.println(polygons.get(polygons.size() - 1).getBaseArea());

                break;
            case HEIGHT:

                System.out.println(polygons.get(0).getHeight());
                for(int i = 1000; i < polygons.size() - 1; i = i + 1000)
                {
                    System.out.println(polygons.get(i).getHeight());
                }
                System.out.println(polygons.get(polygons.size() - 1).getHeight());

                break;
        }

        System.out.println("\nSort time using " + sortType + " SORT: " + sortTime + " seconds.");
    }
}
