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
import com.jack.paham.model.Hard;

import java.util.ArrayList;

public class HardAdapter extends RecyclerView.Adapter<HardAdapter.MyViewHolder> {

    private Context hContext;
    public static ArrayList<Hard> dataHard;

    public HardAdapter(Context hContext, ArrayList<Hard> dataHard) {
        HardAdapter.dataHard = dataHard;
        this.hContext = hContext;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.img_grid, parent, false);
        HardAdapter.MyViewHolder viewHolder = new HardAdapter.MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        Hard hard= dataHard.get(position);
        ImageView image = holder.imageView;
        image.setImageResource(hard.getImg());
        ImageView lock = holder.img_lock;
        int mScore = HardActivity.passed_hard;
        if (hard.getNum()<mScore || hard.getNum() < 72){
            lock.setVisibility(View.GONE);
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent dataQuiz = new Intent(context, HardActivity.class);
                    dataQuiz.putExtra(HardActivity.EXTRA_HARD, dataHard.get(position));
                    context.startActivity(dataQuiz);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return dataHard.size();
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
