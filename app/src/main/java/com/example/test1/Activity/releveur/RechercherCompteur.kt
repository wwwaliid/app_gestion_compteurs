package com.example.test1.Activity.releveur

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.test1.Activity.MainActivity
import com.example.test1.Activity.admin.AdminDashboard
import com.example.test1.Activity.agentfacturation.AgentFacturationHome
import com.example.test1.Adapter.CompteurAdapter
import com.example.test1.Data.Compteur
import com.example.test1.Data.CompteurRecherche
import com.example.test1.Data.CompteurRes
import com.example.test1.Data.User
import com.example.test1.R
import com.example.test1.Retrofit.RetrofitClient
import com.example.test1.Retrofit.RetrofitInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RechercherCompteur : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.rechercher_compteur)

        val retrofit = RetrofitClient.getInstance();
        val iretrofit = retrofit.create(RetrofitInterface::class.java)

        val rechercherNumero_edittext : EditText = findViewById(R.id.recherchernumero_edittext)
        val rechercherQuartier_edittext : EditText = findViewById(R.id.rechercherquartier_edittext)
        val rechercher_button : Button = findViewById(R.id.rechercher_button)
        val listView = findViewById<ListView>(R.id.rechercher_list)

        val compteurList: ArrayList<CompteurRes> = ArrayList<CompteurRes>()

        rechercher_button.setOnClickListener{

            compteurList.clear()
            listView.adapter = null

            val numero : String = rechercherNumero_edittext.text.toString()
            val quartier : String = rechercherQuartier_edittext.text.toString()

            val compteurRecherche = CompteurRecherche(numero,quartier)
            val req = iretrofit.rechercherCompteur(compteurRecherche)
            req.enqueue(object : Callback<List<CompteurRes>> {
                override fun onResponse(call: Call<List<CompteurRes>>, response: Response<List<CompteurRes>>) {
                    Log.d("compteur rechercher", response.body().toString())
                    val compteurResponse = response.body()
                    if (compteurResponse != null) {
                        for (c in compteurResponse) {
                            /*Log.v(
                            MainActivity::class.simpleName,
                            "ID: ${c.id} \n NAME: ${c.name}"
                        )*/
                            compteurList.add(
                                CompteurRes(c.id ,c.numero, c.nom_abonne, c.adresse, c.index, c.ancien_index, c.date_releve, c.quartier, c.anomalie)
                            )
                            val adapter = CompteurAdapter(this@RechercherCompteur, compteurList)
                            listView.adapter = adapter
                        }
                    }
                }
                override fun onFailure(call: Call<List<CompteurRes>>, t: Throwable) {
                    Log.i(MainActivity::class.simpleName, "on FAILURE////////")
                }
            })

        }

    }
}