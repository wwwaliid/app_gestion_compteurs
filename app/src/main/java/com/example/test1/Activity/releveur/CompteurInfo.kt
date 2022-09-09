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

        nomAbonne.text = intent.getStringExtra("nom_abonne");
        index.text = intent.getStringExtra("index");
        ancienIndex.text = intent.getStringExtra("ancien_index");
        adresse.text = intent.getStringExtra("adresse");
        dateReleve.text = intent.getStringExtra("date_releve");
        numero.text = intent.getStringExtra("numero");
        quartier.text = intent.getStringExtra("quartier");

        val ajouter_anomalie : Button = findViewById(R.id.ajouter_anomalie_button)
        val index_edittext : EditText = findViewById(R.id.index_edittext)
        val valider : Button = findViewById(R.id.valider)

        valider.setOnClickListener{
            Log.d("indeeeeeeeeex",index_edittext.text.toString())
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

        ajouter_anomalie.setOnClickListener{
            val intent = Intent(applicationContext, CreerAnomalie::class.java)
            intent.putExtra("numero_compteur", numero.text)
            startActivity(intent)
        }

    }
}
