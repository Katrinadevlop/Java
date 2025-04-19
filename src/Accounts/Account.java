package Accounts;

abstract class Account {
    long amount;

    public abstract boolean add(long amount);

    public abstract boolean pay(long amount);

    public boolean transfer(Account account, long amount) {
        if (pay(amount)) {
            if(account.add(amount)){
                return true;
            } else {
                add(amount);
                return false;
            }
        } return false;
    }

    public long getBalance() {
        return this.amount;
    }
}
