import Accounts.CreditAccount;
import Accounts.SimpleAccount;
import Loggers.SimpleLogger;
import Loggers.SmartLogger;

public class Main {
    public static void main(String[] args) {
        CreditAccount creditAccount = new CreditAccount(50000);
        SimpleAccount simpleAccount = new SimpleAccount();

        System.out.println("Добавляем на дебет счет 120 000 руб.");
        System.out.println("Результат: "+simpleAccount.add(120_000));
        System.out.println("Баланс дебет счета: "+simpleAccount.getBalance()+"\n");

        System.out.println("Снимаем с дебет счета 18 000 руб.");
        System.out.println("Результат: "+simpleAccount.pay(18_000));
        System.out.println("Баланс дебет счета: "+simpleAccount.getBalance()+"\n");

        System.out.println("Снимаем с дебет счета 120 000 руб.");
        System.out.println("Результат: "+simpleAccount.pay(120_000));
        System.out.println("Баланс дебет счета: "+simpleAccount.getBalance()+"\n");


        System.out.println("Добавляем на кредит счет 120 000 руб.");
        System.out.println("Результат: "+creditAccount.add(120_000));
        System.out.println("Баланс кредит счета: "+creditAccount.getBalance()+"\n");

        System.out.println("Снимаем с кредит счета 18 000 руб.");
        System.out.println("Результат: "+creditAccount.pay(18_000));
        System.out.println("Баланс кредит счета: "+creditAccount.getBalance()+"\n");

        System.out.println("Снимаем с кредит счета 120 000 руб.");
        System.out.println("Результат: "+creditAccount.pay(120_000));
        System.out.println("Баланс кредит счета: "+creditAccount.getBalance()+"\n");


        System.out.println("Транзакция с дебет на кредит 100000");
        System.out.println("Результат: "+simpleAccount.transfer(creditAccount, 100_000));
        System.out.println("Баланс дебет: "+simpleAccount.getBalance()+", Баланс кредит: "+creditAccount.getBalance()+"\n");
        System.out.println("Транзакция с дебет на кредит 8000");
        System.out.println("Результат: "+simpleAccount.transfer(creditAccount, 8000));
        System.out.println("Баланс дебет: "+simpleAccount.getBalance()+", Баланс кредит: "+creditAccount.getBalance()+"\n");
        System.out.println("Транзакция с кредит на дебет 100000");
        System.out.println("Результат: "+creditAccount.transfer(simpleAccount, 100_000));
        System.out.println("Баланс дебет: "+simpleAccount.getBalance()+", Баланс кредит: "+creditAccount.getBalance()+"\n");
        System.out.println("Транзакция с кредит на дебет 8000");
        System.out.println("Результат: "+creditAccount.transfer(simpleAccount, 8000));
        System.out.println("Баланс дебет: "+simpleAccount.getBalance()+", Баланс кредит: "+creditAccount.getBalance()+"\n");
    }
}