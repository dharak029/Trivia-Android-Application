/*
* Assignment Homework 04
* Dharak Shah, Viranchi Deshpande
* Homework Group 09
* TriviaStats.java
* */
package com.example.dharak029.gorup09_hw04;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class TriviaStats extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia_stats);
        TextView txtPerccent = (TextView) findViewById(R.id.txtPercent);
        ProgressBar progressBar = (ProgressBar)findViewById(R.id.correctAnswerProgress);
        Button btnQuit = (Button)findViewById(R.id.btnQuit);
        Button btnTryAgain = (Button)findViewById(R.id.btnTryAgain);
        progressBar.setMax(100);
        progressBar.setProgress(0);

        if(getIntent().getExtras()!=null){
            int percent = (int)getIntent().getExtras().getDouble("correctAnswerPercent");
            txtPerccent.setText(percent+"%");
            progressBar.setProgress(percent);
        }

        btnQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TriviaStats.this,MainActivity.class);
                startActivity(intent);
            }
        });

        btnTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TriviaStats.this,Trivia.class);
                startActivity(intent);
            }
        });
    }
}
