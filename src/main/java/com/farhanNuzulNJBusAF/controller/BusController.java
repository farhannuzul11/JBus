package com.farhanNuzulNJBusAF.controller;

import com.farhanNuzulNJBusAF.*;
import com.farhanNuzulNJBusAF.dbjson.JsonAutowired;
import com.farhanNuzulNJBusAF.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

/**
 * Controller class for managing bus-related operations.
 *
 * <p>
 * This class handles the creation of buses, addition of schedules, and retrieval of buses based on
 * account ID. It uses a JSON-based table to store bus information.
 * </p>
 *
 * @author Farhan Nuzul
 * @version 1.0
 */
@RestController
@RequestMapping("/bus")
public class BusController implements BasicGetController<Bus> {

    /**
     * The JSON table to store bus information.
     */
    public static @JsonAutowired(value = Bus.class, filepath = "src\\main\\java\\com\\farhanNuzulNJBusAF\\json\\bus.json") JsonTable<Bus> busTable;

    /**
     * Constructs a new BusController.
     */
    public BusController() {}

    /**
     * Gets the JSON table instance for buses.
     *
     * @return The JSON table instance for buses.
     */
    public JsonTable<Bus> getJsonTable() {
        return this.busTable;
    }

    /**
     * Creates a new bus with the specified parameters.
     *
     * @param accountId The ID of the account associated with the bus.
     * @param name The name of the bus.
     * @param capacity The capacity of the bus.
     * @param facilities The list of facilities available on the bus.
     * @param busType The type of the bus.
     * @param price The price of the bus.
     * @param stationDepartureId The ID of the departure station.
     * @param stationArrivalId The ID of the arrival station.
     * @return A BaseResponse containing the result of the creation and the created bus.
     */
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

    /**
     * Adds a schedule to an existing bus.
     *
     * @param busId The ID of the bus to which the schedule will be added.
     * @param time The time of the schedule.
     * @return A BaseResponse containing the result of the schedule addition and the updated bus.
     */
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

    /**
     * Retrieves a list of buses associated with a specific account.
     *
     * @param accountId The ID of the account.
     * @return A list of buses associated with the specified account.
     */
    @GetMapping("/getBus")
    public List<Bus> getBus(@RequestParam int accountId) {
        return Algorithm.<Bus>collect(getJsonTable(), b->b.accountId==accountId);
    }
}




