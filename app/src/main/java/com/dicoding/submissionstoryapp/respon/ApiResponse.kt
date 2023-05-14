package com.dicoding.submissionstoryapp.respon

import com.dicoding.submissionstoryapp.data.StoryListItem
import com.google.gson.annotations.SerializedName

data class ApiResponse<T>(
    @field:SerializedName("error") val error: Boolean,

    @field:SerializedName("message") val message: String,

    @field:SerializedName("listStory") val data: T
)

data class GetAllStoriesResponse(

    @field:SerializedName("listStory") val listStory: ArrayList<StoryListItem>,

    @field:SerializedName("error") val error: Boolean,

    @field:SerializedName("message") val message: String?
)

data class FileUploadResponse(
    @field:SerializedName("error") val error: Boolean,

    @field:SerializedName("message") val message: String
)
