package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class UserActivity extends AppCompatActivity {

    EditText userName;
    TextView errMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        userName = findViewById(R.id.user_name);
        errMsg = findViewById(R.id.err_msg);
    }

    public void startQuiz(View view) {
        String user_name = userName.getText().toString();
        if (user_name.length() <= 0) {
            errMsg.setText(R.string.invalid_user_msg);
            errMsg.setVisibility(View.VISIBLE);
        } else {
            errMsg.setVisibility(View.INVISIBLE);
        }
    }
}