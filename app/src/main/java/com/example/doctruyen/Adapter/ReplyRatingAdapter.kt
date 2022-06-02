package com.example.doctruyen.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.doctruyen.Model.ReplyBookData
import com.example.doctruyen.databinding.CommentItemReplyBinding

class ReplyRatingAdapter(private val context: Context,private val list: List<ReplyBookData>):RecyclerView.Adapter<ReplyRatingAdapter.ReplyRatingViewHolder>() {
    inner class ReplyRatingViewHolder(binding: CommentItemReplyBinding):RecyclerView.ViewHolder(binding.root){
        val name = binding.nameAuthorLib
        val content = binding.contentRating
        val time = binding.timeComment
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReplyRatingViewHolder {
        val binding = CommentItemReplyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReplyRatingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReplyRatingViewHolder, position: Int) {
        holder.name.text = list[position].name
        holder.content.text = list[position].content
        holder.time.text = list[position].date
    }

    override fun getItemCount(): Int {
        return list.size
    }
}