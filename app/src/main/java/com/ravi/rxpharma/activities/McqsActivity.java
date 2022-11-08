package com.ravi.rxpharma.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.ravi.rxpharma.R;
import com.ravi.rxpharma.adapter.QuestionSetAdapter;
import com.ravi.rxpharma.model.DataItem;
import com.ravi.rxpharma.model.McqModel;
import com.ravi.rxpharma.retrofit.McqApiImplementer;
import com.ravi.rxpharma.utilities.Utility;

import java.util.ArrayList;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class McqsActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView tvToolbar;
    private RecyclerView recyclerView;
    private ArrayList<DataItem> arrayList = new ArrayList<>();
    private QuestionSetAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mcqs);

        toolbar = findViewById(R.id.toolbar);
        tvToolbar = findViewById(R.id.tvToolbar);
        tvToolbar.setText("MCQ Question");
        setSupportActionBar(toolbar);

        getMcqData();

        recyclerView = findViewById(R.id.recyclerView);
    }

    private void getMcqData() {

        if (Utility.isNetConnected(this)) {
            Utility.showProgressDialog(this);
            McqApiImplementer.getMcq(new Callback<McqModel>() {
                @Override
                public void onResponse(Call<McqModel> call, Response<McqModel> response) {
                    if (response.code() == 200) {
                        McqModel mcqModel = response.body();
                        arrayList = (ArrayList<DataItem>) mcqModel.getData();
                        setRecyclerView();
                    } else {
                        Utility.showAlertDialog(McqsActivity.this,response.errorBody().toString());
                    }
                }

                @Override
                public void onFailure(Call<McqModel> call, Throwable t) {
                    Utility.dismissProgressDialog(McqsActivity.this);
                    if (t.getMessage() != null) {
                        t.printStackTrace();
                    }
                }
            });
        } else {
            Utility.showAlertDialog(this, "Please Connect to Internet");
        }
    }

    private void setRecyclerView() {
        adapter = new QuestionSetAdapter(getArrayList(), McqsActivity.this);
        Utility.dismissProgressDialog(McqsActivity.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(McqsActivity.this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }

    private ArrayList<McqModel> getArrayList() {
        ArrayList<McqModel> mcqModelArrayList = new ArrayList<>();

        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            ArrayList<DataItem> dataItems = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                int index = r.nextInt(15);
                dataItems.add(arrayList.get(index));
            }
            mcqModelArrayList.add(new McqModel(dataItems, "Question Set " + (i + 1)));
        }
        return mcqModelArrayList;
    }
}