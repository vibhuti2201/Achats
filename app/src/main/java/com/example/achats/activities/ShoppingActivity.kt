package com.example.achats.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.achats.R
import com.example.achats.databinding.ActivityShoppingBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class ShoppingActivity : AppCompatActivity() {

val binding by lazy {
    ActivityShoppingBinding.inflate(layoutInflater)
}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val navController = findNavController(R.id.shoppingHostFragment)
        binding.bottomNavigation.setupWithNavController(navController)


    }


}