package com.geek.infoandroid.android.IntentSingTonLifeCycle;

public class Singltone {
    static Singltone instance;//поле должно быть статичным
    public String nashiDannie;
    public int nashiDannieInt;

    public static Singltone getInstance(){//метод который дает единственный экземпляр этого класса(если его нет ,создает)
         if(instance == null){//если не создан
             instance = new Singltone();//то создаем
         }
         return instance;//если создан то отправляем
    }
    //как работать с данными
    //Singltone.getInstance().nashiDannie = "123";//сохранть данные в синглтон
    String str = Singltone.getInstance().nashiDannie;//достать данные
}
