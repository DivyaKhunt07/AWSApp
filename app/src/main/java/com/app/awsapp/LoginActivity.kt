package com.app.awsapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.app.awsapp.databinding.ActivityMainBinding
import com.app.awsapp.model.LoginData
import com.app.awsapp.model.RegistrationClassModel
import com.app.awsapp.model.UserData
import com.app.awsapp.retrofit.RetrofitClient
import com.app.awsapp.retrofit.UserServices
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        setView()
    }

    private fun setView() {
        //btn login
        binding.buttonLogin.setOnClickListener {
            if(binding.editTextEmail.text.toString().isEmpty())
            {
                showMsg("Please enter email")
            }
            else if(binding.editTextPassword.text.toString().isEmpty())
            {
                showMsg("Please enter password")
            }
            else {
                binding.progressbar.visibility=View.VISIBLE
                loginUser()
            }
        }

        //btn signup
        binding.btnSignup.setOnClickListener {
            val intent = Intent(this@LoginActivity, SignupActivity::class.java)
            startActivity(intent)
        }
    }

    fun loginUser() {
        var retrofit = RetrofitClient.getRetrofit(this.applicationContext)
        val service = retrofit.create(UserServices::class.java)
        CoroutineScope(Dispatchers.IO).launch {

            var userdata = LoginData(binding.editTextEmail.text.toString(), binding.editTextPassword.text.toString())
            val response = service.signIn(userdata)
            withContext(Dispatchers.Main) {
                response.enqueue(
                    object : Callback<RegistrationClassModel> {
                        override fun onFailure(call: Call<RegistrationClassModel>, t: Throwable) {
                            binding.progressbar.visibility=View.GONE
                            showMsg("Failure")
                        }

                        override fun onResponse(
                            call: Call<RegistrationClassModel>,
                            response: Response<RegistrationClassModel>
                        ) {
                            binding.progressbar.visibility=View.GONE
                            if(response.isSuccessful) {
                                showMsg(response.message().toString())
                                val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                                startActivity(intent)
                                finish()
                            }
                            else{
                                showMsg("Incorrect login credential")
                            }
                        }
                    }
                )
            }
        }
    }

    fun showMsg(msg:String)
    {
        Toast.makeText(applicationContext,msg,Toast.LENGTH_SHORT).show()
    }
}