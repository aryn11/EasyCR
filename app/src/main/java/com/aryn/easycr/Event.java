package com.aryn.easycr;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.UUID;

/**
 * Created by user on 21.03.2016.
 */
public class Event {
    private UUID mId;
    private String mTitle;
    private Date mDate;

    private static final String JSON_ID = "id";
    private static final String JSON_TITLE = "title";
    //private static final String JSON_SOLVED = "solved";
    private static final String JSON_DATE = "date";

    private String mType;


    public Event() {
        mId = UUID.randomUUID();
        mDate = new Date();
    }

    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(JSON_ID, mId.toString());
        json.put(JSON_TITLE, mTitle);
        //json.put(JSON_SOLVED, mSolved);
        json.put(JSON_DATE, mDate.getTime());
        return json;
    }
    public Event(JSONObject json) throws JSONException {
        mId = UUID.fromString(json.getString(JSON_ID));
        mTitle = json.getString(JSON_TITLE);
        //mSolved = json.getBoolean(JSON_SOLVED);
        mDate = new Date(json.getLong(JSON_DATE));
    }

    public UUID getId() {

        return mId;
    }

    public void setId(UUID mId) {
        this.mId = mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date mDate) {
        this.mDate = mDate;
    }

    public String getType() {
        return mType;
    }

    public void setType(String mType) {
        this.mType = mType;
    }

    @Override
    public String toString() {
        return mTitle;
    }
}
