package com.example.achats.viewmodel

import androidx.lifecycle.ViewModel
import com.bumptech.glide.load.engine.Resource
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class MainCategoryViewModel  @Inject constructor(
    private val firestore: FirebaseFirestore
): ViewModel(){

    private val specialProducts= MutableStateFlow<Resource>()
}