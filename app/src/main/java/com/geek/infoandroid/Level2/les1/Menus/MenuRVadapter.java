package com.geek.infoandroid.Level2.les1.Menus;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geek.infoandroid.R;

import java.util.ArrayList;

public class MenuRVadapter extends RecyclerView.Adapter<MenuRVadapter.ViewHolder>{
    ArrayList<Integer> ar;
    MenuActivity menuActivity;

    public MenuRVadapter(ArrayList<Integer> ar, MenuActivity menuActivity) {
        if(ar != null) {
            this.ar = ar;
        } else {
            this.ar = new ArrayList<>();
        }
        this.menuActivity = menuActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.for_menu_rv,parent,false);//создаем ресайкл вью
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String text = "Элемент номер" + ar.get(position);
        holder.textView.setText(text);
        //этим методом можно прям в активити обозначить любое вью,которое нам нужно(делаем здесь для того чтоб можно было определить на какой именно элемент мы нажали)
        menuActivity.registerForContextMenu(holder.textView);//вызывается только из активити,устанавливает вью , на которое вызовется контекстное меню
        //menuActivity.unregisterForContextMenu(holder.textView);// снимаем с нее вызов контекстного меню
    }

    @Override
    public int getItemCount() {//получаем количество элементов
        return ar == null? 0: ar.size();
    }

    public void addItem() {
        int num =ar.size()+1;//добавляем элементы с номером начиная с 1
        ar.add(num);
        notifyItemInserted(ar.size()-1);//осуществляем вставку в этот элемент
    }

    public void editItem(int newNum) {
        if(ar.size() > 0) {//если размер массива больше чем 0 то
            int latestElement = ar.size() - 1;//сохраняем последний индекс списка
            ar.set(latestElement, newNum);//вставляем в последний элемент по индексу передаваемое число
            notifyItemChanged(latestElement);//осуществляем это в списке ,передаем тоже индекс
        }
    }

    public void removeElement() {
        if(ar.size() > 0) {//если размер листа больше чем 0
            int latestElement = ar.size() - 1;//сохраняем последний индекс
            ar.remove(latestElement);//удаляем последний элемент в листе по индексу
            notifyItemRemoved(latestElement);//удаляем его из ресайкл вью
        }
    }

    public void clearList() {
        ar.clear();//отчищаем список
        notifyDataSetChanged();//отчищаем список в рв
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView =itemView.findViewById(R.id.text_view_for_rv_menu);//определяем вью
        }
    }
}
