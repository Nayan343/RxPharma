package com.ravi.rxpharma.retrofit;

import com.ravi.rxpharma.api.APIConstant;
import com.ravi.rxpharma.api.ApiInterface;
import com.ravi.rxpharma.model.McqModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class McqApiImplementer  {
    private static Retrofit retrofit = null;

    private static Retrofit getClient() {

        if (retrofit == null) {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(APIConstant.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            return retrofit;
        }
        return retrofit;
    }

    public static void getMcq(Callback<McqModel> cb){
        ApiInterface api = McqApiImplementer.getClient().create(ApiInterface.class);
        Call<McqModel> call = api.getMcq("string2");
        call.enqueue(cb);
    }
}
