package cmput301w18t22.com.tenner;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.app.ActionBar;
import android.widget.EditText;

public class PostTaskFragment extends Fragment {

    // Task object

    EditText titleText;
    EditText descriptionText;
    EditText locationText;
    // image

    public static PostTaskFragment newInstance() {
        PostTaskFragment fragment = new PostTaskFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainActivity) getActivity()).setActionBarTitle("New Task");
        setHasOptionsMenu(true); // Enable Action Bar Buttons



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_post_task, container, false);

        titleText = (EditText)view.findViewById(R.id.editTitle);
        descriptionText = (EditText)view.findViewById(R.id.editDescription);
        locationText = (EditText)view.findViewById(R.id.editLocation);
        // image view

        return view;
    }

    // Action Bar Save Button
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_addtask, menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.post_save:
                // Save Action
                // done();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // Validate and Save input
    public void done(View view) {

    }


}
