package com.example.doctruyen.Adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.doctruyen.Model.RatingBookData
import com.example.doctruyen.Model.ReplyBookData
import com.example.doctruyen.databinding.CommentItemBinding
import com.example.doctruyen.service.FirebaseRealTime

class RatingBookAdapter(private val context: Context,private val listComment:List<RatingBookData>):RecyclerView.Adapter<RatingBookAdapter.RatingViewHolder>() {
    var onItemClick:((RatingBookData)->Unit)?=null
    private val firebaseRealTime = FirebaseRealTime()
    private lateinit var listReplyCommentRecyclerView:RecyclerView
    private lateinit var ratingReplyAdapter:ReplyRatingAdapter

    inner class RatingViewHolder(private val binding:CommentItemBinding):RecyclerView.ViewHolder(binding.root){
        val name = binding.nameAuthorLib
        val content = binding.contentRating
        val time = binding.timeComment
        val rate = binding.ratingBar2

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listComment[adapterPosition])
            }
        }
        fun setManagerReplyRatingListRecycler(listReplyComment: List<ReplyBookData>) {
            listReplyCommentRecyclerView = binding.recyclerViewReply
            listReplyCommentRecyclerView.layoutManager = LinearLayoutManager(context)
            ratingReplyAdapter = ReplyRatingAdapter(context, listReplyComment)
            listReplyCommentRecyclerView.adapter = ratingReplyAdapter
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatingViewHolder {
        val binding = CommentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RatingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RatingViewHolder, position: Int) {
        holder.name.text = listComment[position].name
        holder.content.text = listComment[position].content
        holder.time.text = listComment[position].date
        holder.rate.rating = listComment[position].rate!!.toFloat()

        firebaseRealTime.getReplyRating("Reply${listComment[position].id}"){ list->
            holder.setManagerReplyRatingListRecycler(list)
        }

    }

    override fun getItemCount(): Int {
        return listComment.size
    }

}