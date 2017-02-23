package sait.cprg311.application;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

import sait.cprg311.exceptions.InvalidNoteException;
import sait.cprg311.note.Note;

/**
 * @author awassill
 * @version Oct 24 2015
 * 
 *	Class Description: Application that can play a comma delimited song in the format of a text file
 * specified by the user via the command line.
 *	It validates user and file input and sends a corresponding message in case of an error.
 */
public class Song
{
	private MidiChannel [] channels;
	
	/**
	 * Constuctor for a song object. 
	 * Opens synthesizer, instrument and channel resources.
	 */
	public Song()
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
	 * Method to play the a rest length passed as an argument.
	 * @param length the length of the rest
	 */
	public void playRest(int length)
	{
		try
		{
			Thread.sleep(length);
		}
		catch(InterruptedException ie)
		{
			
		}
	}
	
	/**
	 * Method to play the note passed as an argument.
	 * @param note the note to play
	 */
	public void playNote(Note note)
	{
		channels[1].noteOn(note.getMIDIAbsoluteNumber(), 127);
      //sets the instrument to play the note.
		
		try
		{
			Thread.sleep(note.getNoteLength());
		}
		catch(InterruptedException ie)
		{
			
		}
		
		channels[1].noteOff(note.getMIDIAbsoluteNumber(),127);
	}
	
	/**
	 * Method to validate user input into a song path, read in the song file, then play the song. 
	 * Otherwise sends an error message to the user.
	 */
	public void runSong()
	{
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter name of the song you wish to play, or x to exit: ");
		String songFile = "";
		
		while(!songFile.equalsIgnoreCase("x"))
		{
			songFile = sc.nextLine();
			System.out.println();
			
			if(!songFile.equalsIgnoreCase("x"))
			{
				playSong(songFile);
				System.out.println("End of song.");
				System.out.println();
				System.out.print("Enter name of the song you wish to play, or x to exit: ");
			}
		}
		
		System.out.println("Song application closed.");
		sc.nextLine();
		sc.close();
		System.exit(0);
	}
	
	/**
	 * Method to play the song specified as a string argument.
	 * Sends error messages to the user if any are encountered.
	 * @param songFile the  name of song file to play
	 */
	public void playSong(String songFile)
	{
		songFile = "res/" + songFile.trim() + ".txt";
		BufferedReader br = null;
		String line = "";
		Note note;
		
		try
		{
			br = new BufferedReader(new FileReader(songFile));
			while ((line = br.readLine()) != null)
			{
			   //uses comma as separator
				String[] notes = line.split(",");
				
				for(int i = 0; i < notes.length; i++)
				{
					try
					{
						if(isInteger(notes[i]))
						{
							note = new Note(Integer.parseInt(notes[i]));
							playNote(note);
						}
						else if(isDouble(notes[i]))
						{
							note = new Note(Double.parseDouble(notes[i]));
							playNote(note);
						}
						else
						{
							note = new Note(notes[i]);
							
							if(note.noteType.equals("Rest"))
							{
								playRest(note.getNoteLength());
							}
							else
							{
								playNote(note);
							}
						}
					}
					catch(InvalidNoteException ine)
					{
						System.out.println(ine.getMessage());
					}
				}
			}
		}
		catch (FileNotFoundException e)
		{
			System.out.println("File not found! Make sure to enter it without extensions i.e. songtest");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (br != null)
			{
				try
				{
					br.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Method to check if the passed input is of type double.
	 * @param str input in the form of a string
	 * @return boolean returns true if the input is a double, otherwise false
	 */
	private boolean isDouble(String str)
	{
		try
		{
			Double.parseDouble(str);
			return true;
		}
		catch (NumberFormatException e)
		{
			return false;
		}
	}
	
	/**
	 * Method to check if the passed input is of type integer.
	 * @param str input in the form of a string
	 * @return boolean returns true if the input is an integer, otherwise false
	 */
	private boolean isInteger(String str)
	{
		try
		{
			Integer.parseInt(str);
			return true;
		}
		catch (NumberFormatException e)
		{
			return false;
		}
	}
}
