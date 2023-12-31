package hotelBooking.management;

import hotelBooking.database.Database;
import hotelBooking.domain.Room;
import hotelBooking.domain.User;
import hotelBooking.utility.Constant;

import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class OperationManagement {
    public static void startProgram() {
        Database userDatabase = new Database("C:\\Users\\Georgiev\\IdeaProjects\\HotelBooking\\src\\resource\\userList.txt");
        Database roomDatabase = new Database("C:\\Users\\Georgiev\\IdeaProjects\\HotelBooking\\src\\resource\\roomList.txt");
        Database bookingDatabase = new Database("C:\\Users\\Georgiev\\IdeaProjects\\HotelBooking\\src\\resource\\bookingList.txt");
//        initialBooking(bookingDatabase);
        //        initialUser(userDatabase);
//        initialRoom(roomDatabase);
        HashMap<String, User> userList = (HashMap<String, User>) userDatabase.readObject();
        UserManagement userManagement = new UserManagement();
        userManagement.setUserList(userList);
        HashMap<String, Room> roomList = (HashMap<String, Room>) roomDatabase.readObject();
        RoomManagement roomManagement = new RoomManagement();
        roomManagement.setRoomList(roomList);
        BookingManagement bookingManagement = new BookingManagement(userManagement, roomManagement);
        bookingManagement.setBookingList((HashMap<String, ArrayList<Date>>) bookingDatabase.readObject());
        AdminManagement adminManagement = new AdminManagement(userDatabase, userManagement, roomDatabase, roomManagement, bookingDatabase, bookingManagement);
        Scanner sc = new Scanner(System.in);
        ArrayList<String> menu = new ArrayList<>();
        menu.add("Register user: select 1");
        menu.add("Log in user: select 2");
        menu.add("Log out user: select 3");
        menu.add("View user profile: select 4");
        menu.add("View a room: select 5");
        menu.add("Book a room: select 6");
        menu.add("View all rooms: select 7");
        menu.add("Admin services: select 8");
        menu.add("Exit: select 0");
        int userChoice = 99;
        while(userChoice != 0 ) {
            System.out.println("=========================================");
            menu.forEach(System.out::println);
            System.out.println("=========================================");
            try {
                userChoice = Integer.parseInt(sc.nextLine());
                switch (userChoice) {
                    case 1:
                        if(userManagement.registerUser(sc)) {
                            userDatabase.saveObject(userManagement.getUserList());
                        }
                        break;
                    case 2:
                        userManagement.logIn(sc);
                        break;
                    case 3:
                        userManagement.logOut(sc);
                        break;
                    case 4:
                        userManagement.view(sc);
                        break;
                    case 5:
                        roomManagement.view(sc);
                        break;
                    case 6:
                        if(bookingManagement.bookRoom(sc)) {
                            userDatabase.saveObject(userManagement.getUserList());
                            roomDatabase.saveObject(roomManagement.getRoomList());
                            bookingDatabase.saveObject(bookingManagement.getBookingList());
                        };
                        break;
                    case 7:
                        roomManagement.viewAll();
                        break;
                    case 8:
                        adminManagement.Operation(sc);
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

    public static void initialUser(Database userDatabase) {
        User admin = new User("admin", "admin", "admin", "aaA1ddD2mmM3iiI4nnN5");
        HashMap<String, User> userList = new HashMap<>();
        userList.put("admin", admin);
        userDatabase.saveObject(userList);
    }

    public static void initialRoom(Database roomDatabase) {
        Room room = new Room(101, "Single", 32.00, 10.00);
        HashMap<String, Room> roomList = new HashMap<>();
        roomList.put(room.getRoomReference(), room);
        roomDatabase.saveObject(roomList);
    }

    public static void initialBooking(Database bookingDatabase) {
        ParsePosition pos = new ParsePosition(0);
        Date date = Constant.dayFormat.parse("01/01/2022", pos);
        ArrayList<Date> dateList = new ArrayList<>();
        dateList.add(date);
        HashMap<String, ArrayList<Date>> bookingList = new HashMap<>();
        bookingList.put("admin_initial", dateList);
        bookingDatabase.saveObject(bookingList);
    }
}
