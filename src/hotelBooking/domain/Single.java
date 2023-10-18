package hotelBooking.domain;

public class Single extends Room{
    private int number;
    private String type;
    private double pricePerNight;
    private double cancellationFee;
    private String status;

    private boolean sharedBath;

    public Single(int number, String type, double pricePerNight, double cancellationFee, String status, boolean sharedBath) {
        super(number, type, pricePerNight, cancellationFee, status);
        this.sharedBath = sharedBath;
    }

    public boolean isSharedBath() {
        return this.sharedBath;
    }

    public void setSharedBath(boolean sharedBath) {
        this.sharedBath = sharedBath;
    }

    @Override
    public void view() {
        super.view();
        if (this.sharedBath) {
            System.out.println("This room is with a shared bathroom facilities");
        } else {
            System.out.println("This room has its own bathroom facilities");
        }

    }

}
