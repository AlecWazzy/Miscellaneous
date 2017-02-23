package sait.cprg311.enumerations;

/**
 *  @author awassill
 *	@version Nov 9 2015
 *	Class Description: Enumeration to differentiate the sort types.
 *	Comparison types: BUBBLE b, SELECTION s, INSERTION i, MERGE m, QUICK q, SHELL z
 */
public enum SortType
{
    //Attributes
    BUBBLE("b"),
    SELECTION("s"),
    INSERTION("i"),
    MERGE("m"),
    QUICK("q"),
    SHELL("z");

    private String text;

    /**
     * Constructor to assign the inputted string.
     * @param type the inputted string
     */
    SortType(String type)
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
    public static SortType getSortType(String letter) throws IllegalArgumentException
    {
        for(SortType type : SortType.values())
        {
            if(type.equals(letter))
            {
                return type;
            }
        }

        throw new IllegalArgumentException("Not a valid sort type.");
    }
}
