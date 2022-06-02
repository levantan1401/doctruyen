package com.example.doctruyen.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.doctruyen.Adapter.ResultSearchAdapter
import com.example.doctruyen.Model.BookDataTest
import com.example.doctruyen.databinding.FragmentResultBinding
import com.google.gson.Gson

class ResultFragment(private val listBook:List<BookDataTest>,private val value: String) : Fragment() {
    private val gson = Gson()
    private lateinit var mybooks: BookDataTest


    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!
    private lateinit var managerBookRecyclerView : RecyclerView
    private lateinit var managerBookAdapter: ResultSearchAdapter
    var onItemClickChild:((BookDataTest)->Unit)?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentResultBinding.inflate(inflater, container, false)

        if (listBook.isNotEmpty()){
            binding.textView21.append(" $value")
            setManagerBookListRecycler(listBook)
        }else {
            binding.textView21.text = "KHONG CO KET QUA"
        }

        return binding.root
    }

    private fun setManagerBookListRecycler(listBook: List<BookDataTest>) {
        managerBookRecyclerView = binding.listbookRecycler
        managerBookRecyclerView.layoutManager = GridLayoutManager(context,2)
        managerBookAdapter = ResultSearchAdapter(requireContext(), listBook)
        managerBookRecyclerView.adapter = managerBookAdapter

        managerBookAdapter.onItemClick={
            onItemClickChild?.invoke(it)
        }
    }

}