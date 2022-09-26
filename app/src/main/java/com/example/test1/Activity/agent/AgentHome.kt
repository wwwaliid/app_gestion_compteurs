package com.example.test1.Activity.agent

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.test1.Activity.admin.ListeAnomalies
import com.example.test1.Activity.admin.ListeUsers
import com.example.test1.Activity.admin.SignupPage
import com.example.test1.Activity.releveur.ListeCompteurs
import com.example.test1.Activity.releveur.RechercherCompteur
import com.example.test1.R

class AgentHome  : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.agent_home)

        val intent = intent
        val role = intent.getStringExtra("role")

        val liste_compteurs_button : Button = findViewById(R.id.agent_liste_button)
        val rechercher_button : Button = findViewById<Button>(R.id.agent_rechercher_button)

        liste_compteurs_button.setOnClickListener{
            val intent = Intent(applicationContext, ListeCompteurs::class.java)
            intent.putExtra("role",role)
            startActivity(intent)
        }
        rechercher_button.setOnClickListener{
            val intent = Intent(applicationContext, RechercherCompteur::class.java)
            intent.putExtra("role",role)
            startActivity(intent)
        }
    }
}