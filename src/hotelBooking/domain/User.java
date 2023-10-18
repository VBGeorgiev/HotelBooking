package hotelBooking.domain;

import hotelBooking.utility.Utility;

import java.util.ArrayList;

public class User {
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private ArrayList<String> previousBookings;

    public User(String username, String firstName, String lastName, String password) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        if(Utility.isValid(password)) {
            this.password = password;
        } else {
            System.out.println("Password must consist of at least one digit, one lower and one capital alphabet, and have from 8 to 20 symbols");
        }
        this.previousBookings = new ArrayList<>();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if(Utility.isValid(password)) {
            this.password = password;
        } else {
            System.out.println("Password must consist of at least one digit, one lower and one capital alphabet, and have from 8 to 20 symbols");
        }

    }

    public ArrayList<String> getPreviousBookings() {

        return this.previousBookings;
    }

    public void addBooking(String bookingReference) {
        this.previousBookings.add(bookingReference);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
