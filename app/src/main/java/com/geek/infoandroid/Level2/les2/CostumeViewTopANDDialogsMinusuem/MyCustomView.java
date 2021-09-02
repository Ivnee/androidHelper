package com.geek.infoandroid.Level2.les2.CostumeViewTopANDDialogsMinusuem;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.geek.infoandroid.R;

public class MyCustomView extends View {

    public MyCustomView(Context context) {//только програмно сможем создать
        super(context);
        initView(null);
    }

        public MyCustomView(Context context, @Nullable AttributeSet attrs) {//в хмл сможем создать
            super(context, attrs);
            initView(attrs);
        }

    public MyCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {//сможем добавить стиль
        super(context, attrs, defStyleAttr);
        initView(attrs);
    }

    public MyCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {//сможем добавить еще тему
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(attrs);
    }

    private Path path;
    private Paint paintPath;
    private Paint paint;
    private Rect rect;

    private int color;


    private void initView(AttributeSet attr){
        TypedArray typedArray = getContext().obtainStyledAttributes(attr, R.styleable.MyCustomView);//получаем атрибуты в наш тайпд эррей//стиль создаем в хмл стайл,и там указываем нужные нам атрибуты
        color = typedArray.getColor(R.styleable.MyCustomView_my_color,Color.RED);//путь к атрибуду указывается,название стиля ,который мы создали _ название атрибута

        typedArray.recycle();//освободить наш тайпед аррай,обязательно освобождаем

        paint = new Paint();
        paintPath = new Paint();
        paintPath.setStyle(Paint.Style.STROKE);//для рисования линией нужно выбирать строук и увеличить ширину через .setStrokeWidth
        paintPath.setStrokeWidth(20);
        paintPath.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);//fill -полностью stroke - снаружи fill and stroke = и то и то
        paint.setColor(color);//цвет рисования

        rect = new Rect();
        path = new Path();
    }


    public void channgeColor(){//изменить цвет нашей вью на серый
        paint.setColor(Color.GRAY);
        invalidate();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float pointX = event.getX();
        float pointY = event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN://когда нажали на вьюху
                Toast.makeText(getContext(),"Press d",Toast.LENGTH_SHORT).show();
                path.moveTo(pointX,pointY);//указываем откуда начинаем рисовать(стартовая точка при нажатии)
                break;
            case MotionEvent.ACTION_UP://когда мы отпустили
                Toast.makeText(getContext(),"Press up",Toast.LENGTH_SHORT).show();
                break;
            case MotionEvent.ACTION_MOVE://когда наводим курсор на наш элемент
                Toast.makeText(getContext(),"move",Toast.LENGTH_SHORT).show();
                path.lineTo(pointX,pointY);
                break;
            default:return false;}

            postInvalidate();//по мере возможность перерисовыывать вьюшку(когда процесс будет свободен)
            return true;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);//получаем мод вью,мб это врап контент или матч парент или цифрами задали
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
        //MeasureSpec.EXACTLY;//жестко задал значение размера
        //MeasureSpec.AT_MOST; //матч парент
        //MeasureSpec.UNSPECIFIED;//вроп контент

        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);//получаем сами размеры наших вьюх
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);

        int height = 0;//просто объявили переменную
        int width = 0;//просто

        if (modeWidth == MeasureSpec.EXACTLY){//если размер задан строго - то://сетим то,сколько указали (100dp)
            width = sizeWidth - getPaddingStart() - getPaddingEnd();//если указаны отступы справа и слева,иолучаем их и вычитаем из ширины
        }else if (modeWidth == MeasureSpec.AT_MOST){
            width = sizeWidth- getPaddingStart() - getPaddingEnd()  ;//сколько родитель нам отдал ,столько мы и засетим в ширину вьюхи
        }else if(modeWidth == MeasureSpec.UNSPECIFIED){//вроп контент,подгоняем к размеру 500
            width = 500;
        }
        // тоже самое для высоты
        if (modeHeight == MeasureSpec.EXACTLY){
            height = sizeHeight - getPaddingTop() - getPaddingBottom();//у высоты мы вычитаем верх и низ,для рассчета отступов
        }else if (modeHeight == MeasureSpec.AT_MOST){
            height = sizeHeight- getPaddingTop() - getPaddingBottom()  ;
        }else if(modeHeight == MeasureSpec.UNSPECIFIED){
            height = 200;
        }

        rect.set(0,0,width,height);
        setMeasuredDimension(width,height);//обязательно выызывать этот метод в он межа ,принимает ширину и высоту (иначе крашнется приложение)
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(rect,paint);//рисуем с помощью канваса ,даем фигуру и кисть
        canvas.drawPath(path,paintPath);
    }
}
