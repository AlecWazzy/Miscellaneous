package sait.cprg311.note;

import sait.cprg311.exceptions.InvalidNoteException;

/**
  * @author awassill
  * @version Oct 23 2015
  * This is the contract specification for a musical pitch or note.
  * A pitch can be completely specified as one of the following:
  * 1. A number of cycles per second or frequency.
  * 2. The number of half steps above a commonly agreed upon pitch
  *    (concert pitch=440Hz = A = MIDI69) or
  * 3. The common music note name (C, D, E, F, G, A, B) with the
  *    the suffix indicating the octave number [-1,9] and an additional
  *    suffix prefix '#' for sharp notes and 'b' for flat notes or
  * 4. The MIDI absolute note number [0,127] where 60 is middle C.
  *
  * Higher pitches have higher frequencies. Two pitches are an octave apart
  * (12 semitones) if one frequency is twice the other. A semitone is
  * aproximately an increase in pitch of 1.06 times higher.
  */
public abstract class NoteADT implements Comparable<NoteADT>
{
	public enum NoteType
   {
		R("Rest"),
		C("C"),
		CSharp("C#"),
		DFlat("Db"),
		D("D"),
		DSharp("D#"),
		EFlat("Eb"),
		E("E"),
		F("F"),
		FSharp("F#"),
		GFlat("Gb"),
		G("G"),
		GSharp("G#"),
		AFlat("Ab"),
		A("A"),
		ASharp("A#"),
		BFlat("Bb"),
		B("B");
		
		 private String modifier;
		 
		 NoteType(String modifier)
		 {
			 this.modifier = modifier;
		 }
		 
		 
		 
		 public String getModifier()
		 {
			 return this.modifier;
		 }
		 
		 public static NoteType fromString(String text) throws IllegalArgumentException
		 {
			 if (text != null)
			 {
				 for (NoteType b : NoteType.values())
				 {
					 if (text.equalsIgnoreCase(b.modifier))
					 {
						 return b;
					 }
				 }
			 }
		    throw new IllegalArgumentException();
	    }
		 
		 public int getValue()
	    {
	   	 switch(this)
	   	 {
	   		 case C: return 12;
	   		 case CSharp: return 13;
	   		 case DFlat: return 13;
	   		 case D: return 14;
	   		 case DSharp: return 15;
	   		 case EFlat: return 15;
	   		 case E: return 16;
	   		 case F: return 17;
	   		 case FSharp: return 18;
	   		 case GFlat: return 18;
	   		 case G: return 19;
	   		 case GSharp: return 20;
	   		 case AFlat: return 20;
	   		 case A: return 21;
	   		 case ASharp: return 22;
	   		 case BFlat: return 22;
	   		 case B: return 23;
	   		 default: throw new IllegalStateException("");	   		
   	 	}
	    }
   }
	
	protected int 			midiNoteValue = 0;
	protected int			noteLength = 200;
	protected int			octave = 0;

	public NoteType 	noteType;

  /**
   * A semitone is aproximately an increase in pitch of 1.06 times higher.
   */
	public static final double SEMI_TONE_INCREASE_IN_PITCH = Math.pow(2.0,1.0/12.0);
	
  /**
   * The agreed upon pitch "modern concert pitch"
   */
  	public static final double HZ_CONCERT_PITCH = 440.0; //Hz
  	public static final int    MIDI_CONCERT_PITCH = 69;
  
  /**
   * The high and low limits on the range of midi numbers.
   */
  	public static final int    LOW_MIDI_ABSOLUTE_NUMBER = 0;
  	public static final int    HIGH_MIDI_ABSOLUTE_NUMBER = 127;

  
  /**
   * The constructors for a note accept a frequency as a double for Hz or
   * a number of semitones as an int above or below the concert pitch (440Hz)
   * or a music note as a String such as C#4 
   * Imperfect frequencies are tuned to the nearest half pitch
   * Notes that are outside the range of 0 - 127 are invalid
   * notes and the InvalidNoteException is raised.
   */
  	
  	/**
  	 * Method to contruct a note using a frequency in Hertz.
  	 * @param frequency the frequency in Hertz
  	 * @throws InvalidNoteException thrown if the midi value is outside the valid range.
  	 */
	public NoteADT(double frequency) throws InvalidNoteException
	{
		midiNoteValue = (int) Math.round(MIDI_CONCERT_PITCH + 12 * 
				(Math.log(frequency / HZ_CONCERT_PITCH) / Math.log(2)));
		midiNoteValueCheck(midiNoteValue);
	}
	
	/**
	 * Method to construct a note using semitones or half-steps.
	 * @param semitones the semitones or half-steps
	 * @throws InvalidNoteException thrown if the midi value is outside the valid range.
	 */
	public NoteADT(int semitones) throws InvalidNoteException
	{
		midiNoteValue = MIDI_CONCERT_PITCH + semitones;
		midiNoteValueCheck(midiNoteValue);
	}
	
	/**
	 * Method to construct a note using a string, throws InvalidNoteException if the string is not recognized.
	 * @param strNote the string to interpret as a note
	 * @throws InvalidNoteException thrown if the string is not recognized.
	 */
	public NoteADT(String strNote) throws InvalidNoteException
	{
		strNote.trim();
		
		if(strNote.endsWith("-"))
		{
			noteLength = 400;
			strNote = strNote.substring(0, strNote.length() - 1);
		}
		
		String restRegex1 = "^[rR]$";
		String noteRegex1 = "^[a-gA-G][#Bb][0-9]$";
      String noteRegex2 = "^[a-gA-G][-][1]$";
      String noteRegex3 = "^[a-gA-G][-][1][#Bb]$";
      String noteRegex4 = "^[a-gA-G][0-9]$";
      String noteRegex5 = "^[a-gA-G][0-9][#Bb]$";
      String noteRegex6 = "^[a-gA-G][#Bb][-][1]$";
      
      if(strNote.matches(restRegex1))
      {
      	noteType = NoteType.fromString("Rest");
      	if(noteType == null)
      	{
      		throw new InvalidNoteException(strNote + " is not a valid note.");
      	}
      }
      else if(strNote.matches(noteRegex1))
      {
      	noteType = NoteType.fromString(strNote.substring(0, 2));
      	if(noteType == null)
      	{
      		throw new InvalidNoteException(strNote + " is not a valid note.");
      	}
      	octave = Integer.parseInt(strNote.substring(2));
      	midiNoteValue = calculateMidi();
      }
      else if(strNote.matches(noteRegex2))
      {
      	noteType = NoteType.fromString(strNote.substring(0, 1));
      	if(noteType == null)
      	{
      		throw new InvalidNoteException(strNote + " is not a valid note.");
      	}
      	octave = Integer.parseInt(strNote.substring(1,3));
      	midiNoteValue = calculateMidi();
      }
      else if(strNote.matches(noteRegex3))
      {
      	noteType = NoteType.fromString(strNote.substring(0, 1) + strNote.substring(3));
      	if(noteType == null)
      	{
      		throw new InvalidNoteException(strNote + " is not a valid note.");
      	}
      	octave = Integer.parseInt(strNote.substring(1,3));
      	midiNoteValue = calculateMidi();
      }
      else if(strNote.matches(noteRegex4))
      {
      	noteType = NoteType.fromString(strNote.substring(0, 1));
      	if(noteType == null)
      	{
      		throw new InvalidNoteException(strNote + " is not a valid note.");
      	}
      	octave = Integer.parseInt(strNote.substring(1,2));
      	midiNoteValue = calculateMidi();
      }
      else if(strNote.matches(noteRegex5))
      {
      	noteType = NoteType.fromString(strNote.substring(0, 1) + strNote.substring(2));
      	if(noteType == null)
      	{
      		throw new InvalidNoteException(strNote + " is not a valid note.");
      	}
      	octave = Integer.parseInt(strNote.substring(1,2));
      	midiNoteValue = calculateMidi();
      }
      else if(strNote.matches(noteRegex6))
      {
      	noteType = NoteType.fromString(strNote.substring(0, 2));
      	if(noteType == null)
      	{
      		throw new InvalidNoteException(strNote + " is not a valid note.");
      	}
      	octave = Integer.parseInt(strNote.substring(2));
      	midiNoteValue = calculateMidi();
      }
      else
      {
      	throw new InvalidNoteException(strNote + " is not a valid note.");
      }
      midiNoteValueCheck(midiNoteValue);
	}

	/**
   * Returns the frequency of this note in cycles per second- Hertz (Hz)
   * 
   * Preconditions: A valid internal reference number exists for a note
   * and the numbers range from 0 (C-1) to 127 (G9).
   * 
   * Postconditions: The internal reference number is successfully converted
   * to a frequency in hertz and returned to the calling function.
   * 
   * @return double The frequency in Hz.
   */
   public abstract double getFrequencyInHz();

  /**
   * Returns the frequency of this note in semitones (half steps) above or below
   * the concert pitch (440Hz).
   * 
   * Preconditions: A valid internal reference number exists for a note
   * and the numbers range from 0 (C-1) to 127 (G9).
   * 
   * Postconditions: The internal reference number is successfully converted
   * to number of half steps or semitones above or below concert pitch (69)
   * and returned to the calling function.
   * 
   * @return int The frequency in Semitones or half steps
   */
   public abstract int getHalfSteps();

  /**
   * Returns the frequency of this note as a MIDI absolute Number.
   * 
   * Preconditions: A valid internal reference number exists for a note
   * and the numbers range from 0 (C-1) to 127 (G9).
   * 
   * Postconditions: The internal reference number is successfully returned
   * as the MIDI absolute Number to the calling function.
   *  
   * @return int The frequency MIDI absolute Number
   */
   public abstract int getMIDIAbsoluteNumber();

   /**
    * Method compares if this note is 12 semitones above or 
    * 12 semitones below the other note, then the notes form an octave.
    * 
    * Preconditions: A valid internal reference number exists and the note
    * passed into the method is a valid Note.
    * 
    * Postconditions: An octave is formed if the note passed in is exactly
    * 12 semitones above or below the internal reference MIDI absolute number.
    * If the difference is + or - 12 semitones then a boolean true is returned
    * otherwise a boolean false is returned.
    * 
    * @param note The note that can form an Octave
    * @return boolean true if they do form an octave
    */
   public abstract boolean formOctave(NoteADT note);

   /**
    * Raise or lower the pitch by any number of semitones.
    * 
    * Preconditions: A valid internal reference number exists for a note
    * and the numbers range from 0 (C-1) to 127 (G9).
    * 
    * Postconditions: The internal reference MIDI absolute number is
    * increased or decreased by the amount of the semitones passed into
    * the method.
    * 
    * @param numberOfSemitones Change int used to calculate the new note
    */
    public abstract void modifyNoteBySemitones(int numberOfSemitones) throws InvalidNoteException;

   /**
    * The compareTo method will be implemented by the Comparable interface
    * it will adhere to the Comparable interface contract referenced in
    * the Java API - java.lang.*; and will have the following signature.
    * public int comparteTo(NoteADT note);.
    */
    public abstract int compareTo(NoteADT note);
    
    /**
     * Method to return the length of the note.
     * @return noteLength the length in miliseconds 
     * that the note should be played
     */
    public int getNoteLength()
    {
   	 return noteLength;
    }
    
    /**
     * Method to validate a midi value. Throws InvalidNoteException if the midi is invalid.
     * @param midiNoteValue2 the midi vlaue to validate
     * @throws InvalidNoteException thrown if the midi value is outside the valid range.
     */
    public void midiNoteValueCheck(int midiNoteValue2) throws InvalidNoteException
    {
   	 /**
		 * Reset the midi Note value to the lowest absolute value
		 * if it is lowest than the lowest absolute value.
		 */
		if(midiNoteValue2 < LOW_MIDI_ABSOLUTE_NUMBER)
		{
			throw new InvalidNoteException(midiNoteValue + " is below valid midi range.");
		}
		
		/**
		 * Reset the midi Note value to the highest absolute value
		 * if it is higher than the highest absolute value.
		 */
		if(midiNoteValue2 > HIGH_MIDI_ABSOLUTE_NUMBER)
		{
			throw new InvalidNoteException(midiNoteValue + " is above valid midi range.");
		}
    }
    
    /**
     * Method using the note type and octave to calculate the midi value.
     * @return the absolute midi value for the note
     * @throws InvalidNoteException thrown if the midi value is outside the valid range.
     */
   private int calculateMidi() throws InvalidNoteException
 	{
   	//Midi value of the note given the note type, assuming octave is 0
   	 int midiResult = noteType.getValue();
   	 
   	 midiNoteValueCheck(midiResult + (octave * 12));
   	 
   	 return midiResult + (octave * 12);
 	}
}