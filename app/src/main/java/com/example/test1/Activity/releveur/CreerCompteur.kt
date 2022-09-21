package com.example.test1.Activity.releveur

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.test1.Activity.MainActivity
import com.example.test1.Activity.admin.AdminDashboard
import com.example.test1.Activity.agentfacturation.AgentFacturationHome
import com.example.test1.Data.Compteur
import com.example.test1.Data.User
import com.example.test1.R
import com.example.test1.Retrofit.RetrofitClient
import com.example.test1.Retrofit.RetrofitInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreerCompteur : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.creer_compteur)

        val retrofit = RetrofitClient.getInstance();
        val iretrofit = retrofit.create(RetrofitInterface::class.java)

        val numero_edittext : EditText = findViewById(R.id.numero_edittext)
        val nomAbonne_edittext : EditText = findViewById(R.id.nomAbonne_edittext)
        val adresse_edittext : EditText = findViewById(R.id.adresse_edittext)
        val quartier_edittext : EditText = findViewById(R.id.quartier_edittext)
        val type_spinner : Spinner = findViewById(R.id.type_spinner)
        val creercompteur_button : Button = findViewById(R.id.creerCompteur_button)

        val types: ArrayList<String> = ArrayList()
        types.add("Eau")
        types.add("Électricité")
        val dataAdapter: ArrayAdapter<String> = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, types)
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        type_spinner.setAdapter(dataAdapter);

        creercompteur_button.setOnClickListener{
            val numero : String = numero_edittext.text.toString()
            val nomAbonne : String = nomAbonne_edittext.text.toString()
            val adresse : String = adresse_edittext.text.toString()
            val quartier : String = quartier_edittext.text.toString()
            var type : String = type_spinner.getSelectedItem().toString()

            if(numero=="" || nomAbonne=="" || adresse=="" || quartier==""){
                val toast = Toast.makeText(applicationContext,"Please enter all fields", Toast.LENGTH_SHORT)
                toast.show()
            }
            else{
                val compteur = Compteur(numero, nomAbonne, adresse, "0", "0", "",quartier, "Aucune", type)
                val req = iretrofit.creerCompteur(compteur)
                req.enqueue(object : Callback<Compteur> {
                    override fun onResponse(call: Call<Compteur>, response: Response<Compteur>) {

                    }
                    override fun onFailure(call: Call<Compteur>, t: Throwable) {
                        Log.i(MainActivity::class.simpleName, "on FAILURE??????")
                    }
                })
            }
        }

    }
}