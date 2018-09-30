package project2;

import java.util.ArrayList;
import java.util.Collections;

public class MovieList extends ArrayList<Movie> {
    
    public MovieList() {
    }

    public MovieList getMatchingTitles ( String keyword ) {

        if (keyword == null || keyword.length() == 0) {
            return null;
        }
        // Search the the list of movies and see what contains the keywords
        MovieList results = new MovieList();
        int i = 0;

        for (i=0;i<size(); i++) {
            if (get(i).title.toLowerCase().contains(keyword.toLowerCase())) {
                results.add(get(i));
            }
        }
        
        Collections.sort(results);
        return results;
        
    }

    public MovieList getMatchingActor ( String keyword ) {

        if (keyword == null || keyword.length() == 0) {
            return null;
        }

        MovieList results = new MovieList();
        int i = 0;

        for (i=0;i<size(); i++) {
            if (get(i).actor1 != null && get(i).actor1.name().toLowerCase().contains(keyword.toLowerCase())) {
                results.add(get(i));
            }

            if (get(i).actor2 != null && get(i).actor2.name().toLowerCase().contains(keyword.toLowerCase())) {
                results.add(get(i));
            }

            if (get(i).actor3 != null && get(i).actor3.name().toLowerCase().contains(keyword.toLowerCase())) {
                results.add(get(i));
            }
        }

        Collections.sort(results);
        return results;
    }

    public Movie find(Movie movie) {

        int i;
        for (i=0;i<size(); i++) {
            if (get(i).equals(movie)) {
                return get(i);
            }
        }
        return null;
    }
}