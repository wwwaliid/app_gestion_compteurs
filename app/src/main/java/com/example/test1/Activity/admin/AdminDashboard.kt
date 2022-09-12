package com.example.test1.Activity.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.test1.Activity.admin.SignupPage
import com.example.test1.R

class AdminDashboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.admin_dashboard)

        val liste_users_button : Button = findViewById<Button>(R.id.liste_user_button)
        val signup_button : Button = findViewById<Button>(R.id.home_signup_button)
        val liste_anomalies_button = findViewById<Button>(R.id.liste_anomalies_button)

        liste_users_button.setOnClickListener{
            val intent = Intent(applicationContext, ListeUsers::class.java)
            startActivity(intent)
        }
        signup_button.setOnClickListener{
            val intent = Intent(applicationContext, SignupPage::class.java)
            startActivity(intent)
        }
        liste_anomalies_button.setOnClickListener{
            val intent = Intent(applicationContext, ListeAnomalies::class.java)
            startActivity(intent)
        }
    }
}





