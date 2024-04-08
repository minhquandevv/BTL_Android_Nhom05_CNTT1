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

import com.tlu.btlandroid.R;

import helpers.ExerciseDiaryHelper;

public class ExerciseDiaryAdapter extends RecyclerView.Adapter<ExerciseDiaryAdapter.ExerciseViewHolder> {

    private Context mContext;
    private Cursor mCursor;

    private static ExerciseAdapter.OnItemClickListener mListener;


    public ExerciseDiaryAdapter(Context context, Cursor cursor) {
        mContext = context;
        mCursor = cursor;
    }

    public static void setOnItemClickListener(ExerciseAdapter.OnItemClickListener listener) {
        mListener = listener;
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

    public static class ExerciseViewHolder extends RecyclerView.ViewHolder {
        public TextView mNameTextView;
        public TextView mDurationTextView;
        public TextView mCaloBurnTextView;
        public ImageButton mDeleteButton;

        public ExerciseViewHolder(@NonNull View itemView) {
            super(itemView);
            mNameTextView = itemView.findViewById(R.id.tv_NAME);
            mDurationTextView = itemView.findViewById(R.id.tv_DURATION);
            mCaloBurnTextView = itemView.findViewById(R.id.tv_CALOBURN);
            mDeleteButton = itemView.findViewById(R.id.btn_delete_exercise_inday);
        }
    }

    @NonNull
    @Override
    public ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_exercise_in_day, parent, false);
        return new ExerciseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseViewHolder holder, int position) {
        if (mCursor != null && mCursor.moveToPosition(position)) {
            String name = "";
            int duration = 0;
            float calorieBurn = 0.0f;
            int id = -1;

            int nameColumnIndex = mCursor.getColumnIndex("name");
            int durationColumnIndex = mCursor.getColumnIndex("duration");
            int calorieBurnColumnIndex = mCursor.getColumnIndex("calorie_burn");
            int idColumnIndex = mCursor.getColumnIndex("id");

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

            final int finalId = id;
            final String finalName = name;
            holder.mDeleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteExercise(finalId);
                }
            });



            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onItemClick(finalName);
                    }
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
                ExerciseDiaryHelper exerciseDiaryHelper = new ExerciseDiaryHelper(mContext);
                exerciseDiaryHelper.deleteExercise(id);
                swapCursor(exerciseDiaryHelper.getAllExercises());
                Toast.makeText(mContext, "Exercise deleted", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("No", null);
        builder.show();
    }


}
