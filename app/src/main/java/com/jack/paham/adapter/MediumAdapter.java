package com.jack.paham.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jack.paham.EasyActivity;
import com.jack.paham.R;
import com.jack.paham.medium.MediumActivity;
import com.jack.paham.model.Medium;

import java.util.ArrayList;

public class MediumAdapter extends RecyclerView.Adapter<MediumAdapter.MyViewHolder> {
    private Context mContext;
    public static ArrayList<Medium> data_Medium;

    public MediumAdapter(Context mContext, ArrayList<Medium> data_Medium) {
        this.mContext = mContext;
        MediumAdapter.data_Medium = data_Medium;
    }

    @NonNull
    @Override
    public MediumAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.img_grid, parent, false);
        MediumAdapter.MyViewHolder viewHolder = new MediumAdapter.MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MediumAdapter.MyViewHolder holder, final int position) {

        Medium medium = data_Medium.get(position);
        ImageView image = holder.imageView;
        image.setImageResource(medium.getImg());
        ImageView lock = holder.img_lock;
        int tScore = EasyActivity.score;
        int mScore = MediumActivity.passed;
        if (medium.getNum() < tScore || medium.getNum()<mScore || medium.getNum() < 42){
            lock.setVisibility(View.GONE);
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent dataQuiz = new Intent(context, MediumActivity.class);
                    dataQuiz.putExtra(MediumActivity.EXTRA_MEDIUM, data_Medium.get(position));
                    context.startActivity(dataQuiz);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return data_Medium.size();
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
