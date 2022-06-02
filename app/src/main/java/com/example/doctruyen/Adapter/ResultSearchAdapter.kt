package com.example.doctruyen.Adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.doctruyen.Model.BookDataTest
import com.example.doctruyen.databinding.CategoryRowItemBinding
import com.example.doctruyen.databinding.FragmentResultBinding


class ResultSearchAdapter(
    val context: Context,
    private val bookSearchList: List<BookDataTest>
) : RecyclerView.Adapter<ResultSearchAdapter.ResultViewHolder>() {

    private lateinit var listbookRecycler :RecyclerView

    var onItemClick:((BookDataTest)->Unit)?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
        val binding =
            CategoryRowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ResultViewHolder(binding)
    }



    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        holder.bookName.text = bookSearchList[position].book
        holder.author.text = bookSearchList[position].author
        val uri = Uri.parse(bookSearchList[position].img.toString())
        holder.bind(uri)

    }

    override fun getItemCount(): Int {
        return bookSearchList.size
    }

    inner class ResultViewHolder(binding: CategoryRowItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val bookImg =binding.imgAuthorLib
        val bookName = binding.nameAuthorLib
        val author = binding.bestAuditorName
        fun bind(uri: Uri) {
            Glide.with(context).load(uri).into(bookImg)
        }
        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(bookSearchList[adapterPosition])
            }
        }
    }

}