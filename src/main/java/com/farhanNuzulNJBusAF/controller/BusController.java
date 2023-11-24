package com.farhanNuzulNJBusAF.controller;

import com.farhanNuzulNJBusAF.*;
import com.farhanNuzulNJBusAF.dbjson.JsonAutowired;
import com.farhanNuzulNJBusAF.dbjson.JsonTable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/bus")
public class BusController implements BasicGetController<Bus> {
    public static @JsonAutowired(value = Account.class, filepath = "src\\main\\java\\com\\farhanNuzulNJBusAF\\json\\bus_db.json") JsonTable<Bus> busTable;

    public BusController() {}

    public JsonTable<Bus> getJsonTable() {
        return this.busTable;
    }

    @PostMapping("/create")
    public BaseResponse<Bus> create(
            @RequestParam int accountId,
            @RequestParam String name,
            @RequestParam int capacity,
            @RequestParam List<Facility> facilities,
            @RequestParam BusType busType,
            @RequestParam int price,
            @RequestParam int stationDepartureId,
            @RequestParam int stationArrivalId
    ) {
        Predicate<Account> predAcc = a -> a.id == accountId && a.company != null;
        boolean valid1 = Algorithm.exists(AccountController.accountTable, predAcc);
        Predicate<Station> predStation = s -> s.id == stationDepartureId || s.id == stationArrivalId;
        boolean valid2 = Algorithm.exists(StationController.stationTable, predStation);

        if(valid1 && valid2) {
            Predicate<Station> DepStation = s -> s.id == stationDepartureId;
            Predicate<Station> ArrStation = s -> s.id == stationArrivalId;
            Bus bus = new Bus(name, facilities, new Price(price), capacity, busType, Algorithm.find(StationController.stationTable, DepStation), Algorithm.find(StationController.stationTable, ArrStation), accountId);
            busTable.add(bus);
            return new BaseResponse<>(true, "Berhasil menambahkan bus", bus);
        } else {
            return new BaseResponse<>(false, "Gagal menambahkan bus", null);
        }
    }

    @PostMapping("/addSchedule")
    public BaseResponse<Bus> addSchedule(
            @RequestParam int busId,
            @RequestParam String time
    ) {
        Predicate<Bus> pred = b -> b.id == busId;
        Bus b = Algorithm.find(getJsonTable(), pred);
        if(b != null) {
            Timestamp timestamp = Timestamp.valueOf(time);
            b.addSchedule(timestamp);
            return new BaseResponse<>(true, "Berhasil membuat schedule", b);
        }
        return new BaseResponse<>(false, "Gagal membuat schedule", null);
    }
}

//package com.farhanNuzulNJBusAF.controller;
//
//import com.farhanNuzulNJBusAF.*;
//import com.farhanNuzulNJBusAF.controller.BaseResponse;
//import com.farhanNuzulNJBusAF.controller.BasicGetController;
//import com.farhanNuzulNJBusAF.dbjson.JsonAutowired;
//import com.farhanNuzulNJBusAF.dbjson.JsonTable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import com.farhanNuzulNJBusAF.Algorithm;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/bus")
//public class BusController /*implements BasicGetController*/ {
//    public static @JsonAutowired(value = Account.class, filepath = "C:\\Users\\asus\\OneDrive\\Dokumen\\Semester 3\\OOP\\OOP 01\\JBus\\src\\main\\java\\com\\farhanNuzulNJBusAF\\json\\station.json") JsonTable<Account> busTable;
//
//    @PostMapping("/create")
//    public BaseResponse<Bus> create (
//        @RequestParam int accountId,
//        @RequestParam String name,
//        @RequestParam int capacity,
//        @RequestParam List<Facility> facilities,
//        @RequestParam BusType busType,
//        @RequestParam int pricee,
//        @RequestParam int statioDepartureId,
//        @RequestParam int statioArrivalId
//
//    ) {
////        Station departureStation = null;
////        Station arrivalStation = null;
////        Price priceResult = null;
////
////        Predicate<Account> predAcc = a -> a.id == accountId && a.company != null;
////        boolean valid1 = Algorithm.exists(AccountController.accountTable, predAcc);
////        Predicate<Bus> predBus = b -> b.departure == departureStation && b.arrival == arrivalStation;
////        boolean valid2 = Algorithm.exists(BusController.busTable, predBus);
////
////        if (valid1 && valid2) {
////            Bus bus = new Bus(name, facilities, priceResult, capacity, busType, departureStation, arrivalStation, accountId);
////           busTable.add(bus);
////            return new BaseResponse<>(true, "Dapat menambahkan bus", bus);
////        } else {
////            return new BaseResponse<>(false, "Gagal menambahkan bus", null);
////        }
////    }
//    return null;
//    }
//
//    public BaseResponse<Bus> addSchedule(
//            @RequestParam int busId,
//            @RequestParam String time
//    ) {
////        Bus bus = busTable.getById(busId);
////
////        if (bus != null) {
////            try {
//////                bus.addSchedule(time);
////                return new BaseResponse<>(true, "Berhasil menambahkan schedule", bus);
////            } catch (Exception e) {
////                return new BaseResponse<>(false, "Gagal menambahkan schedule: " + e.getMessage(), null);
////            }
////        } else {
////            return new BaseResponse<>(false, "Bus tidak ditemukan", null);
////        }
//        return null;
//    }
//
//}




