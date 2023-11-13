package com.farhanNuzulNJBusAF.controller;

import com.farhanNuzulNJBusAF.Account;
import com.farhanNuzulNJBusAF.BusType;
import com.farhanNuzulNJBusAF.Facility;
import com.farhanNuzulNJBusAF.Payment;
import com.farhanNuzulNJBusAF.dbjson.JsonAutowired;
import com.farhanNuzulNJBusAF.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController /*implements BasicGetController*/{
    public static @JsonAutowired(value = Account.class, filepath = "C:\\Users\\asus\\OneDrive\\Dokumen\\Semester 3\\OOP\\OOP 01\\JBus\\src\\main\\java\\com\\farhanNuzulNJBusAF\\json\\account.json") JsonTable<Account> paymentTable;

    @RequestMapping(value="/makeBooking", method=RequestMethod.POST)
    public BaseResponse<Payment> makeBooking(
    @RequestParam int buyerId,
    @RequestParam int renterId,
    @RequestParam int busId,
    @RequestParam List<String> busSeats,
    @RequestParam String departure
    ){
        return null;
    }

    @RequestMapping(value="/{id}/accept", method=RequestMethod.POST)
    public BaseResponse<Payment> accept(@PathVariable int id){
        return null;
    }
    @RequestMapping(value="/{id}/cancel", method=RequestMethod.POST)
    public BaseResponse<Payment> cancel(@PathVariable int id){
        return null;
    }

}
