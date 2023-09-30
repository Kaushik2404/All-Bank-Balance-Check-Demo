package com.example.allbalancecheckapp.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.example.allbalancecheckapp.R
import com.example.allbalancecheckapp.databinding.ActivityFdactivityBinding

class FDActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFdactivityBinding
    var FDorRD="FD"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityFdactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.RD.setOnClickListener {
            binding.RD.setBackgroundResource(R.drawable.back_layout_fd)
            binding.FD.setBackgroundResource(0)
            binding.fdTxtMonth.visibility=View.VISIBLE
            binding.fdMonth.visibility=View.VISIBLE
            FDorRD="RD"
        }
        binding.FD.setOnClickListener {
            binding.FD.setBackgroundResource(R.drawable.back_layout_fd)
            binding.RD.setBackgroundResource(0)
            binding.fdTxtMonth.visibility=View.GONE
            binding.fdMonth.visibility=View.GONE
            FDorRD="FD"
        }

        binding.btnCalculator.setOnClickListener {
            val amount=binding.fdAmount.text.toString().toLong()
            Log.d("TAG111","$amount")
            val intrest=binding.fdRate.text.toString().toInt()
            Log.d("TAG111","$intrest")
            val y=binding.fdYear.text.toString().toInt()
            Log.d("TAG111","$y")
            val month=binding.fdMonth.text.toString().toInt()
            if(FDorRD=="FD"){

                binding.Total.text=amount.toString()
                val maturityAmount=((amount*intrest)/100)*y+amount
                binding.MaturityAmount.text=maturityAmount.toString()
            }
            else if(FDorRD=="RD")
            {    var totalMonth=month+(y+12)
                var tex:Long=0
                var okAmount:Long=0
                for(i in 1..totalMonth){
                     okAmount=amount*i
                    val calculateTax:Int=(((okAmount*intrest)/100)*0.08334).toInt()
                    Log.d("TAG111","TAX Calculate tax $calculateTax")
                    tex=calculateTax.toLong()+tex

                }
                Log.d("TAG111","TAX AMOUNT $okAmount")
                Log.d("TAG111","TAX total $tex")
                binding.Total.text=okAmount.toString()
                binding.MaturityAmount.text=(okAmount+tex).toString()

            }


        }
        binding.toolbar.back.setOnClickListener {
            onBackPressed()
        }

    }
}