package com.geek.infoandroid.Level2.les4.Picasso;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.geek.infoandroid.R;
import com.google.android.material.circularreveal.CircularRevealWidget;
import com.squareup.picasso.Picasso;

public class ActivityPicasso extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picasso);
        ImageView imageView = findViewById(R.id.image_view);
        Picasso.get().load("http://pngimg.com/uploads/sun/sun_PNG13451.png")//можем через Uri ,ссылка на картинку,загружаем
                //.transform()//делаем формку картинки через канвас,разобраться потом
                .rotate(90)//развернуть картинку
                .centerCrop()//обрезать по центру
                .placeholder(R.drawable.decorator_item)//отображать картинку пока не загружено нужное изображение
                .into(imageView);//в наш имедж вью

    }
}
