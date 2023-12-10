package com.farhanNuzulNJBusAF.controller;

import com.farhanNuzulNJBusAF.*;
import com.farhanNuzulNJBusAF.dbjson.JsonTable;
import com.farhanNuzulNJBusAF.dbjson.JsonAutowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

/**
 * Controller class for handling payment-related operations.
 *
 * <p>
 * This class includes methods for making bookings, accepting bookings, and canceling bookings.
 * It utilizes JSON tables for managing payment data.
 * </p>
 */
@RestController
@RequestMapping("/payment")
public class PaymentController implements BasicGetController<Payment> {
    public static @JsonAutowired(value = Payment.class, filepath = "src\\main\\java\\com\\farhanNuzulNJBusAF\\json\\payment.json") JsonTable<Payment> paymentTable;

    public PaymentController() {}

    public JsonTable<Payment> getJsonTable() {
        return this.paymentTable;
    }

    /**
     * Handles the process of making a booking.
     *
     * <p>
     * This method is invoked by the client to make a booking. It checks the buyer's balance,
     * the existence of the buyer, the existence of the bus, and the availability of the bus schedule.
     * If all conditions are met, a new payment is created, and the booking status is set to "WAITING".
     * </p>
     *
     * @param buyerId       The ID of the buyer making the booking.
     * @param renterId      The ID of the renter accepting the booking.
     * @param busId         The ID of the bus being booked.
     * @param busSeats      The list of bus seats being booked.
     * @param departureDate The departure date of the bus schedule.
     * @return A BaseResponse object containing the success status, message, and the created Payment object.
     */
    @RequestMapping(value="/makeBooking", method= RequestMethod.POST)
    public BaseResponse<Payment> makeBooking(
            @RequestParam int buyerId,
            @RequestParam int renterId,
            @RequestParam int busId,
            @RequestParam List<String> busSeats,
            @RequestParam String departureDate
    ) {
        Timestamp timestamp = Timestamp.valueOf(departureDate);
        Predicate<Account> predAccount = a -> a.id == buyerId;
        Predicate<Bus> predBus = b -> b.id == busId;

        Account acc = Algorithm.find(AccountController.accountTable, predAccount);
        Bus bus = Algorithm.find(BusController.busTable, predBus);

        boolean statusSched = false;

        for(int i = 0; i < bus.schedules.size(); i++) {
            if(bus.schedules.get(i).departureSchedule.equals(timestamp)) {
                System.out.println(bus.schedules.get(i).departureSchedule);
                statusSched = true;
            }
        }

        Payment payment = null;
        if(acc != null && bus != null && statusSched) {
            if(acc.balance >= bus.price.price * busSeats.size()) {
                payment = new Payment(buyerId, renterId, busId, busSeats, timestamp);
            } else {
                return new BaseResponse<>(false, "Kekurangan saldo", payment);
            }
        }

        if(payment == null) {
            return new BaseResponse<>(false, "Gagal membuat booking", payment);
        } else {
            payment.makeBooking(timestamp, busSeats, bus);
            payment.status = Invoice.PaymentStatus.WAITING;
            paymentTable.add(payment);
            return new BaseResponse<>(true, "Berhasil membuat booking", payment);
        }
    }

    /**
     * Handles the process of accepting a booking.
     *
     * <p>
     * This method is invoked by the client to accept a booking. It checks the existence of the payment
     * with the specified ID and sets its status to "SUCCESS".
     * </p>
     *
     * @param id The ID of the payment to be accepted.
     * @return A BaseResponse object containing the success status, message, and the updated Payment object.
     */
    @RequestMapping(value="/{id}/accept", method= RequestMethod.POST)
    public BaseResponse<Payment> accept(@PathVariable int id) {
        Predicate<Payment> pred = p -> p.id == id;
        Payment payment = null;
        if(Algorithm.exists(getJsonTable(), pred)) {
            payment = Algorithm.find(getJsonTable(), pred);
            payment.status = Invoice.PaymentStatus.SUCCESS;
            return new BaseResponse<>(true, "Sukses menerima booking", payment);
        }
        return new BaseResponse<>(false, "Gagal menerima booking", payment);
    }

    /**
     * Handles the process of canceling a booking.
     *
     * <p>
     * This method is invoked by the client to cancel a booking. It checks the existence of the payment
     * with the specified ID and sets its status to "FAILED".
     * </p>
     *
     * @param id The ID of the payment to be canceled.
     * @return A BaseResponse object containing the success status, message, and the updated Payment object.
     */
    @RequestMapping(value="/{id}/cancel", method=RequestMethod.POST)
    public BaseResponse<Payment> cancel(@PathVariable int id) {
        Predicate<Payment> pred = p -> p.id == id;
        Payment payment = null;
        if(Algorithm.exists(getJsonTable(), pred)) {
            payment = Algorithm.find(getJsonTable(), pred);
            payment.status = Invoice.PaymentStatus.FAILED;
            return new BaseResponse<>(true, "Sukses meng-cancel booking", payment);
        }
        return new BaseResponse<>(false, "Gagal meng-cancel booking", payment);
    }

}