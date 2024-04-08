package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SongAdapter extends ArrayAdapter<String> {

    public SongAdapter(@NonNull Context context, ArrayList<String> songList) {
        super(context, android.R.layout.simple_list_item_1, songList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        }

        String currentSong = getItem(position);

        TextView songTextView = convertView.findViewById(android.R.id.text1);
        songTextView.setText(currentSong);

        return convertView;
    }
}
