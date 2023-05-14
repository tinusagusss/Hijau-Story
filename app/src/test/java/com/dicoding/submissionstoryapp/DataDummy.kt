package com.dicoding.submissionstoryapp

import com.dicoding.submissionstoryapp.data.StoryListItem

object DataDummy {

    fun generateDummyNewsEntity(): List<StoryListItem> {

        val newsList = ArrayList<StoryListItem>()

        for (i in 0..10) {
            val news = StoryListItem(
                i.toString(),
                "tester",
                "Test",
                "https://story-api.dicoding.dev/images/stories/photos-1683466295348_zeH6ld_A.jpg",
                "2022-02-22T22:22:22Z",
                37.423318876991544,
                -122.08600345999002
            )
            newsList.add(news)
        }

        return newsList

    }
}