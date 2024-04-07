package adapter;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.tlu.btlandroid.Fragment.achievementFragment;
import com.tlu.btlandroid.Fragment.exerciseFragment;
import com.tlu.btlandroid.Fragment.informationFragment;
import com.tlu.btlandroid.Fragment.musicFragment;
import com.tlu.btlandroid.Fragment.workoutFragment;

public class Pageadapter extends FragmentStateAdapter {

    public Pageadapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            case 0:
                return new workoutFragment();


            case 1:
                return new exerciseFragment();

            case 2:
                return new achievementFragment();

            case 3:
                return new musicFragment();

            case 4:
                return new informationFragment();

            default:
                return new workoutFragment();
        }

    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
