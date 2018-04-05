package cmput301w18t22.com.tenner.Helpers;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by schoo on 3/29/2018.
 */

public class StatusHelper {

    private static final StatusHelper ourInstance = new StatusHelper();

    static StatusHelper getInstance() {
        return ourInstance;
    }

    Map<String, String> bidStatus = new HashMap<String, String>();
    Map<String, String> taskStatus = new HashMap<String, String>();

    public StatusHelper() {
        bidStatus.put("assigned", "assigned");
        bidStatus.put("bidded", "bidded");
        bidStatus.put("declined", "declined");

        taskStatus.put("assigned", "assigned");
        taskStatus.put("bidded", "bidded");
        taskStatus.put("requested", "requested");
        taskStatus.put("done", "done");
    }

    public String getBidStatus(String status){
        return bidStatus.get(status);
    }

    public String getTaskStatus(String status){
        return taskStatus.get(status);
    }
}
