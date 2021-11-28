package com.example.testcoroutine

import android.app.Activity
import android.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.geek.infoandroid.Kotlin.les7RetrofitOkHttpGlide.retrofitCoroutines.ViewModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ViewFlow : Fragment() {
    //как дождаться изменения размера вью или конца анимации

    //отслеживание изменений в тексте
    val editText: EditText = EditText(this)//здесь затычка(находим как обычно по айди)
    val flow = callbackFlow<String> {
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                s?.toString()?.let { trySend(it) }
            }
        }
        editText.addTextChangedListener(textWatcher)
        awaitClose { editText.removeTextChangedListener(textWatcher) }
    }.map { it + "поиск" }//добавляем уже к нему filter map и тд

    //Обработка изменения в тексте для вью модели
    class MainViewModel(val searchUseCase: SearchUserCase) : ViewModel() {
        private val _searchQuery =
            MutableStateFlow("")//флоу который будет отправлять текст запроса(и добавляем обработку любую)

        val searchResultFlow = _searchQuery.asStateFlow()
            .debounce(500)//перерыв между запросами будет 500
            .filter { it.length > 3 }//длина слова должна быть больше 3-х
            .mapLatest { query ->//для последнего значения мы делаем следущий код..
                searchUseCase.execute(query)//юзкейс для выполнения запроса в апи или рум
            }

        fun search(query: String) {//метод,который будет вызываться в активити(фрагменте) из листенера едит текста
            _searchQuery.value = query
        }
    }


    class MainActivity() : Activity() {
        val editText: EditText = EditText(this)//какой-то эдит текст(находить по айди)
        val viewModel =
            MainViewModel(SearchUserCase())//находим вью модель через viewModelProviders()

        fun initViews() {
            editText.addTextChangedListener { viewModel.search(it.toString()) }//под капотом это TextWatcher,котлиновская реализация
            MainScope().launch { viewModel.searchResultFlow.collect { it } }//а здесь мы подписались уже на флоу с доп операторами
        }
    }
}

//////////////
class SearchUserCase {
    fun execute(str: String) {
        //выполнить поиск
    }
}
