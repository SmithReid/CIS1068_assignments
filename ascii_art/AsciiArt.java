//Code written by Reid Smith
//Begun 2020-09-11
//Code will print the Mandelbrot set and then the Eagle's stadium
//Attemped extra credit and failed. I believe this program meets the specifications 
    //the assignment
//Please see Mandelbrot.java for an additional nested for loop

public class AsciiArt {
    /*SIZE = 4 will reproduce the image from the assignment. I recommend some values
        in excess of 10 for some pretty mandelbrot set stuff*/
    public static final int SIZE = 4;

    public static void parking_lot() {
        System.out.println(" _________________");
        for (int i = 0; i <= SIZE * SIZE; i++) {
            System.out.print("|");
            for (int j = 0; j < 2; j++){
                for (int k = 0; k < SIZE * 2; k++) {
                    System.out.print("_");
                }
                System.out.print("|");
            }
            System.out.println();
        }
    }

    public static void line(int n) {
        for (int i = 0; i < SIZE * SIZE - n; i++) {
            System.out.print(" ");
        }

        for (int i = 0; i < n; i++) {
            if (i == 0) {
                System.out.print("_/");
            } else if (i == 1) {
                //do nothing
            } else {
                System.out.print(".");
            }
        }

        for (int i = n; i > 0; i--) {
            if (i == 0) {
                System.out.print("/");
            } else {
                System.out.print(".");
            }
        }

        for (int i = 0; i < SIZE * SIZE - n; i++) {
            System.out.print(" ");
        }
    }

    public static void stadium() {
        System.out.print(" ");
        for (int i = 0; i <= SIZE * SIZE; i++) {
            System.out.print("__");
        }
        System.out.println();

        for (int i = 0; i < (SIZE * SIZE) / 2; i++) {
            System.out.print("|");
            
            line(i);

            System.out.print("|");
            System.out.println();
        }

        for (int i = SIZE * SIZE; i >= 0; i--) {
            System.out.print("|");
            
            line(i);

            System.out.print("|");
            System.out.println();
        }

        System.out.print("|");
        for (int i = 0; i <= SIZE * SIZE; i++) {
            System.out.print("__");
        }
        System.out.print("|");    
    }

    public static void main(String[] args) {
        Mandelbrot mandelbrot = new Mandelbrot();
        System.out.println();
        parking_lot();
        System.out.println();
        stadium();
    }
}
