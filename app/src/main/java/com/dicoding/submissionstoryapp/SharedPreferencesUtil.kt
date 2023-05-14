package com.dicoding.submissionstoryapp

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesUtil(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun saveEmail(email: String) {
        sharedPreferences.edit().putString(KEY_EMAIL, email).apply()
    }

    fun getEmail(defaultValue: String): String {
        return sharedPreferences.getString(KEY_EMAIL, defaultValue) ?: defaultValue
    }

    fun savePassword(password: String) {
        sharedPreferences.edit().putString(KEY_PASSWORD, password).apply()
    }

    fun getPassword(defaultValue: String): String {
        return sharedPreferences.getString(KEY_PASSWORD, defaultValue) ?: defaultValue
    }

    fun saveToken(token: String) {
        sharedPreferences.edit().putString(KEY_TOKEN, token).apply()
    }

    fun getToken(defaultValue: String): String {
        return sharedPreferences.getString(KEY_TOKEN, defaultValue) ?: defaultValue
    }

    fun clearData() {
        sharedPreferences.edit().clear().apply()
    }

    companion object {
        private const val PREF_NAME = "user_preferences"
        private const val KEY_EMAIL = "email"
        private const val KEY_PASSWORD = "password"
        private const val KEY_TOKEN = "token"
    }
}
