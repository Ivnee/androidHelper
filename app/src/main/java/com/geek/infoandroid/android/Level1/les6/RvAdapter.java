package com.geek.infoandroid.android.Level1.les6;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geek.infoandroid.R;

import java.util.ArrayList;
import java.util.List;

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.MyViewHolder> {

    interface MyOnClickListener{
        void onClick(int position);
    }

    private List<DataClass> dataList = new ArrayList<>();
    private MyOnClickListener listener;

    public void setOnMyClickListener(MyOnClickListener listener){
        this.listener = listener;
    }
    public void setDataList(List<DataClass> list) {
        dataList.clear();
        dataList.addAll(list);
        notifyDataSetChanged();//данные изменились
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(dataList.get(position),listener);


    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView text1;
        private TextView text2;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            text1 = itemView.findViewById(R.id.item1);
            text2 = itemView.findViewById(R.id.item2);

        }
        public void bind(DataClass dataClass, final MyOnClickListener listener) {
            text1.setText(String.valueOf(dataClass.temp));
            text2.setText(dataClass.name);
            text2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(getAdapterPosition());
                }
            });
            if(getAdapterPosition() % 2 == 0){
                itemView.setBackgroundColor(Color.BLACK);
            }else {
                itemView.setBackgroundColor(Color.GRAY);
            }
        }
    }
}
