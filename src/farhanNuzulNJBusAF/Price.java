package farhanNuzulNJBusAF;



public class Price
{
    public double price;
    //public int discount;
    public double rebate;
    
    public Price(double price){
        this.price = price;
        //this.discount = 0;
        this.rebate = 0;
    }
    
    public Price(double price, double rebate){
        this.price = price;
        //this.discount = 0;
        this.rebate = rebate;        
    }
    
    public String toString(){
        return "price: " + this.price + " rebate: " + this.rebate;
    }

//    public int price() {
//
//    }
    
    /*public Price(double price, int discount){
        this.price = price;
        this.discount = discount;
        this.rebate = 0;       
    }
    
    private double getDiscountedPrice(){
        if (discount > 100){
            discount = 100;
        }
        
        double discountfloat = (double) discount;
        return (((100-discountfloat)*price)/100);
    }
    
    private double getRebatedPrice(){
        if (rebate> price){
            return 0;
        }
        else{
            return (price - rebate);
        }
    }*/
}
