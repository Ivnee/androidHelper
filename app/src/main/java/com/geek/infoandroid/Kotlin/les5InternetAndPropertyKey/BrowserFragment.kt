package com.example.myweather.ui.browser

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.webkit.WebView
import androidx.fragment.app.Fragment
import com.geek.infoandroid.R
import java.net.URL
import javax.net.ssl.HttpsURLConnection
import kotlin.concurrent.thread
//запрос для веб вью
class BrowserFragment(): Fragment(R.layout.fragment_browser) {

    lateinit var webView: WebView
    val handler = Handler(Looper.getMainLooper())
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        webView = view as WebView
        loadPage("https://ya.ru")
    }
    private fun loadPage(page:String){
        val url = URL(page)
        thread {
            val connection = url.openConnection() as HttpsURLConnection
            try {
                with(connection) {
                    requestMethod = "GET"
                    readTimeout = 10000
                    val pageText = inputStream.bufferedReader().readText()
                    handler.post{
                        webView.loadData(pageText, "text/html; charset=utf-8", "utf-8")
                    }
                }
            } catch (ex: Exception) {

            } finally {
                connection.disconnect()

            }
        }
    }
}