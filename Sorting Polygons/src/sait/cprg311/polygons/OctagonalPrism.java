package sait.cprg311.polygons;

import sait.cprg311.enumerations.CompareType;

/**
 *  @author awassill
 *	@version Nov 2 2015
 *	Class Description: Class implementing polygon of octagonal prism type.
 *  Constructs and calculates octagonal prism attributes using inputted parameters.
 */
public class OctagonalPrism extends Polygon
{
    /**
     * Constructor to build the octagonal prism.
     * @param compareType the compare type given by the user at initial execution time.
     * @param height the height of the octagonal prism
     * @param side the side length of the octagonal prism
     */
    public OctagonalPrism(CompareType compareType, double height, double side)
    {
        super(compareType, height, side);
    }

    /**
     * Calculates the base area of the octagonal prism.
     * Preconditions: A valid side length value exists.
     * Post-conditions: The base area of the octagonal prism is calculated.
     * @return the base area of the octagonal prism
     */
    public double calculateBaseArea()
    {
        return 2 * (1 + Math.sqrt(2)) * (value * value);
    }

    /**
     * Calculates the volume of the octagonal prism.
     * Preconditions: Valid values for height and side exist.
     * Post-conditions: The volume of the octagonal prism is calculated.
     * @return the volume of the octagonal prism
     */
    public double calculateVolume()
    {
        return baseArea * height;
    }

    /**
     * Method to print out a string representation of the octagonal prism.
     * @return the string representation of the octagonal prism
     */
    @Override
    public String toString()
    {
        return "Type: Octagonal Prism" +
                "\nEdge Length: " + value
                + super.toString();
    }
}
    //octagonal prism