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
import com.jack.paham.model.Category;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder>{

    private Context context;
    public static ArrayList<Category> categoryArrayList;

    public CategoryAdapter() {
    }

    public CategoryAdapter(Context context, ArrayList<Category> categoryArrayList) {
        this.context = context;
        CategoryAdapter.categoryArrayList = categoryArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.img_grid, parent, false);
        CategoryAdapter.MyViewHolder viewHolder = new CategoryAdapter.MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        Category category = categoryArrayList.get(position);
        ImageView image = holder.imageView;
        image.setImageResource(category.getImg());
        ImageView lock = holder.img_lock;
        int tScore = EasyActivity.score;
        if (category.getNum() < tScore || category.getNum() < 2) {
            lock.setVisibility(View.GONE);
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent dataQuiz = new Intent(context, EasyActivity.class);
                    dataQuiz.putExtra(EasyActivity.EXTRA_QUIZ, categoryArrayList.get(position));
                    context.startActivity(dataQuiz);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return categoryArrayList.size();
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

