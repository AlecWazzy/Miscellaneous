package sait.cprg311.enumerations;

/**
 *  @author awassill
 *	@version Nov 9 2015
 *	Class Description: Enumeration to differentiate the polygon types.
 *	Polygon types: CYLINDER Cylinder, CONE Cone, SQUAREPRISM SquarePrism,
 *	TRIANGULARPRISM TriangularPrism, PENTAGONALPRISM PentagonalPrism,
 *	OCTAGONALPRISM OctagonalPrism, PYRAMID Pyramid
 */
public enum PolygonType
{
    //Attributes
    CYLINDER("Cylinder"),
    CONE("Cone"),
    SQUAREPRISM("SquarePrism"),
    TRIANGULARPRISM("TriangularPrism"),
    PENTAGONALPRISM("PentagonalPrism"),
    OCTAGONALPRISM("OctagonalPrism"),
    PYRAMID("Pyramid");

    private String text;

    /**
     * Constructor to assign the inputted string.
     * @param type the inputted string
     */
    PolygonType(String type)
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
     * @param poly the input string to compare to the enumerations.
     * @return the matched enumeration to the input string. If no match is found, throws IllegalArgumentException.
     * @throws IllegalArgumentException no match is found
     */
    public static PolygonType getPolygonType(String poly) throws IllegalArgumentException
    {
        if(poly != null)
        {
            for(PolygonType type : PolygonType.values())
            {
                if(type.equals(poly))
                {
                    return type;
                }
            }
        }

        throw new IllegalArgumentException("Not a valid polygon type.");
    }
}
