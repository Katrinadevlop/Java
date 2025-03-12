import java.util.Scanner;

//Условные операторы и циклы
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int count = 0;
        while(true) {
            System.out.println("Введите год: ");
            int yearLeap = scanner.nextInt();
            System.out.println("Введите количество дней: ");
            int yearDays = scanner.nextInt();

            int res = isLeapYear(yearLeap);

            if (res == yearDays)
                count++;
            else {
                System.out.println("Неправильно! В этом году " + res + " дней!");
                System.out.println("Набрано очков: " + count);
                break;
            }
        }
    }

    private static int isLeapYear(int yearLeap) {
        int res = (yearLeap % 400 == 0 || (yearLeap % 4 == 0 && yearLeap % 100 != 0)) ? 366 : 365;
        return res;
    }
}