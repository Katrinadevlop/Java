import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ListToDo listToDo = new ListToDo();

        while (true) {
            System.out.println();
            printMenu();
            System.out.print("Ваш выбор: ");
            int answerUser = Integer.parseInt(scanner.nextLine());
            System.out.println();

            if (answerUser == 0) {
                break;
            }

            switch (answerUser) {
                case 1:
                    System.out.print("Введите название задачи:");
                    String name = scanner.nextLine();
                    listToDo.addCase(name);
                    break;
                case 2:
                    List<String> list = listToDo.getAllCase();
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println((i + 1) + ". " + list.get(i));
                    }
                    break;
                case 3:
                    System.out.print("Введите номер для удаления:");
                    int index = Integer.parseInt(scanner.nextLine());
                    listToDo.deleteCaseIndex(index);
                    break;
                case 4:
                    System.out.print("Введите задачу для удаления:");
                    name = scanner.nextLine();
                    listToDo.deleteCaseName(name);
                    break;
                case 5:
                    System.out.print("Введите ключевое слово для удаления задач: ");
                    String keyWord = scanner.nextLine();
                    listToDo.deleteKeyWord(keyWord);
                    break;
            }
            System.out.println("Ваш список дел:");
            List<String> list = listToDo.getAllCase();
            for (int i = 0; i < list.size(); i++) {
                System.out.println((i + 1) + ". " + list.get(i));
            }
        }
    }

    public static void printMenu() {
        System.out.println("Выберите операцию:\n" +
                "0. Выход из программы\n" +
                "1. Добавить дело\n" +
                "2. Показать дела\n" +
                "3. Удалить дело по номеру\n" +
                "4. Удалить дело по названию\n" +
                "5. Удалить дело по ключевому слову"
        );
    }
}