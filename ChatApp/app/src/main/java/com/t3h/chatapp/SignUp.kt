package com.t3h.chatapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.t3h.chatapp.databinding.RegisterLayoutBinding

class SignUp : AppCompatActivity() {
    private lateinit var registerLayoutBinding: RegisterLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerLayoutBinding = RegisterLayoutBinding.inflate(layoutInflater)
        val view = registerLayoutBinding.root
        setContentView(view)
        registerLayoutBinding.btnSignUp.setOnClickListener(){}

        registerLayoutBinding.tvLogin.setOnClickListener(){
            val intent = Intent(this, LogIn::class.java)
            startActivity(intent)
        }

    }
}