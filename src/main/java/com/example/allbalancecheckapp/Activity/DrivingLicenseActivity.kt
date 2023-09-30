package com.example.allbalancecheckapp.Activity

import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import com.example.allbalancecheckapp.R
import com.example.allbalancecheckapp.databinding.ActivityDrivingLicenseBinding

class DrivingLicenseActivity : AppCompatActivity() {
    lateinit var binding: ActivityDrivingLicenseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityDrivingLicenseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.back.setOnClickListener {
            onBackPressed()
        }

        binding.webview.webViewClient = WebViewClient()
        binding.webview.loadUrl("https://parivahan.gov.in")
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