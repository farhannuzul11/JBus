package farhanNuzulNJBusAF;

import java.util.ArrayList;

public class Validate
{
    public static ArrayList<Double> filter (Price[] list, int value, boolean less){
        ArrayList<Double> array = new ArrayList<Double>();
        
        for (int i = 0; i < list.length; i++){
            Price priceList = list[i];
            if (less == true && priceList.price <= value){
                array.add(priceList.price);
            }
            else if (less == false && priceList.price > value){
                array.add(priceList.price);
            }
        }
        return array;
    }
}
