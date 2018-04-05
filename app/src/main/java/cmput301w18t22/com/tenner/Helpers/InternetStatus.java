package cmput301w18t22.com.tenner.Helpers;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

class InternetStatus {

    //https://developer.android.com/training/monitoring-device-state/connectivity-monitoring.html#DetermineType

    private static final InternetStatus ourInstance = new InternetStatus();

    static InternetStatus getInstance() {
        return ourInstance;
    }

    public InternetStatus() {
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
