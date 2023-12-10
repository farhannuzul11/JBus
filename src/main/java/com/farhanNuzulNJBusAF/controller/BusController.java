package com.farhanNuzulNJBusAF.controller;

import com.farhanNuzulNJBusAF.*;
import com.farhanNuzulNJBusAF.dbjson.JsonAutowired;
import com.farhanNuzulNJBusAF.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/bus")
public class BusController implements BasicGetController<Bus> {
    public static @JsonAutowired(value = Bus.class, filepath = "src\\main\\java\\com\\farhanNuzulNJBusAF\\json\\bus.json") JsonTable<Bus> busTable;

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

    @GetMapping("/getBus")
    public List<Bus> getBus(@RequestParam int accountId) {
        return Algorithm.<Bus>collect(getJsonTable(), b->b.accountId==accountId);
    }
}




