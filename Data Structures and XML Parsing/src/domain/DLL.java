package domain;

import utilities.Iterator;
import utilities.List;

import java.util.NoSuchElementException;

/**
 * Class for Doubly Linked List
 * Created by awassill on 11/20/2015.
 */
public class DLL<T> implements List<T>
{
    protected int size;
    protected DLLNode head;
    protected DLLNode tail;

    public DLL()
    {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public int size()
    {
        return size;
    }

    @Override
    public void clear()
    {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean add(int index, T toAdd) throws NullPointerException, IndexOutOfBoundsException
    {
        rangeCheck(index);
        elementIsNullCheck(toAdd);

        DLLNode temp1 = head;

        while (--index != 0)
        {
            temp1 = temp1.next;
        }

        DLLNode temp2 = temp1.next;
        DLLNode newNode = new DLLNode(toAdd);
        newNode.next = temp2;
        temp2.prev = newNode;
        temp1.next = newNode;
        newNode.prev = temp1;
        size++;
        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean add(T toAdd) throws NullPointerException, IndexOutOfBoundsException
    {
        elementIsNullCheck(toAdd);

        DLLNode temp = new DLLNode(toAdd);

        if(isEmpty())
        {
            head = temp;
            tail = temp;
        }
        else
        {
            tail.next = temp;   //attach former tail to temp
            temp.prev = tail;   //attach temp to former tail
            tail = temp;        //assign the new tail
        }

        size++;
        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean addAll(List toAdd) throws NullPointerException
    {
        if (toAdd == null)
            throw new NullPointerException("List specified is null.");

        Iterator iterator = toAdd.iterator();

        add((T) toAdd.get(0));

        while (iterator.hasNext())
        {
            T element = (T) iterator.next();
            add(element);
        }
        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) throws IndexOutOfBoundsException, NullPointerException
    {
        if(isEmpty())
            throw new NullPointerException("The list is empty.");

        rangeCheck(index);

        if(index == size - 1)
            return getLast();

        DLLNode temp = head;
        while (index != 0)
        {
            temp = temp.next;
            index--;
        }

        return (T) temp.element;
    }

    @SuppressWarnings("unchecked")
    public T getFirst() throws NullPointerException
    {
        return (T) head.element;
    }

    @SuppressWarnings("unchecked")
    public T getLast() throws NullPointerException
    {
        return (T) tail.element;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T remove(int index) throws IndexOutOfBoundsException
    {
        rangeCheck(index);

        if(index == 0)
            return removeFirst();
        if(index == size - 1)
            return removeLast();

        DLLNode cur = head;
        while (--index != 0)
        {
            cur = cur.next;
        }

        DLLNode toBeDeleted = cur.next;
        DLLNode temp = toBeDeleted.next;
        cur.next = temp;
        temp.prev = cur;
        toBeDeleted.next = null;
        toBeDeleted.prev = null;
        size--;
        return (T) toBeDeleted.element;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T remove(T toRemove) throws NullPointerException
    {
        elementIsNullCheck(toRemove);

        if(isEmpty())
            throw new NullPointerException("List is empty.");

        DLLNode cursor = head;

        while(cursor.element != toRemove)
        {
            if(cursor.next.element == null)
                return null;

            cursor = cursor.next;
        }

        DLLNode temp1 = cursor.prev;
        DLLNode temp2 = cursor.next;

        temp1.next = temp2;
        temp2.prev = temp1;
        cursor.next = null;
        cursor.prev = null;
        size--;
        return (T) cursor.element;
    }

    @SuppressWarnings("unchecked")
    public T removeFirst() throws NullPointerException
    {
        DLLNode first = head;
        head = head.next;

        if(head != null)
            head.prev = null;

        size--;
        return (T) first.element;
    }

    @SuppressWarnings("unchecked")
    public T removeLast() throws NullPointerException
    {
        DLLNode last = tail;
        tail = tail.prev;
        tail.next = null;
        size--;
        return (T) last.element;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T set(int index, T toChange) throws NullPointerException, IndexOutOfBoundsException
    {
        rangeCheck(index);
        elementIsNullCheck(toChange);

        DLLNode temp1 = head;
        while (index != 0)
        {
            index--;
            temp1 = temp1.next;
        }

        T old = (T) temp1.element;
        temp1.element = toChange;
        return old;
    }

    public int indexOf(T element)
    {
        DLLNode temp = head;
        int i = 0;

        while(temp.element != element)
        {
            temp = temp.next;
            i++;

            if(temp == null)
                return -1;
        }

        return i;
    }

    private void rangeCheck(int index)
    {
        if(index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index is out of range: " + index + ".");
    }

    private void elementIsNullCheck(T element) throws NullPointerException
    {
        if(element == null)
            throw new NullPointerException("Element specified is null.");
    }

    @Override
    public boolean isEmpty()
    {
        return size == 0;
    }

    @Override
    public boolean contains(T toFind) throws NullPointerException
    {
        return indexOf(toFind) >= 0;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T[] toArray(T[] toHold) throws NullPointerException
    {
        if(toHold == null)
            throw new NullPointerException("Cannot copy to a null array.");

        if (toHold.length < this.size)
            toHold = (T[]) new Object[this.size];

        for (int i = 0; i < toHold.length; i++)
        {
            toHold[i] = get(i);
        }

        return toHold;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T[] toArray()
    {
        T[]arr = (T[]) new Object[this.size];

        for (int i = 0; i < arr.length; i++)
        {
            arr[i] = get(i);
        }

        return arr;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Iterator<T> iterator()
    {
        return new MyDLLIterator();
    }

    @SuppressWarnings("unchecked")
    private class MyDLLIterator implements Iterator
    {
        private DLLNode cursor = head;

        @Override
        public boolean hasNext()
        {
            return cursor.next != null;
        }

        @Override
        public T next() throws NoSuchElementException
        {
            cursor = cursor.next;
            return (T) cursor.element;
        }
    }
}
