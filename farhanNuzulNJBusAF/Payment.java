package farhanNuzulNJBusAF;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Payment extends Invoice {
    private int busId;
    private Calendar departureDate;
    private String busSeat;

    public Payment(int id, int buyerId, int renterId, int busId, String busSeat) {
        super(id, buyerId, renterId);
        this.busId = busId;
        this.busSeat = busSeat;
        // Set departureDate to 2 days from now
        this.departureDate = Calendar.getInstance();
        this.departureDate.add(Calendar.DAY_OF_MONTH, 2);
    }

    public Payment(int id, Account buyer, Renter renter, int busId, String busSeat) {
        super(id, buyer, renter);
        this.busId = busId;
        this.busSeat = busSeat;
        // Set departureDate to 2 days from now
        this.departureDate = Calendar.getInstance();
        this.departureDate.add(Calendar.DAY_OF_MONTH, 2);
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
