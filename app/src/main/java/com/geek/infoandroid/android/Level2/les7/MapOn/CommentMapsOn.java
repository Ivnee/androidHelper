package com.geek.infoandroid.android.Level2.les7.MapOn;

public class CommentMapsOn {
/*
    1)заходим в гугле на android google maps sdk(вторая ссылка)
    2)нажимаем как получить ключ API
        а)нажимаем Откройте Google Cloud Platform Console.
        б)переходим в Maps SDK for Android и подключаем их
        в)переходим в учетные данные и там будет наш ключик(по саиту лучше потыкать,объяснили коряво,непонятно получится ли у меня так ключ получить)
    3)помещаем ключ к себе в приложение в манифест
        а)<meta-data
            android:name="com.google.android.geo.API_KEY"
            android:value="КЛЮЧ"/>
    4) заходим в Maps SDK For Android  версии ...
        а)ищем там строчку подключения(implementation 'com.google.android.gms:play-services-maps:17.0.0')
        б)и вставляем в гредл зависимости
    5)добавляем в нашу активити (любую видимо) фрагмент
        а)добавляем фрагмент
        б)указываем у него имя<fragment
                                android:id="@+id/map"
                                android:name="com.google.android.gms.maps.MapFragment" />

MANIFEST)в манифесте указываем пермишены(разрешения)
            а)    <uses-permission android:name="android.permission.INTERNET"/>//разрешение на использование интернета
            б)    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />//разрешение на запрос местоположения



    МейнАктивити implements OnMapReadyCallBack
    onCreate(){
    1)находим наш LocationManager loc = (LocationManager) getSystemService(LOCATION_SERVICE);
    2)находим наш фрагмент Fragment mapFrag = getFragmentManager.findFragmentById(R.id.map);
    3)затем у нашего mapFrag.getMapAsync(this);//передаем ему зис(это наш колбек,который мы имплементив в нашем классе,из котороего мы получаем метод onMapReady)
    }
    4)имплементим интерфейс OnMapReadyCallBack и переопределяем метод onMapReady(GoogleMap g)
            а)в этом методе мы получаем нашу карту
            б)Лучше объявить в самом верху GoogleMap myMap = null
            в)И в нашем методе OnMapReadt(GoogleMap g) присвоить myMap = g; чтоб был доступ во всем классе
    5)запрашиваем разрешения в нашем методе
            а) if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            б)ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 100);}в массив можно вставить сразу несколько пермишеннов ,сразу несколько запросить(100 это прост реквест код)
    6)переопределяем (уже не в методе а в нашем классе(в данном случае мейн)) метод onRequestPermissionsResult
            а)в нем приходит наш реквест код(100), наши разрешения и результаты
            б)    if(requestCode == 100) {если наш код 100 , то
                    boolean result = grantResults[0] == PackageManager.PERMISSION_GRANTED;//проверяем результат ,получили разрешение или нет
                    if(result) recreate();}если получили разрешение,пересоздаем и снова поподаем в наши методы,только теперь там все разрешения получени и мы идем дальше
    7)Что делаем в ветке else(if это проверка на пермишены,если все прошли ,то делаем else)
            а)myMap.setMyLocationEnable(true);//позволяем определять наше местоположение
            б)так же устанавливаем все что нам нужно по настройкам в myMap.getUoSettomgs.и делаем что нам нужно,например .setMyLocationButtonEnable(false)отключает кнопку найти меня
            в)если включим эту кнопку ,можем поставить на нее листенер
                в1)myMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
                         @Override
                        public boolean onMyLocationButtonClick() {
                            и выполняем нужный нам код в этом листенере
            г)mMap.getUiSettings().setZoomControlsEnabled(true); вроде разрешает контролировать зум



какие команды можно использовать для нашей myMap(что можно сетить на кнопки)
    1)виды карты
        а)myMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);//установит стандартный вид карты
        б)myMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);спутник
        в)mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);//типа нормального вида,но что-то еще вроде есть там,проверить можно будет
    2)команды для траффика ,пробки авто
        а)myMap.setTrafficEnabled(true);//будет показывать пробки
        б)myMap.isTrafficEnabled();возвращает тру или фолс в зависимости от того включены пробки на карте или нет
        в)можно сделать так myMap.setTrafficEnaled(!myMap.isTrafficEnabled())//при нажатии будет включать/выключать пробки
    3)как сделать чтоб нас нашло на камере
        а)final Location loc =locManage.getLastKnowLocation(LocationManager.PASSIVE_PROVIDER);//находим нашу локацию(можно через любой провайдер)
        б)if(loc!=null){LatLng myTarget = new LatLng(loc.getLatitude(),loc.getLongitude())};//если локация естьЮ то у нее находим широту и долготу и вставляем в латлнг
        в)if(myTarget != null){myMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myTarget,15F(*это какой зум,как приближать будедм*)))}//если получили таргет(то анимируем переход камеры на наше местороложение)
    4)поставить маркер
                     LatLng moscow = new LatLng(55.752830, 37.617257);//просто место указывааем(можно через геокодер сделать,чтоб по названию находило место нужное и ставило там маркекр
            mMap.addMarker(new MarkerOptions()//создаем маркер
                    .position(moscow)                   //указываем где он будет стоять
                    .snippet("Population: 4,137,400")   //текст (под названием будет )
                    .flat(true)                         //плоский (тру)
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_search_marker))//какой будет маркер(если не указывать будет дефоолтный)
                    .title("Marker in Moscow"));        //название
            moveCamera(moscow, 16F);
    5)рисовать линии 1:10 примерно,урок 7 поток 2 это у рыбалки(у власкина чет запутался)

*/
}
