package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

import untity.Song;

public class SongAdapter extends ArrayAdapter<Song> {

    private LayoutInflater inflater;

    public SongAdapter(@NonNull Context context, ArrayList<Song> songs) {
        super(context, android.R.layout.simple_list_item_1, songs);
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.songTextView = convertView.findViewById(android.R.id.text1);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Song song = getItem(position);

        if (song != null) {
            viewHolder.songTextView.setText(song.getTenBaiHat());
        }

        return convertView;
    }

    static class ViewHolder {
        TextView songTextView;
    }
}
