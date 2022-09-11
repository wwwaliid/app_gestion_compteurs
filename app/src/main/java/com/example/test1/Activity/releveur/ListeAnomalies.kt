package com.example.test1.Activity.releveur

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import android.widget.TextView
import com.example.test1.Activity.MainActivity
import com.example.test1.Adapter.AnomalieAdapter
import com.example.test1.Adapter.UsersAdapter
import com.example.test1.Data.AnomalieRes
import com.example.test1.Data.User
import com.example.test1.Data.UserRes
import com.example.test1.R
import com.example.test1.Retrofit.RetrofitClient
import com.example.test1.Retrofit.RetrofitInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListeAnomalies : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.liste_anomalies_page)

        val retrofit = RetrofitClient.getInstance();
        val iretrofit = retrofit.create(RetrofitInterface::class.java)

        val anomaliesList: ArrayList<AnomalieRes> = ArrayList<AnomalieRes>()
        val listView = findViewById<ListView>(R.id.liste_anomalies_listview)

        val AnomaliesQuery = iretrofit.listAnomalies()
        AnomaliesQuery.enqueue(object : Callback<List<AnomalieRes>> {
            override fun onResponse(call: Call<List<AnomalieRes>>, response: Response<List<AnomalieRes>>) {
                val allAnomalies = response.body()
                if (allAnomalies != null) {
                    for (a in allAnomalies) {
                        anomaliesList.add(
                            AnomalieRes(a.id ,a.numero_compteur, a.description, a.date_creation)
                        )
                        val adapter: AnomalieAdapter = AnomalieAdapter(this@ListeAnomalies, anomaliesList)
                        listView.adapter = adapter
                    }
                    val nbrAnomalies = findViewById<TextView>(R.id.nbrAnomalies_text)
                    nbrAnomalies.text = "Nombre des anomalies : " + allAnomalies.count().toString()
                }
            }

            override fun onFailure(call: Call<List<AnomalieRes>>, t: Throwable) {
                Log.i(MainActivity::class.simpleName, "on FAILURE!!!!")
            }
        })
    }
    override fun onRestart() {
        super.onRestart()
        val retrofit = RetrofitClient.getInstance();
        val iretrofit = retrofit.create(RetrofitInterface::class.java)

        val anomaliesList: ArrayList<AnomalieRes> = ArrayList<AnomalieRes>()
        val listView = findViewById<ListView>(R.id.liste_anomalies_listview)

        val AnomaliesQuery = iretrofit.listAnomalies()
        AnomaliesQuery.enqueue(object : Callback<List<AnomalieRes>> {
            override fun onResponse(call: Call<List<AnomalieRes>>, response: Response<List<AnomalieRes>>) {
                val allAnomalies = response.body()
                if (allAnomalies != null) {
                    for (a in allAnomalies) {
                        anomaliesList.add(
                            AnomalieRes(a.id ,a.numero_compteur, a.description, a.date_creation)
                        )
                        val adapter: AnomalieAdapter = AnomalieAdapter(this@ListeAnomalies, anomaliesList)
                        listView.adapter = adapter
                    }
                    val nbrAnomalies = findViewById<TextView>(R.id.nbrAnomalies_text)
                    nbrAnomalies.text = "Nombre des anomalies : " + allAnomalies.count().toString()
                }
            }

            override fun onFailure(call: Call<List<AnomalieRes>>, t: Throwable) {
                Log.i(MainActivity::class.simpleName, "on FAILURE!!!!")
            }
        })
    }


}





