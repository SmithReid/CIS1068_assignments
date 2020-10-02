//Code written by Reid Smith
//August 31, 2020
//Assignment 2: Song
//Compiles a song

//cat, hen, duck, goose, sheep

public class Song {

    public static void catSound() {
        System.out.println("Cat goes fiddle-i-fee.");
    }
    public static void henSound() {
        System.out.println("Hen goes chimmy-chuck, chimmy-chuck,");
        catSound();
    }
    public static void duckSound() {
        System.out.println("Duck goes quack, quack,");
        henSound();
    }
    public static void gooseSound() {
        System.out.println("Goose goes hissy, hissy,");
        duckSound();
    }
    public static void sheepSound() {
        System.out.println("Sheep goes baa, baa,");
        gooseSound();
    }
    public static void otherSound() {
        System.out.println("{other} goes lorem ipsum,");
        sheepSound();
    }
    
    public static void animalPurchase(String animal) {
        System.out.println("Bought me a " + animal + " and the " + animal + " pleased me,");
        System.out.println("I fed my " + animal + " under yonder tree.");
    }

    public static void main(String[] args) {
        animalPurchase("cat");
        catSound();
        System.out.println();
        animalPurchase("hen");
        henSound();
        System.out.println();
        animalPurchase("duck");
        duckSound();
        System.out.println();
        animalPurchase("goose");
        gooseSound();
        System.out.println();
        animalPurchase("sheep");
        sheepSound();
        System.out.println();
        animalPurchase("{other}");
        otherSound();
        System.out.println();
    }
}