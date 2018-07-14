package com.example.fucai4

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.act_web.*

/**
 * Created by 42224 on 2018/4/23.
 */
class WebActivity : Activity(){
    var url:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_web)
        web_title.setTitleTextView("网站")
        url="https://www.baidu.com/"
        web_title.setOnImageButClickListener { v: View? ->
            finish()
        }
        mWebView.setWebViewClient(object : WebViewClient() {
            //设置在webView点击打开的新网页在当前界面显示,而不跳转到新的浏览器中
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return true
            }
        })
        mWebView.getSettings().setJavaScriptEnabled(true)  //设置WebView属性,运行执行js脚本
        mWebView.loadUrl(url)
    }
}