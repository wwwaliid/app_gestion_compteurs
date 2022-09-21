package com.example.test1.Adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.test1.Activity.releveur.CompteurInfo
import com.example.test1.Data.Compteur
import com.example.test1.Data.CompteurRes
import com.example.test1.R
import org.w3c.dom.Text

class CompteurAdapter(private val context: Activity, private val arrayList: ArrayList<CompteurRes>) : ArrayAdapter<CompteurRes>(context,
    R.layout.list_item, arrayList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater : LayoutInflater = LayoutInflater.from(context)
        val view : View = inflater.inflate(R.layout.list_item, null, true)

        val nomAbonne : TextView = view.findViewById(R.id.nomAbonne_text)
        val index : TextView = view.findViewById(R.id.index_text)
        val dateReleve : TextView = view.findViewById(R.id.dateReleve_text)
        val numero : TextView = view.findViewById(R.id.numero_text)
        val quartier : TextView = view.findViewById(R.id.quartier_text)
        val type : ImageView = view.findViewById(R.id.type_image_list)


        nomAbonne.text = arrayList[position].nom_abonne
        index.text = arrayList[position].index
        dateReleve.text = arrayList[position].date_releve
        numero.text = arrayList[position].numero
        quartier.text = arrayList[position].quartier

        if(arrayList[position].type == "Eau"){
            type.setBackgroundResource(R.drawable.water)
        }
        else if(arrayList[position].type == "Électricité"){
            type.setBackgroundResource(R.drawable.electricity)
        }


        view.setOnClickListener{
            val intent = Intent(view.context, CompteurInfo::class.java)
            intent.putExtra("id", arrayList[position].id.toString())
            intent.putExtra("nom_abonne", arrayList[position].nom_abonne)
            intent.putExtra("index", arrayList[position].index)
            intent.putExtra("ancien_index", arrayList[position].ancien_index)
            intent.putExtra("adresse", arrayList[position].adresse)
            intent.putExtra("date_releve", arrayList[position].date_releve)
            intent.putExtra("numero", arrayList[position].numero)
            intent.putExtra("quartier", arrayList[position].quartier)
            intent.putExtra("anomalie", arrayList[position].anomalie)
            intent.putExtra("type", arrayList[position].type)

            context.startActivity(intent)
        }


        return view
    }

}

