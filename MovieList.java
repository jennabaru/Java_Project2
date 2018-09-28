package project2;

public class MovieList extends ArrayList<Movie> {
    
    public MovieList() {
        ArrayList<Movie> movies;
        return movies;
    }

    public MovieList getMatchingTitles ( String keyword ) {
        
    }

    public MovieList getMatchingActor ( String keyword ) {

    }

    public Movie find(Movie movie) {

        int i;
        for (i=0;i< movies.size(); i++) {
            if (movies.get(i).equals(movie)) {
                return Movie;
            }
        }
        return null;
    }
}