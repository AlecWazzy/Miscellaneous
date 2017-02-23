package sait.cprg311.drivers;

import sait.cprg311.application.Song;

/**
 * @author awassill
 *	@version Oct 24 2015
 *	Class Description: Driver to run the Song application.
 */
public class SongDriver
{
	public static void main(String[] args)
	{
		Song s = new Song();
		s.runSong();
	}
}
