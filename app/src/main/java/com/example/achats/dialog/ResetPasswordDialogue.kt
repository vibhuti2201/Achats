package com.example.achats.dialog

import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.achats.R
import com.google.android.material.bottomsheet.BottomSheetDialog

fun Fragment.setupBottomSheetDialog(){
    val dialog=BottomSheetDialog(requireContext())
    val view= layoutInflater.inflate(R.layout.reset_password_dialogue,null)
    dialog.setContentView(view)
    dialog.show()

    val edEmail= view.findViewById<EditText>(R.id.edResetPassword)
    val buttonSend= view.findViewById<Button>(R.id.buttonSendResetPassword)
    val buttonCancel= view.findViewById<Button>(R.id.buttonCancelResetPassword)

    buttonSend.setOnClickListener {
        val email= edEmail.text.toString().trim()

    }

    


}