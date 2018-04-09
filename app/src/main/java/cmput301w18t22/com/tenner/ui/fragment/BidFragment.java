package cmput301w18t22.com.tenner.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aspsine.fragmentnavigator.FragmentNavigator;

import cmput301w18t22.com.tenner.R;
import cmput301w18t22.com.tenner.ui.adapter.BidFragmentAdapter;
import cmput301w18t22.com.tenner.ui.widget.BidTabLayout;
import cmput301w18t22.com.tenner.ui.widget.TabLayout;

/**
 * Created by Dinesh on 3/24/2018.
 */

public class BidFragment extends Fragment {


    public static final String TAG = BidFragment.class.getSimpleName();

    private FragmentNavigator mNavigator;

    private BidTabLayout bidTabLayout;

    public BidFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNavigator = new FragmentNavigator(getChildFragmentManager(), new BidFragmentAdapter(), R.id.bidChildContainer);
        mNavigator.setDefaultPosition(0);
        mNavigator.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bid, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bidTabLayout = (BidTabLayout) view.findViewById(R.id.bidTabLayout);
        bidTabLayout.setOnTabItemClickListener(new BidTabLayout.OnTabItemClickListener() {
            @Override
            public void onTabItemClick(int position, View view) {
                setCurrentTab(position);
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setCurrentTab(mNavigator.getCurrentPosition());
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mNavigator.onSaveInstanceState(outState);
    }

    public void setCurrentTab(int position) {
        mNavigator.showFragment(position);
        bidTabLayout.select(position);
    }


    public static Fragment newInstance(int position) {
        Fragment fragment = new BidFragment();
        return fragment;
    }
}
