package com.example.doctruyen.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.doctruyen.Model.AuthorData
import com.example.doctruyen.R

class AuthorAdapter(val context: Context?, val authorData: List<AuthorData>) : RecyclerView.Adapter<AuthorAdapter.ViewHolder>() {
    private lateinit var adapter: Adapter

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.top_author_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.authorImg.setImageResource(authorData[position].imgAuthor)
        holder.authorName.text = authorData[position].nameAuthor
        holder.authortotalBook.text = "${authorData[position].totalBook.toString() + 'M'} books"
        holder.totalViews.text = "${authorData[position].views.toString()} views"
    }

    override fun getItemCount(): Int {
        return authorData.size
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val authorImg = view.findViewById<ImageView>(R.id.authorImg)
        val authorName = view.findViewById<TextView>(R.id.authorName)
        val authortotalBook = view.findViewById<TextView>(R.id.authortotalBook)
        val totalViews = view.findViewById<TextView>(R.id.storeTitle)
    }
}

class AuthorLibAdapter(val context: Context?, val authorData: List<AuthorData>) : RecyclerView.Adapter<AuthorLibAdapter.ViewHolder>() {
    private lateinit var adapter: Adapter

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.author_item_lib,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.img_author_lib.setImageResource(authorData[position].imgAuthor)
        holder.name_author_lib.text = authorData[position].nameAuthor
        holder.total_book_lib.text = "${authorData[position].totalBook.toString() + 'M'} books"
        holder.total_view_lib.text = "${authorData[position].views.toString()} views"
        holder.descAuthor_lib.text = authorData[position].descAuthor
    }

    override fun getItemCount(): Int {
        return authorData.size
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val img_author_lib = view.findViewById<ImageView>(R.id.img_author_lib)
        val name_author_lib = view.findViewById<TextView>(R.id.name_author_lib)
        val total_book_lib = view.findViewById<TextView>(R.id.total_book_lib)
        val total_view_lib = view.findViewById<TextView>(R.id.total_view_lib)
        val descAuthor_lib = view.findViewById<TextView>(R.id.descAuthor_lib)

    }
}

