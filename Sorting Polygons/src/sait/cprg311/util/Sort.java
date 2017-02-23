package sait.cprg311.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *  @author awassill
 *	@version Nov 9 2015
 *	Class Description: Utility class of sorting algorithms.
 *  Each public method takes in a List of elements and sorts them in ascending order.
 */
public final class Sort
{
    /**
     * Method to sort a list of elements using the bubble sort algorithm.
     * Time complexity for bubble sort on average is: O(n^2).
     * @param list the list of elements to sort
     * @param <T> generic class which extends Comparable, allowing the sorting method to function
     */
    public static <T extends Comparable<? super T>> void bubbleSort (List<T> list)
    {
        if(list == null || list.size() ==0)
        {
            return;
        }

        boolean swapped;

        do
        {
            swapped = false;
            for(int i = 0; i < list.size() - 1; i++)
            {
                if(list.get(i).compareTo(list.get(i + 1)) == 1)
                {
                    Collections.swap(list, i, i + 1);
                    swapped = true;
                }
            }
        } while (swapped);
    }

    /**
     * Method to sort a list of elements using the selection sort algorithm.
     * Time complexity for selection sort on average is: O(n^2).
     * @param list the list of elements to sort
     * @param <T> generic class which extends Comparable, allowing the sorting method to function
     */
    public static <T extends Comparable<? super T>> void selectionSort(List<T> list)
    {
        if(list == null || list.size() ==0)
        {
            return;
        }

        for(int i = 0; i < list.size() - 1; i++)
        {
            int index = i;
            for(int j =  i + 1; j < list.size(); j++)
            {
                if(list.get(j).compareTo(list.get(index)) == -1)
                {
                    index = j;
                }
            }

            Collections.swap(list, i, index);
        }
    }

    /**
     * Method to sort a list of elements using the insertion sort algorithm.
     * Time complexity for insertion sort on average is: O(n^2).
     * @param list the list of elements to sort
     * @param <T> generic class which extends Comparable, allowing the sorting method to function
     */
    public static <T extends Comparable<? super T>> void insertionSort(List<T> list)
    {
        if(list == null || list.size() ==0)
        {
            return;
        }

        T element;
        int i;
        int j;

        for(j = 1; j < list.size(); j++)
        {
            element = list.get(j);

            for(i = j - 1; i >= 0 && list.get(i).compareTo(element) == 1; i--)
            {
                list.set(i + 1, list.get(i));
            }

            list.set(i + 1, element);
        }
    }

    /**
     * Method to sort a list of elements using the merge sort algorithm.
     * Time complexity for merge sort on average is: O(n log(n)).
     * @param list the list of elements to sort
     * @param <T> generic class which extends Comparable, allowing the sorting method to function
     */
    public static <T extends Comparable<? super T>> void mergeSort(List<T> list)
    {
        if(list == null || list.size() ==0)
        {
            return;
        }

        int size = list.size();

        List<T> temp = new ArrayList<T>(list);

        doMergeSort(list, temp, 0, size - 1);
    }

    /**
     * Method to recursively sort the list of elements.
     * @param list the list of elements
     * @param temp a temporary list of elements
     * @param low pointer for the current low value
     * @param high pointer for the current high value
     * @param <T> generic class which extends Comparable, allowing the sorting method to function
     */
    private static <T extends Comparable<? super T>> void doMergeSort(List<T> list, List<T> temp, int low, int high)
    {
        if(low < high)
        {
            int middle = low + (high - low) / 2;

            doMergeSort(list, temp, low, middle);
            doMergeSort(list, temp, middle + 1, high);
            mergeParts(list, temp, low, middle, high);
        }
    }

    /**
     * Method to merge parts of the temporary list back into the main list.
     * @param list the list of elements
     * @param temp a temporary list of elements
     * @param low pointer for the current low value
     * @param middle ponter for the current middle value
     * @param high pointer for the current high value
     * @param <T> generic class which extends Comparable, allowing the sorting method to function
     */
    private static <T extends  Comparable<? super T>> void mergeParts(List<T> list, List<T> temp, int low, int middle, int high)
    {
        for(int i = low; i <= high; i++)
        {
            temp.set(i,list.get(i));
        }

        int i = low;
        int j = middle + 1;
        int k = low;

        while (i <= middle && j <= high)
        {
            if(temp.get(i).compareTo(temp.get(j)) <= 0)
            {
                list.set(k, temp.get(i));
                i++;
            }
            else
            {
                list.set(k, temp.get(j));
                j++;
            }
            k++;
        }

        //Copy left half
        while (i <= middle)
        {
            list.set(k, temp.get(i));
            i++;
            k++;
        }
    }

    /**
     * Method to sort a list of elements using the quick sort algorithm.
     * Time complexity for quick sort on average is: O(n log(n)).
     * @param list the list of elements to sort
     * @param <T> generic class which extends Comparable, allowing the sorting method to function
     */
    public static <T extends Comparable<? super T>> void quickSort(List<T> list)
    {
        if(list == null || list.size() ==0)
        {
            return;
        }

        int start = 0;
        int size = list.size();
        doQuickSort(list,start, size - 1);
    }

    /**
     * Method to recursively sort the list of elements.
     * @param list the list of elements
     * @param low pointer for the current low value
     * @param high pointer for the current high value
     * @param <T> generic class which extends Comparable, allowing the sorting method to function
     */
    private static <T extends Comparable<? super T>> void doQuickSort(List<T> list, int low, int high)
    {
        if(low < high)
        {
            int i = low;
            int j = high;
            int middle = low + (high - low) / 2;
            T pivot = list.get(middle);

            do
            {
                while (list.get(i).compareTo(pivot) == -1) i++;

                while (list.get(j).compareTo(pivot) == 1) j--;

                if(i <= j)
                {
                    T temp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, temp);
                    i++;
                    j--;
                }
            } while (i <= j);

            if(low < j)
            {
                doQuickSort(list, low, j);
            }

            if(high > i)
            {
                doQuickSort(list, i, high);
            }
        }
    }

    /**
     * Method to sort a list of elements using the shell sort algorithm.
     * Time complexity for shell sort on average is: O((n log(n))^2).
     * @param list the list of elements to sort
     * @param <T> generic class which extends Comparable, allowing the sorting method to function
     */
    public static <T extends Comparable<? super T>> void shellSort(List<T> list)
    {
        if(list == null || list.size() ==0)
        {
            return;
        }

        int j;

        for(int gap = list.size() / 2; gap > 0; gap /= 2)
        {
            for(int i = gap; i < list.size(); i++)
            {
                T temp = list.get(i);
                for(j = i; j >= gap && temp.compareTo(list.get(j - gap)) < 0; j -= gap)
                {
                    list.set(j, list.get(j - gap));
                }
                list.set(j, temp);
            }
        }
    }
}
