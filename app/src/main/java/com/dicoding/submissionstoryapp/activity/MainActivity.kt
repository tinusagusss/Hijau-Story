package com.dicoding.submissionstoryapp.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.submissionstoryapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        btnToLogin()

        val sharedPreferences = getSharedPreferences("MY_PRE", Context.MODE_PRIVATE)
        val email = sharedPreferences.getString("EMAIL", "")
        val password = sharedPreferences.getString("PASSWORD", "")

        if (!email.isNullOrEmpty() && !password.isNullOrEmpty()) {
            startActivity(Intent(this, StoryListActivity::class.java))
        }
    }

    private fun btnToLogin() {
        binding.btnSignIn.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
        }
    }

}