package hotelBooking.management;

import hotelBooking.utility.Constant;

import java.text.ParsePosition;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class BookingManagement {
    private UserManagement userManagement;
    private RoomManagement roomManagement;
    private HashMap<String, ArrayList<Date>> bookingList;

    public BookingManagement(UserManagement userManagement, RoomManagement roomManagement) {
        this.userManagement = userManagement;
        this.roomManagement = roomManagement;
    }

    public HashMap<String, ArrayList<Date>> getBookingList() {
        return bookingList;
    }

    public void setBookingList(HashMap<String, ArrayList<Date>> bookingList) {
        this.bookingList = bookingList;
    }
    public boolean bookRoom(Scanner sc) {
        String username = userManagement.logIn(sc);
        if(!username.isEmpty()) {
            Date firstDay = this.parseDate("Please enter start date (e.g. 23/05/2024):", sc);
            Date finalDay = this.parseDate("Please enter final date (e.g. 23/05/2024):", sc);
            long numDays = Duration.between(firstDay.toInstant(), finalDay.toInstant()).toDays();
            ArrayList<String> availableRooms = this.searchAvailableRooms(numDays, firstDay);
            System.out.println("Available rooms: ");
            availableRooms.forEach(roomRef -> System.out.println(roomRef));
            if(!availableRooms.isEmpty()) {
                System.out.println("Please select a room reference: ");
                String roomRef = sc.nextLine();
                if(availableRooms.contains(roomRef)) {
                    this.setBooking(username, roomRef, numDays, firstDay);
                    System.out.println("Room booking successful");
                    return true;
                } else {
                    System.out.println("Selected room reference is incorrect");
                    return false;
                }

            } else {
                System.out.println("Sorry, no rooms available for this period");
                return false;
            }
        } else {
            return false;
        }

    }

    private Date parseDate(String userMessage, Scanner sc) {
        System.out.println(userMessage);
        ParsePosition parsePosition = new ParsePosition(0);
        return Constant.dayFormat.parse(sc.nextLine(), parsePosition);
    }

    public boolean isRoomAvailable(String roomReference, long numDays, Date firstDay) {
        if (numDays > 0) {
            for (long i = 0; i < numDays; i++) {
                Date tempDay = Date.from(firstDay.toInstant().plus(i, ChronoUnit.DAYS));
                if (this.roomManagement.getRoomList().get(roomReference).isBooked(tempDay)) {
                    return false;
                }

            }

        }

        return true;
    }

    public void setBooking(String username, String roomReference, long numDays, Date firstDay) {
        String bookingReference = username + "--->" + roomReference + "--->" + Constant.dayFormat.format(firstDay);
        ArrayList<Date> bookingDates = new ArrayList<>();
        for (long i = 0; i < numDays; i++) {
            Date tempDay = Date.from(firstDay.toInstant().plus(i, ChronoUnit.DAYS));
            this.roomManagement.getRoomList().get(roomReference).addBooking(tempDay);
            bookingDates.add(tempDay);
        }

        this.bookingList.put(bookingReference, bookingDates);
        this.userManagement.getUserList().get(username).addBooking(bookingReference);
    }

    public ArrayList<String> searchAvailableRooms(long numDays, Date firstDay) {
        ArrayList<String> availableRooms = new ArrayList<>();
        for (String roomRef:this.roomManagement.getRoomList().keySet()) {
            if(this.isRoomAvailable(roomRef, numDays, firstDay)){
                availableRooms.add(roomRef);
            }
        }

        return availableRooms;
    }

    public void view(String bookingReference) {
        System.out.println(bookingReference);
        System.out.println("=========================================");
        this.bookingList.
                get(bookingReference)
                .stream()
                .sorted()
                .forEach(date -> System.out.println(Constant.dayFormat.format(date)));
    }

}
