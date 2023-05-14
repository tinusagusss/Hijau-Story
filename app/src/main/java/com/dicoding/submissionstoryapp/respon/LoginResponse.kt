package com.dicoding.submissionstoryapp.respon

import com.google.gson.annotations.SerializedName

data class LoginResponse(

    @field:SerializedName("loginResult") val loginResult: LoginResultDicoding,

    @field:SerializedName("error") val error: Boolean? = null,

    @field:SerializedName("message") val message: String? = null
)

data class LoginResultDicoding(

    @field:SerializedName("name") val name: String,

    @field:SerializedName("userId") val userId: String,

    @field:SerializedName("token") val token: String
)

data class RegisterResponse(
    val error: String,
    val message: String,
)

