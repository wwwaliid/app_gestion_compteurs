package com.example.test1.Activity.admin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.test1.Activity.MainActivity
import com.example.test1.Data.EditIndexReq
import com.example.test1.Data.UserRes
import com.example.test1.R
import com.example.test1.Retrofit.RetrofitClient
import com.example.test1.Retrofit.RetrofitInterface
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class UserInfo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_info)

        val retrofit = RetrofitClient.getInstance();
        val iretrofit = retrofit.create(RetrofitInterface::class.java)

        val intent = intent

        val nom: TextView = findViewById(R.id.nom)
        val prenom: TextView = findViewById(R.id.prenom)
        val email: TextView = findViewById(R.id.email)
        val password: TextView = findViewById(R.id.password)

        nom.text = intent.getStringExtra("nom")
        prenom.text = intent.getStringExtra("prenom")
        email.text = intent.getStringExtra("email")
        password.text = intent.getStringExtra("password")

        val supprimer : Button = findViewById(R.id.supprimer_button)

        supprimer.setOnClickListener{
            val dialogBuilder = AlertDialog.Builder(this)
            val supprimerUserPopupView : View = layoutInflater.inflate(R.layout.supprimer_popup,null)

            dialogBuilder.setView(supprimerUserPopupView)
            val dialog = dialogBuilder.create()
            dialog.show()

            val oui : Button = supprimerUserPopupView.findViewById(R.id.oui_supprimer)
            val non : Button = supprimerUserPopupView.findViewById(R.id.non_supprimer)

            oui.setOnClickListener{
                val req = iretrofit.deleteUser(intent.getStringExtra("id").toString())
                req.enqueue(object : Callback<UserRes> {
                    override fun onResponse(call: Call<UserRes>, response: Response<UserRes>) {
                        Log.i(MainActivity::class.simpleName, "USER DELETED")
                    }
                    override fun onFailure(call: Call<UserRes>, t: Throwable) {
                        Log.i(MainActivity::class.simpleName, "on FAILURE!!!!")
                    }
                })
                finish()
            }
            non.setOnClickListener{
                dialog.hide()
            }
        }
    }
}