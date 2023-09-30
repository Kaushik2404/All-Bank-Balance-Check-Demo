package com.example.allbalancecheckapp.Activity

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.GridView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.allbalancecheckapp.Model.GridData
import com.example.allbalancecheckapp.R
import com.example.allbalancecheckapp.adapter.GridRVAdapter
import com.example.allbalancecheckapp.databinding.ActivityCategoryBinding
import com.example.allbalancecheckapp.onIteamViewClick


class CategoryActivity : AppCompatActivity() {
    lateinit var categoryGV: GridView
    private lateinit var CategoList: List<GridData>
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    var mSlideTest=false




    lateinit var binding: ActivityCategoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.share.setOnClickListener {
        val intent=Intent(Intent.ACTION_SEND)
            intent.type="text/plain"
            val shareBody="https://play.google.com/store/apps/details?id=com.mobileappxperts.allbankbalance.checkbankbalance"
            val shareSub="Your Subject here:"
            intent.putExtra(Intent.EXTRA_SUBJECT,shareSub)
            intent.putExtra(Intent.EXTRA_TEXT,shareBody)
            startActivity(Intent.createChooser(intent,"Share using"))
        }
        binding.myDrawerLayout.addDrawerListener( object:ActionBarDrawerToggle(this,binding.myDrawerLayout,0,0){
            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
                mSlideTest=true
            }

            override fun onDrawerClosed(drawerView: View) {
                super.onDrawerClosed(drawerView)
                mSlideTest=false
            }
        })

//        binding.myDrawerLayout.addDrawerListener(actionBarDrawerToggle)
//        actionBarDrawerToggle.syncState()
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.toolbar.back.setOnClickListener {
            if(mSlideTest){
                binding.myDrawerLayout.closeDrawer(Gravity.LEFT);
            }else{
                binding.myDrawerLayout.openDrawer(Gravity.LEFT);
            }
        }

        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        val listPos = prefs.getInt("POS", -1)

//        val listPos=intent.getIntExtra("POS",-1)
        categoryGV = findViewById(R.id.idGVcourses)
        CategoList = ArrayList<GridData>()
        addDataList()

        val GridAdapter = GridRVAdapter( CategoList, this@CategoryActivity, object:onIteamViewClick{
            override fun onViewClick(pos: Int) {
               if(pos==0){
                   val intent=Intent(applicationContext,BankBalanceActivity::class.java)
                   intent.putExtra("POSITION",listPos)
                   startActivity(intent)
               }
                if(pos==1){
                    val intent=Intent(applicationContext,LoanEmiActivity::class.java)
                    intent.putExtra("POSITION",listPos)
                    startActivity(intent)
                }
                if(pos==2){

                    val intent=Intent(applicationContext,NetBankingActivity::class.java)
                    intent.putExtra("POSITION",listPos)
                    Log.d("TAG111","netbanking  $listPos")
                    startActivity(intent)
                }
                if(pos==3){

                    val intent=Intent(applicationContext,RtoActivity::class.java)
                    intent.putExtra("POSITION",listPos)
                    Log.d("TAG111","rto  $listPos")
                    startActivity(intent)
                }
                if(pos==4){
                    val intent=Intent(applicationContext,FDActivity::class.java)
                    intent.putExtra("POSITION",listPos)
                    Log.d("TAG111","fd/rd  $listPos fd")
                    startActivity(intent)
                }

            }
        })

        categoryGV.adapter = GridAdapter

    }


    private fun addDataList() {
        CategoList = CategoList + GridData(
            R.drawable.background_category1,"Bank Balance",
            R.drawable.balance
        )
        CategoList = CategoList + GridData(
            R.drawable.background_category2,"Calculator",
            R.drawable.calculator
        )
        CategoList = CategoList + GridData(
            R.drawable.background_category3,"Net Banking",
            R.drawable.netbanking
        )
        CategoList = CategoList + GridData(
            R.drawable.background_category4,"RTO Vehicle Info",
            R.drawable.rto
        )
        CategoList = CategoList + GridData(R.drawable.background_category5,"FD / RD", R.drawable.fd)
    }
}