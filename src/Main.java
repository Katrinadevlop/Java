import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Address addressParis = new Address("France", "Paris");
        Address addressBerlin = new Address("Germany", "Berlin");
        Address addressNewYork = new Address("USA", "New York");
        Address addressTokyo = new Address("Japan", "Tokyo");
        Address addressKazan = new Address("Россия", "Казань");

        Map<Address, Integer> costPerAddress = new HashMap<>();
        costPerAddress.put(addressParis, 2321);
        costPerAddress.put(addressBerlin, 2342);
        costPerAddress.put(addressNewYork, 3214);
        costPerAddress.put(addressTokyo, 2562);
        costPerAddress.put(addressKazan, 2600);

        int totalCost = 0;
        Set<String> uniqueCountries = new HashSet<>();

        while (true) {
            System.out.print("Заполним новый заказ? Да/Нет: ");
            String answerUser = scanner.nextLine();
            if (answerUser.equals("Нет")) {
                break;
            }

            System.out.print("Заполнение нового заказа.\n");
            System.out.print("Введите страну: ");
            String country = scanner.nextLine();
            System.out.print("Введите город: ");
            String city = scanner.nextLine();
            System.out.print("Введите вес (кг): ");
            int weigth = Integer.parseInt(scanner.nextLine());

            Address userAddress = new Address(country, city);
            if (costPerAddress.containsKey(userAddress)) {
                int priceKg = costPerAddress.get(userAddress);
                int shippingCost = priceKg * weigth;
                totalCost += shippingCost;
                uniqueCountries.add(country);
                System.out.print("Стоимость доставки составит: " + shippingCost + "\n");
                System.out.print("Общая стоимость всех доставок: " + totalCost + "\n");
                System.out.println("Доставки были оформлены в " + uniqueCountries.size() + " уникальные страны.");
            } else {
                System.out.println("Доставки по этому адресу нет\n");
            }
        }
    }
}