package com.example.test1.Adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.test1.Activity.admin.UserInfo
import com.example.test1.Activity.releveur.CompteurInfo
import com.example.test1.Data.User
import com.example.test1.Data.UserRes
import com.example.test1.R

class UsersAdapter(private val context: Activity, private val arrayList: ArrayList<UserRes>) : ArrayAdapter<UserRes>(context,
    R.layout.users_list_item, arrayList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater : LayoutInflater = LayoutInflater.from(context)
        val view : View = inflater.inflate(R.layout.users_list_item, null, true)

        val nom : TextView = view.findViewById(R.id.nom_user_text)
        val prenom : TextView = view.findViewById(R.id.prenom_user_text)
        val email : TextView = view.findViewById(R.id.email_user_text)
        val password : TextView = view.findViewById(R.id.password_user_text)


        nom.text = arrayList[position].nom
        email.text = arrayList[position].email
        password.text = arrayList[position].password
        prenom.text = arrayList[position].prenom

        view.setOnClickListener{
            val intent = Intent(view.context, UserInfo::class.java)
            intent.putExtra("id", arrayList[position].id.toString())
            intent.putExtra("nom", arrayList[position].nom)
            intent.putExtra("prenom", arrayList[position].prenom)
            intent.putExtra("email", arrayList[position].email)
            intent.putExtra("password", arrayList[position].password)
            context.startActivity(intent)
        }

        return view
    }

}

