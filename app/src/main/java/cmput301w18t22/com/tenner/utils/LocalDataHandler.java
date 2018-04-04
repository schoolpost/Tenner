package cmput301w18t22.com.tenner.utils;

import android.app.Activity;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;

import cmput301w18t22.com.tenner.classes.User;
import cmput301w18t22.com.tenner.utils.Constants;

/**
 * Created by Schoolpost on 2018-04-04.
 */

public class LocalDataHandler {

    private Activity activity;

    public LocalDataHandler(Activity activity) {
        this.activity = activity;
    }

    public void saveUserInFile(User user) {
        try {
            FileOutputStream fos = activity.openFileOutput(Constants.FILENAME,
                    Context.MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            gson.toJson(user, out);
            out.flush();

        } catch (FileNotFoundException e) {
            throw new RuntimeException();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public User loadUserFromFile() {

        try {
            FileInputStream fis = activity.openFileInput(Constants.FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));


            Gson gson = new Gson();

            Type fileType = new TypeToken<User>() {
            }.getType();
            User user = gson.fromJson(in, fileType);
            return user;

        } catch (FileNotFoundException e) {
            User user = new User("", "", "", "");
            return user;
        } catch (IOException e) {
            throw new RuntimeException();
        } catch (Exception e) {
            throw new RuntimeException();
        }

    }

}
