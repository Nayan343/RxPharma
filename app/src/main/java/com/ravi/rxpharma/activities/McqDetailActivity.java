package com.ravi.rxpharma.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ravi.rxpharma.R;
import com.ravi.rxpharma.adapter.McqAdapter;
import com.ravi.rxpharma.model.DataItem;
import com.ravi.rxpharma.model.McqModel;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class McqDetailActivity extends AppCompatActivity {
    private McqAdapter mcqAdapter;
    private McqModel mcqModel;
    private ArrayList<DataItem> arrayList = new ArrayList<>();
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mcq_detail);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView tvToolbar = findViewById(R.id.tvToolbar);
        TextView tvTimer = findViewById(R.id.tvTimer);
        recyclerView = findViewById(R.id.recyclerView);

        try {
            if (getIntent().getExtras() != null) {
                String data = getIntent().getStringExtra("data");
                Gson gson = new Gson();
                mcqModel = gson.fromJson(data, McqModel.class);
                arrayList = (ArrayList<DataItem>) mcqModel.getData();
                tvToolbar.setText(mcqModel.getStatus());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        mcqAdapter = new McqAdapter(this, arrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(mcqAdapter);

        new CountDownTimer(600000, 1000) {
            @Override
            public void onTick(long l) {
                NumberFormat f = new DecimalFormat("00");

                long hour = (l / 3600000) % 24;

                long min = (l / 60000) % 60;

                long sec = (l / 1000) % 60;
                tvTimer.setText(f.format(hour) + ":" + f.format(min) + ":" + f.format(sec));
            }

            @Override
            public void onFinish() {
                tvTimer.setText("00:00:00");
                McqAdapter.showSubmitDialog("Time up, Please Submit the Quiz ?");
            }
        }.start();
    }
}