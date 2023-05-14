package com.dicoding.submissionstoryapp.data

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.dicoding.submissionstoryapp.network.ApiService

class StoryRepository(
    private val storyDatabase: StoryDatabase,
    private val apiService: ApiService
) {
    fun getStory(): LiveData<PagingData<StoryListItem>> {
        @OptIn(ExperimentalPagingApi::class)
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            remoteMediator = StoryRemoteMediator(storyDatabase, apiService),
            pagingSourceFactory = {
                StoryPagingSource(apiService)
                storyDatabase.quoteDao().getAllStories()
            }
        ).liveData
    }
}