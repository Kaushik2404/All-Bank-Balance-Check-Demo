package com.example.allbalancecheckapp.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import com.example.allbalancecheckapp.R
import com.example.allbalancecheckapp.databinding.ActivityFindChallanBinding

class FindChallanActivity : AppCompatActivity() {
    lateinit var binding: ActivityFindChallanBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityFindChallanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.back.setOnClickListener {
            onBackPressed()
        }

        binding.webview.webViewClient = WebViewClient()
        binding.webview.loadUrl("https://echallan.parivahan.gov.in")
        binding.webview.settings.javaScriptEnabled = true
        binding.webview.settings.setSupportZoom(true)

    }
    override fun onBackPressed() {
        if (binding.webview.canGoBack())
            binding.webview.goBack()
        else
            super.onBackPressed()
    }

}