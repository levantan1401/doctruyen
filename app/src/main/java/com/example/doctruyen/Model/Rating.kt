package com.example.doctruyen.Model

data class RatingBookData(
    val id: String? = null,
    val content: String? = null,
    val name: String? = null,
    val date:String?=null,
    val rate: String? = null,
    val book_id:String?=null
)
data class ReplyBookData(
    val id: String? = null,
    val content: String? = null,
    val name: String? = null,
    val date:String?=null,
    val idAdmin:String?=null
)


