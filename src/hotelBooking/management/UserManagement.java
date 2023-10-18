package hotelBooking.management;

import hotelBooking.domain.User;
import hotelBooking.utility.Utility;

import java.util.HashMap;
import java.util.Scanner;

public class UserManagement {
    private HashMap<String, User> userList;
    public UserManagement() {
        this.userList = new HashMap<>();
    }

    public boolean registerUser(Scanner sc) {
        System.out.println("Please enter username: ");
        String username = sc.nextLine();
        if(userList.containsKey(username)) {
            System.out.println("Username already exist, please try again");
            return false;
        }

        System.out.println("Please enter your first name: ");
        String firstName = sc.nextLine();
        System.out.println("Please enter your last name: ");
        String lastName = sc.nextLine();
        System.out.println("Please enter your password: ");
        String password = sc.nextLine();
        if(!Utility.isValid(password)) {
            System.out.println("Password must consist of at least one digit, one lower and one capital alphabet, and have from 8 to 20 symbols");
            return false;
        }

        User user = new User(username, firstName, lastName, password);
        userList.put(username, user);
        return true;
    }

    public void userPreview(String username) {
        if(userList.containsKey(username)) {
            System.out.println("username: " + userList.get(username).getUsername());
            System.out.println("first name: " + userList.get(username).getFirstName());
            System.out.println("last name: " + userList.get(username).getLastName());
            System.out.println("previous bookings: " + userList.get(username).getPreviousBookings().toString());
//                    .forEach(e -> System.out.println());
        } else {
            System.out.println("Username not found");
        }
    }

}
