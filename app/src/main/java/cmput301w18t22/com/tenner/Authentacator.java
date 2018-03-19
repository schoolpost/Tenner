package cmput301w18t22.com.tenner;

import android.util.Log;
import android.util.Patterns;

import java.io.Console;
import java.io.File;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Created by Schoolpost on 2018-03-18.
 */

public class Authentacator {

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

    public boolean checkUserExists(String username) {
        return Boolean.FALSE;
    }

    public boolean searchUser(User userToAdd){

        //Gets all users
        ElasticSearchController.SearchUser searchUser = new ElasticSearchController.SearchUser();
        searchUser.execute("");
        ArrayList<User> existingUsers = new ArrayList<User>();

        try {
            existingUsers = searchUser.get();
        } catch (Exception e){
            Log.i("Error", "User Search -> Error in Login Activity!");
        }

        boolean foundMatch = false;

        for(User user : existingUsers) {
            if (user.getEmail().equals(userToAdd.getEmail())) {
                Log.i("Error", "User Registration -> Similar User Found!");
                foundMatch = true;
                break;
            }
        }

        return foundMatch;
    }

}


