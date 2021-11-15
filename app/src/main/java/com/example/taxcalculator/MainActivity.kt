package com.example.taxcalculator

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val splashScreen = installSplashScreen()
        setContentView(R.layout.activity_main)

        val loginBtn:Button=findViewById(R.id.loginBtn)
        val sharedPreference=UserSharedPreference(this)
        if (sharedPreference.getValue("username")!="username"){
            moveActivity(this,HomeScreen(),sharedPreference.getValue("username"))
        }else{
        loginBtn.setOnClickListener {
            val username:EditText=findViewById(R.id.username)
            val password:EditText=findViewById(R.id.password)
            val user=UserLogin(username.text.toString(),password.text.toString())
            if (!user.checkIsEmpty()){
                if (user.validateLogin()){
                    sharedPreference.save("username",username.text.toString())
                    moveActivity(this, activity = HomeScreen(),username.text.toString())
                }
                else{
                    Toast.makeText(this,getString(R.string.user_authentication),Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this,getString(R.string.fill_credentials),Toast.LENGTH_SHORT).show()
            }
            }

        }


    }
    private fun moveActivity(context: Context,activity:Activity,username:String){
        val intent=Intent(context,activity::class.java)
        intent.putExtra("username",username)
        startActivity(intent)
        finish()
    }
}