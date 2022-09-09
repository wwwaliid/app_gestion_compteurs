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
        val email : TextView = view.findViewById(R.id.email_user_text)
        val password : TextView = view.findViewById(R.id.password_user_text)
        val role : TextView = view.findViewById(R.id.role_user_text)


        nom.text = arrayList[position].nom
        email.text = arrayList[position].email
        password.text = arrayList[position].password
        if(arrayList[position].role == "1"){
            role.text = "Releveur"
        }
        else if(arrayList[position].role == "2"){
            role.text = "Agent de facturation"
        }

        view.setOnClickListener{
            val intent = Intent(view.context, UserInfo::class.java)
            intent.putExtra("id", arrayList[position].id.toString())
            intent.putExtra("nom", arrayList[position].nom)
            intent.putExtra("email", arrayList[position].email)
            if(arrayList[position].role == "1"){
                intent.putExtra("role", "Releveur")
            }
            else if(arrayList[position].role == "2"){
                intent.putExtra("role", "Agent de facturation")
            }
            intent.putExtra("password", arrayList[position].password)
            context.startActivity(intent)
        }

        return view
    }

}

