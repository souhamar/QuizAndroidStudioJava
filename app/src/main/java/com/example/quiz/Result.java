package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Result extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);


        final AppCompatButton  startNewbtn = findViewById(R.id.startAgain);
        final TextView correctA = findViewById(R.id.correctA);
        final TextView incorrectA = findViewById(R.id.incorrectA);

        final int getCorrectA = getIntent().getIntExtra("correct",0);
        final int getIncorrectA = getIntent().getIntExtra("incorrect",0);

        correctA.setText("Correct Answers : "+String.valueOf(getCorrectA));
        incorrectA.setText("Wrong Answers : "+String.valueOf(getIncorrectA));


        startNewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Result.this, MainActivity.class));
                 finish();
            }
        });
    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(Result.this, MainActivity.class));
        finish();
    }
}