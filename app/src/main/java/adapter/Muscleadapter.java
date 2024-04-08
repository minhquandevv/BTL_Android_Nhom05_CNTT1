package adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tlu.btlandroid.R;

public class Muscleadapter extends RecyclerView.Adapter<Muscleadapter.muscleViewHolder>{
    @NonNull
    @Override
    public muscleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull muscleViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class muscleViewHolder extends RecyclerView.ViewHolder{
        private TextView nameMuscle;
        private TextView number1;
        private TextView number2;


        public muscleViewHolder(@NonNull View itemView) {
            super(itemView);
            nameMuscle = itemView.findViewById(R.id.tvms1);
            number1 = itemView.findViewById(R.id.tvms2);
            number2 = itemView.findViewById(R.id.tvms3);

        }
    }
}
