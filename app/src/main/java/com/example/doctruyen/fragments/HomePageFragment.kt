package com.example.doctruyen.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.doctruyen.Adapter.*
import com.example.doctruyen.Model.BookDataTest
import com.example.doctruyen.R
import com.example.doctruyen.ReadBook
import com.example.doctruyen.service.FirebaseService
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import kotlinx.coroutines.launch

class HomePageFragment : Fragment() {

    private var firebaseService = FirebaseService()
    private lateinit var mviewpager: ViewPager
    private lateinit var mtablayout: TabLayout
    private lateinit var cateRW : RecyclerView
    private lateinit var topTrendRW : RecyclerView
    private lateinit var myBookAdapter: MyBookAdapter
    private lateinit var bookAdapter: TopTrendAdapter
    private lateinit var authorAdapter:AuthorAdapter
    private lateinit var topAuthorRW: RecyclerView
    private val gson = Gson()
//    private lateinit var firebaseDatabase: FirebaseDatabase
    var onItemSearchClick: ((List<BookDataTest>,value: String) -> Unit)? = null
    private var listBookSearchResult: BookDataTest? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home_page, container, false)
        insertDataMyBooks(view)
        insertDataTopTrending(view)
        insertDataAuthor(view)
        tabLayoutHome(view)
        val search_view = view.findViewById<SearchView>(R.id.search_view)
        search_view.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                val value: String = query
                search(value)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }

        })
        return view
    }

    private fun search(value: String) {

        if (value != ""){
            firebaseService.getBookWhere(
                value,
                listOf("author","book")
            ){list->
                onItemSearchClick?.invoke(list,value)
            }
        }

    }


    private fun insertDataAuthor(view: View) {
        topAuthorRW = view.findViewById<RecyclerView>(R.id.topauthor_recycler)
        firebaseService.getAuthorData {listAuthor->
            authorAdapter = AuthorAdapter(requireContext(), listAuthor)
            topAuthorRW.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL,false)
            topAuthorRW.adapter = authorAdapter
//            myBookAdapter.onItemClick={
//                val intent = Intent(requireContext(),ReadBook::class.java)
//                val gson = Gson()
//                val json = gson.toJson(it)
//                intent.putExtra("author",json)
//                startActivity(intent)
//            }
        }
    }

    private fun insertDataMyBooks(view: View) {
        cateRW = view.findViewById<RecyclerView>(R.id.category_recycler)
        firebaseService.getBook {listBook->
            myBookAdapter = MyBookAdapter(requireContext(), listBook)
            cateRW.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL,false)
            cateRW.adapter = myBookAdapter
            myBookAdapter.onItemClick={
                val intent = Intent(requireContext(),ReadBook::class.java)
                val gson = Gson()
                val json = gson.toJson(it)
                val PDF = it.nd
                intent.putExtra("myBooks",json)
                intent.putExtra("PDF_mybook",PDF)
                startActivity(intent)
            }
        }

    }

    private fun insertDataTopTrending(view: View) {
        topTrendRW = view.findViewById<RecyclerView>(R.id.toptrend_recycler)
        firebaseService.getBook {listBook->
            bookAdapter = TopTrendAdapter(requireContext(), listBook)
            topTrendRW.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL,false)
            topTrendRW.adapter = bookAdapter
            bookAdapter.onItemClick={
                val intent = Intent(requireContext(),ReadBook::class.java)
                val gson = Gson()
                val json = gson.toJson(it)
                val PDF = it.nd
                intent.putExtra("myBooks",json)
                intent.putExtra("PDF_top",PDF)
                startActivity(intent)
            }
        }
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
}