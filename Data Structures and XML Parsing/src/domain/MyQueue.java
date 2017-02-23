package domain;

import exceptions.EmptyQueueException;
import utilities.Iterator;
import utilities.QueueADT;

/**
 * Queue Class
 * Created by 645111 on 11/24/2015.
 */
public class MyQueue<T> implements QueueADT
{
    private DLL list = new DLL();

    @Override
    @SuppressWarnings("unchecked")
    public void enqueue(Object toAdd) throws NullPointerException
    {
        if(toAdd == null)
            throw new NullPointerException("Element specified is null");

        list.add(toAdd);
    }

    @Override
    public Object dequeue() throws EmptyQueueException
    {
        if(list.isEmpty())
            throw new EmptyQueueException("Queue is empty.");

        return list.removeFirst();
    }

    @Override
    public Object peek() throws EmptyQueueException
    {
        if(list.isEmpty())
            throw new EmptyQueueException("Queue is empty.");

        return list.getFirst();
    }

    @Override
    public void dequeueAll()
    {
        list.clear();
    }

    @Override
    public boolean isEmpty()
    {
        return list.isEmpty();
    }

    @Override
    public Iterator iterator()
    {
        return list.iterator();
    }

    @Override
    public boolean equals(QueueADT that)
    {
        if(this.size() != that.size())
            return false;

        Object[] thisArray = this.toArray();
        Object[] thatArray = that.toArray();

        for (int i = 0; i < this.size(); i++)
        {
            if(thisArray[i] != thatArray[i])
                return false;
        }

        return true;
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
    public boolean isFull()
    {
        return false;
    }

    @Override
    public int size()
    {
        return list.size();
    }
}
