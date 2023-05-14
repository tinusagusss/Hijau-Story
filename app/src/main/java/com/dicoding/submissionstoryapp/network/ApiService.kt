package com.dicoding.submissionstoryapp.network

import com.dicoding.submissionstoryapp.data.StoryListItem
import com.dicoding.submissionstoryapp.respon.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @FormUrlEncoded
    @POST("register")
    fun setUserAccount(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<RegisterResponse>

    @FormUrlEncoded
    @POST("login")
    fun checkUserAccount(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<LoginResponse>

    @GET("stories")
    suspend fun getAllStories(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): ApiResponse<List<StoryListItem>>

    @GET("stories?location=1")
    fun getAllLocationStories(
    ): Call<GetAllStoriesResponse>

    @Multipart
    @POST("stories")
    fun uploadStories(
        @Part file: MultipartBody.Part,
        @Part("description") description: RequestBody,
        @Part("lat") lat: Double?,
        @Part("lon") lon: Double?,
    ): Call<FileUploadResponse>


}