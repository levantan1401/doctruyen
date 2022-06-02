package com.example.doctruyen.fragments

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.doctruyen.LoginActivity
import com.example.doctruyen.MainActivity
import com.example.doctruyen.Model.BookDataTest
import com.example.doctruyen.Model.RatingBookData
import com.example.doctruyen.R
import com.example.doctruyen.databinding.ActivityRatingBookBinding
import com.example.doctruyen.databinding.ActivityReadBookBinding
import com.example.doctruyen.service.FirebaseRealTime
import com.google.gson.Gson

class RatingBookActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRatingBookBinding
    private var firebaseRealTime = FirebaseRealTime()
    private val gson = Gson()
    private lateinit var myBook:BookDataTest
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRatingBookBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = firebaseRealTime.getUserInfo(this)
        if (user.uid != null) {
            val json = intent.getStringExtra("MY_BOOK_RATING")
            myBook = gson.fromJson(json, BookDataTest::class.java)
        } else {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        binding.button.setOnClickListener {
            val content = binding.contentRating.text
            val rate = binding.ratingBar4.rating
            val currentTime = firebaseRealTime.getCurrentTime()
            val ratingBookData = RatingBookData("${user.uid}${myBook.id}", "$content", "${user.name}", "$currentTime", "$rate", "${myBook.id}")

            firebaseRealTime.insertRatingBook(ratingBookData.book_id.toString(),ratingBookData.id.toString(),ratingBookData){
                if (it){
                    Toast.makeText(this,"Cảm ơn quý khách đã đánh giá sản phẩm",Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this,"Err",Toast.LENGTH_SHORT)
                }
            }
        }

    }
}