package com.tlu.btlandroid.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.tlu.btlandroid.Activity_customer;
import com.tlu.btlandroid.Activity_fitness;
import com.tlu.btlandroid.LoginActivity;
import com.tlu.btlandroid.R;
import com.tlu.btlandroid.ActivityMetrics;

public class informationFragment extends Fragment {

    private View mView;
    private LinearLayout metrics, customer, fitness;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.informationfragment, container, false);
        initUi();
        initListener();
        //Hàmlogout chỉ được gọi khi nhấn vào Layout logout
        LinearLayout logout = mView.findViewById(R.id.layout_logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

        return mView;
    }
    private void logout() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("login", getActivity().MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isLoggedIn", false);
        editor.apply();
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    private void initListener() {
        metrics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActivityMetrics.class);
                startActivity(intent);
            }
        });
        customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Activity_customer.class);
                startActivity(intent);
            }
        });

        fitness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Activity_fitness.class);
                startActivity(intent);
            }
        });
    }

    private void initUi() {
        customer = mView.findViewById(R.id.layout_customer);
        metrics = mView.findViewById(R.id.layout_metrics);
        fitness = mView.findViewById(R.id.layout_fitness);
    }

}
