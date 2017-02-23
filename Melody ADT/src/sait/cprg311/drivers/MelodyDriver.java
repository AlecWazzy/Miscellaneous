package sait.cprg311.drivers;

import sait.cprg311.application.Melody;

/**
 * @author awassill
 *	@version Oct 24 2015
 *	Class Description: Driver to run the Melody application.
 */
public class MelodyDriver
{
	public static void main(String[] args)
	{
		Melody m = new Melody();
		m.runMelody();
	}
}
