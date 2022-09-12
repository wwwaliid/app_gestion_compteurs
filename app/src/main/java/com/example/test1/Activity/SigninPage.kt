package com.example.test1.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.test1.Activity.admin.AdminDashboard
import com.example.test1.Activity.agentfacturation.AgentFacturationHome
import com.example.test1.Activity.releveur.ListeCompteurs
import com.example.test1.Activity.releveur.ReleveurHome
import com.example.test1.Data.SigninReq
import com.example.test1.Data.User
import com.example.test1.R
import com.example.test1.Retrofit.RetrofitClient
import com.example.test1.Retrofit.RetrofitInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SigninPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signin_page)

        val retrofit = RetrofitClient.getInstance();
        val iretrofit = retrofit.create(RetrofitInterface::class.java)


        val username_edittext : EditText = findViewById(R.id.email_edittext)
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
                val data = SigninReq(email, password)
                val req = iretrofit.signIn(data)
                req.enqueue(object : Callback<User> {
                    override fun onResponse(call: Call<User>, response: Response<User>) {
                        Log.d("reeeeeeeeeeeeees",response.body().toString())
                        val user_connected : User? = response.body()
                        if (user_connected != null) {
                            Log.d("role",user_connected.role)
                            if(user_connected.role == "0"){
                                val intent = Intent(applicationContext, AdminDashboard::class.java)
                                startActivity(intent)
                            }
                            else if (user_connected.role == "1"){
                                val intent = Intent(applicationContext, ReleveurHome::class.java)
                                intent.putExtra("nom", user_connected.nom)
                                startActivity(intent)
                            }
                            else if (user_connected.role == "2"){
                                val intent = Intent(applicationContext, AgentFacturationHome::class.java)
                                startActivity(intent)
                            }
                        }
                    }
                    override fun onFailure(call: Call<User>, t: Throwable) {
                        Log.i(MainActivity::class.simpleName, "on FAILURE")
                    }
                })
            }


        }
    }
}