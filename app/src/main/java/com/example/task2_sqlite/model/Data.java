package com.example.task2_sqlite.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Data implements Parcelable {

    private int id;
    private String data;
    private String date;
    private String time;


    public Data(int id, String data) {
        this.id = id;
        this.data = data;
    }

    public Data(String data, String date, String time) {
        this.data = data;
        this.date = date;
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Data() {
    }

    public Data(String data) {
        this.data = data;
    }

    protected Data(Parcel in) {
        id = in.readInt();
        data = in.readString();
    }

    public static final Creator<Data> CREATOR = new Creator<Data>() {
        @Override
        public Data createFromParcel(Parcel in) {
            return new Data(in);
        }

        @Override
        public Data[] newArray(int size) {
            return new Data[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(data);

    }

}
