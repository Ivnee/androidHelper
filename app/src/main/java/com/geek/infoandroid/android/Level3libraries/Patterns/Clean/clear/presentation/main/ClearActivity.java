package com.geekbrains.lesson1.clear.presentation.main;

import android.os.Bundle;


import com.geekbrains.lesson1.R;
import com.geekbrains.lesson1.clear.data.repository.ArticleRepositoryImpl;
import com.geekbrains.lesson1.clear.data.network.Api;
import com.geekbrains.lesson1.clear.data.network.RetrofitInit;
import com.geekbrains.lesson1.clear.domain.repository.ArticleRepository;
import com.geekbrains.lesson1.clear.domain.usecase.ArticleInteractor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
//https://startandroid.ru/ru/courses/architecture-components/27-course/architecture-components/524-urok-1.html
public class ClearActivity extends AppCompatActivity {

    private ArticleAdapter adapter;
    private ClearViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        initViewModel();
        initRecycleView();

        viewModel.articleLiveData.observe(this, data -> {
            adapter.setList(data);
        });
    }

    private void initViewModel() {
        Api api = RetrofitInit.newApiInstance();
        ArticleRepository repository = new ArticleRepositoryImpl(api);
        ArticleInteractor interactor = new ArticleInteractor(repository);

        viewModel = ViewModelProviders.of(this, new ClearViewModelFactory(interactor)).get(ClearViewModel.class);
        getLifecycle().addObserver(viewModel);//добавляем наблюдателя за нашим жизненным циклом
        //getLifecycle().removeObserver(viewModel);//метод для отписки обсервера
    }

    private void initRecycleView() {
        adapter = new ArticleAdapter();

        RecyclerView rv = findViewById(R.id.rv_articles);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
    }
}
