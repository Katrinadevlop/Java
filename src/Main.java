import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PasswordChecker passwordChecker = new PasswordChecker();

        System.out.println("Введите мин. длину пароля: ");
        int minLength = scanner.nextInt();
        passwordChecker.setMinLength(minLength);

        System.out.println("Введите макс. допустимое количество повторений символа подряд: ");
        int maxRepeats = scanner.nextInt();
        passwordChecker.setMaxRepeats(maxRepeats);

        while(true){
            System.out.println("Введите пароль или end: ");
            String password = scanner.nextLine();

            if (password.equals("end")){
                System.out.println("Программа завершена");
                break;
            }

            System.out.println(passwordChecker.verify(password));
        }
    }
}