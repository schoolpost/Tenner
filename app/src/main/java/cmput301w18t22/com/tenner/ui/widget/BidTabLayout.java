package cmput301w18t22.com.tenner.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import cmput301w18t22.com.tenner.R;
import cmput301w18t22.com.tenner.ui.adapter.FragmentAdapter;

/**
 * Created by Dinesh 3/24/1018
 */
public class BidTabLayout extends LinearLayout {

    private OnTabItemClickListener mOnTabItemClickListener;

    public interface OnTabItemClickListener {
        void onTabItemClick(int position, View view);
    }

    public BidTabLayout(Context context) {
        this(context, null);
    }

    public BidTabLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BidTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(HORIZONTAL);
        inflate(context, R.layout.bid_tab_layout, this);

        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            final int finalI = i;
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnTabItemClickListener.onTabItemClick(finalI, v);
                }
            });
        }
    }

    public void setOnTabItemClickListener(OnTabItemClickListener listener) {
        this.mOnTabItemClickListener = listener;
    }

    public void select(int position) {
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if (i == position) {
                selectChild(child, true);
            } else {
                selectChild(child, false);
            }
        }
    }

    private void selectChild(View child, boolean select) {
        if (child instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) child;
            group.setSelected(select);
            for (int i = 0; i < group.getChildCount(); i++) {
                selectChild(group.getChildAt(i), select);
            }
        } else {
            child.setSelected(select);
        }
    }

}