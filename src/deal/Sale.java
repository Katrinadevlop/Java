package deal;

public class Sale extends Deal {
    private String productName;
    private int productPrice;
    public Sale(String productName, int productPrice) {
        super("Продажа " + productName + " на " + productPrice + " руб.", 0, productPrice);
        this.productName = productName;
        this.productPrice = productPrice;
    }
}
