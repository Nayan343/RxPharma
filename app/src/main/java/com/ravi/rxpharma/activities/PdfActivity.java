package com.ravi.rxpharma.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnErrorListener;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.ravi.rxpharma.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class PdfActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);
        TextView tvPageNo = findViewById(R.id.tvPageNo);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView tvToolbar = findViewById(R.id.tvToolbar);
        tvToolbar.setText("PDF Viewer ");
        PDFView pdfView = findViewById(R.id.pdfView);
        try {
//            URL url = new URL("https://rxpharma.000webhostapp.com/19SOEIT11014.pdf");
            URL url = new URL("https://www.tutorialspoint.com/android/android_tutorial.pdf");
            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Please wait...");
            progressDialog.create();
            progressDialog.show();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                        InputStream inputStream = connection.getInputStream();
                        pdfView.fromStream(inputStream).onLoad(new OnLoadCompleteListener() {
                            @Override
                            public void loadComplete(int nbPages) {
                                progressDialog.dismiss();
                                Toast.makeText(PdfActivity.this, "PDF loaded", Toast.LENGTH_SHORT).show();
                            }
                        }).onError(new OnErrorListener() {
                            @Override
                            public void onError(Throwable t) {
                                progressDialog.dismiss();
                                Toast.makeText(PdfActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }).onPageChange(new OnPageChangeListener() {
                            public void onPageChanged(int page, int pageCount) {
                                tvPageNo.setText((page + 1) + "/" + pageCount );
                            }
                        }).load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }
}