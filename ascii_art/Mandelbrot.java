//Code written by Reid Smith
//Begun 2020-09-10
//Loosely based on python project https://www.github.com/SmithReid/mandelbrot
//Modified for ascii art for school project

//I recognize that this is masively overkill for this assignment. 
//I don't care. It was fun. It is pretty. 

//This is a great place to read about the math behind the Mandelbrot set
//https://mathworld.wolfram.com/MandelbrotSet.html

public class Mandelbrot {
    //The code will produce an image approximately SIZE*2 by SIZE characters
    private final int MSIZE = AsciiArt.SIZE * 10;
    /*Runtime on my machine stays reasonable in excess of 100,000 iterations, 
        but 1,000 is entirely sufficient*/
    public final int ITERATIONS = 1000;
    //Frame the image on the complex plane
    public final double XMIN = -2.0;
    public final double XMAX = 1.0;
    public final double YMIN = -1.5;
    public final double YMAX = 1.5;
    //4.0 is appropriate. Most small values > 1.0 are acceptable. 
    public final double DIVERGENCEVALUE = 4.0;

    public boolean diverged(Complex z) {
        if (Double.isNaN(z.real)) {
            return true;
        } else if (z.real > DIVERGENCEVALUE || z.real < -1 * DIVERGENCEVALUE) {
            return true;
        }
        return false;
    }

    public boolean maths(double x, double y) {
        Complex z = new Complex(0, 0);
        Complex c = new Complex(x, y);
        for (int i = 0; i < ITERATIONS; i++) {
            z = z.selfSquare();
            z = z.add(c);
        }
        return diverged(z);
    }


    public Mandelbrot() {
        for (double i = YMIN; i < YMAX; i += (YMAX - YMIN) / MSIZE) {
            for (double j = XMIN; j < XMAX; j += (XMAX - XMIN) / MSIZE) {
                if (maths(j, i)) {
                    System.out.print("O");
                } else {
                    System.out.print(".");
                }
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}