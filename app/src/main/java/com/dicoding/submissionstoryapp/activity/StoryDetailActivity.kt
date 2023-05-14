package com.dicoding.submissionstoryapp.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.dicoding.submissionstoryapp.R
import com.dicoding.submissionstoryapp.data.StoryListItem
import com.dicoding.submissionstoryapp.databinding.ActivityStoryDetailBinding

@Suppress("DEPRECATION")
class StoryDetailActivity : AppCompatActivity() {
    private var username: String = ""

    private lateinit var binding: ActivityStoryDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStoryDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = getString(R.string.title_detail_story)
        val story = intent.getParcelableExtra<StoryListItem>("Story") as StoryListItem

        supportActionBar?.apply {
            title = username.replaceFirstChar(Char::uppercase)
            setDisplayHomeAsUpEnabled(true)
        }

        binding.tvUserName.text = story.name
        binding.tvDescription.text = story.description
        Glide.with(binding.imgStory.context).load(story.photoUrl).into(binding.imgStory)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}