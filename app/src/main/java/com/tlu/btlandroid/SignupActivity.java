package com.tlu.btlandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import helpers.UserDatabaseHelper;


public class SignupActivity extends AppCompatActivity {
    TextInputEditText fullnameEditText, emailEditText, passwordEditText;
    Button signupButton;
    TextView loginView;
    UserDatabaseHelper userDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        fullnameEditText = findViewById(R.id.fullname);
        emailEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        signupButton = findViewById(R.id.signup);
        loginView = findViewById(R.id.loginView);

        userDatabaseHelper = new UserDatabaseHelper(this);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullname = fullnameEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                userDatabaseHelper.addUser(fullname, email, password);

                showToastAndFinish("Đăng ký thành công");
            }
        });

        loginView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this, com.tlu.btlandroid.LoginActivity.class));
            }
        });
    }

    private void showToastAndFinish(String message) {
        Toast.makeText(SignupActivity.this, message, Toast.LENGTH_SHORT).show();
        finish();
    }
}
