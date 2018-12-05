package com.example.abhishek.melody;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getting the global now playing track object
        MusicApp mapp = ((MusicApp)getApplicationContext());
        Track currentTrack = mapp.getCurrentTrack();

        // getting display dimensions of the device
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        Button b1 = (Button)findViewById(R.id.songs);
        Button b2 = (Button)findViewById(R.id.artists);
        int playbar_height = 52;
        int height = displayMetrics.heightPixels;
        int available_height = height - 5*playbar_height;

        //setting height of buttons
        ViewGroup.LayoutParams params1 = b1.getLayoutParams();
        params1.height=available_height/ 2;
        b1.setLayoutParams(params1);

        ViewGroup.LayoutParams params2 = b2.getLayoutParams();
        params2.height=available_height/ 2;
        b2.setLayoutParams(params2);

        //Populating the arraylist of songs
        final ArrayList<Track> tracks = new ArrayList<Track>();
        tracks.add(new Track("Hello","Adele"));
        tracks.add(new Track("Friends","Anne Marie"));
        tracks.add(new Track("Alone","Marshmellow"));
        tracks.add(new Track("End Game","Taylor Swift"));
        tracks.add(new Track("Delicate","Taylor Swift"));
        tracks.add(new Track("Gorgeous","Taylor Swift"));
        tracks.add(new Track("Circles","Ananya Birla"));
        tracks.add(new Track("All the Ways","Meghan Trainor"));
        tracks.add(new Track("So far Away","Martin Garrix"));
        tracks.add(new Track("Girls Like you","Maroon5"));
        tracks.add(new Track("Connection","One Republic"));

        //clickelisteners for buttons with intent to their respective activities
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tracksIntent = new Intent(MainActivity.this,TracksActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("tracks",tracks);
                //bundle.putParcelable("currentTrack",currentTrack);
                tracksIntent.putExtras(bundle);
                //tracksIntent.putParcelableArrayListExtra("tracks",ArrayList<Track extends Parcelable> tracks);
                startActivity(tracksIntent);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent albumIntent = new Intent(MainActivity.this,ArtistActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("tracks",tracks);
               // bundle.putParcelable("currentTrack",currentTrack);
                albumIntent.putExtras(bundle);
                //tracksIntent.putParcelableArrayListExtra("tracks",ArrayList<Track extends Parcelable> tracks);
                startActivity(albumIntent);
            }
        });

        final LinearLayout playbar = (LinearLayout)findViewById(R.id.playbar);
        playbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent playintent = new Intent(MainActivity.this, NowPlayingActivity.class);
                //Bundle b = new Bundle();;
               // b.putParcelable("currentTrack",currentTrack);
               // playintent.putExtras(b);
                startActivity(playintent);
            }
        });

    }
}