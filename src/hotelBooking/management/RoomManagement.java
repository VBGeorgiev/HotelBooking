package hotelBooking.management;

import hotelBooking.domain.Room;
import hotelBooking.utility.Constant;

import java.text.ParsePosition;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class RoomManagement {
    //    HashMap<roomReference, Room>
    private HashMap<String, Room> roomList;

    public RoomManagement() {
        this.roomList = new HashMap<>();
    }

    public RoomManagement(HashMap<String, Room> roomList) {
        this.roomList = roomList;
    }

    public HashMap<String, Room> getRoomList() {
        return roomList;
    }

    public void setRoomList(HashMap<String, Room> roomList) {
        this.roomList = roomList;
    }

    public boolean addRoom(Scanner sc) {
        System.out.println("Please enter room number: ");
        int roomNumber = Integer.parseInt(sc.nextLine());
        System.out.println("Please enter room type: ");
        String roomType = sc.nextLine();
        if (this.roomList.containsKey(roomType + roomNumber)) {
            System.out.println("This room reference, " + roomType + roomNumber + " already exists");
            return false;
        }

        System.out.println("Please enter price per night: ");
        double pricePerNight = Double.parseDouble(sc.nextLine());
        System.out.println("Please enter cancellation fee: ");
        double cancellationFee = Double.parseDouble(sc.nextLine());


        Room room = new Room(roomNumber, roomType, pricePerNight, cancellationFee);
        this.roomList.put(room.getRoomReference(), room);
        System.out.println("Room addition successful");
        return true;
    }

    public boolean bookRoom(Scanner sc) {
//        System.out.println("Please enter room reference (e.g. Single 101):");
//        String roomReference = sc.nextLine();
//        if (!roomExist(roomReference)) {
//            System.out.println("Wrong room reference");
//            return false;
//        }

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
                this.setRoomBooked(roomRef, numDays, firstDay);
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

//        if (this.isRoomAvailable(roomReference, numDays, firstDay)) {
//            this.setRoomBooked(roomReference, numDays, firstDay);
//        }
//
//        System.out.println("Room booking successful");
//        return true;

    }

    public void view(Scanner sc) {
        System.out.println("Enter room reference (e.g. Single 101): ");
        String roomReference = sc.nextLine();
        if (roomExist(roomReference)) {
            this.roomList.get(roomReference).view();
        } else {
            System.out.println("Wrong room reference");
        }

    }

    private boolean roomExist(String roomReference) {
        if (this.roomList.containsKey(roomReference)) {
            return true;
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
                if (this.roomList.get(roomReference).isBooked(tempDay)) {
                    return false;
                }

            }

        }

        return true;
    }

    public void setRoomBooked(String roomReference, long numDays, Date firstDay) {
        for (long i = 0; i < numDays; i++) {
            Date tempDay = Date.from(firstDay.toInstant().plus(i, ChronoUnit.DAYS));
            this.roomList.get(roomReference).addBooking(tempDay);
        }

    }

    public ArrayList<String> searchAvailableRooms(long numDays, Date firstDay) {
        ArrayList<String> availableRooms = new ArrayList<>();

        for (String roomRef:this.roomList.keySet()) {
            if(this.isRoomAvailable(roomRef, numDays, firstDay)){
                availableRooms.add(roomRef);
            }
        }

        return availableRooms;
    }

}
