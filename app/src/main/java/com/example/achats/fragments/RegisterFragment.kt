package com.example.achats.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.achats.R
import com.example.achats.data.User
import com.example.achats.databinding.FragmentRegisterBinding
import com.example.achats.util.Resource
import com.example.achats.viewmodel.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect


private val TAG= "RegisterFragment"
@AndroidEntryPoint
class RegisterFragment : Fragment() {

private lateinit var binding:FragmentRegisterBinding
private  val viewModel by viewModels<RegisterViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentRegisterBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
         binding.buttonRegisterRegister.setOnClickListener {
             val user= User(
                 edFirstName.text.toString().trim(),
                 edLastName.text.toString().trim(),
                 edEmail.text.toString().trim()
             )
             val password = edPassword.text.toString()
             viewModel.createAccountWithEmailAndPassword(user,password)
         }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.register.collect{
                when(it){
                    is Resource.Loading -> {
                       binding.buttonRegisterRegister.startAnimation()
                    }
                    is Resource.Success->{
                         Log.d("text",it.message.toString())
                        binding.buttonRegisterRegister.revertAnimation()
                    }
                    is Resource.Error->{
                        Log.e(TAG,it.message.toString())
                        binding.buttonRegisterRegister.revertAnimation()
                    }
                    else -> Unit
                }
            }
        }
    }
}