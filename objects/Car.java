public class Car {
    int year;
    String make, model;
    double mpg, milesDriven, fuelCapacity, fuelRemaining;

    public Car(int cYear, 
                String cMake, String cModel, 
                double cMpg, double cFuelCapacity, double cFuelRemaining) {
        year = cYear;
        make = cMake; 
        model = cModel;
        mpg = cMpg;
        fuelCapacity = cFuelCapacity;
        fuelRemaining = cFuelRemaining;
    }

    public void fillTankFull() {
        fuelRemaining = fuelCapacity;
    }

    public void fillTank(double gasPumped) {
        fuelRemaining += gasPumped;
    }

    public void drive(double miles) {
        milesDriven += miles;
        fuelRemaining -= miles / mpg;
    }

    public double getFuelRemaining() {
        return fuelRemaining;
    }

    public double getRange() {
        return fuelRemaining * mpg;
    }

    public String toString() {
        return year + " " + make + " " + model + "\n" + 
                "Approx fuel gauge: " + new Fraction((int) Math.round(fuelRemaining), 
                                            (int) Math.round(fuelCapacity)).simplify();
    }

    public String toStringVerbose() {
        return year + " " + make + " " + model + "\n" + 
                "mpg: " + mpg + "\n" + 
                "Fuel Remaining: " + fuelRemaining + "\n" + 
                "Fuel Capacity: " + fuelCapacity + "\n" + 
                "Range: " + (this.getRange());
    }
}