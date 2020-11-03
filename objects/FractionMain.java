public class FractionMain {
    public static void main(String[] args) {
        /* EXPECTED OUTPUT: 
        1/2
        2/4
        1/2
        true
        true
        false
        */
        
        Fraction half = new Fraction(1, 2);
        System.out.println(half);
        Fraction twoQuarters = new Fraction(2, 4);
        System.out.println(twoQuarters);
        System.out.println(twoQuarters.simplify());

        System.out.println(new Fraction(1, 2)
                            .equals(new Fraction(1, 4)
                                .add(new Fraction(2, 8))));

        System.out.println(new Fraction(1, 2).equals(new Fraction(2, 4)));
        System.out.println(new Fraction(2, 3).equals(new Fraction(3, 4)));
    }
}