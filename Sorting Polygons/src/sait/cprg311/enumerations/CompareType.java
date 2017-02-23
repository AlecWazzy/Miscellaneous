package sait.cprg311.enumerations;

/**
 *  @author awassill
 *	@version Nov 9 2015
 *	Class Description: Enumeration to differentiate the comparison types.
 *	Comparison types: VOLUME v, HEIGHT h, BASE_AREA a
 */
public enum CompareType
{
	//Attributes
	VOLUME("v"),
	HEIGHT("h"),
	BASE_AREA("a");
	
	private String text;

	/**
	 * Constructor to assign the inputted string.
	 * @param type the inputted string
     */
	CompareType(String type)
	{
		this.text = type;
	}

	/**
	 * Method to compare Strings to the enumeration types, returns true if there is a match.
	 * @param type the String to compare to the enumerations.
	 * @return boolean true if the String matches an enumeration, otherwise false.
     */
	public boolean equals(String type)
	{
		return this.text.equalsIgnoreCase(type);
	}

	/**
	 * Method to loop through each compare type, and compare them to the given input string.
	 * @param letter the input string to compare to the enumerations.
	 * @return the matched enumeration to the input string. If no match is found, throws IllegalArgumentException.
	 * @throws IllegalArgumentException no match is found
     */
	public static CompareType getCompareType(String letter) throws IllegalArgumentException
	{
		if(letter != null)
		{
			for(CompareType type : CompareType.values())
			{
				if(type.equals(letter))
				{
					return type;
				}
			}
		}

		throw new IllegalArgumentException("Not a valid compare type.");
	}
}
