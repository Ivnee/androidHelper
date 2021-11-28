package com.geek.infoandroid.android.MaterialDesign7;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.geek.infoandroid.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
//диалог который появляется снизу
public class DialogFragment extends BottomSheetDialogFragment {
    BottomSheetClickListener callBack;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.botton_sheet_dialog_fragment,container,false);//присоединяется как фрагмент 1 в 1
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button button = view.findViewById(R.id.button1);//находим так же кнопки
        button.setOnClickListener(new View.OnClickListener() {//также устанавливаем все листенеры и тд как и в обычном фрагменте
            @Override                       //а потом уже выхываем в активити этот фрагмент
            public void onClick(View view) {
                callBack.onButton1Click();//вызываем колбек у интерфейса
                callBack.onButton2Click();//вызываем метод коллбек 2 у интерфейса(реализуются нажатия кнопок в самом активити)
            }
        });
    }

    public BottomSheetClickListener getCallBack() {
        return callBack;
    }

    public void setCallBack(BottomSheetClickListener callBack) {
        this.callBack = callBack;
    }
}
//чтобы вызвать в АКТИВИТИ -> Fragment1 fragment1 = new Fragment1()  fragment1.show(getSupportFragmentManager,"Диалог1");