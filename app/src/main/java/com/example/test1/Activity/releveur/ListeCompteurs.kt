package com.example.test1.Activity.releveur

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.test1.Activity.MainActivity
import com.example.test1.Adapter.CompteurAdapter
import com.example.test1.Data.Compteur
import com.example.test1.Data.CompteurRes
import com.example.test1.R
import com.example.test1.Retrofit.RetrofitClient
import com.example.test1.Retrofit.RetrofitInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListeCompteurs : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.page_compteurs)

        val retrofit = RetrofitClient.getInstance();
        val iretrofit = retrofit.create(RetrofitInterface::class.java)

        val compteurList: ArrayList<CompteurRes> = ArrayList<CompteurRes>()
        val listView = findViewById<ListView>(R.id.list1)

        fun sendListCompteursReq() {
            val compteurRes = iretrofit.listCompteurs()
            compteurRes.enqueue(object : Callback<List<CompteurRes>> {
                override fun onResponse(call: Call<List<CompteurRes>>, response: Response<List<CompteurRes>>) {
                    val allCompteur = response.body()
                    if (allCompteur != null) {
                        for (c in allCompteur) {
                            /*Log.v(
                            MainActivity::class.simpleName,
                            "ID: ${c.id} \n NAME: ${c.name}"
                        )*/
                            compteurList.add(
                                CompteurRes(c.id ,c.numero, c.nom_abonne, c.adresse, c.index, c.ancien_index, c.date_releve, c.quartier, c.anomalie)
                            )
                            val adapter: CompteurAdapter = CompteurAdapter(this@ListeCompteurs, compteurList)
                            listView.adapter = adapter

                        }
                        val nbrCompteur = findViewById<TextView>(R.id.nbrCompteur)
                        nbrCompteur.text = "Nombre de compteurs : " + allCompteur.count().toString()
                    }
                }

                override fun onFailure(call: Call<List<CompteurRes>>, t: Throwable) {
                    Log.i(MainActivity::class.simpleName, "on FAILURE!!!!")
                }
            })
        }
        sendListCompteursReq()

        /*val refresh_button: Button = findViewById(R.id.refresh)
        refresh_button.setOnClickListener {
            compteurList.clear()
            listView.adapter = null
            sendListCompteursReq()
        }*/
    }
    override fun onRestart(){
        super.onRestart()
        val retrofit = RetrofitClient.getInstance();
        val iretrofit = retrofit.create(RetrofitInterface::class.java)

        val compteurList: ArrayList<CompteurRes> = ArrayList<CompteurRes>()
        val listView = findViewById<ListView>(R.id.list1)

        val compteurRes = iretrofit.listCompteurs()
        compteurRes.enqueue(object : Callback<List<CompteurRes>> {
            override fun onResponse(call: Call<List<CompteurRes>>, response: Response<List<CompteurRes>>) {
                val allCompteur = response.body()
                if (allCompteur != null) {
                    for (c in allCompteur) {
                        /*Log.v(
                        MainActivity::class.simpleName,
                        "ID: ${c.id} \n NAME: ${c.name}"
                    )*/
                        compteurList.add(
                            CompteurRes(c.id ,c.numero, c.nom_abonne, c.adresse, c.index, c.ancien_index, c.date_releve, c.quartier,c.anomalie)
                        )
                        val adapter: CompteurAdapter = CompteurAdapter(this@ListeCompteurs, compteurList)
                        listView.adapter = adapter

                    }
                    val nbrCompteur = findViewById<TextView>(R.id.nbrCompteur)
                    nbrCompteur.text = "Nombre de compteurs : " + allCompteur.count().toString()
                }
            }

            override fun onFailure(call: Call<List<CompteurRes>>, t: Throwable) {
                Log.i(MainActivity::class.simpleName, "on FAILURE!!!!")
            }
        })
    }
}