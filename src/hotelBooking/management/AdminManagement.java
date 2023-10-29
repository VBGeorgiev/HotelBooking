package hotelBooking.management;

import hotelBooking.database.Database;

import java.util.Scanner;

public class AdminManagement {
    private Database userDatabase;
    private UserManagement userManagement;
    private Database roomDatabase;
    private RoomManagement roomManagement;
    private Database bookingDatabase;
    private BookingManagement bookingManagement;

    public AdminManagement(Database userDatabase, UserManagement userManagement, Database roomDatabase, RoomManagement roomManagement, Database bookingDatabase, BookingManagement bookingManagement) {
        this.userDatabase = userDatabase;
        this.userManagement = userManagement;
        this.roomDatabase = roomDatabase;
        this.roomManagement = roomManagement;
        this.bookingDatabase = bookingDatabase;
        this.bookingManagement = bookingManagement;
    }

    public void Operation(Scanner sc) {
        boolean isAdminMode = true;
        int adminService;
        while(isAdminMode) {
            System.out.println("=========================================");
            System.out.println("Admin Services Menu:");
            System.out.println("=========================================");
            System.out.println("Add new room: select 1");
            System.out.println("View booking: select 2");
            System.out.println("Backup database: select 3");
            System.out.println("View user details from user list: select 4");
            System.out.println("Exit: select 0");
            try {
                adminService = Integer.parseInt(sc.nextLine());
                switch (adminService) {
                    case 1:
                        if(this.roomManagement.addRoom(sc)) {
                            this.roomDatabase.saveObject(this.roomManagement.getRoomList());
                        }
                        break;
                    case 2:
                        this.bookingManagement
                                .getBookingList()
                                .keySet().
                                forEach(System.out::println);
                        System.out.println("=========================================");
                        System.out.println("Select booking reference: ");
                        String bookingReference = sc.nextLine();
                        if(this.bookingManagement.getBookingList().containsKey(bookingReference)){
                            this.bookingManagement.view(bookingReference);
                        } else {
                            System.out.println("Wrong booking reference, please try again");
                        }
                        break;
                    case 3:
                        Database userDatabaseBackup = new Database("C:\\Users\\Georgiev\\IdeaProjects\\HotelBooking\\src\\resource\\userListBackup.txt");
                        Database roomDatabaseBackup = new Database("C:\\Users\\Georgiev\\IdeaProjects\\HotelBooking\\src\\resource\\roomListBackup.txt");
                        Database bookingDatabaseBackup = new Database("C:\\Users\\Georgiev\\IdeaProjects\\HotelBooking\\src\\resource\\bookingListBackup.txt");
                        userDatabaseBackup.saveObject(this.userManagement.getUserList());
                        roomDatabaseBackup.saveObject(this.roomManagement.getRoomList());
                        bookingDatabaseBackup.saveObject(this.bookingManagement.getBookingList());
                        System.out.println("Database has been backed up");
                        break;
                    case 4:
                        this.userManagement
                                .getUserList()
                                .keySet().
                                forEach(username -> System.out.println(username));
                        System.out.println("=========================================");
                        System.out.println("Select username: ");
                        String username = sc.nextLine();
                        if(this.userManagement.getUserList().containsKey(username)){
                            this.userManagement.getUserList().get(username).view();
                        } else {
                            System.out.println("Wrong username, please try again");
                        }
                        break;
                    case 0:
                        isAdminMode = false;
                        break;
                    default:
                        System.out.println("Please select a service: ");
                        break;
                }
            } catch(Exception e) {
                System.out.println(e);
                System.out.println("Please select a service: ");
            }

        }

    }

}
