package com.geek.infoandroid.Level2.les2.DialogFragment;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.geek.infoandroid.MaterialDesign7.DialogFragment;
//окно по центру экрана
public class DialogFragment1 extends DialogFragment {
    //такой же диалог фрагмент будет просто делается по другому(редкий вариант)
    //это алерт диалог по сути но выглядит он и работает как диалог фрагмент
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {//переопределяем он креейт диалог
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
               .setTitle("title").setMessage("msg");//создаем наш диалог фрагмент с помощью алерт диалога
        return builder.create();//возвращаем его
    }
    //вызывается так же в АКТИВИТИ или ФРАГМЕНТЕ как и диалог фрагмент 2(который создаем в ручную)
    //в активити создаем экземпляр DialogFragment1 d = new DialogFragment1();
    //d.show(getSupportFragmentManager(),"dialogBuilder tag"
}
