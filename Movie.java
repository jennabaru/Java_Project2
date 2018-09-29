package project2;

import java.util.ArrayList;

public class Movie implements Comparable<Movie>{
    String title;
    int year;
    ArrayList<Location> location = new ArrayList<Location>();
    String director;
    String writer;
    Actor actor1;
    Actor actor2;
    Actor actor3;

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
        this.location.add(loc);
        //add given location to the list of filming locations for the current movie object
    }

    public String toString() {
        return "";

    }

    public Boolean equals(Movie movie) {
        if (this.year == movie.year && this.title == movie.title) {
            return true;
        }
        return false;
    }

    public int compareTo(Movie movie) {
        return 0;
    }
}