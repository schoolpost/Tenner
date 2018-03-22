package com.example.test;

import com.loopj.android.http.*;

/**
 * Created by moc on 3/18/18.
 */

public class ElasticSearchController {

    public static class RestClient {
        private static final String BASE_URL = "https://4bf5aa5ed70c412da36dcacb5b740d79.vfs.cloud9.us-east-1.amazonaws.com/";

        private static AsyncHttpClient client = new AsyncHttpClient();

        public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
            client.get(getAbsoluteUrl(url), params, responseHandler);
        }

        public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
            client.post(getAbsoluteUrl(url), params, responseHandler);
        }

        private static String getAbsoluteUrl(String relativeUrl) {
            return BASE_URL + relativeUrl;
        }
    }
}