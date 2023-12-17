package com.miu.foodiepal_culinarycompanion

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.miu.foodiepal_culinarycompanion.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var userList : ArrayList<UserAccount> = arrayListOf<UserAccount>()
    lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userList.add(UserAccount(1, "abedur", "rahman", "abedur@gmail.com", "123"))
        userList.add(UserAccount(2, "adyan", "rahman", "adyan.eit@gmail.com", "123"))
        userList.add(UserAccount(3, "abedur", "panna", "panna@gmail.com", "123"))
        userList.add(UserAccount(4, "Abedur", "Rahman", "mdrahman@miu.edu", "123"))
        userList.add(UserAccount(5, "admin", "admin", "admin@admin.com", "admin"))


        binding.btnLogin.setOnClickListener {
            var tmpEmail: String =binding.etEmail.text.toString().trim()
            var tmpPass : String = binding.etPassword.text.toString().trim()

            if (tmpEmail.isNullOrEmpty()){
                binding.etEmail.error = "Please enter a valid email"
            }
            if(tmpPass.isNullOrEmpty()){
                binding.etPassword.error = "Please enter a valid password"
            }
            if(!verifyUser(tmpEmail,tmpPass)){
                Toast.makeText(this,"User not found.", Toast.LENGTH_LONG).show()
            }else{
                binding.etEmail.text.clear()
                binding.etPassword.text.clear()

                val sharedPreference = getSharedPreferences("LOGIN_PRE", Context.MODE_PRIVATE)
                val editor = sharedPreference.edit()
                editor.putString("EMAIL",tmpEmail)
                editor.putString("PASSWORD", tmpPass)
                editor.apply()

                var intent =Intent(this, TabActivity::class.java )
                startActivity(intent)
            }
        }
    }
    fun verifyUser(email: String, password:String):Boolean{
        return userList.contains(UserAccount(0,"","",email, password))
    }
}