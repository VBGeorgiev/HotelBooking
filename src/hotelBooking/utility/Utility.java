package hotelBooking.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utility {
    public static boolean isValid(String password) {
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,20}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(password);
        return m.matches();
    }

}
