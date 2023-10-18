package hotelBooking.domain;

public class Deluxe extends Room{
    private int number;
    private String type;
    private double pricePerNight;
    private double cancellationFee;
    private String status;
    private boolean fridge;
    private boolean jacuzzi;


    public Deluxe(int number, String type, double pricePerNight, double cancellationFee, String status, boolean fridge, boolean jacuzzi) {
        super(number, type, pricePerNight, cancellationFee, status);
        this.fridge = fridge;
        this.jacuzzi = jacuzzi;
    }

    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public double getPricePerNight() {
        return pricePerNight;
    }

    @Override
    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    @Override
    public double getCancellationFee() {
        return cancellationFee;
    }

    @Override
    public void setCancellationFee(double cancellationFee) {
        this.cancellationFee = cancellationFee;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isFridge() {
        return fridge;
    }

    public void setFridge(boolean fridge) {
        this.fridge = fridge;
    }

    public boolean isJacuzzi() {
        return jacuzzi;
    }

    public void setJacuzzi(boolean jacuzzi) {
        this.jacuzzi = jacuzzi;
    }

    @Override
    public void view() {
        super.view();
        if (this.jacuzzi) {
            System.out.println("This room has jacuzzi");
        } else {
            System.out.println("This room has not got jacuzzi");
        }

        if (this.fridge) {
            System.out.println("This room has fridge");
        } else {
            System.out.println("This room has not got fridge");
        }
    }
}
