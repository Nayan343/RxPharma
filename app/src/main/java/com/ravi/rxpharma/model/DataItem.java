package com.ravi.rxpharma.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DataItem implements Serializable {

    @SerializedName("option_d")
    private String optionD;

    @SerializedName("option_b")
    private String optionB;

    @SerializedName("option_c")
    private String optionC;

    @SerializedName("answer")
    private String answer;

    @SerializedName("questions")
    private String questions;

    @SerializedName("id")
    private String id;

    @SerializedName("option_a")
    private String optionA;

    public void setOptionD(String optionD){
        this.optionD = optionD;
    }

    public String getOptionD(){
        return optionD;
    }

    public void setOptionB(String optionB){
        this.optionB = optionB;
    }

    public String getOptionB(){
        return optionB;
    }

    public void setOptionC(String optionC){
        this.optionC = optionC;
    }

    public String getOptionC(){
        return optionC;
    }

    public void setAnswer(String answer){
        this.answer = answer;
    }

    public String getAnswer(){
        return answer;
    }

    public void setQuestions(String questions){
        this.questions = questions;
    }

    public String getQuestions(){
        return questions;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return id;
    }

    public void setOptionA(String optionA){
        this.optionA = optionA;
    }

    public String getOptionA(){
        return optionA;
    }
}
