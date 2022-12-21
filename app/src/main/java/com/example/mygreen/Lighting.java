package com.example.mygreen;

import android.os.Parcel;
import android.os.Parcelable;

public class Lighting implements Parcelable {

    private Integer ID;
    private String Lighting;

    public Lighting(Integer Id, String lighting)
    {
        ID = Id;
        Lighting = lighting;
    }
    protected Lighting(Parcel in) {
        ID = in.readInt();
        Lighting = in.readString();
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ID);
        dest.writeString(Lighting);
    }

    public static final Creator<Lighting> CREATOR = new Creator<Lighting>() {
        @Override
        public Lighting createFromParcel(Parcel in) {
            return new Lighting(in);
        }

        @Override
        public Lighting[] newArray(int size) {
            return new Lighting[size];
        }
    };

    public void setID(Integer Id) {
        ID = Id;
    }

    public void setLighting(String lighting){
        Lighting = lighting;
    }

    public  int getID()
    {
        return  ID;
    }

    public String getLighting() {
        return Lighting;
    }

    @Override
    public int describeContents() {
        return 0;
    }

}
