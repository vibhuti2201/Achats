package com.example.achats.fragments.loginRegister

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.achats.R
import com.example.achats.activities.ShoppingActivity
import com.example.achats.databinding.FragmentAccountOptionsBinding
import com.example.achats.databinding.FragmentIntroductionBinding
import com.example.achats.viewmodel.IntroductionViewModel
import com.example.achats.viewmodel.IntroductionViewModel.Companion.ACCOUNT_OPTIONS_FRAGMENT
import com.example.achats.viewmodel.IntroductionViewModel.Companion.SHOPPING_ACTIVITY
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect


@AndroidEntryPoint
class IntroductionFragment : Fragment() {

private lateinit var binding: FragmentIntroductionBinding
private val viewModel by viewModels<IntroductionViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentIntroductionBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launchWhenStarted {
            viewModel.navigate.collect{
                when(it){
                    SHOPPING_ACTIVITY->{
                        Intent(requireActivity(), ShoppingActivity::class.java).also{ intent->
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)
                        }

                    }
                    ACCOUNT_OPTIONS_FRAGMENT->{
                        findNavController().navigate(it)
                    }
                    else -> Unit
                }

            }
        }
        binding.buttonStart.setOnClickListener {
            viewModel.startButtonClick()
            findNavController().navigate(R.id.action_introductionFragment_to_accountOptionsFragment)
        }
    }


}