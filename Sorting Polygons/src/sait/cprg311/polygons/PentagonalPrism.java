package sait.cprg311.polygons;

import sait.cprg311.enumerations.CompareType;

/**
 *  @author awassill
 *	@version Nov 2 2015
 *	Class Description: Class implementing polygon of pentagonal prism type.
 *  Constructs and calculates pentagonal prism attributes using inputted parameters.
 */
public class PentagonalPrism extends Polygon
{
	/**
	 * Constructor to build the pentagonal prism.
	 * @param compareType the compare type given by the user at initial execution time.
	 * @param height the height of the pentagonal prism
	 * @param side the side length of the pentagonal prism
	 */
	public PentagonalPrism(CompareType compareType, double height, double side)
	{
		super(compareType, height, side);
	}

	/**
	 * Calculates the base area of the pentagonal prism.
	 * Preconditions: A valid side length value exists.
	 * Post-conditions: The base area of the pentagonal prism is calculated.
	 * @return the base area of the pentagonal prism
	 */
	public double calculateBaseArea()
	{
		return (5 * (value * value)) * Math.tan(Math.toRadians(54)) / 4;
	}

	/**
	 * Calculates the volume of the pentagonal prism.
	 * Preconditions: Valid values for height and side exist.
	 * Post-conditions: The volume of the pentagonal prism is calculated.
	 * @return the volume of the pentagonal prism
	 */
	public double calculateVolume()
	{
		return baseArea * height;
	}

	/**
	 * Method to print out a string representation of the pentagonal prism.
	 * @return the string representation of the pentagonal prism
	 */
	@Override
	public String toString()
	{
		return "Type: Pentagonal Prism" +
				"\nEdge Length: " + value
				+ super.toString();
	}
}
