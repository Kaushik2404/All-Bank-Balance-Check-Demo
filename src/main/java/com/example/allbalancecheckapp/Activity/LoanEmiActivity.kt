package com.example.allbalancecheckapp.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.allbalancecheckapp.R
import com.example.allbalancecheckapp.databinding.ActivityLoanEmiBinding
import kotlin.math.pow

class LoanEmiActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoanEmiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoanEmiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.back.setOnClickListener {
            onBackPressed()
        }



        binding.CarBike.setOnClickListener {

            binding.CarBike.setBackgroundResource(R.drawable.back_layout_fd)
            binding.person.setBackgroundResource(R.drawable.back_layout_fd1)
            binding.Home.setBackgroundResource(R.drawable.back_layout_fd1)

            binding.EmiAmount.text="0"
            binding.NumberofEMI.text="0"
            binding.TotalInterest.text="0"
            binding.Total.text= "0"
        }
        binding.person.setOnClickListener {
            binding.person.setBackgroundResource(R.drawable.back_layout_fd)
            binding.CarBike.setBackgroundResource(R.drawable.back_layout_fd1)
            binding.Home.setBackgroundResource(R.drawable.back_layout_fd1)

            binding.EmiAmount.text="0"
            binding.NumberofEMI.text="0"
            binding.TotalInterest.text="0"
            binding.Total.text= "0"
        }
        binding.Home.setOnClickListener {
            binding.Home.setBackgroundResource(R.drawable.back_layout_fd)
            binding.CarBike.setBackgroundResource(R.drawable.back_layout_fd1)
            binding.person.setBackgroundResource(R.drawable.back_layout_fd1)

            binding.EmiAmount.text="0"
            binding.NumberofEMI.text="0"
            binding.TotalInterest.text="0"
            binding.Total.text= "0"
        }



        binding.btnSerch.setOnClickListener {

            val amount=binding.fdAmount.text.toString().toInt()
            val rate=binding.fdRate.text.toString().toDouble()
            val year=binding.fdYear.text.toString().toInt()
            val month=binding.fdMonth.text.toString().toInt()


            val duration = month + (year * 12)
            val monthlyIR = (rate / 100.0) / 12
            val firstPart = amount * monthlyIR
            val numerator = (1 + monthlyIR).pow(duration)
            val denominator = (1 + monthlyIR).pow(duration) - 1
            val fraction = numerator / denominator
            val emi = firstPart * fraction
            val totalPayment = emi * duration
            val totalInterest = totalPayment - amount

            binding.EmiAmount.text=emi.toInt().toString()
            binding.NumberofEMI.text=duration.toString()
            binding.TotalInterest.text=totalInterest.toInt().toString()
            binding.Total.text= totalPayment.toInt().toString()
        }





    }
}