package drivers;

import application.XMLParserApplication;

/**
 * Driver class to run the XML Parser application.
 * Created by 645111 on 11/27/2015.
 */
public class XMLParserDriver
{
    public static void main(String[] args)
    {
        XMLParserApplication xmlApp = new XMLParserApplication();
        try
        {
      	  xmlApp.run(args[0]);
        }
        catch (IndexOutOfBoundsException e)
        {
      	  System.out.println("Run the application with an xml filename as an argument!");
      	  System.out.println("For example : sample1");
        }
    }
}
