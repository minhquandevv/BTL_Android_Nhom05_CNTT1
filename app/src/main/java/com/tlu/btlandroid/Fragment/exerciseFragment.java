package com.tlu.btlandroid.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tlu.btlandroid.R;

import adapter.ExerciseAdapter;
import helpers.ExerciseDatabaseHelper;
import untity.Exercise;

public class exerciseFragment extends Fragment {
    private View mView;
    private RecyclerView mRecyclerView;
    private ExerciseAdapter mAdapter;
    private ExerciseDatabaseHelper mDatabaseHelper;

    private EditText mEditName, mEditDuration, mEditCaloBurn;
    private Button mBtnAdd;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.exercisefragment, container, false);

        mDatabaseHelper = new ExerciseDatabaseHelper(getContext());

        mRecyclerView = mView.findViewById(R.id.rcv_exercise);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new ExerciseAdapter(getContext(), null);
        mRecyclerView.setAdapter(mAdapter);

        mEditName = mView.findViewById(R.id.edit_name_data);
        mEditDuration = mView.findViewById(R.id.edit_duration_data);
        mEditCaloBurn = mView.findViewById(R.id.edit_calorburn_data);
        mBtnAdd = mView.findViewById(R.id.btn_add_exercise);

        mBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddExerciseDialog();
            }
        });


        SearchView searchView = mView.findViewById(R.id.search_item);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Cursor cursor = mDatabaseHelper.searchExercise(newText);
                if (cursor != null) {
                    mAdapter.swapCursor(cursor);
                }
                return true;
            }
        });

        Cursor cursor = mDatabaseHelper.getAllExercises();
        if (cursor != null) {
            mAdapter.swapCursor(cursor);
        }


        mAdapter.setOnItemClickListener(new ExerciseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String exerciseName) {
                showExerciseInstructionDialog(exerciseName);
            }
        });


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

    private void showAddExerciseDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Add Exercise");

        View viewInflated = LayoutInflater.from(getContext()).inflate(R.layout.add_exercise_to_db, (ViewGroup) getView(), false);

        final EditText inputName = viewInflated.findViewById(R.id.edit_name_data);
        final EditText inputDuration = viewInflated.findViewById(R.id.edit_duration_data);
        final EditText inputCaloBurn = viewInflated.findViewById(R.id.edit_calorburn_data);

        builder.setView(viewInflated);

        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = inputName.getText().toString().trim();
                String durationStr = inputDuration.getText().toString().trim();
                String caloBurnStr = inputCaloBurn.getText().toString().trim();

                if (name.isEmpty() || durationStr.isEmpty() || caloBurnStr.isEmpty()) {
                    Toast.makeText(getContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                int duration = Integer.parseInt(durationStr);
                float caloBurn = Float.parseFloat(caloBurnStr);

                addExercise(name, duration, caloBurn);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    private void addExercise(String name, int duration, float caloBurn) {
        long result = mDatabaseHelper.addExercise(new Exercise(name, duration, caloBurn));
        if (result != -1) {
            Toast.makeText(getContext(), "Exercise added successfully", Toast.LENGTH_SHORT).show();
            Cursor cursor = mDatabaseHelper.getAllExercises();
            if (cursor != null) {
                mAdapter.swapCursor(cursor);
            }
        } else {
            Toast.makeText(getContext(), "Failed to add exercise", Toast.LENGTH_SHORT).show();
        }
    }


}
