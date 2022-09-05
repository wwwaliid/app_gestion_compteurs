package com.example.test1.Adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.test1.Activity.releveur.CompteurInfo
import com.example.test1.Data.Compteur
import com.example.test1.Data.CompteurRes
import com.example.test1.R

class CompteurAdapter(private val context: Activity, private val arrayList: ArrayList<CompteurRes>) : ArrayAdapter<CompteurRes>(context,
    R.layout.list_item, arrayList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater : LayoutInflater = LayoutInflater.from(context)
        val view : View = inflater.inflate(R.layout.list_item, null, true)

        val nomAbonne : TextView = view.findViewById(R.id.nomAbonne_text)
        val index : TextView = view.findViewById(R.id.index_text)
        val ancienIndex : TextView = view.findViewById(R.id.ancienIndex_text)
        val rue : TextView = view.findViewById(R.id.adresse_text)
        val dateReleve : TextView = view.findViewById(R.id.dateReleve_text)
        val numero : TextView = view.findViewById(R.id.numero_text)


        nomAbonne.text = arrayList[position].nom_abonne
        index.text = arrayList[position].index
        ancienIndex.text = arrayList[position].ancien_index
        rue.text = arrayList[position].adresse
        dateReleve.text = arrayList[position].date_releve
        numero.text = arrayList[position].numero

        view.setOnClickListener{
            val intent = Intent(view.context, CompteurInfo::class.java)
            intent.putExtra("id", arrayList[position].id.toString())
            intent.putExtra("nom_abonne", arrayList[position].nom_abonne)
            intent.putExtra("index", arrayList[position].index)
            intent.putExtra("ancien_index", arrayList[position].ancien_index)
            intent.putExtra("adresse", arrayList[position].adresse)
            intent.putExtra("date_releve", arrayList[position].date_releve)
            intent.putExtra("numero", arrayList[position].numero)

            context.startActivity(intent)
        }


        return view
    }

}

