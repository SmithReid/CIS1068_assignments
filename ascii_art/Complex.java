//Code written by Reid Smith
//Begun 2020-09-10
//A simple custom complex number class

public class Complex {
    public static final double TWO = 2.0;

    double real;
    double imaginary;

    public Complex(double cReal, double cImaginary) {
        real = cReal;
        imaginary = cImaginary;
    }

    public Complex selfSquare() {
        return new Complex(Math.pow(real, TWO) - Math.pow(imaginary, TWO), 
            TWO * real * imaginary);
    }

    public Complex add(Complex other) {
        return new Complex(real + other.real, imaginary + other.imaginary);
    }
}