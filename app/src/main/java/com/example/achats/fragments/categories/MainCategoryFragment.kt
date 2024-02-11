package com.example.achats.fragments.categories

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.achats.R
import com.example.achats.adapters.BestDealsAdapter
import com.example.achats.adapters.BestProductAdapter
import com.example.achats.adapters.SpecialProductAdapter
import com.example.achats.databinding.FragmentMainCategoryBinding
import com.example.achats.util.Resource
import com.example.achats.viewmodel.MainCategoryViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

private val TAG="MainCategoryFragment"
@AndroidEntryPoint
class MainCategoryFragment: Fragment(R.layout.fragment_main_category) {
    private lateinit var binding:FragmentMainCategoryBinding
    private lateinit var specialProductAdapter: SpecialProductAdapter
    private lateinit var bestDealsAdapter: BestDealsAdapter
    private lateinit var bestProductAdapter: BestProductAdapter
    private val viewModel by viewModels<MainCategoryViewModel> ()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding= FragmentMainCategoryBinding.inflate(inflater)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupSpecialProductsRv()
        setupBestDealsRv()
        setupBestProductsRv()
        lifecycleScope.launchWhenStarted {
            viewModel.specialProducts.collectLatest {
                when(it){
                    is Resource.Loading->{
                        showLoading()
                    }
                    is Resource.Success->{
                        specialProductAdapter.differ.submitList(it.data)
                        hideLoading()
                    }
                    is Resource.Error->{
                        hideLoading()
                        Log.e(TAG,it.message.toString())
                        Toast.makeText(requireContext(),it.message,Toast.LENGTH_SHORT).show()
                    }
                    else -> {}
                }
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.bestDealsProducts.collectLatest {
                when(it){
                    is Resource.Loading->{
                        showLoading()
                    }
                    is Resource.Success->{
                        bestDealsAdapter.differ.submitList(it.data)
                        hideLoading()
                    }
                    is Resource.Error->{
                        hideLoading()
                        Log.e(TAG,it.message.toString())
                        Toast.makeText(requireContext(),it.message,Toast.LENGTH_SHORT).show()
                    }
                    else -> {}
                }
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.bestProducts.collectLatest {
                when(it){
                    is Resource.Loading->{
                        binding.bestProductsProgressBar.visibility=View.VISIBLE
                    }
                    is Resource.Success->{
                        bestProductAdapter.differ.submitList(it.data)
                        binding.bestProductsProgressBar.visibility=View.GONE
                    }
                    is Resource.Error->{
                        binding.bestProductsProgressBar.visibility=View.GONE
                        Log.e(TAG,it.message.toString())
                        Toast.makeText(requireContext(),it.message,Toast.LENGTH_SHORT).show()
                    }
                    else -> {}
                }
            }
        }
       binding.nestedScrollMainCategory.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener{v,_,scrollY,_,_ ->
          if(v.getChildAt(0).bottom <= v.height+ scrollY){
              viewModel.fetchBestProducts()
          }
       })
    }

    private fun setupBestProductsRv() {
      bestProductAdapter= BestProductAdapter()
        binding.rvBestProducts.apply {
            layoutManager= GridLayoutManager(requireContext(),2,GridLayoutManager.VERTICAL,false)
            adapter=bestProductAdapter
        }
    }

    private fun setupBestDealsRv() {
        bestDealsAdapter= BestDealsAdapter()
        binding.rvBestDealsProducts.apply {
            layoutManager=
                LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
            adapter=bestDealsAdapter
        }
    }

    private fun hideLoading() {
        binding.bestProductsProgressBar.visibility=View.GONE
    }

    private fun showLoading() {
        binding.bestProductsProgressBar.visibility=View.VISIBLE
    }

    private fun setupSpecialProductsRv(){
       specialProductAdapter= SpecialProductAdapter()
        binding.rvSpecialProducts.apply {
            layoutManager=
                LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
            adapter=specialProductAdapter
        }


    }
}