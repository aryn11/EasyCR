package com.aryn.easycr;

import android.support.v4.app.Fragment;

/**
 * Created by user on 27.03.2016.
 */
public class EventListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new EventListFragment();
    }
}
