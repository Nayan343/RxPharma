package com.ravi.rxpharma.adapter;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.ravi.rxpharma.R;
import com.ravi.rxpharma.activities.ResultActivity;
import com.ravi.rxpharma.model.DataItem;
import com.ravi.rxpharma.model.McqModel;

import java.util.ArrayList;

public class McqAdapter extends RecyclerView.Adapter<McqAdapter.ViewHolder> {
    private static Context context;
    private static ArrayList<DataItem> arrayList = new ArrayList<>();
    public int currentItem = 0;
    public static int correct = 0;

    public McqAdapter(Context context, ArrayList<DataItem> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.mcq, parent, false);
        return new  ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {

            SharedPreferences sharedPreferences = context.getSharedPreferences("Rx_Pharma",MODE_PRIVATE);
            String save = sharedPreferences.getString(String.valueOf(currentItem), "");
            Integer i = holder.getAdapterPosition();
            holder.itemView.setVisibility(View.GONE);
            holder.itemView.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
            if (i == currentItem) {
                holder.itemView.setVisibility(View.VISIBLE);
                holder.itemView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                String question = currentItem+1+". " + arrayList.get(i).getQuestions();
                holder.tvQuestion.setText(question);
                holder.rbA.setText(arrayList.get(i).getOptionA());
                if (save.equals(holder.rbA.getText().toString().trim())) {
                    holder.rbA.setChecked(true);
                }
                holder.rbB.setText(arrayList.get(i).getOptionB());
                if (save.equals(holder.rbB.getText().toString().trim())) {
                    holder.rbB.setChecked(true);
                }
                holder.rbC.setText(arrayList.get(i).getOptionC());
                if (save.equals(holder.rbC.getText().toString().trim())) {
                    holder.rbC.setChecked(true);
                }
                holder.rbD.setText(arrayList.get(i).getOptionD());
                if (save.equals(holder.rbD.getText().toString().trim())) {
                    holder.rbD.setChecked(true);
                }

                if (currentItem == 0) {
                    holder.btnPrevious.setVisibility(View.GONE);
                } else {
                    holder.btnPrevious.setVisibility(View.VISIBLE);

                }
                if (currentItem == 9) {
                    holder.btnNext.setVisibility(View.GONE);
                    holder.btnSubmit.setVisibility(View.VISIBLE);
                } else {
                    holder.btnNext.setVisibility(View.VISIBLE);
                    holder.btnSubmit.setVisibility(View.GONE);
                }

                holder.btnPrevious.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        correct--;
                        currentItem--;
                        notifyDataSetChanged();
                    }
                });
                holder.btnNext.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int selectedId = holder.radioGroup.getCheckedRadioButtonId();
                        if (selectedId == -1) {
                            currentItem++;
                            notifyDataSetChanged();
                        }
                        else {
                            RadioButton radioButton = (RadioButton) holder.radioGroup.findViewById(selectedId);
                            String answer = radioButton.getText().toString().trim();
                            String ans = arrayList.get(i).getAnswer().trim();

                            SharedPreferences preferences = context.getSharedPreferences("Rx_Pharma",MODE_PRIVATE);
                            SharedPreferences.Editor myEdit = preferences.edit();
                            myEdit.putString(String.valueOf(currentItem), answer);
                            myEdit.apply();

                            if (ans.equals(answer)) {
                                correct++;
                            }
                            currentItem++;
                            notifyDataSetChanged();
                        }
                    }
                });
                holder.btnSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int selectedId = holder.radioGroup.getCheckedRadioButtonId();
                        if (selectedId != -1) {
                            RadioButton radioButton = (RadioButton) holder.radioGroup.findViewById(selectedId);
                            String answer = radioButton.getText().toString().trim();
                            String ans = arrayList.get(i).getAnswer().trim();

                            SharedPreferences preferences = context.getSharedPreferences("Rx_Pharma", MODE_PRIVATE);
                            SharedPreferences.Editor myEdit = preferences.edit();
                            myEdit.putString(String.valueOf(currentItem), answer);
                            myEdit.apply();

                            if (ans.equals(answer)) {
                                correct++;
                            }
                        }
                        showSubmitDialog("Do you want to Submit the Quiz ?");
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvQuestion;
        RadioGroup radioGroup;
        RadioButton rbA, rbB, rbC, rbD;
        Button btnPrevious, btnNext, btnSubmit;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvQuestion = itemView.findViewById(R.id.tvQuestion);
            radioGroup = itemView.findViewById(R.id.radioGroup);
            rbA = itemView.findViewById(R.id.rbA);
            rbB = itemView.findViewById(R.id.rbB);
            rbC = itemView.findViewById(R.id.rbC);
            rbD = itemView.findViewById(R.id.rbD);
            btnNext = itemView.findViewById(R.id.btnNext);
            btnSubmit = itemView.findViewById(R.id.btnSubmit);
            btnPrevious = itemView.findViewById(R.id.btnPrevious);
        }
    }

    public static void showSubmitDialog(String message) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setMessage(message);
        alertDialog.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                McqModel mcqModel = new McqModel();
                mcqModel.setStatus(String.valueOf(correct));
                mcqModel.setData(arrayList);
                Gson gson = new Gson();
                String data = gson.toJson(mcqModel);
                context.startActivity(new Intent(context, ResultActivity.class).putExtra("data",data));
                ((Activity)context).finish();
            }
        });
        alertDialog.create().show();
    }
}
