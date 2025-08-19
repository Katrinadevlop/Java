import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        List<String> firstClients = List.of("Anya", "Sveta", "Olya", "Alexandra", "Ruslana", "Olesya", "Vika");
        Queue<String> clients = new ArrayDeque<>(firstClients);

        while (!clients.isEmpty()){
            String client = clients.poll();
            System.out.println("Сделал новый маникюр " + client);
            if (Math.random() < 0.5) {
                clients.offer("a friend of " + client);
            }
        }
    }
}