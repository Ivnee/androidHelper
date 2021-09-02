package com.geek.infoandroid.Level3libraries.Patterns.MOXYpattern;

import com.arellomobile.mvp.MvpView;

public interface MoxyExampleView extends MvpView {//интерфейс для работы с вьюхами(MvpView для того чтоб moxy аннотации смогли привязать вьюху)
    void setButtonText(int btnIndex, int value);
}
