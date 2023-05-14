package com.dicoding.submissionstoryapp.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.submissionstoryapp.MainViewModel
import com.dicoding.submissionstoryapp.R
import com.dicoding.submissionstoryapp.ViewModelFactory
import com.dicoding.submissionstoryapp.adapter.LoadingStateAdapter
import com.dicoding.submissionstoryapp.adapter.StoryListAdapter
import com.dicoding.submissionstoryapp.databinding.ActivityListStoryBinding

class StoryListActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels {
        ViewModelFactory(this)
    }

    private lateinit var binding: ActivityListStoryBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListStoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "List of Stories"

        binding.rvListStory.layoutManager = LinearLayoutManager(this)


        getData()
    }

    private fun getData() {
        val adapter = StoryListAdapter()
        binding.rvListStory.adapter = adapter.withLoadStateFooter(footer = LoadingStateAdapter {
            adapter.retry()
        })
        mainViewModel.story.observe(this) {
            adapter.submitData(lifecycle, it)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val mMenuInflater = menuInflater
        mMenuInflater.inflate(R.menu.menu_option, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_logout -> {
                val sharedPreferences = getSharedPreferences("MY_PRE", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.clear()
                editor.apply()

                startActivity(Intent(this, SignInActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                })
                true
            }

            R.id.action_maps -> {
                startActivity(Intent(this, MapsActivity::class.java))
                true
            }


            R.id.add_photo -> {
                startActivity(Intent(this, AddStoryActivity::class.java))
                true
            }
            else -> true
        }
    }

}