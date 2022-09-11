package com.example.test1.Activity.releveur

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.test1.Activity.MainActivity
import com.example.test1.Data.Anomalie
import com.example.test1.Data.Compteur
import com.example.test1.R
import com.example.test1.Retrofit.RetrofitClient
import com.example.test1.Retrofit.RetrofitInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class CreerAnomalie : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.creer_anomalie)

        val retrofit = RetrofitClient.getInstance();
        val iretrofit = retrofit.create(RetrofitInterface::class.java)

        val intent = intent

        val creerAnomalie : Button = findViewById(R.id.creerAnomalie_button)
        val numero_compteur: EditText = findViewById(R.id.numero_compteur_anomalie)
        val description: EditText = findViewById(R.id.description_anomalie)

        numero_compteur.hint = intent.getStringExtra("numero_compteur")
        Log.d("hmmmmm numero",intent.getStringExtra("numero_compteur").toString())

        creerAnomalie.setOnClickListener{
            val description : String = description.text.toString()

            val c: Date = Calendar.getInstance().getTime()
            val df = SimpleDateFormat("dd/MM/yyyy hh:mm", Locale.getDefault())
            val date_creation: String = df.format(c)

            if(description==""){
                val toast = Toast.makeText(applicationContext,"Please enter all fields", Toast.LENGTH_SHORT)
                toast.show()
            }
            else{
                val anomalie = Anomalie(intent.getStringExtra("numero_compteur").toString(), description, date_creation)
                val req = iretrofit.creerAnomalie(anomalie)
                req.enqueue(object : Callback<Anomalie> {
                    override fun onResponse(call: Call<Anomalie>, response: Response<Anomalie>) {

                    }
                    override fun onFailure(call: Call<Anomalie>, t: Throwable) {
                        Log.i(MainActivity::class.simpleName, "on FAILURE??????")
                    }
                })
            }
        }
    }
}