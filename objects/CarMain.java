public class CarMain {
    public static void main(String[] args) {
        Car moostang = new Car(1966, "Ford", "Mustang", 13, 14, 14);
        moostang.drive(35);
        moostang.fillTankFull();
        moostang.drive(100);
        moostang.fillTank(4.0);
        System.out.println(moostang);
        System.out.println();
        System.out.println(moostang.toStringVerbose());
    }
}