package com.v7ench.eswaripg;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by vignesh2514 on 26/1/17.
 */

public class Members_list implements Parcelable {
    private String name;
    private String roomno;
    private String monum;
    private String amt;
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoomno() {
        return roomno;
    }

    public void setRoomno(String roomno) {
        this.roomno = roomno;
    }

    public String getMonum() {
        return monum;
    }

    public void setMonum(String monum) {
        this.monum = monum;
    }

    public String getAmt() {
        return amt;
    }

    public void setAmt(String amt) {
        this.amt = amt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}

