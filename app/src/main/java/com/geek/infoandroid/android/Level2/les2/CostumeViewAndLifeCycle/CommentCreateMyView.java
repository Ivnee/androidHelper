package com.geek.infoandroid.android.Level2.les2.CostumeViewAndLifeCycle;

public class CommentCreateMyView {
    /*
  ATTRS
    1)Создаем в папке values макет с параметрами attrs.xml
        2)В нем прописываем параметры ,которые наша вьюха сможет принимать
            а)<declare-styleable name="CreateCostumeView"> ... <> создаем контейнер для параметров (name вроде должен быть по имени как имя класса )
            б)в ней прописываем стили <attr name="battery_color" format="color" /> цвет format="dimansion" это размер dp sp
            в)<attr name="level" format="integer" /> уровень заряда( в формате указывается типа данных которые принимает наш стиль)
  Activity
    1)Создаем класс и наследуем его от View или от любой другой вьюхи ,которой нам нужно воспользоваться
    2)импортируем 4 конструктора (если мы их не переопределим, не сможем добавить через хмл нашу вьюху)
    3)создаем прямоугольники с которых будем лепить вьюху
            а)RectF battery = new RectF(),принимает числа с плавающей точкой
            б)Rect level = new Rect(),целые числа,создают прямоугольники
            в)Paint standartColor= new Paint();покрасить батарею (в стандартный цвет)
            г)Paint pressedColor = new Paint();покрасить батарею(при нажатии)
    */
}
