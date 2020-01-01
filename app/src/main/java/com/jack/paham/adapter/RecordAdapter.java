package com.jack.paham.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jack.paham.R;
import com.jack.paham.model.Record;

import java.util.List;

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.MyViewHolder> {

    private Context context;
    private List<Record> dataRecord;

    public RecordAdapter(Context context, List<Record> dataRecord) {
        this.context = context;
        this.dataRecord = dataRecord;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_record, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(v);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(dataRecord.get(position).getName());
        holder.address.setText("(" + dataRecord.get(position).getAddress() + ")");
        holder.score.setText(dataRecord.get(position).getScore());
        if (position%2 == 1)
            holder.rowRecord.setBackgroundResource(R.color.colorBlack);
    }

    @Override
    public int getItemCount() {
        return dataRecord.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView name, address, score, num;
        private ImageView bgScore;
        private LinearLayout rowRecord;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_name_record);
            address = itemView.findViewById(R.id.tv_address_record);
            score = itemView.findViewById(R.id.tv_score_record);
            rowRecord = itemView.findViewById(R.id.row_record);
        }
    }
}
