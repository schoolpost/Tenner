package cmput301w18t22.com.tenner;

import android.util.Patterns;

import java.io.File;
import java.util.regex.Pattern;

/**
 * Created by Schoolpost on 2018-03-18.
 */

public class Authentacator {


    public boolean checkEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    public boolean checkPhoneNum(String username) {
        Pattern pattern = Patterns.PHONE;
        return pattern.matcher(username).matches();

    }

    public boolean checkName(String name) {
        String expression = "^[a-zA-Z\\s]+";
        return name.matches(expression);
    }

    public boolean checkUserExists(String username) {
        return Boolean.FALSE;
    }

}


