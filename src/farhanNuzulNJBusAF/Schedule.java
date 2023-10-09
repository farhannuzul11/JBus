package farhanNuzulNJBusAF;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;
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
            seatAvailability.put("AF" + formattedSeatNumber, true);
        }
    }

    public boolean isSeatAvailable(List<String> seat) {
        boolean allSeatsAvailable = true;
        for (String seats : seat) {
            if (!seatAvailability.containsKey(seats) || !seatAvailability.get(seats)) {
                allSeatsAvailable = false;
                break;
            }
        }
        return allSeatsAvailable;
    }



    public void bookSeat(List<String> seats) {
        boolean allSeatsAvailable = isSeatAvailable(seats);
        if (allSeatsAvailable) {
            for (String seat : seats) {
                seatAvailability.put(seat, false);
                System.out.println("Kursi " + seat + " terbooking.");
            }
        } else {
            System.out.println("Beberapa kursi tidak tersedia.");
        }
    }

    public String toString() {
        int totalSeats = seatAvailability.size();
        int occupiedSeats = totalSeats - (int) seatAvailability.values().stream().filter(Boolean::booleanValue).count();

        return "Tanggal keberangkatan: " + departureSchedule +
                "\nJumlah kursi terisi: " + occupiedSeats +
                "\nTotal kursi: " + totalSeats;
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

