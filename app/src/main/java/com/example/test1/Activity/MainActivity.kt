package com.example.test1.Activity

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.test1.Adapter.CompteurAdapter
import com.example.test1.Data.Compteur
import com.example.test1.R
import com.example.test1.Retrofit.RetrofitClient
import com.example.test1.Retrofit.RetrofitInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.page_compteurs)


        /*val buttonSignin = findViewById<Button>(R.id.buttonSignin)
        buttonSignin.setOnClickListener {
            val textView: TextView = findViewById(R.id.text1) as TextView
            textView.text="hmm"
            //handleSignin();
            /*val intent = Intent(this, HomePage::class.java)
            startActivity(intent)*/

            /*Thread {
                val compteurRes = iretrofit.listCompteurs().execute()
            }.start()*/

        }*/

    }

    private fun handleSignin() {

    }
}