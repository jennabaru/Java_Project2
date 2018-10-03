package project2;

import java.util.ArrayList;

public class Movie implements Comparable<Movie>{
    public String title;
    public int year;
    public ArrayList<Location> location = new ArrayList<Location>();
    public String director;
    public String writer;
    public Actor actor1;
    public Actor actor2;
    public Actor actor3;

    public Movie(String title, int year) throws IllegalArgumentException{
        if (title == null || title.length()==0){
            throw new IllegalArgumentException("Movie title expected");
        }
        if(year<1900 || year>2020){
            throw new IllegalArgumentException("Invalid year. Year must be between 1990 and 2020");
        }
        this.title = title;
        this.year = year;
    }

    public Movie(String title, int year, String director, String writer, 
                                                            Actor actor1, Actor actor2, Actor actor3) throws IllegalArgumentException{
        if (title == null || title.length()==0){
            throw new IllegalArgumentException("Movie title expected");
        }
        if(year<1900 || year>2020){
            throw new IllegalArgumentException("Invalid year. Year must be between 1990 and 2020");
        }
        if (actor1 == null){
            throw new IllegalArgumentException("Movie must have an actor");
        }
        this.title = title;
        this.year = year;
        this.director = director;
        this.writer = writer;
        this.actor1 = actor1;
        this.actor2 = actor2;
        this.actor3 = actor3;
    }

    public void addLocation(Location loc) throws IllegalArgumentException{
        if (loc == null){
            throw new IllegalArgumentException("Invalid location");
        }
        // DBG System.out.printf("    calling add -> %s\n", loc.getLocation());
        this.location.add(loc);
        //add given location to the list of filming locations for the current movie object
    }

    public String toString() {
        System.out.println();
        if(actor2!= null && actor3!= null){

            String movieOutput = String.format("%s (%d) \n------------------------------------ \ndirector\t: %s\nwriter  \t: %s\nstarring\t: %s, %s, %s\nfilmed on location at:\n",
            title, year, director, writer, actor1.name(), actor2.name(), actor3.name());

            String locations = "";

            int i=0;
            for(i=0;i<location.size(); i++){
                if(location.get(i).getFunFact()!=null && (!(location.get(i).getFunFact().isEmpty()))){
                    locations = locations.concat(String.format("\t%s (%s)\n", location.get(i).getLocation(), location.get(i).getFunFact()));
                }else{
                    locations = locations.concat(String.format("\t%s\n", location.get(i).getLocation()));
                }
            }

            movieOutput = movieOutput.concat(locations);
            return movieOutput;

        }else if(actor2!=null){

            String movieOutput = String.format("%s (%d) \n------------------------------------ \ndirector\t: %s\nwriter\t\t: %s\nstarring\t: %s, %s\nfilmed on location at:\n",
            title, year, director, writer, actor1.name(), actor2.name());

            String locations = "";

            int i=0;
            for(i=0;i<location.size(); i++){
                if(location.size()==1 && location.get(i).getFunFact()!= null && (!(location.get(i).getFunFact().isEmpty()))){
                    locations = locations.concat(String.format("\t%s (%s)\n", location.get(i).getLocation(), location.get(i).getFunFact()));
                }else{
                    locations = locations.concat(String.format("\t%s\n", location.get(i).getLocation()));
                }
            }

            movieOutput = movieOutput.concat(locations);
            return movieOutput;


        }else{
            String movieOutput = String.format("%s (%d) \n------------------------------------ \ndirector\t: %s\nwriter\t\t: %s\nstarring\t: %s\nfilmed on location at:\n",
            title, year, director, writer, actor1.name());

            String locations = "";

            int i=0;
            if (location.get(i).getFunFact()!= null && (!(location.get(i).getFunFact().isEmpty()))){
                for(i=0; i<location.size(); i++) {
                    locations = locations.concat(String.format("\t%s (%s)\n", location.get(i).getLocation(), location.get(i).getFunFact()));
                }
            }else{
                for(i=0; i<location.size(); i++) {
                    locations = locations.concat(String.format("\t%s\n", location.get(i).getLocation()));
                }
            }

            movieOutput = movieOutput.concat(locations);
            return movieOutput;

        }
        //return movieOutput;
    }

    public Boolean equals(Movie movie) {
        //if (this.year == movie.year && this.title.equalsIgnoreCase(movie.title)) {
       //     return true;
        //}
        //return false;
        if(movie.compareTo(this)==0){
            return true;
        }
        return false;
    }
    @Override
    public int compareTo(Movie movie) {
        if (this.year == movie.year) {
            return this.title.toLowerCase().compareTo(movie.title.toLowerCase());
        } else if (this.year > movie.year) {
            return 1;
        } else {
            return -1;
        }
    }
}