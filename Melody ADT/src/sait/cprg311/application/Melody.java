package sait.cprg311.application;

import java.util.Scanner;
import javax.sound.midi.*;
import sait.cprg311.exceptions.InvalidNoteException;
import sait.cprg311.note.Note;

/**
 * @author awassill
 *	@version Oct 24 2015
 *
 *	Class Description: Application that can play an 
 *	octave up from the note specified by the user via the command line.
 *	It validates user input and sends a corresponding message in case of an error.
 */
public class Melody
{
	private MidiChannel [] channels;
	
	/**
	 * Constuctor for a melody object. 
	 * Opens synthesizer, instrument and channel resources.
	 */
	public Melody()
	{
		Synthesizer synthesizer = null;
      
      try
      {
         synthesizer = MidiSystem.getSynthesizer();
         synthesizer.open();
      }
      catch(MidiUnavailableException mue)
      {
      	
      }
      
       Instrument [] instruments = synthesizer.getDefaultSoundbank().getInstruments();
       synthesizer.loadInstrument(instruments[30]);
       channels = synthesizer.getChannels();
	}
	
	/**
	 * Method to play the note passed as an argument.
	 * @param note the note to play
	 */
	private void playNote(Note note)
	{
		channels[1].noteOn(note.getMIDIAbsoluteNumber(), 127);
      //sets the instrument to play the note.
		
		try
		{
			Thread.sleep(1500);
		}
		catch(InterruptedException ie)
		{
			
		}
		
		channels[1].noteOff(note.getMIDIAbsoluteNumber(),127);
	}
	
	/**
	 * Method to validate user input into a valid note, form an octave, and play the notes. 
	 * Otherwise sends an error message to the user.
	 */
	public void runMelody()
	{
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter a note in the format A3# or E3, or x to exit: ");
		String line = "";
		
		while(!line.equalsIgnoreCase("x"))
		{
			try
			{
				line = sc.nextLine().trim();
				System.out.println();
				Note note = new Note(line);
				Note raisedNote = new Note(line);
				raisedNote.modifyNoteBySemitones(12);
				
				playNote(note);
				
				if(note.formOctave(raisedNote))
				{
					System.out.println("These notes form an octave!");
					playNote(raisedNote);
					
				}
				else
				{
					System.out.println("Note cannot form an octave!");
				}
			}
			catch (InvalidNoteException ine)
			{
				if(!line.equalsIgnoreCase("x"))
				{
					System.out.println("Encountered an invalid note!");
				}
			}
			System.out.println();
			if(!line.equalsIgnoreCase("x"))
				System.out.print("Enter a note in the format A3# or E3, or x to exit: ");
		}
		
		System.out.println("Melody application closed.");
		sc.nextLine();
		sc.close();
		System.exit(0);
	}
}
