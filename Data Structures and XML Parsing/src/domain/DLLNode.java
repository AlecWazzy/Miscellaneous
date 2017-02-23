package domain;

/**
 * DLL NODE CLASS
 * Created by 645111 on 11/26/2015.
 */
public class DLLNode<T>
{
    protected T element;
    protected DLLNode next;
    protected DLLNode prev;

    protected DLLNode(T element)
    {
        this.element = element;
    }
}
