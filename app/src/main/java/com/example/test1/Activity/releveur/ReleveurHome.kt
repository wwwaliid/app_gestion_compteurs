package com.example.test1.Activity.releveur

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.test1.R

class ReleveurHome : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.releveur_home)

        val intent = intent

        val releveur_home_title: TextView = findViewById(R.id.releveur_home_title)

        releveur_home_title.text = intent.getStringExtra("nom") + " " + intent.getStringExtra("prenom")

        val liste_compteurs_button : Button = findViewById(R.id.liste_compteur_button)
        val creer_compteur_button : Button = findViewById(R.id.creer_compteur_button)
        val rechercher_compteur_button : Button = findViewById(R.id.rechercher_compteur_button)
        val contacter_admin_button : Button = findViewById(R.id.contacter_admin_button)

        liste_compteurs_button.setOnClickListener{
            val intent = Intent(applicationContext, ListeCompteurs::class.java)
            startActivity(intent)
        }
        creer_compteur_button.setOnClickListener{
            val intent = Intent(applicationContext, CreerCompteur::class.java)
            startActivity(intent)
        }
        rechercher_compteur_button.setOnClickListener{
            val intent = Intent(applicationContext, RechercherCompteur::class.java)
            startActivity(intent)
        }
        contacter_admin_button.setOnClickListener{
            val callIntent = Intent(Intent.ACTION_DIAL)
            callIntent.data = Uri.parse("tel:0123456789")
            startActivity(callIntent)
        }

    }
}





