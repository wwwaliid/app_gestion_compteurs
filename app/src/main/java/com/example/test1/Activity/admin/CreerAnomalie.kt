package com.example.test1.Activity.admin

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.test1.Activity.MainActivity
import com.example.test1.Data.Anomalie
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
        val description: EditText = findViewById(R.id.description_anomalie)

        creerAnomalie.setOnClickListener{
            val description : String = description.text.toString()

            if(description==""){
                val toast = Toast.makeText(applicationContext,"Please enter all fields", Toast.LENGTH_SHORT)
                toast.show()
            }
            else{
                val anomalie = Anomalie(description)
                val req = iretrofit.creerAnomalie(anomalie)
                req.enqueue(object : Callback<Anomalie> {
                    override fun onResponse(call: Call<Anomalie>, response: Response<Anomalie>) {

                    }
                    override fun onFailure(call: Call<Anomalie>, t: Throwable) {
                        Log.i(MainActivity::class.simpleName, "on FAILURE??????")
                    }
                })
                val dialogBuilder = AlertDialog.Builder(this)
                dialogBuilder.setTitle("Info")
                dialogBuilder.setMessage("Anomalie créé avec succés")
                dialogBuilder.setIcon(R.drawable.ic_baseline_check_24)
                val dialog = dialogBuilder.create()
                dialog.show()
            }
        }
    }
}