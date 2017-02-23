package sait.cprg311.polygons;

import sait.cprg311.enumerations.CompareType;

/**
 *  @author awassill
 *	@version Nov 2 2015
 *	Class Description: Class implementing polygon of square prism type.
 *  Constructs and calculates square prism attributes using inputted parameters.
 */
public class SquarePrism extends Polygon
{
    /**
     * Constructor to build the square prism.
     * @param compareType the compare type given by the user at initial execution time.
     * @param height the height of the square prism
     * @param side the side length of the square prism
     */
    public SquarePrism(CompareType compareType, double height, double side)
    {
        super(compareType, height, side);
    }

    /**
     * Calculates the base area of the square prism.
     * Preconditions: A valid side length value exists.
     * Post-conditions: The base area of the square prism is calculated.
     * @return the base area of the square prism
     */
    public double calculateBaseArea() {
        return value * value;
    }

    /**
     * Calculates the volume of the square prism.
     * Preconditions: Valid values for height and side exist.
     * Post-conditions: The volume of the square prism is calculated.
     * @return the volume of the square prism
     */
    public double calculateVolume()
    {
        return (value * value) * height;
    }

    /**
     * Method to print out a string representation of the square prism.
     * @return the string representation of the square prism
     */
    @Override
    public String toString()
    {
        return "Type: Square Prism" +
                "\nEdge Length: " + value
                + super.toString();
    }
}
