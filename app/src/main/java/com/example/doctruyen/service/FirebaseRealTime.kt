package com.example.doctruyen.service

import android.app.Activity
import android.content.Context
import android.util.Log
import com.example.doctruyen.Model.RatingBookData
import com.example.doctruyen.Model.ReplyBookData
import com.example.doctruyen.Model.User
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class FirebaseRealTime {
    private var database: DatabaseReference = Firebase.database.reference

    // user
    fun getUser(uid: String, callback: ((User) -> Unit)) {
        database.child("Users").child("$uid").get().addOnSuccessListener { documents ->
            val user = documents.getValue(User::class.java)
            callback.invoke(user!!)
        }
    }

    fun getUserInfo(activity: Activity): User {
        val gson = Gson()
        val pref = activity?.getSharedPreferences("PrefName", Context.MODE_PRIVATE)
        val json = pref?.getString("USERINFO", "NULL")
        if (json == "NULL") {
            return User()
        }
        return gson.fromJson(json, User::class.java)
    }

    fun logOut(activity: Activity): Boolean {
        val pref = activity?.getSharedPreferences("PrefName", Context.MODE_PRIVATE)
        val editor = pref?.edit()
        editor?.remove("USERINFO")
        return editor?.commit()!!
    }

    // rating

    fun insertRatingBook(idBook:String,id: String, ratingBookData: RatingBookData, callback: (Boolean) -> Unit) {
        database.child("Rating$idBook").child("$id").setValue(ratingBookData).addOnSuccessListener {
            callback.invoke(true)
        }.addOnFailureListener {
            callback.invoke(false)
        }
    }

    fun getRaTingBook(idBook:String,callback: (List<RatingBookData>) -> Unit){
        val arrayList = ArrayList<RatingBookData>()
        database.child("Rating$idBook").get().addOnSuccessListener {
            documents->
            for (document in documents.children){
                val ratingBookData = document.getValue(RatingBookData::class.java)
                arrayList.add(ratingBookData!!)
            }
            callback.invoke(arrayList)
        }
    }

    // reply rating

    fun insertReplyRating(id:String,idAdmin:String,replyBookData: ReplyBookData,callback: (Boolean) -> Unit){
        database.child("$id").child("$idAdmin").setValue(replyBookData).addOnSuccessListener {
            callback.invoke(true)
        }.addOnFailureListener {
            callback.invoke(false)
        }
    }

    fun getReplyRating(idReply:String,callback: (List<ReplyBookData>) -> Unit){
        val arrayList = ArrayList<ReplyBookData>()
        database.child("$idReply").get().addOnSuccessListener {
                documents->
            for (document in documents.children){
                val replyBookData = document.getValue(ReplyBookData::class.java)
                arrayList.add(replyBookData!!)
            }
            callback.invoke(arrayList)
        }
    }

    // time
    fun getCurrentTime(): String {
        val myFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        return myFormat.format(Date())
    }
}