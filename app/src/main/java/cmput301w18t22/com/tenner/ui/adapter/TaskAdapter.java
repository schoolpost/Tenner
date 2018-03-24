package cmput301w18t22.com.tenner.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import cmput301w18t22.com.tenner.R;
import cmput301w18t22.com.tenner.classes.Task;

/**
 * Custom Adapter provides the adapter to add a specified taskList to a ListView object
 *
 * Needs to define all 4 overidden methods to function properly, as well as have layout defined in XML
 *
 *
 * Based on https://guides.codepath.com/android/Using-a-BaseAdapter-with-ListView
 * Retrieved 2018-02-05
 */
public class TaskAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Task> taskList;

    //public constructor
    public TaskAdapter(Context context, ArrayList<Task> taskList) {
        this.context = context;
        this.taskList = taskList;
    }

    @Override
    public int getCount() {
        return taskList.size(); //returns number of tasks in taskList
    }

    @Override
    public Task getItem(int position) {
        return taskList.get(position); //returns subscription at specified position
    }

    @Override
    public long getItemId(int position) {
        return position;
    } // This needs a better approach -- what's our plan for primary keys from ElasticSearch??

    /**
     * Get the view for the listView object.
     *
     * @param position integer representing position in ListView
     * @param convertView current View
     * @param parent parent ViewGroup
     * @return View
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // inflate the layout for each list row
        if (convertView == null) {
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.taskadapter_item, parent, false);
        }

        // get current item to be displayed
        Task currentTask = getItem(position);

        // get TextView objects
        TextView nameTextView = (TextView) convertView.findViewById(R.id.task_title);
        TextView requesterNameTextView = (TextView) convertView.findViewById(R.id.requester_name);
        TextView lowestBidTextView = (TextView) convertView.findViewById(R.id.bid_amt);

        // get Subscription information and display in textViews
        nameTextView.setText(currentTask.getTitle());
        requesterNameTextView.setText(currentTask.getRequester().toDisplayName());
        lowestBidTextView.setText(currentTask.getLowestBid().toString());

        // returns the view for the current row
        return convertView;
    }
}



