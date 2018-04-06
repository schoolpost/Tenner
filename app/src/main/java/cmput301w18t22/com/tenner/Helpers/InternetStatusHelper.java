package cmput301w18t22.com.tenner.Helpers;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

class InternetStatusHelper {

    //https://developer.android.com/training/monitoring-device-state/connectivity-monitoring.html#DetermineType

    private static final InternetStatusHelper ourInstance = new InternetStatusHelper();

    static InternetStatusHelper getInstance() {
        return ourInstance;
    }

    public InternetStatusHelper() {
    }

    public boolean isConnected(Context ct) {
        ConnectivityManager connectivityManager = (ConnectivityManager) ct.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo currentActiveNetwork = connectivityManager.getActiveNetworkInfo();

        if(currentActiveNetwork != null && currentActiveNetwork.isConnectedOrConnecting()){
            return true;
        } else {
            return false;
        }
    }
}