package adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.tlu.btlandroid.R;

import helpers.ExerciseDatabaseHelper;
import helpers.ExerciseDiaryHelper;
import untity.Exercise;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder> {
    private Context mContext;
    private Cursor mCursor;
    private ExerciseDatabaseHelper mDatabaseHelper;
    private OnItemClickListener mListener;

    public ExerciseAdapter(Context context, Cursor cursor) {
        mContext = context;
        mCursor = cursor;
        mDatabaseHelper = new ExerciseDatabaseHelper(context);
    }

    public void swapCursor(Cursor newCursor) {
        if (mCursor != null) {
            mCursor.close();
        }

        mCursor = newCursor;

        if (newCursor != null) {
            notifyDataSetChanged();
        }
    }

    public interface OnItemClickListener {
        void onItemClick(String exerciseName);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public static class ExerciseViewHolder extends RecyclerView.ViewHolder {
        public TextView mNameTextView;
        public TextView mDurationTextView;
        public TextView mCaloBurnTextView;
        public ImageButton mDeleteButton;
        public FloatingActionButton mAddButton;

        public ExerciseViewHolder(@NonNull View itemView) {
            super(itemView);
            mNameTextView = itemView.findViewById(R.id.tv_NAME);
            mDurationTextView = itemView.findViewById(R.id.tv_DURATION);
            mCaloBurnTextView = itemView.findViewById(R.id.tv_CALOBURN);
            mDeleteButton = itemView.findViewById(R.id.btn_delete_exercise);
            mAddButton = itemView.findViewById(R.id.btn_add_exercise_diary);
        }
    }

    @NonNull
    @Override
    public ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_exercise, parent, false);
        return new ExerciseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseViewHolder holder, int position) {
        if (mCursor != null && mCursor.moveToPosition(position)) {
            String name = "";
            int duration = 0;
            float calorieBurn = 0.0f;
            int id = -1;

            int nameColumnIndex = mCursor.getColumnIndex(ExerciseDatabaseHelper.COLUMN_NAME);
            int durationColumnIndex = mCursor.getColumnIndex(ExerciseDatabaseHelper.COLUMN_DURATION);
            int calorieBurnColumnIndex = mCursor.getColumnIndex(ExerciseDatabaseHelper.COLUMN_CALORIE_BURN);
            int idColumnIndex = mCursor.getColumnIndex(ExerciseDatabaseHelper.COLUMN_ID);

            if (nameColumnIndex != -1) {
                name = mCursor.getString(nameColumnIndex);
            }
            if (durationColumnIndex != -1) {
                duration = mCursor.getInt(durationColumnIndex);
            }
            if (calorieBurnColumnIndex != -1) {
                calorieBurn = mCursor.getFloat(calorieBurnColumnIndex);
            }
            if (idColumnIndex != -1) {
                id = mCursor.getInt(idColumnIndex);
            }

            holder.mNameTextView.setText("Name: " + name);
            holder.mDurationTextView.setText("Duration: " + duration + " minutes");
            holder.mCaloBurnTextView.setText("CaloBurn: " + calorieBurn + " kcal");

            final String finalName = name;
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onItemClick(finalName);
                    }
                }
            });

            final int finalId = id;
            holder.mDeleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteExercise(finalId);
                }
            });

            int finalDuration = duration;
            float finalCalorieBurn = calorieBurn;
            holder.mAddButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Exercise exercise = new Exercise(finalName, finalDuration, finalCalorieBurn);
                    ExerciseDiaryHelper exerciseDiaryHelper = new ExerciseDiaryHelper(mContext);
                    exerciseDiaryHelper.addExerciseDiary(exercise);
                    Toast.makeText(mContext, "Exercise added to diary", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (mCursor == null) {
            return 0;
        }
        return mCursor.getCount();
    }

    private void deleteExercise(int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("Confirm Delete");
        builder.setMessage("Are you sure you want to delete this exercise?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (id != -1) {
                    long result = mDatabaseHelper.deleteExercise(id);
                    if (result > 0) {
                        Toast.makeText(mContext, "Exercise deleted successfully", Toast.LENGTH_SHORT).show();
                        if (mCursor != null) {
                            mCursor.close();
                        }
                        mCursor = mDatabaseHelper.getAllExercises();
                        notifyDataSetChanged();
                    } else {
                        Toast.makeText(mContext, "Failed to delete exercise", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        builder.setNegativeButton("No", null);
        builder.show();
    }
}
