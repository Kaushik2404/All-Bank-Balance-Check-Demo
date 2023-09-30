package com.example.allbalancecheckapp.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.allbalancecheckapp.adapter.BankAdapter
import com.example.allbalancecheckapp.Model.BankDataItem
import com.example.allbalancecheckapp.StickyHeaderItemDecoration
import com.example.allbalancecheckapp.databinding.ActivityHomeBinding
import com.example.allbalancecheckapp.onIteamViewClick
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class HomeActivity : AppCompatActivity() {


    private var bankList:MutableList<BankDataItem> = mutableListOf()
    private lateinit var bankAdapter: BankAdapter

    lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)





        val jsonString = assets.open("bank_database.json")
            .bufferedReader()
            .use { it.readText() }

         bankList = Gson().fromJson(jsonString,object : TypeToken<MutableList<BankDataItem>>(){}.type)


        binding.recyclerView.layoutManager=LinearLayoutManager(this)
        binding.recyclerView.setHasFixedSize(true)
        bankAdapter= BankAdapter(applicationContext,bankList,object: onIteamViewClick {
            override fun onViewClick(pos: Int) {
                val prefs = PreferenceManager.getDefaultSharedPreferences(applicationContext)
                prefs.edit().putInt("POS",pos).commit()
               val intent=Intent(applicationContext, CategoryActivity::class.java)
                startActivity(intent)

            }

        })

        binding.recyclerView.adapter=bankAdapter

        binding.recyclerView.addItemDecoration(StickyHeaderItemDecoration(binding.recyclerView, bankAdapter as BankAdapter))



    }
}