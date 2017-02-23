package application;

import domain.MyArrayList;
import domain.MyQueue;
import domain.MyStack;
import exceptions.EmptyQueueException;
import utilities.Iterator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Application class for the XML Parser assignment.
 * Created by 645111 on 11/27/2015.
 */
public class XMLParserApplication
{
    private MyArrayList<String> lines = new MyArrayList<>();
    private MyQueue<String> errorLog = new MyQueue<>();
    private MyStack<String> openingTagStack = new MyStack<>();
    private MyStack<String> closingTagStack = new MyStack<>();
    private final Pattern OPEN_TAG_REGEX = Pattern.compile("<(.+?)>");
    private final Pattern SELF_CLOSE_TAG_REGEX = Pattern.compile("<(.+?)/>");
    private final Pattern CLOSE_TAG_REGEX = Pattern.compile("</(.+?)>");
    private int numOfParseErrors = 0;
    private int linePos = 1;

    public void run(String fileString)
    {
        try
        {
            File file = new File("res/" + fileString + ".xml");
            Scanner sc = new Scanner(file);

            while (sc.hasNext())
            {
                lines.add(sc.nextLine());
            }

            parseLines(lines);

            System.out.println("Results of parsing " + fileString + ".xml\n");

            for (int i = 0; i < errorLog.size();)
            {
                System.out.println("Error at line " + errorLog.peek() + ":");
                errorLog.dequeue();
                System.out.println(errorLog.peek() + "\n");
                errorLog.dequeue();
            }

            System.out.println("Number of parsing errors: " + numOfParseErrors);
            System.out.println("\nRun complete.");
            sc.close();
        }
        catch (FileNotFoundException ex)
        {
            System.out.println(fileString + ".xml was not found!");
        }
        catch (EmptyQueueException ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Method to parse the lines of the XML file. Returns any errors found.
     *
     * @param lines MyArrayList of lines from the XML file
     */
    private void parseLines(MyArrayList<String> lines)
    {
        for (int i = 0; i < lines.size(); i++)
        {
            lines.set(i,lines.get(i).trim());  //For each line, strip the tab characters
        }

        Iterator lineIterator = lines.iterator();

        if (lines.get(0).startsWith("<?") && lines.get(0).endsWith("?>"))
        {
            lineIterator.next();    //Ignore XML version and encoding specifics
            linePos++;
        }

        while (lineIterator.hasNext())   //For every line after
        {
            String line = lineIterator.next().toString();

            parseSingleLine(line);
            linePos++;
        }
    }

    private void parseSingleLine(String line)
    {
        Matcher SINGLE_CLOSE_TAG_MATCHER = CLOSE_TAG_REGEX.matcher(line);           //Matcher for closing tags
        Matcher SELF_CLOSE_TAG_MATCHER = SELF_CLOSE_TAG_REGEX.matcher(line);        //Matcher for self closing tags
        Matcher SINGLE_OPEN_TAG_MATCHER = OPEN_TAG_REGEX.matcher(line);             //Matcher for opening tags

        if(SELF_CLOSE_TAG_MATCHER.matches())
        {
            //Ignore self tag
        }
        else if (SINGLE_CLOSE_TAG_MATCHER.find())
        {
            SINGLE_CLOSE_TAG_MATCHER.reset();

            while (SINGLE_CLOSE_TAG_MATCHER.find())
            {
                closingTagStack.push(SINGLE_CLOSE_TAG_MATCHER.group(1).split(" ", 2)[0]);   //Extract identifier and push to stack
                checkTags(line);    //Check if the recent closing identifier matches the recent opening identifier.
            }
        }
        else if (SINGLE_OPEN_TAG_MATCHER.find())
        {
            SINGLE_OPEN_TAG_MATCHER.reset();

            while (SINGLE_OPEN_TAG_MATCHER.find())
            {
                openingTagStack.push(SINGLE_OPEN_TAG_MATCHER.group(1).split(" ", 2)[0]);    //Extract identifier and push to stack
            }
        }
    }

    private void checkTags(String line)
    {
        if (closingTagStack.peek().equals(openingTagStack.peek()))
        {
            openingTagStack.pop();  //If they do match, remove the identifiers from the stacks
            closingTagStack.pop();
        }
        else
        {
            errorLog.enqueue(Integer.toString(linePos)); //Else, add the error to the error log
            errorLog.enqueue(line);
            numOfParseErrors++;
        }
    }
}