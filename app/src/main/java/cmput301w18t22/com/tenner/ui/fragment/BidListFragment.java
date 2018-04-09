package cmput301w18t22.com.tenner.ui.fragment;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Date;

import cmput301w18t22.com.tenner.R;
import cmput301w18t22.com.tenner.classes.Bid;
import cmput301w18t22.com.tenner.classes.Location;
import cmput301w18t22.com.tenner.classes.Task;
import cmput301w18t22.com.tenner.classes.User;
import cmput301w18t22.com.tenner.ui.adapter.MyBidAdapter;
import cmput301w18t22.com.tenner.ui.adapter.OtherBidAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class BidListFragment extends Fragment {

    //public static final String TAG = TaskListFragment.class.getSimpleName();

    public static final String EXTRA_TEXT = "extra_text";

    private static final int MOCK_LOAD_DATA_DELAYED_TIME = 500;

    private static Handler sHandler = new Handler(Looper.getMainLooper());

    private BidListFragment.WeakRunnable mRunnable = new BidListFragment.WeakRunnable(this);

    private String mText;

    private ProgressBar progressBar;
    private ImageButton filter;

    private ArrayList<Bid> bidList;
    private MyBidAdapter myBidAdapter;
    private OtherBidAdapter oBidAdapter;
    private SwipeMenuListView displayList;
    private int tab;

    private boolean showMyBids;

    public BidListFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance(int position) {
        Fragment fragment = new BidListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("tab", position);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tab = getArguments().getInt("tab");
        if (tab == 0) {
            showMyBids = true;
        } else showMyBids = false;

        bidList = new ArrayList<Bid>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bid_list_fragment, container, false);
    }

    /*
    private void showPopup(View v) {
        PopupMenu popup = new PopupMenu(getContext(), v);
        MenuInflater inflater = popup.getMenuInflater();
        // TODO: make bid filters menu
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
    */

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        displayList = (SwipeMenuListView) view.findViewById(R.id.bidsList);
        /*filter = (ImageButton) ((AppCompatActivity) getActivity()).getSupportActionBar().getCustomView().findViewById(R.id.taskFilterButton);
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup(view);
            }
        });*/

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

        if (showMyBids) {
            myBidAdapter = new MyBidAdapter(getContext(), bidList);
            displayList.setAdapter(myBidAdapter);
        } else {
            oBidAdapter = new OtherBidAdapter(getContext(), bidList);
            displayList.setAdapter(oBidAdapter);
        }

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
                        Log.i("Delete", "now");
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

        // Fake Data Here

        User testUser = new User("email@example.com", "First", "Last", "780-123-4567", "");
        Location testLocation = new Location(0.0f, 0.0f, "123 Main St");
        Task testTask = new Task("Task Title", "Task Description", testLocation, new Date(), testUser);
        Bid bid = new Bid(testUser, "1.00", new Date());

        for (int i = 0; i < 20; i++) {
            bidList.add(bid);
        }

        sHandler.post(mRunnable);
        Log.i("debug", "Loaded");
    }

    private static class WeakRunnable implements Runnable {

        WeakReference<BidListFragment> mBidListFragmentReference;

        public WeakRunnable(BidListFragment bidListFragment) {
            this.mBidListFragmentReference = new WeakReference<BidListFragment>(bidListFragment);
        }

        @Override
        public void run() {
            BidListFragment bidListFragment = mBidListFragmentReference.get();
            if (bidListFragment != null && !bidListFragment.isDetached()) {
                bidListFragment.showProgressBar(false);
                bidListFragment.bindData();
            }
        }
    }

}
