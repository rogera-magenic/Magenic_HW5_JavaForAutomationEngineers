import models.Movie;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MovieTest {
    /**
     * This test is for the 'Getters'
     */
    @Test
    public void testGetters() {
        String testName = "Luca";
        double testPrice = 50.00;
        int testQuantity = 4;

        Movie movie = new Movie(testName, testPrice, testQuantity);

        Assert.assertEquals(movie.getName(), testName);
        Assert.assertEquals(movie.getPrice(), testPrice);
        Assert.assertEquals(movie.getQuantity(), testQuantity);
    }

    /**
     * This test is for the 'toString' override
     */
    @Test
    public void testToString() {
        String testName = "The Croods";
        double testPrice = 90.00;
        int testQuantity = 55;

        // This is the expected format of how the movie is returned after 'toString' is called
        String unformattedExpectedString = "MOVIE TITLE: %s | PRICE: %s | QUANTITY: %s";

        Movie movie = new Movie(testName, testPrice, testQuantity);

        Assert.assertEquals(movie.toString(), String.format(unformattedExpectedString, testName, testPrice, testQuantity));
    }

    /**
     * This test is for the 'equals' override
     */
    @Test
    public void testEquals() {
        Movie movie = new Movie("Gozilla", 100.00, 10);
        Movie wrongMovie = new Movie("King Kong", 100.00, 10);
        Movie duplicateMovie = new Movie("Gozilla", 100.00, 10);

        Assert.assertFalse(movie.equals(wrongMovie));
        Assert.assertTrue(movie.equals(duplicateMovie));
    }
}
