package com.farhanNuzulNJBusAF;

import java.sql.Timestamp;
public class BookingThread extends Thread{

    private Bus bus;
    private Timestamp timestamp;
    public BookingThread(String name, Bus bus, Timestamp timestamp){
        super(name);
        this.bus = bus;
        this.timestamp = timestamp;
        this.start();
    }
    public void run() {
        System.out.println("Thread " + Thread.currentThread().getId() + " ID: " + Thread.currentThread().getName() + " is running");
        synchronized (bus) {
                String seatToBook = "AF01";
                boolean booked = Payment.makeBooking(timestamp, seatToBook, bus);
                if (booked) {
                    System.out.println("Thread ID: " + getId() + " Thread Name: " + getName() + " booked seat: " + seatToBook);
                } else {
                    System.out.println("Thread ID: " + getId() + " Thread Name: " + getName() + " failed to book seat: " + seatToBook);
                }
//            System.out.println("Thread " + Thread.currentThread().getId() + " ID: " + Thread.currentThread().getName() + " is running"
//            );
//            Payment.makeBooking(this.timestamp, "AF01", this.bus);
        }
    }
}
