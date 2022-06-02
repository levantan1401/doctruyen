package com.example.doctruyen.UserInterface

import android.content.Intent
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.doctruyen.Adapter.TablayoutAdapter
import com.example.doctruyen.R
import com.example.doctruyen.ReadBook
import com.example.doctruyen.fragments.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson

class HomePage : AppCompatActivity() {
    private val homePageFragment = HomePageFragment()
    private val libraryFragment = LibraryFragment()
    private val storeFragment = StoreFragment()
    private val userFragment = UserFragment()

    private lateinit var resultFragment: ResultFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)
        replaceFragment(homePageFragment)
//        initTablayoutHomepage()
        navigation_bottom()

        homePageFragment.onItemSearchClick ={ it, value ->
            resultFragment = ResultFragment(it,value)
            replaceFragment(resultFragment)

            resultFragment.onItemClickChild = {
                val intent = Intent(this,ReadBook::class.java)
                val gson = Gson()
                val json = gson.toJson(it)
                intent.putExtra("myBooks",json)
                startActivity(intent)
            }
        }
    }

    private fun navigation_bottom() {
        val bottom_navigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home -> replaceFragment(homePageFragment)
                R.id.store -> replaceFragment(storeFragment)
                R.id.library -> replaceFragment(libraryFragment)
                R.id.profile -> replaceFragment(userFragment)
            }
            true
        }
    }
    private fun replaceFragment(fragment: Fragment) {
        if(true)  // fragment != null
        {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.commit()
        }
    }

}