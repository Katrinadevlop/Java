package Accounts;

public class CreditAccount extends Account {
    private long creditLimit;

    public CreditAccount(long creditLimit) {
        this.amount = creditLimit;
        this.creditLimit = creditLimit;
    }

    @Override
    public boolean add(long amount) {
        if (amount + this.amount > creditLimit) {
            return false;
        }
        else {
            this.amount += amount;
            return true;
        }
    }

    @Override
    public boolean pay(long amount) {
        if (this.amount - amount < 0) {
            return false;
        } else {
            this.amount -= amount;
            return true;
        }
    }
}
