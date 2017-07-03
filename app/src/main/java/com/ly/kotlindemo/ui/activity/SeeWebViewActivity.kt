package com.ly.kotlindemo.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import com.ly.kotlindemo.R
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.verticalLayout
import org.jetbrains.anko.webView

/**
 * Created by Ly on 2017/6/23.
 */
class SeeWebViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var url = intent.getStringExtra("url")
        var desc = intent.getStringExtra("desc")
        verticalLayout {
            appBarLayout {
                val mToolBar = toolbar {
                }
                setSupportActionBar(mToolBar)
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
                supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_white_24dp)
                supportActionBar?.title = desc
            }
            webView {
                webChromeClient = WebChromeClient()
                webViewClient = WebViewClient()
                settings.javaScriptEnabled = true
                loadUrl(url)
            }
        }
    }
}