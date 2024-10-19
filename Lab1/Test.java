public class Test {

    public static void main(String[] args) {
        ComplexNumber cn1 = new ComplexNumber(4, -6);
        ComplexNumber cn2 = new ComplexNumber(2, 5);

        System.out.println("cn1: " + cn1);
        System.out.println("cn1: " + cn2);
        System.out.println();

        System.out.println("sum: " + (cn1.sum(cn2)));
        System.out.println("sub: " + (cn1.sub(cn2)));
        System.out.println("mul: " + (cn1.mul(cn2)));
        System.out.println("div: " + (cn1.div(cn2)));

        ComplexNumber[] array1 = new ComplexNumber[]{
                new ComplexNumber(4, 1), new ComplexNumber(-3, 3),
                new ComplexNumber(-5, -6), new ComplexNumber(6, -6),

        };
        ComplexNumber[] array2 = new ComplexNumber[]{
                new ComplexNumber(-2, 8), new ComplexNumber(0, -6),
                new ComplexNumber(3, -2), new ComplexNumber(6, 1),

        };

        Matrix matrix1 = new Matrix(2, 2, array1);
        Matrix matrix2 = new Matrix(2, 2, array2);

        System.out.println("matrix1:\n" + matrix1);
        System.out.println("matrix2:\n" + matrix2);

        System.out.println("sum: \n" + matrix1.sum(matrix2));
        System.out.println("sub: \n" + matrix1.sub(matrix2));
        System.out.println("mul: \n" + matrix1.mul(matrix2));
        System.out.println("div: \n" + matrix1.div(matrix2));

        System.out.println("det1: " + matrix1.det());
        System.out.println("det2: " + matrix2.det());

        System.out.println("transpose mat1:\n" + matrix1.transpose());
        System.out.println("inverse mat1:\n" + matrix1.inverse());
    }
}