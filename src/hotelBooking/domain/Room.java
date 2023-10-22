package hotelBooking.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Room implements Serializable {
    private int number;
    private String type;
    private double pricePerNight;
    private double cancellationFee;
    private String roomReference;
    private ArrayList<Date> roomBooked;
    public Room(int number, String type, double pricePerNight, double cancellationFee) {
        this.number = number;
        this.type = type;
        this.pricePerNight = pricePerNight;
        this.cancellationFee = cancellationFee;
        this.roomBooked = new ArrayList<>();
        this.roomReference = this.type + " " + this.number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public double getCancellationFee() {
        return cancellationFee;
    }

    public void setCancellationFee(double cancellationFee) {
        this.cancellationFee = cancellationFee;
    }

    public String getRoomReference() {
        return roomReference;
    }

    public void addBooking(Date date) {
        this.roomBooked.add(date);
    }
    public void cancelBooking(Date date) {
        this.roomBooked.remove(date);
    }

    public boolean isBooked(Date date) {
        if(this.roomBooked.contains(date)) {
            return true;
        } else {
            return false;
        }

    }

    public void view() {
        System.out.println("Room reference: " + this.roomReference);
        System.out.println("Room number: " + this.number);
        System.out.println("Room type: " + this.type);
        System.out.println("Price per night: " + this.pricePerNight);
        System.out.println("Cancellation fee: " + this.cancellationFee);
        System.out.println("Room booked on: " + this.roomBooked.toString());
    }

}
