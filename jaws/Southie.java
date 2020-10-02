// Code written by Reid Smith
// Began 2020-09-28

// CIS1068 Assignment 5
// My intention is to preserve whitespace and NOT preserve case
// The program should also work for any text, not just the jaws script

// This script will read a file specified in the constant "INFILE"
// and output it to "OUTFILE" in a "southie" accent... whatever that means

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.lang.StringIndexOutOfBoundsException;

public class Southie {
    public static final String INFILE = "textfiles/jaws.txt";
    public static final String OUTFILE = "textfiles/jaws_out.txt";

    public static Scanner getScanner() throws FileNotFoundException {
        return new Scanner(new File(INFILE));
    }

    public static String processWord(String word) {
        return "word";
    }

    public static String processLine(String original_line) {
        String output = "";
        String line = "   " + original_line;
        char predecessor, prepredecessor, preprepredecessor, successor;
        // the notation is messy, but I think it's clear. preprepredecessor will be used once

        for (int i = 0; i < line.length(); i++) {
            char ch = line.charAt(i);
            if (!(ch >= 41 && ch <= 90) && !(ch >= 97 && ch <= 122)) {
                // Then ch is NOT a letter. 
                output += ch;
            } else {
                // ch IS a letter
                predecessor = line.charAt(i - 1); 
                prepredecessor = line.charAt(i - 2);
                preprepredecessor = line.charAt(i - 3);
                try {
                    successor = line.charAt(i + 1);
                } catch (StringIndexOutOfBoundsException e) {
                    successor = '\n';
                }

//////////////////////////////////////////////////////////////////////////////

                if (ch == 'y' && 
                    predecessor == 'r' && 
                    prepredecessor == 'e' && 
                    preprepredecessor == 'v') {
                    //then
                    output = output.substring(0, i - 3) + "wicked";
                } else if (ch == 'r') {
                    if (((predecessor == 'e' && prepredecessor == 'e') 
                                            || predecessor == 'i')
                                         && (successor == ' ' || successor == '\n')) {
                        output += "yah";
                    } else if (predecessor == 'o' && prepredecessor == 'o') {
                        output += "wah";
                    } else if (predecessor == 'a' ||
                                        predecessor == 'e' ||
                                        predecessor == 'i' ||
                                        predecessor == 'o' ||
                                        predecessor == 'u') {
                        output += "h";
                    } 
                } else if (ch == 'a' && (successor == ' ' || successor == '\n')) {
                    output += "ar";
                } else {
                    output += ch;
                }
            }
        }
        output = output.substring(3);
        return output;
    }

    public static void process(Scanner reader) throws FileNotFoundException {
        PrintStream ps = new PrintStream(new File(OUTFILE));
        while (reader.hasNextLine()) {
            String line = reader.nextLine().toLowerCase();
            ps.println(processLine(line));
        }
        ps.close();
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner reader = getScanner();
        process(reader);
    }
}