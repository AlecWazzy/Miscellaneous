package sait.cprg311.drivers;

import sait.cprg311.application.PolygonApplication;
import sait.cprg311.enumerations.CompareType;
import sait.cprg311.enumerations.SortType;

/**
 *  @author awassill
 *	@version Nov 9 2015
 *	Class Description: Driver to run the Polygon application.
 *  Takes in arguments for file name, type of comparison(base area, height, volume)
 *  and type of sort to run the polygons through. These arguments can be inputted in no particular order, case insensitive.
 *  Format example: –Fpolyfor3.txt –Th –Sq
 *  File name follows -F, comparison type follows -T, and sort type follows -S.
 *  Custom sort type implemented of choice: Shell Sort, with the command Z.
 */
public class PolygonDriver
{
    public static void main(String[] args)
    {
        String fileString = "default";
        String compareString = "";
        String sortString = "";

        for(String s : args)
        {

            if(s.substring(1,2).equalsIgnoreCase("f"))
            {
                fileString = s.substring(2);
            }
            else if(s.substring(1,2).equalsIgnoreCase("t"))
            {
                compareString = s.substring(2);
            }
            else if(s.substring(1,2).equalsIgnoreCase("s"))
            {
                sortString = s.substring(2);
            }
        }

        try
        {
            CompareType compareType = CompareType.getCompareType(compareString);
            SortType sortType = SortType.getSortType(sortString);
            PolygonApplication pa = new PolygonApplication();
            pa.run(fileString, compareType, sortType);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
