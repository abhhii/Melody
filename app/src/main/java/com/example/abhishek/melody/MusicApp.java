package com.example.abhishek.melody;
import android.app.Application;

public class MusicApp extends Application{
    private Track currentTrack = new Track(null,null);

    public Track getCurrentTrack() {
        return currentTrack;
    }
    public void setCurrentTrack(String trackTitle, String trackArtist){
        currentTrack.setTrackTitle(trackTitle);
        currentTrack.setTrackArtist(trackArtist);
    }
}
