package com.example.test1.Activity.agent

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
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


class AgentCompteurInfo : AppCompatActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.agent_compteur_info)

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

        val x = Integer.parseInt(intent.getStringExtra("index"))
        val y = Integer.parseInt(intent.getStringExtra("ancien_index"))

        val conso = x-y
        Log.d("consommation : ", conso.toString())

        if(intent.getStringExtra("type") == "Eau"){
            type.setCompoundDrawablesWithIntrinsicBounds(R.drawable.water2, 0, 0, 0)
        }
        else if(intent.getStringExtra("type") == "Électricité"){
            type.setCompoundDrawablesWithIntrinsicBounds(R.drawable.electricity2, 0, 0, 0)
        }

    }
}
