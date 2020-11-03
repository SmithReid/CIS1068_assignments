public class Fraction {
    int n, d;

    public Fraction(int cN, int cD) {
        n = cN;
        d = cD;
    }

    public int getNum() {
        return n;
    }

    public int getDenom() {
        return d;
    }

    public void setNum(int sN) {
        n = sN;
    }

    public void setDenom(int sD) {
        d = sD;
    }

    public String toString() {
        return n + "/" + d;
    }

    public Fraction add(Fraction toAdd) {
        Fraction simpleThat = toAdd.simplify();
        Fraction simpleThis = this.simplify();
        int commonDenominator = simpleThat.d * simpleThis.d;
        int outputNum = (simpleThis.getNum() * simpleThat.getDenom()) + 
                        (simpleThat.getNum() * simpleThis.getDenom());
        return new Fraction(outputNum, commonDenominator);
    }

    public boolean equals(Fraction other) {
        if (this.getNum() * other.getDenom() == other.getNum() * this.getDenom()) {
            return true;
        }
        return false;
    }

    public Fraction simplify() {
        int initialN = n;
        int initialD = d;
        Fraction output = new Fraction(n, d);
        for (int i = 1; i < d; i++) {
            if (d % i == 0) {
                if (n % i == 0) {
                    output = new Fraction(initialN, initialD);
                    output.n = n / i;
                    output.d = d / i;
                }
            }
        }
        return output;
    }
}