import models.Movie;
import models.MovieDatabase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MovieDatabaseTest {

    /**
     * This tests add and delete movie functions
     */
    @Test
    public void testAddDeleteMovie() {
        MovieDatabase db = new MovieDatabase();
        Movie movieOne = new Movie("Ghost Busters", 100, 5);
        Movie movieTwo = new Movie("Spiderman", 90, 4);

        db.addMovie(movieOne);
        db.addMovie(movieTwo);

        //Verify that movieOne is added
        Assert.assertTrue(db.getMovieArchive().contains(movieOne));
        //Verify that movieTwo is added
        Assert.assertTrue(db.getMovieArchive().contains(movieTwo));

        db.deleteMovie(movieOne);

        //Verify that movieOne is deleted
        Assert.assertFalse(db.getMovieArchive().contains(movieOne));

    }

    /**
     * This tests update movie name, price and quantity
     */
    @Test
    public void testUpdateMovies() {
        String newMovieName = "World War Z";
        double newPrice = 140.00;
        int newQty = 20;

        MovieDatabase db = new MovieDatabase();
        Movie movieOne = new Movie("A Quite Place", 70, 9);

        db.addMovie(movieOne);
        db.updateMovieName(movieOne.getName(), newMovieName);
        db.updateMoviePrice(newMovieName, newPrice);
        db.updateMovieQuantity(newMovieName, newQty);

        //Verify movieOne is updated to new Movie Name
        Assert.assertTrue(db.getMovieArchive().contains(new Movie(newMovieName)));
        //Verify movieOne quantity is updated from 70 to 140
        Assert.assertTrue(db.findMovieByName(newMovieName).contains(new Movie(newMovieName, newPrice)));
        //Verify movieOne quantity is updated from 9 to 20
        Assert.assertTrue(db.findMovieByName(newMovieName).contains(new Movie(newMovieName, newQty)));
    }
}
