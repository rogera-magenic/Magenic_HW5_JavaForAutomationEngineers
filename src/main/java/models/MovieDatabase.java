package models;

import utils.UserHelperMethods;

import java.sql.Array;
import java.util.*;

/**
 * MovieDatabase.java
 */
public class MovieDatabase {

    private List<Movie> movieArchive;

    public MovieDatabase() {
        movieArchive = new ArrayList<Movie>();
    }

    public List<Movie> getMovieArchive() {
        return movieArchive;
    }

    public void setMovieArchive(List<Movie> movieArchive) {
        this.movieArchive = movieArchive;
    }

    public void addMovie(Movie newMovie) {
        if(findMovieByName(newMovie.getName()).isEmpty())
            movieArchive.add(newMovie);
        else
            System.out.println("Movie [" + newMovie.getName() + "] exists! No Update was done." +
                    " Please use Update Movie function for price and quantity." + System.lineSeparator());
    }

    public void deleteMovie(Movie movieToDelete) {
        if (movieArchive.contains(movieToDelete)) {
            for (Movie movie : movieArchive) {
                if (movie.equals(movieToDelete)) {
                    //System.out.println("[" + movieToDelete.getName() + "] is successfully removed from Movie Database.");
                    movieArchive.remove(movieToDelete);
                    break;
                }
            }
        }
    }

    public void updateMovieName(String movieName, String newMovieName) {
        boolean findMovie = false;

        for (Movie movie : movieArchive) {
            if (movie.getName().equals(movieName)) {
                //System.out.println("[" + movieName + "] is updated to [" + newMovieName + "]");
                movie.setName(newMovieName);
                findMovie = true;
                break;
            }
        }

        if(!findMovie) {
            System.out.println("[" + movieName + "] was not found. No updates was done");
        }
    }

    public void updateMoviePrice(String movieName, double price) {
        boolean findMovie = false;

        for (Movie movie : movieArchive) {
            if (movie.getName().equals(movieName)) {
                //System.out.println("[" + movieName + "] price is updated from " + movie.getPrice() + " to " + price);
                movie.setPrice(price);
                findMovie = true;
                break;
            }
        }

        if(!findMovie) {
            System.out.println("[" + movieName + "] was not found. No updates was done");
        }
    }

    public void updateMovieQuantity(String movieName, int qty) {
        boolean findMovie = false;

        for (Movie movie : movieArchive) {
            if (movie.getName().equals(movieName)) {
                if (qty > 0) {
                    movie.setQuantity(qty);
                }
                else if (qty < 1) {
                    movieArchive.remove(movie);
                }
                findMovie = true;
                break;
            }
        }

        if(!findMovie) {
            System.out.println("[" + movieName + "] was not found. No updates was done");
        }
    }

    public List<Movie> findMovieByName(String movieName) {
        List<Movie> filterMovie = new ArrayList<Movie>();

        for (Movie movie : movieArchive) {
            if (movie.getName().equals(movieName)) {
                filterMovie.add(movie);
            }
        }

        return filterMovie;
    }

    public static void main(String[] args) {
        MovieDatabase db = new MovieDatabase();

        db.addMovie(new Movie("TEST 2", 100.00, 3));
        db.addMovie(new Movie("TEST 1", 400.00, 5));
        db.updateMovieName("TEST 2", "TEST 3");
        db.updateMovieName("TEST 3", "TEST 2");
        db.updateMoviePrice("TEST 2", 200);
        db.updateMovieQuantity("TEST 1", 2);

        UserHelperMethods.printMovie(db.movieArchive);

        System.out.println(db.getMovieArchive().size());
        System.out.println(db.movieArchive.contains(new Movie("TEST 1")));
        db.deleteMovie(new Movie("TEST 1"));
        System.out.println(db.movieArchive.contains(new Movie("TEST 1")));
    }
}
