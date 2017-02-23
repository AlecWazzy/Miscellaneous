package sait.cprg311.polygons;

import sait.cprg311.enumerations.CompareType;

/**
 *  @author awassill
 *	@version Nov 2 2015
 *	Class Description: Class implementing polygon of cone type.
 *  Constructs and calculates cone attributes using inputted parameters.
 */
public class Cone extends Polygon
{
    /**
     * Constructor to build the cone.
     * @param compareType the compare type given by the user at initial execution time.
     * @param height the height of the cone
     * @param value the radius
     */
    public Cone(CompareType compareType, double height, double value)
    {
        super(compareType, height, value);
    }

    /**
     * Calculates the base area of the cone.
     * Preconditions: A valid radius exists.
     * Post-conditions: The base area of the cone is calculated.
     * @return the base area of the cone
     */
    public double calculateBaseArea()
    {
        return Math.PI * (value * value);
    }

    /**
     * Calculates the volume of the cone.
     * Preconditions: Valid values for height and radius exist.
     * Post-conditions: The volume of the cone is calculated.
     * @return the volume of the cone
     */
    public double calculateVolume()
    {
        return (Math.PI * (value * value) * height) / 3;
    }


    /**
     * Method to print out a string representation of the cone.
     * @return the string representation of the cone
     */
    @Override
    public String toString()
    {
        return "Type: Cone" +
                "\nRadius: " + value
                + super.toString();
    }
}
