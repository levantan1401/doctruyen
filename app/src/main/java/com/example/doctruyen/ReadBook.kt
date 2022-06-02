package com.example.doctruyen

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.doctruyen.Adapter.RatingBookAdapter
import com.example.doctruyen.Model.BookDataTest
import com.example.doctruyen.Model.RatingBookData
import com.example.doctruyen.Model.ReplyBookData
import com.example.doctruyen.databinding.ActivityReadBookBinding
import com.example.doctruyen.fragments.RatingBookActivity
import com.example.doctruyen.service.FirebaseRealTime
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.gson.Gson

class ReadBook : AppCompatActivity() {
    private val gson = Gson()
    private lateinit var PDFLink: String
    private var bookID = ""
    private lateinit var json: String
    private lateinit var mybooks: BookDataTest
    private lateinit var binding: ActivityReadBookBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var listCommentRecyclerView: RecyclerView
    private lateinit var ratingAdapter: RatingBookAdapter
    private val firebaseRealTime = FirebaseRealTime()

    private companion object {
        const val TAG = "READ_BOOK_TAG"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReadBookBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        bookID = intent.getStringExtra("booksID")!!
//        topTrend()
        myBooks()
        firebaseRealTime.getRaTingBook(mybooks.id.toString()) { list ->
            setManagerRatingListRecycler(list)
        }
        binding.backBtn.setOnClickListener {
            onBackPressed()
        }
        binding.btndownload.setOnClickListener {
            checkUser()
        }
        binding.imgbtnComment.setOnClickListener {
            val intent = Intent(this, RatingBookActivity::class.java)
            Log.i("test", "$mybooks")
            val json = gson.toJson(mybooks)
            intent.putExtra("MY_BOOK_RATING", json)
            startActivity(intent)
        }
    }

    private fun checkUser() {
        val user = firebaseRealTime.getUserInfo(this)
        if (user.uid != null) {
            beginDownload()
        } else {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    private fun beginDownload() {

    }


    private fun myBooks() {
        val json = intent.getStringExtra("myBooks")
        PDFLink = intent.getStringExtra("PDF_mybook").toString()

        mybooks = gson.fromJson(json, BookDataTest::class.java)

        val img = findViewById<ImageView>(R.id.avatar)
        val img_bg = findViewById<ImageView>(R.id.avatar_bg)
        val nameBook = findViewById<TextView>(R.id.nameTP)
        val author = findViewById<TextView>(R.id.nameActor)
        val descriptor = findViewById<TextView>(R.id.textnd)
        val uri = Uri.parse(mybooks.img)

        Glide.with(this).load(uri).into(img)
        Glide.with(this).load(uri).into(img_bg)

        nameBook.text = mybooks.book
        author.text = mybooks.author
        descriptor.text = mybooks.desc

        binding.reading.setOnClickListener {
            val intent = Intent(this, Doc::class.java)
            intent.putExtra("PDF_mybook", PDFLink)
            startActivity(intent)
        }
    }

    private fun topTrend() {
        val json = intent.getStringExtra("bookDataTest")
        PDFLink = intent.getStringExtra("PDF_top").toString()
        val bookDataTest = gson.fromJson(json, BookDataTest::class.java)

        val img = findViewById<ImageView>(R.id.avatar)
        val img_bg = findViewById<ImageView>(R.id.avatar_bg)
        val nameBook = findViewById<TextView>(R.id.nameTP)
        val author = findViewById<TextView>(R.id.nameActor)
        val descriptor = findViewById<TextView>(R.id.textnd)

        val uri = Uri.parse(bookDataTest.img)
        Glide.with(this).load(uri).into(img)
        Glide.with(this).load(uri).into(img_bg)
        nameBook.text = bookDataTest.book
        author.text = bookDataTest.author
        descriptor.text = bookDataTest.desc

        binding.reading.setOnClickListener {
            intent.putExtra("PDF_top", PDFLink)
            startActivity(Intent(this, Doc::class.java))
        }
    }

    private fun setManagerRatingListRecycler(listComment: List<RatingBookData>) {
        listCommentRecyclerView = binding.recyclerViewRating
        listCommentRecyclerView.layoutManager = LinearLayoutManager(this)
        ratingAdapter = RatingBookAdapter(this, listComment)
        listCommentRecyclerView.adapter = ratingAdapter
        ratingAdapter.onItemClick = { ratingBookData ->
            Log.i("test","$ratingBookData")
//            val replyBookData = ReplyBookData("Reply${ratingBookData.id}","cam on rat nhieu","Văn Dũng","${firebaseRealTime.getCurrentTime()}","7UmKKJCRgNUDBJSDqnHb9LShGCr2")
//            firebaseRealTime.insertReplyRating("${replyBookData.id}","${replyBookData.idAdmin}",replyBookData){
//
//            }
        }
    }
}
