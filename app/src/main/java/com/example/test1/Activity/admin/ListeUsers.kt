package com.example.test1.Activity.admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import android.widget.TextView
import com.example.test1.Activity.MainActivity
import com.example.test1.Adapter.UsersAdapter
import com.example.test1.Data.User
import com.example.test1.Data.UserRes
import com.example.test1.R
import com.example.test1.Retrofit.RetrofitClient
import com.example.test1.Retrofit.RetrofitInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListeUsers : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.liste_users_page)

        val retrofit = RetrofitClient.getInstance();
        val iretrofit = retrofit.create(RetrofitInterface::class.java)

        val usersList: ArrayList<UserRes> = ArrayList<UserRes>()
        val listView = findViewById<ListView>(R.id.liste_users_listview)

        val usersQuery = iretrofit.listUsers()
        usersQuery.enqueue(object : Callback<List<UserRes>> {
            override fun onResponse(call: Call<List<UserRes>>, response: Response<List<UserRes>>) {
                val allUsers = response.body()
                if (allUsers != null) {
                    for (u in allUsers) {
                        usersList.add(
                            UserRes(u.id, u.nom, u.prenom, u.email, u.password, u.role)
                        )
                        val adapter: UsersAdapter = UsersAdapter(this@ListeUsers, usersList)
                        listView.adapter = adapter
                    }
                    val nbrUser = findViewById<TextView>(R.id.nbrUsers_text)
                    nbrUser.text = "Nombre des utilisateurs : " + allUsers.count().toString()
                }
            }

            override fun onFailure(call: Call<List<UserRes>>, t: Throwable) {
                Log.i(MainActivity::class.simpleName, "on FAILURE!!!!")
            }
        })
    }

    override fun onRestart() {
        super.onRestart()
        val retrofit = RetrofitClient.getInstance();
        val iretrofit = retrofit.create(RetrofitInterface::class.java)

        val usersList: ArrayList<UserRes> = ArrayList<UserRes>()
        val listView = findViewById<ListView>(R.id.liste_users_listview)

        val usersQuery = iretrofit.listUsers()
        usersQuery.enqueue(object : Callback<List<UserRes>> {
            override fun onResponse(call: Call<List<UserRes>>, response: Response<List<UserRes>>) {
                val allUsers = response.body()
                if (allUsers != null) {
                    for (u in allUsers) {
                        usersList.add(
                            UserRes(u.id ,u.nom, u.prenom ,u.email, u.password, u.role)
                        )
                        val adapter: UsersAdapter = UsersAdapter(this@ListeUsers, usersList)
                        listView.adapter = adapter
                    }
                    val nbrUser = findViewById<TextView>(R.id.nbrUsers_text)
                    nbrUser.text = "Nombre des utilisateurs : " + allUsers.count().toString()
                }
            }

            override fun onFailure(call: Call<List<UserRes>>, t: Throwable) {
                Log.i(MainActivity::class.simpleName, "on FAILURE!!!!")
            }
        })
    }
}





