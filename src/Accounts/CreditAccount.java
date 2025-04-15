package Accounts;

public class CreditAccount extends Account {
    private long creditLimit;

    public CreditAccount(long amount, long creditLimit) {
        super(amount);
        this.creditLimit = creditLimit;
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
        if (amount > 0 && (this.amount - amount) >= -creditLimit) {
            this.amount -= amount;
            return true;
        }
        return false;
    }
}
