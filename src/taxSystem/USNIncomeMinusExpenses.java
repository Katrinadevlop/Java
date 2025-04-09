package taxSystem;

public class USNIncomeMinusExpenses extends TaxSystem {

    @Override
    public int calcTaxFor(int debit, int credit) {
        int usnIncomeminusExpenses = (debit - credit) * 15 / 100;
        if (usnIncomeminusExpenses >= 0)
            return usnIncomeminusExpenses;
        else
            return -1;
    }
}
