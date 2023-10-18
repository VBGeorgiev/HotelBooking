package hotelBooking.management;

import hotelBooking.domain.Room;

import java.util.HashMap;

public class RoomManagement {
//    <String "type" + " " + "number", Room room>
    private HashMap<String, Room> roomList;
    public RoomManagement() {
        this.roomList = new HashMap<>();
    }

    public void viewRoom(String roomReference) {
        if(roomList.containsKey(roomReference)) {
            roomList.get(roomReference).view();
        } else {
            System.out.println("Username not found");
        }
    }
}
