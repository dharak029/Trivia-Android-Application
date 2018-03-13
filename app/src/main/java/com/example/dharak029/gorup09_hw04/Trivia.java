/*
* Assignment Homework 04
* Dharak Shah, Viranchi Deshpande
* Homework Group 09
* Trivia.java
* */
package com.example.dharak029.gorup09_hw04;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Trivia extends AppCompatActivity {

    TextView txtTimer;
    TextView txtQuestion;
    TextView txtQNO;
    ProgressBar progressBar;
    Button btnNext;
    Button btnQuit;
    int questionIndex=0;
    RadioGroup rg;
    int answerID=0;
    double correctAnswerPercent =0;
    int userAnswer = 0;
    int correctAnswerCount=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia);

        btnQuit = (Button)findViewById(R.id.btnQuit);
        btnNext = (Button)findViewById(R.id.btnNext);
        txtTimer = (TextView) findViewById(R.id.txtTimer);
        txtQuestion = (TextView)findViewById(R.id.txtQuestion);
        progressBar = (ProgressBar)findViewById(R.id.progressImage);
        txtQNO = (TextView)findViewById(R.id.txtQNO);
        final ArrayList<TriviaQuestions> listOfQuestion = MainActivity.triviaQuestionsArrayList;

        TriviaQuestions object = listOfQuestion.get(questionIndex);

        txtQuestion.setText(object.getQuestion());
        txtQNO.setText("Q"+(object.getId()+1));
        new getImage().execute(object.getImageURL());
        addRadioButtons(object.getChoices());
        answerID = object.getAnswer();

        rg = (RadioGroup) findViewById(R.id.radioGroup);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                userAnswer = checkedId;
            }
        });

        btnQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Trivia.this,MainActivity.class);
                startActivity(intent);
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionIndex++;
                if(answerID == userAnswer){
                    correctAnswerCount++;
                }
                ImageView img = (ImageView)findViewById(R.id.imgQuestion);
                if(questionIndex<listOfQuestion.size()) {

                    TriviaQuestions object = listOfQuestion.get(questionIndex);
                    txtQuestion.setText(object.getQuestion());
                    txtQNO.setText("Q" + (object.getId() + 1));
                    answerID = object.getAnswer();
                    ((ViewGroup) findViewById(R.id.radioGroup)).removeAllViews();
                    addRadioButtons(object.getChoices());
                    if(object.getImageURL()!="") {
                        img.setVisibility(View.INVISIBLE);
                        new getImage().execute(object.getImageURL());
                    }
                    else{
                        img.setVisibility(View.INVISIBLE);
                    }
                }
                else{
                    correctAnswerPercent = 100*correctAnswerCount/(listOfQuestion.size()-1);
                    Intent intent = new Intent(Trivia.this,TriviaStats.class);
                    intent.putExtra("correctAnswerPercent", correctAnswerPercent);
                    startActivity(intent);
                }
            }
        });
        new CountDownTimer(121000, 1000) {

            public void onTick(long millisUntilFinished) {
                txtTimer.setText("Time Left: " + millisUntilFinished / 1000+" seconds");
            }

            public void onFinish() {
                correctAnswerPercent = 100*correctAnswerCount/(listOfQuestion.size()-1);
                Intent intent = new Intent(Trivia.this,TriviaStats.class);
                intent.putExtra("correctAnswerPercent", correctAnswerPercent);
                startActivity(intent);
            }
        }.start();


    }

    public void addRadioButtons(ArrayList<String> choices) {

            RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup);
            rg.setOrientation(LinearLayout.VERTICAL);

            for (int i = 0; i < choices.size(); i++) {
                RadioButton rdbtn = new RadioButton(this);
                rdbtn.setId(1 + i);
                rdbtn.setText(choices.get(i));
                rg.addView(rdbtn);
            }
    }

    public class getImage extends AsyncTask<String,Integer,Bitmap> {

        @Override
        protected Bitmap doInBackground(String... params) {
            try{
                publishProgress(25);
                URL url = new URL(params[0]);
                HttpURLConnection con = (HttpURLConnection)url.openConnection();
                publishProgress(50);
                con.setRequestMethod("GET");
                publishProgress(75);
                Bitmap image = BitmapFactory.decodeStream(con.getInputStream());

                publishProgress(100);
                return image;
            }
            catch (Exception e){
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setMax(100);
            progressBar.setProgress(0);
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Bitmap s) {
            super.onPostExecute(s);
            if(s != null) {
                ImageView img = (ImageView) findViewById(R.id.imgQuestion);
                img.setImageBitmap(s);
                progressBar.setVisibility(View.GONE);
                img.setVisibility(View.VISIBLE);
            }
        }
    }
}


