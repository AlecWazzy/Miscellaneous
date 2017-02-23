package domain;

import utilities.Iterator;
import utilities.List;

import java.util.NoSuchElementException;

/**
 * Class for array based implementation of list.
 * Created by 645111 on 11/13/2015.
 */
public class MyArrayList<T> implements List<T>
{
    /**
     * Array buffer into which the elements of the ArrayList are stored.
     */
    private T[] arr;

    /**
     * The size of the ArrayList (the number of elements).
     */
    private int size;

    /**
     * Constructs an empty list with an initial capacity of 10.
     */
    @SuppressWarnings("unchecked")
    public MyArrayList()
    {
        //Default initial capacity
        int DEFAULT_CAPACITY = 10;
        this.arr = (T[]) new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    /**
     * Constructs an empty list with the specified initial capacity.
     * @param capacity the initial capacity of the list
     * @throws IllegalArgumentException if the capacity is negative.
     */
    @SuppressWarnings("unchecked")
    public MyArrayList(int capacity)
    {
        if(capacity >= 0)
        {
            this.arr = (T[]) new Object[capacity];
            this.size = 0;
        }
        else
            throw new IllegalArgumentException("Capacity out of bounds: " + capacity);
    }

    /**
     * Return the number of elements in this list.
     * @return the number of elements in the list
     */
    @Override
    public int size()
    {
        return this.size;
    }

    /**
     * Removes all of the elements from the list.
     */
    @Override
    public void clear()
    {
        for(int i = 0; i < size; i++)
            this.arr[i] = null;

        this.size = 0;
    }

    /**
     * Method to add an element into the list at the index specified.
     * @param index
     * 			The index at which the specified element is to be inserted.
     * 			The element is inserted before the existing element at [index],
     * 			or at the end if index is equal to the
     * 			size (<code>size()</code>).
     * @param toAdd The element to be inserted.
     * @return <tt>true</tt> if the element was added to the list successfully
     * @throws NullPointerException if the element to be added is null
     * @throws IndexOutOfBoundsException the index is out of bounds
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean add(int index, T toAdd) throws NullPointerException, IndexOutOfBoundsException
    {
        if(toAdd == null)
            throw new NullPointerException("Object to be added is null.");

        T[] arr1 = (T[]) new Object[arr.length + 1];
        System.arraycopy(arr, 0, arr1, 0, index);
        System.arraycopy(arr, index, arr1, index + 1, arr.length - (index + 1));
        arr = arr1;

        arr[index] = toAdd;
        this.size++;
        return true;
    }

    /**
     * Appends the specified element to the end of this list.
     * @param toAdd Element to be appended to this list.
     * @return <tt>true</tt> if the element is appended successfully.
     * @throws NullPointerException if the element to be added is null
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean add(T toAdd) throws NullPointerException, IndexOutOfBoundsException
    {
        if(toAdd == null)
            throw new NullPointerException("Object to be added is null.");

        if(size == arr.length)
        {
            T[] arr1 = (T[]) new Object[arr.length + 1];
            System.arraycopy(arr, 0, arr1, 0, arr.length);
            arr = arr1;
        }

        arr[size] = toAdd;
        this.size++;
        return true;
    }

    /**
     * Appends all of the elements in the specified list to the end of this list.
     * @param toAdd The new sub list to be added.
     * @return <tt>true</tt> if the list changed as a result of the call
     * @throws NullPointerException if the specified list is null
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean addAll(List toAdd) throws NullPointerException
    {
        for (int i = 0; i < toAdd.size(); i++)
        {
            this.add((T) toAdd.get(i));
        }
        return true;
//        T[] arr1 = (T[]) toAdd.toArray();
//        int newCapacity = arr1.length;
//        System.arraycopy(arr1, 0, arr, size, newCapacity);
//        this.size = this.size + newCapacity;
//        return newCapacity != 0;
    }

    /**
     * Returns the element at the specified position in the list.
     * @param index Index of element to return.
     * @return the element at the specified position in the list
     * @throws IndexOutOfBoundsException if the index is not within the valid range.
     */
    @Override
    public T get(int index) throws IndexOutOfBoundsException
    {
        return arr[index];
    }

    /**
     * Removes the element at the specified position in this list.
     * @param index The index of the element to remove.
     * @return the element that was removed from the list
     * @throws IndexOutOfBoundsException specified index is out of valid range
     */
    @Override
    public T remove(int index) throws IndexOutOfBoundsException
    {
        T oldValue = arr[index];

        int numMoved = size - index - 1;
        if(numMoved > 0)
            System.arraycopy(arr, index + 1, arr, index, numMoved);

        arr[--size] = null;

        return oldValue;
    }

    /**
     * Removes the first occurrence of the specified element from this list, if it is present.
     * @param toRemove The element to be removed from this list.
     * @return <tt>true</tt> element to be removed from the list, if present
     * @throws NullPointerException
     */
    @Override
    public T remove(T toRemove) throws NullPointerException
    {
        if(toRemove == null)
        {
            for(int index = 0; index < size; index++)
                if(arr[index] == null)
                {
                    return remove(index);
                }
        }
        else
        {
            for(int index = 0; index < size; index++)
                if(toRemove.equals(arr[index]))
                {
                    return remove(index);
                }
        }
        return null;
    }

    /**
     * Replaces the element at the specified position in the list with the specified element.
     * @param index The index of the element to replace.
     * @param toChange Element to be stored at the specified position.
     * @return the element previously at the specified position
     * @throws NullPointerException
     * @throws IndexOutOfBoundsException
     */
    @SuppressWarnings("unchecked")
    @Override
    public T set(int index, T toChange) throws NullPointerException, IndexOutOfBoundsException
    {
        T oldValue = arr[index];
        arr[index] = toChange;
        return oldValue;
    }

//    /**
//     * Checks if the given index is in range.
//     * @param index the index to check if it is within range
//     * @throws IndexOutOfBoundsException if the index out of valid range
//     */
//    private void rangeCheck(int index)
//    {
//        if(index < 0 || index >= this.size)
//            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
//    }

    /**
     * Returns <tt>true</tt> if the list contains no elements.
     * @return <tt>true</tt> if the list contains no elements
     */
    @Override
    public boolean isEmpty()
    {
        return size == 0;
    }

    /**
     * Returns <tt>true</tt> if the list contains the specified element.
     * @param toFind The element whose presence in this list is to be tested.
     * @return <tt>true</tt> if the list contains the specified element
     */
    @Override
    public boolean contains(T toFind)
    {
        return indexOf(toFind) >= 0;
    }

    /**
     * Returns the index of the first occurrence of the specified element in the list,
     * or -1 if the list does not contain the element.
     * @param toFind element to find
     * @return position of element in the list, or -1 if there is no such element.
     */
    public int indexOf(T toFind)
    {
        if(toFind == null)
        {
            for (int i = 0; i < size; i++)
                if(arr[i]==null)
                    return i;
        }
        else
        {
            for(int i = 0; i < size; i++)
                if(toFind.equals(arr[i]))
                    return i;
        }
        return -1;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object[] toArray(Object[] toHold) throws NullPointerException
    {
        if(toHold == null)
            throw new NullPointerException("Cannot copy to a null array.");

        if (toHold.length < this.size)
        {
            toHold = new Object[this.size];
        }

        System.arraycopy(this.arr, 0, toHold, 0, this.size);

        return toHold;
    }

    /**
     * Returns an array containing all of the elements in the list.
     * @return an array containing all of the elements in the list
     */
    @Override
    @SuppressWarnings("unchecked")
    public T[] toArray()
    {
        T[] temp = (T[]) new Object[size];
        System.arraycopy(arr, 0, temp, 0, size);
        return temp;
    }

    @Override
    public Iterator<T> iterator()
    {
        return new ArrayBasedIterator();
    }

    private class ArrayBasedIterator implements Iterator<T>
    {
        //Attributes
        private T[] 	 	copyOfElements;
        private int 		pos;

        //Constructors
        @SuppressWarnings("unchecked")
        public ArrayBasedIterator()
        {
            copyOfElements = (T[]) new Object[size()];
            System.arraycopy(arr, 0, copyOfElements, 0, copyOfElements.length);
        }

        /* (non-Javadoc)
         * @see linearUtilities.Iterator#hasNext()
         */
        @Override
        public boolean hasNext()
        {
            return pos < copyOfElements.length;
        }

        /* (non-Javadoc)
         * @see linearUtilities.Iterator#next()
         */
        @Override
        public T next() throws NoSuchElementException
        {
            T toReturn = copyOfElements[pos];
            pos++;
            return toReturn;
        }
    }
}
