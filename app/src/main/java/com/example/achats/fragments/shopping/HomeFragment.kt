package com.example.achats.fragments.shopping

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.achats.R
import com.example.achats.adapters.HomeViewpagerAdapter
import com.example.achats.databinding.FragmentHomeBinding
import com.example.achats.fragments.categories.AccessoryFragment
import com.example.achats.fragments.categories.ChairFragment
import com.example.achats.fragments.categories.CupboardFragment
import com.example.achats.fragments.categories.FurnitureFragment
import com.example.achats.fragments.categories.MainCategoryFragment
import com.example.achats.fragments.categories.TableFragment
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment: Fragment(R.layout.fragment_home) {
    private lateinit var binding:FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categoriesFragments= arrayListOf<Fragment>(
          MainCategoryFragment(),
            ChairFragment(),
            CupboardFragment(),
            TableFragment(),
            AccessoryFragment(),
            FurnitureFragment()
        )


        binding.viewpagerHome.isUserInputEnabled=false
        //instance of adapter

        val viewPager2Adapter= HomeViewpagerAdapter(categoriesFragments,childFragmentManager,lifecycle)
        binding.viewpagerHome.adapter=viewPager2Adapter
        TabLayoutMediator(binding.tabLayout,binding.viewpagerHome){ tab,position->
           when(position){
               0-> tab.text= "Main"
               1-> tab.text="Chair"
               2-> tab.text="Cupboard"
               3->tab.text="Table"
               4-> tab.text="Accessory"
               5-> tab.text="Furniture"
           }
        }.attach()
    }
}