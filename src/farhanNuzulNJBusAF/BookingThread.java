package farhanNuzulNJBusAF;

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
        synchronized (bus) {
            System.out.println("Thread " + Thread.currentThread().getId() + " ID: " + Thread.currentThread().getName() + " is running"
            );
            Payment.makeBooking(this.timestamp, "ER01", this.bus);
        }
    }
}
