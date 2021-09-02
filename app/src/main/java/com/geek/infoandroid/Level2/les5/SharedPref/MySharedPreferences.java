package com.geek.infoandroid.Level2.les5.SharedPref;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
//ЕЩЕ ПО ЗАПИСИ ФАЙЛОВ ПОСМОТРЕТЬ В ЛЕКЦИЯХ ПО ШАРЕД ПРЕФЕРЕНСУ ОТ РЫБАЛКИ
//в любом месте где есть контекст можем обратиться к шеред преференсу
public class MySharedPreferences extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //1)вызываем преференс в активити(название файла шп будет как у нашего класса активити)
        SharedPreferences pref = getPreferences(MODE_PRIVATE);//стандартный метед получения преaеренса в активити(стандартный мод) и файл в который все сохранится будет называться так же как наш класс(например MainActivity)
        //2)в других местах где есть контекст(фрагменты,сервисы ...)(название указывается в аргументах)
        SharedPreferences preff = getSharedPreferences("preferenceName",MODE_PRIVATE);//получить преф в фрагментах и тд(имя файла преференса,стандартный мод)

        //положить данные
        SharedPreferences.Editor editor = pref.edit();//создаем добавлятель для шп
        editor.putString("Key","наши данные");//положить стринг в шп
        editor.putInt("key2",100);//положить инт в шп
        editor.commit();//сохраняем все что добавили сразу(говняга)
        editor.apply();//сохраняет все что добавили в бэкграунде(рекомендуется)
        //достать данные
        //так же определяем  шп через getPreferences или getSharedPreferences
        String stroka = pref.getString("key","default");//ключ по которому достаем данные и даннные ,если по этому ключу ничего нет
        int inta = pref.getInt("key2",1);//достаем по ключу, если данынх нет ставим 1



        //вызов шп в фрагменте
        final SharedPreferences defaultPrefs =
                PreferenceManager.getDefaultSharedPreferences(getContext());

        ((MainActivity)getActivity()).getPreferences(Context.MODE_PRIVATE);

        final SharedPreferences namedPrefs =
                getActivity().getSharedPreferences("named_pref", Context.MODE_PRIVATE);
    }
}
