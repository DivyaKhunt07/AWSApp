package com.app.awsapp.model

data class LoginClassModel(var success:Boolean, var message:String, var data:LoginData)

data class LoginData(var phoneNumber:String,var password:String)
