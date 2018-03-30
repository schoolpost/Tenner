package cmput301w18t22.com.tenner.ui.adapter;

import android.support.v4.app.Fragment;

import com.aspsine.fragmentnavigator.FragmentNavigatorAdapter;

import cmput301w18t22.com.tenner.ui.fragment.BidListFragment;
import cmput301w18t22.com.tenner.ui.fragment.MainFragment;

/**
 * Created by Dinesh on 3/24/2018.
 */

public class BidFragmentAdapter implements FragmentNavigatorAdapter {

    public static final String[] TABS = {"My Bids", "Incoming Bids"};

    @Override
    public Fragment onCreateFragment(int position) {
        return BidListFragment.newInstance(position);
    }

    @Override
    public String getTag(int position) {
        return MainFragment.TAG + TABS[position];
    }

    @Override
    public int getCount() {
        return TABS.length;
    }

}