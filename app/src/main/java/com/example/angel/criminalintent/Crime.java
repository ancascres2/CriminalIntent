package com.example.angel.criminalintent;

import java.util.UUID;

/**
 * Created by Angel on 27.01.15.
 */
public class Crime {

    private UUID mId;
    private String mTitle;

    public Crime(){
        // Generate unique identifier
        mId = UUID.randomUUID();
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }
}
