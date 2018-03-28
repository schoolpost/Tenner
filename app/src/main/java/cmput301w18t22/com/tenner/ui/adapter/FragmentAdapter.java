package cmput301w18t22.com.tenner.ui.adapter;

import android.support.v4.app.Fragment;

import com.aspsine.fragmentnavigator.FragmentNavigatorAdapter;

import cmput301w18t22.com.tenner.ui.fragment.HomeFragment;
import cmput301w18t22.com.tenner.ui.fragment.MainFragment;
import cmput301w18t22.com.tenner.ui.fragment.ProfileFragment;
import cmput301w18t22.com.tenner.ui.fragment.TasksFragment;

/**
 * Created by aspsine on 16/3/31.
 */
public class FragmentAdapter implements FragmentNavigatorAdapter {

    private static final String TABS[] = {"Home", "Tasks", "Post", "Bids", "Profile"};

    @Override
    public Fragment onCreateFragment(int position) {
        if (position == 0) {
            return HomeFragment.newInstance(TABS[position]);
        }
        if (position == 1) {
            return TasksFragment.newInstance(position);
        }
        if (position == 2) {
            // PostFragment
        }
        if (position == 3) {
            // BidsFragment
        }
        if (position == 4) {
            return ProfileFragment.newInstance(TABS[position]);
        }
        return MainFragment.newInstance(TABS[position]);
    }

    @Override
    public String getTag(int position) {
        if (position == 1) {
            return TasksFragment.TAG;
        }
        return MainFragment.TAG + TABS[position];
    }

    @Override
    public int getCount() {
        return TABS.length;
    }

    public String getName(int position) {
        return TABS[position];
    }
}
