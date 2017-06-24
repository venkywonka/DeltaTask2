package com.example.android.deltatask2;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Admin on 24-06-2017.
 */

public class AlbumAdapter extends ArrayAdapter<Album> {

    public AlbumAdapter(Activity context, ArrayList<Album> albums){

        super(context,0,albums);
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;

        if(listItemView==null){
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        Album currentAlbum = getItem(position);

        TextView t = (TextView) listItemView.findViewById(R.id.text_view_caption);
        t.setText(currentAlbum.getCaption());

        ImageView i = (ImageView) listItemView.findViewById(R.id.image);
        i.setImageDrawable(currentAlbum.getImage());

        return listItemView;

    }
}
