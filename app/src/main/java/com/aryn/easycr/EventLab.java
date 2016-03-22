package com.aryn.easycr;

import android.content.Context;

/**
 * Created by user on 22.03.2016.
 */
public class EventLab {
    private static EventLab sEventLab;
    private Context mAppContex;

    private EventLab (Context context){
        mAppContex = context;
    }

    public static EventLab get(Context c){
        if (sEventLab == null)
            sEventLab = new EventLab(c.getApplicationContext());
        return sEventLab;
    }
}
