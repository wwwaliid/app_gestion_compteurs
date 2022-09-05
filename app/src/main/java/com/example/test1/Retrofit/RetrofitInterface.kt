package com.example.test1.Retrofit

import com.example.test1.Data.Compteur
import com.example.test1.Data.EditIndexReq
import com.example.test1.Data.SigninReq
import com.example.test1.Data.user
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RetrofitInterface {
    /*@POST("/login")
    fun executeLogin(@Body map: HashMap<String?, String?>?): Call<LoginResult?>?

    @POST("/signup")
    fun executeSignup(@Body map: HashMap<String?, String?>?): Call<Void?>?*/

    @GET("compteurs")
    fun listCompteurs(): Call<List<Compteur>>
    @POST("editindex")
    fun editIndex(@Body data : EditIndexReq): Call<EditIndexReq>
    @POST("signin")
    fun signIn(@Body data : SigninReq): Call<user>
}