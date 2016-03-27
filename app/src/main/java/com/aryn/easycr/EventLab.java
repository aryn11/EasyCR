package com.aryn.easycr;

import android.content.Context;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

/**
 * Created by user on 22.03.2016.
 */
public class EventLab {
    private static EventLab sEventLab;
    private Context mAppContex;
    private ArrayList<Event> mEvents;

    private EventLab (Context context){
        mAppContex = context;
        mEvents = new ArrayList<Event>();
        for (int i = 0; i < 100; i++) {
            Event c = new Event();
            c.setTitle("Crime #" + i);
            c.setDate(new Date());
            mEvents.add(c);
        }
    }

    public static EventLab get(Context c){
        if (sEventLab == null)
            sEventLab = new EventLab(c.getApplicationContext());
        return sEventLab;
    }

    public ArrayList<Event> getEvents() {
        return mEvents;
    }
    public Event getEvent (UUID id) {
        for(Event e : mEvents) {
            if(e.getId().equals(id))
                return e;
        }
        return null;
    }
}
