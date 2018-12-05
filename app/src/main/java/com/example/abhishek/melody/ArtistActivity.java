package com.example.abhishek.melody;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ArtistActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_list);

        // getting Tracks from calling activity
        final ArrayList<Track> tracks = getIntent().getParcelableArrayListExtra("tracks");
        ArrayList<String> artisttext = new ArrayList<>();

        //getting artists names
        for(int i = 0; i < tracks.size(); i++){
            if(artisttext.indexOf(tracks.get(i).getTrackArtist()) < 0){
                artisttext.add(tracks.get(i).getTrackArtist());
            }
        }
        ArtistAdapter adapter = new ArtistAdapter(this, artisttext);
        final ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);

        //onclicklistener to launch now playing activity on click
        final LinearLayout playbar = (LinearLayout)findViewById(R.id.playbar);
        playbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent playintent = new Intent(ArtistActivity.this, NowPlayingActivity.class);
                startActivity(playintent);
            }
        });

        // onclicklistener to show songs of selected artist
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent artistsong = new Intent(ArtistActivity.this,ArtistSongsActivity.class);
                Bundle b = new Bundle();
                String selectedArtist = listView.getItemAtPosition(position).toString();
                b.putString("artistname",selectedArtist);
                b.putParcelableArrayList("tracks",tracks);
                artistsong.putExtras(b);
                startActivity(artistsong);
            }
        });
    }
}