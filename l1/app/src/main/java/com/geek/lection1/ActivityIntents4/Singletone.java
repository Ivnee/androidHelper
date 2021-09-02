package com.geek.lection1.ActivityIntents4;

class Singleton {
    private static Singleton instance = null;
    public String text = "0";//поле для сохранения данных и передачи между классами

    public static Singleton getInstance(){//создание единственного класса ,если он ещещ не создал и возврат его
        if(instance == null){
            instance = new Singleton();
        }
        return instance;
    }

    //как получать данный синглтон и данные из него??
    //Singleton.getInstance.text = "123" устанавливаем
    //Singleton.getInstance.text; получаем
}
