package com.dicoding.submissionstoryapp.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.dicoding.submissionstoryapp.network.ApiService

class StoryPagingSource(private val apiService: ApiService) : PagingSource<Int, StoryListItem>() {

    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, StoryListItem> {
        return try {
            val page = params.key ?: INITIAL_PAGE_INDEX

            val responseData = apiService.getAllStories(page, params.loadSize)

            LoadResult.Page(
                data = responseData.data,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (responseData.data.isEmpty()) null else page + 1
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, StoryListItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
