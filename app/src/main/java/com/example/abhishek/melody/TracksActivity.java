package com.example.abhishek.melody;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

public class TracksActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_list);

        ArrayList<Track> tracks = getIntent().getParcelableArrayListExtra("tracks");
        TrackAdapter adapter = new TrackAdapter(this, tracks);
        final ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);

        MusicApp mapp = ((MusicApp)getApplicationContext());
        final Track currentTrack = mapp.getCurrentTrack();

        final LinearLayout playbar = (LinearLayout)findViewById(R.id.playbar);
        playbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent playintent = new Intent(TracksActivity.this, NowPlayingActivity.class);
                startActivity(playintent);
            }
        });

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
