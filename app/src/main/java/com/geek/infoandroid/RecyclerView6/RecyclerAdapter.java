package com.geek.infoandroid.RecyclerView6;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.geek.infoandroid.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {//насбеюуемся от адаптера библиотеки RecyclerView и указываем в дженерик свой вью холдер
    private ArrayList<String > data;//данные которые принимает наш вью холдер
    private ForClickOnRVitem forClickOnRVitem;

    public RecyclerAdapter(ArrayList<String> data,ForClickOnRVitem forClickOnRVitem) {//конструктор с которого получаем данные для списка
        this.data = data;//сетим полученный список в наш адаптер
        this.forClickOnRVitem = forClickOnRVitem;// интерфейс реализующий нажатие на список ресайкл вью
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {//возвращает наши холдеры который мы создали
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_items, parent,false);//раздуваем наш вью холдер,сетим макет,вьюхи(парент), и приатачивание всегда можно ставить фолс
        return new ViewHolder(view);//возвращаем наш вью холдер,который в конструкторе ,созданном нами , ждет эту вьюху
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {//приходит на вход наш вью холдер с вьюхами для каждого элемента
        String text= data.get(position);//получаем текст из массива который получили в конструкторе адаптера
        holder.textView.setText(text);//достаем элемент из нашего вью холдера в которйы сетим данные
        holder.imageView.setImageResource(R.drawable.ic_launcher_background);//установил по дефолту(лучше делать для нескольких элементов в листе класс контейнер)
        holder.textView.setOnClickListener(new View.OnClickListener() {//устанавливаем клик листенер на каждый элемент
            @Override
            public void onClick(View view) {
                forClickOnRVitem.onRVitemClick(data.get(position));//вызываем метод в мейн активити через интерфейс коллбэк
            }
        });
    }

    @Override
    public int getItemCount() {//метод который возвращает количество элементов
        return data==null? 0: data.size();//если дата нулл ,вернем ноль,если нет,вернем размер листа
    }
    void add (String newElement){
        data.add(newElement);//добавляем элемент в лист
        notifyItemInserted(data.size()-1);//добавить элемент в рв или data.size()-1 ВЕРНО.

        //notifyItemChanged(5);//указываем на какой позиции поменяли элемент и он обновит его в рв
    }
    void remove(){
        data.remove(0);//удалить элемент из листа
        notifyItemRemoved(0);//удалить элемент из рв
    }
    void move(){
        String str = data.get(1);//сохраняем строку под индексом 1
        data.remove(1);//удаляем строку под индексом 1
        data.add(0,str);//добавляем строку в индекс 0
        notifyItemMoved(1,0);//смена элементов в рв(индексыы элементов которые меняем)
    }
    void clearList(){
        data = new ArrayList<>();//обнуляем аррайлист
        notifyDataSetChanged();//полностью отчистить списко рв
    }

    class ViewHolder extends RecyclerView.ViewHolder {//созадем вью холдер
        ImageView imageView;
        TextView textView;//обьявляем здесь все вью которыйе засунем в каждую единицу списка рв
        public ViewHolder(@NonNull View itemView) {//обязательно делаем конструктор
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);//находим айдишники наших вьюх в конструкторе вью холдера
            textView = itemView.findViewById(R.id.text_view_for_rv_menu);
        }


    }
    ////////////////////////////////////////////////DIFFUTIL
    public void setData(List<String> toAdd) {
        NotesDiffutilCallback callback = new NotesDiffutilCallback(this.data, toAdd);
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(callback);

        this.data.clear();
        this.data.addAll(toAdd);

        result.dispatchUpdatesTo(this);
    }
    public static class NotesDiffutilCallback extends DiffUtil.Callback {

        private final List<String > oldList;
        private final List<String> newList;

        public NotesDiffutilCallback(List<String> oldList, List<String> newList) {
            this.oldList = oldList;
            this.newList = newList;
        }

        @Override
        public int getOldListSize() {
            return oldList.size();
        }

        @Override
        public int getNewListSize() {
            return newList.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
           return true;
            // return oldList.get(oldItemPosition).getUniqueTag().equals(newList.get(newItemPosition).getUniqueTag());
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
        }
    }



}
