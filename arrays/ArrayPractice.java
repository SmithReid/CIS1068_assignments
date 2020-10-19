/**
 *
 * @author Reid Smith
 */
public class ArrayPractice {
    /* sets every item in the array A references to initialValue */
    public static void initialize(int A[], int initialValue) {
        for (int i = 0; i < A.length; i++) {
            A[i] = initialValue;
        }
    }

    /*
     * returns the average of the items in the array A references. Be careful: the array contains int
     * but the method returns double. What do we do to handle this?
     */
    public static double average(int A[]) {
        int sum = 0;
        for (int i = 0; i < A.length; i++) {
            sum += A[i];
        }
        return Double.valueOf(sum) / Double.valueOf(A.length);

    }

    /* returns the number of times that x appears in the array A references */
    public static int numOccurrences(int A[], int x) {
        int occurances = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] == x) {
                occurances++;
            }
        }
        return occurances;
    }


    /*
     * returns the index of the first occurrence of x in the array A references or -1 if x doesn't
     * exist in the array
     */
    public static int find(int A[], int x) {
        for (int i = 0; i < A.length; i++) {
            if (A[i] == x) {
                return i;
            }
        }
        return -1;
    }

    /*
     * Returns the index of the first occurrence of item within the first n elements of the array A[]
     * references or -1 if item is not among the first n elements of the array
     */
    public static int findN(int A[], int item, int n) {
        for (int i = 0; i < n; i++) {
            if (A[i] == item) {
                return i;
            }
        }
        return -1;
    }

    /*
     * returns the index of the last occurrence of x in the array A references or -1 if x doesn't
     * exist in the array
     */
    public static int findLast(int A[], int x) {
        for (int i = A.length - 1; i >= 0; i--) {
            if (A[i] == x) {
                return i;
            }
        }
        return -1;
    }

    /* returns the largest item found in the array A references */
    public static int largest(int A[]) {
        int largest = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] > largest) {
                largest = A[i];
            }
        }
        return largest;
    }

    /* returns the index of the largest item found in the array A references */
    public static int indexOfLargest(int A[]) {
        int largestIndex = 0;
        int largestValue = A[0];
        for (int i = 1; i < A.length; i++) {
            if (A[i] > largestValue) {
                largestIndex = i;
                largestValue = A[i];
            }
        }
        return largestIndex;
    }

    /*
     * returns the index of the largest odd number in the array A references or -1 if the array
     * contains no odd numbers
     */
    public static int indexOfLargestOdd(int A[]) {
        int largestValue = Integer.MIN_VALUE;
        int largestIndex = -1;
        for (int i = 0; i < A.length; i++) {
            if (A[i] % 2 == 1 && A[i] > largestValue) {
                largestIndex = i;
                largestValue = A[i];
            }
        }
        return largestIndex;
    }

    /*
     * returns a new array consisting of all of the elements of A[]
     */
    public static int[] copy(int A[]) {
        int[] output = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            output[i] = A[i];
        }
        return output;
    }

    /*
     * Returns a reference to a new array consisting of all of the first n elements of A[]. If
     * n>A.length, returns a reference to a new array of size n, with the first A.length elements
     * exactly the same as A, and the remaining n-A.length elements set to 0. If n<=0, returns null.
     */
    public static int[] copyN(int A[], int n) {
        if (n <= 0) {
            return null;
        }
        int[] output = new int[n];
        for (int i = 0; i < output.length; i++) {
            try {
                output[i] = A[i];
            } catch (ArrayIndexOutOfBoundsException e) {
                output[i] = 0;
            }
        }
        return output;
    }

    /*
     * returns a reference to an array consisting of all of the elements of the array A references
     * that are odd. If there are no odd integers in the array, the function returns null.
     */
    public static int[] copyOdds(int[] A) {
        int nOdds = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] % 2 == 1) {
                nOdds++;
            }
        }
        if (nOdds == 0) {
            return null;
        }
        int[] output = new int[nOdds];
        int location = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] % 2 == 1) {
                output[location] = A[i];
                location++;
            }
        }
        return output;
    }

    /* removes and returns the item at index x shifting all elements at */
    /* indices > x one position to the left and filling in a 0 at the */
    /* right-most position in the array. */

    /* if x is an invalid index, returns -1. */

    /* For example, if before we call function with x = 2, */
    /* the the array is: */

    /* |----+----+----+----+----+----+----+----+----+-----| */
    /* | 10 | 20 | 30 | 40 | 50 | 60 | 70 | 80 | 90 | 100 | */
    /* |----+----+----+----+----+----+----+----+----+-----| */

    /* after the function finishes, the array is: */

    /* |----+----+----+----+----+----+----+----+-----+---| */
    /* | 10 | 20 | 40 | 50 | 60 | 70 | 80 | 90 | 100 | 0 | */
    /* |----+----+----+----+----+----+----+----+-----+---| */

    /* and the function returns 30 */
    public static int remove(int[] A, int x) {
        if (x < 0 || x >= A.length){
            return -1;
        }
        int output = -1;
        for (int i = 0; i <= A.length - 2; i++) {
            if (i < x) {
                ; // do nothing
            } else if (i == x) {
                output = A[i];
            } else {
                A[i - 1] = A[i];
            }
        }
        A[A.length - 2] = 0;
        return output;
    }


    /* shifts all elements of the array A references one position to the left, */
    /* removing the first element and filling in 0 from the right hand side. */

    /* For example, if before we call the function the the array is: */

    /* |----+----+----+----+----+----+----+----+----+-----| */
    /* | 10 | 20 | 30 | 40 | 50 | 60 | 70 | 80 | 90 | 100 | */
    /* |----+----+----+----+----+----+----+----+----+-----| */

    /* after the function finishes, the array is: */

    /* +----+----+----+----+----+----+----+----+-----|----| */
    /* | 20 | 30 | 40 | 50 | 60 | 70 | 80 | 90 | 100 | 0 | */
    /* +----+----+----+----+----+----+----+----+-----|----| */
    public static void shiftLeft(int[] A) {
        for (int i = 0; i < A.length - 2; i++) {
            A[i] = A[i + 1];
        }
        A[A.length - 2] = A[A.length - 1];
    }


    /*
     * returns true if A is in sorted ascending order and false otherwise
     */
    public static boolean isSortedAscending(int[] A) {
        int previous = A[0];
        for (int i = 1; i < A.length; i++) {
            if (!(A[i] >= previous)) {
                return false;
            }
            previous = A[i];
        }
        return true;
    }

    /* Returns the number of items in the array that A references starting at index x that are in */
    /* ascending sorted order. */

    /* For example, if the array is: */
    /* |----+----+---+---+---+---+----+----+----| */
    /* | 0  | 1  | 2 | 3 | 4 | 5 | 6  | 7  | 8 | */
    /* |----+----+---+---+---+---+----+----+----| */
    /* | 10 | 11 | 5 | 3 | 9 | 6 | 18 | 37 | 40 | */
    /* |----+----+---+---+---+---+----+----+----| */

    /* and x is 0, the function return 2, because 10 and 11 are in sorted order. */

    /* If x is 5, the function returns 4, because 6, 18, 37, and 40 are in sorted order. */

    /*
     * If x is 2, the function returns 1.
     * 
     */
    public static int sortedAscendingRun(int[] A, int x) {
        int previous = A[x-1];
        int sortCount = 0;
        for (int i = x; i < A.length; i++) {
            if (!(A[i] >= previous)) {
                return sortCount;
            }
            sortCount++;
            previous = A[i];
        }
        return sortCount;
    }

    /*
     * returns a new array consisting of all of the elements of A[] followed by all of the elements of
     * B[]. For example, if A[] is: {10,20,30} and B[] is: {5, 9, 38}, the method returns the array :
     * {10,20,30,5,9,38}
     */
    public static int[] copyAll(int A[], int B[]) {
        int[] output = new int[A.length + B.length];
        int outputIndex = 0;
        for (int i = 0; i < A.length; i++) {
            output[outputIndex] = A[i];
            outputIndex++;
        }
        for (int i = 0; i < B.length; i++) {
            output[outputIndex] = B[i];
            outputIndex++;
        }
        return output; 
    }

    /*
     * reverses the order of the elements in A[]. For example, if A[] is: {10,20,30,40,50}, after the
     * method, A[] would be {50,40,30,20,10}
     */
    public static void reverse(int A[]) {
        int otherSide = A.length;
        int temp;
        for (int i = 0; i < A.length / 2; i++) {
            temp = A[i];
            A[i] = A[otherSide];
            A[otherSide] = temp;
            otherSide--;
        }
    }

    /*
     * Extra credit:
     *
     * Returns a new array consisting of all of the elements of A, but with no duplicates. For
     * example, if A[] is {10,20,5,32,5,10,9,32,8}, the method returns the array {10,20,5,32,9,8}
     */
    public static int[] uniques(int A[]) {
        return null;
    }

    public static void main(String[] args) {
        ; // do nothing
    }
}





