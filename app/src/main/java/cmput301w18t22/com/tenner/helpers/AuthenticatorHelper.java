package cmput301w18t22.com.tenner.helpers;

import android.util.Patterns;

import java.util.regex.Pattern;

/**
 * Created by schoo on 3/22/2018.
 */

public class AuthenticatorHelper {

    public boolean checkEmail(String email) {
        if (email.length() < 8) {
            return Boolean.FALSE;
        }
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    public boolean checkPhoneNum(String phone) {
        String patternS = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}";
        return phone.matches(patternS);

    }

    public boolean checkName(String name) {
        if (name.length() > 30) {
            return Boolean.FALSE;
        }
        String expression = "^[a-zA-Z\\s]+";
        return name.matches(expression);
    }
}
