package com.example.abhishek.melody;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

public class Track implements Parcelable{
    private String mTrackTitle;
    private String mTrackArtist;

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){
        @NonNull
        public Track createFromParcel(Parcel in){
            return new Track(in);
        }
        public Track[] newArray(int size){
            return new Track[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mTrackTitle);
        dest.writeString(this.mTrackArtist);
    }

    public Track(String title, String artist){
        mTrackTitle = title;
        mTrackArtist = artist;
    }

    public Track(Parcel in){
        this.mTrackTitle = in.readString();
        this.mTrackArtist = in.readString();
    }

    public String getTrackTitle(){ return mTrackTitle; }
    public String getTrackArtist(){ return mTrackArtist; }
    public void setTrackArtist(String artist){ mTrackArtist = artist; }
    public void setTrackTitle(String title){ mTrackTitle = title; }
}