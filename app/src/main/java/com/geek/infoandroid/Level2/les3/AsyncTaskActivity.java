package com.geek.infoandroid.Level2.les3;

import android.os.AsyncTask;

import androidx.appcompat.app.AppCompatActivity;

public class AsyncTaskActivity extends AppCompatActivity {//можно Integer,Integer,Integer и любую последовательность типов данных

    class MyAsyncTask extends AsyncTask<String,String,Integer>{//параметры асинг таска (№1)передается в doInBackground(массив),№2)onProgressUpdate(любой массив),№3) то что должен вернуть поток doInBackground() и что должно войти в onPostExecute()


        @Override
        protected void onPreExecute() {
            //перед выполнением кода делается этот метод(можно использозвать его для инициализации
        }
        //только этот метод явзяется обязательным
        @Override
        protected Integer doInBackground(String... strings) {//принимает массив строк(в этом методе выполняется код асинхронно, т.е. в бэкграунд потоке)
            //код здесь выполняется когда мы где угодно создаем
            //MyAsyncTask at = new MyAsyncTask() и вызываем у него метод at.execute(String... arr)
            //ar.cancel() - отменить выполнение
            for (int i = 0; i <100 ; i++) {
                publishProgress(String.valueOf(i),"любое кол-во строк(тут массив ловится)");//вызывает метод onProgressUpdate(если указать второй параметр АсинкТаск Integer ,можно публиковать интеджеры
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {//вызывается методом publishProgressв  doInBackground
            //можем публиковать наши данные ,например загрузку выполнения сделать и тд
        }

        @Override
        protected void onPostExecute(Integer integer) {//интеджер указывается в третьем аргументе асинк таска
            //здесь выполняется код после того как все завершилось(как дуИнБэкграунд сделал return чего-либо(3 аргумент АсинкТаска))
        }
    }
}
