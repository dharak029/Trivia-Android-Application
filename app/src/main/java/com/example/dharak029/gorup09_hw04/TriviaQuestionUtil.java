/*
* Assignment Homework 04
* Dharak Shah, Viranchi Deshpande
* Homework Group 09
* TriviaQuestionsUtil.java
* */
package com.example.dharak029.gorup09_hw04;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by dharak029 on 9/27/2017.
 */

public class TriviaQuestionUtil {

    public static ArrayList<TriviaQuestions> parseTriviaQuestion(String stream) throws JSONException{

        ArrayList<TriviaQuestions> triviaQuestionsArrayList = new ArrayList<TriviaQuestions>();
        JSONObject jsonObject = new JSONObject(stream);
        JSONArray jsonArray = jsonObject.getJSONArray("questions");

        for(int i=0;i<jsonArray.length();i++){

            JSONObject object = jsonArray.getJSONObject(i);
            TriviaQuestions triviaQuestions = TriviaQuestions.createTrivia(object);
            triviaQuestionsArrayList.add(triviaQuestions);
        }


        return triviaQuestionsArrayList;
    }



}
