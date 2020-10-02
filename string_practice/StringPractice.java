// Code written by Reid Smith
// Begun 2020-09-21
// Code follows instructions supplied in comments for school project
// The fun stuff is at the bottom
// The stuff as required by the class is not at the bottom
// https://www.github.com/SmithReid/StringPractice

import java.util.function.Supplier;

public class StringPractice {
  /*
   * returns true if c is one of the characters typically used to represent a "face card" in a
   * standard deck of playing cards.
   * 
   * These include: jack 'J' or 'j' queen 'Q' or 'q' king 'K' or 'k' ace 'A' or 'a
   */
    public static boolean isFace(char c) {
    /*
     * placeholder just so that the function compiles. fill in your implementation here.
     *
     * there is a similar placeholder for each of the remaining functions
     */
    char cUpper = Character.toUpperCase(c);
    if (cUpper == 'J' ||
            cUpper == 'Q' ||
            cUpper == 'K' ||
            cUpper == 'A') {
        return true;
        }
    return false;
    }


    /*
     * returns the index of the first face-card letter in s or -1 if s contains no face-card letters
     */
    public static int indexOfFirstFace(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (isFace(s.charAt(i))) {
                return i;
            }
        }
        return -1;
    }

    /*
     * returns the index of the first occurrence of a face-card letter in s  starting from index
     * startPosition or -1 if there are none at index startPosition or later. Notice that this method
     * has the same name as the previous one, but that it takes a different number of arguments. This
     * is perfectly legal in Java. It's called "method overloading"
    */
    public static int indexOfFirstFace(String s, int startPosition) {
        for (int i = startPosition; i < s.length(); i++) {
            if (isFace(s.charAt(i))) {
                return i;
            }
        }
        return -1;
    }

    /*
     * returns the index of the last occurrence of a face-card letter in s or -1 if s contains none
    */
    public static int indexOfLastFace(String s) {
        for (int i = s.length() - 1; i >= 0; i--) {
            if (isFace(s.charAt(i))) {
                return i;
            }
        }
        return -1;
    }

    /*
     * returns s in reverse. For example, if s is "Apple", the method returns the String "elppA"
    */
    public static String reversed(String s) {
        String output = "";
        for (int i = s.length() - 1; i >= 0; i--) {
            output += s.charAt(i);
        }
        return output;
    }

    /*
     * returns the number of times that n occurs in h. For example, if h is "Mississippi" and n is
     * "ss" the method returns 2.
    */
    public static int numOccurrences(String h, String n) {
        /* Instructions ambiguous: 
            Should numOcurrances("sss", "ss") return 1 or 2? 
            This will return 2.
        */
        int output = 0;
        for (int i = 0; i < h.length() - (n.length() - 1); i++) {
            if (h.charAt(i) == n.charAt(0)) {
                String hSub = h.substring(i, i + n.length());
                if (hSub.equals(n)){
                    output += 1;
                }
            }
        }
        return output;
    }

    /*
   * returns true if s is the same backwards and forwards and false otherwise
   */
    public static boolean sameInReverse(String s) {
        if (s.equals(reversed(s))) {
            return true;
        }
        return false;
    }

    /*
     * returns a new String which is the same as s, but with all of the face-card letters removed.
    */
    public static String withoutFaces(String s) {
        String output = "";
        int lastCut = 0;
        for (int i = 0; i < s.length(); i++) {
            if (isFace(s.charAt(i))) {
                output += s.substring(lastCut, i);
                lastCut = i + 1;
            }
        }
        output += s.substring(lastCut);
        return output;
    }

    /*
     * returns true if s consists only of face-card letters or false otherwise
     */
    public static boolean containsOnlyFaces(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (!isFace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /*
     * returns true if s contains no face-card letters or false otherwise
    */
    public static boolean containsNoFaces(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (isFace(s.charAt(i))) {
                return false;
            }
        }
        return true;    }

    /*
     * Passed a reference to a person's name in the form FIRST_NAME LAST_NAME. The method returns a
     * reference to a new String in the form LAST_NAME, FIRST_NAME.
     *
     * For example, if we were passed "Spongebob Squarepants", we'd return "Squarepants, Spongebob".
     * You may assume that the method is passed exactly two words separated by a single space.
    */
    public static String lastFirst(String s) {
        String output = "";
        int i = 0;
        while (s.charAt(i) != ' ') {
            i++;
        }
        output += s.substring(i + 1);
        output += ", " + s.substring(0, i);
        return output;
    }

    /*************************Tests below********************/

    private boolean testIsFace() {
        char[] faceCards = "jJqQkKaA".toCharArray();
        for (char card : faceCards) {
            if (!isFace(card)) {
                return false;
            }
        }
        return true;
    }

    private boolean testIndexOfFirstFace() {
        if (indexOfFirstFace("1234J") != 4)
            return false;
        if (indexOfFirstFace("123k") != 3)
            return false;
        if (indexOfFirstFace("jqkaAKQJ") != 0)
            return false;
        if (indexOfFirstFace("gobbledigooK") != 11) 
            return false;
        if (indexOfFirstFace("jkjkjkjk1234Q", 8) != 12)
            return false;
        if (indexOfFirstFace("1234a", 3) != 4)
            return false;
        if (indexOfFirstFace("12345678") != -1)
            return false;
        if (indexOfFirstFace("12345678", 3) != -1)
            return false;
        return true;
    }

    private boolean testIndexOfLastFace() {
        if (indexOfLastFace("jkdjfksfesq") != 10)
            return false;
        if (indexOfLastFace("j") != 0)
            return false;
        if (indexOfLastFace("1234Q") != 4)
            return false;
        if (indexOfLastFace("jkjkJKJKQQaaAaA") != 14)
            return  false;
        if (indexOfLastFace("a1234") != 0)
            return false;
        return true;
    }

    private boolean testReversed() {
        if (!reversed("gohangasalamiimalasagnahog").equals("gohangasalamiimalasagnahog"))
            return false;
        if (!reversed("imanamregalagermanami").equals("imanamregalagermanami"))
            return false;
        if (!reversed("Hello world").equals("dlrow olleH"))
            return false;
        if (!reversed("1234").equals("4321"))
            return false;
        return true; 
    }

    private boolean testNumOccurances() {
        if (numOccurrences("sss", "ss") != 2)
            return false;
        if (numOccurrences("12334", "3") != 2)
            return false;
        if (numOccurrences("56787878", "78") != 3) 
            return false;
        if (numOccurrences("1234123412345678", "1234") != 3)
            return false;
        if (numOccurrences("This is not an 0ct0pu5", "octopus") != 0)
            return false;
        return true;
    }

    private boolean testSameInReverse() {
        if (!sameInReverse("gohangasalamiimalasagnahog"))
            return false;
        if (!sameInReverse("imanamregalagermanami"))
            return false;
        if (sameInReverse("Hello world")) 
            return false;
        if (sameInReverse("1234"))
            return false;
        return true;
    }
    
    private boolean testWithoutFaces() {
        if (!withoutFaces("jkjkjk").equals(""))
            return false;
        if (!withoutFaces("viking").equals("viing"))
            return false;
        if (!withoutFaces("1234").equals("1234"))
            return false;
        return true;
    }

    private boolean testContainsOnlyFaces() {
        // if ONLY faces are present, we ! the condition
        if (containsOnlyFaces("1234"))
            return false;
        if (containsOnlyFaces("jkjkjkjkjk1"))
            return false;
        if (!containsOnlyFaces("jkJKQQaA"))
            return false;
        if (!containsOnlyFaces("J"))
            return false;
        return true;
    }

    private boolean testContainsNoFaces() {
        // if NO faces are present, we ! the condition
        if (containsNoFaces("1234j"))
            return false;
        if (containsNoFaces("jkjkJKJKAQaq"))
            return false;
        if (!containsNoFaces("1234567"))
            return false;
        if (!containsNoFaces("Spongebob"))
            return false;
        return true;
    }

    private boolean testLastFirst() {
        if (!lastFirst("Spongebob Squarepants").equals("Squarepants, Spongebob"))
            return false;
        if (!lastFirst("Donald Trump").equals("Trump, Donald"))
            return false;
        if (!lastFirst("Joe Biden").equals("Biden, Joe"))
            return false;
        if (!lastFirst("YG FDT").equals("FDT, YG")) // It's a great song, throw it into YouTube
                                                   // Macklemore does a good cover, too
            return false;
        return true;
    }

    public void runTests() {
        // There's gotta be a cleaner way to do this
        Supplier<Boolean> testIsFace = this::testIsFace;
        Supplier<Boolean> testIndexOfFirstFace = this::testIndexOfFirstFace;
        Supplier<Boolean> testIndexOfLastFace = this::testIndexOfLastFace;
        Supplier<Boolean> testReversed = this::testReversed;
        Supplier<Boolean> testNumOccurances = this::testNumOccurances;
        Supplier<Boolean> testSameInReverse = this::testSameInReverse;
        Supplier<Boolean> testWithoutFaces = this::testWithoutFaces;
        Supplier<Boolean> testContainsOnlyFaces = this::testContainsOnlyFaces;
        Supplier<Boolean> testContainsNoFaces = this::testContainsNoFaces;
        Supplier<Boolean> testLastFirst = this::testLastFirst;

        Supplier[] tests = new Supplier[] {
                testIsFace, 
                testIndexOfFirstFace,
                testIndexOfLastFace,
                testReversed,
                testNumOccurances, 
                testSameInReverse, 
                testWithoutFaces, 
                testContainsOnlyFaces, 
                testContainsNoFaces, 
                testLastFirst
            };

        for (Supplier test : tests) {
            System.out.println(test.get());
        }
    }


    // Wanted to do other things, like print the name of `test`
    public static void main(String[] args) {
        StringPractice sp = new StringPractice();
        sp.runTests();
    }
}








