package com.aryn.easycr;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by user on 22.03.2016.
 */
public class EventLab {
    private static final String TAG = "Event";
    private static final String FILENAME = "event.json";
    private ArrayList<Event> mEvents;
    private EventJSONSerializer mSerializer;
    private static EventLab sEventLab;
    private Context mAppContext;

    private EventLab(Context context) {
        mAppContext = context;
        mSerializer = new EventJSONSerializer(mAppContext, FILENAME);
        try {
            mEvents = mSerializer.loadEvents();
        } catch (Exception e) {
            mEvents = new ArrayList<Event>();
            Log.e(TAG, "Error loading crimes: ", e);
        }

    }

    public static EventLab get(Context c) {
        if (sEventLab == null)
            sEventLab = new EventLab(c.getApplicationContext());
        return sEventLab;
    }

    public void addEvent(Event event) {
        mEvents.add(event);
    }

    public void deleteEvent(Event c) {
        mEvents.remove(c);
    }

    public ArrayList<Event> getEvents() {
        return mEvents;
    }

    public Event getEvent(UUID id) {
        for (Event e : mEvents) {
            if (e.getId().equals(id))
                return e;
        }
        return null;
    }

    public boolean saveEvents() {
        try {
            mSerializer.saveEvents(mEvents);
            Log.d(TAG, "events saved to file");
            return true;
        } catch (Exception e) {
            Log.e(TAG, "Error saving events: ", e);
            return false;
        }
    }
}
