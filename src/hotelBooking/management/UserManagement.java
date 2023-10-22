package hotelBooking.management;

import hotelBooking.database.Database;
import hotelBooking.domain.User;
import hotelBooking.utility.Utility;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Scanner;

public class UserManagement implements Serializable{
    private HashMap<String, User> userList;
    public UserManagement(HashMap<String, User> userList) {
        this.userList = userList;
    }

    public UserManagement() {
        this.userList = new HashMap<>();
    }

    public boolean registerUser(Scanner sc) {
        String[] username = readUsername(sc);
        if(username[1].equals("Username exist")) {
            System.out.println("Username already exist, please try again");
            System.out.println("Registration not successful");
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
            System.out.println("Registration not successful");
            return false;
        }

        User user = new User(username[0], firstName, lastName, password);
        this.userList.put(username[0], user);
        System.out.println("Registration successful");
        return true;
    }

    public void viewUser(Scanner sc) {
        String[] username = readUsername(sc);
        if(username[1].equals("Username exist")) {
            String password = readPassword(sc, username[0]);
            if (!password.equals("Wrong password")) {
                System.out.println("username: " + this.userList.get(username[0]).getUsername());
                System.out.println("first name: " + this.userList.get(username[0]).getFirstName());
                System.out.println("last name: " + this.userList.get(username[0]).getLastName());
                System.out.println("User logged: " + this.userList.get(username[0]).isLogIn());
                System.out.println("previous bookings: " + this.userList.get(username[0]).getPreviousBookings().toString());
            } else {
                System.out.println("Wrong password");
            }

        } else {
            System.out.println("Username does not exist");
        }

    }

    public void logIn(Scanner sc) {
        String[] username = readUsername(sc);
        if(username[1].equals("Username exist")) {
            String password = readPassword(sc, username[0]);
            if (password.equals("Wrong password")) {
                this.userList.get(username[0]).setLogIn(true);
                System.out.println("Wrong password");
            } else {
                if (this.userList.get(username[0]).isLogIn()) {
                    System.out.println("User has been already logged in");
                } else {
                    this.userList.get(username[0]).setLogIn(true);
                    System.out.println("Successful login");
                }

            }

            } else {
            System.out.println("Username does not exist");
        }

    }

    public HashMap<String, User> getUserList() {
        return this.userList;
    }

    public void setUserList(HashMap<String, User> userList) {
        this.userList = userList;
    }

    private String[] readUsername(Scanner sc) {
        String[] username = new String[2];
        System.out.println("Please enter username: ");
        username[0] = sc.nextLine();
        if(this.userList.containsKey(username[0])) {
            username[1] = "Username exist";
        } else {
            username[1] = "Username not exist";
        }

        return username;
    }

    private String readPassword(Scanner sc, String username) {
        System.out.println("Please enter your password: ");
        String password = sc.nextLine();
        if(this.userList.get(username).getPassword().equals(password)) {
            return password;
        } else {
            return "Wrong password";
        }

    }

//    private void saveToDatabase(Database userDatabase) {
//        userDatabase.saveObject(this.u);
//    }

}
