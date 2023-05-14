package com.dicoding.submissionstoryapp

import android.content.Context
import com.dicoding.submissionstoryapp.data.StoryDatabase
import com.dicoding.submissionstoryapp.data.StoryRepository
import com.dicoding.submissionstoryapp.network.AuthInterceptor

object Injection {
    fun provideRepository(context: Context): StoryRepository {
        val database = StoryDatabase.getDatabase(context)
        val apiService = AuthInterceptor.ApiConfiguration.getApiService(context)
        return StoryRepository(database, apiService)
    }
}