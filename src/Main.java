import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PasswordChecker passwordChecker = new PasswordChecker();

        try {
            System.out.print("Введите минимальную длинну: ");
            int minLength = Integer.parseInt(scanner.nextLine());
            passwordChecker.setMinLength(minLength);
            System.out.print("Введите макс. количество повторений символа подряд: ");
            int maxRepeats = Integer.parseInt(scanner.nextLine());
            passwordChecker.setMaxRepeats(maxRepeats);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            while (true) {
                System.out.print("Введите пароль или end: ");
                String password = scanner.nextLine();

                if (password.equals("end")) {
                    break;
                }

                System.out.println(passwordChecker.verify(password) ? "Подходит!" : "Не подходит!");
            }
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Программа завершена");
    }
}