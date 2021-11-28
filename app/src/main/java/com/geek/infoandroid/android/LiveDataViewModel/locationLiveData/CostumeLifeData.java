package com.geek.infoandroid.android.LiveDataViewModel.locationLiveData;

import android.location.Location;

import androidx.lifecycle.LiveData;

public class CostumeLifeData extends LiveData<Location> {
    LocationService.LocationListener locationListener = new LocationService.LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            setValue(location);
        }
    };

    @Override
    protected void onActive() {
        LocationService.addListener(locationListener);
    }

    @Override
    protected void onInactive() {
        LocationService.removeListener(locationListener);
    }

    // LocationService - это просто какой-то сервис, который предоставляет нам текущую локацию. Его реализация
    // в данном примере не важна. Главное - это то, что мы подписываемся (addListener) на сервис, когда нам
    // нужны данные, и отписываемся (removeListener), когда данные больше не нужны.
}
