package farhanNuzulNJBusAF;

public class JBus{
    public static void main (String [] args){
        Payment testPayment = new Payment (1, 1, 1, "A", 1, "A", "A");
        Invoice testInvoice = new Invoice (2, 2, 2, "B");
        Station testStation = new Station (3, "C", City.DEPOK);
        System.out.println(testPayment.print());
        System.out.println(testInvoice.print());
        System.out.println(testStation.print());    
    }
}
 
    /*public static Bus createBus(){
        Price price = new Price(750000, 5);
        Bus bus = new Bus ("Netlab", Facility.LUNCH, price, 25);
        return bus;
    }
    
    public static void main (String [] args){
        Price tesPrice = new Price(1000);
        Voucher tes = new Voucher("Netlab", 100, Type.DISCOUNT, 100, 20);
        
        System.out.println(tes.name);
        System.out.println(tes.canApply(tesPrice));
        System.out.println(tes.apply(tesPrice));
        Bus testBus = createBus();
        System.out.println(testBus.name);
        System.out.println(testBus.facility);
        System.out.println(testBus.price.price);
        System.out.println(testBus.capacity);*/



/*public class JBus{
    public static int getBusId(){
        return 0;
    }
    
    public static String getBusName(){
        return "Bus";
    }
    
    public static boolean isDiscount(){
        return true;
    }
    
    public static float getDiscountPercentage(int beforeDiscount, int afterDiscount){
        float a;
        if (beforeDiscount < afterDiscount){
            System.out.println("Tidak ada potongan Harga");
            a = 0;
        } else{
            a = beforeDiscount - afterDiscount;
            a = (a/beforeDiscount)*100;
        }
        return a;
    }
    
    
    public static int getDiscountedPrice(int price, float discountPercentage){
        if (discountPercentage > 100.0f){
            discountPercentage = 100.0f;
        } 
        int discountedPrice = (int) ((100-discountPercentage)*price)/100;
        return discountedPrice;
    }
    
    public static int getOriginalPrice(int discountedPrice, float discountPercentage){
        if (discountPercentage > 100.0f){
            discountPercentage = 100.0f;
        }
        
        int originalPrice = (int) (discountedPrice / ((100 - discountPercentage) / 100));
        
        return originalPrice;
    }
    
    public static float getAdminFeePercentage(){
         return 0.05f;
    }
    
    public static int getAdminFee(int price){
         float result = price*0.05f;
         int adminFee = (int) result;
         return adminFee;
    }
    
    public static int getTotalPrice(int price, int numberOfSeat){
        float adminFeeFloat = price*0.05f;
        int adminFee = (int) adminFeeFloat;
        
        int result = (price*numberOfSeat)+(adminFee*numberOfSeat);
        return result;
    }
    
    public static void main (String [] args){
        int i = getBusId();
        System.out.println(i);
        
        String s = getBusName();
        System.out.println(s);
        
        boolean b = isDiscount();
        System.out.println(b);
        
        float f = getDiscountPercentage(1000, 900);
        System.out.println(f);
        
        int priceAfterDiscount = getDiscountedPrice(1000, 10.0f);
        System.out.println(priceAfterDiscount);
        
        int priceBeforeDiscount = getOriginalPrice(900, 10.0f);
        System.out.println(priceBeforeDiscount);
        
        float adminFee = getAdminFeePercentage();
        System.out.println(adminFee);
        
        int adminFeeReal = getAdminFee(1000);
        System.out.println(adminFeeReal);
        
        int totalPrice =  getTotalPrice(10000,2);
        System.out.println(totalPrice);
    }
}
*/

