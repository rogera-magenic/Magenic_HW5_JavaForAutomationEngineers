package models;

import resources.OptionStrings;
import utils.UserHelperMethods;

import java.util.List;
import java.util.Scanner;

public class MovieStore extends OptionStrings {
    private MovieDatabase movieDatabase;

    private Scanner scanner;

    public MovieStore() {
        this.movieDatabase = new MovieDatabase();
        this.scanner = new Scanner(System.in);
    }

    public MovieDatabase getMovieDatabase() {
        return movieDatabase;
    }

    protected void setMovieDatabase(MovieDatabase movieDatabase) {
        this.movieDatabase = movieDatabase;
    }

    private void mainMenu() {
        int userChoice = UserHelperMethods.displayOptionsAndWaitForValidOption(MAIN_MENU_OPTIONS);
        String userChoiceText = MAIN_MENU_OPTIONS[userChoice];

        if (userChoiceText.equals("Buy Movie")) {
            buyMovieMenu();
        } else if (userChoiceText.equals("Manage Movie")) {
            manageMovieMenu();
        } else {
            System.out.println("ENDING PROGRAM...");
            System.out.println("Thank you for using this Movie Store!");
        }
    }

    private void manageMovieMenu() {
        int userChoice = UserHelperMethods.displayOptionsAndWaitForValidOption(MANAGE_MOVIE_OPTIONS);
        String userChoiceText = MANAGE_MOVIE_OPTIONS[userChoice];

        switch (userChoiceText) {
            case "Show Movie List":
                UserHelperMethods.printMovie(movieDatabase.getMovieArchive());
                break;
            case "Add Movie":
                String movieTitle = UserHelperMethods.getStringInputFromPrompt("Movie Title: ");
                double moviePrice = UserHelperMethods.getDoubleInputFromPrompt("Movie Price: ");
                int movieQty = UserHelperMethods.getIntInputFromPrompt("Movie Quantity: ");

                movieDatabase.addMovie(new Movie(movieTitle, moviePrice, movieQty));
                break;
            case "Delete Movie":
                movieTitle = UserHelperMethods.getStringInputFromPrompt("Which movie would you like to delete: ");
                Movie movieToDelete = findMovie(movieTitle);

                movieDatabase.deleteMovie(movieToDelete);
                break;
            case "Update Movie":
                updateMovieMenu();
                break;
        }

        System.out.println("Returning to Main Menu...");
        mainMenu();
    }

    private void updateMovieMenu() {
        int userChoice = UserHelperMethods.displayOptionsAndWaitForValidOption(UPDATE_MOVIE_OPTIONS);
        String userChoiceText = UPDATE_MOVIE_OPTIONS[userChoice];

        switch (userChoiceText) {
            case ("Update Movie Name"):
                String movieTitle = UserHelperMethods.getStringInputFromPrompt("Enter Movie Title: ");
                Movie movieToUpdate = findMovie(movieTitle);
                if (movieToUpdate != null) {
                    String newMovieTitle = UserHelperMethods.getStringInputFromPrompt("Enter new Movie Title: ");
                    movieDatabase.updateMovieName(movieToUpdate.getName(), newMovieTitle);
                }
                break;
            case ("Update Movie Price"):
                movieTitle = UserHelperMethods.getStringInputFromPrompt("Enter Movie Title: ");
                movieToUpdate = findMovie(movieTitle);
                if (movieToUpdate != null) {
                    double newMoviePrice = UserHelperMethods.getDoubleInputFromPrompt("Enter new Movie Price: ");
                    movieDatabase.updateMoviePrice(movieToUpdate.getName(), newMoviePrice);
                }
                break;
            case ("Update Movie Quantity"):
                movieTitle = UserHelperMethods.getStringInputFromPrompt("Enter Movie Title: ");
                movieToUpdate = findMovie(movieTitle);
                if (movieToUpdate != null) {
                    int newMovieQty = UserHelperMethods.getIntInputFromPrompt("Enter new Movie Quantity: ");
                    movieDatabase.updateMovieQuantity(movieToUpdate.getName(), newMovieQty);
                }
                break;
        }
    }

    private void buyMovieMenu() {
        UserHelperMethods.printMovie(movieDatabase.getMovieArchive());
        String movieTitle = UserHelperMethods.getStringInputFromPrompt("Enter movie title to buy: ");
        Movie movieSearch = findMovie(movieTitle);
        if (movieSearch != null) {
            int movieQtyToBuy = UserHelperMethods.getIntInputFromPrompt("Enter quantity to buy: ");

            if (movieQtyToBuy > movieSearch.getQuantity()) {
                System.out.println("Our MOVIE: " + movieSearch.getName() + " has only " + movieSearch.getQuantity() +
                        " stocks left. No transaction was made");
            } else {

                System.out.println("MOVIE: " + movieSearch.getName() + " with " + movieQtyToBuy +
                        " quantity has a total of " + movieSearch.getPrice() * movieQtyToBuy);

                System.out.println("Do you want to proceed on buying the product?");

                int userChoice = UserHelperMethods.displayOptionsAndWaitForValidOption(BUY_MOVIE_OPTIONS);
                String userChoiceText = BUY_MOVIE_OPTIONS[userChoice];

                if(userChoiceText.equals("Buy")) {
                    movieDatabase.updateMovieQuantity(movieSearch.getName(),
                            movieSearch.getQuantity() - movieQtyToBuy);
                    System.out.println("MOVIE: " + movieSearch.getName() + " with " + movieQtyToBuy +
                            " quantity is successfully bought.");
                } else {
                    System.out.println("Transaction cancelled.");
                }
            }
        }

        System.out.println("Returning to Main Menu...");
        mainMenu();
    }

    private Movie findMovie(String movieName) {
        List<Movie> foundMovies = movieDatabase.findMovieByName(movieName);
        if (foundMovies.isEmpty()) {
            System.out.println("MOVIE: " + movieName + " was not found. Returning to main menu:");
            return null;
        } else if (foundMovies.size() == 1) {
            return foundMovies.get(0);
        } else {
            int selectedMovieIndex = UserHelperMethods.displayOptionsAndWaitForValidOption(foundMovies);
            return foundMovies.get(selectedMovieIndex);
        }
    }

    public static void main(String[] args) {
        MovieStore movieStore = new MovieStore();

        //Add new movie to the database
        movieStore.movieDatabase.addMovie(new Movie("Godzilla", 400.00, 10));
        movieStore.movieDatabase.addMovie(new Movie("King Kong", 230.00, 4));
        movieStore.movieDatabase.addMovie(new Movie("Avengers I", 800.00, 3));
        movieStore.movieDatabase.addMovie(new Movie("Avengers II", 600.00, 1));
        movieStore.movieDatabase.addMovie(new Movie("Avengers III", 900.00, 5));
        movieStore.movieDatabase.addMovie(new Movie("Iron Man", 900.00, 8));

        movieStore.mainMenu();
    }

}
