package farhanNuzulNJBusAF;

import java.sql.Timestamp;

public class JBus
{
    public static Bus createBus() {
        Price price = new Price(750000, 5);
        Bus bus = new Bus(1, "Netlab Bus", Facility.LUNCH, price, 20, BusType.REGULER, City.BANDUNG, new Station(1, "Depok Terminal", City.DEPOK, "Jl. Margonda Raya"), new Station(2, "Halte UI", City.JAKARTA, "Universitas Indonesia"));
        return bus;
    }

    public static void main(String[] args) {
        Bus b = createBus();
        Timestamp schedule1 = Timestamp.valueOf("2023-7-18 15:00:00");
        Timestamp schedule2 = Timestamp.valueOf("2023-7-20 12:00:00");

        b.addSchedule(schedule1);
        b.addSchedule(schedule2);

        b.schedules.forEach(Schedule::printSchedule);

        for (Schedule s: b.schedules) {
               s.printSchedule();
        }

        //Invalid date
        Timestamp t1 = Timestamp.valueOf("2023-7-19 15:00:00");
        System.out.println("Make booking at July 19, 2023 15:00:00 Seat ER12");
        System.out.println(Payment.makeBooking(t1, "AF12", b));

        //Valid date, invalid seat
        Timestamp t2 = Timestamp.valueOf("2023-7-18 15:00:00");
        System.out.println("\nMake booking at July 18, 2023 15:00:00 Seat ER20");
        System.out.println(Payment.makeBooking(t2, "AF20", b));

        //Valid date, valid seat
        System.out.println("\nMake booking at July 18, 2023 15:00:00 Seat ER07");
        System.out.println(Payment.makeBooking(t2, "AF07", b));

        Timestamp t3 = Timestamp.valueOf("2023-7-20 12:00:00");
        System.out.println("\nMake booking at July 20, 2023 12:00:00 Seat ER01");
        System.out.println(Payment.makeBooking(t3, "AF01", b));

        System.out.println("\nMake booking at July 20, 2023 12:00:00 Seat ER01 again");
        System.out.println(Payment.makeBooking(t3, "AF01", b));

        //Check if the data changes
        System.out.println("\nUpdated Schedule\n");
        b.schedules.forEach(Schedule::printSchedule);
    }
}
