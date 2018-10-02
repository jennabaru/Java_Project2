package project2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;   
import java.util.Arrays;

public class SFMovieData{

    public static void main(String[] args){

        File movieFile = null;

        if (args.length == 0){
            System.err.println("Usage Error: The name of a file is expected as the argument");
            System.exit(1);
        }

        // Create a new MovieList to store all the Movies from the CSV file
        MovieList movieDB = new MovieList();
        
        try {

            movieFile = new File(args[0]);
            Scanner movies = new Scanner(movieFile);

            // Skip past the headers in the CSV file
            movies.nextLine();
            
            ArrayList<String> csvMovie = null; 

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
                if (csvMovie.size() < 9) {
                    //DEBUG: System.out.printf("Bad CSV Entry, skipping\n");
                    continue;
                }

                Movie movie = new Movie(csvMovie.get(0), Integer.parseInt(csvMovie.get(1)));
                Movie existingMovie = movieDB.find(movie);
                
                Actor actor1 = null;
                Actor actor2 = null;
                Actor actor3 = null;

                
                if (existingMovie == null) {
                    // Use the full constuctor to create a movie and add it to the MovieList
                    if (csvMovie.size() >= 9 && csvMovie.get(8).length() > 0){
                        actor1 = new Actor(csvMovie.get(8));
                    }
                    if (csvMovie.size() >= 10  && csvMovie.get(9).length() > 0 ){
                        actor2 = new Actor(csvMovie.get(9));
                    }
                    
                    if (csvMovie.size() >= 11 && csvMovie.get(10).length() > 0) {
                        actor3 = new Actor(csvMovie.get(10));
                    }

                    // If the first actor is blank, skip addig the movie
                    if (actor1 != null) {
                        Movie newMovie =  new Movie(csvMovie.get(0), Integer.parseInt(csvMovie.get(1)), csvMovie.get(6), csvMovie.get(7), 
                            actor1, actor2, actor3);

                        if(csvMovie.get(2)!= null && csvMovie.get(2).length() > 0) {
                           // DBG System.out.printf("adding location to new movie %s\n", newMovie.title);
                            Location loc = new Location(csvMovie.get(2), csvMovie.get(3));
                            newMovie.addLocation(loc);
                        }
                        //add new movie object into movie list array
                        // DBG System.out.printf(" *** loc before add: %d\n", newMovie.location.size());
                        movieDB.add(newMovie);
                    }
                } else {
                    // DBG System.out.println("    Found existing movie");
                    // add the location
                    if(csvMovie.get(2)!= null && csvMovie.get(2).length() > 0) {
                        Location loc = new Location(csvMovie.get(2), csvMovie.get(3));
                        existingMovie.addLocation(loc);
                        // DBG System.out.printf("Adding Location to %s [%d]\n", existingMovie.title, existingMovie.location.size());

                    }
                }
                
            }
    } catch(FileNotFoundException e) {
        System.err.println("Error: the file "+movieFile.getAbsolutePath()+" does not exist.\n");
		System.exit(1);
    }  
        
    // DEBUG
    // DBG System.out.printf("There are %d entries in the database\n", movieDB.size());

    // Start the Console Program and loop until the user quits
    Boolean userQuit = false;
    welcomeBanner();

    while(!userQuit){
       Scanner cmdScanner = new Scanner (System.in);
       
       System.out.println("Enter your search query: ");
       System.out.println();

       while(cmdScanner.hasNextLine()){
           String cmd[] = cmdScanner.nextLine().trim().split(" ");
           if(cmd.length == 0 || (cmd.length==1 && !cmd[0].equalsIgnoreCase("quit"))){
               System.err.println("Usage Error: program expects title keyword, actor keyword, or quit");
               break;

           } else if (cmd[0].equalsIgnoreCase("title")) {
                //SEARCH FOR TITLE KEYWORD
                // DEBUG System.out.println("Searching title");
                
                String keywords = "";
                int i = 0;
                for(i=1; i<cmd.length; i++) {
                    keywords = keywords.concat(cmd[i]);
                    keywords = keywords.concat(" ");
                } 
                keywords = keywords.trim();
                
                // DBG System.out.printf("DEBUG searching title with [%s]\n", keywords);

                MovieList titleResults = movieDB.getMatchingTitles(keywords);

                if(titleResults==null){
                    System.out.println("No results, try again");
                }else{
                    //System.out.printf("Found %d results\n", titleResults.size());
                    for(i=0; i<titleResults.size();i++){
                        System.out.println(titleResults.get(i).toString());
                        System.out.println();
                    }
                }

                System.out.println("Enter your search query: ");
                System.out.println();

           } else if (cmd[0].equalsIgnoreCase("actor")) {  
                //search for actor keyword
                // DEBUG System.out.println("Searching actor"); 


                String keywords = "";
                int i = 0;
                for(i=1; i<cmd.length; i++) {
                    keywords = keywords.concat(cmd[i]);
                    keywords = keywords.concat(" ");
                } 
                keywords = keywords.trim();


                // DBG System.out.printf("DEBUG searching actor with [%s]\n", keywords);

                MovieList actorResults = movieDB.getMatchingActor(keywords);
                if(actorResults== null){
                    System.out.println("No results, try again");
                }else {
                    // System.out.printf("Found %d results\n", actorResults.size());
                    for (i=0; i<actorResults.size(); i++) {
                        System.out.println(actorResults.get(i).toString());
                    }
                }

                System.out.println("Enter your search query: ");

           } else {
               if (cmd[0].equalsIgnoreCase("quit")){
                   userQuit=true;
                   break;
               }
           }
       }
    }
}

    public static void welcomeBanner(){
        System.out.println("Search the database by matching keywords to titles or actor names.");
        System.out.println("\tTo search for matching titles, enter title KEYWORD");
        System.out.println("\tTo search for matching actor names, enter actor KEYWORD");
        System.out.println("\tTo finish the program, enter quit");
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
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
