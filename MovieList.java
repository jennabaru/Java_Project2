package project2;

import java.util.ArrayList;

public class MovieList extends ArrayList<Movie> {
    
    public MovieList() {
    }

    public MovieList getMatchingTitles ( String keyword ) {

        // Search the the list of movies and see what contains the keywords
        MovieList results = new MovieList();
        int i = 0;
        System.out.printf("searching %d titles\n", size());

        for (i=0;i<size(); i++) {
            // DEBUG System.out.printf("comparing [%s] to [%s]\n", keyword, get(i).title);
            if (get(i).title.toLowerCase().contains(keyword.toLowerCase())) {
                results.add(get(i));
            }
        }
        return results;
        
    }

    public MovieList getMatchingActor ( String keyword ) {
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
        return results;
    }

    public Movie find(Movie movie) {

        int i;
        for (i=0;i<size(); i++) {
            if (get(i).equals(movie)) {
                return movie;
            }
        }
        return null;
    }
}