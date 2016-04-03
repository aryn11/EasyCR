package com.aryn.easycr;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;
import java.util.zip.Inflater;


/**
 * A simple {@link Fragment} subclass.
 */
public class EventFragment extends Fragment {
    public static final String EXTRA_EVENT_ID = "com.aryn.easycr.event_id";
    public static final String EVENT_SAVED = "com.aryn.easycr.is_saved";

    private static final String DIALOG_DATE = "date";
    private static final int REQUEST_DATE = 0;

    //variables for saving local changes (localDate and localTitle)
    private String lTitle;
    private Date lDate;

    //Views
    private Event mEvent;
    private EditText mTitleField;
    private Button mDateButton;
    private Button mSave;

    public EventFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.single_main, menu);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setHasOptionsMenu(true);
        mEvent = new Event();
        UUID eventId = (UUID)getArguments().getSerializable(EXTRA_EVENT_ID);
        mEvent = EventLab.get(getActivity()).getEvent(eventId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_event, container, false);

        mTitleField = (EditText)v.findViewById(R.id.event_title);
        mTitleField.setText(mEvent.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //lTitle = s.toString();
                mEvent.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mDateButton = (Button)v.findViewById(R.id.crime_date);
        lDate = mEvent.getDate();
        updateDate();
        mDateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentManager fm = getActivity()
                        .getSupportFragmentManager();
                DatePickerFragment dialog = DatePickerFragment
                        .newInstance(mEvent.getDate());
                dialog.setTargetFragment(EventFragment.this, REQUEST_DATE);
                dialog.show(fm, DIALOG_DATE);
            }
        });

        mSave = (Button)v.findViewById(R.id.button_save);
        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //dataChanged(true);
            }
        });

        return v;
    }


    public void updateDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, MMM dd, yyyy", Locale.ENGLISH);
        String data = simpleDateFormat.format(mEvent.getDate());
        mDateButton.setText(data);
    }

    public static EventFragment newInstance(UUID eventId) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_EVENT_ID, eventId);
        EventFragment fragment = new EventFragment();
        fragment.setArguments(args);
        return fragment;
    }

   /* public void dataChanged(boolean isChanged){
        mEvent.setTitle(lTitle);
        mEvent.setDate(lDate);
    }*/

    @Override
    public void onPause() {
        super.onPause();
        EventLab.get(getActivity()).saveEvents();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) return;
        if (requestCode == REQUEST_DATE) {
            Date date = (Date)data
                    .getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mEvent.setDate(date);
            updateDate();
        }
    }


}
