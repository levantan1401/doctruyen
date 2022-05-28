package com.example.doctruyen

import android.app.ProgressDialog
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.doctruyen.databinding.ActivityDocBinding
import com.example.doctruyen.service.FirebaseService
import com.github.barteksc.pdfviewer.PDFView
import com.google.firebase.database.FirebaseDatabase
import java.io.BufferedInputStream
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL


class Doc : AppCompatActivity() {
    private lateinit var PDFlink: String
    private lateinit var pdfView: PDFView
    private lateinit var binding : ActivityDocBinding

    private companion object{
        const val TAG = "PDF_VIEW_TAG"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDocBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        loadBookDetail()

        val btnBack = findViewById<ImageButton>(R.id.backBtn)
        btnBack.setOnClickListener {
            onBackPressed()
        }

        PDFlink = intent.getStringExtra("PDF_mybook").toString()
        pdfView = findViewById(R.id.pdfView)
        val uri = Uri.parse(PDFlink)

        Log.d("AAA", "URI: $uri")

        pdfView.fromAsset("harrypotter_test.pdf")
            .password(null)
            .defaultPage(0)
            .enableSwipe(true)
            .swipeHorizontal(false)
            .enableDoubletap(true)
            .onPageError{ page, t ->
                Toast.makeText(this,"Error" +page,  Toast.LENGTH_SHORT).show()
                Log.d("AAA", t.localizedMessage)
            }
            .onTap { false }
            .enableAnnotationRendering(true)
            .load()
    }

//    private fun loadBookDetail() {
//        Log.d(TAG,"loadBook tu firebase")
//        val ref = FirebaseService()
//        Log.d(TAG,"REF: $ref")
//        ref.getBook {
//            Log.d(TAG,"List: $it")
//        }
//
//    }
}