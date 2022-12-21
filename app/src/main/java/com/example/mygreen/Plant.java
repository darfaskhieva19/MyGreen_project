package com.example.mygreen;

import android.os.Parcel;
import android.os.Parcelable;

public class Plant implements Parcelable {

    private Integer Id;
    private String Title;
    private String Description;
    private String Photo;
    private String Care;
    private String Watering;
    private String Lighting;
    private String Spraying;
    private String Link;

    public static final Creator<Plant> CREATOR = new Creator<Plant>() {
        @Override
        public Plant createFromParcel(Parcel in) {
            return new Plant(in);
        }

        @Override
        public Plant[] newArray(int size) {
            return new Plant[size];
        }
    };

    public Plant(Integer id, String title, String description, String link, String photo, String care, String watering, String lighting, String spraying)
    {
        Id = id;
        Title = title;
        Description = description;
        Link = link;
        Photo = photo;
        Care = care;
        Lighting = lighting;
        Watering = watering;
        Spraying = spraying;
    }
    protected Plant(Parcel in) {
        Id = in.readInt();
        Title = in.readString();
        Description = in.readString();
        Link = in.readString();
        Photo = in.readString();
        Care = in.readString();
        Spraying = in.readString();
        Lighting = in.readString();
        Watering = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeInt(Id);
        dest.writeString(Title);
        dest.writeString(Description);
        dest.writeString(Link);
        dest.writeString(Photo);
        dest.writeString(Care);
        dest.writeString(Lighting);
        dest.writeString(Watering);
        dest.writeString(Spraying);
    }

    public  int getID()
    {
        return  Id;
    }

    public String getTitle() {
        return Title;
    }

    public String getDescription() {
        return Description;
    }

    public String getLink() {
        return Link;
    }

    public String getPhoto() {
        return Photo;
    }

    public String getCare() {
        return Care;
    }

    public String getWatering() {
        return Watering;
    }

    public String getLighting() {
        return Lighting;
    }

    public String getSpraying() {
        return Spraying;
    }

    public void setID(Integer id) {
        Id = id;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setLink(String link) {
        Link = link;
    }

    public void setPhoto(String photo) {
        Photo = photo;
    }

    public void setCare(String care){
        Care = care;
    }

    public void setWatering(String watering){
        Watering = watering;
    }

    public void setLighting(String lighting){
        Lighting = lighting;
    }

    public void setSpraying(String spraying){
        Spraying = spraying;
    }



}

