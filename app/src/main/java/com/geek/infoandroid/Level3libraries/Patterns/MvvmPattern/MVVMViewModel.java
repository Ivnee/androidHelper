package com.geek.infoandroid.Level3libraries.Patterns.MvvmPattern;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
//нужно подключить лайв дату
//1)ненужно заморачиваться как сохранить нашу вью модель 2)не нужно заморачиваться по поводу сохранения состояния после пересоздания экрана,библиотека сама отработает
public class MVVMViewModel extends ViewModel {//чтоб определить вью модель,обязательно нужно наследоватьс от абстрактного класса ViewModel(библиотечный класс)
    private Model model;
    //создаем лайф даты(по сути обервебл)
    MutableLiveData<Integer> seconds = new MutableLiveData<>();//на эти методы будет подписка в активити и внутри них описаны действия
    MutableLiveData<Integer> minutes = new MutableLiveData<>();//В дженерике указываем тип данных ,который будет отправляться командой minutes.setValue("данные")
    MutableLiveData<Integer> hours = new MutableLiveData<>();//затем подписываемся на эти 3 лайфдаты в нашем активити

    public MVVMViewModel(){
        model = new Model();//конструктор в котором создаем нашу новую модель для данных
    }

    public void incSec() {
        int newModelValue = calcNewModelValue(0);//расчитываем новое значение в методе calcNewModelLValue
        model.setElementValueAtIndex(0, newModelValue);//полученное значение сетим в модель( тоже число +1)
        seconds.setValue(newModelValue);//и устанавливаем значение для наших секунд из лайвДаты
    }

    public void incMin() {
        int newModelValue = calcNewModelValue(1);
        model.setElementValueAtIndex(1, newModelValue);
        minutes.setValue(newModelValue);//после того как произвели setValue , подписчик получает данные и обновляет их в методе observe
    }

    public void incHours() {
        int newModelValue = calcNewModelValue(2);
        model.setElementValueAtIndex(2, newModelValue);
        hours.setValue(newModelValue);
    }

    private int calcNewModelValue(int modelElementIndex) {
        int currentValue = model.getElementValueAtIndex(modelElementIndex);//получаем текущий элемент по индексу
        return currentValue + 1;//плюсуем к этому числу +1 и возвращаем
    }

}
