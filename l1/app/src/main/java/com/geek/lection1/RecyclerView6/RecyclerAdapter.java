package com.geek.lection1.RecyclerView6;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geek.lection1.R;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{//наследуемся от адаптера и передаем ему свой класс вью холд
    private ArrayList<String> data;

    public RecyclerAdapter(ArrayList<String> data) {//конструктор для приема арлиста с данными
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {//возвращает наш вью холдер
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_layout,parent,false);//создаем ресайкл вью,передаем лейаут с элементами,контейнер вьюх(парент),всегда фолс(присоединение к активити)
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {//содержит вью холдер ,в котором текст вью для каждого элемента
        holder.textView.setText(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data==null?0:data.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{//класс который содержит в себе все элементы нашего списка
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.recyclerView);//находим элемент который будем отображать в списк
        }
    }
}
