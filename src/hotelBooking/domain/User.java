package hotelBooking.domain;

import hotelBooking.utility.Utility;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable{
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private boolean logIn;
    private ArrayList<String> previousBookings;

    public User(String username, String firstName, String lastName, String password) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.logIn = true;
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
        this.password = password;
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

    public boolean isLogIn() {
        return logIn;
    }

    public void setLogIn(boolean logIn) {
        this.logIn = logIn;
    }

    public void view() {
        System.out.println("username: " + this.getUsername());
        System.out.println("first name: " + this.getFirstName());
        System.out.println("last name: " + this.getLastName());
        System.out.println("User logged: " + this.isLogIn());
        this.getPreviousBookings()
                .stream()
                .forEach(booking -> System.out.println(booking));

    }
}
