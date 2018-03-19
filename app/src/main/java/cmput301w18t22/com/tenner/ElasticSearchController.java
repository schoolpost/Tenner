package cmput301w18t22.com.tenner;

import android.os.AsyncTask;
import android.util.Log;

import com.searchly.jestdroid.DroidClientConfig;
import com.searchly.jestdroid.JestClientFactory;
import com.searchly.jestdroid.JestDroidClient;

import java.util.ArrayList;
import java.util.List;

import io.searchbox.core.DocumentResult;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;

/**
 * Created by moc on 3/18/18.
 */

public class ElasticSearchController {
    public static JestDroidClient client;

    //Users
    public static class AddUsers extends AsyncTask<User, Void, Boolean> {

        @Override
        protected Boolean doInBackground(User... users) {
            verifySettings();

            for (User user : users) {
                Index index = new Index.Builder(user).index("tenner").type("users").build();
                try {
                    DocumentResult result = client.execute(index);
                    if (result.isSucceeded()) {
                        Log.i("LoginActivitySuccess", "User Registration Successful!");
                        return true;
                    } else {
                        Log.i("LoginActivityError", "User Registration ES -> Error!");
                    }
                } catch (Exception e) {
                    Log.i("LoginActivityError", "User Registration ES Exception -> Error!");
                    Log.i("Yolo", e.getMessage());
                }
            }

            return false;
        }
    }

    public static class SearchUser extends AsyncTask<String, Void, ArrayList<User>> {
        @Override
        protected ArrayList<User> doInBackground(String... search_parameters) {
            verifySettings();

            ArrayList<User> users = new ArrayList<User>();

            Search search = new Search.Builder(search_parameters[0]).addIndex("tenner").addType("users").build();

            try {
                SearchResult result = client.execute(search);
                if (result.isSucceeded()) {
                    List<User> foundUsers = result.getSourceAsObjectList(User.class);
                    users.addAll(foundUsers);
                } else {
                    Log.i("LoginActivityError", "User Search ES -> Error!");
                }
            } catch (Exception e) {
                Log.i("LoginActivityError", "User Search ES Exception -> Error!");
            }

            return users;
        }
    }

    //Tasks
    public static class AddTasks extends AsyncTask<Task, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Task... tasks) {
            verifySettings();

            for (Task task : tasks) {
                Index index = new Index.Builder(task).index("tenner").type("tasks").build();
                try {
                    DocumentResult result = client.execute(index);
                    if (result.isSucceeded()) {
                        Log.i("TaskActivitySuccess", "Task Upload Successful!");
                        return true;
                    } else {
                        Log.i("TaskActivityError", "Task Upload ES -> Error!");
                    }
                } catch (Exception e) {
                    Log.i("TaskActivityError", "Task Upload ES Exception -> Error!");
                }
            }

            return false;
        }
    }

    public static class SearchTasks extends AsyncTask<String, Void, ArrayList<Task>> {
        @Override
        protected ArrayList<Task> doInBackground(String... search_parameters) {
            verifySettings();

            ArrayList<Task> tasks = new ArrayList<Task>();

            Search search = new Search.Builder(search_parameters[0]).addIndex("tenner").addType("tasks").build();

            try {
                SearchResult result = client.execute(search);
                if (result.isSucceeded()) {
                    List<Task> foundTasks = result.getSourceAsObjectList(Task.class);
                    tasks.addAll(foundTasks);
                } else {
                    Log.i("TaskActivityError", "Task Search ES -> Error!");
                }
            } catch (Exception e) {
                Log.i("TaskActivityError", "Task Search ES Exception -> Error!");
            }

            return tasks;
        }
    }

    public static void verifySettings() {
        if (client == null) {
            DroidClientConfig.Builder builder = new DroidClientConfig.Builder("http://cmput301.softwareprocess.es:8080/");
            DroidClientConfig config = builder.build();

            JestClientFactory factory = new JestClientFactory();
            factory.setDroidClientConfig(config);
            client = (JestDroidClient) factory.getObject();
        }
    }
}