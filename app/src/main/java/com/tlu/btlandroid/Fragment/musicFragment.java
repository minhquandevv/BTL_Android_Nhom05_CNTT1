package com.tlu.btlandroid.Fragment;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
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

import com.tlu.btlandroid.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import untity.Song;

public class musicFragment extends Fragment {

    TextView txtTenBaiHat, txtTotalTime, txtTimeSong;
    ImageButton btnPlay, btnNext, btnPrevius, btnStop;
    SeekBar seekBar;
    ImageView ivDisc;
    ArrayList<Song> arrayListSong;
    int position = 0;
    MediaPlayer mediaPlayer;
    ArrayAdapter<String> adapter;
    ListView listViewSongs;
    Animation animation;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.musicfragment, container, false);
        addControlls(mView);
        createData();
        khoiTaoMediaPlayer();
        addAllEvents();
        animation = AnimationUtils.loadAnimation(requireActivity(), R.anim.disc_rotate);
        return mView;
    }

    private void addControlls(View view) {
        txtTenBaiHat = view.findViewById(R.id.txtTenBaiHat);
        txtTotalTime = view.findViewById(R.id.txtTotalTime);
        txtTimeSong = view.findViewById(R.id.txtTimeSong);
        btnPlay = view.findViewById(R.id.btnPlay);
        btnNext = view.findViewById(R.id.btnNext);
        btnPrevius = view.findViewById(R.id.btnPrevius);
        btnStop = view.findViewById(R.id.btnStop);
        seekBar = view.findViewById(R.id.seekBar);
        ivDisc = view.findViewById(R.id.ivDisc);
        listViewSongs = view.findViewById(R.id.lvBaiHat);
    }

    private void createData() {
        arrayListSong = new ArrayList<>();
        arrayListSong.add(new Song("Ah puh - IU", R.raw.ahpuh));
        arrayListSong.add(new Song("LILAC - IU", R.raw.lilac));
        arrayListSong.add(new Song("Hi Spring Bye - IU", R.raw.hispringbye));
        arrayListSong.add(new Song("Flu - IU", R.raw.flu));
        arrayListSong.add(new Song("My sea - IU", R.raw.mysea));
        arrayListSong.add(new Song("Coin - IU", R.raw.coin));
        arrayListSong.add(new Song("Epilogue - IU", R.raw.epilogue));
        arrayListSong.add(new Song("Celebrity - IU", R.raw.celebrity));
        arrayListSong.add(new Song("Troll - IU", R.raw.troll));
        arrayListSong.add(new Song("Empty cup - IU", R.raw.emptycup));

        ArrayList<String> songNames = new ArrayList<>();
        for (Song song : arrayListSong) {
            songNames.add(song.getTenBaiHat());
        }

        adapter = new ArrayAdapter<>(requireActivity(), android.R.layout.simple_list_item_1, songNames);

        listViewSongs.setAdapter(adapter);

        listViewSongs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                musicFragment.this.position = position;
                khoiTaoMediaPlayer();
                mediaPlayer.start();
                btnPlay.setImageResource(R.drawable.pause);
                ivDisc.startAnimation(animation);
                setTotalTime();
                UpdateTimeSong();
            }
        });
    }

    private void khoiTaoMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        mediaPlayer = MediaPlayer.create(requireActivity(), arrayListSong.get(position).getFile());
        txtTenBaiHat.setText(arrayListSong.get(position).getTenBaiHat());
    }

    private void setTotalTime() {
        SimpleDateFormat dinhDangGio = new SimpleDateFormat("mm:ss");
        txtTotalTime.setText(dinhDangGio.format(mediaPlayer.getDuration()) + "");
    }

    private void UpdateTimeSong() {
        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat dinhDangGio = new SimpleDateFormat("mm:ss");
                txtTimeSong.setText(dinhDangGio.format(mediaPlayer.getCurrentPosition()));
                seekBar.setProgress(mediaPlayer.getCurrentPosition());
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        position++;
                        if (position > arrayListSong.size() - 1) {
                            position = 0;
                        }
                        if (mediaPlayer.isPlaying()) {
                            mediaPlayer.stop();
                            ivDisc.clearAnimation();
                        }
                        khoiTaoMediaPlayer();
                        mediaPlayer.start();
                        btnPlay.setImageResource(R.drawable.pause);
                        ivDisc.startAnimation(animation);
                        setTotalTime();
                        UpdateTimeSong();
                    }
                });
                handler.postDelayed(this, 500);
            }
        }, 100);
        seekBar.setMax(mediaPlayer.getDuration());
    }

    private void addAllEvents() {
        listViewSongs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                musicFragment.this.position = position;
                khoiTaoMediaPlayer();
                mediaPlayer.start();
                btnPlay.setImageResource(R.drawable.pause);
                ivDisc.startAnimation(animation);
                setTotalTime();
                UpdateTimeSong();
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position++;
                if (position > arrayListSong.size() - 1) {
                    position = 0;
                }
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                    ivDisc.clearAnimation();
                }
                khoiTaoMediaPlayer();
                mediaPlayer.start();
                btnPlay.setImageResource(R.drawable.pause);
                ivDisc.startAnimation(animation);
                setTotalTime();
                UpdateTimeSong();
            }
        });
        btnPrevius.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position--;
                if (position < 0) {
                    position = arrayListSong.size() - 1;
                }
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }
                khoiTaoMediaPlayer();
                mediaPlayer.start();
                btnPlay.setImageResource(R.drawable.pause);
                ivDisc.startAnimation(animation);
                setTotalTime();
                UpdateTimeSong();
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                mediaPlayer.release();
                btnPlay.setImageResource(R.drawable.play);
                khoiTaoMediaPlayer();
                ivDisc.clearAnimation();
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    btnPlay.setImageResource(R.drawable.play);
                    ivDisc.clearAnimation();
                } else {
                    mediaPlayer.start();
                    btnPlay.setImageResource(R.drawable.pause);
                    ivDisc.startAnimation(animation);
                }
                setTotalTime();
                UpdateTimeSong();
            }
        });
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                position++;
                if (position > arrayListSong.size() - 1) {
                    position = 0;
                }
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }
                khoiTaoMediaPlayer();
                mediaPlayer.start();
                btnPlay.setImageResource(R.drawable.pause);
            }
        });
    }
}
