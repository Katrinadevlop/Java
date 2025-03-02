import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Ekaterina Kolosova");

        Scanner scan = new Scanner(System.in);
        int icome = scan.nextInt();
        int spending = scan.nextInt();
        System.out.println("Итого (руб):");
        System.out.println(icome - spending);
    }
}