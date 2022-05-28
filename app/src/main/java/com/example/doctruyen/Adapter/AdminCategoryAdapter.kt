package com.example.doctruyen.Adapter

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.doctruyen.FilterCategory
import com.example.doctruyen.R
import com.example.doctruyen.databinding.AdminRowCategoryItemBinding
import com.example.doctruyen.firebase.Test
import com.google.firebase.database.FirebaseDatabase

class AdminCategoryAdapter(val context: Context, var categoryArrayList: ArrayList<Test>):
    RecyclerView.Adapter<AdminCategoryAdapter.HolderCategory>() {

    var onItemClick:((Test)->Unit)?=null

    inner class HolderCategory(binding:AdminRowCategoryItemBinding): RecyclerView.ViewHolder(binding.root){
        var categoryTV = binding.categoryTV
        var deleteBtn = binding.deleteBtn
        init {
            binding.deleteBtn.setOnClickListener {
                onItemClick?.invoke(categoryArrayList[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderCategory {
        val binding =
            AdminRowCategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HolderCategory(binding)
    }

    override fun onBindViewHolder(holder: HolderCategory, position: Int) {
        holder.categoryTV.text = categoryArrayList[position].category
    }

    override fun getItemCount(): Int {
       return categoryArrayList.size
    }

}