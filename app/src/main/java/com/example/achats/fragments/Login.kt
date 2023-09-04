package com.example.achats.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.achats.R
import com.example.achats.databinding.FragmentLoginBinding
import com.example.achats.databinding.FragmentRegisterBinding
import com.example.achats.viewmodel.LoginViewModel

class Login : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel by viewModels<LoginViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentLoginBinding.inflate(inflater)
        return binding.root
    }

    fun onViewCreate(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.apply {
            buttonLoginLogin.setOnClickListener {

            }
        }
    }


}