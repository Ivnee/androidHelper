package com.geek.infoandroid.Level2.les2.CostumeViewAndLifeCycle;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.geek.infoandroid.R;

public class CreateCostumeView extends View {
    private int batteryColor = Color.GRAY;// Цвет батареи
    private int levelColor = Color.GREEN;// Цвет уровня заряда
    private int levelPressedColor = Color.RED;// Цвет уровня заряда при нажатии +
    private int level = 100;//Уровень заряда батареи
    private RectF batteryBack = new RectF(); // Изображение батареи
    private Rect batteryLevel = new Rect(); // Изображение уровня заряда
    private Rect batteryHead = new Rect(); // Изображение головы батареи
    private Paint levelPressedPaint;// "Краска" уровня заряда при касании +
    private Paint batteryPaint;// "Краска" батареи
    private Paint levelPaint;// "Краска" заряда
    private boolean pressed;//нажата ли кнопка
    private OnClickListener listener; // клик листенер для того чтоб переопределять и устанавливать на него какой-то метод


    public CreateCostumeView(Context context) {//принимает просто контекст на вход
        //если переопределить только этот метод,то можно создавать будет только програмно
        super(context);
        init();
    }


    // Вызывается при добавлении элемента в макет // AttributeSet attrs - набор параметров, указанных в макете для этого элемента/
    public CreateCostumeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //если этот то сможем добавить в хмл
        initAttrs(context, attrs);
        init();
    }

    // Вызывается при добавлении элемента в макет с установленными стилями . attrs - набор параметров, указанных в макете для этого элемента
    // defStyleAttr - базовый установленный стиль
    public CreateCostumeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        //если этот метод,то сможем применить какой-то стиль к нашей вьюхе в хмл
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
        init();
    }

    // Вызывается при добавлении элемента в макет с установленными стилями
    // AttributeSet attrs - набор параметров, указанных в макете для этого элемента
    // int defStyleAttr - базовый установленный стиль
    // int defStyleRes - ресурс стиля, если он не был определен в предыдущем параметре
    public CreateCostumeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        //если этот метод,то еще сможем прменить ко всему прочему тему для нашего вью
        super(context, attrs, defStyleAttr, defStyleRes);
        initAttrs(context, attrs);
        init();
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CreateCostumeView, 0, 0);//вставляем все параметры вьюхи в тайпед аррай (дефолтное значение не используем 0,0)
        batteryColor = typedArray.getColor(R.styleable.CreateCostumeView_battery_color, Color.GRAY);//получаем цвет ,который устанавливаем в хмл для вьюхи app:battery_color=""
        levelColor = typedArray.getColor(R.styleable.CreateCostumeView_level_color, Color.GREEN);//получаем цвет уровня заряда
        levelPressedColor = typedArray.getColor(R.styleable.CreateCostumeView_level_pressed_color, Color.RED);//получаем цвет уровня заряда при нажатии
        level = typedArray.getInteger(R.styleable.CreateCostumeView_level, 100);//уровень заряда
        typedArray.recycle(); //массив боьлше не нужен (освобожадем память)
    }


    private void init() {//проинициализировали краски
        batteryPaint = new Paint();//создаем элемент для раскраски
        batteryPaint.setColor(batteryColor);//устанавливаем цвет которым будем красить
        batteryPaint.setStyle(Paint.Style.FILL);//стиль который закрашивает полносью(есть разные стили,которые по контуру только  и тд)
        //цвет заряда
        levelPaint = new Paint();
        levelPaint.setColor(levelColor);
        levelPaint.setStyle(Paint.Style.FILL);
        //цвет при нажатии
        levelPressedPaint = new Paint();
        levelPressedPaint.setColor(levelPressedColor);
        levelPressedPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {//устанавливаем размеры вьюх
        super.onSizeChanged(w, h, oldw, oldh);
        //w это ширина которую задаем в хмл  h это высота
        //getPuddingLeft() getPuddingRight получить отступы которые задаются в хмл
        batteryBack.set(10, 10, 100, 40);//размер задней части
        batteryHead.set(100, 20, 130, 30);
        batteryLevel.set(25, 25, 95, 35);
    }

    @Override
    protected void onDraw(Canvas canvas) {//конв это наш холст  размеры которогг мы обозначили в хмл width height
        super.onDraw(canvas);
        canvas.drawRoundRect(batteryBack, 10, 10, batteryPaint);//отрисовать прямоугольник c закруглением (1сама фигура,2скругление углов, 3скругление углов, 4пэинт красит батарею)
        if (pressed) {
            canvas.drawRect(batteryLevel, levelPressedPaint);//если нажата рисуем цвет красный
        } else {
            canvas.drawRect(batteryLevel, levelPaint);//если нет , зеленый
        }
        canvas.drawRect(batteryHead, batteryPaint);//рисуем головку батареи
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {//метод для отслеживания нажатия на вьюху(как на Button)
        int najatiei = event.getAction();//определяем нажата вьюха у нас или нет
        pressed = najatiei == MotionEvent.ACTION_DOWN;//если нажато то будет тру ,если нет то фолс
        if (pressed) {//если прессед то
            if (listener != null){//если установили на эту вью setOnClickListener, то
                listener.onClick(this);//вызываем метод onClick у него
            }
        }
        invalidate();//перерисовываем нашу вьюху,этот метод вызывает onDraw;
        return true;
    }

    @Override
    public void setOnClickListener(View.OnClickListener listener) {//переопределили метод сет он клик листенер,чтоб можно было его применять для нашей вью и устаавливать свой код
        this.listener = listener;//сетим листенер для нашего вью
    }
}
