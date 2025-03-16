import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int income = 0;
        int expenses = 0;

        while (true) {
            System.out.println();
            System.out.println("Выберите операцию и введите её номер:\n" +
                    "1. Добавить новый доход\n" +
                    "2. Добавить новый расход\n" +
                    "3. Выбрать систему налогообложения");
            String operationNumber = scanner.nextLine();

            if (Objects.equals(operationNumber, "end")) {
                break;
            }

            switch (Integer.parseInt(operationNumber)) {
                case 1:
                    System.out.println("Введите сумму дохода:");
                    String moneyStrIncome = scanner.nextLine();
                    int currentIncome = Integer.parseInt(moneyStrIncome);
                    income += currentIncome;
                    break;
                case 2:
                    System.out.println("Введите сумму расхода:");
                    String moneyStrExpenses = scanner.nextLine();
                    int currentExpenses = Integer.parseInt(moneyStrExpenses);
                    expenses += currentExpenses;
                    break;
                case 3:
                    int usnIncome = getUsnIncome(income);
                    int usnIncomeminusExpenses = getUsnIncomeminusExpenses(income, expenses);

                    if (usnIncome == -1 || usnIncomeminusExpenses == -1)
                        System.out.println("Налог не может быть отрицательным");
                    else if (usnIncome == usnIncomeminusExpenses)
                        System.out.println("Можете выбрать любую систему налогообложения");
                    else if (usnIncome < usnIncomeminusExpenses) {
                        System.out.println("Мы советуем вам УСН доходы\n" +
                                "Ваш налог составит: " + usnIncome + " рублей\n" +
                                "Налог на другой системе: " + usnIncomeminusExpenses + " рублей\n" +
                                "Экономия: " + (usnIncomeminusExpenses - usnIncome) + " рублей");
                    } else {
                        System.out.println("Мы советуем вам УСН доходы минус расходы\n" +
                                "Ваш налог составит: " + usnIncomeminusExpenses + " рублей\n" +
                                "Налог на другой системе: " + usnIncome + " рублей\n" +
                                "Экономия: " + (usnIncome - usnIncomeminusExpenses) + " рублей");
                    }
                    break;
            }
        }
        System.out.println("Программа завершена!");
    }

    private static int getUsnIncomeminusExpenses(int income, int expenses) {
        int usnIncomeminusExpenses = (income - expenses) * 15 / 100;
        if (usnIncomeminusExpenses >= 0)
            return usnIncomeminusExpenses;
        else
            return -1;
    }

    private static int getUsnIncome(int income) {
        int usnIncome = income * 6 / 100;
        if (usnIncome >= 0)
            return usnIncome;
        else
            return -1;
    }
}
