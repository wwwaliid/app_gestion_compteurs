package com.example.test1.Activity.releveur

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.test1.Activity.MainActivity
import com.example.test1.Activity.admin.SignupPage
import com.example.test1.Data.AnomalieRes
import com.example.test1.Data.EditIndexReq
import com.example.test1.Data.UserRes
import com.example.test1.R
import com.example.test1.Retrofit.RetrofitClient
import com.example.test1.Retrofit.RetrofitInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*


class AnomalieInfo : AppCompatActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.anomalie_info)

        val retrofit = RetrofitClient.getInstance();
        val iretrofit = retrofit.create(RetrofitInterface::class.java)

        val intent = intent

        val description: TextView = findViewById(R.id.description)

        description.text = intent.getStringExtra("description");

        val supprimer_anomalie : Button = findViewById(R.id.supprimer_anomalie_button)

        supprimer_anomalie.setOnClickListener{
            finish()
            val req = iretrofit.deleteAnomalie(intent.getStringExtra("id").toString())
            req.enqueue(object : Callback<AnomalieRes> {
                override fun onResponse(call: Call<AnomalieRes>, response: Response<AnomalieRes>) {
                    Log.i(MainActivity::class.simpleName, "USER DELETED")
                }
                override fun onFailure(call: Call<AnomalieRes>, t: Throwable) {
                    Log.i(MainActivity::class.simpleName, "on FAILURE!!!!")
                }
            })
        }

    }
}
