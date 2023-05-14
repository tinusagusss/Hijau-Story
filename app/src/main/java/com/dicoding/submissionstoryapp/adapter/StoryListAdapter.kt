package com.dicoding.submissionstoryapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.submissionstoryapp.activity.StoryDetailActivity
import com.dicoding.submissionstoryapp.data.StoryListItem
import com.dicoding.submissionstoryapp.databinding.ItemListStoryBinding

class StoryListAdapter :
    PagingDataAdapter<StoryListItem, StoryListAdapter.MyViewHolder>(DIFF_CALLBACK) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ItemListStoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) {
            holder.bind(data)
        }
    }

    class MyViewHolder(private val binding: ItemListStoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: StoryListItem) {
            binding.tvItemQuote.text = data.description
            binding.tvItemAuthor.text = data.name
            Glide.with(binding.imgPhoto.context).load(data.photoUrl).into(binding.imgPhoto)

            binding.root.setOnClickListener {
                val intent = Intent(binding.root.context, StoryDetailActivity::class.java)
                intent.putExtra("Story", data)
                binding.root.context.startActivity(intent)
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<StoryListItem>() {
            override fun areItemsTheSame(oldItem: StoryListItem, newItem: StoryListItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: StoryListItem, newItem: StoryListItem
            ): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}