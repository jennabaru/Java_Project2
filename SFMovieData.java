package project2;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;   

public class SFMovieData{

    public static void main(String[] args){
        if (args.length == 0){
            System.err.println("Usage Error: The name of a file is expected as the argument");
            System.exit(1);
        }

        // Create a new MovieList to store all the Movies from the CSV file
        MovieList movieDB = new MovieList();
        
        try {

            File movieFile = new File(args[0]);
            movies = new Scanner (movieFile);

            // Skip past the headers in the CSV file
            movies.nextLine();
            
            ArrayList<String> csvMovies = null; 

            while (movies.hasNextLine()) {
                String movieString = movies.nextLine();
                csvMovie = splitCSVLine(movieString);

          
                if (csvMovie == null){
                    System.err.println("Error: movie database is empty");
                    System.exit(1);
                }

                // DEBUG - REMOVE LATER
                // System.out.printf("read: %s\n", movie);
                //System.out.printf("The Title is %s\n", csvMovies.get(0));

                // for each CSV line we need to:
                // See if there is already a Movie in the MovieList class that has the same title and year via compariable interface
                // If so, use the addLocation method to add the location.
                // Otherwise, create a Movie object and add it to the MovieList

                // create a movie 
                Movie movie = new Movie(csvMovie.get(0),csvMovie.get(1));
                Movie existingMovie = movieDB.find(movie);
                
                if (existingMovie == null) {
                    // Use the full constuctor to create a movie and add it to the MovieList
                    if (csvMovie.get(8)!= null || csvMovie.get(8)!=""){
                        Actor actor1 = new Actor(csvMovie.get(8));
                    }
                    if (csvMovie.get(9)!= null || csvMovie.get(9)!=""){
                        Actor actor2 = new Actor(csvMovie.get(9));
                    }
                    if (csvMovie.get(10)!= null || csvMovie.get(10)!=""){
                        Actor actor3 = new Actor(csvMovie.get(10));
                    }
                    Movie newMovie =  new Movie(csvMovie.get(0), csvMovie.get(1), csvMovie.get(6), csvMovie.get(7), 
                    actor1, actor2, actor3);

                    if(csvMovie.get(2)!= null || csvMovie.get(2)!= ""){
                        newMovie.addLocation(csvMovie.get(2));
                    }
                    //add new movie object into movie list array
                    movieDB.append(newMovie);
                } else {
                    // add the location
                    if(csvMovie.get(2)!= null || csvMovie.get(2)!= ""){
                        existingMovie.addLocation(csvMovie.get(2));
                    }
                }
                
            }
    } catch(FileNotFoundException e) {
        System.err.println("Error: the file "+movieFile.getAbsolutePath()+" does not exist.\n");
		System.exit(1);
    }  
        
    // Start the Console Program and loop until the user quits
    Boolean userQuit = false;
    welcomeBanner();

    while(!userQuit){

    }

}

    public static void welcomeBanner(){
        System.out.println("Search the database by matching keywords to titles or actor names.");
        System.out.println("To search for matching titles, enter title KEYWORD");
        System.out.println("To search for matching actor names, enter actor KEYWORD");
        System.out.println("To finish the program, enter quit");
    }
    /**
    * Splits the given line of a CSV file according to commas and double quotes
    * (double quotes are used to surround multi-word entries so that they may contain commas)
    * @author Joanna Klukowska
    * @param textLine  a line of text to be passed
    * @return an Arraylist object containing all individual entries found on that line
    */
    public static ArrayList<String> splitCSVLine(String textLine){
        if (textLine == null ) return null;
        ArrayList<String> entries = new ArrayList<String>(); 
        int lineLength = textLine.length();
        StringBuffer nextWord = new StringBuffer(); 
        char nextChar;
        boolean insideQuotes = false; 
        boolean insideEntry= false;

        // iterate over all characters in the textLine
        for (int i = 0; i < lineLength; i++) { 
            nextChar = textLine.charAt(i);

            // handle smart quotes as well as regular quotes
            if (nextChar == '"'|| nextChar == '\u201C' || nextChar =='\u201D') {

                //change insideQuotes flag when nextChar is a quote 
                if (insideQuotes) {
                    insideQuotes = false; insideEntry = false;
                }else {
                    insideQuotes = true;
                    insideEntry = true; }
            } else if (Character.isWhitespace(nextChar)) { 
                if ( insideQuotes || insideEntry ) {
                // add it to the current entry
                    nextWord.append( nextChar );
                }else { // skip all spaces between entries 
                    continue;
                }
            } else if ( nextChar == ',') {
                if (insideQuotes){ // comma inside an entry 
                    nextWord.append(nextChar);
                } else { // end of entry found
                    insideEntry = false; 
                    entries.add(nextWord.toString());
                    nextWord = new StringBuffer(); 
                }
            } else {
                // add all other characters to the nextWord
                nextWord.append(nextChar); insideEntry = true;
            }
        }
        // add the last word ( assuming not empty )
        // trim the white space before adding to the list 
        if (!nextWord.toString().equals("")) {
            entries.add(nextWord.toString().trim());
        }
        return entries;
    }
}