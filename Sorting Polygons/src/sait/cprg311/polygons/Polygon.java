package sait.cprg311.polygons;

import sait.cprg311.enumerations.CompareType;

/**
 *  @author awassill
 *	@version Nov 2 2015
 *	Class Description: Abstract class to hold the binding agreement and attributes for implementing polygon types.
 *  Constructs and calculates polygon attributes using inputted parameters.
 */
public abstract class Polygon implements Comparable<Polygon>
{
    //Attributes
    protected double baseArea;
    protected double value;
    protected double height;
    protected double volume;
    protected CompareType compareType;

    /**
     * Constructor to build the polygon.
     * @param compareType the compare type given by the user at initial execution time.
     * @param height the height of the polygon
     * @param value the value which holds the side length, or radius, depending on the implementing polygon
     */
    public Polygon(CompareType compareType, double height, double value)
    {
        this.compareType = compareType;
        this.height = height;
        this.value = value;
        this.baseArea = calculateBaseArea();
        this.volume = calculateVolume();
    }

    /**
     * Returns the calculated base area of the polygon.
     * Preconditions: A valid implementing polygon exists and the base area has been calculated.
     * Post-conditions: The base area of the polygon is returned.
     * @return double the calculated base area of the polygon
     */
    public double getBaseArea()
    {
        return baseArea;
    }

    /**
     * Returns the height of the polygon.
     * Preconditions: A valid implementing polygon exists and the height has been initialized.
     * Post-conditions: The height of the polygon is returned.
     * @return double the height of the polygon
     */
    public double getHeight()
    {
        return height;
    }

    /**
     * Returns the calculated volume of the polygon.
     * Preconditions: A valid implementing polygon exists and the volume has been calculated.
     * Post-conditions: The volume of the polygon is returned.
     * @return double the calculated volume of the polygon
     */
    public double getVolume()
    {
        return volume;
    }

    /**
     * Calculates the base area of the polygon.
     * Preconditions: A valid implementing polygon exists.
     * Post-conditions: The base area of the polygon is calculated.
     * @return the base area
     */
    public abstract double calculateBaseArea();

    /**
     * Calculates the volume of the polygon.
     * Preconditions: A valid implementing polygon exists.
     * Post-conditions: The volume of the polygon is calculated.
     * @return the base area
     */
    public abstract double calculateVolume();

    /**
     * Method to compare polygons using the compare type enumeration.
     * @param p the polygon to compare
     * @return 1 if the calling polygon is greater than the compared polygon,
     * -1 if the calling polygon is less than the compared polygon, and 0 if the polygons are equal
     */
    public int compareTo(Polygon p)
    {
        switch (compareType)
        {
            case VOLUME:
                if(this.getVolume() > p.getVolume())
                    return 1;
                else if(this.getVolume() < p.getVolume())
                    return -1;
                else
                    return 0;
            case HEIGHT:
                if(this.getHeight() > p.getHeight())
                    return 1;
                else if(this.getHeight() < p.getHeight())
                    return -1;
                else
                    return 0;
            case BASE_AREA:
                if(this.getBaseArea() > p.getBaseArea())
                    return 1;
                else if(this.getBaseArea() < p.getBaseArea())
                    return -1;
                else
                    return 0;
            default:
                return -5;
        }
    }

    /**
     * Method to print out a string representation of the polygon using shared values
     * such as height, base area and volume.
     * @return the string representation of the polygon
     */
    @Override
    public String toString()
    {
        return "\nHeight: " + height +
                "\nBase Area: " + baseArea
                + "\nVolume: " + volume;
    }
}
