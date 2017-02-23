package domain;

import utilities.Iterator;
import utilities.StackADT;

import java.util.EmptyStackException;

/**
 * Stack Class
 * Created by 645111 on 11/24/2015.
 */
public class MyStack<T> implements StackADT<T>
{
    private MyArrayList list;

    public MyStack()
    {
        list = new MyArrayList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public void push(Object toAdd) throws NullPointerException
    {
        list.add(toAdd);
    }

    @Override
    @SuppressWarnings("unchecked")
    public T pop() throws EmptyStackException
    {
        if(list.isEmpty())
            throw new EmptyStackException();

        return (T) list.remove(size() - 1);
    }

    @Override
    @SuppressWarnings("unchecked")
    public T peek() throws EmptyStackException
    {
        if(list.isEmpty())
            throw new EmptyStackException();

        return (T) list.get(size() - 1);
    }

    @Override
    public void clear()
    {
        list.clear();
    }

    @Override
    public boolean isEmpty()
    {
        return list.isEmpty();
    }

    @Override
    public Object[] toArray()
    {
        return list.toArray();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object[] toArray(Object[] holder) throws ArrayStoreException, NullPointerException
    {
        return list.toArray(holder);
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean contains(T toFind) throws NullPointerException
    {
        return list.contains(toFind);
    }

    @Override
    @SuppressWarnings("unchecked")
    public int search(T toFind)
    {
        int result = list.indexOf(toFind);

        if(result == - 1)
            return -1;

        return size() - result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Iterator<T> iterator()
    {
        return list.iterator();
    }

    @Override
    public boolean equals(StackADT that)
    {
        if(this.isEmpty() && that.isEmpty())
            return false;

        if(this.size() != that.size())
            return false;

        Iterator thisIterator = this.iterator();
        Iterator thatIterator = that.iterator();

        while(thisIterator.hasNext())
        {
            if(thisIterator.next() != thatIterator.next())
                return false;
        }

        return true;
    }

    @Override
    public int size()
    {
        return list.size();
    }
}
