package cmput301w18t22.com.tenner.server;
import cmput301w18t22.com.tenner.classes.User;

import android.util.Log;

import org.json.*;

import com.google.gson.Gson;
import com.loopj.android.http.*;



/**
 * Created by moc on 3/22/18.
 */


public class ElasticSearchRestClient {

    private static final ElasticSearchRestClient ourInstance = new ElasticSearchRestClient();

    public static ElasticSearchRestClient getInstance() {
        return ourInstance;
    }

    private ElasticSearchRestClient() {
    }

    public void getAllUsers() throws JSONException {
        ElasticServer.RestClient.get("getAllUsers", null, new JsonHttpResponseHandler() {

            //https://stackoverflow.com/questions/33215539/foreach-with-jsonarray-and-jsonobject/33215597
            //http://loopj.com/android-async-http/
            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onFinish() {
                super.onFinish();
            }

            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    Log.i("yo11", response.get(0).toString());
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Log.i("Failure", String.valueOf(statusCode));
            }
        });
    }

    public void postUser(User user) throws JSONException {

        RequestParams params = new RequestParams();
        //https://github.com/google/gson
        Gson gson = new Gson();
        String json = gson.toJson(user);

        try {
            params.put("user", json);
        } catch (Exception e) {

        }

        ElasticServer.RestClient.post("signUpUser", params, new JsonHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onFinish() {
                super.onFinish();
            }

            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    Log.i("lol", response.get("Success").toString());
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });
    }

    public void postLoginUser(String username) throws JSONException {

        RequestParams params = new RequestParams();

        try {
            params.put("user", username);
        } catch (Exception e) {

        }

        ElasticServer.RestClient.post("signInUser", params, new JsonHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onFinish() {
                super.onFinish();
            }

            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    Log.i("lol", response.get("Success").toString());
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });
    }
}