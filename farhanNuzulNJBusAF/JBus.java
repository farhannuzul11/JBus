package farhanNuzulNJBusAF;

public class JBus{
    public static void main (String [] args){
    Review testReview = new Review(1, "23 August 2023", "Bad Quality");
    Price testPrice = new Price (100000, 20000);
    Station testDeparture = new Station (2, "Depok Terminal", City.DEPOK, "Jl. Margonda Raya");
    Station testArrival = new Station (3, "Halte UI", City.JAKARTA, "Universitas Indonesia");
    Bus testBus = new Bus (1, "Busway", Facility.AC, testPrice, 50, BusType.REGULER, City.DEPOK, testDeparture, testArrival);
    Account testAccount = new Account (1, "Bob", "bob@gmail.com", "bob");
    Rating testRating = new Rating();
    System.out.println(testReview);
    System.out.println(testBus);
    System.out.println(testAccount);
    System.out.println(testPrice);
    System.out.println(testRating);
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

