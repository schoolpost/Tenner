package cmput301w18t22.com.tenner.ui.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.ref.WeakReference;

import cmput301w18t22.com.tenner.R;
import cmput301w18t22.com.tenner.utils.SharedPrefUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    public static final String TAG = ProfileFragment.class.getSimpleName();

    public static final String EXTRA_TEXT = "extra_text";

    private static final int MOCK_LOAD_DATA_DELAYED_TIME = 500;

    private static Handler sHandler = new Handler(Looper.getMainLooper());
    private ProfileFragment.WeakRunnable mRunnable = new ProfileFragment.WeakRunnable(this);

    private String mText;

    private TextView name;
    private TextView email;
    private TextView phone;
    private ProgressBar progressBar;


    public static Fragment newInstance(String text) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_TEXT, text);
        fragment.setArguments(bundle);
        return fragment;
    }

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mText = getArguments().getString(EXTRA_TEXT);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);

        name = (TextView) view.findViewById(R.id.profile_name);
        email = (TextView) view.findViewById(R.id.profile_email);
        phone = (TextView) view.findViewById(R.id.profile_phone);

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

        name = null;
        email = null;
        phone = null;

        super.onDestroyView();
    }

    private void showProgressBar(boolean show) {
        progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    private void bindData() {
        // Change to user value
        name.setText("Csaba"); // First + Last Name
        email.setText("test@gmail.com");
        phone.setText("XXX-XXX-XXXX");
    }

    private void loadData() {
        showProgressBar(true);
        sHandler.post(mRunnable);
    }

    private static class WeakRunnable implements Runnable {

        WeakReference<ProfileFragment> mProfileFragmentReference;

        public WeakRunnable(ProfileFragment profileFragment) {
            this.mProfileFragmentReference = new WeakReference<ProfileFragment>(profileFragment);
        }

        @Override
        public void run() {
            ProfileFragment profileFragment = mProfileFragmentReference.get();
            if (profileFragment != null && !profileFragment.isDetached()) {
                profileFragment.showProgressBar(false);
                profileFragment.bindData();
            }
        }
    }


}
