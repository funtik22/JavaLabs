import java.text.DecimalFormat;

public class ComplexNumber {
    final private double re;
    final private double im;

    public ComplexNumber() {
        re = 0;
        im = 0;
    }

    public ComplexNumber(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public ComplexNumber sum(ComplexNumber other) {
        return new ComplexNumber(this.re + other.re, this.im + other.im);
    }

    public ComplexNumber sub(ComplexNumber other) {
        return new ComplexNumber(this.re - other.re, this.im - other.im);
    }

    public ComplexNumber mul(ComplexNumber other) {
        final double re = this.re * other.re - this.im * other.im;
        final double im = this.im * other.re + this.re * other.im;
        return new ComplexNumber(re, im);
    }

    public ComplexNumber mul(double d) {
        return new ComplexNumber(re * d, im * d);
    }

    public ComplexNumber div(ComplexNumber other) {
        ComplexNumber complexConjugate = new ComplexNumber(other.re, -other.im);
        ComplexNumber numerator = this.mul(complexConjugate);
        double denominator = other.mul(complexConjugate).re;
        double re = 0, im = 0;
        try {
            re = numerator.re / denominator;
            im = numerator.im / denominator;
        } catch (RuntimeException RE) {
            System.out.println("Division by zero");
            System.exit(0);
        }
        return new ComplexNumber(re, im);
    }

    public boolean isZero() {
        return re == 0 && im == 0;
    }

    public void print() {
        DecimalFormat df = new DecimalFormat("#.##");
        if (im >= 0) {
            System.out.printf("%7s+%-7s", df.format(re), df.format(im) + "i");
        } else {
            System.out.printf("%7s%-7s", df.format(re), df.format(im) + "i");
        }
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.##");
        if (im >= 0) {
            return String.format("%7s+%-7s", df.format(re), df.format(im) + "i");
        } else {
            return String.format("%7s%-7s", df.format(re), df.format(im) + "i");
        }
    }
}
