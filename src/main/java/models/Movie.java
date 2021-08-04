package models;

public class Movie implements Comparable<Movie> {
    private String name;
    private double price;
    private int quantity;

    public Movie(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Movie(String name) {
        this.name = name;
    }

    public Movie(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Movie(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !Movie.class.isAssignableFrom(obj.getClass())) {
            return false;
        }

        final Movie otherMovie = (Movie) obj;

        return this.getName().equals(otherMovie.getName());
    }

    @Override
    public String toString() {
        return "MOVIE TITLE: " + getName() + " | PRICE: " + getPrice() + " | QUANTITY: " + getQuantity();
    }

    @Override
    public int compareTo(Movie otherMovie) {
        if (this.getName().compareTo(otherMovie.getName()) > 0) {
            return 1;
        } else if (this.getName().compareTo(otherMovie.getName()) < 0) {
            return -1;
        } else {
            return 0;
        }
    }

    public static void main(String[] args) {
        Movie movieOne = new Movie("MovieOne", 100.00, 1);
        Movie movieTwo = new Movie("MovieTwo", 200.00, 4);
        Movie dupe = new Movie("MovieOne", 100.00, 8);

        movieOne.compareTo(movieTwo);
        movieOne.compareTo(dupe);

        System.out.println(movieOne);
        System.out.println(movieTwo);

        System.out.println("Songs are equal: " + movieOne.equals(movieTwo));

        movieTwo.setName("MovieOne");

        System.out.println(movieTwo);
        System.out.println("Songs are equal: " + (movieOne.equals(movieTwo) && movieTwo.equals(movieOne)));
    }
}
