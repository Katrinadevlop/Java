package taxSystem;

public class USNIncome extends TaxSystem {
    @Override
    public int calcTaxFor(int debit, int credit) {
        int usnIncome = debit * 6 / 100;
        if (usnIncome >= 0)
            return usnIncome;
        else
            return -1;
    }
}
