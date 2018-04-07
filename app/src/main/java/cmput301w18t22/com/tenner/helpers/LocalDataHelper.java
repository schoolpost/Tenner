package cmput301w18t22.com.tenner.helpers;

import
        android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.google.android.gms.tasks.Tasks;
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
import java.util.ArrayList;
import java.util.Date;

import cmput301w18t22.com.tenner.classes.Location;
import cmput301w18t22.com.tenner.classes.Task;
import cmput301w18t22.com.tenner.classes.User;
import cmput301w18t22.com.tenner.ui.activity.MainActivity;

/**
 * Created by Schoolpost on 2018-04-04.
 */

public class LocalDataHelper {

    private static final LocalDataHelper ourInstance = new LocalDataHelper(new MainActivity());

    public static LocalDataHelper getInstance() {
        return ourInstance;
    }

    private Activity activity;

    public LocalDataHelper(Activity activity) {
        this.activity = activity;
    }

    public void saveUserInFile(User user) {
        try {
            FileOutputStream fos = activity.openFileOutput(ConstantsHelper.USERFILE,
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
            FileInputStream fis = activity.openFileInput(ConstantsHelper.USERFILE);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));


            Gson gson = new Gson();

            Type fileType = new TypeToken<User>() {
            }.getType();
            User user = gson.fromJson(in, fileType);
            return user;

        } catch (FileNotFoundException e) {
            User user = new User("", "", "", "", "");
            return user;
        } catch (IOException e) {
            throw new RuntimeException();
        } catch (Exception e) {
            throw new RuntimeException();
        }

    }

    public void saveRequestedTasksToFile(ArrayList<Task> tasks) {
        try {
            FileOutputStream fos = activity.openFileOutput(ConstantsHelper.RTASKSFILE,
                    Context.MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            for (Task task : tasks) {
                Log.i("task", task.getTitle());
            }

            Gson gson = new Gson();
            gson.toJson(tasks, out);
            out.flush();

        } catch (FileNotFoundException e) {
            throw new RuntimeException();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public void saveProvidingTasksToFile(ArrayList<Task> tasks) {
        try {
            FileOutputStream fos = activity.openFileOutput(ConstantsHelper.PTASKSFILE,
                    Context.MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));


            Gson gson = new Gson();
            gson.toJson(tasks, out);
            out.flush();

        } catch (FileNotFoundException e) {
            throw new RuntimeException();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public ArrayList<Task> getRequestedTasks() {
        try {
            FileInputStream fis = activity.openFileInput(ConstantsHelper.RTASKSFILE);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            Type fileType = new TypeToken<ArrayList<Task>>() {
            }.getType();
            ArrayList<Task> tasks = gson.fromJson(in, fileType);
            return tasks;

        } catch (FileNotFoundException e) {
            Log.i("hey", e.getMessage());
            return new ArrayList<Task>();
        } catch (IOException e) {
            throw new RuntimeException();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public ArrayList<Task> getProvidingTasks() {
        try {
            FileInputStream fis = activity.openFileInput(ConstantsHelper.PTASKSFILE);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            Type fileType = new TypeToken<ArrayList<Task>>() {
            }.getType();
            ArrayList<Task> tasks = gson.fromJson(in, fileType);
            return tasks;

        } catch (FileNotFoundException e) {
            Log.i("hey2", e.getMessage());
            return new ArrayList<Task>();
        } catch (IOException e) {
            throw new RuntimeException();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public void saveTaskToFile(Task task) {
        try {
            FileOutputStream fos = activity.openFileOutput(ConstantsHelper.VIEWTASKFILE,
                    Context.MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));


            Gson gson = new Gson();
            gson.toJson(task, out);
            out.flush();

        } catch (FileNotFoundException e) {
            throw new RuntimeException();
        } catch (IOException e) {
            throw new RuntimeException();
        }

    }

    public Task getTaskFromFile() {
        try {
            FileInputStream fis = activity.openFileInput(ConstantsHelper.VIEWTASKFILE);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            Type fileType = new TypeToken<Task>() {
            }.getType();
            Task task = gson.fromJson(in, fileType);
            return task;

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException();
        } catch (Exception e) {
            throw new RuntimeException();
        }

    }

}
