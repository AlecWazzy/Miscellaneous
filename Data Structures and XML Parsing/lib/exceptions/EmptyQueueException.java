package exceptions;

/**
 * Created by 645111 on 11/24/2015.
 */
public class EmptyQueueException extends Exception
{
	private static final long serialVersionUID = 1L;

	public EmptyQueueException(String message)
    {
        super(message);
    }
}
