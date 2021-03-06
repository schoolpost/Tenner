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
import android.widget.AdapterView;
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

import cmput301w18t22.com.tenner.R;
import cmput301w18t22.com.tenner.classes.Task;
import cmput301w18t22.com.tenner.classes.User;
import cmput301w18t22.com.tenner.helpers.InternetStatusHelper;
import cmput301w18t22.com.tenner.server.ElasticServer;
import cmput301w18t22.com.tenner.ui.activity.TaskDetailActivity;
import cmput301w18t22.com.tenner.ui.adapter.TaskAdapter;
import cmput301w18t22.com.tenner.helpers.LocalDataHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class TaskListFragment extends Fragment {

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
    private User user;
    private LocalDataHelper localDataHelper;
    private InternetStatusHelper internetStatusHelper = new InternetStatusHelper();

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
        localDataHelper = new LocalDataHelper(getActivity());
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
                        deleteTask(position);
                        break;
                }
                return false;
            }
        });

        displayList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                openTaskDetails(taskList.get(i));
            }
        });
    }


    public void openTaskDetails(Task task) {
        Intent intent = new Intent();
        intent.setClass(getContext(), TaskDetailActivity.class);
        localDataHelper.saveTaskToFile(task);
        startActivity(intent);

    }


    /**
     * mock load data
     */
    private void loadData() {
        // Server Call
        Log.i("lol2", "lol3");
//        sendOfflineTasks();
        showProgressBar(true);
        user = localDataHelper.loadUserFromFile();
        String url;
        switch (tab) {
            case 0:
                url = "getRequestedTasks";
                taskList = localDataHelper.getRequestedTasks();
                break;
            case 1:
                url = "getProvidingTasks";
                taskList = localDataHelper.getProvidingTasks();
                break;
            default:
                url = "";
        }

        if (internetStatusHelper.isConnected(getContext())) {
            try {
                getTasks(url);
            } catch (JSONException e) {

            }
        } else {
            sHandler.postDelayed(mRunnable, 500);
        }


    }
    //TODO : We are so close to finish offline just need to fix this function
//    public void sendOfflineTasks() {
//        try {
//            LocalDataHelper localDataHelper = new LocalDataHelper(getActivity());
//            ArrayList<Task> offlineTasks = localDataHelper.getOfflineTasks();
//            Log.i("upload3", "lol");
//            for(int i = 0; i < offlineTasks.size(); i++){
//                Log.i("upload", "lol");
//                int val = 0;
//                if(i == offlineTasks.size() - 1){
//                    val = 1;
//                }
//                postTask(offlineTasks.get(i), val);
//            }
//        } catch (Exception e) {
//            Log.i("upload", e.getMessage());
//        }
//    }

    public void getTasks(final String url) throws JSONException {

        RequestParams params = new RequestParams();

        try {
            params.put("user", user.getEmail());
        } catch (Exception e) {

        }

        ElasticServer.RestClient.post(url, params, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                try {

                    Gson gson = new GsonBuilder().create();
                    taskList = new ArrayList<Task>();

                    for (int i = 0; i < response.length(); i++) {
                        taskList.add(gson.fromJson(response.get(i).toString(), Task.class));
                    }
                    int index = 0;
                    for (Task t : taskList) {
                        index++;
                        Log.i("item" + index, t.getTitle());
                    }

                } catch (Exception e) {

                }
                if (url.equals("getRequestedTasks")) {
                    localDataHelper.saveRequestedTasksToFile(taskList);
                } else if (url.equals("getProvidingTasks")) {
                    localDataHelper.saveProvidingTasksToFile(taskList);
                }
                sHandler.postDelayed(mRunnable, 500);
            }
        });
    }


    public void deleteTask(final int taskIndex) {

        if (!internetStatusHelper.isConnected(getContext())) {
            taskList.remove(taskIndex);
            myAdapter.notifyDataSetChanged();
            switch (tab) {
                case 0:
                    localDataHelper.saveRequestedTasksToFile(taskList);
                    break;
                case 1:
                    localDataHelper.saveProvidingTasksToFile(taskList);
                    break;
            }
        } else {

            RequestParams params = new RequestParams();

            try {
                params.put("user", user.getEmail());
                params.put("title", taskList.get(taskIndex).getTitle());
            } catch (Exception e) {

            }

            ElasticServer.RestClient.post("deleteTask", params, new JsonHttpResponseHandler() {

                @Override
                public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response) {
                    super.onSuccess(statusCode, headers, response);
                    try {

                        if (response.has("Success")) {
                            taskList.remove(taskIndex);
                            myAdapter.notifyDataSetChanged();
                        }

                        if (response.has("Error")) {
                            Log.i("Error", "somthign bad happened");
                        }


                    } catch (Exception e) {

                    }
                }
            });
        }

    }

    public void postTask(final Task task, final int i) {
        RequestParams params = new RequestParams();
        //https://github.com/google/gson
        Gson gson = new Gson();
        String json = gson.toJson(task);

        try {
            params.put("task", json);
        } catch (Exception e) {

        }

        ElasticServer.RestClient.post("editTask", params, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    if (response.has("Success")) {
                        if(i != 0){
                            //localDataHelper.deleteOfflineTasks();
                        }
                    } else if (response.has("Error")) {
                    }
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
