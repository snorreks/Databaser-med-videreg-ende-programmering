package Meny;

public class Euler {
    public static void main(String[] args) {

        double y = 3;
        double x = 0;
        double h = 0.1;
        double f;

        for (int n = 0; n < 1000000; n++) {
            f = 6 * Math.pow(x, 2) - 3 * Math.pow(x, 2) * y;
            if (n == 10) {
                System.out.println(n + "\nx: " + x + " y: " + y + " f: " + f);
                break;
            }
            x += h;
            y = y + h * f;

        }
    }
}
