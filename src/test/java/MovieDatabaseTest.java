import models.Movie;
import models.MovieDatabase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MovieDatabaseTest {

    /**
     * This tests add movie functions
     */
    @Test
    public void testAddMovie() {
        MovieDatabase db = new MovieDatabase();
        Movie movieOne = new Movie("Ghost Busters", 100, 5);
        Movie movieTwo = new Movie("Spiderman", 90, 4);

        db.addMovie(movieOne);
        db.addMovie(movieTwo);

        //Verify that movieOne is added
        Assert.assertTrue(db.getMovieArchive().contains(movieOne));
        //Verify that movieTwo is added
        Assert.assertTrue(db.getMovieArchive().contains(movieTwo));
    }

    /**
     * This tests add and delete movie functions
     */
    @Test
    public void testDeleteMovie() {
        MovieDatabase db = new MovieDatabase();
        Movie movieOne = new Movie("Spiderman", 90, 4);

        db.addMovie(movieOne);
        db.deleteMovie(movieOne);

        //Verify that movieOne is deleted
        Assert.assertFalse(db.getMovieArchive().contains(movieOne));

    }

    /**
     * This tests update movie name, price and quantity
     */
    @Test
    public void testUpdateMovieName() {
        String newMovieName = "World War Z";

        MovieDatabase db = new MovieDatabase();
        Movie movieOne = new Movie("World War Z1", 70, 9);

        db.addMovie(movieOne);
        db.updateMovieName(movieOne.getName(), newMovieName);

        //Verify movieOne is updated to new Movie Name
        Assert.assertTrue(db.getMovieArchive().contains(new Movie(newMovieName)));
    }

    /**
     * This tests update movie name, price and quantity
     */
    @Test
    public void testUpdateMoviePrice() {
        String movieName = "A Quite Place II";
        double newPrice = 140.00;

        MovieDatabase db = new MovieDatabase();
        Movie movieOne = new Movie(movieName, 70, 9);

        db.addMovie(movieOne);
        db.updateMoviePrice(movieName, newPrice);

        //Verify movieOne quantity is updated from 70 to 140
        Assert.assertTrue(db.findMovieByName(movieName).contains(new Movie(movieName, newPrice)));
    }

    /**
     * This tests update movie name, price and quantity
     */
    @Test
    public void testUpdateMovieQty() {
        String movieName = "Wrath of God";
        double newPrice = 140.00;
        int newQty = 20;

        MovieDatabase db = new MovieDatabase();
        Movie movieOne = new Movie(movieName, 70, 9);

        db.addMovie(movieOne);
        db.updateMovieQuantity(movieName, newQty);
        
        //Verify movieOne quantity is updated from 9 to 20
        Assert.assertTrue(db.findMovieByName(movieName).contains(new Movie(movieName, newQty)));
    }
}
