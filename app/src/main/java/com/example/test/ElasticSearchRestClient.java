package com.example.test;

import android.util.Log;

import org.json.*;
import com.loopj.android.http.*;

import cz.msebera.android.httpclient.entity.mime.Header;

/**
 * Created by moc on 3/22/18.
 */

public class ElasticSearchRestClient {

    public void getData() throws JSONException {
        ElasticSearchController.RestClient.get("ping", null, new JsonHttpResponseHandler() {



            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.i("This", "Instead");
                // If the response is JSONObject instead of expected JSONArray
            }

            public void onSuccess(int statusCode, Header[] headers, JSONArray timeline) {
                // Pull out the first event on the public timeline
                Log.i("this2", "instead");
                int i = 0;
//                for(Object o: timeline){
//                    if ( o instanceof JSONObject ) {
//                        i++;
//                        try {
//                            Log.i("tag" + i, ((JSONObject) o).getString("email"));
//                        } catch (Exception e){
//
//                        }
//                    }
//                }


//                JSONObject firstEvent = timeline.get(0);
//                String tweetText = firstEvent.getString("text");

                // Do something with the response
//                System.out.println(tweetText);
            }
        });
    }
}
