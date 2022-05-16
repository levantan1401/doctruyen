package com.example.doctruyen.Model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class UserDataDemo(val username: String? = null, val email: String? = null) {
}