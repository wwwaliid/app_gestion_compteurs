package com.example.test1.Activity.releveur

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.test1.Activity.admin.ListeAnomalies
import com.example.test1.R

class ReleveurHome : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.releveur_home)


        val liste_compteurs_button : Button = findViewById<Button>(R.id.liste_compteur_button)
        val creer_compteur_button : Button = findViewById<Button>(R.id.creer_compteur_button)
        val rechercher_compteur_button : Button = findViewById<Button>(R.id.rechercher_compteur_button)

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
    }
}





