package cmput301w18t22.com.tenner.server;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by moc on 3/22/18.
 */

public class ElasticServer {
    private static final ElasticServer.RestClient ourInstance = new ElasticServer.RestClient();

    static ElasticServer.RestClient getInstance() {
        return ourInstance;
    }

    private ElasticServer() {
    }

    public static class RestClient {
        private static final String BASE_URL = "https://cmput301tenner.herokuapp.com/";

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