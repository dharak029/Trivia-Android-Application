/*
* Assignment Homework 04
* Dharak Shah, Viranchi Deshpande
* Homework Group 09
* MainActivity.java
* */
package com.example.dharak029.gorup09_hw04;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.BufferedReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static ArrayList<TriviaQuestions> triviaQuestionsArrayList;
    ProgressBar progressBar;
    ImageView imageView;
    TextView txtTriviaReady;
    Button btnStartTrivia;
    Button btnExit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        imageView = (ImageView)findViewById(R.id.imageView);
        txtTriviaReady = (TextView)findViewById(R.id.txtTriviaReady);
        btnStartTrivia = (Button)findViewById(R.id.btnStart);
        btnStartTrivia.setEnabled(false);
        btnExit = (Button)findViewById(R.id.btnExit);

        new GetTriviaQuestions(MainActivity.this).execute("http://dev.theappsdr.com/apis/trivia_json/index.php");

        btnStartTrivia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,Trivia.class);
                startActivity(intent);

            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveTaskToBack(true);
                MainActivity.this.finish();            }
        });
    }

    public static ArrayList<TriviaQuestions> setData(ArrayList<TriviaQuestions> list){
        triviaQuestionsArrayList = list;
        return triviaQuestionsArrayList;
    }

}
