package com.tlu.btlandroid.Fragment;

import android.app.AlertDialog;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tlu.btlandroid.R;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import adapter.Dayadapter;
import adapter.ExerciseAdapter;
import adapter.ExerciseDiaryAdapter;
import helpers.ExerciseDiaryHelper;
import untity.day;

@RequiresApi(api = Build.VERSION_CODES.O)
public class workoutFragment extends Fragment {

    private View mView;
    private TextView tv4;
    private TextView tv5;
    private TextView tv8;
    private TextView tv7;
    private TextView tv6;

    private RecyclerView rcv;
    private RecyclerView rc2;
    private Dayadapter dayadapter;

    LocalDate localDate = LocalDate.now();
    Calendar calendar = Calendar.getInstance();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.workoutfragment, container, false);

        rcv = mView.findViewById(R.id.rc1);
        dayadapter = new Dayadapter(mView.getContext());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mView.getContext(), RecyclerView.HORIZONTAL, false);
        rcv.setLayoutManager(linearLayoutManager);
        dayadapter.setData(getListDay());
        rcv.setAdapter(dayadapter);


        rc2 = mView.findViewById(R.id.rc2);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(mView.getContext());
        rc2.setLayoutManager(linearLayoutManager2);

        String[] dayNames = {"Chủ nhật", "Thứ Hai", "Thứ Ba", "Thứ Tư", "Thứ Năm", "Thứ Sáu", "Thứ Bảy"};
        String dayName = dayNames[calendar.get(Calendar.DAY_OF_WEEK) - 1];

        tv5 = mView.findViewById(R.id.tv5);
        tv5.setText("Hôm nay là: " + dayName + ", ngày: " + localDate.toString());


        ExerciseDiaryHelper exerciseDiaryHelper = new ExerciseDiaryHelper(getContext());
        tv6 = mView.findViewById(R.id.tv6);
        tv6.setText("Exercise: " + exerciseDiaryHelper.getExerciseCount());
        tv7 = mView.findViewById(R.id.tv7);
        tv7.setText("Calo Burn: " + exerciseDiaryHelper.getCaloBurnSum());
        tv8 = mView.findViewById(R.id.tv8);
        tv8.setText("Duration: " + exerciseDiaryHelper.getDurationSum());

        ExerciseDiaryAdapter.setOnItemClickListener(new ExerciseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String exerciseName) {
                showExerciseInstructionDialog(exerciseName);
            }
        });
        tv4 = mView.findViewById(R.id.tv4);
        tv4.setText(dayName);
        return mView;
    }

    private void showExerciseInstructionDialog(String exerciseName) {
        int videoResource = getVideoResourceFromExerciseName(exerciseName);
        if (videoResource == -1) {
            Toast.makeText(getContext(), "No video found for this exercise", Toast.LENGTH_SHORT).show();
            return;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(exerciseName);
        VideoView videoView = new VideoView(getContext());
        videoView.setMediaController(new MediaController(getContext()));
        String videoPath = "android.resource://" + getContext().getPackageName() + "/" + videoResource;
        videoView.setVideoURI(Uri.parse(videoPath));
        videoView.start();
        builder.setView(videoView);
        builder.setPositiveButton("Close", null);
        builder.show();
    }


    private int getVideoResourceFromExerciseName(String exerciseName) {
        switch (exerciseName) {
            case "Squad":
                return R.raw.squad;
            case "Plank":
                return R.raw.coin;
            case "Hip band":
                return R.raw.flu;
            case "Leg":
                return R.raw.celebrity;
            case "Hanging":
                return R.raw.lilac;
            default:
                return -1;
        }
    }
    private List<day> getListDay() {
        List<day> list = new ArrayList<>();
        list.add(new day(R.drawable.thu2, "THỨ HAI"));
        list.add(new day(R.drawable.thu3, "THỨ BA"));
        list.add(new day(R.drawable.thu4, "THỨ TƯ"));
        list.add(new day(R.drawable.thu5, "THỨ NĂM"));
        list.add(new day(R.drawable.thu6, "THỨ SÁU"));
        list.add(new day(R.drawable.thu7, "THỨ BẨY"));
        list.add(new day(R.drawable.chunhat, "CHỦ NHẬT"));
        return list;
    }

    @Override
    public void onResume() {
        super.onResume();
        ExerciseDiaryHelper exerciseDiaryHelper = new ExerciseDiaryHelper(getContext());
        Cursor cursor = exerciseDiaryHelper.getAllExercises();
        ExerciseDiaryAdapter exerciseDiaryAdapter = new ExerciseDiaryAdapter(getContext(), cursor);
        rc2.setAdapter(exerciseDiaryAdapter);
        updateExerciseDiary();
    }

    private void updateExerciseDiary() {
        ExerciseDiaryHelper exerciseDiaryHelper = new ExerciseDiaryHelper(getContext());
        Cursor cursor = exerciseDiaryHelper.getAllExercises();
        ExerciseDiaryAdapter exerciseDiaryAdapter = new ExerciseDiaryAdapter(getContext(), cursor);
        rc2.setAdapter(exerciseDiaryAdapter);

        tv6.setText("Exercise: " + exerciseDiaryHelper.getExerciseCount());
        tv7.setText("Calo Burn: " + exerciseDiaryHelper.getCaloBurnSum());
        tv8.setText("Duration: " + exerciseDiaryHelper.getDurationSum());
    }


}
