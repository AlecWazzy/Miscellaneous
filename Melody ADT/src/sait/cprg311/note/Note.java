package sait.cprg311.note;

import sait.cprg311.exceptions.InvalidNoteException;

/**
 * @author awassill
 *	@version Oct 23 2015
 *
 *	Class Description: Class implementation for a musical pitch or note.
 *	A pitch can be completely specified as one of the following:
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
public class Note extends NoteADT
{
	/**
  	 * Method to contruct a note using a frequency in Hertz.
  	 * @param frequency the frequency in Hertz
  	 * @throws InvalidNoteException thrown if the midi value is outside the valid range.
  	 */
	public Note(double frequency) throws InvalidNoteException
	{
		super(frequency);
	}
	
	/**
	 * Method to construct a note using semitones or half-steps.
	 * @param semitones the semitones or half-steps
	 * @throws InvalidNoteException thrown if the midi value is outside the valid range.
	 */
	public Note(int semitones) throws InvalidNoteException
	{
		super(semitones);
	}
	
	/**
	 * Method to construct a note using a string, throws InvalidNoteException if the string is not recognized.
	 * @param strNote the string to interpret as a note
	 * @throws InvalidNoteException thrown if the string is not recognized.
	 */
	public Note(String strNote) throws InvalidNoteException
	{
		super(strNote);
	}
	
	@Override
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
	public double getFrequencyInHz()
	{
		return Math.pow(SEMI_TONE_INCREASE_IN_PITCH, 
				midiNoteValue - MIDI_CONCERT_PITCH) 
				* HZ_CONCERT_PITCH;
	}
	
	@Override
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
	public int getHalfSteps()
	{
		return midiNoteValue - MIDI_CONCERT_PITCH;
	}
	
	@Override
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
	public int getMIDIAbsoluteNumber()
	{
		return midiNoteValue;
	}
	
	@Override
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
	public boolean formOctave(NoteADT note)
	{
		if(this.compareTo(note) == 12 || this.compareTo(note) == -12)
			return true;
		else
			return false;
	}
	
	@Override
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
	public void modifyNoteBySemitones(int numberOfSemitones) throws InvalidNoteException
	{
		int midiChanged = midiNoteValue + numberOfSemitones;
		midiNoteValueCheck(midiChanged);
		midiNoteValue = midiChanged;
	}
	
	@Override
	/**
    * The compareTo method will be implemented by the Comparable interface
    * it will adhere to the Comparable interface contract referenced in
    * the Java API - java.lang.*; and will have the following signature.
    * public int comparteTo(NoteADT note);.
    */
	public int compareTo(NoteADT note)
	{
		return midiNoteValue - note.midiNoteValue;
	}
}
