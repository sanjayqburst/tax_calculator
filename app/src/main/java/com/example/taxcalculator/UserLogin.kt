package com.example.taxcalculator


class UserLogin(private val userName:String, private val passWord:String) {
    fun checkIsEmpty():Boolean{
        return userName.isEmpty()||passWord.isEmpty()
    }
    fun validateLogin():Boolean{
        return userName=="Sanjay"&&passWord=="password"
    }
}