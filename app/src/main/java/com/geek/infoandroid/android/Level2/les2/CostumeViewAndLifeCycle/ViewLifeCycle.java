package com.geek.infoandroid.android.Level2.les2.CostumeViewAndLifeCycle;

public class ViewLifeCycle {
/*
1)Конструктор самой вьюхи( с него начинается создание любого объекта в джава)(КОНСТРУКТОРОВ НЕСКОЛЬКО ВЫЗЫВАЕТСЯ(ТИПА ДЛЯ ХМЛ ,ДЛЯ ПРОГРАМНОГО СОЗДАНИЯ,В ЗАВИСИМОСТИ ОТ ТОГО ,КАК МЫ СОЗДАЕМ))
2)requestLayout() будет вызван когда нужно будет наш элемент прикрепить к лейауту(когда происходят изменения,передвигаем или уменьшаем ,то вызываем этот метод)(типа как onResume();)
3)onAttachToWindow() вызывается при прикреплении вьюхи к своему окну(можно будет узнать о соседних вьюхах и сослаться на них)(еще есть он детач)
4)onMeasure() в этом методе можем определить высоту и ширину нашего элемента(обязательно setMeasuredDimension();в него передаем ширину и высоту , иначе приложение крашнется)
или onSizeChanged() // меняет размер вьюхи тоже
5)onLayout() вызывается когда элемент прекрепляется к своему лейауту
6)onDraw() рисует наш элемент
7)invalidate()(вызывается в любом месте) если вызываем этот метод то onDraw перерисовывает наш элемент (если сменили бэкграунд цвет например)requestLayout() полностю пересоздаст нашу вьюху(если нуэно сменить расположение или размер вьюхи например),но это дольше и больше жрет системых ресурсов
 */
}