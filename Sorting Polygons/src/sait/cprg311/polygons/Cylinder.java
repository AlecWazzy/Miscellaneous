package sait.cprg311.polygons;

import sait.cprg311.enumerations.CompareType;

/**
 *  @author awassill
 *	@version Nov 2 2015
 *	Class Description: Class implementing polygon of cylinder type.
 *  Constructs and calculates cylinder attributes using inputted parameters.
 */
public class Cylinder extends Polygon
{
    /**
     * Constructor to build the cylinder.
     * @param compareType the compare type given by the user at initial execution time.
     * @param height the height of the cylinder
     * @param value the radius of the cylinder
     */
    public Cylinder(CompareType compareType, double height, double value)
    {
        super(compareType, height, value);
    }

    /**
     * Calculates the base area of the cylinder.
     * Preconditions: A valid radius exists.
     * Post-conditions: The base area of the cylinder is calculated.
     * @return the base area of the cylinder
     */
    public double calculateBaseArea()
    {
        return Math.PI * (value * value);
    }

    /**
     * Calculates the volume of the cylinder.
     * Preconditions: Valid values for height and radius exist.
     * Post-conditions: The volume of the cylinder is calculated.
     * @return the volume of the cylinder
     */
    public double calculateVolume()
    {
        return Math.PI * (value * value) * height;
    }

    /**
     * Method to print out a string representation of the cylinder.
     * @return the string representation of the cylinder
     */
    @Override
    public String toString()
    {
        return "Type: Cylinder" +
                "\nRadius: " + value
                + super.toString();
    }
}
