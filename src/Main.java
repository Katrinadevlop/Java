import deal.Deal;
import deal.Expenditure;
import deal.Sale;
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
        company1.setTaxSystem(taxSystem);
        company1.shiftMoney(400);
        company1.shiftMoney(-100);
        company1.shiftMoney(600);
        System.out.println(company1.payTaxes());

        Company company2 = new Company("OQ9", taxSystem1);
        company2.shiftMoney(400);
        company2.shiftMoney(-1000);
        company2.shiftMoney(600);
        System.out.println(company2.payTaxes());
        Deal[] deal = {
                new Sale("Машина", 100),
                new Expenditure("Машина", 200)
        };

        System.out.println("Сделка: " + deal[0].comment + " разницу доходов и расходов, которая была на момент старта уплаты налогов: " + company2.applyDeals(deal));
        System.out.println("Сделка: " + deal[1].comment + " разницу доходов и расходов, которая была на момент старта уплаты налогов: " + company2.applyDeals(deal));
    }
}

