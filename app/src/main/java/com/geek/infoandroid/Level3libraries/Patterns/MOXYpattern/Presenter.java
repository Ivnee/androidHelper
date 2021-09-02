package com.geek.infoandroid.Level3libraries.Patterns.MOXYpattern;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.geek.infoandroid.R;

@InjectViewState//аннотация для того чтоб можно было заинжектить презентр в  нашей активити(в вьюхе)
public class Presenter extends MvpPresenter<MoxyMainActivity> {//экстенд класс библиотеки,в дженерик которого передаем нашу активити ,к которой инджектимся
//теперь ,у нас указан вMvpPresenter наш активити, все выделенно аннотациями и мокси сможеет связать наш активити и презентр
//теперь не нужно будет аттачить и детачить вью к презентеру,в презенторе ее сохранять,обнулять  и тд , теперь это делает мокси

    Model model;

    @Override//метод из мокси ,метод вызывается при первом присоединении вью
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        model = new Model();
    }

    @Override//метод из мокси
    public void attachView(MoxyMainActivity view) {//метод вызывается когда мокси присоединяет наше вью к презентору
        super.attachView(view);
    }

    @Override//метод из мокси
    public void detachView(MoxyMainActivity view) {//когда отсоединяет наше вью от презентора
        super.detachView(view);
    }

    public void buttonClick(final int btnId){//вызвали этот метод из нашей вьюхи(активити)
        int newModelValue;
        switch (btnId) {
            case R.id.moxy_btn1:
                newModelValue = calcNewModelValue(0);//увеличиваем значение для кнопки на 1
                model.setElementValueAtIndex(0, newModelValue);//добавляем это в модель
                getViewState().setButtonText(1, newModelValue);//отображаем на вью(вызываем метод из нашего вью и сетим в него аргументы)
                break;
            case R.id.moxy_btn2:
                newModelValue = calcNewModelValue(1);
                model.setElementValueAtIndex(1, newModelValue);
                getViewState().setButtonText(2, newModelValue);//getViewState() - обращается к нашей вьюе(активити) и у нее вызывает методы
                getViewState().setTTT();//можно вызвать любой (private не получится) метод с помощью getViewState()
                break;
            case R.id.moxy_btn3:
                newModelValue = calcNewModelValue(2);
                model.setElementValueAtIndex(2, newModelValue);
                getViewState().setButtonText(3, newModelValue);
                break;
        }

    }
    private int calcNewModelValue(int modelElementIndex) {//в методе прибавляем +1 к значению нажатой кнопки
        int currentValue = model.getElementValueAtIndex(modelElementIndex);
        return currentValue + 1;
    }
}