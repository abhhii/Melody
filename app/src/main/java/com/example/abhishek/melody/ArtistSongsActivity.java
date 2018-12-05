package com.example.abhishek.melody;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

public class ArtistSongsActivity extends AppCompatActivity {
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
        setContentView(R.layout.item_list);
        //getting global now playing track object
        MusicApp mapp = ((MusicApp)getApplicationContext());
        final Track currentTrack = mapp.getCurrentTrack();

        ActionBar actionBar =  getSupportActionBar();
        if(actionBar != null){
            actionBar.setTitle(getResources().getString(R.string.now_playing));
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        ArrayList<Track> alltracks = getIntent().getParcelableArrayListExtra("tracks");
        String artist = getIntent().getStringExtra("artistname");
        ArrayList<Track> selectedTracks = new ArrayList<Track>();

        //selecting tracks to display
        for(int i = 0; i<alltracks.size(); i++)
        {
            if(alltracks.get(i).getTrackArtist().equals(artist))
                selectedTracks.add(alltracks.get(i));
        }
        TrackAdapter adapter = new TrackAdapter(this, selectedTracks);
        final ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);

        final LinearLayout playbar = (LinearLayout)findViewById(R.id.playbar);
        playbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent playintent = new Intent(ArtistSongsActivity.this, NowPlayingActivity.class);
                startActivity(playintent);
            }
        });

        //updating now playing global now playing object when list item is clicked
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Track temp = (Track) listView.getItemAtPosition(position);
                currentTrack.setTrackTitle(temp.getTrackTitle());
                currentTrack.setTrackArtist(temp.getTrackArtist());
            }
        });
    }
}