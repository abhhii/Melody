package com.example.abhishek.melody;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class NowPlayingActivity extends AppCompatActivity {

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_playing);

        ActionBar actionBar =  getSupportActionBar();
        if(actionBar != null){
            actionBar.setTitle(getResources().getString(R.string.now_playing));
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // Getting global now playing object
        MusicApp mapp = ((MusicApp)getApplicationContext());
        Track currentTrack = mapp.getCurrentTrack();

        // Getting display dimensions of the device
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int screen_height = displayMetrics.heightPixels;

        // Setting imageView height w.r.t screen size
        ImageView albumart = (ImageView)findViewById(R.id.albumart);
        ViewGroup.LayoutParams params1 = albumart.getLayoutParams();
        int temp = (int)Math.round(0.6*screen_height);
        params1.height= temp;
        albumart.setLayoutParams(params1);

        TextView songname = (TextView) findViewById(R.id.songname);
        TextView artistname = (TextView) findViewById(R.id.artistname);

        if(currentTrack.getTrackTitle() !=null && currentTrack.getTrackArtist() != null){
            songname.setText(currentTrack.getTrackTitle());
            artistname.setText(currentTrack.getTrackArtist());
        }else{
            //Log.v("Now Playong Activity","Triggred else");
            songname.setText(R.string.select_song);
            artistname.setText("");
        }
    }
}
