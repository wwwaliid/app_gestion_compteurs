package com.example.test1.Adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.test1.Activity.CompteurInfo
import com.example.test1.Data.Compteur
import com.example.test1.R

class CompteurAdapter(private val context: Activity, private val arrayList: ArrayList<Compteur>) : ArrayAdapter<Compteur>(context,
    R.layout.list_item, arrayList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater : LayoutInflater = LayoutInflater.from(context)
        val view : View = inflater.inflate(R.layout.list_item, null, true)

        val id : TextView = view.findViewById(R.id.id_text)
        val nomAbonne : TextView = view.findViewById(R.id.nomAbonne_text)
        val index : TextView = view.findViewById(R.id.index_text)
        val ancienIndex : TextView = view.findViewById(R.id.ancienIndex_text)
        val rue : TextView = view.findViewById(R.id.rue_text)
        val dateReleve : TextView = view.findViewById(R.id.dateReleve_text)
        val numero : TextView = view.findViewById(R.id.numero_text)


        id.text = arrayList[position].id
        nomAbonne.text = arrayList[position].nomAbonne
        index.text = arrayList[position].index
        ancienIndex.text = arrayList[position].ancien_index
        rue.text = arrayList[position].rue
        dateReleve.text = arrayList[position].date_releve
        numero.text = arrayList[position].numero

        view.setOnClickListener{
            val intent = Intent(view.context, CompteurInfo::class.java)
            intent.putExtra("id", arrayList[position].id)
            intent.putExtra("nomAbonne", arrayList[position].nomAbonne)
            intent.putExtra("index", arrayList[position].index)
            intent.putExtra("ancien_index", arrayList[position].ancien_index)
            intent.putExtra("rue", arrayList[position].rue)
            intent.putExtra("dateReleve", arrayList[position].date_releve)
            intent.putExtra("numero", arrayList[position].numero)

            context.startActivity(intent)
        }


        return view
    }

}

