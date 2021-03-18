package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class QuizActivity extends AppCompatActivity {

    String user_name;
    TextView questionNo;
    TextView questionField;
    JSONArray questions;
    JSONObject question;
    JSONArray options;
    int score;
    Button option1, option2, option3, option4;
    int index;
    int[] userAnswers = new int[6];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Intent intent = getIntent();
        user_name = intent.getStringExtra(UserActivity.USERNAME);
        questionField = findViewById(R.id.question);
        questionNo = findViewById(R.id.questionNo);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        index = 0;
        score = 0;
        try {
            questions = new JSONArray(getResources().getStringArray(R.array.questions));
            if (savedInstanceState != null) {
                this.index = savedInstanceState.getInt("INDEX");
                this.userAnswers = savedInstanceState.getIntArray("USER_ANSWERS");
            }
            setQuestion();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void nextQuestion(View view) throws JSONException {
        this.index = (this.index + 1) % questions.length();
        setQuestion();
    }

    public void prevQuestion(View view) throws JSONException {
        this.index = (this.index - 1 + questions.length()) % questions.length();
        setQuestion();
    }


    @SuppressLint("SetTextI18n")
    public void setQuestion() throws JSONException {
        question = new JSONObject(questions.getString(index));
        questionField.setText(question.getString("question"));
        options = question.getJSONArray("options");
        questionNo.setText("Question " + ((index + 1)));
        option1.setText(options.getString(0));
        option1.setTag(1);
        option2.setText(options.getString(1));
        option2.setTag(2);
        option3.setText(options.getString(2));
        option3.setTag(3);
        option4.setText(options.getString(3));
        option4.setTag(4);
        setWhiteAll();
        if (userAnswers[index] != 0) {
            if (userAnswers[index] == (int) option1.getTag())
                option1.setBackgroundColor(0xFFFFAB91);
            else if (userAnswers[index] == (int) option2.getTag())
                option2.setBackgroundColor(0xFFFFAB91);
            else if (userAnswers[index] == (int) option3.getTag())
                option3.setBackgroundColor(0xFFFFAB91);
            else if (userAnswers[index] == (int) option4.getTag())
                option4.setBackgroundColor(0xFFFFAB91);
        }
    }

    public void selectOption(View view) {
        setWhiteAll();
        userAnswers[index] = (int) view.getTag();
        view.setBackgroundColor(0xFFFFAB91);
    }

    public void setWhiteAll() {
        option1.setBackgroundColor(Color.WHITE);
        option2.setBackgroundColor(Color.WHITE);
        option3.setBackgroundColor(Color.WHITE);
        option4.setBackgroundColor(Color.WHITE);
    }

    public void submitQuiz(View view) throws JSONException {
        score = 0;
        for (int i = 0; i < userAnswers.length; i++) {
            if (userAnswers[i] == new JSONObject(questions.getString(i)).getInt("answer"))
                score++;
        }
        Intent intent = new Intent(this, ScoreActivity.class);
        intent.putExtra("User", user_name);
        intent.putExtra("Score", score);
        startActivity(intent);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putIntArray("USER_ANSWERS", userAnswers);
        outState.putInt("INDEX", index);
    }
}