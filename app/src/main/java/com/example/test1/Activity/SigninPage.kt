package com.example.test1.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import com.example.test1.Data.SigninReq
import com.example.test1.Data.user
import com.example.test1.R
import com.example.test1.Retrofit.RetrofitClient
import com.example.test1.Retrofit.RetrofitInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.POST


class SigninPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signin_page)

        val retrofit = RetrofitClient.getInstance();
        val iretrofit = retrofit.create(RetrofitInterface::class.java)


        val username_edittext : EditText = findViewById(R.id.username_edittext)
        val password_edittext : EditText = findViewById(R.id.password_edittext)
        val signin_button : Button = findViewById(R.id.buttonSignin)

        signin_button.setOnClickListener{
            val email : String = username_edittext.text.toString()
            val password : String = password_edittext.text.toString()

            if(email=="" || password==""){
                val toast = Toast.makeText(applicationContext,"Please enter both fields", Toast.LENGTH_SHORT)
                toast.show()
            }
            else{
                val data : SigninReq = SigninReq(email, password)
                val req = iretrofit.signIn(data)
                req.enqueue(object : Callback<user> {
                    override fun onResponse(call: Call<user>, response: Response<user>) {
                        Log.d("reeeeeeeeeeeeees",response.body().toString())
                        val user_connected : user? = response.body()
                        if (user_connected != null) {
                            Log.d("role",user_connected.role)
                            if(user_connected.role == "0"){
                                val intent = Intent(applicationContext, AdminDashboard::class.java)
                                startActivity(intent)
                            }
                            else if (user_connected.role == "1"){
                                val intent = Intent(applicationContext, MainActivity::class.java)
                                startActivity(intent)
                            }
                            else if (user_connected.role == "2"){

                            }
                        }
                    }
                    override fun onFailure(call: Call<user>, t: Throwable) {
                        Log.i(MainActivity::class.simpleName, "on FAILURE??????")
                    }
                })
            }


        }
    }
}