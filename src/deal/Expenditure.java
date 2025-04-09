package deal;

public class Expenditure extends Deal{
    private String productName;
    private int productPrice;
    public Expenditure(String productName, int productPrice) {
        super("Покупка " + productName + " на " + productPrice + " руб.", productPrice, 0);
        this.productName = productName;
        this.productPrice = productPrice;
    }
}
