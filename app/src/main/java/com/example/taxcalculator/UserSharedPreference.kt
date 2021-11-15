package com.example.taxcalculator

import android.content.Context
import android.content.SharedPreferences

class UserSharedPreference (private val context: Context){
    private val sharedPreference: SharedPreferences? =context.getSharedPreferences("userLogin",Context.MODE_PRIVATE)
    fun save(Key:String,value: String){
        val editor= sharedPreference?.edit()
        editor?.putString(Key,value)
        editor?.apply()
    }
    fun getValue(Key:String):String{
        return sharedPreference?.getString(Key,"username").toString()
    }
    fun clearPreference(){
        val editor= sharedPreference?.edit()
        editor?.clear()
        editor?.apply()
    }

}