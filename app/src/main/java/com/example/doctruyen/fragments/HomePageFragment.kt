package com.example.doctruyen.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.doctruyen.Adapter.*
import com.example.doctruyen.Model.AuthorData
import com.example.doctruyen.Model.BookData
import com.example.doctruyen.Model.CategoryData
import com.example.doctruyen.R
import com.google.android.material.tabs.TabLayout

class HomePageFragment : Fragment() {
    private lateinit var mviewpager: ViewPager
    private lateinit var mtablayout: TabLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home_page, container, false)
        initRecyclerView(view)
        tabLayoutHome(view)
        return view
    }

    private fun tabLayoutHome(view: View) {
        mviewpager = view.findViewById<ViewPager>(R.id.viewpager)
        mtablayout = view.findViewById<TabLayout>(R.id.tab_layout_lib)

        val viewPagerAdapter = TablayoutAdapter(parentFragmentManager, FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
        viewPagerAdapter.addFragment(BestSellerFragment(),"Best Seller")
        viewPagerAdapter.addFragment(TheLatestFragment(),"The Latest")
        viewPagerAdapter.addFragment(FavouricFragment(),"Favouric")
        viewPagerAdapter.addFragment(ComingSoonFragment(),"Coming Soon")

        mviewpager.adapter = viewPagerAdapter
        mtablayout.setupWithViewPager(mviewpager)
    }

    private fun initRecyclerView(view: View) {
        val cateRW = view.findViewById<RecyclerView>(R.id.category_recycler)
        val topTrendRW = view.findViewById<RecyclerView>(R.id.toptrend_recycler)
        val topAuthorRW = view.findViewById<RecyclerView>(R.id.topauthor_recycler)

        val listCategory = listOf(
            CategoryData(R.drawable.recentimage1,"Am Lake","J.Docweck",1850,"0"),
            CategoryData(R.drawable.recentimage2,"Am Lake","J.Docweck",1850,"0"),
            CategoryData(R.drawable.recentimage2,"Am Lake","J.Docweck",1850,"0"),
            CategoryData(R.drawable.recentimage1,"Am Lake","J.Docweck",1850,"0"),
            CategoryData(R.drawable.recentimage1,"Am Lake","J.Docweck",1850,"0"),
            CategoryData(R.drawable.recentimage1,"Am Lake","J.Docweck",1850,"0"),
            CategoryData(R.drawable.recentimage1,"Am Lake","J.Docweck",1850,"0")
            )
//        TOP TREND
        val listBook = listOf(
            BookData(R.drawable.recentimage2,"Am Lake","J.Docweck",1850),
            BookData(R.drawable.recentimage2,"Am Lake","J.Docweck",1850),
            BookData(R.drawable.recentimage2,"Am Lake","J.Docweck",1850),
            BookData(R.drawable.recentimage1,"Am Lake","J.Docweck",1850),
            BookData(R.drawable.recentimage1,"Am Lake","J.Docweck",1850),
            BookData(R.drawable.recentimage1,"Am Lake","J.Docweck",1850)
            )
        val listAuthor = listOf(
            AuthorData("J.DocWeck",R.drawable.mail,30,10000,""),
            AuthorData("J.DocWeck",R.drawable.mail,25,800,""),
            AuthorData("J.DocWeck",R.drawable.mail,12,1060,""),
            AuthorData("J.DocWeck",R.drawable.mail,8,100,"")
            )


        val cateAdapter = CategoryAdapter(context, listCategory)
        cateRW.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL,false)
        cateRW.adapter = cateAdapter

        val bookAdapter = TopTrendAdapter(context, listBook)
        topTrendRW.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL,false)
        topTrendRW.adapter = bookAdapter

        val authorAdapter = AuthorAdapter(context, listAuthor)
        topAuthorRW.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL,false)
        topAuthorRW.adapter = authorAdapter

    }
}