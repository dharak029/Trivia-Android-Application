/*
* Assignment Homework 04
* Dharak Shah, Viranchi Deshpande
* Homework Group 09
* GetYTriviaQuestions.java
* */
package com.example.dharak029.gorup09_hw04;

import android.os.AsyncTask;
import android.view.View;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by dharak029 on 9/27/2017.
 */

public class GetTriviaQuestions extends AsyncTask<String,Integer,ArrayList<TriviaQuestions>> {
    MainActivity activity;

    public GetTriviaQuestions(MainActivity activity){
        this.activity = activity;
    }
    @Override
    protected ArrayList<TriviaQuestions> doInBackground(String... params) {

        BufferedReader br = null;
        try {
            publishProgress(25);
            URL url = new URL(params[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                publishProgress(50);
                br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = br.readLine();
                publishProgress(75);
                while (line != null) {
                    sb.append(line);
                    line = br.readLine();
                }
                ArrayList<TriviaQuestions> list = TriviaQuestionUtil.parseTriviaQuestion(sb.toString());
                publishProgress(100);
                return list;
            }

        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  null;
    }

        @Override
    protected void onPreExecute() {
        super.onPreExecute();
            activity.progressBar.setMax(100);
    }

    @Override
    protected void onPostExecute(ArrayList<TriviaQuestions> triviaQuestionses) {

        activity.setData(triviaQuestionses);
        activity.progressBar.setVisibility(View.GONE);
        activity.txtTriviaReady.setVisibility(View.VISIBLE);
        activity.btnStartTrivia.setEnabled(true);
        activity.imageView.setVisibility(View.VISIBLE);
        super.onPostExecute(triviaQuestionses);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        activity.progressBar.setProgress(values[0]);
    }
}
