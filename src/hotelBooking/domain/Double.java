package hotelBooking.domain;

public class Double extends Room {
    private int number;
    private String type;
    private double pricePerNight;
    private double cancellationFee;
    private String status;

    private boolean allInclusive;

    public Double(int number, String type, double pricePerNight, double cancellationFee, String status, boolean allInclusive) {
        super(number, type, pricePerNight, cancellationFee, status);
        this.allInclusive = allInclusive;
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

    public boolean isAllInclusive() {
        return this.allInclusive;
    }

    public void setAllInclusive(boolean allInclusive) {
        this.allInclusive = allInclusive;
    }

    @Override
    public void view() {
        super.view();
        if (this.allInclusive) {
            System.out.println("This room is with allInclusive services");
        } else {
            System.out.println("This room is without allInclusive services");
        }

    }
}
