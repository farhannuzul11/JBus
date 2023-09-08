package farhanNuzulNJBusAF;

public class JBus{
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
            //belum konversi dari int -> float
        }
        return a;
    }
    
    getOriginalPrice(int discountedPrice, float discountPercentage)
    
    public static void main (String [] args){
        int i = getBusId();
        System.out.println(i);
        
        String s = getBusName();
        System.out.println(s);
        
        boolean b = isDiscount();
        System.out.println(b);
        
        float f = getDiscountPercentage(1000, 900);
        System.out.println(f);
    }
}


