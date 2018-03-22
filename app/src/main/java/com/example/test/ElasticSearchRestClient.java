package com.example.test;

import android.util.Log;

import org.json.*;
import com.loopj.android.http.*;

/**
 * Created by moc on 3/22/18.
 */

public class ElasticSearchRestClient {

    private static final ElasticSearchRestClient ourInstance = new ElasticSearchRestClient();

    static ElasticSearchRestClient getInstance() {
        return ourInstance;
    }

    private ElasticSearchRestClient() {
    }

    public void getAllUsers() throws JSONException {
        ElasticServer.RestClient.get("getAllUsers", null, new JsonHttpResponseHandler() {

            //https://stackoverflow.com/questions/33215539/foreach-with-jsonarray-and-jsonobject/33215597
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
}
