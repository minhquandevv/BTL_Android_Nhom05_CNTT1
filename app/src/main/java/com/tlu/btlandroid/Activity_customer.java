package com.tlu.btlandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Activity_customer extends AppCompatActivity {
    private EditText EditText_customer, Editext_name;
    private Button Button_customer;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        initUi();
        initListener();
    }
    private void initUi(){
        EditText_customer = findViewById(R.id.customer);
        Button_customer = findViewById(R.id.button_send);
        Editext_name = findViewById(R.id.feedback_username);
    }

    private void initListener() {
        Button_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("customer/feedback");
                String name = Editext_name.getText().toString().trim();
                String feedback = EditText_customer.getText().toString().trim();

                myRef.child(name).setValue(feedback, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                        Toast.makeText(Activity_customer.this, "Gửi phản hồi thành công", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
