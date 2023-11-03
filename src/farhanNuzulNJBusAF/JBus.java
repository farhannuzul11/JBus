package farhanNuzulNJBusAF;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

public class JBus {
    public static List<Bus> filterByDeparture(List<Bus> buses,City departure, int page, int padeSize){
        try {
            String filepath =
                    "C:\\Users\\Rafie\\netlabJBus\\JBus\\data\\buses.json";
            JsonTable<Bus> busList = new JsonTable<>(Bus.class,filepath);
            List<Bus> filteredBus =
                    filterByDeparture(busList,City.JAKARTA,1,10);
            filteredBus.forEach(bus -> System.out.println(bus.toString()));
        }
        catch (Throwable t) {
            t.printStackTrace();
        }
        return buses;
    }


    public static void main(String[] args) {

        //TP Modul 6

//        String filepath = "C:\\Users\\asus\\OneDrive\\Dokumen\\Semester 3\\OOP\\OOP 01\\JBus\\data\\station.json";
//        Gson gson = new Gson();
//
//        try {
//            BufferedReader bufferedReader = new BufferedReader(new FileReader(filepath));
//            List<Station> stationjson = gson.fromJson(bufferedReader, new TypeToken<List<Station>>() {}.getType());
//            stationjson.forEach(e -> System.out.println(e.toString()));
//            System.out.println();
//            bufferedReader.close();
//        }
//        catch (IOException e){
//            e.printStackTrace();
//        }
    }
}
//
//import java.sql.Timestamp;
//import java.util.*;
//
//
//public class JBus {
//    public static void main(String[] args) {
//        // PT Modul 5
//        // Tes Pagination
//        Bus b = createBus();
//        List<Timestamp> listOfSchedules = new ArrayList<>();
//        listOfSchedules.add(Timestamp.valueOf("2023-7-18 15:00:00"));
//        listOfSchedules.add(Timestamp.valueOf("2023-7-20 12:00:00"));
//        listOfSchedules.add(Timestamp.valueOf("2023-7-22 10:00:00"));
//        listOfSchedules.add(Timestamp.valueOf("2023-7-26 12:00:00"));
//
//        listOfSchedules.forEach(b::addSchedule);
//        System.out.println("Page 1");
//        Algorithm.paginate(b.schedules, 0, 3, t -> true).forEach(System.out::println);
//        System.out.println("=====================================================");
//        System.out.println("Page 2");
//        Algorithm.paginate(b.schedules, 1, 3, t -> true).forEach(System.out::println);
//        System.out.println("=====================================================");
//
//        // Tes Booking
//        String msgSuccess = "Booking Success!";
//        String msgFailed = "Booking Failed";
//        // valid date, invalid seat = Booking Failed
//        Timestamp t1 = Timestamp.valueOf("2023-7-19 15:00:00");
//        System.out.println("\nMake booking at July 19, 2023 15:00:00 Seats: S17 S18");
//        System.out.println(Payment.makeBooking(t1, List.of("IO17", "IO18"), b)? msgSuccess : msgFailed);
//        // valid date, invalid seat = Booking Failed
//        Timestamp t2 = Timestamp.valueOf("2023-7-18 15:00:00");
//        System.out.println("Make booking at July 18, 2023 15:00:00 Seat S26");
//        System.out.println(Payment.makeBooking(t2, "IO26", b)? msgSuccess : msgFailed);
//        // valid date, valid seat = Booking Success
//        System.out.println("Make booking at July 18, 2023 15:00:00 Seats: S7 S8");
//        System.out.println(Payment.makeBooking(t2, List.of("IO07", "IO08"), b)? msgSuccess : msgFailed);
//        // valid date, valid seat = Booking Success
//        Timestamp t3 = Timestamp.valueOf("2023-7-20 12:00:00");
//        System.out.println("Make booking at July 20, 2023 12:00:00 Seats: S1 S2");
//        System.out.println(Payment.makeBooking(t3, List.of("IO01", "IO02"), b)? msgSuccess : msgFailed);
//        // valid date, book the same seat = Booking Failed
//        System.out.println("Make booking at July 20, 2023 12:00:00 Seat S1");
//        System.out.println(Payment.makeBooking(t3, "IO01", b)? msgSuccess : msgFailed);
//        // check if the data changed
//        System.out.println("\nUpdated Schedule");
//        Algorithm.paginate(b.schedules, 0, 4, t-> true).forEach(System.out::println);
//    }
//
//    public static Bus createBus() {
//        Price price = new Price(750000, 5);
//        Bus bus = new Bus("Netlab Bus", Facility.LUNCH, price, 25, BusType.REGULER, City.BANDUNG, new Station("Depok Terminal", City.DEPOK, "Jl. Margonda Raya"), new Station("Halte UI", City.JAKARTA, "Universitas Indonesia"));
//        return bus;
//    }
//}

/*public class JBus {

   public static void main(String[] args) {
        Integer[] numbers = {18, 10, 22, 43, 18, 67, 12, 11, 88, 22, 18};
        System.out.println("Number "+Arrays.toString(numbers));

        // Tes Algorithm
        System.out.print("1. ");
        testCount(numbers);
        System.out.print("2. ");
        testFind(numbers);
        System.out.print("3. ");
        testExist(numbers);
        System.out.println("4. Filtering");
        testCollect(numbers);
    }*/
 /*   private static int id;


    public Bus createBus() {
        Price price = new Price(750000, 5);
        Bus bus = new Bus(id, "Netlab Bus", Facility.LUNCH, price, 20, BusType.REGULER, City.BANDUNG, new Station(id, "Depok Terminal", City.DEPOK, "Jl. Margonda Raya"), new Station(2, "Halte UI", City.JAKARTA, "Universitas Indonesia"));
        id++;
        return bus;
    }

    public static void main(String[] args) {
            Integer[] numbers = {18, 10, 22, 43, 18, 67, 12, 11, 88, 22, 18};
            System.out.println("Number "+Arrays.toString(numbers));

            // Tes Algorithm
            System.out.print("1. ");
            testCount(numbers);
            System.out.print("2. ");
            testFind(numbers);
            System.out.print("3. ");
            testExist(numbers);
            System.out.println("4. Filtering");
            testCollect(numbers);
        }
        private static void testExist(Integer[] t) {
            int valueToCheck = 67;
            boolean result3 = Algorithm.exists(t, valueToCheck);
            if (result3) {
                System.out.println(valueToCheck + " exist in the array.");
            } else {
                System.out.println(valueToCheck + " doesn't exists in the array.");
            }
        }
        public static void testCount(Integer[] t) {
            int valueToCount = 18;
            int result = Algorithm.count(t, valueToCount);
            System.out.println("Number " + valueToCount + " appears " + result + " times");
        }
        public static void testFind(Integer[] t) {
            Integer valueToFind = 69;
            Integer result2 = Algorithm.find(t, valueToFind);
            System.out.print("Finding " + valueToFind + " inside the array : ");
            if (result2 != null) {
                System.out.println("Found!" + result2);
            } else {
                System.out.println("Not Found");
            }
        }
        private static void testCollect(Integer[] t) {
            Predicate<Integer> below = (val)->val<=22;
            Predicate<Integer> above = (val)->val>43;

            List<Integer> integerBelow = Algorithm.collect(t, below);
            List<Integer> integerAbove = Algorithm.collect(t, above);

            System.out.println("Below 22");
            System.out.println(integerBelow);
            System.out.println("Above 43");
            System.out.println(integerAbove);



    }
}*/



//        System.out.println("Halo From IntellijID");
//        Bus b = createBus();
//        Timestamp schedule1 = Timestamp.valueOf("2023-7-18 15:00:00");
//        Timestamp schedule2 = Timestamp.valueOf("2023-7-20 12:00:00");
//
//        b.addSchedule(schedule1);
//        b.addSchedule(schedule2);
//
//        b.schedules.forEach(Schedule::printSchedule);
//
//        for (Schedule s: b.schedules) {
//               s.printSchedule();
//        }
//
//        //Invalid date
//        Timestamp t1 = Timestamp.valueOf("2023-7-19 15:00:00");
//        System.out.println("Make booking at July 19, 2023 15:00:00 Seat ER12");
//        System.out.println(Payment.makeBooking(t1, "AF12", b));
//
//        //Valid date, invalid seat
//        Timestamp t2 = Timestamp.valueOf("2023-7-18 15:00:00");
//        System.out.println("\nMake booking at July 18, 2023 15:00:00 Seat ER20");
//        System.out.println(Payment.makeBooking(t2, "AF20", b));
//
//        //Valid date, valid seat
//        System.out.println("\nMake booking at July 18, 2023 15:00:00 Seat ER07");
//        System.out.println(Payment.makeBooking(t2, "AF07", b));
//
//        Timestamp t3 = Timestamp.valueOf("2023-7-20 12:00:00");
//        System.out.println("\nMake booking at July 20, 2023 12:00:00 Seat ER01");
//        System.out.println(Payment.makeBooking(t3, "AF01", b));
//
//        System.out.println("\nMake booking at July 20, 2023 12:00:00 Seat ER01 again");
//        System.out.println(Payment.makeBooking(t3, "AF01", b));
//
//        //Check if the data changes
//        System.out.println("\nUpdated Schedule\n");
//        b.schedules.forEach(Schedule::printSchedule);
