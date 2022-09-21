package com.example.test1.Activity.releveur

import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.test1.Activity.MainActivity
import com.example.test1.Data.AnomalieRes
import com.example.test1.Data.CompteurRes
import com.example.test1.Data.EditAnomalieReq
import com.example.test1.Data.EditIndexReq
import com.example.test1.R
import com.example.test1.Retrofit.RetrofitClient
import com.example.test1.Retrofit.RetrofitInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*


class CompteurInfo : AppCompatActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.compteur_info)

        val retrofit = RetrofitClient.getInstance();
        val iretrofit = retrofit.create(RetrofitInterface::class.java)

        val intent = intent

        val nomAbonne: TextView = findViewById(R.id.nomAbonne)
        val index: TextView = findViewById(R.id.index)
        val ancienIndex: TextView = findViewById(R.id.ancienIndex)
        val adresse: TextView = findViewById(R.id.adresse)
        val dateReleve: TextView = findViewById(R.id.dateReleve)
        val numero: TextView = findViewById(R.id.numero)
        val quartier: TextView = findViewById(R.id.quartier)
        val anomalie: TextView = findViewById(R.id.anomalie)
        val type: TextView = findViewById(R.id.type)

        nomAbonne.text = intent.getStringExtra("nom_abonne")
        index.text = intent.getStringExtra("index")
        ancienIndex.text = intent.getStringExtra("ancien_index")
        adresse.text = intent.getStringExtra("adresse")
        dateReleve.text = intent.getStringExtra("date_releve")
        numero.text = intent.getStringExtra("numero")
        quartier.text = intent.getStringExtra("quartier")
        anomalie.text = intent.getStringExtra("anomalie")
        type.text = intent.getStringExtra("type")

        if(intent.getStringExtra("type") == "Eau"){
            type.setCompoundDrawablesWithIntrinsicBounds(R.drawable.water2, 0, 0, 0)
        }
        else if(intent.getStringExtra("type") == "Électricité"){
            type.setCompoundDrawablesWithIntrinsicBounds(R.drawable.electricity2, 0, 0, 0)
        }

        val index_edittext : EditText = findViewById(R.id.index_edittext)
        val valider : Button = findViewById(R.id.valider)

        valider.setOnClickListener{
            ancienIndex.text = index.text
            index.text = index_edittext.text.toString()

            val c: Date = Calendar.getInstance().getTime()
            val df = SimpleDateFormat("dd/MM/yyyy hh:mm", Locale.getDefault())
            val formattedDate: String = df.format(c)
            println("Current time => $formattedDate")
            dateReleve.text = formattedDate

            val data : EditIndexReq = EditIndexReq(intent.getStringExtra("id").toString(), index_edittext.text.toString(), intent.getStringExtra("index").toString(), formattedDate)
            val req = iretrofit.editIndex(data)
            req.enqueue(object : Callback<EditIndexReq> {
                override fun onResponse(call: Call<EditIndexReq>, response: Response<EditIndexReq>) {

                }
                override fun onFailure(call: Call<EditIndexReq>, t: Throwable) {
                    Log.i(MainActivity::class.simpleName, "on FAILURE!!!!")
                }
            })
        }

        val anomalie_spinner : Spinner = findViewById(R.id.anomalie_spinner)
        val anomaliesList: ArrayList<String> = ArrayList<String>()
        val AnomaliesQuery = iretrofit.listAnomalies()
        anomaliesList.add("Aucune")

        AnomaliesQuery.enqueue(object : Callback<List<AnomalieRes>> {
            override fun onResponse(call: Call<List<AnomalieRes>>, response: Response<List<AnomalieRes>>) {
                val allAnomalies = response.body()
                if (allAnomalies != null) {
                    for (a in allAnomalies) {
                        anomaliesList.add(a.description)
                    }
                }
            }
            override fun onFailure(call: Call<List<AnomalieRes>>, t: Throwable) {
                Log.i(MainActivity::class.simpleName, "on FAILURE!!!!")
            }
        })
        val dataAdapter: ArrayAdapter<String> = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, anomaliesList)
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        anomalie_spinner.setAdapter(dataAdapter)

        val modifier_anomalie =findViewById<Button>(R.id.modifier_anomalie_button)
        modifier_anomalie.setOnClickListener{
            var anomalie_description : String = anomalie_spinner.getSelectedItem().toString()
            val req = iretrofit.editAnomalie(EditAnomalieReq(intent.getStringExtra("id").toString(), anomalie_description))

            req.enqueue(object : Callback<EditAnomalieReq> {
                override fun onResponse(call: Call<EditAnomalieReq>, response: Response<EditAnomalieReq>) {

                }
                override fun onFailure(call: Call<EditAnomalieReq>, t: Throwable) {
                    Log.i(MainActivity::class.simpleName, "on FAILURE!!!!")
                }
            })
            anomalie.text = anomalie_description
        }


        val supprimer_compteur : Button = findViewById(R.id.supprimer_compteur_button)

        supprimer_compteur.setOnClickListener{
            finish()
            val req = iretrofit.deleteCompteur(intent.getStringExtra("id").toString())
            req.enqueue(object : Callback<CompteurRes> {
                override fun onResponse(call: Call<CompteurRes>, response: Response<CompteurRes>) {
                    Log.i(MainActivity::class.simpleName, "COMPTEUR DELETED")
                }
                override fun onFailure(call: Call<CompteurRes>, t: Throwable) {
                    Log.i(MainActivity::class.simpleName, "on FAILURE!!!!")
                }
            })
        }

    }
}
