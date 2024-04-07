package adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.tlu.btlandroid.R;

import java.util.List;

import untity.Fitness;

public class Fitnessadapter extends RecyclerView.Adapter<Fitnessadapter.FitnessViewHolder> {
    private List<Fitness> mListFitness;

    public Fitnessadapter(List<Fitness> mListFitness) {
        this.mListFitness = mListFitness;
    }

    @NonNull
    @Override
    public FitnessViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fitness, parent, false);
        return new FitnessViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FitnessViewHolder holder, int position) {
        Fitness fitness = mListFitness.get(position);
        if(fitness==null){
            return ;
        }
        holder.tvTitle.setText(fitness.getTitle());
        holder.tvFitness.setText(fitness.getName());
    }

    @Override
    public int getItemCount() {
        if(mListFitness!=null){
            return mListFitness.size();
        }
        return 0;
    }

    public class FitnessViewHolder extends RecyclerView.ViewHolder{
        private TextView tvTitle;
        private TextView tvFitness;

        public FitnessViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvFitness=itemView.findViewById(R.id.tv_fitness);
        }
    }
}
