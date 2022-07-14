package com.app.awsapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.app.awsapp.databinding.ActivityMainBinding
import com.app.awsapp.databinding.ActivitySignUpBinding
import com.app.awsapp.model.LoginData
import com.app.awsapp.model.RegistrationClassModel
import com.app.awsapp.model.UserData
import com.app.awsapp.retrofit.RetrofitClient
import com.app.awsapp.retrofit.UserServices
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class SignupActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        setView()
    }

    private fun setView() {
        //btn login
        binding.btnLogin.setOnClickListener {
            onBackPressed()
        }

        //btn signup
        binding.buttonSignup.setOnClickListener {
            if (binding.editTextFirestName.text.isEmpty()) {
                showMsg("Please enter firstname")
            } else if (binding.editTextLastname.text.isEmpty()) {
                showMsg("Please enter lastname")
            } else if (binding.editTextEmail.text.isEmpty()) {
                showMsg("Please enter email")
            } else if (binding.editTextPassword.text.isEmpty()) {
                showMsg("Please enter password")
            } else if (binding.editTextConPassword.text.isEmpty()) {
                showMsg("Please enter confirm password")
            } else if (binding.editTextPassword.text.toString() != binding.editTextConPassword.text.toString()) {
                showMsg("Password and confirm password not match")
            } else if (binding.editTextNumber.text.isEmpty()) {
                showMsg("Please enter number")
            } else {
                binding.progressbar.visibility = View.VISIBLE
                addUser()
            }
        }
    }

    private fun addUser() {
        var retrofit = RetrofitClient.getRetrofit(this.applicationContext)
        val service = retrofit.create(UserServices::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            var userdata = UserData(
                binding.editTextFirestName.text.toString(),
                binding.editTextLastname.text.toString(),
                binding.editTextEmail.text.toString(),
                binding.editTextNumber.text.toString(),
                binding.editTextPassword.text.toString()
            )
            val response = service.signup(userdata)
            withContext(Dispatchers.Main) {
                response.enqueue(
                    object : Callback<RegistrationClassModel> {
                        override fun onFailure(call: Call<RegistrationClassModel>, t: Throwable) {
                            binding.progressbar.visibility = View.GONE
                            showMsg("Failure")
                        }

                        override fun onResponse(
                            call: Call<RegistrationClassModel>,
                            response: Response<RegistrationClassModel>
                        ) {
                            binding.progressbar.visibility = View.GONE
                            if (response.isSuccessful) {
                                showMsg("User register successfully")
                                val intent = Intent(this@SignupActivity, LoginActivity::class.java)
                                startActivity(intent)
                                finish()
                            } else {
                                showMsg("Something went wrong")
                            }
                        }
                    }
                )
            }
        }
    }

    fun showMsg(msg: String) {
        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
    }
}