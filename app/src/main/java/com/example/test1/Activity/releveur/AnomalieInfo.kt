package com.example.test1.Activity.releveur

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
import com.example.test1.Activity.admin.SignupPage
import com.example.test1.Data.AnomalieRes
import com.example.test1.Data.EditIndexReq
import com.example.test1.Data.UserRes
import com.example.test1.R
import com.example.test1.Retrofit.RetrofitClient
import com.example.test1.Retrofit.RetrofitInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*


class AnomalieInfo : AppCompatActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.anomalie_info)

        val retrofit = RetrofitClient.getInstance();
        val iretrofit = retrofit.create(RetrofitInterface::class.java)

        val intent = intent

        val description: TextView = findViewById(R.id.description)
        val code: TextView = findViewById(R.id.code)
        val supprimer_anomalie : Button = findViewById(R.id.supprimer_anomalie_button)

        description.text = intent.getStringExtra("description")
        code.text = intent.getStringExtra("code")

        supprimer_anomalie.setOnClickListener{
            val dialogBuilder = AlertDialog.Builder(this)
            val supprimerPopupView : View = layoutInflater.inflate(R.layout.supprimer_popup,null)
            dialogBuilder.setView(supprimerPopupView)
            val dialog = dialogBuilder.create()
            dialog.show()

            val oui : Button = supprimerPopupView.findViewById(R.id.oui_supprimer)
            val non : Button = supprimerPopupView.findViewById(R.id.non_supprimer)

            oui.setOnClickListener{
                val req = iretrofit.deleteAnomalie(intent.getStringExtra("id").toString())
                req.enqueue(object : Callback<AnomalieRes> {
                    override fun onResponse(call: Call<AnomalieRes>, response: Response<AnomalieRes>) {
                        Log.i(MainActivity::class.simpleName, "DELETED")
                    }
                    override fun onFailure(call: Call<AnomalieRes>, t: Throwable) {
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
