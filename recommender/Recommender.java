// Code written by Reid Smith
// This version begun on 10/22/2020
// Old versions available in old_versions directory

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.Math;

public class Recommender {
    /*******************CONSTANTS**********************/

    public static final String BOOKSFILE = "textfiles/books.txt";
    public static final String RATINGSFILE = "textfiles/ratings.txt";

    public static final int NUM_RECOMMENDATIONS = 0;
        // if this is 0, we will be recommending each book the user has not read in order 
        // from most recommended to least recommended, but for extra credit, this can 
        // be made non-zero
        // under this implementation, NUM_RECOMMENDATIONS must be <= unread books
        // or the script throws index out of bounds. 

    /****************HELPER FUNCTIONS******************/
    // (these functions do not appear in main, but 
        // a great deal of the magic does happen here)

    public static int[] constructColumn(int[][] arr, int colNum) {
        int[] output = new int[arr.length];
        for (int j = 0; j < output.length; j++) {
            output[j] = arr[j][colNum];
        }
        return output;
    }

    public static int getDoubleMaxIndex(double[] arr) {
        double maxValue = arr[0];
        // see function name for output description
        int output = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > maxValue) {
                maxValue = arr[i];
                output = i;
            }
        }
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

    public static double userGonnaLikeItScore(int[] userRatings, double[] bookScores) {
        double output = 0.0;
        int countInfo = 0;
        for (int i = 0; i < bookScores.length; i++) {
            if (bookScores[i] != 1.0) {
                output += userRatings[i] * bookScores[i];
                countInfo++;
            }
        }
        return output / countInfo;
    }

    public static double[] copyDoubleArray(double[] arr) {
        double[] output = new double[arr.length];
        for (int i = 0; i < output.length; i++) {
            output[i] = arr[i];
        }
        return output;
    }

    public static double[] dropFromDoubleArray(double[] arr, int drop) {
        double[] output = new double[arr.length - 1];
        for (int i = 0; i < arr.length; i++) {
            if (i < drop) {
                output[i] = arr[i];
            } else if (i == drop) {
                ;
            } else {
                output[i - 1] = arr[i];
            }
        }
        return output;
    }

    /*************MAIN FUNCTIONS*********************/
    // (these function DO appear in main) (in order of their appearance here)

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

    public static int[] getUserRatings(String[] books) {
        int[] output = new int[books.length];
        Scanner s = new Scanner(System.in);
        for (int i = 0; i < books.length; i++) {
            System.out.println("What do you think of \"" + books[i] + "\"?");
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

    public static double[] makeRecommendationStrength(double[][] bookSimilarityMatrix, 
                                                int[] userRatings) {
        /* This function builds the array of doubles corresponding to how strongly we
        recommend each book */

        // we will build an nBooks long array
        double[] output = new double[userRatings.length];
        // where each cell is how much the user will like each book
        for (int i = 0; i < userRatings.length; i++) {
            if (userRatings[i] == -1) {
                output[i] = userGonnaLikeItScore(userRatings, bookSimilarityMatrix[i]);
            } else {
                output[i] = 0; // indicating that the user has read it, not to recommend
            }
        }
        return output;
    }

    public static int[] bookOrderer(double[] recommendationStrength) {
        /* This function takes an array of doubles and returns ints corresponding the 
        indexes from MAX to MIN of the doubles */
        // crap, I basically have to implement most of a sorting algorithm from scratch
        // as filtered in userGonnaLikeItScore(), 
            // recommendationStrength[i] == 0.0 if user has not read

        int outputLength = 0; 
        for (int i = 0; i < recommendationStrength.length; i++) {
            if (recommendationStrength[i] != 0) {
                outputLength++;
            }
        }
        int[] output = new int[outputLength];
        int indexAdjustment = 0;
        double[] relevantValues = copyDoubleArray(recommendationStrength); 
        // the copy is not necessary, but I feel like it's a good practice to retain the 
        // recommendationStrengths for the particular user, and it's not much memory
        for (int i = 0; i < output.length; i++) {
            int maxIndex = getDoubleMaxIndex(relevantValues);
            output[i] = maxIndex + indexAdjustment;
            relevantValues[maxIndex] = 0.0; 
        }
        return output;
    }

    public static void printRecommendations(int[] recommendations, 
                                            String[] books) {
        for (int i = 0; i < recommendations.length; i++) {
            System.out.println("Recommendation #" + 
                            (i + 1) + 
                            ": " + 
                            books[recommendations[i]]);
        }
    }

    public static void printSomeRecommendations(int[] recommendations,
                                                String[] books) {
        for (int i = 0; i < NUM_RECOMMENDATIONS; i++) {
            System.out.println("Recommendation #" + 
                            (i + 1) + 
                            ": " + 
                            books[recommendations[i]]);
        }
    }

    public static void main(String[] args) throws FileNotFoundException{
        String[] books = read1DString(BOOKSFILE);
        int[][] ratings = read2DInt(RATINGSFILE);

        // in the below function, we will compute a nBooks x nBooks matrix of 
        // book - book similarity which we will use to generate book recommendations
        // This array will be symmetrical across the diagonal
        // 1.0s on the diagonal represent that a book is identical to itself

        double[][] bookSimilarityMatrix = makeSimilarityMatrix(ratings);

        int[] userRatings = getUserRatings(books);
        // int[] userRatings = {5, -1, -1, 4, -1, 4, -1, -1, -1, -1, 4, 2, 3, -1, -1, -1, -1, -1, 4, -1};
        // hardcoded for testing

        // 18 points Create a method that determines for each of the 30 people a score, which represents how similar that person's tastes are to the taste of the user of the program. Store these similarity scores in an array of 30 doubles. The similarity scores should be between 0 and 1 each.

        // userUserSimilarity is the array of 30 doubles mentioned in this assignment requirement
        double[] userUserSimilarity = getUserUserSimilarity(userRatings, ratings);

        double[] recommendationStrength = makeRecommendationStrength(bookSimilarityMatrix, userRatings);

        int[] recommendations = bookOrderer(recommendationStrength);

        if (NUM_RECOMMENDATIONS == 0) {
            printRecommendations(recommendations, books);
        } else {
            printSomeRecommendations(recommendations, books);
        }
    }
}







