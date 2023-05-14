package com.dicoding.submissionstoryapp.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.submissionstoryapp.SharedPreferencesUtil
import com.dicoding.submissionstoryapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        btnToLogin()

        val sharedPreferencesUtil = SharedPreferencesUtil(this)
        val email = sharedPreferencesUtil.getEmail("")
        val password = sharedPreferencesUtil.getPassword("")

        if (email.isNotEmpty() && password.isNotEmpty()) {
            startActivity(Intent(this, StoryListActivity::class.java))
        }
    }

    private fun btnToLogin() {
        binding.btnSignIn.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
        }
    }

}