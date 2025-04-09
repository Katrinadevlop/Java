import taxSystem.TaxSystem;
import taxSystem.USNIncome;
import taxSystem.USNIncomeMinusExpenses;

public class Main {
    public static void main(String[] args) {
        TaxSystem taxSystem = new USNIncomeMinusExpenses();
        Company company = new Company("feow", taxSystem);
        company.shiftMoney(400);
        company.shiftMoney(-100);
        company.shiftMoney(600);
        System.out.println(company.payTaxes());

        TaxSystem taxSystem1 = new USNIncome();
        Company company1 = new Company("kore", taxSystem1);
        company1.shiftMoney(400);
        company1.shiftMoney(-100);
        company1.shiftMoney(600);
        System.out.println(company1.payTaxes());
    }
}

