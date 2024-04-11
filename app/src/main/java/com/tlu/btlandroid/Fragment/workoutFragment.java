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
        dayadapter = new Dayadapter(getContext(), getListDay());
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(mView.getContext(),RecyclerView.HORIZONTAL,false);
        rcv.setLayoutManager(linearLayoutManager);
        dayadapter.setData(getListDay());
        rcv.setAdapter(dayadapter);


        rc2 = mView.findViewById(R.id.rc2);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(mView.getContext());
        rc2.setLayoutManager(linearLayoutManager2);

        String[] dayNames = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        String dayName = dayNames[calendar.get(Calendar.DAY_OF_WEEK) - 1];
        dayadapter.setData(getListDay());
        rcv.setAdapter(dayadapter);
        tv5 = mView.findViewById(R.id.tv5);
        tv5.setText("" + dayName + ", " + localDate.toString());


        ExerciseDiaryHelper exerciseDiaryHelper = new ExerciseDiaryHelper(getContext());
        tv6 = mView.findViewById(R.id.tv6);
        tv6.setText("Exercise: " + exerciseDiaryHelper.getExerciseCount());
        tv7 = mView.findViewById(R.id.tv7);
        tv7.setText("Calo: " + exerciseDiaryHelper.getCaloBurnSum() + " cal");
        tv8 = mView.findViewById(R.id.tv8);
        tv8.setText("Duration: " + exerciseDiaryHelper.getDurationSum() + " min");

        ExerciseDiaryAdapter.setOnItemClickListener(new ExerciseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String exerciseName) {
                showExerciseInstructionDialog(exerciseName);
            }
        });
        return mView;
    }


    private List<day> getListDay() {
        List<day> list = new ArrayList<>();

        // Get current day of week (1 to 7, where 1=Sunday, 2=Monday, ..., 7=Saturday)
        int currentDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        // Add today's day
        list.add(new day(getDayImageResource(currentDayOfWeek), getDayName(currentDayOfWeek)));

        // Calculate tomorrow's day
        int tomorrowDayOfWeek = currentDayOfWeek + 1 > 7 ? 1 : currentDayOfWeek + 1;
        list.add(new day(getDayImageResource(tomorrowDayOfWeek), getDayName(tomorrowDayOfWeek)));
        return list;
    }


    private int getDayImageResource(int dayOfWeek) {
        switch (dayOfWeek) {
            case Calendar.SUNDAY:
                return R.drawable.chunhat;
            case Calendar.MONDAY:
                return R.drawable.thu2;
            case Calendar.TUESDAY:
                return R.drawable.thu3;
            case Calendar.WEDNESDAY:
                return R.drawable.thu4;
            case Calendar.THURSDAY:
                return R.drawable.thu5;
            case Calendar.FRIDAY:
                return R.drawable.thu6;
            case Calendar.SATURDAY:
                return R.drawable.thu7;
            default:
                return R.drawable.chunhat;
        }
    }

    private String getDayName(int dayOfWeek) {
        String[] dayNames = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        return dayNames[dayOfWeek - 1];
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

            case "Plank":
                return R.raw.plank;
            case "Squad":
                return R.raw.squad;
            case "Hip bent":
                return R.raw.bent;
            case "Leg":
                return R.raw.leg;
            case "Hanging":
                return R.raw.hanging;
            default:
                return -1;
        }
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
        tv7.setText("Calo: " + exerciseDiaryHelper.getCaloBurnSum() + " cal");
        tv8.setText("Time: " + exerciseDiaryHelper.getDurationSum() + " min");
    }
}
