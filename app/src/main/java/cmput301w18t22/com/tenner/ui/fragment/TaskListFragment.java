package cmput301w18t22.com.tenner.ui.fragment;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.ProgressBar;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Date;

import cmput301w18t22.com.tenner.R;
import cmput301w18t22.com.tenner.classes.Location;
import cmput301w18t22.com.tenner.classes.Task;
import cmput301w18t22.com.tenner.classes.User;
import cmput301w18t22.com.tenner.server.ElasticServer;
import cmput301w18t22.com.tenner.ui.activity.PostTaskActivity;
import cmput301w18t22.com.tenner.ui.activity.TaskDetailActivity;
import cmput301w18t22.com.tenner.ui.adapter.TaskAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class TaskListFragment extends Fragment {

    //public static final String TAG = TaskListFragment.class.getSimpleName();

    public static final String EXTRA_TEXT = "extra_text";

    private static final int MOCK_LOAD_DATA_DELAYED_TIME = 500;

    private static Handler sHandler = new Handler(Looper.getMainLooper());

    private TaskListFragment.WeakRunnable mRunnable = new TaskListFragment.WeakRunnable(this);

    private String mText;

    private ProgressBar progressBar;
    private ImageButton filter;

    private ArrayList<Task> taskList;
    private TaskAdapter myAdapter;
    private SwipeMenuListView displayList;
    private int tab;

    public TaskListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            setFilter();
        }
    }

    public static Fragment newInstance(int position) {
        Fragment fragment = new TaskListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("tab", position);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tab = getArguments().getInt("tab");
        taskList = new ArrayList<Task>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task_list_fragment, container, false);
    }

    private void showPopup(View v) {
        PopupMenu popup = new PopupMenu(getContext(), v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.task_filters, popup.getMenu());
        popup.show();
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Log.i("YO", "HeLLO");
                return false;
            }
        });
    }

    public void setFilter() {
        filter = (ImageButton) ((AppCompatActivity) getActivity()).getSupportActionBar().getCustomView().findViewById(R.id.taskFilterButton);
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup(view);
            }
        });
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        displayList = (SwipeMenuListView) view.findViewById(R.id.tasksList);
        setFilter();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState == null) {
            loadData();
        } else {
            bindData();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(EXTRA_TEXT, mText);
    }

    @Override
    public void onDestroyView() {
        sHandler.removeCallbacks(mRunnable);
        progressBar = null;
        super.onDestroyView();
    }

    private void showProgressBar(boolean show) {
        progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    private void bindData() { // Attach to listview
        Log.i("debug", "Binding");

        myAdapter = new TaskAdapter(getContext(), taskList);
        displayList.setAdapter(myAdapter);

        DisplayMetrics metrics = getContext().getResources().getDisplayMetrics();
        float dp = 80f;
        float fpixels = metrics.density * dp;
        final int pixels = (int) (fpixels + 0.5f);

        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(pixels);
                // set a icon
                deleteItem.setIcon(R.drawable.ic_delete_black_24dp);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };

        displayList.setMenuCreator(creator);

        displayList.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        taskList.remove(position);
                        myAdapter.notifyDataSetChanged();
                        break;
                }
                return false;
            }
        });

    }

    /**
     * mock load data
     */
    private void loadData() { // Server Call
        Log.i("debug", "Loading");
        showProgressBar(true);

        sHandler.post(mRunnable);
        try {
            getTasks();
        } catch(JSONException e){

        }

        Task test = new Task("My Task", "Best Task Ever", new Location(1f, 1f, "New York"),
                new Date(), new User("me@google.com", "John", "Doe", "555-5556", ""));

//        taskList.add(test);
//        taskList.add(test);
//        taskList.add(test);
//        taskList.add(test);
//        taskList.add(test);
//        taskList.add(test);
//        taskList.add(test);
//        taskList.add(test);
//        taskList.add(test);
//        taskList.add(test);
//        taskList.add(test);
//        taskList.add(test);
//        taskList.add(test);
//        taskList.add(test);
//        taskList.add(test);
//        taskList.add(test);
//        taskList.add(test);
//        taskList.add(test);
//        taskList.add(test);
//        taskList.add(test);

        Log.i("debug", "Loaded");
    }

    public void getTasks() throws JSONException {

        ElasticServer.RestClient.get("getaAllTasks", new RequestParams(), new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    taskList = new ArrayList<Task>();
                    Gson gson = new GsonBuilder().create();
                    for(int i = 0; i < response.length(); i++){
                        Task task = gson.fromJson(response.get(i).toString(), Task.class);
                        taskList.add(task);
                    }
                    bindData();
                } catch (Exception e) {

                }
            }
        });
    }

    private static class WeakRunnable implements Runnable {

        WeakReference<TaskListFragment> mTaskListFragmentReference;

        public WeakRunnable(TaskListFragment taskListFragment) {
            this.mTaskListFragmentReference = new WeakReference<TaskListFragment>(taskListFragment);
        }

        @Override
        public void run() {
            TaskListFragment taskListFragment = mTaskListFragmentReference.get();
            if (taskListFragment != null && !taskListFragment.isDetached()) {
                taskListFragment.showProgressBar(false);
                taskListFragment.bindData();
            }
        }
    }

}
