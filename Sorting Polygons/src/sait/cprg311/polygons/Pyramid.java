package sait.cprg311.polygons;

import sait.cprg311.enumerations.CompareType;

/**
 *  @author awassill
 *	@version Nov 2 2015
 *	Class Description: Class implementing polygon of pyramid type.
 *  Constructs and calculates pyramid attributes using inputted parameters.
 */
public class Pyramid extends Polygon
{
    /**
     * Constructor to build the pyramid.
     * @param compareType the compare type given by the user at initial execution time.
     * @param height the height of the pyramid
     * @param side the side length of the pyramid
     */
    public Pyramid(CompareType compareType, double height, double side)
    {
        super(compareType, height, side);
    }

    /**
     * Calculates the base area of the pyramid.
     * Preconditions: A valid side length value exists.
     * Post-conditions: The base area of the pyramid is calculated.
     * @return the base area of the pyramid
     */
    public double calculateBaseArea() {
        return value * value;
    }

    /**
     * Calculates the volume of the pyramid.
     * Preconditions: Valid values for height and side exist.
     * Post-conditions: The volume of the pyramid is calculated.
     * @return the volume of the pyramid
     */
    public double calculateVolume()
    {
        return ((value * value) * height) / 3;
    }

    /**
     * Method to print out a string representation of the pyramid.
     * @return the string representation of the pyramid
     */
    @Override
    public String toString()
    {
        return "Type: Pyramid" +
                "\nEdge Length: " + value
                + super.toString();
    }
}
