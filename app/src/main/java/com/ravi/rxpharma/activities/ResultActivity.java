package com.ravi.rxpharma.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ravi.rxpharma.R;
import com.ravi.rxpharma.adapter.ViewAnswerAdapter;
import com.ravi.rxpharma.model.DataItem;
import com.ravi.rxpharma.model.McqModel;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity implements View.OnClickListener {
    private McqModel mcqModel;
    private ArrayList<DataItem> arrayList = new ArrayList<>();
    private ProgressBar progressBar;
    private TextView tvPercent;
    private RelativeLayout answer, result;
    private Button retry, btnAnswer;
    private RecyclerView recyclerView;
    private ViewAnswerAdapter answerAdapter;
    private String data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        progressBar = findViewById(R.id.progressBar);
        tvPercent = findViewById(R.id.tvPercent);
        answer = findViewById(R.id.rlAnswer);
        result = findViewById(R.id.result);
        retry = findViewById(R.id.retry);
        retry.setOnClickListener(this);
        btnAnswer = findViewById(R.id.viewAnswer);
        btnAnswer.setOnClickListener(this);
        recyclerView = findViewById(R.id.recyclerView);

        data = getIntent().getStringExtra("data");
        Gson gson = new Gson();
        mcqModel = gson.fromJson(data, McqModel.class);
        arrayList = (ArrayList<DataItem>) mcqModel.getData();
        int mark = Integer.parseInt(mcqModel.getStatus());
        progressBar.setProgress(mark*10);
        tvPercent.setText(mark*10 + "%");

        answerAdapter = new ViewAnswerAdapter(this, arrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(answerAdapter);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.retry) {
            SharedPreferences preferences = getSharedPreferences("Rx_Pharma", MODE_PRIVATE);
            SharedPreferences.Editor myEditor = preferences.edit();
            myEditor.clear();
            myEditor.apply();
            startActivity(new Intent(ResultActivity.this, McqDetailActivity.class).putExtra("data", data));
            finish();
        } else if (view.getId() == R.id.viewAnswer) {
            result.setVisibility(View.GONE);
            answer.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences preferences = getSharedPreferences("Rx_Pharma", MODE_PRIVATE);
        SharedPreferences.Editor myEditor = preferences.edit();
        myEditor.clear();
        myEditor.apply();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        SharedPreferences preferences = getSharedPreferences("Rx_Pharma", MODE_PRIVATE);
        SharedPreferences.Editor myEditor = preferences.edit();
        myEditor.clear();
        myEditor.apply();
        startActivity(new Intent(ResultActivity.this, McqsActivity.class));
        finish();
    }
}