package com.dicoding.submissionstoryapp

import android.content.ContentValues
import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.dicoding.submissionstoryapp.data.StoryListItem
import com.dicoding.submissionstoryapp.data.StoryRepository
import com.dicoding.submissionstoryapp.network.AuthInterceptor
import com.dicoding.submissionstoryapp.respon.GetAllStoriesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(storyRepository: StoryRepository) : ViewModel() {

    val story: LiveData<PagingData<StoryListItem>> =
        storyRepository.getStory().cachedIn(viewModelScope)

    private val _stories = MutableLiveData<ArrayList<StoryListItem>>()
    val stories: LiveData<ArrayList<StoryListItem>> = _stories

    fun getListLocationStory(context: Context) {
        val apiService =
            AuthInterceptor.ApiConfiguration.getApiService(context).getAllLocationStories()
        apiService.enqueue(object : Callback<GetAllStoriesResponse> {
            override fun onResponse(
                call: Call<GetAllStoriesResponse>,
                response: Response<GetAllStoriesResponse>
            ) {
                if (response.isSuccessful) {
                    try {
                        _stories.value = response.body()?.listStory

                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                } else {
                    Log.e(ContentValues.TAG, "onFailure getListStory : ${response.message()}")
                    response.errorBody()?.let { Log.v("Error code 400", it.string()) };
                }
            }

            override fun onFailure(call: Call<GetAllStoriesResponse>, t: Throwable) {
                Log.e(ContentValues.TAG, "OnFailure getListStory : ${t.message}")
            }

        })
    }

}

class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(Injection.provideRepository(context)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}



