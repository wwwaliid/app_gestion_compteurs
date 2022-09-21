package com.example.test1.Activity.admin

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.test1.Activity.MainActivity
import com.example.test1.Data.User
import com.example.test1.R
import com.example.test1.Retrofit.RetrofitClient
import com.example.test1.Retrofit.RetrofitInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup_page)

        val retrofit = RetrofitClient.getInstance();
        val iretrofit = retrofit.create(RetrofitInterface::class.java)

        val username_edittext : EditText = findViewById(R.id.username_edittext)
        val email_edittext : EditText = findViewById(R.id.email_edittext2)
        val password_edittext : EditText = findViewById(R.id.password_edittext2)
        val role_spinner : Spinner = findViewById(R.id.role_spinner)
        val signup_button : Button = findViewById(R.id.signup_button)

        val roles: ArrayList<String> = ArrayList()
        roles.add("Releveur")
        roles.add("Agent de facturation")
        val dataAdapter: ArrayAdapter<String> = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, roles)
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        role_spinner.setAdapter(dataAdapter);

        signup_button.setOnClickListener {
            val nom: String = username_edittext.text.toString()
            val email: String = email_edittext.text.toString()
            val password: String = password_edittext.text.toString()
            var role : String = role_spinner.getSelectedItem().toString()
            if(role =="Releveur"){
                role="1"
            }
            else if(role=="Agent de facturation"){
                role="2"
            }

            if(nom=="" || email=="" || password==""){
                val toast = Toast.makeText(applicationContext,"Il faut remplir tous les champs !", Toast.LENGTH_SHORT)
                toast.show()
            }
            else{
                val user: User = User(nom, email, password, role)
                val req = iretrofit.signUp(user)
                req.enqueue(object : Callback<User> {
                    override fun onResponse(call: Call<User>, response: Response<User>) {

                    }
                    override fun onFailure(call: Call<User>, t: Throwable) {
                        Log.i(MainActivity::class.simpleName, "on FAILURE??????")
                    }
                })

                val dialogBuilder = AlertDialog.Builder(this)
                dialogBuilder.setTitle("Info")
                dialogBuilder.setMessage("Utilisateur créé avec succés")
                dialogBuilder.setIcon(R.drawable.ic_baseline_check_24)
                val dialog = dialogBuilder.create()
                dialog.show()
            }

        }
    }
}