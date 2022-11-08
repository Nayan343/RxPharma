package com.ravi.rxpharma.utilities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.appcompat.app.AlertDialog;

public class Utility {
    public static ProgressDialog progressDialog;
    public static AlertDialog.Builder alertDialog;
    public static boolean isNetConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    public static void showProgressDialog(Context context) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Please Wait...");
        progressDialog.create();
        progressDialog.show();
    }

    public static void dismissProgressDialog(Context context) {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    public static void showAlertDialog(Context context, String message) {
        alertDialog = new AlertDialog.Builder(context);
        alertDialog.setMessage(message);
        alertDialog.setCancelable(false);
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alertDialog.create().show();
    }
}
