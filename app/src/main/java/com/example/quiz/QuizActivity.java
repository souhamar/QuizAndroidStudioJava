package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Delayed;

public class QuizActivity extends AppCompatActivity {


    private TextView question;
    private TextView questions;

    private AppCompatButton option1, option2, option3, option4, btnNext;

    private List<QuestionsList> questionsLists;

    private int CQP = 0;
    private String SelectedOptionByUser = "";
    private Timer quizTimer;
  private int totalTimeInMins = 1;
  private int second = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

          final TextView timer = findViewById(R.id.timer);
        question = findViewById(R.id.question);
        questions = findViewById(R.id.questions);

        option1 = findViewById(R.id.btnOption1);
        option2 = findViewById(R.id.btnOption2);
        option3 = findViewById(R.id.btnOption3);
        option4 = findViewById(R.id.btnOption4);

        btnNext = findViewById(R.id.btnNext);

        questionsLists = Questions.getQuestions();

        questions.setText((CQP + 1) + "/" + questionsLists.size());
        question.setText(questionsLists.get(0).getQuestion());
        option1.setText(questionsLists.get(0).getOption1());
        option2.setText(questionsLists.get(0).getOption2());
        option3.setText(questionsLists.get(0).getOption3());
        option3.setText(questionsLists.get(0).getOption3());
        option4.setText(questionsLists.get(0).getOption4());

        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SelectedOptionByUser.isEmpty()) {
                    SelectedOptionByUser = option1.getText().toString();
                    option1.setBackgroundColor(Color.RED);
                    option1.setTextColor(Color.WHITE);
                    revealAnswer();
                    questionsLists.get(CQP).setUserSelectedAnswer(SelectedOptionByUser);
                }
            }
        });
        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SelectedOptionByUser.isEmpty()) {
                    SelectedOptionByUser = option2.getText().toString();
                    option2.setBackgroundColor(Color.RED);
                    option2.setTextColor(Color.WHITE);
                    getCorrectAnswers();
                    revealAnswer();
                    questionsLists.get(CQP).setUserSelectedAnswer(SelectedOptionByUser);
                }

            }
        });
        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SelectedOptionByUser.isEmpty()) {
                    SelectedOptionByUser = option3.getText().toString();
                    option3.setBackgroundColor(Color.RED);
                    option3.setTextColor(Color.WHITE);
                    revealAnswer();
                    questionsLists.get(CQP).setUserSelectedAnswer(SelectedOptionByUser);
                }
            }
        });
        option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SelectedOptionByUser.isEmpty()) {
                    SelectedOptionByUser = option4.getText().toString();
                    option4.setBackgroundColor(Color.RED);
                    option4.setTextColor(Color.WHITE);
                    revealAnswer();
                    questionsLists.get(CQP).setUserSelectedAnswer(SelectedOptionByUser);
                }
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SelectedOptionByUser.isEmpty()) {
                    Toast.makeText(QuizActivity.this, "you should select an option", Toast.LENGTH_LONG).show();
                } else {
                    changeQuestion();
                }
            }
        });

        startTimer(timer);

    }
    private void startTimer(TextView timerTextView){

       quizTimer  = new Timer();

        quizTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(second==0){
                    totalTimeInMins--;
                    second = 59;

                }
                else if (second==0 && totalTimeInMins==0){
                    quizTimer.purge();
                    quizTimer.cancel();
                    Toast.makeText(QuizActivity.this, "TimeOver",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(QuizActivity.this, Result.class);
                    intent.putExtra("correct",getCorrectAnswers());
                    intent.putExtra("incorrect",getIncorrectAnswers());
                    startActivity(intent);
                    finish();
                }
                else{
                    second--;
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                       String finalMinutes = String.valueOf(totalTimeInMins);
                        String finalSecond = String.valueOf(second);
                        if (finalMinutes.length()==1)
                        {
                            finalMinutes = "0"+finalMinutes;
                        }

                        if (finalSecond.length()==1)
                        {
                            finalSecond = "0"+finalMinutes;
                        }
                        timerTextView.setText(finalMinutes+":"+finalSecond);
                    }
                });
            }
        }, 1000, 1000);
    }
    private void changeQuestion() {
        CQP++;
        if ((CQP + 1) == questionsLists.size()) {
            btnNext.setText("Submit Quiz");
        }
        if (CQP < questionsLists.size()) {
            SelectedOptionByUser = "";
            option1.setBackgroundResource(R.drawable.answers_style);
            option1.setTextColor(Color.parseColor("#1F6BB8"));

            option2.setBackgroundResource(R.drawable.answers_style);
            option2.setTextColor(Color.parseColor("#1F6BB8"));

            option3.setBackgroundResource(R.drawable.answers_style);
            option3.setTextColor(Color.parseColor("#1F6BB8"));

            option4.setBackgroundResource(R.drawable.answers_style);
            option4.setTextColor(Color.parseColor("#1F6BB8"));

            questions.setText((CQP + 1) + "/" + questionsLists.size());
            question.setText(questionsLists.get(CQP).getQuestion());
            option1.setText(questionsLists.get(CQP).getOption1());
            option2.setText(questionsLists.get(CQP).getOption2());
            option3.setText(questionsLists.get(CQP).getOption3());
            option3.setText(questionsLists.get(CQP).getOption3());
            option4.setText(questionsLists.get(CQP).getOption4());

        } else {

            Intent intent = new Intent(QuizActivity.this, Result.class);
            intent.putExtra("correct", getCorrectAnswers());
            intent.putExtra("incorrect", getIncorrectAnswers());
            startActivity(intent);
            finish();
        }
    }



    private int getCorrectAnswers() {
        int correctAnswers = 0;

        for (int i = 0; i < questionsLists.size(); i++) {
            final String getUserSelectedAnswer = questionsLists.get(i).getUserSelectedAnswer();
            final String getAnswer = questionsLists.get(i).getAnswer();

            if (getUserSelectedAnswer.equals(getAnswer)) {
                correctAnswers++;
            }

        }
        return correctAnswers++;

    }

    private int getIncorrectAnswers() {
        int correctAnswers = 0;

        for (int i = 0; i < questionsLists.size(); i++) {
            final String getUserSelectedAnswer = questionsLists.get(i).getUserSelectedAnswer();
            final String getAnswer = questionsLists.get(i).getAnswer();
            if (!getUserSelectedAnswer.equals(getAnswer)) {
                correctAnswers++;

            }
        }
        return correctAnswers++;
    }

    /* public void score() {
             final String getUserSelectedAnswer = questionsLists.get(i).getUserSelectedAnswer();
             final String getAnswer = questionsLists.get(i).getAnswer();
             if (getUserSelectedAnswer.equals(getAnswer)) {
                 sr++;
                 score.setText("Score: " + sr);
             }
         }
     }*/
    private void revealAnswer ()
    {
        final String getAnswer = questionsLists.get(CQP).getAnswer();
        if (option1.getText().toString().equals(getAnswer)){
            option1.setBackgroundColor(Color.GREEN);
            option1.setTextColor(Color.WHITE);
        }
        else if(option2.getText().toString().equals(getAnswer)){
            option2.setBackgroundColor(Color.GREEN);
            option2.setTextColor(Color.WHITE);
        }
        else if(option3.getText().toString().equals(getAnswer)){
            option3.setBackgroundColor(Color.GREEN);
            option3.setTextColor(Color.WHITE);
        }
        else if(option4.getText().toString().equals(getAnswer)){
            option4.setBackgroundColor(Color.GREEN);
            option4.setTextColor(Color.WHITE);
        }
    }





}