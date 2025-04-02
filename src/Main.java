import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] productArray = {"Молоко", "Хлеб", "Гречневая крупа"};
        int[] priseProductArray = {50, 14, 80};
        int[] basketArray = new int[3];

        printMenu(productArray, priseProductArray);

        int productNumber = 0;
        int productCount = 0;
        int sumProducts = 0;
        while (true) {
            System.out.println("Выберите товар и количество или введите `end`");

            String userAnswer = scanner.nextLine();
            if (userAnswer.equals("end")) {
                break;
            }

            productNumber = Integer.parseInt(userAnswer.split(" ")[0]);
            productCount = Integer.parseInt(userAnswer.split(" ")[1]);

            basketArray[productNumber - 1] += productCount;
        }
        sumProducts = getSumProducts(basketArray, sumProducts, priseProductArray);
        printBasket(basketArray, productArray, priseProductArray, sumProducts);
    }

    private static int getSumProducts(int[] basketArray, int sumProducts, int[] priseProductArray) {
        for (int i = 0; i < basketArray.length; i++) {
            sumProducts += priseProductArray[i] * basketArray[i];
        }
        return sumProducts;
    }

    private static void printBasket(int[] basketArray, String[] productArray, int[] priseProductArray, int cost) {
        System.out.println("Ваша корзина:");
        for (int i = 0; i < basketArray.length; i++) {
            if (basketArray[i] > 0) {
                System.out.print(productArray[i] + " " + basketArray[i] + " шт " + priseProductArray[i] + " руб/шт " + priseProductArray[i] * basketArray[i] + " руб в сумме\n");
            }
        }
        System.out.print("Итого: " + cost + " руб");
    }

    private static void printMenu(String[] productArray, int[] priseProductArray) {
        System.out.println("Список возможных товаров для покупки:");
        int count = 1;
        for (int i = 0; i < productArray.length; i++) {
            System.out.println(count + ". " + productArray[i] + " " + priseProductArray[i] + " руб/шт");
            count++;
        }
    }
}