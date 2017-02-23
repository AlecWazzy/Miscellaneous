package sait.cprg311.polygons;

import sait.cprg311.enumerations.CompareType;

/**
 *  @author awassill
 *	@version Nov 2 2015
 *	Class Description: Class implementing polygon of triangular prism type.
 *  Constructs and calculates triangular prism attributes using inputted parameters.
 */
public class TriangularPrism extends Polygon
{
    /**
     * Constructor to build the triangular prism.
     * @param compareType the compare type given by the user at initial execution time.
     * @param height the height of the triangular prism
     * @param side the side length of the triangular prism
     */
    public TriangularPrism(CompareType compareType, double height, double side)
    {
        super(compareType, height, side);
    }

    /**
     * Calculates the base area of the triangular prism.
     * Preconditions: A valid side length value exists.
     * Post-conditions: The base area of the triangular prism is calculated.
     * @return the base area of the triangular prism
     */
    public double calculateBaseArea()
    {
        return ((value * value) * Math.sqrt(3)) / 4;
    }

    /**
     * Calculates the volume of the triangular prism.
     * Preconditions: Valid values for height and side exist.
     * Post-conditions: The volume of the triangular prism is calculated.
     * @return the volume of the triangular prism
     */
    public double calculateVolume()
    {
        return baseArea * height;
    }

    /**
     * Method to print out a string representation of the triangular prism.
     * @return the string representation of the triangular prism
     */
    @Override
    public String toString()
    {
        return "Type: Triangular Prism" +
                "\nEdge Length: " + value
                + super.toString();
    }
}
