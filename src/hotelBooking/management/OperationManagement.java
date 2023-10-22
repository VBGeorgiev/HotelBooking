package hotelBooking.management;

import hotelBooking.database.Database;
import hotelBooking.domain.Room;
import hotelBooking.domain.User;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class OperationManagement {
    public static void startProgram() {
        Database userDatabase = new Database("C:\\Users\\Georgiev\\IdeaProjects\\HotelBooking\\src\\resource\\userList.txt");
        Database roomDatabase = new Database("C:\\Users\\Georgiev\\IdeaProjects\\HotelBooking\\src\\resource\\roomList.txt");
//        initialUser(userDatabase);
//        initialRoom(roomDatabase);
        UserManagement userManagement = (UserManagement) userDatabase.readObject();
        RoomManagement roomManagement = (RoomManagement) roomDatabase.readObject();
        Scanner sc = new Scanner(System.in);
        ArrayList<String> menu = new ArrayList<>();
        menu.add("Register user: select 1");
        menu.add("Log in: select 2");
        menu.add("View user profile: select 3");
        menu.add("View a room: select 4");
        menu.add("Book a room: select 5");
        menu.add("Cancel room reservation: select 6");
        menu.add("Admin services: select 7");
        menu.add("Exit: select 0");
        int userChoice = 99;
        while(userChoice != 0 ) {
            menu.forEach(System.out::println);
            try {
                userChoice = Integer.parseInt(sc.nextLine());
                switch (userChoice) {
                    case 1:
                        if(userManagement.registerUser(sc)) {
                            userDatabase.saveObject(userManagement);
                        }
                        break;
                    case 2:
                        userManagement.logIn(sc);
                        break;
                    case 3:
                        userManagement.viewUser(sc);
                        break;
                    case 4:
                        roomManagement.view(sc);
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
                System.out.println("Please try again");
                userChoice = 99;
            }

        }

    }

//    public static HashMap<String, User> readUserDatabase () {
//        Path root = FileSystems.getDefault().getPath(new String()).toAbsolutePath();
//        System.out.println(root);
//        Database hotelDatabase = new Database()
//        HashMap<String, User> userList = hotelDatabase.readObject();
//    }

    public static void initialUser(Database userDatabase) {
        User user = new User("admin", "admin", "admin", "aaA1ddD2mmM3iiI4nnN5");
        HashMap<String, User> userList = new HashMap<>();
        userList.put("admin", user);
        UserManagement userManagement = new UserManagement(userList);
        userDatabase.saveObject(userManagement);
    }

    public static void initialRoom(Database roomDatabase) {
        Room room = new Room(101, "Single", 32.00, 10.00);
        HashMap<String, Room> roomList = new HashMap<>();
        roomList.put(room.getRoomReference(), room);
        RoomManagement roomManagement = new RoomManagement(roomList);
        roomDatabase.saveObject(roomManagement);
    }
}
