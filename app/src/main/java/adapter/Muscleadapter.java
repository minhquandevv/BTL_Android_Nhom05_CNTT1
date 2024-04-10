package adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.tlu.btlandroid.Activity_detail;
import com.tlu.btlandroid.R;

import java.util.List;

import untity.Muscle;

public class Muscleadapter extends RecyclerView.Adapter<Muscleadapter.muscleViewHolder>{
    private List<Muscle> mListMuscle;
    private Context mContext;
    private int[] imageIds = {R.drawable.abs, R.drawable.arms, R.drawable.backmuscle, R.drawable.buttocks, R.drawable.chest, R.drawable.hips, R.drawable.legs, R.drawable.shoulders};

    private int[] imageMus = {R.drawable.muscleabs, R.drawable.musclearms, R.drawable.muscleback, R.drawable.musclebuttocks, R.drawable.musclechest, R.drawable.musclehips, R.drawable.musclelegs, R.drawable.muscleshoulders};

//    public MuscleAdapter(List<Muscle> mListMuscle) {
//        this.mListMuscle = mListMuscle;
//    }


    public Muscleadapter(List<Muscle> mListMuscle) {
        this.mListMuscle = mListMuscle;

    }

    @NonNull
    @Override
    public muscleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_muscle,parent,false);
        return new muscleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull muscleViewHolder holder, int position) {
        final Muscle muscle = mListMuscle.get(position);
        if (muscle==null){
            return;
        }
        int imageIndex = position % imageIds.length;
        holder.imgMuscle.setImageResource(imageIds[imageIndex]);
        holder.nameMuscle.setText(muscle.getName());
        holder.number1.setText(muscle.getNumber1());
        holder.number2.setText(muscle.getNumber2());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Activity_detail.class);
                intent.putExtra("name",muscle.getName());
                intent.putExtra("imageId",imageMus[imageIndex]);
                intent.putExtra("info",muscle.getInformation());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mListMuscle!=null){
            return mListMuscle.size();
        }
        return 0;
    }

    public static class muscleViewHolder extends RecyclerView.ViewHolder{
        private TextView nameMuscle;
        private TextView number1;
        private TextView number2;
        private ImageView imgMuscle;
        private CardView layout;
        private TextView informationMuscle;


        public muscleViewHolder(@NonNull View itemView) {
            super(itemView);
            nameMuscle = itemView.findViewById(R.id.muslce_name);
            number1 = itemView.findViewById(R.id.muslce_number1);
            number2 = itemView.findViewById(R.id.muslce_number2);
            imgMuscle = itemView.findViewById(R.id.img1);
            layout = itemView.findViewById(R.id.layout_item_muscle);
            informationMuscle = itemView.findViewById(R.id.informationMuscle);
        }
    }
}
