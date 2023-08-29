package com.example.achats.util

import android.util.Patterns

//in file so that we don't have to write this in a function again and again
fun validateEmail(email: String): RegisterValidation{
    if(email.isEmpty())
        return RegisterValidation.Failed("Email cannot be empty")

    if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        return RegisterValidation.Failed("Wrong email format")

    return RegisterValidation.Success

}

fun validatePassword(password:String):RegisterValidation{
    if(password.isEmpty())
        return RegisterValidation.Failed("Password cannot be empty")

    if(password.length<6)
        return RegisterValidation.Failed("Password should contains a char")

    return RegisterValidation.Success
}