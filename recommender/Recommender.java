// Code written by Reid Smith
// This version begun on 10/22/2020
// Old versions available in old_versions directory

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.Math;

public class Recommender {
    public static final String BOOKSFILE = "textfiles/books.txt";
    public static final String RATINGSFILE = "textfiles/ratings.txt";

    public static void print1DInt(int[] arr) {
        // obvious debugging function is obvious
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void print1DDouble(double[] arr) {
        // obvious debugging function is obvious
        // obvious copy of above function is obvious
        // (python masterrace)
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static String[] read1DString(String filename) throws FileNotFoundException {
        // reasonably boilerplate, with help from stackoverflow
        Scanner sc = new Scanner(new File(filename));
        int nRows = 0;
        while (sc.hasNextLine()) {
            String trash = sc.nextLine();
            nRows++;
        }
        String[] output = new String[nRows];
        sc.close();
        Scanner sc2 = new Scanner(new File(filename));
        for (int i = 0; i < nRows; i++) {
            output[i] = sc2.nextLine();
        }
        sc2.close();
        return output;
    }

    public static int[][] read2DInt(String filename) throws FileNotFoundException {
        // reasonably boilerplate, with help from stackoverflow
        Scanner sc = new Scanner(new File(filename));
        int rowsCount = 1;
        String[] firstLine = sc.nextLine().trim().split(" ");
        while (sc.hasNextLine()) {
            String trash = sc.nextLine();
            rowsCount++;
        }
        sc.close();
        Scanner sc2 = new Scanner(new File(filename));
        int[][] output = new int[rowsCount][firstLine.length];
        for (int i = 0; i < output.length; i++) {
            String[] line = sc2.nextLine().trim().split(" ");
            for (int j = 0; j < line.length; j++) {
                output[i][j] = Integer.parseInt(line[j]);
            }
        }
        sc2.close();
        return output;
    }

    public static double computeSimilarity(int[] objectOne, int[] objectTwo) {
        // this is a fairly general function for computing cosine similarity
        // it is adapted from an earlier version using books (bookOne and bookTwo) to be
        // useful for users, books, avacados... any two lists of ratings of equal length
        int bothRated = 0;
        for (int i = 0; i < objectOne.length; i++) {
            if (objectOne[i] != -1 && objectTwo[i] != -1) {
                bothRated++;
            }
        }

        int[] oneRatings = new int[bothRated];
        int[] twoRatings = new int[bothRated];
        int bothRatedCounter = 0;

        for (int i = 0; i < objectOne.length; i++) {
            if (objectOne[i] != -1 && objectTwo[i] != -1) {
                oneRatings[bothRatedCounter] = objectOne[i];
                twoRatings[bothRatedCounter] = objectTwo[i];
                bothRatedCounter++;
            }
        }

        int objectOneScoresSquared = 0;
        int objectTwoScoresSquared = 0;
        int sumOfProducts = 0;
        for (int i = 0; i < oneRatings.length; i++) {
            objectOneScoresSquared += oneRatings[i] * oneRatings[i];
            objectTwoScoresSquared += twoRatings[i] * twoRatings[i];
            sumOfProducts += oneRatings[i] * twoRatings[i];
        }
        return Double.valueOf(sumOfProducts) / 
            (
                Math.sqrt(Double.valueOf(objectOneScoresSquared)) 
                * 
                Math.sqrt(Double.valueOf(objectTwoScoresSquared))
            );
    }

    public static int[] getUserRatings(String[] books) {
        int[] output = new int[books.length];
        Scanner s = new Scanner(System.in);
        for (int i = 0; i < books.length; i++) {
            System.out.println("What do you think of " + books[i] + "?");
            output[i] = s.nextInt();
        }
        return output;
    }

    public static double[] getUserUserSimilarity(int[] userRatings, int[][] ratings) {
        double[] output = new double[ratings.length];
        for (int i = 0; i < ratings.length; i++) {
            output[i] = computeSimilarity(userRatings, ratings[i]);
        }
        return output;
    }

    public static void printRecommendations(int[] recommendationOrder, String[] books) {
        for (int i = 0; i < books.length; i++) {
            System.out.println("Recommendation #" + (i + 1) + " is " + 
                                books[recommendationOrder[i]]);
        }
    }

    public static int[] constructColumn(int[][] arr, int colNum) {
        int[] output = new int[arr.length];
        for (int j = 0; j < output.length; j++) {
            output[j] = arr[j][colNum];
        }
        return output;
    }

    public static double[][] makeSimilarityMatrix(int[][] ratings) {
        double[][] similarityMatrix = new double[ratings[0].length][ratings[0].length];
        // number of books by number of books
        for (int i = 0; i < similarityMatrix.length; i++) {
            for (int j = 0; j < similarityMatrix[0].length; j++) {
        // the loop is a square, but the "[0]" is provided for clarity in that
        // i is looping over rows, while j loops over columns

        // the similarity matrix will be symetrical across the main diagonal. 
        // book i's similarity to book j should be the same as book j's similarity to book i
                if (i == j) {
                    similarityMatrix[i][j] = 1.0;
                } else {
                    int[] bookOne = constructColumn(ratings, i);
                    int[] bookTwo = constructColumn(ratings, j);

                    similarityMatrix[i][j] = computeSimilarity(bookOne, bookTwo);
                }
            }
        }
        return similarityMatrix;
    }

    public static void main(String[] args) throws FileNotFoundException{
        String[] books = read1DString(BOOKSFILE);
        int[][] ratings = read2DInt(RATINGSFILE);
        // int[] userRatings = getUserRatings(books);
        int[] userRatings = {5, -1, -1, 5, 4, 5, 4, -1, -1, -1, 5, 4, -1, -1, 
                            -1, -1, -1, 5, -1};
        double[] userUserSimilarity = getUserUserSimilarity(userRatings, ratings);
        // 18 points Create a method that determines for each of the 30 people a score, which represents how similar that person's tastes are to the taste of the user of the program. Store these similarity scores in an array of 30 doubles. The similarity scores should be between 0 and 1 each.

        // userUserSimilarity is the array of 30 doubles mentioned in this assignment requirement

        // 18 points Create an array that represents recommended ratings for the user. There should be 20 numbers in this array, one for each book. The higher the number, the more strongly your program thinks the user will like the book. The number should be the average over all 30 ratings for the book that are greater than 0 (only include ratings for users who have actually rated the book). However, it should be a weighted average: people who are more similar to the current user should have a higher weight than people who are less similar.

        // in the below function, we will compute a nBooks x nBooks matrix of 
        // book - book similarity which we will use to generate book recommendations

        double[][] similarityMatrix = makeSimilarityMatrix(ratings);

        // TODO get book recommendations for the user
    }
}







