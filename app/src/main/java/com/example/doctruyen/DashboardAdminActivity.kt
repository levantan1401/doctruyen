package com.example.doctruyen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.doctruyen.Adapter.AdminCategoryAdapter
import com.example.doctruyen.databinding.ActivityDashboardAdminBinding
import com.example.doctruyen.firebase.fireBase_Category
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import java.util.*
import kotlin.collections.ArrayList

class DashboardAdminActivity : AppCompatActivity() {
    private  lateinit var binding: ActivityDashboardAdminBinding
//    firebase auth
    private  lateinit var firebaseAuth: FirebaseAuth

//    Array list to hold categories
    private lateinit var categoryArrayList: ArrayList<fireBase_Category>
    private lateinit var adapterCategory: AdminCategoryAdapter


//    Bien
    private lateinit var searchEt: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        init firebase auth
        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()
        loadCategories()
        searchEt = findViewById<EditText>(R.id.searchEt)
        searchEt.addTextChangedListener(object: TextWatcher{

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                try {
                    adapterCategory.filter.filter(s)
                }
                catch (e: Exception)
                {

                }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
//        handle click logout
        binding.logoutBtn.setOnClickListener {
            firebaseAuth.signOut()
            checkUser()
        }
//        handle click start add category page
        val addCategoryBtn = findViewById<Button>(R.id.addCategoryBtn)
        addCategoryBtn.setOnClickListener {
            startActivity(Intent(this,CategoryAddActivity::class.java))

        }
    }

    private fun loadCategories() {
        categoryArrayList = ArrayList()

        val ref = FirebaseDatabase.getInstance().getReference("Categories")
        ref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                // Clear list
                categoryArrayList.clear()
                for (ds in snapshot.children){
                    val model = ds.getValue(fireBase_Category::class.java)

                    categoryArrayList.add(model!!)
                }
                adapterCategory = AdminCategoryAdapter(this@DashboardAdminActivity,categoryArrayList)
                val recyclerView_Cate_admin = findViewById<RecyclerView>(R.id.recyclerView_Cate_admin)
                recyclerView_Cate_admin.adapter = adapterCategory
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun checkUser() {
        val firebaseUser = firebaseAuth.currentUser
        if(firebaseUser == null){
//            not loggin in goto main screen
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
        else{
            // logged in, get and show user info
            val email = firebaseUser.email
//            set to textview of toolbar
            binding.subTitleTv.text= email
        }
    }
}