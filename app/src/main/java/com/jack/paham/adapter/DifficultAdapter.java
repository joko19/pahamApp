package com.jack.paham.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jack.paham.R;
import com.jack.paham.difficult.DifficultActivity;
import com.jack.paham.model.Difficult;

import java.util.ArrayList;

public class DifficultAdapter extends RecyclerView.Adapter<DifficultAdapter.MyViewHolder> {
    private Context context;
    public static ArrayList<Difficult> data_difficult;

    public DifficultAdapter(Context context, ArrayList<Difficult> data_difficult){
        this.context = context;
        DifficultAdapter.data_difficult = data_difficult;
    }

    @NonNull
    @Override
    public DifficultAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.img_grid, parent, false);
        DifficultAdapter.MyViewHolder viewHolder = new DifficultAdapter.MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DifficultAdapter.MyViewHolder holder, final int position) {

        Difficult difficult = data_difficult.get(position);
        ImageView image = holder.imageView;
        image.setImageResource(difficult.getImg());
        ImageView lock = holder.img_lock;
        int tScore = DifficultActivity.passed_diff;
        if (difficult.getNum()<92 || difficult.getNum()<tScore){
        lock.setVisibility(View.GONE);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent dataQuiz = new Intent(context, DifficultActivity.class);
                dataQuiz.putExtra(DifficultActivity.EXTRA_DIFFICULT, data_difficult.get(position));
                context.startActivity(dataQuiz);
            }
        });
        }
    }

    @Override
    public int getItemCount() {
        return data_difficult.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView, img_lock;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_grid);
            img_lock = itemView.findViewById(R.id.img_lock);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
