package cmput301w18t22.com.tenner.ui.adapter;

import android.support.v4.app.Fragment;

import com.aspsine.fragmentnavigator.FragmentNavigatorAdapter;

import cmput301w18t22.com.tenner.ui.fragment.MainFragment;
import cmput301w18t22.com.tenner.ui.fragment.TaskListFragment;

/**
 * Created by schoo on 3/22/2018.
 */

public class TasksFragmentAdapter implements FragmentNavigatorAdapter {

    public static final String[] TABS = {"Requested", "Providing"};

    @Override
    public Fragment onCreateFragment(int position) {
        return TaskListFragment.newInstance(position);
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
