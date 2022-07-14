package com.app.awsapp.model

data class RegistrationClassModel(var success:Boolean, var message:String, var data:UserData)

data class UserData(var firstName:String,var lastName:String,var email:String,var phoneNumber:String,var password:String)
