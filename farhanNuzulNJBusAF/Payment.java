package farhanNuzulNJBusAF;

import java.util.Calendar;
import java.text.*;

public class Payment extends Invoice{
    private int busId;
    public Calendar departureDate;
    public String busSeat;
    
    public Payment(int id, int buyerId, int renterId, int busId, String busSeat){
        super (id, buyerId, renterId);
        this.busId = busId;
        this.busSeat = busSeat;
    }
    
    public Payment(int id, Account buyer, Renter renter, int busId, String busSeat){
        super (id, buyer, renter);
        this.busId = busId;
        this.busSeat = busSeat;        
    }
    
    public String getDepartureInfo(){
        return "id: " + this.id + " Time: " + this.time + " buyerID: "+ this.buyerId + " renterId: " + this.renterId + " busId: " + this.busId + " departureDate: " + this.departureDate + " busSeat: " + this.busSeat;
    }
    
    public int getBusId(){
        return this.busId;
    }
    
    public String getTime(){
        SimpleDateFormat DateFormat = new SimpleDateFormat ("MMMm DD, hh:mm:ss");
        String curr_date = DateFormat.format(departureDate.getTime());
        return curr_date;
    }
}
