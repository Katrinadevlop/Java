import deal.Deal;
import taxSystem.TaxSystem;

class Company {
    private String title;
    private int debit = 0;
    private int credit = 0;
    private TaxSystem taxSystem;

    public Company(String title, TaxSystem taxSystem) {
        this.title = title;
        this.taxSystem = taxSystem;
    }

    public void shiftMoney(int amount) {
        if (amount > 0) {
            debit += amount;
        } else if (amount < 0) {
            credit += Math.abs(amount);
        }
    }

    public void setTaxSystem(TaxSystem taxSystem) {
        this.taxSystem = taxSystem;
    }

    public String payTaxes() {
        String res = "";
        int tax = taxSystem.calcTaxFor(debit, credit);
        if (tax <= -1) {
            res = "Налог не может быть отрицательным";
        } else {
            res = "Компания " + title + " уплатила налог в размере: " + tax + " руб.;";
            credit = 0;
            debit = 0;
        }
        return res;
    }

    public int applyDeals(Deal[] deals) {
        for (Deal d : deals) {
            credit += d.creditChange;
            debit += d.debitChange;
        }
        int difference = Math.abs(debit - credit);
        System.out.println(payTaxes());
        return difference;
    }
}