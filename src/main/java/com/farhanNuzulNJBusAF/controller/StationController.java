package com.farhanNuzulNJBusAF.controller;

import com.farhanNuzulNJBusAF.Bus;
import com.farhanNuzulNJBusAF.City;
import com.farhanNuzulNJBusAF.Station;
import com.farhanNuzulNJBusAF.dbjson.JsonAutowired;
import com.farhanNuzulNJBusAF.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/station")
public class StationController implements BasicGetController<Station> {
    public static @JsonAutowired(value = Station.class, filepath = "C:\\Users\\asus\\OneDrive\\Dokumen\\Semester 3\\OOP\\OOP 01\\JBus\\src\\main\\java\\com\\farhanNuzulNJBusAF\\json\\station.json") JsonTable<Station> stationTable; //account?
    @Override
    public JsonTable<Station> getJsonTable() {
        return stationTable;
    }

    //Add new Station
    @PostMapping("/create")
    public BaseResponse<Station> createStation(
            @RequestParam String stationName,
            @RequestParam String city,
            @RequestParam String address
    ) {
        try {
            // Validate parameters
            if (stationName.isBlank() || city.isBlank() || address.isBlank()) {
                return new BaseResponse<>(false, "Parameter values cannot be blank or null", null);
            }

            // Validate city as a valid enum value
            City cityEnum = City.valueOf(city.toUpperCase());

            // Create a new station using the provided details
            Station newStation = new Station(stationName, cityEnum, address);

            // Add the new station to the stationTable
            stationTable.add(newStation);

            //Success response message
            return new BaseResponse<>(true, "Station added successfully", newStation);
        } catch (IllegalArgumentException e) {
            // Handle invalid enum value
            return new BaseResponse<>(false, "Invalid city value", null);
        } catch (Exception e) {
            // Handle other unexpected errors
            return new BaseResponse<>(false, "An error occurred while adding the station", null);
        }
    }

}