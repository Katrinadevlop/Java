package Accounts;

public class SimpleAccount extends Account {

    @Override
    public boolean add(long amount) {
            this.amount += amount;
            return this.amount >= 0;
    }

    @Override
    public boolean pay(long amount) {
        if (this.amount < amount) {
            return false;
        } else {
            this.amount -= amount;
            return true;
        }
    }
}
