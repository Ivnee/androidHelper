package com.geek.infoandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.geek.infoandroid.Fragments5.BusData;
import com.geek.infoandroid.Fragments5.EventBus;
import com.geek.infoandroid.Fragments5.Fragment1;
import com.geek.infoandroid.Fragments5.Fragment2;
import com.geek.infoandroid.IntentSingTonLifeCycle.IntentsActivity;
import com.geek.infoandroid.MaterialDesign7.DialogFragment;
import com.geek.infoandroid.RecyclerView6.RecyclerActivity;
import com.geek.infoandroid.RecyclerView6.RecyclerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_main);
//создавалки
        //return inflater.inflate(R.layout.fragment1,container,false);//создаем фрагмент или боттом щит фрагмент
  //Menu обычные
        //getMenuInflater().inflate(R.menu.top_menu,menu);//в методе он креейт опшнс мену ,для раздувания топ меню
        //return true;

  //RecyclerView в onCreateViewHolder
        //View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_items, parent,false);//раздуваем наш вью холдер,сетим макет,вьюхи(парент), и приатачивание всегда можно ставить фолс
        //return new ViewHolder(view);//возвращаем наш вью холдер,который в конструкторе ,созданном нами , ждет эту вьюху

    //popUpMenu
        //PopupMenu popupMenu = new PopupMenu(MenuActivity.this,view);//в качестве контекста передаем обязательно нашу активити
        //getMenuInflater().inflate(R.menu.top_menu,popupMenu.getMenu());//показываем наш попап меню,передаем в него макет того как наше меню выглядит и получаем меню у смого попапменю потому как  он принимает только меню

    //viewBinding
        //ActivityMainBinding binding = ActivityMainBinding.inflate(LayoutInflater.from(this));//подключаем наш биндинг
//вьюхи
   //текст вью
        textView = findViewById(R.id.firstTextView);//найти текст вью по айди
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });//установить слушатель на нажатие
        textView.getText().toString();//получить текст из вью(возвращает массив чаров)

        textView.setVisibility(View.VISIBLE);//видно вью
        textView.setVisibility(View.GONE);//убираем полностью с экрана
        textView.setVisibility(View.INVISIBLE);//делаем вью невидимой но оно все равно занимает место
        //1)Integer.parseInt(text)парсить стринг в интеджер //2)String.valueOf(text) парсить из числового в текст

    // имидж вью
        ImageView imageView = new ImageView(getBaseContext());//создаем картинку програмно
        imageView.setImageResource(R.drawable.ic_launcher_background);//устанавливаем картинку
   //FloatingActionButton

        FloatingActionButton fab = findViewById(R.id.fab); //определяем флоатинг экшн батон
        Snackbar.make(textView,"сообщение для снекбара, до этого засетили любой вью",Snackbar.LENGTH_SHORT).setAction("экшн(текс для нажатия)", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        }).show();

   //snackBar
        fab.setOnClickListener(new View.OnClickListener() {//клик клистерер для FloatinActionButton
            @Override
            public void onClick(View view) {//реализуем в онклик снекбар (требует на вход вьюху,которая его вызывает или вообще общий вью) вызывается только с активити на которой ты находишься
                Snackbar.make(view,"сообщение для снекбара",Snackbar.LENGTH_SHORT).setAction("экшн(текс для нажатия)", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {//клик листенер для нажатия на экшн
                        System.out.println("выполняем код по нажатию на экшн");
                    }
                }).show();//показать снекбар
            }
        });
    //WebView
        WebView webView = findViewById(R.id.web_view);//найти веб вью (маленький браузер у вас в приложении
        webView.loadUrl("https://geekbrains.ru/");//открыть в веб вью страничку

//лейауты
        LinearLayout layout = new LinearLayout(getBaseContext());//создаем лейаут программно
        layout.setOrientation(LinearLayout.VERTICAL);//установить ориентацию
        layout.addView(textView);//добавить какую-нибудь вью в лейаут
        Toast.makeText(getBaseContext(),"сообщение",Toast.LENGTH_SHORT).show();//вызвать тост

        TypedArray typedArray = getResources().obtainTypedArray(R.array.array);//принимает массив любого типа по айди(картинки ,цвета и тд)
//intents
//наша активити
        Intent intent = new Intent(getApplicationContext(), IntentsActivity.class);//куда мы хотим перейти (в активити IntentsActiviry)
        Intent intentBrowser = new Intent(Intent.ACTION_VIEW, Uri.parse("google.com"));//перейти на страничку гугл(любой сайт
        Intent openAppIntent = new Intent ( Intent.ACTION_VIEW, Uri.parse("ivan://ognev"));//интент для открытия аппа2(указать в манифесте аппа2 фильтр на прием этого ури,чтоб оно определяло данный интент)
        Intent smsIntent = new Intent(Intent.ACTION_SENDTO);//интент для отправки смс сообщений

        smsIntent.setData(Uri.parse("smsto:+79776599820"));//указываем кому будем отправлять смс
        smsIntent.putExtra("smsKey","сообщение");//кладем сообщение в интент(запускаем через startActivity())


        ActivityInfo activityInfo = openAppIntent.resolveActivityInfo(getPackageManager(),openAppIntent.getFlags());//есть такое приложение то вообще?
        if (activityInfo != null) startActivity(openAppIntent);//если это приложение вообще есть,то запускай

        intent.putExtra("ключ","14.06.1995");//кладем данные в интент
        startActivity(intent);//осуществляем переход
        startActivityForResult(intent,100);//запускаем активити на получение результата(100 - код ,по которому заберем данные потом)
        //onActivityResult();//вызывается при возврате на наше активити,возвращает данные ,нужный реквест код и тд,получаем данные в этом методе
//активити которую открыли
        Intent getIntent = getIntent();//получаем интент,который запустил эту активити(хоть интент между аппов,хоть интент между страниц,любой)
        String textFromIntent = getIntent.getStringExtra("ключ");//достаем данные стр,которые передали в данную страницу
        Bundle bundle = getIntent.getExtras();//или получаем все данный которые были в интенте в виде бандла
        Intent resultIntent = new Intent();//интент для возврата данных
        resultIntent.putExtra("ключ2", "сами данные");//кладем результат
        setResult(RESULT_OK,resultIntent);//отправляем результат обратно в нашу активити
        setResult(RESULT_CANCELED);//отменяем результат
        finish();//закрывает активити ,которую открыли интентом

//фрагменты
        //ottoBus
        EventBus.getBus().register(this);//подписываемся на получение событий из фрагментов и тд(в onStart())(принимает на вход объекты,нужно сделать класс для передачи данных)
        EventBus.getBus().unregister(this);//отписываемся от событий в (onStop())
        //@Subscribe //Подписываем метод данной аннотацией ,который будем вызывать из фрагмента (void metod(BusData busData))
        EventBus.getBus().post(new BusData());//вызываем подписанный метод и кидаем туда класс контейнер(до этого заполняем любыми данными его)
        //(register, unregister,post: @Subscribe)
        //без оттобаса вызывается все с помощью getActivity().doMethod
    //в активити
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();//получаем транзакцию для смены фрагментов в контейнерах

        transaction.replace(R.id.layoutForFragment,new Fragment1());//заменяем то что было в контейнере на фрагмент 2
        transaction.add(R.id.layoutForFragment,new Fragment1());//для пустого контейнера(вызывать без бэкстека,чтоб не возвращаться на пустой экран) добавляем фрагмент в контейнер
        transaction.remove(new Fragment1());//удалит данный фрагмент с экрана

        transaction.addToBackStack(null);//добавить совершенную транзакцию в бэкстек(вызывается перед коммитом)
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);//эффект с которым будет происходить транзакция фрагмента
        transaction.commit();//закомитить транзакцию
    //в фрагменте
        Bundle bundle2 = new Bundle();//создаем бандл для хранения данных
       // bundle.putSerializable("какой-то ключ",ClassContainer);//вставляем данные в бандл,должно быть серриалайзабл
        Fragment2 fragment2 = new Fragment2();
        fragment2.setArguments(bundle2);//установить данные в фрагмент
        fragment2.getArguments().getSerializable("какой то ключ ");//получаем сериализованный класс
        //FragmentTransaction ft = getFragmentManager().beginTransaction();//начать транзакцию фрагментов
        //ft.replace(R.id.layoutForFragment,new Fragment2());//заменяем фрагмент в контейнере
        //setReteinInstance(true);//отменяет вызов методов onDestroy onCreate   (данные не удаляются)

//BottomDialogFragment
        //создаем фрагмент (public class DialogFragment extends BottomSheetDialogFragment ) и раздуваем как обычный фрагмент
        //делаем коллбек интерфейс для работы этого фрагмента в активити
        //вызов этого фрагмента в активити
        DialogFragment dialogFragment = new DialogFragment();//создаем наш диалог фрагмент
        //dialogFragment.setCallBack(MainActivity.this);устанавливаем что нажатия кнопок будут реализовываться для этого актиивити и реализуем здесь кликлистенеры из интерфефйса с колбек методами
        dialogFragment.show(getSupportFragmentManager(),"текст который будет сверху");//вызываем у него метод шоу чтоб он показался
//RecyclerView
        RecyclerView recyclerView = null;
        ArrayList <String> data = new ArrayList<>();

        //1) контекст 2) колонки
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getBaseContext(),3);//менджер для рв(сетка,элементы делястя на 3 олонк
        //1) контекст 2) ориентация 3) перевернуть?(llm либо горизонтал либо вертикал)
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseContext(),LinearLayoutManager.VERTICAL,false);//создаем менеджер ,который будет выставлять элементы рв
        RecyclerAdapter adapter = new RecyclerAdapter(data, new RecyclerActivity());//создаем экземпляр адаптера который сами составляли//new просто чтоб красным небыло на вход дал класс рв
        // (class extends ReciclerView.Adapter<класс.внутренний класс вью холдер наш>)
        recyclerView.setAdapter(adapter);//назначаем адаптер
        recyclerView.setLayoutManager(linearLayoutManager);//назначаем лейаут менеджер

        //команды действий с элементами рв(RecyclerView)

        /*notifyItemInserted(data.size());//добавить элемент в рв
        notifyItemRemoved(0);//удалить элемент из рв
        notifyItemMoved(1,0);//смена элементов в рв(индексыы элементов которые меняем)
        notifyDataSetChanged();//полностью отчистить списко рв
        notifyItemChanged(5);//указываем на какой позиции поменяли элемент и он обновит его в рв
*/

//устанавливаем навигайшн вью (более подробно в пекедже
        BottomNavigationView navView = findViewById(R.id.nav_view);//находим навигейшн вью из макета

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.home).build();//айди в меню и в навигейшн у страниц должно быть одиноковыыми(указываем каке лейауты будут открываться на кнопки)
        //находим фрагмент ,который у нас является навигационным
        NavController navController = Navigation.findNavController(this,R.id.main_navigation_fragment);//находим наш фрагмент навигацию,в который все остальные фрагменты сетятся
        NavigationUI.setupActionBarWithNavController(this,navController,appBarConfiguration);//передаем наш активити,наш навигац фрагмент, и все фрагменты которые в макете навигаций
        NavigationUI.setupWithNavController(navView,navController);//все это прикручивается к BottomNavigationView


//clickListeners для вьюх
        //listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){...})листенер для списков,выдает позицию на которую мы нажали
        //textView.setOnClickListener(new View.OnClickListener() {...})//стандарнтый листенер ,в основном для кнопок
        //navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {new BottomNavigationView.OnNavigationItemSelectedListener()}//для навигейшн вью,при нажатии на элемент навигации срабатывает действие

        //textView.setOnFocusChangeListener(new View.OnFocusChangeListener() {...  //2 аргумента в нем(сама вью и hasFocus) если выбрана текст вью, фокус тру, если убрали фокус ,то фолс
        // TextView tv = (TextView) v});//кастим получаемый вью в текст вью,            используется для TextInputEditText из материал дезайна
        // потом tv.setError(massage) или отменяем tv.setError(null)
/*
        MenuItem search = menu.findItem(R.id.search);                       листенер для серч вью в опшнс меню,которая сверху
        final SearchView searchText = (SearchView) search.getActionView();
        searchText.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
        @Override
            public boolean onQueryTextSubmit(String query) {
                Snackbar.make(searchText,query,Snackbar.LENGTH_SHORT).show();
                return true;
            }*/                                                                      //листенер для серч вью и как он находится

        //popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener()//кликлистенер для попапмену
//Internet и потоки
        final Handler handler = new Handler();//класс который позволяет сохранть ссылку на тот поток в котором он был создан
/*
        HttpsURLConnection urlConnection = null;//создаем урл коннекшн
        URL url = null;
        try {
            url = new URL("https://google.com/");//создаем ссылку
            urlConnection = (HttpsURLConnection)url.openConnection();//открываем соединение ,создаем запрос
            urlConnection.setRequestMethod("GET");//устанавливаем метод получения данных для данной ссылки
            urlConnection.setReadTimeout(10000);//установка времени ожидания ответа(если за 10 сек не выполняется выводим ошибку)
            BufferedReader in =  new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));//создаем баффер ридер который будет получать поток из нашего соединения
*/

//или
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String dateText = simpleDateFormat.format(new Date(System.currentTimeMillis()));

        //
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        dateFormat.format(date(System.currentTimeMillis()));
        System.out.println(dateFormat.format(date));
        //или
        DateFormat dateFormat2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        System.out.println(dateFormat2.format(cal.getTime()));
//ресурсы
        //getResources().getConfiguration().orientation;//получить ориентацию экрана
    boolean isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;//конфигурация лендскейп??
    }
}
