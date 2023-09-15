package farhanNuzulNJBusAF;



public class Price
{
    public double price;
    public int discount;
    public double rebate;
    
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

}
