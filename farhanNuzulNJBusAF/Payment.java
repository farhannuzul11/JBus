package farhanNuzulNJBusAF;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Payment extends Invoice {
    private int busId;
    public Timestamp departureDate;
    public String busSeat;

    public Payment(int id, int buyerId, int renterId, int busId, String busSeat, Timestamp departureDate) {
        super(id, buyerId, renterId);
        this.busId = busId;
        this.busSeat = busSeat;
        this.departureDate = new Timestamp(System.currentTimeMillis());
    }

    public Payment(int id, Account buyer, Renter renter, int busId, String busSeat, Timestamp departureDate) {
        super(id, buyer, renter);
        this.busId = busId;
        this.busSeat = busSeat;
        this.departureDate = new Timestamp(System.currentTimeMillis());
    }
    
    public static boolean isAvailable(Timestamp departureSchedule, String seat, Bus bus) {
        for (Schedule schedule : bus.schedules) {
            if (schedule.departureSchedule.equals(departureSchedule) && schedule.seatAvailability.containsKey(seat)) {
                Boolean isSeatAvailable = schedule.seatAvailability.get(seat);
                return isSeatAvailable != null && isSeatAvailable;
            }
        }
        return false; 
    }
    
    public static boolean makeBooking(Timestamp departureSchedule, String seat, Bus bus) {
    for (Schedule schedule : bus.schedules) {
        if (schedule.departureSchedule.equals(departureSchedule)) {
            if (schedule.isSeatAvailable(seat)) {
                schedule.bookSeat(seat);
                return true; 
            } else {
                return false; 
            }
        }
    }
    return false; 
    }

    public String getDepartureInfo() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss");
        String formattedDate = dateFormat.format(departureDate.getTime());
        return "id: " + this.id + " Time: " + formattedDate + " buyerID: " + this.buyerId
                + " renterId: " + this.renterId + " busId: " + this.busId + " busSeat: " + this.busSeat;
    }

    public int getBusId() {
        return this.busId;
    }

    public String getTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy HH:mm:ss");
        String formattedDate = dateFormat.format(departureDate.getTime());
        return formattedDate;
    }
}
