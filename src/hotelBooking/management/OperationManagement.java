package hotelBooking.management;

import hotelBooking.database.Database;
import hotelBooking.domain.User;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class OperationManagement {
    public static void startProgram() {
//        Path root = FileSystems.getDefault().getPath(new String()).toAbsolutePath();
//        System.out.println(root);
        Scanner sc = new Scanner(System.in);
        ArrayList<String> menu = new ArrayList<>();
        menu.add("Review a room: select 1");
        menu.add("Book a room: select 2");
        menu.add("Cancel room reservation: select 3");
        menu.add("Register user: select 4");
        menu.add("View user profile: select 5");
        menu.add("Exit: select 0");
        int userChoice = 99;
        while(userChoice != 0 ) {
            menu.forEach(System.out::println);
            try {
                userChoice = Integer.parseInt(sc.nextLine());
                switch (userChoice) {
                    case 1:
                        System.out.println(1);
                        break;
                    case 2:
                        System.out.println(2);
                        break;
                    case 3:
                        System.out.println(3);
                        break;
                    case 4:
                        System.out.println(4);
                        break;
                    case 5:
                        System.out.println(5);
                        break;
                    case 0:
                        break;
                    default:
                        userChoice = 99;
                        System.out.println("Please try again selecting the menu options only");
                }
            } catch (Exception e) {
                System.out.println(e);
                System.out.println("Please try again selecting the menu options only");
                userChoice = 99;
            }

        }

    }

    public static HashMap<String, User> readUserDatabase () {
        Path root = FileSystems.getDefault().getPath(new String()).toAbsolutePath();
        System.out.println(root);
        Database hotelDatabase = new Database()
        HashMap<String, User> userList = hotelDatabase.readObject();
    }
}
