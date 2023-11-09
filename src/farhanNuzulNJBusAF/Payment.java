package farhanNuzulNJBusAF;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

public class Payment extends Invoice {
    private int busId;
    public Timestamp departureDate;
    public String busSeat;

    public Payment(int buyerId, int renterId, int busId, String busSeat, Timestamp departureDate) {
        super(buyerId, renterId);
        this.busId = busId;
        this.busSeat = busSeat;
        this.departureDate = new Timestamp(System.currentTimeMillis());
    }

    public Payment(Account buyer, Renter renter, int busId, String busSeat, Timestamp departureDate) {
        super(buyer, renter);
        this.busId = busId;
        this.busSeat = busSeat;
        this.departureDate = new Timestamp(System.currentTimeMillis());
    }
    
   /* public static boolean isAvailable(Timestamp departureSchedule, String seat, Bus bus) {
        for (Schedule schedule : bus.schedules) {
            if (schedule.departureSchedule.equals(departureSchedule) && schedule.seatAvailability.containsKey(seat)) {
                Boolean isSeatAvailable = schedule.seatAvailability.get(seat);
                return isSeatAvailable != null && isSeatAvailable;
            }
        }
        return false; 
    } */

    public static boolean makeBooking(Timestamp departureSchedule, List<String> seat, Bus bus){
            boolean allBooked = true;
            for (String s : seat){
                boolean booked = makeBooking(departureSchedule, s, bus);
                if (!booked){
                    allBooked = false;
                }
            }
            return allBooked;
//        if(availableSchedule(departureSchedule, seat, bus) != null){
//            for(Schedule s: bus.schedules){
//                if(s.departureSchedule.equals(departureSchedule)){
//                    s.bookSeat(seat);
//                    s.bookSeat(seat);
//                    return true;
//                }
//            }
//        }
//        return false;
    }

    public static boolean makeBooking(Timestamp departureSchedule, String seat, Bus bus){
        if(availableSchedule(departureSchedule, seat, bus) != null){
            for(Schedule s: bus.schedules){
                if(s.departureSchedule.equals(departureSchedule)){
                    s.bookSeat(seat);
                    return true;
                }
            }
        }
        return false;
    }



    public static Schedule availableSchedule(Timestamp departureSchedule, String seat, Bus bus) {
        for (Schedule schedule : bus.schedules) {
            if (schedule.departureSchedule.equals(departureSchedule)) {
                return schedule;
            }
        }
        return null;
    }

    public static Schedule availableSchedule(Timestamp departureSchedule, List <String> seat, Bus bus) {
        for (Schedule schedule : bus.schedules) {
            if (schedule.departureSchedule.equals(departureSchedule)) {
                return schedule;
            }
        }
        return null;
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
