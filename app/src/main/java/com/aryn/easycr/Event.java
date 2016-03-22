package com.aryn.easycr;

import java.util.Date;
import java.util.UUID;

/**
 * Created by user on 21.03.2016.
 */
public class Event {
    private UUID mId;
    private String mTitle;
    private Date mDate;


    public Event() {
        mId = UUID.randomUUID();
        mDate = new Date();
    }

    public void setId(UUID mId) {
        this.mId = mId;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public UUID getId() {

        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date mDate) {
        this.mDate = mDate;
    }
}
