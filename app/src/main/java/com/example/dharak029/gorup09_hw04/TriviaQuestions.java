package com.example.dharak029.gorup09_hw04;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/*
* Assignment Homework 04
* Dharak Shah, Viranchi Deshpande
* Homework Group 09
* TriviaQuestions.java
* */

public class TriviaQuestions implements Serializable{

    String question,imageURL;
    int id,answer;
    ArrayList<String> choices;

    public static TriviaQuestions createTrivia(JSONObject js) throws JSONException {

        TriviaQuestions triviaQuestions = new TriviaQuestions();
        JSONObject choicesObject = js.getJSONObject("choices");
        JSONArray jsonArray = choicesObject.getJSONArray("choice");
        ArrayList<String> choices = new ArrayList<String>();

        for(int i=0;i<jsonArray.length();i++){
            String choice = ""+jsonArray.getString(i);
            choices.add(choice);
        }

        triviaQuestions.setQuestion(js.getString("text"));
        triviaQuestions.setId(js.getInt("id"));
        triviaQuestions.setChoices(choices);
        try {
            triviaQuestions.setImageURL(js.getString("image"));
        }
        catch (Exception e){
            triviaQuestions.setImageURL("");
        }
        triviaQuestions.setAnswer(choicesObject.getInt("answer"));

        return triviaQuestions;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public ArrayList<String> getChoices() {
        return choices;
    }

    public void setChoices(ArrayList<String> choices) {
        this.choices = choices;
    }
}
