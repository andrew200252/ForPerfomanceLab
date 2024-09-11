import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        System.out.print("Enter array dimension: ");
        int n = scan.nextInt();
        System.out.print("Enter the interval length: ");
        int m = scan.nextInt();

        int[] array = new int[n];

        for (int i = 0; i < n; i++) {
            array[i] = i + 1;
        }

        int current = 0;
        do {
            System.out.print(STR."\{array[current]} ");
            current = (current + m - 1) % n;
        } while (current != 0);
    }
}