package com.tlu.btlandroid.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tlu.btlandroid.R;

import java.util.ArrayList;
import java.util.List;

import adapter.Muscleadapter;
import adapter.Muscleadapter;
import untity.Muscle;

public class achievementFragment extends Fragment{

    private View mView;
    private RecyclerView rcv_muscle;
    private Muscleadapter mMuscleAdapter;
    private List<Muscle> mListMuscle;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.achievementfragment,container,false);
        initUi();
        getListUser();
        return mView;
    }

    private void getListUser() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("muscle");
        Context context = requireActivity().getApplicationContext();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Muscle muscle = dataSnapshot.getValue(Muscle.class);
                    mListMuscle.add(muscle);
                }
                mMuscleAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(context,"Lấy không thanh cong",Toast.LENGTH_SHORT).show();
            }
        });
//        myRef.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//                Muscle muscle = snapshot.getValue(Muscle.class);
//                if(muscle!=null){
//                    mListMuscle.add(muscle);
//                    mMuscleAdapter.notifyDataSetChanged();
//                }
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
    }

    private void initUi() {
        rcv_muscle = mView.findViewById(R.id.rcv_muscle);
        Context context = requireActivity().getApplicationContext();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcv_muscle.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL);
        rcv_muscle.addItemDecoration(dividerItemDecoration);
        mListMuscle = new ArrayList<>();
        mMuscleAdapter = new Muscleadapter(mListMuscle);
        rcv_muscle.setAdapter(mMuscleAdapter);
    }

}
