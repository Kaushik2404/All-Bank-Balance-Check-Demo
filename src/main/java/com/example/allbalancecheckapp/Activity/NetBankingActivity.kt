package com.example.allbalancecheckapp.Activity

import android.os.Bundle
import android.preference.PreferenceManager
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.allbalancecheckapp.Model.BankDataItem
import com.example.allbalancecheckapp.R
import com.example.allbalancecheckapp.databinding.ActivityNetBankingBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class NetBankingActivity : AppCompatActivity() {
    lateinit var binding: ActivityNetBankingBinding
    private var bankList: MutableList<BankDataItem> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNetBankingBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.toolbar.back.setOnClickListener {
            onBackPressed()
        }




        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        val listPos = prefs.getInt("POS", -1)

        val jsonString = assets.open("bank_database.json")
            .bufferedReader()
            .use { it.readText() }

        bankList =
            Gson().fromJson(jsonString, object : TypeToken<MutableList<BankDataItem>>() {}.type)

        val netBankingUrl = bankList[listPos].url
        binding.webView.webViewClient = MyWebViewClient()
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.settings.loadWithOverviewMode = true
        binding.webView.settings.useWideViewPort = true
        binding.webView.settings.allowFileAccess = true
        binding.webView.settings.allowContentAccess = true
        binding.webView.settings.allowFileAccessFromFileURLs = true
        binding.webView.settings.allowUniversalAccessFromFileURLs = true
        binding.webView.settings.domStorageEnabled = true
        binding.webView.settings.savePassword = true
//        binding.webView.settings.userAgentString = userAgent
        binding.webView.loadUrl(netBankingUrl)
       binding.webView.settings.setSupportZoom(true)
    }

    override fun onBackPressed() {
        if (binding.webView.canGoBack())
            binding.webView.goBack()
        else
            super.onBackPressed()
    }

    inner class MyWebViewClient : WebViewClient() {
        @Deprecated("Deprecated in Java")
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return true
        }

        override fun onPageCommitVisible(view: WebView, url: String) {
            super.onPageCommitVisible(view, url)

        }
    }

}