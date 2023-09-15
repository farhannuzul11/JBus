package farhanNuzulNJBusAF;



public class Price
{
    private double price;
    private int discount;
    private double rebate;
    
    public Price(double price){
        this.price = price;
        this.discount = 0;
        this.rebate = 0;
    }
    
    public Price(double price, int discount){
        this.price = price;
        this.discount = discount;
        this.rebate = 0;       
    }
    
    public Price(double price, double rebate){
        this.price = price;
        this.discount = 0;
        this.rebate = rebate;        
    }
    
    //getter
    public double getPrice() {
        return price;
    }

    public int getDiscount() {
        return discount;
    }

    public double getRebate() {
        return rebate;
    }
    
    //setter
    public void setPrice(double price) {
        this.price = price;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public void setRebate(double rebate) {
        this.rebate = rebate;
    }
}
