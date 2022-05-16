package com.example.doctruyen.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.doctruyen.Model.BookData
import com.example.doctruyen.R

class TopTrendAdapter(val context: Context?, val bookData: List<BookData>) : RecyclerView.Adapter<TopTrendAdapter.ViewHolder>(){
    private lateinit var adapter: Adapter
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopTrendAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.category_row_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: TopTrendAdapter.ViewHolder, position: Int) {
        holder.bookImg.setImageResource(bookData[position].imgBook)
        holder.bookName.text = bookData[position].nameBook

    }

    override fun getItemCount(): Int {
        return bookData.size
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val bookImg = view.findViewById<ImageView>(R.id.img_author_lib)
        val bookName = view.findViewById<TextView>(R.id.name_author_lib)

    }

}
