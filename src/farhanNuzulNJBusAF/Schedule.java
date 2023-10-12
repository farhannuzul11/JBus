package farhanNuzulNJBusAF;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

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

    public boolean isSeatAvailable(String seat) {
        return seatAvailability.containsKey(seat) && seatAvailability.get(seat);
    }

    public boolean isSeatAvailable(List<String> seats) {
        boolean allSeatsAvailable = true;
        for (String seat : seats) {
            if (!seatAvailability.containsKey(seat) || !seatAvailability.get(seat)) {
                allSeatsAvailable = false;
                break;
            }
        }
        return allSeatsAvailable;
    }



    public void bookSeat(String seat) {
        if (isSeatAvailable(seat)) {
            seatAvailability.put(seat, false);
            System.out.println("Kursi " + seat + " terbooking.");
        } else {
            System.out.println("Kursi " + seat + " tidak tersedia.");
        }
    }

    public void bookSeat(List<String> seats) {
        for (String seat : seats) {
            bookSeat(seat);
        }
    }

    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
        String formattedDepartureSchedule = dateFormat.format(this.departureSchedule.getTime());

        Predicate<Boolean> isOccupied = occupied -> !occupied;
        List<Boolean> occupiedSeatsList = new ArrayList<>(seatAvailability.values());
        List<Boolean> occupiedSeats = Algorithm.paginate(occupiedSeatsList, 0, seatAvailability.size(), isOccupied);

        int occupiedSeatsCount = occupiedSeats.size();
        return "Schedule : " + formattedDepartureSchedule + "\nOccupied : " + occupiedSeatsCount + "/" + seatAvailability.size();
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

