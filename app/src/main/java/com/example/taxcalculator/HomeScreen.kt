package com.example.taxcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class HomeScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)
        val sharedPreference=UserSharedPreference(this)
        val logoutBtn:Button=findViewById(R.id.logoutBtn)
        val welcome:TextView=findViewById(R.id.welcome)
        val calculate:Button=findViewById(R.id.calculateTax)
        val clear:Button=findViewById(R.id.clear)

        clear.setOnClickListener {
            val salary:EditText=findViewById(R.id.salaryAmount)
            salary.text.clear()
        }
        welcome.text=getString(R.string.welcome,sharedPreference.getValue("username"))

        calculate.setOnClickListener {
            val salary:EditText=findViewById(R.id.salaryAmount)
            val amount=salary.text
            val taxAmount:TextView=findViewById(R.id.taxAmount)
            if (amount.isNotEmpty()){
                val tax= calculateTax(amount =amount.toString().toDouble())
                taxAmount.text=getString(R.string.tax_amount,amount,tax)
            }else{
                Toast.makeText(this,getString(R.string.salary),Toast.LENGTH_SHORT).show()
            }
        }

        logoutBtn.setOnClickListener {
            sharedPreference.clearPreference()
            finish()
        }
    }
    private fun calculateTax(amount:Double):Double{
        return when {
            amount>=1500000 -> {
                amount*0.3
            }
            amount<1500000 && amount>=1250000 -> {
                amount*0.25
            }
            amount<1250000 && amount>=1000000 -> {
                amount*0.2
            }
            amount<1000000 && amount>=750000 -> {
                amount*0.15
            }
            amount<750000 && amount>=500000 -> {
                amount*0.1
            }
            amount<500000 && amount>=250000 -> {
                amount*0.05
            }
            else -> {
                0.0
            }
        }
    }
}