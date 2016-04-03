package com.aryn.easycr;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuInflater;

import java.util.UUID;

/**
 * Created by user on 27.03.2016.
 */
public class EventActivity extends SingleFragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected Fragment createFragment() {
        UUID eventId = (UUID)getIntent()
                .getSerializableExtra(EventFragment.EXTRA_EVENT_ID);
        return EventFragment.newInstance(eventId);
    }

}
