package hotelBooking.management;

import hotelBooking.domain.Room;
import hotelBooking.domain.User;
import hotelBooking.utility.Utility;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
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
        if(this.roomList.containsKey(roomType + roomNumber)) {
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
        System.out.println("Please enter room reference (e.g. Single 101):");
        String roomReference = sc.nextLine();
        if(!isRoomValid(roomReference)) {
            System.out.println("Wrong room reference");
            return false;
        }

        System.out.println("Please enter desire date (e.g. 23/05/2024):");
        SimpleDateFormat dayFormat = new SimpleDateFormat("dd/MM/yyyy");
        ParsePosition parsePosition = new ParsePosition(0);
        Date day = dayFormat.parse(sc.nextLine(), parsePosition);

        System.out.println(dayFormat.format(day));
        return true;

    }

    public void view(Scanner sc) {
        System.out.println("Enter room reference (e.g. Single 101): ");
        String roomReference = sc.nextLine();
        if(isRoomValid(roomReference)) {
            this.roomList.get(roomReference).view();
        } else {
            System.out.println("Wrong room reference");
        }

    }

    private boolean isRoomValid(String roomReference) {
        if(this.roomList.containsKey(roomReference)) {
            return true;
        } else {
            return false;
        }
    }

}
