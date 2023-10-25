package com.example.achats.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.achats.R
import com.example.achats.databinding.ActivityShoppingBinding

class ShoppingActivity : AppCompatActivity() {
    val binding by lazy{
        ActivityShoppingBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)

        val navController= findNavController(R.id.shopping_graph)
        binding.bottomNavigation.setupWithNavController(navController)
    }
}