import java.util.Scanner;
import static ru.netology.service.CustomsService.calculateCustoms;

//Структура программы
public class Main {
    public static void main(String[] arg){
        System.out.println("Добро пожаловать в калькулятор расчёта пошлины!");
        System.out.print("Введите цену товара (в руб.): ");

        Scanner scanner = new Scanner(System.in);
        int price  = scanner.nextInt();

        System.out.print("Введите вес товара (в кг.): ");
        int weight = scanner.nextInt();

        System.out.print("Размер пошлины (в руб.) составит: " + calculateCustoms(price,weight));
    }
}