package hotelBooking.database;

import java.io.*;

public class Database {
    private String path;

    public Database(String path) {
        this.path = path;
    }

    public void saveObject (Object obj) {
        try(FileOutputStream fos = new FileOutputStream(this.path)) {
            try(ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(obj);
            } catch (IOException e) {
                System.out.println(e);
            }
        } catch (IOException e) {
            System.out.println(e);
        }

    }

    public Object readObject () {
        try(FileInputStream fis = new FileInputStream(this.path)) {
            try(ObjectInputStream ois = new ObjectInputStream(fis)) {
                return ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println(e);
                System.out.println("no object");
                return null;
            }
        } catch (IOException e) {
            System.out.println(e);
            System.out.println("no file");
            return null;
        }

    }

}
