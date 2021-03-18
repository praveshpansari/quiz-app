package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {

    TextView score;
    TextView user;
    TextView message;
    TextView scoreMessage;
    String user_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        Intent intent = getIntent();
        score = findViewById(R.id.scoreScore);
        user = findViewById(R.id.scoreUserName);
        message = findViewById(R.id.scoreTitle);
        scoreMessage = findViewById(R.id.scoreMsg);

        int scores = intent.getIntExtra("Score", 0);

        score.setText(Integer.toString(scores));

        user_name = intent.getStringExtra("User");
        user.setText(user_name);
        if (scores < 6) {
            message.setText(R.string.lostTitle);
            scoreMessage.setText(R.string.winMsg);
        } else {
            message.setText(R.string.winTitle);
            scoreMessage.setText(R.string.lostMsg);
        }
    }


    public void restartQuiz(View view) {
        Intent intent = new Intent(this, QuizActivity.class);
        intent.putExtra(UserActivity.USERNAME, user_name);
        startActivity(intent);
    }
}