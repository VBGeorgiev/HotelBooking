package hotelBooking.domain;

import java.io.Serializable;

public class Room implements Serializable {
    private int number;
    private String type;
    private double pricePerNight;
    private double cancellationFee;
    private String status;

    public Room(int number, String type, double pricePerNight, double cancellationFee) {
        this.number = number;
        this.type = type;
        this.pricePerNight = pricePerNight;
        this.cancellationFee = cancellationFee;
        this.status = "available";
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void view() {
        System.out.println(this.type + " " + this.number);
        System.out.println(this.pricePerNight);
        System.out.println(this.cancellationFee);
    }
}
