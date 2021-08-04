package utils;

import models.Movie;

import java.util.*;

public class UserHelperMethods {
    public static void printArray(String[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println(i + ". " + array[i]);
        }
    }

    public static void printMovie(List<Movie> movies) {
        for (int i = 0; i < movies.size(); i++) {
            System.out.println(i + ". " + movies.get(i));
        }
    }

    public static void printOptions(String[] options) {
        printArray(options);
        System.out.println("Enter the number of your selected option:");
    }

    public static int displayOptionsAndWaitForValidOption(String[] options) {
        Scanner scanner = new Scanner(System.in);
        int option = -1;

        printOptions(options);

        do {
            String input = scanner.nextLine();

            try {
                option = Integer.parseInt(input);

                if (option >= 0 && option <= options.length) {
                    return option;
                }

                printOptions(options);
            } catch (NumberFormatException e) {
                printOptions(options);
            }
        } while (true);
    }

    public static int displayOptionsAndWaitForValidOption(List<Movie> movieOptions) {
        String[] movieText = new String[movieOptions.size()];

        for (int i = 0; i < movieOptions.size(); i++) {
            movieText[i] = movieOptions.get(i).toString();
        }

        return displayOptionsAndWaitForValidOption(movieText);
    }

    public static String getStringInputFromPrompt(String prompt) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(prompt);
        return scanner.nextLine();
    }

    public static Double getDoubleInputFromPrompt(String prompt) {
        double output = 0;
        boolean loop = false;

        System.out.println(prompt);

        while (!loop) {
            try {
                Scanner scanner = new Scanner(System.in);
                output = Double.parseDouble(scanner.nextLine());
                loop = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid data. Please try again.");
                System.out.println(prompt);
            }
        }

        return output;
    }

    public static Integer getIntInputFromPrompt(String prompt) {
        int output = 0;
        boolean loop = false;

        System.out.println(prompt);

        while (!loop) {
            try {
                Scanner scanner = new Scanner(System.in);
                output = Integer.parseInt(scanner.nextLine());
                loop = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid data. Please try again.");
                System.out.println(prompt);
            }
        }

        return output;
    }
}
