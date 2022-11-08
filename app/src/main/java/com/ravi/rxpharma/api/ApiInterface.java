package com.ravi.rxpharma.api;

import com.ravi.rxpharma.model.McqModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("getMcqs.php")
    Call<McqModel> getMcq(@Query("question") String answer);
}

