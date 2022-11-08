package com.ravi.rxpharma.adapter;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ravi.rxpharma.R;
import com.ravi.rxpharma.model.DataItem;

import java.util.ArrayList;

public class ViewAnswerAdapter extends RecyclerView.Adapter<ViewAnswerAdapter.ViewHolder> {
    private Context context;
    private ArrayList<DataItem> arrayList;

    public ViewAnswerAdapter(Context context, ArrayList<DataItem> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.mcq_answer, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int i = holder.getAdapterPosition();
        SharedPreferences preferences = context.getSharedPreferences("Rx_Pharma", MODE_PRIVATE);
        String save = preferences.getString(String.valueOf(i), "").trim();
        holder.tvQuestion.setText(i + 1 + ". " + arrayList.get(i).getQuestions());

        holder.tvA.setText(arrayList.get(i).getOptionA());
        if (!save.equals(holder.tvA.getText().toString().trim()) && !holder.tvA.getText().toString().trim().equals(arrayList.get(i).getAnswer().trim())) {
            holder.tvA.setTextColor(context.getResources().getColor(R.color.black));
        }else if (save.equals(holder.tvA.getText().toString().trim())) {
            holder.tvA.setTextColor(context.getResources().getColor(R.color.tale_red));
        }
        if (holder.tvA.getText().toString().trim().equals(arrayList.get(i).getAnswer().trim())) {
            holder.tvA.setTextColor(context.getResources().getColor(R.color.green));
        }

        holder.tvB.setText(arrayList.get(i).getOptionB());
        if (!save.equals(holder.tvB.getText().toString().trim()) && !holder.tvB.getText().toString().trim().equals(arrayList.get(i).getAnswer().trim())) {
            holder.tvB.setTextColor(context.getResources().getColor(R.color.black));
        }else if (save.equals(holder.tvB.getText().toString().trim())) {
            holder.tvB.setTextColor(context.getResources().getColor(R.color.tale_red));
        }
        if (holder.tvB.getText().toString().trim().equals(arrayList.get(i).getAnswer().trim())) {
            holder.tvB.setTextColor(context.getResources().getColor(R.color.green));
        }

        holder.tvC.setText(arrayList.get(i).getOptionC());
        if (!save.equals(holder.tvC.getText().toString().trim()) && !holder.tvC.getText().toString().trim().equals(arrayList.get(i).getAnswer().trim())) {
            holder.tvC.setTextColor(context.getResources().getColor(R.color.black));
        }else if (save.equals(holder.tvC.getText().toString().trim())) {
            holder.tvC.setTextColor(context.getResources().getColor(R.color.tale_red));
        }
        if (holder.tvC.getText().toString().trim().equals(arrayList.get(i).getAnswer().trim())) {
            holder.tvC.setTextColor(context.getResources().getColor(R.color.green));
        }

        holder.tvD.setText(arrayList.get(i).getOptionD());
        if (!save.equals(holder.tvD.getText().toString().trim()) && !holder.tvD.getText().toString().trim().equals(arrayList.get(i).getAnswer().trim())) {
            holder.tvD.setTextColor(context.getResources().getColor(R.color.black));
        }else if (save.equals(holder.tvD.getText().toString().trim())) {
            holder.tvD.setTextColor(context.getResources().getColor(R.color.tale_red));
        }
        if (holder.tvD.getText().toString().trim().equals(arrayList.get(i).getAnswer().trim())) {
            holder.tvD.setTextColor(context.getResources().getColor(R.color.green));
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvQuestion, tvA, tvB, tvC, tvD;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvQuestion = itemView.findViewById(R.id.tvQuestion);
            tvA = itemView.findViewById(R.id.tvA);
            tvB = itemView.findViewById(R.id.tvB);
            tvC = itemView.findViewById(R.id.tvC);
            tvD = itemView.findViewById(R.id.tvD);
        }
    }
}

