package farhanNuzulNJBusAF;

import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;

public class Schedule {
    public Calendar departureSchedule;
    public Map<String, Boolean> seatAvailability;

    public Schedule(Calendar departureSchedule, int numberOfSeats) {
        this.departureSchedule = departureSchedule;
        initializeSeatAvailability(numberOfSeats);
    }

    private void initializeSeatAvailability(int numberOfSeats) {
        this.seatAvailability = new LinkedHashMap<>();
        for (int i = 1; i <= numberOfSeats; i++) {
            seatAvailability.put("AF" + i, true);
        }
    }
}
