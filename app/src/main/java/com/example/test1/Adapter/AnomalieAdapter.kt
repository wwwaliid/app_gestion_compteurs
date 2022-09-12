package com.example.test1.Adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.test1.Activity.admin.UserInfo
import com.example.test1.Activity.releveur.AnomalieInfo
import com.example.test1.Activity.releveur.CompteurInfo
import com.example.test1.Data.AnomalieRes
import com.example.test1.Data.User
import com.example.test1.Data.UserRes
import com.example.test1.R

class AnomalieAdapter(private val context: Activity, private val arrayList: ArrayList<AnomalieRes>) : ArrayAdapter<AnomalieRes>(context,
    R.layout.anomalies_list_item, arrayList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater : LayoutInflater = LayoutInflater.from(context)
        val view : View = inflater.inflate(R.layout.anomalies_list_item, null, true)

        val description_anomalie : TextView = view.findViewById(R.id.description_anomalie_text)

        description_anomalie.text = arrayList[position].description

        /*view.setOnClickListener{
            val intent = Intent(view.context, AnomalieInfo::class.java)
            intent.putExtra("id", arrayList[position].id.toString())
            intent.putExtra("description", arrayList[position].description)

            context.startActivity(intent)
        }*/


        return view
    }

}

