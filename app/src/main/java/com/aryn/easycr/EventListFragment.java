package com.aryn.easycr;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 */
public class EventListFragment extends ListFragment {
    private static final String TAG = "EventListFragment";

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        //Event e = (Event)(getListAdapter()).getItem(position);
        Event e = ((EventAdapter)getListAdapter()).getItem(position);
        Log.d(TAG, e.getTitle() + " was clicked");
        Intent i = new Intent(getActivity(), EventActivity.class);
        i.putExtra(EventFragment.EXTRA_EVENT_ID, e.getId());
        startActivity(i);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((EventAdapter)getListAdapter()).notifyDataSetChanged();
    }

    private class EventAdapter extends ArrayAdapter<Event> {
        public EventAdapter(ArrayList<Event> events) {
            super(getActivity(), 0, events);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_events, null);
            }

            Event event = getItem(position);

            TextView titleTextView =
                    (TextView)convertView.findViewById(R.id.event_list_item_titleTextView);
            titleTextView.setText(event.getTitle());
            TextView dateTextView =
                    (TextView)convertView.findViewById(R.id.event_list_item_dateTextView);
            dateTextView.setText(event.getDate().toString());
            CheckBox solvedCheckBox =
                    (CheckBox)convertView.findViewById(R.id.event_list_item_solvedCheckBox);
            //solvedCheckBox.setChecked(event.isSolved());
            return convertView;
        }
    }

    private ArrayList<Event> mEvents;

    public EventListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mEvents = EventLab.get(getActivity()).getEvents();

        //ArrayAdapter<Event> adapter = new ArrayAdapter<Event>(getActivity(), android.R.layout.simple_list_item_1, mEvents);

        EventAdapter adapter = new EventAdapter(mEvents);
        setListAdapter(adapter);
    }




}
