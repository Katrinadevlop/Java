package Accounts;

abstract class Account {
    protected long amount;

    public Account(long amount) {
        this.amount = amount;
    }

    public abstract boolean add(long amount);

    public abstract boolean pay(long amount);

    public boolean transfer(Account account, long amount) {
        if (amount > 0 && this.amount >= amount) {
            pay(amount);
            return account.add(amount);
        }
        return false;
    }

    public long getBalance() {
        return amount;
    }
}
