// Code written by Reid Smith
// Begun 11/20/2020
// CIS1068 Assignment 7

/*
This is a script for building a recommender system based on cosine similarity between
"books," and recommending books to the user similar to books they rate positively. 
It will easily generalize to any application beyond books. Example formats for the input 
files can be found in the textfiles directory. 

There is a tradeoff between runtime of loading the script and execution once the user 
inputs their own ratings. I have chosen to increase loading runtime and decrease the time required to make each recommendation by preloading the "similarityMatrix" between books. 

It is my understanding that Amazon and Netflix use contrary techniques in their recommender
systems. One uses user-user similarity and one uses object-object (or content-content)
similarity. This would be an easy switch to make. 
*/

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Recommender {
    public static final String BOOKSFILE = "textfiles/books.txt";
    public static final String RATINGSFILE = "textfiles/ratings.txt";

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

    public static int[] constructColumn(int[][] arr, colNum) {
        int[] output = new int[arr.size];
        for (int j = 0; j < output.size; j++) {
            output[j] = arr[j][colNum];
        }
        return output;
    }

    public static double computeSimilarity(int[] bookOne, int[] bookTwo) {
        int bothRated = 0;
        for (int i = 0; i < bookOne.length; i++) {
            if (bookOne[i] != -1 && bookTwo[i] != -1) {
                bothRated++;
            }
        } 
        // TODO
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
                    similarityMatrix[i][j] = 1;
                } else {
                    int[] bookOne = constructColumn(ratings, i);
                    int[] bookTwo = constructColumn(ratings, j);
                    similarityMatrix[i][j] = computeSimilarity(bookOne, bookTwo)
                }
            }
        }
        return similarityMatrix;
    }

    public static void main(String[] args) throws FileNotFoundException {
        int[][] ratings = read2DInt(RATINGSFILE);
        String[] books = read1DString(BOOKSFILE);
        double[][] similarityMatrix = makeSimilarityMatrix(ratings);
    }
}





