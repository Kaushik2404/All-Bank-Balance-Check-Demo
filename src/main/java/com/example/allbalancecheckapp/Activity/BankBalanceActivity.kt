package com.example.allbalancecheckapp.Activity

import android.Manifest
import android.app.Instrumentation.ActivityResult
import android.content.ContentValues.TAG
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.allbalancecheckapp.Model.BankDataItem
import com.example.allbalancecheckapp.R
import com.example.allbalancecheckapp.databinding.ActivityBankBalanceBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.Exception


class BankBalanceActivity : AppCompatActivity() {
    private var bankList: MutableList<BankDataItem> = mutableListOf()


    lateinit var binding: ActivityBankBalanceBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBankBalanceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        val listPos = prefs.getInt("POS", -1)


        binding.toolbar.back.setOnClickListener {
            onBackPressed()
        }

        val jsonString = assets.open("bank_database.json")
            .bufferedReader()
            .use { it.readText() }

        bankList =
            Gson().fromJson(jsonString, object : TypeToken<MutableList<BankDataItem>>() {}.type)



        binding.BankBalance.setOnClickListener {
            val permission=Manifest.permission.CALL_PHONE
            permissionLancherSingle.launch(permission)
            // val phone_number = bankList[listPos].bankBalanceNo
            // Toast.makeText(applicationContext, phone_number.toString(), Toast.LENGTH_SHORT).show()
            checkpermission()
                Log.d("TAG111", "calling")
             val phone_number = bankList[listPos].bankBalanceNo.toString()

            if(phone_number!="0"){
                val phone_intent = Intent(Intent.ACTION_CALL)
                phone_intent.data = Uri.parse("tel:$phone_number")
                startActivity(phone_intent)
            }
            else{
               Toast.makeText(applicationContext, "This bank is not Provide Facility", Toast.LENGTH_SHORT).show()
            }

        }

        binding.BlockAtm.setOnClickListener {
            checkpermission()

                Log.d("TAG111", "Blocking")
                val phone_number = bankList[listPos].blockAtmCard.toString()
            if(phone_number!="0"){
                val phone_intent = Intent(Intent.ACTION_CALL)
                phone_intent.data = Uri.parse("tel:$phone_number")
                startActivity(phone_intent)
            }
            else{
                Toast.makeText(applicationContext, "This bank is not Provide Facility", Toast.LENGTH_SHORT).show()
            }



        }
        binding.MiniStatement.setOnClickListener {

            checkpermission()
                Log.d("TAG111", "Statement")
                val phone_number = bankList[listPos].miniStatement.toString()
            if(phone_number!="0"){
                val phone_intent = Intent(Intent.ACTION_CALL)
                phone_intent.data = Uri.parse("tel:$phone_number")
                startActivity(phone_intent)
            }
            else{
                Toast.makeText(applicationContext, "This bank is not Provide Facility", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun checkpermission() {
        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.CALL_PHONE)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE),101)
        }
    }
    private val permissionLancherSingle =registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ){isGranted ->
        Log.d(TAG,"permission :is Granted: $isGranted")
        if(isGranted){
            Toast.makeText(applicationContext, "permission apply", Toast.LENGTH_SHORT).show()
        }
        else{
            Log.d(TAG,"permission :is denied:")
            Toast.makeText(applicationContext, "permission denied", Toast.LENGTH_SHORT).show()
        }
    }

}