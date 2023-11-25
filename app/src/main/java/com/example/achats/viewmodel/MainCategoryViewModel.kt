package com.example.achats.viewmodel

import androidx.lifecycle.ViewModel
import com.bumptech.glide.load.engine.Resource
import com.example.achats.data.Product
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class MainCategoryViewModel  @Inject constructor(
    private val firestore: FirebaseFirestore
): ViewModel(){

    private val _specialProducts= MutableStateFlow<Resource<List<Product>>>(Resource.Unspecified())
    val specialProduct: StateFlow<<Resource<List<Product>>> = _specialProducts

}