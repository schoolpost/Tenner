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

    // TODO we need a function which adds tweets to elastic search
    public static class AddUser extends AsyncTask<User, Void, Void> {

        @Override
        protected Void doInBackground(User... users) {
            verifySettings();
            int i = 0;
            for (User user : users) {
                Index index = new Index.Builder(user).index("tenner").type("user").build();

                Log.i("user" + i, user.getEmail());
                i++;

                try {
                    // where is the client?
                    DocumentResult result = client.execute(index);
                    if(result.isSucceeded())
                    {
                        Log.i("useragain", "hello");
                        //tweet.setId(result.getId());
                    } else {
                        Log.i("Error", result.getErrorMessage());
                        Log.i("Error","Some error =(");
                    }

                } catch (Exception e) {
                    Log.i("Error", "The application failed to build and send the users!");
                }

            }
            return null;
        }
    }

    /*public static class AddUser extends AsyncTask<User, Void, Void> {
        verifySettings();

        @Override
        protected Void doInBackground(User... users) {
            int i = 0;
            for(User user : users){
//                Index index = new Index.Builder(user).index("testing").type("user").build();

                Log.i("user" + i, user.getEmail());
                i++;
                //
            }

            return null;
        }
    }*/

    public static class GetTweetsTask extends AsyncTask<String, Void, ArrayList<User>> {
        @Override
        protected ArrayList<User> doInBackground(String... search_parameters) {
            verifySettings();

            ArrayList<User> users = new ArrayList<User>();

            // TODO Build the query
            Search search = new Search.Builder(search_parameters[0]).addIndex("testing").addType("user").build();
            try {
                // TODO get the results of the query
                SearchResult result = client.execute(search);
                if(result.isSucceeded())
                {
                    List<User> foundUsers = result.getSourceAsObjectList(User.class);
                    users.addAll(foundUsers);
                }
                else
                {
                    Log.i("Error", "Search query failed to find any thing =/");
                }
            }
            catch (Exception e) {
                Log.i("Error", "Something went wrong when we tried to communicate with the elasticsearch server!");
            }

            return users;
        }
    }

    public static void verifySettings(){
        if (client == null) {
            DroidClientConfig.Builder builder = new DroidClientConfig.Builder("http://cmput301.softwareprocess.es:8080/");
            DroidClientConfig config = builder.build();

            JestClientFactory factory = new JestClientFactory();
            factory.setDroidClientConfig(config);
            client = (JestDroidClient) factory.getObject();
        }
    }
}
