package sait.cprg311.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sait.cprg311.exceptions.InvalidNoteException;
import sait.cprg311.note.Note;

public class NoteTest
{
	@Before
	public void setUp() throws Exception
	{
		
	}
	
	@After
	public void tearDown() throws Exception
	{
		
	}
	
	@Test
	public void testValidFrequency()
	{
		try
		{
			assertNotNull("Note should not be null", new Note(440.00) );
			assertEquals(new Note(4186.00).getMIDIAbsoluteNumber(), new Note("C8").getMIDIAbsoluteNumber());
			assertEquals(new Note(880.00).getMIDIAbsoluteNumber(), new Note("A5").getMIDIAbsoluteNumber());
		}
		catch (InvalidNoteException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testInvalidFrequency()
	{
		try
		{
			new Note(13400.00);
			fail("InvalidNoteException should be thrown");
		}
		catch (InvalidNoteException e)
		{
			assertTrue(e.getMessage(), true);
		}
	}
	
	@Test
	public void testValidSemitones()
	{
		try
		{
			assertNotNull("Note should be constructed", new Note(20));
		}
		catch (InvalidNoteException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testInvalidSemitones()
	{
		try
		{
			new Note(59);
			fail("InvalidNoteException should be thrown");
		}
		catch (InvalidNoteException e)
		{
			assertTrue("Note was not constructed", true);
		}
	}
	
	@Test
	public void testValidString()
	{
		try
		{
			assertEquals(new Note("A4").getMIDIAbsoluteNumber(), 69);
			assertEquals(new Note("G9").getMIDIAbsoluteNumber(), 127);
			assertEquals(new Note("C-1").getMIDIAbsoluteNumber(), 0);
			assertEquals(new Note("A#4").getMIDIAbsoluteNumber(), 70);
		}
		catch (InvalidNoteException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testInvalidString()
	{
		try
		{
			new Note("r5");
			new Note("-");
			new Note("gibberish");
			fail("InvalidNoteException should be thrown");
		}
		catch (InvalidNoteException e)
		{
			assertTrue("Note was not constructed", true);
		}
	}
	
	@Test
	public void testRest()
	{
		try
		{
			assertEquals(new Note("r").noteType.toString().toLowerCase(), "r");
		}
		catch (InvalidNoteException e)
		{
			fail("Note should be a rest");
		}
	}
	
	@Test
	public void testLength()
	{
		try
		{
			assertEquals(new Note("A4-").getNoteLength(), 400);
		} 
		catch (InvalidNoteException e)
		{
			fail("Length should be 400");
		}
	}
}
