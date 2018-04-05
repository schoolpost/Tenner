package cmput301w18t22.com.tenner.classes;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by schoo on 3/29/2018.
 */

public class Status {

    private static final Status ourInstance = new Status();

    static Status getInstance() {
        return ourInstance;
    }

    Map<String, String> bidStatus = new HashMap<String, String>();
    Map<String, String> taskStatus = new HashMap<String, String>();

    public Status() {
        bidStatus.put("assigned", "assigned");
        bidStatus.put("bidded", "bidded");
        bidStatus.put("declined", "declined");

        taskStatus.put("assigned", "assigned");
        taskStatus.put("bidded", "bidded");
        taskStatus.put("requested", "requested");
        taskStatus.put("done", "done");
    }
}
