import static java.lang.Math.pow;

public class Matrix {
    final int n;
    final int m;
    ComplexNumber[] array2D;

    public Matrix() {
        n = 0;
        m = 0;
        array2D = null;
    }

    public Matrix(int n, int m) {
        this.n = n;
        this.m = m;
        array2D = new ComplexNumber[n * m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                array2D[i * m + j] = new ComplexNumber(0, 0);
            }
        }
    }

    public Matrix(int n, int m, ComplexNumber[] array2D) {
        this.n = n;
        this.m = m;
        this.array2D = array2D.clone();
    }

    public Matrix sum(Matrix other) {
        if (n != other.n || m != other.m) {
            System.out.println("Error: Matrix have different sizes");
            System.exit(0);
        }
        Matrix matrix = new Matrix(n, m);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix.array2D[i * m + j] = this.array2D[i * m + j].sum(other.array2D[i * m + j]);
            }
        }
        return matrix;
    }

    public Matrix sub(Matrix other) {
        if (n != other.n || m != other.m) {
            System.out.println("Error: Matrix have different sizes");
            System.exit(0);
        }
        Matrix matrix = new Matrix(n, m);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix.array2D[i * m + j] = this.array2D[i * m + j].sub(other.array2D[i * m + j]);
            }
        }
        return matrix;
    }

    public Matrix mul(Matrix other) {
        if (m != other.n) {
            System.out.println("Error: Matrix have the wrong sizes");
            System.exit(0);
        }
        Matrix matrix = new Matrix(n, other.m);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < other.m; j++) {
                for (int x = 0; x < m; x++) {
                    matrix.array2D[i * m + j] = matrix.array2D[i * m + j].sum(this.array2D[i * m + x].mul(other.array2D[x * m + j]));
                }
            }
        }
        return matrix;
    }

    //  A/B = A*inv(B)
    public Matrix div(Matrix other) {
        if (m != other.n) {
            System.out.println("Error: Matrix have the wrong sizes");
        }
        return this.mul(other.inverse());
    }

    public Matrix div(ComplexNumber d) {
        Matrix matrix = new Matrix(n, m);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix.array2D[i * m + j] = array2D[i * m + j].div(d);
            }
        }
        return matrix;
    }

    public Matrix transpose() {
        Matrix trans = new Matrix(m, n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                trans.array2D[j * n + i] = array2D[i * m + j];
            }
        }
        return trans;
    }

    //use finding attached Matrix
    Matrix inverse() {
        if (this.det().isZero()) {
            System.out.println("Inverse matrix isn't exist because det=0");
        }
        Matrix attached = new Matrix(n, m);
        Matrix inv = this.transpose();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                attached.array2D[i * m + j] = (inv.minor(i, j).det()).mul(pow(-1, i + j));
            }
        }
        return attached.div(this.det());
    }

    // this algorithm use "decomposition by the first line"
    public ComplexNumber det() {
        if (n != m) {
            System.out.println("The matrix isn't square");
            System.exit(0);
        }
        if (n == 1) return array2D[0];
        if (n == 2) return (array2D[0].mul(array2D[m + 1])).sub(array2D[m].mul(array2D[1]));
        ComplexNumber det = new ComplexNumber(0, 0);
        for (int j = 0; j < n; j++) {
            det = det.sum((array2D[j].mul(this.minor(0, j).det())).mul(pow(-1, j)));
        }
        return det;
    }

    public Matrix minor(int x, int y) {
        Matrix matrix = new Matrix(n - 1, n - 1);
        int _i = 0, _j = 0;
        for (int i = 0; i < n; i++) {
            if (i == x) {
                continue;
            }
            _j = 0;
            for (int j = 0; j < n; j++) {
                if (j == y) {
                    continue;
                }
                matrix.array2D[_i * (n - 1) + _j] = array2D[i * m + j];
                _j++;
            }
            _i++;
        }
        return matrix;
    }

    public void print() {
        if (n == 0 || m == 0 || array2D == null) {
            System.out.println("Empty matrix");
            System.exit(0);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                array2D[i * m + j].print();
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    @Override
    public String toString() {
        if (n == 0 || m == 0 || array2D == null) {
            System.out.println("Empty matrix");
            System.exit(0);
        }
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                out.append(array2D[i * m + j]).append(" ");

            }
            out.append("\n");
        }
        return out.toString();
    }
}

