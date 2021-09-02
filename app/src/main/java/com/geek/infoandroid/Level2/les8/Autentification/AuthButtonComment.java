package com.geek.infoandroid.Level2.les8.Autentification;

public class AuthButtonComment {
    //0:55-1:05
    /*

2)РАБОРА В КОДЕ
    1)добавляем зависимость implementation 'com.google.android.gms:play-services-auth:17.0.0'
    2)в нужном нам фрагменте или активити объявляем GoogleSignInClient googleSignInClient
    3)находим гугл кнопку private com.google.android.gms.common.SignInButton buttonSignIn; и добавляем ее в XML--- SignInButton
    4)далее билдим GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();указали что будем запрашивать почту пользователя(чтоб потом можно было отобразить)
    5)и получаем сам клиент googleSignInClient = GoogleSignIn.getClient(this,gso)this-Context(передаем активити потому что после нажатия вызывается метод onActivityResult), gso-Options
    6)в клик листенере мы       а)  Intent signInIntent = googleSignInClient.getSignInIntent();получаем гугл интент(который откроет окно авторизации в гугл)
                                б)  startActivityForResult(signInIntent, реквест код инт); открываем его
    7)после авторизации вызывается он активити резалт
                а)проверяем наш if(requestCode == myRequestCode){..} реквест код приходит в активити резалт
                б)в if получаем наш аккаунт Tasl<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data) ...дата приходит в активити резалт
                в)получаем аккаут GoogleSignInAccoun acc = task.getResult();
                г)acc.getEmail(); //получаем почту пользователя
ЕСЛИ НУЖНО ВВЫЙТИ ИЗ ГУГЛ OAUTH
    googleSignInClient.signOut().addCompleteListener(()->{...Toast("Работа завершена")}) можно без листенера

1)РАБОТА НА САИТЕ ГУГЛ КОНСОЛЬ ДЕВЕЛОПЕРС
    1)Заходим в гугл developers консоль
    2)создаем проэкт(слева сверху)
    3) заходим в окно запроса доступа OAuth и вводим название приложения,нажимаем сохранить,этого достаточно
    4)нажимаем учетные данные->создать учетные данные-> идентификатор клиента OAuth
    5)заполняем тип приложения(андроид),имя (game228), название пакета(как называется наш проэкт(AndroidInfo)) и SHA-1 ключ
    6)получить sha-1 ключ (команда из методички) keytool -list -v -alias androiddebugkey -keystore %USERPROFILE%\.android\debug.keystore   пароль:android
    6)чтобы получить SHA-1 ключ (команда из гугла,не получилось),нужно в командной строке ввести (keytool -keystore path-to-debug-or-production-keystore -list -v)
*/}
