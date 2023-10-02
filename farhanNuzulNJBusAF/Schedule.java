package farhanNuzulNJBusAF;

import java.util.LinkedHashMap;
import java.util.Map;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Schedule {
    public Timestamp departureSchedule;
    public Map<String, Boolean> seatAvailability;

    public Schedule(Timestamp departureSchedule, int numberOfSeats) {
        this.departureSchedule = departureSchedule;
        this.seatAvailability = new LinkedHashMap<>();
        initializeSeatAvailability(numberOfSeats);

        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy HH:mm:ss");
        String formattedDepartureSchedule = dateFormat.format(departureSchedule.getTime());
        System.out.println("Tanggal keberangkatan: " + formattedDepartureSchedule);
        printSchedule();
    }

    private void initializeSeatAvailability(int numberOfSeats) {
        for (int seatNumber = 1; seatNumber <= numberOfSeats; seatNumber++) {
            String formattedSeatNumber = String.format("%02d", seatNumber);
            seatAvailability.put("RS" + formattedSeatNumber, true);
        }
    }

    public boolean isSeatAvailable(String seat) {
        return seatAvailability.containsKey(seat) && seatAvailability.get(seat);
    }

    public void bookSeat(String seat) {
        if (isSeatAvailable(seat)) {
            seatAvailability.put(seat, false);
            System.out.println("Kursi " + seat + " terbooking.");
        } else {
            System.out.println("Kursi " + seat + " tidak tersedia.");
        }
    }

    public void printSchedule() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy HH:mm:ss");
        String formattedDepartureSchedule = dateFormat.format(this.departureSchedule.getTime());

        System.out.println("Tanggal keberangkatan: " + formattedDepartureSchedule);

        System.out.println("Daftar kursi dan ketersediaan kursinya: ");

        int maxSeatsPerRow = 4;
        int currentSeat = 1;

        for (String seat : this.seatAvailability.keySet()) {
            String symbol = this.seatAvailability.get(seat)? "O" : "X";
            System.out.print(seat + " : " + symbol + "\t");
            if (currentSeat % maxSeatsPerRow == 0) {
                System.out.println();
            }
            currentSeat++;
        }
        System.out.println("\n");
    }
}

