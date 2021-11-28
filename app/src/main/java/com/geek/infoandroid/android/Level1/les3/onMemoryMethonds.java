package com.geek.infoandroid.android.Level1.les3;

import androidx.appcompat.app.AppCompatActivity;

public class onMemoryMethonds extends AppCompatActivity {
    @Override
    public void onLowMemory() {//вызывается когда у устройства нехватка памяти
        super.onLowMemory();
        //в нем можно почистить разные коллекции ресурсы и тд ,если не хватает памяти(чтоб не сдохло наше приложежние)
    }

    @Override
    public void onTrimMemory(int level) {//вызывается тоже,когда устройство хочет почистить нашу память,но еще имеет левел(узнать про левел)
        super.onTrimMemory(level);
    }
}
