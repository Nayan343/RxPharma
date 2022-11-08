package com.ravi.rxpharma.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class McqModel implements Serializable {


    @SerializedName("data")
    private List<DataItem> data;

    @SerializedName("status")
    private String status;

    public McqModel(List<DataItem> data, String status) {
        this.data = data;
        this.status = status;
    }

    public McqModel() {
    }

    public void setData(List<DataItem> data){
        this.data = data;
    }

    public List<DataItem> getData(){
        return data;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public String getStatus(){
        return status;
    }
}