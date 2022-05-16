package com.example.doctruyen.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.doctruyen.Adapter.BestSellerAdapter
import com.example.doctruyen.Adapter.TablayoutAdapter
import com.example.doctruyen.Model.BestSellerData
import com.example.doctruyen.R
import com.google.android.material.tabs.TabLayout


class BestSellerFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_best_seller, container, false)
        initRecyclerViewBestSeller(view)
        return view
    }

    private fun initRecyclerViewBestSeller(view: View) {
        val bestSellerRW = view.findViewById<RecyclerView>(R.id.bestseller_recycler)

        val listBestSeller = listOf(
            BestSellerData(R.drawable.recentimage1,"Trên đường băng","Lê Văn Tấn",14809,"Đây là một cuốn sách rất hay Đây là một cuốn sách rất hayĐây là một cuốn sách rất hay..."),
            BestSellerData(R.drawable.recentimage2,"Giao tiếp không khó","Horron",14809,"Đây là một cuốn sách rất hay Đây là một cuốn sách rất hay ..."),
            BestSellerData(R.drawable.recentimage2,"One Piece","Horron",14809,"Đây là một cuốn sách rất hay..."),
            BestSellerData(R.drawable.recentimage1,"Naruto","Horron",14809,"Đây là một cuốn sách rất hay..."),
        )
        val bestSellerAdapter = BestSellerAdapter(context,listBestSeller)

        bestSellerRW.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL,false)
        bestSellerRW.adapter = bestSellerAdapter
    }

}