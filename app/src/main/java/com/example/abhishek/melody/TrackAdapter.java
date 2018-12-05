package com.example.abhishek.melody;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class TrackAdapter extends ArrayAdapter<Track>{

    public TrackAdapter(Activity context, ArrayList<Track> tracks){
        super(context, 0, tracks);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //Check if existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        Track currentWord =  getItem(position);

        TextView titleText = (TextView)listItemView.findViewById(R.id.title_text_view);
        titleText.setText(currentWord.getTrackTitle());

        TextView artistText = (TextView)listItemView.findViewById(R.id.artist_text_view);
        artistText.setText(currentWord.getTrackArtist());

        return listItemView;
    }
}
