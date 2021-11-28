package com.geek.infoandroid.android.Level2.les2.DialogFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.geek.infoandroid.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

//окно по центру экрана
public class DialogFragment2 extends DialogFragment {
    Button button1;
    Button button2;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.botton_sheet_dialog_fragment,container,false);//раздуваем фрагмент как обычно
    }

    /*сам стиль который мы вставляем(он позволяет размерам задаваться самим,т е если у кнопки ширина матч парент,он будет широкий)
    <style name="AdlertDialogStyle" parent="android:Theme.Material.Dialog">
        <item name="android:windowMinWidthMajor">0dp</item>
        <item name="android:windowMinWidthMinor">0dp</item>
    </style>*/
    @Override
    public int getTheme() {//устанавливаем стили для этого диалогового окна
        return R.style.AdlertDialogStyle;//возвращаем тему с диалоговым окном
    }


    public void getDialog1(){

        final BottomSheetBehavior<FrameLayout> behavior = ((BottomSheetDialog)getDialog()).getBehavior();
        behavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                //методы в которых мы реагируем на состояние ботом шит диалога
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                //посмотреть методы бехевьера (там много всего по управлению диалогом)
                behavior.getPeekHeight();//получить максимальную высоту идалога
                behavior.setPeekHeight(1000,true);//установить максимальную высоту диалога
            }
        });

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        button1 = view.findViewById(R.id.button1);//находим наши вьюхи
        button2 = view.findViewById(R.id.button2);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();//закрываем окно диалог фрагмента
                Toast.makeText(getActivity(),"code",Toast.LENGTH_SHORT);
            }
        });
    }
    //как показать этот диалог
    //в нужной нам АКТИВИТИ или ФРАГМЕНТЕ пишем следующий код:
    //DialogFragment2 dialog = new DialogFragment2();(можно в диалог фрагменте устанавливать какие-нибудь парамерты через конструктор и передавать в качестве аргументов их
    //dialog.show(getSupportFragmentManager(), "какой-то таг"); вызывает окно

}
