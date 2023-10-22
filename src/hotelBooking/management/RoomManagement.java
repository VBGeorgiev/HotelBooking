package hotelBooking.management;

import hotelBooking.domain.Room;
import hotelBooking.domain.User;
import hotelBooking.utility.Utility;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class RoomManagement implements Serializable {
//    HashMap<roomReference, Room>
    private HashMap<String, Room> roomList;
    public RoomManagement() {
        this.roomList = new HashMap<>();
    }

    public RoomManagement(HashMap<String, Room> roomList) {
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

    public void view(Scanner sc) {
        System.out.println("Enter room reference (e.g. Single 101): ");
        String roomReference = sc.nextLine();
        if(this.roomList.containsKey(roomReference)) {
            this.roomList.get(roomReference).view();
        }

    }

}
