package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tlu.btlandroid.R;

import java.util.List;

import untity.day;

public class Dayadapter extends RecyclerView.Adapter<Dayadapter.DayViewHoler> {

    private Context mContext;
    private List<day> mListDay;

    public Dayadapter(Context mContext){
        this.mContext = mContext;
    }

    public void setData(List<day>days){
        this.mListDay=days;
        notifyDataSetChanged();
    }





    @NonNull
    @Override
    public Dayadapter.DayViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_day,parent,false);
        return new DayViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Dayadapter.DayViewHoler holder, int position) {
        day day = mListDay.get(position);
            if (mListDay==null){
                return;
            }
            holder.imgDay.setImageResource(day.getImg());
            holder.tvDay.setText(day.getName());

    }

    @Override
    public int getItemCount() {
        if(mListDay!=null){
            return mListDay.size();
        }
        return 0;
    }

    public class DayViewHoler extends RecyclerView.ViewHolder {
        private ImageView imgDay;
        private TextView tvDay;
        public DayViewHoler(@NonNull View itemView) {
            super(itemView);
            imgDay = itemView.findViewById(R.id.item_day);
            tvDay = itemView.findViewById(R.id.tv_name);
        }
    }
}
