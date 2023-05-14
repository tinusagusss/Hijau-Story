package com.dicoding.submissionstoryapp.activity

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.submissionstoryapp.databinding.ActivityRegisterBinding
import com.dicoding.submissionstoryapp.network.AuthInterceptor
import com.dicoding.submissionstoryapp.respon.RegisterResponse
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        btnRegister()
        navigateToSignInActivity()

    }

    private fun btnRegister() {
        binding.btnRegister.setOnClickListener {

            val name = binding.edtName.text.toString()
            val email = binding.edtEmail.text.toString()
            val password = binding.edtPassword.text.toString()

            if (password.length >= 8 && name.length >= 8) (
                    setUserAccount(name, email, password))

        }
    }

    private fun setUserAccount(name: String, email: String, password: String) {
        val gson = Gson()
        val client =
            AuthInterceptor.ApiConfiguration.getApiService().setUserAccount(name, email, password)
        client.enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(
                call: Call<RegisterResponse>, response: Response<RegisterResponse>
            ) {
                if (response.isSuccessful) {
                    Toast.makeText(
                        this@RegisterActivity, response.message().toString(), Toast.LENGTH_LONG
                    ).show()
                    startActivity(Intent(this@RegisterActivity, SignInActivity::class.java))

                } else {
                    response.errorBody()?.let {
                        val message: RegisterResponse = gson.fromJson(
                            it!!.charStream(), RegisterResponse::class.java
                        )
                        Toast.makeText(
                            this@RegisterActivity,
                            "Registration Failed: ${message.message}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                Log.e(TAG, "OnFailure : ${t.message}")
                Toast.makeText(
                    this@RegisterActivity, "Registration Failed: ${t.message}", Toast.LENGTH_LONG
                ).show()
            }

        })
    }

    private fun navigateToSignInActivity() {
        binding.tvRegister.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
        }
    }
}