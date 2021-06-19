package com.example.androidquizapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidquizapp.Model.History;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.RankTestyViewHolder> {
    private Context context;
    private List<History> historyList;

    HistoryAdapter(Context mCtx, List<History> rankList) {
        this.context = mCtx;
        this.historyList = rankList;
    }

    @NonNull
    @Override
    public RankTestyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_ranking, parent, false);
        return new RankTestyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.RankTestyViewHolder holder, int position) {
        History history = historyList.get(position);
        holder.txt_name.setText(history.getUser());
        holder.txt_score.setText("" + history.getScore());
        String level= "";
        if(history.getLevel().equals("1"))
        {
            level = "Easy";
        }else if(history.getLevel().equals("2"))
        {
            level = "Normal";
        }
        else
        {
            level = "Hard";
        }
        holder.txt_level.setText("" + level);
    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }

    class RankTestyViewHolder extends RecyclerView.ViewHolder {

        TextView txt_name, txt_score, txt_level;

        RankTestyViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_name = itemView.findViewById(R.id.txt_name);
            txt_score = itemView.findViewById(R.id.txt_score);
            txt_level = itemView.findViewById(R.id.txt_level);
        }
    }
}