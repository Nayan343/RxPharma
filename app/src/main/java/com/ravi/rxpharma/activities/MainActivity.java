package com.ravi.rxpharma.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ravi.rxpharma.R;

public class MainActivity extends AppCompatActivity {
    private CardView cvMcq, cvPdf, cvYoutube;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cvMcq = findViewById(R.id.mcq);
        cvPdf = findViewById(R.id.pdf);
        cvYoutube = findViewById(R.id.youtube);

        cvMcq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, McqsActivity.class));
            }
        });
        cvPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, PdfActivity.class));
            }
        });
        cvYoutube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, YoutubePlayerActivity.class));
            }
        });
    }
}