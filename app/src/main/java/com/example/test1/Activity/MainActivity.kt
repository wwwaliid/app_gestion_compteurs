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

        val retrofit = RetrofitClient.getInstance();
        val iretrofit = retrofit.create(RetrofitInterface::class.java)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.page_compteurs)

        val compteurList:ArrayList<Compteur> = ArrayList<Compteur>()
        val listView = findViewById<ListView>(R.id.list1)

        fun sendListCompteursReq() {
            val compteurRes = iretrofit.listCompteurs()
            compteurRes.enqueue(object : Callback<List<Compteur>> {
                override fun onResponse(call: Call<List<Compteur>>, response: Response<List<Compteur>>) {
                    val allCompteur = response.body()
                    if (allCompteur != null) {
                        for(c in allCompteur) {
                            /*Log.v(
                                MainActivity::class.simpleName,
                                "ID: ${c.id} \n NAME: ${c.name}"
                            )*/
                            compteurList.add(Compteur(c.id, c.nomAbonne, c.index, c.ancien_index, c.rue, c.date_releve, c.numero))
                            val adapter: CompteurAdapter = CompteurAdapter(this@MainActivity, compteurList)
                            listView.adapter = adapter

                        }
                        val nbrCompteur = findViewById<TextView>(R.id.nbrCompteur)
                        nbrCompteur.text = "Nombre de compteurs : " + allCompteur.count().toString()
                    }
                }
                override fun onFailure(call: Call<List<Compteur>>, t: Throwable) {
                    Log.i(MainActivity::class.simpleName, "on FAILURE!!!!")
                }
            })
        }
        sendListCompteursReq()

        val refresh_button : Button = findViewById(R.id.refresh)
        refresh_button.setOnClickListener{
            compteurList.clear()
            listView.adapter = null
            sendListCompteursReq()
        }




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