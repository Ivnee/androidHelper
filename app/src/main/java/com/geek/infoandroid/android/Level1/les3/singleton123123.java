package com.geek.infoandroid.android.Level1.les3;

public final class singleton123123 {//делаем класс финальным,чтоб от него никто не мог наследоваться ,так как это синглтон
    static {
        instance = new singleton123123();//в статическом блоке инициализации создаем наш синглтон для того чтоб он был потокобезопасным
    }
    private static singleton123123 instance = null;//объявляем наш инстанс класс

    public static synchronized singleton123123 getInstance(){//делаем синхронайз для потокобезопастности(можно через монитор или через статический блок инициализации)
        if (instance == null){
            instance = new singleton123123();
        }
        return instance;
    }
    private int count = 0;
    public void incrementCount(){
        count++;
    }


}
