package com.example.mygreen;

import android.os.Parcel;
import android.os.Parcelable;

public class Care implements Parcelable {

    private Integer ID;
    private String Care;

    public Care(Integer Id, String care)
    {
        ID = Id;
        Care = care;
    }

    protected Care(Parcel in) {
        ID = in.readInt();
        Care = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ID);
        dest.writeString(Care);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Care> CREATOR = new Creator<Care>() {
        @Override
        public Care createFromParcel(Parcel in) {
            return new Care(in);
        }

        @Override
        public Care[] newArray(int size) {
            return new Care[size];
        }
    };

    public void setID(Integer Id) {
        ID = Id;
    }
    public void  setCare(String care){
        Care = care;
    }
    public  int getID()
    {
        return  ID;
    }
    public String getCare() {
        return Care;
    }

}
