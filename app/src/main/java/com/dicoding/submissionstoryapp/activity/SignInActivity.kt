package com.dicoding.submissionstoryapp.activity


import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.submissionstoryapp.SharedPreferencesUtil
import com.dicoding.submissionstoryapp.databinding.ActivitySignInBinding
import com.dicoding.submissionstoryapp.network.AuthInterceptor
import com.dicoding.submissionstoryapp.respon.LoginResponse
import com.dicoding.submissionstoryapp.respon.LoginResultDicoding
import com.dicoding.submissionstoryapp.respon.RegisterResponse
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.btnSignIn.setOnClickListener {
            val email = binding.edtEmail.text.toString()
            val password = binding.edtPassword.text.toString()

            authenticateUser(
                email,
                password
            )
        }

        navigateToRegisterActivity()
    }

    private fun authenticateUser(email: String, password: String) {
        val gson = Gson()
        val client =
            AuthInterceptor.ApiConfiguration.getApiService().checkUserAccount(email, password)
        client.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    val loginResultDicoding: LoginResultDicoding = response.body()?.loginResult!!
                    loginResultDicoding.let {
                        saveLoginData(email, password, it.token)
                        navigateToStoryListActivity()
                    }
                    Toast.makeText(this@SignInActivity, "Login Successful", Toast.LENGTH_LONG)
                        .show()
                } else {
                    response.errorBody()?.let {
                        val message: RegisterResponse = gson.fromJson(
                            it.charStream(), RegisterResponse::class.java
                        )
                        Toast.makeText(
                            this@SignInActivity,
                            "Login Failed: ${message.message}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.e(ContentValues.TAG, "OnFailure : ${t.message}")
                Toast.makeText(this@SignInActivity, "Login Failed: ${t.message}", Toast.LENGTH_LONG)
                    .show()

            }

        })
    }

    private fun navigateToRegisterActivity() {
        binding.tvRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity()::class.java))
        }
    }

    private fun navigateToStoryListActivity() {
        startActivity(Intent(this@SignInActivity, StoryListActivity()::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        })
    }

    private fun saveLoginData(email: String, password: String, token: String) {
        val sharedPreferencesUtil = SharedPreferencesUtil(this)
        sharedPreferencesUtil.saveEmail(email)
        sharedPreferencesUtil.savePassword(password)
        sharedPreferencesUtil.saveToken(token)
    }
}

