package cmput301w18t22.com.tenner.ui.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

import cmput301w18t22.com.tenner.R;
import cmput301w18t22.com.tenner.classes.User;
import cmput301w18t22.com.tenner.server.ElasticServer;
import cmput301w18t22.com.tenner.utils.LocalDataHandler;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    public static final String TAG = HomeFragment.class.getSimpleName();

    public static final String EXTRA_TEXT = "extra_text";

    private static final int MOCK_LOAD_DATA_DELAYED_TIME = 500;

    private static Handler sHandler = new Handler(Looper.getMainLooper());

    private HomeFragment.WeakRunnable mRunnable = new HomeFragment.WeakRunnable(this);

    private String mText;

    private User user;

    private TextView greeting;
    private EditText searchBar;
    private ProgressBar progressBar;
    private LocalDataHandler localDataHandler;

    public static Fragment newInstance(String text) {
        HomeFragment fragment = new HomeFragment();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_TEXT, text);
        fragment.setArguments(bundle);
        return fragment;
    }

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        localDataHandler = new LocalDataHandler(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        greeting = (TextView) view.findViewById(R.id.home_greeting);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        searchBar = (EditText) view.findViewById(R.id.home_search);


        // your text box
        searchBar.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    // TODO do something
                    String query = searchBar.getText().toString();
                    try {
                        postEditUser(query);
                    } catch (Exception e){

                    }
                    handled = true;
                }
                return handled;
            }
        });

    }

    public void postEditUser(String query) throws JSONException {

        RequestParams params = new RequestParams();
        Gson gson = new Gson();
        String json = gson.toJson(query);

        try {
            params.put("query", json);
        } catch (Exception e) {

        }

        ElasticServer.RestClient.post("taskSearch", params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    
                } catch (Exception e) {

                }
            }
        });
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
//        outState.putString(EXTRA_TEXT, mText);
    }

    @Override
    public void onDestroyView() {
        sHandler.removeCallbacks(mRunnable);
        searchBar = null;
        progressBar = null;
        super.onDestroyView();
    }

    private void showProgressBar(boolean show) {
        progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    private void bindData() {
        greeting.setText("Welcome, " + user.toDisplayName());

    }

    private void loadData() {
        showProgressBar(true);
        user = localDataHandler.loadUserFromFile();
        sHandler.post(mRunnable);
    }

    private static class WeakRunnable implements Runnable {

        WeakReference<HomeFragment> mHomeFragmentReference;

        public WeakRunnable(HomeFragment homeFragment) {
            this.mHomeFragmentReference = new WeakReference<HomeFragment>(homeFragment);
        }

        @Override
        public void run() {
            HomeFragment homeFragment = mHomeFragmentReference.get();
            if (homeFragment != null && !homeFragment.isDetached()) {
                homeFragment.showProgressBar(false);
                homeFragment.bindData();
            }
        }
    }

}
