package com.example.mygreen;

import android.os.Parcel;
import android.os.Parcelable;

public class Watering implements Parcelable {

    private Integer ID;
    private String Watering;

    public Watering(Integer Id, String watering)
    {
        ID = Id;
        Watering = watering;
    }
    protected Watering(Parcel in) {
        ID = in.readInt();
        Watering = in.readString();
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ID);
        dest.writeString(Watering);
    }

    public static final Creator<Watering> CREATOR = new Creator<Watering>() {
        @Override
        public Watering createFromParcel(Parcel in) {
            return new Watering(in);
        }

        @Override
        public Watering[] newArray(int size) {
            return new Watering[size];
        }
    };

    public void setID(Integer Id) {
        ID = Id;
    }

    public void setWatering(String watering){
        Watering = watering;
    }

    public  int getID()
    {
        return  ID;
    }

    public String getWatering() {
        return Watering;
    }

    @Override
    public int describeContents() {
        return 0;
    }

}

