package com.example.mygreen;

import android.os.Parcel;
import android.os.Parcelable;

public class Spraying implements Parcelable {

    private Integer ID;
    private String Spraying;

    public Spraying(Integer Id, String spraying)
    {
        ID = Id;
        Spraying = spraying;
    }
    protected Spraying(Parcel in) {
        ID = in.readInt();
        Spraying = in.readString();
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ID);
        dest.writeString(Spraying);
    }

    public static final Creator<Spraying> CREATOR = new Creator<Spraying>() {
        @Override
        public Spraying createFromParcel(Parcel in) {
            return new Spraying(in);
        }

        @Override
        public Spraying[] newArray(int size) {
            return new Spraying[size];
        }
    };

    public void setID(Integer Id) {
        ID = Id;
    }

    public void setSpraying(String spraying){
        Spraying = spraying;
    }

    public  int getID()
    {
        return  ID;
    }

    public String getSpraying() {
        return Spraying;
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
