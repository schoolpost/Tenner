package cmput301w18t22.com.tenner.helpers;

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

    Map<String, String> taskStatus = new HashMap<String, String>();
    Map<String, String> taskBidStatus = new HashMap<String, String>();
    Map<String, String> bidStatus = new HashMap<String, String>();

    public StatusHelper() {
        taskStatus.put("assigned", "assigned");
        taskStatus.put("bidded", "bidded");
        taskStatus.put("requested", "requested");
        taskStatus.put("done", "done");

        taskBidStatus.put("assigned", "assigned");
        taskBidStatus.put("bidded", "bidded");
        taskBidStatus.put("declined", "declined");

        bidStatus.put("accepted", "accepted");
        bidStatus.put("declined", "declined");
        bidStatus.put("pending", "pending");
    }

    public String getTaskStatus(String status) {
        return taskStatus.get(status);
    }

    public String taskBidStatus(String status) {
        return taskBidStatus.get(status);
    }

    public String getBidStatus(String status) {
        return bidStatus.get(status);
    }
}