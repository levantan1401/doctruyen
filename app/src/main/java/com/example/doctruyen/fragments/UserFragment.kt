package com.example.doctruyen.fragments

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.doctruyen.LoginActivity
import com.example.doctruyen.Model.User
import com.example.doctruyen.R
import com.example.doctruyen.databinding.FragmentUserBinding
import com.example.doctruyen.service.FirebaseRealTime
import com.google.gson.Gson

class UserFragment : Fragment() {


    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!
    private val firebaseRealTime = FirebaseRealTime()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUserBinding.inflate(inflater, container, false)

        val user = firebaseRealTime.getUserInfo(requireActivity())

        if(user.uid != null){
            binding.emailTv.text = user.email
            binding.nameTv.text = user.name
        }else{
            val intent = Intent(context,LoginActivity::class.java)
            startActivity(intent)
        }

        binding.profileeditBtn.setOnClickListener {
            firebaseRealTime.logOut(requireActivity())
            val intent = Intent(context,LoginActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }

}