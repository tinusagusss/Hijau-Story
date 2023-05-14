package com.dicoding.submissionstoryapp.data

import androidx.paging.PagingSource
import androidx.room.*

@Dao
interface StoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStory(story: List<StoryListItem>)

    @Query("SELECT * FROM story")
    fun getAllStories(): PagingSource<Int, StoryListItem>

    @Query("DELETE FROM story")
    suspend fun deleteAll(): Int
}