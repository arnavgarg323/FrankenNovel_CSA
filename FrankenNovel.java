import java.util.*;
import java.io.File;
import java.io.PrintWriter;

/**
 * Module 9 Project: The Gutenberg FrankenNovel
 *
 * Name: Arnav Garg
 * 
 * AP Computer Science, Virtual Virginia
 */

public class FrankenNovel
{
    //////// MAIN ////////
    
    //For FILENAME, TITLE, and AUTHOR below, include appropriate text.
    
    public static void main(String[] args)
    {
        //Book 1
        File bookFile1 = new File("Frankenstein by Mary Shelley.txt");
        Book book1 = new Book(bookFile1);
        System.out.println("Book 1: Frankenstein by Mary Shelley");
        System.out.println("Word count: " + book1.getWordCount());
        System.out.println("Longest word: " + book1.getLongestWord() + "\n");
        
        //Book 2
        File bookFile2 = new File("The Odyssey by Homer.txt");
        Book book2 = new Book(bookFile2);
        System.out.println("Book 2: The Odyssey by Homer");
        System.out.println("Word count: " + book2.getWordCount());
        System.out.println("Longest word: " + book2.getLongestWord() + "\n");
       
        
        //FrankenNovel
        writeFrankenNovel(book1, book2);        
        System.out.println("My FrankenNovel, The Odyssey of Frankenstein, has been created in frankenOutput.txt.");
        
    }
    
    //////// METHODS ////////
    
    /* Writes to an output file shorterBook's even-indexed words and longerBook's odd-indexed words.
     * After end of shorterBook's list, all remaining words from longerBook's list will print.
     * Uses a try-catch block.   
       @param b1 -- the first Book object
              b2 -- the second Book object
    */
    public static void writeFrankenNovel(Book b1, Book b2)
    {
        Book shorterBook;
        Book longerBook;
        
        /* Determine shorterBook and longerBook. */
        
        if(b1.getWordCount() > b2.getWordCount())
        {
            longerBook = b1;
            shorterBook = b2;
        }
        else
        {
            longerBook = b2;
            shorterBook = b1;
        }
        
        
        try
        {
           PrintWriter outputFile = new PrintWriter("frankenOutput.txt");
           
           /* Print appropriate words to the output file. */
           for (int i = 0; i< shorterBook.getWordCount(); i += 2)
           {
             outputFile.print(shorterBook.getWordList().get(i) + " ");
             outputFile.print(longerBook.getWordList().get(i+1) + " ");
           }
            
           for (int f = shorterBook.getWordCount(); f< longerBook.getWordCount(); f++)
           {
             outputFile.print(longerBook.getWordList().get(f) + " ");
           }
           outputFile.close();
        }
        catch(Exception e)
        {
           System.out.println("Check output filename.");
        }
    }
}

class Book
{
    //// FIELDS ////
    
    /* Declare fields for the word list, the word count, and the longest word.*/
    
    private ArrayList<String> wordList;
    private int wordCount;
    private String longestWord;
    
    ////// CONSTRUCTOR //////
    //@param f -- the file object
    public Book(File f)
    {
        wordList = readBook(f);
        wordCount = countWords(wordList);
        longestWord = findLongestWord(wordList);
    }
    
    //////// METHODS ////////

    /* Reads in every word from an input file to an ArrayList.
     * Punctuation may be included.
     * Uses a try–catch block.
     * Returns an ArrayList filled with "words."
     * @param f -- the input file object
     */
    public static ArrayList<String> readBook(File f)
    {
        int i = 0;
        String input = "";
        
        try
        {
            Scanner scan = new Scanner(f);
            if (i == 0)
            {
                while (scan.hasNextLine())
                {
                    input += " " + scan.nextLine();
                }
                i = 1;
            }
        }
        catch (Exception e)
        {
            System.out.println("Check filename");
        }
        
        String[] a = input.split(" ");
        ArrayList<String> words = new ArrayList<String>(Arrays.asList(a));
        
        return words;
    }
    
    /* Counts the number of words in a list.
     * Returns number of words.
     * @param b -- A Book object's word list
     */
    public static int countWords(ArrayList<String> b)
    {
        return b.size();
    }
    
    /* Finds the longest word in a list.
     * In the case of a tie, return only the first word of longest length.
     * Returns the longest word.
     * @param b -- A Book object's word list
     */
    public static String findLongestWord(ArrayList<String> b)
    {
      int longestWord = b.get(0).length();
      int index = 0;
      
      for(int i = 0; i < b.size(); i++)
      { 
         if(b.get(i).length() > longestWord)
         {
            longestWord = b.get(i).length();
            index = i;
         }
      }
      return b.get(index);
    }
    
    ////////// ACCESSOR METHODS //////////
    
    /* Write 3 accessor methods for the fields of the Book class. */
    
    public ArrayList<String> getWordList()
    {
        return wordList;   
    }
    
    public int getWordCount()
    {
        return wordCount;   
    }
    
    public String getLongestWord()
    {
        return longestWord;   
    }
    
}