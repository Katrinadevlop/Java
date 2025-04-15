import Accounts.CreditAccount;
import Accounts.SimpleAccount;
import Loggers.SimpleLogger;
import Loggers.SmartLogger;

public class Main {
    public static void main(String[] args) {
        SimpleAccount simple = new SimpleAccount(1000);
        CreditAccount credit = new CreditAccount(0, 500);
        SimpleLogger simpleLogger = new SimpleLogger();
        SmartLogger smartLogger = new SmartLogger();

        System.out.println(simple.pay(200));
        System.out.println(simple.getBalance());
        simpleLogger.log("Привет");
        smartLogger.log("Сматр");

        System.out.println(simple.transfer(credit, 300));
        System.out.println(credit.getBalance());
        System.out.println(simple.getBalance());
        simpleLogger.log("Погода");
        smartLogger.log("Ура");

        System.out.println(credit.pay(100));
        System.out.println(credit.getBalance());
        simpleLogger.log("Как");
        smartLogger.log("Да");

        System.out.println(credit.add(100));
        System.out.println(simple.getBalance());
        System.out.println(credit.getBalance());
        simpleLogger.log("Го");
        smartLogger.log("Поехали");
    }
}