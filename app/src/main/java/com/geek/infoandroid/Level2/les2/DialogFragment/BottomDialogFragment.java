package com.geek.infoandroid.Level2.les2.DialogFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.geek.infoandroid.R;
//уже делали такой в материал дезайн( диалог фрагмент снизу)
public class BottomDialogFragment extends BottomSheetDialogFragment {
private BottomDialogListener listener;
    static BottomDialogFragment newInstance() { return new BottomDialogFragment();}//незнаю зачем гетинстанс ,можно создавать объект как обычно я думаю(посмотреть в лекциях препода как все работает )

    void setDialogListener(BottomDialogListener listener){//можно сделать листернер через конструктор чтоб принимался
        this.listener = listener;//устанавливаем слушатель на наш диалог
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.botton_sheet_dialog_fragment,container,false);//создаем наш фрагмент
        setCancelable(false);//нельзя закрыть нажатием мимо и назад
        Button button = view.findViewById(R.id.button1);//находим вьюхи
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();//закрыть окно при нажатии(еще добавляем сюда код для выполнеия)
            }
        });
        return view;
    }
//как вызывается в активити или фрагменте
    BottomDialogListener createListener = new BottomDialogListener() {
    @Override
    public void onButtonClick() {
        System.out.println("делаем код и передаем этот интерфейс с переопределенным методом в наш setDialogListener");
    }
};
    //вызываем в активити или фрагменте наш диалог
    BottomDialogFragment dialog = BottomDialogFragment.newInstance();
    //dialog.setDialogListener(createListener);
    //dialog.show(getSupportFragmentManager(),"Dialog123);

}
