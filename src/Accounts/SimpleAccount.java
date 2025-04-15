package Accounts;

public class SimpleAccount extends Account {
    public SimpleAccount(long amount) {
        super(amount);
    }

    @Override
    public boolean add(long amount) {
        if (amount > 0) {
            this.amount += amount;
            return true;
        }
        return false;
    }

    @Override
    public boolean pay(long amount) {
        if (amount > 0 && this.amount >= amount) {
            this.amount -= amount;
            return true;
        }
        return false;
    }
}
