package com.ravi.rxpharma.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.ravi.rxpharma.R;
import com.ravi.rxpharma.activities.McqDetailActivity;
import com.ravi.rxpharma.model.McqModel;

import java.util.ArrayList;

public class QuestionSetAdapter extends RecyclerView.Adapter<QuestionSetAdapter.ViewHolder> {
    private ArrayList<McqModel> arrayList = new ArrayList<>();
    private Context context;

    public QuestionSetAdapter(ArrayList<McqModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.mcq_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvQuestion.setText(arrayList.get(holder.getAdapterPosition()).getStatus());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                McqModel mcqModel = new McqModel();
                mcqModel.setData(arrayList.get(holder.getAdapterPosition()).getData());
                mcqModel.setStatus(arrayList.get(holder.getAdapterPosition()).getStatus());
                Intent intent = new Intent(context, McqDetailActivity.class);
                Gson gson = new Gson();
                String data = gson.toJson(mcqModel);
                intent.putExtra("data", data);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvQuestion;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvQuestion = itemView.findViewById(R.id.tvQuestion);
        }
    }
}

