package com.tlu.btlandroid.Fragment;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.FirebaseApp;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.tlu.btlandroid.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import untity.Song;

public class musicFragment extends Fragment {

    TextView txtTenBaiHat, txtTotalTime, txtTimeSong;
    ImageButton btnPlay, btnNext;
    SeekBar seekBar;
    ImageView ivDisc;
    ArrayList<Song> arrayListSong;
    int position = 0;
    MediaPlayer mediaPlayer;
    ArrayAdapter<String> adapter;
    ListView listViewSongs;
    Animation animation;
    FirebaseStorage storage;
    StorageReference storageRef;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.musicfragment, container, false);
        addControls(mView);
        FirebaseApp.initializeApp(getContext());
        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();

        createData();

        initializeMediaPlayer();
        addAllEvents();
        animation = AnimationUtils.loadAnimation(requireActivity(), R.anim.disc_rotate);
        return mView;
    }


    private void addControls(View view) {
        txtTenBaiHat = view.findViewById(R.id.txtTenBaiHat);
        txtTotalTime = view.findViewById(R.id.txtTotalTime);
        txtTimeSong = view.findViewById(R.id.txtTimeSong);
        btnPlay = view.findViewById(R.id.btnPlay);
        btnNext = view.findViewById(R.id.btnNext);
        seekBar = view.findViewById(R.id.seekBar);
        ivDisc = view.findViewById(R.id.ivDisc);
        listViewSongs = view.findViewById(R.id.lvBaiHat);
    }

    private void createData() {
        arrayListSong = new ArrayList<>();
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference("music/");
        String storageBaseUrl = "gs://vmquan-th1-android.appspot.com";

        storageRef.listAll().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (StorageReference item : task.getResult().getItems()) {
                    item.getMetadata().addOnSuccessListener(storageMetadata -> {
                        String path = item.getPath();
                        String tenBaiHat = path.substring(path.lastIndexOf("/") + 1);
                        int indexOfExtension = tenBaiHat.lastIndexOf(".");
                        if (indexOfExtension > 0) {
                            tenBaiHat = tenBaiHat.substring(0, indexOfExtension);
                        }
                        String fullPath = storageBaseUrl + path;
                        Log.d("Firebase Storage", "File path: " + fullPath);

                        String finalTenBaiHat = tenBaiHat;
                        arrayListSong.add(new Song(finalTenBaiHat, fullPath));

                        if (adapter == null) {
                            adapter = new ArrayAdapter<>(requireActivity(), android.R.layout.simple_list_item_1);
                            listViewSongs.setAdapter(adapter);
                        }
                        adapter.add(finalTenBaiHat);
                    }).addOnFailureListener(e -> {
                        Log.e("Firebase Storage", "Error getting metadata: " + e.getMessage());
                    });
                }
            } else {
                Log.e("Firebase Storage", "Error listing files: " + task.getException().getMessage());
            }
        });

        listViewSongs.setOnItemClickListener((parent, view, position, id) -> {
            this.position = position;
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
                ivDisc.clearAnimation();
            }
            initializeMediaPlayer();
            mediaPlayer.start();
            btnPlay.setImageResource(R.drawable.pause);
            ivDisc.startAnimation(animation);
            setTotalTime();
            updateTimeSong();
        });
    }


    private void initializeMediaPlayer() {
        if (arrayListSong == null || arrayListSong.isEmpty()) {
            return;
        }

        if (mediaPlayer != null) {
            mediaPlayer.release();
        }

        String songUrl = String.valueOf(arrayListSong.get(position).getFile());
        mediaPlayer = new MediaPlayer();
        try {
            storageRef.child(songUrl).getDownloadUrl().addOnSuccessListener(uri -> {
                try {
                    mediaPlayer.setDataSource(uri.toString());
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        txtTenBaiHat.setText(arrayListSong.get(position).getTenBaiHat());
    }


    private void setTotalTime() {
        SimpleDateFormat dinhDangGio = new SimpleDateFormat("mm:ss");
        txtTotalTime.setText(dinhDangGio.format(mediaPlayer.getDuration()));
    }

    private void updateTimeSong() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat dinhDangGio = new SimpleDateFormat("mm:ss");
                txtTimeSong.setText(dinhDangGio.format(mediaPlayer.getCurrentPosition()));
                seekBar.setProgress(mediaPlayer.getCurrentPosition());
                mediaPlayer.setOnCompletionListener(mp -> {
                    position++;
                    if (position > arrayListSong.size() - 1) {
                        position = 0;
                    }
                    if (mediaPlayer.isPlaying()) {
                        mediaPlayer.stop();
                        ivDisc.clearAnimation();
                    }
                    initializeMediaPlayer();
                    mediaPlayer.start();
                    btnPlay.setImageResource(R.drawable.pause);
                    ivDisc.startAnimation(animation);
                    setTotalTime();
                    updateTimeSong();
                });
                handler.postDelayed(this, 500);
            }
        }, 100);
        seekBar.setMax(mediaPlayer.getDuration());
    }

    private void addAllEvents() {
        btnNext.setOnClickListener(v -> {
            position++;
            if (position > arrayListSong.size() - 1) {
                position = 0;
            }
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
                ivDisc.clearAnimation();
            }
            initializeMediaPlayer();
            mediaPlayer.start();
            btnPlay.setImageResource(R.drawable.pause);
            ivDisc.startAnimation(animation);
            setTotalTime();
            updateTimeSong();
        });

    }
}