package com.example.allbalancecheckapp.Activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.allbalancecheckapp.databinding.ActivityRtoBinding


class RtoActivity : AppCompatActivity() {
    lateinit var binding: ActivityRtoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityRtoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.back.setOnClickListener {
            onBackPressed()
        }

        binding.Licence.setOnClickListener {
            val intent=Intent(applicationContext,DrivingLicenseActivity::class.java)
            startActivity(intent)
        }
        binding.Find.setOnClickListener {
            val intent=Intent(applicationContext,FindChallanActivity::class.java)
            startActivity(intent)
        }
        binding.btnSerch.setOnClickListener {
            val vehicleNumber=binding.edVehicleNumber.text.toString()
            val smsIntent = Intent(
                Intent.ACTION_SENDTO,
                Uri.parse("sms:+916351478024")
            )
            smsIntent.putExtra("sms_body", "VAHAN $vehicleNumber")
            startActivity(smsIntent)
        }


    }
    private fun checkpermission() {
        if(ActivityCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)!= PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this,Manifest.permission.RECEIVE_SMS)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.SEND_SMS),101)
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECEIVE_SMS),102)
        }
    }
}