package sait.cprg311.exceptions;

/**
 * @author Alec
 *	@version Oct 14 2015
 *	Class Description: Class for an exception to be thrown when an invalid note is encountered.
 */
public class InvalidNoteException extends Exception
{
	private static final long serialVersionUID = -7209954859914914861L;

	public InvalidNoteException(String message)
	{
		super(message);
	}
}
