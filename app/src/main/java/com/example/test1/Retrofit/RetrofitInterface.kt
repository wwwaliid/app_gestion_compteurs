package com.example.test1.Retrofit

import com.example.test1.Data.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface RetrofitInterface {
    /*@POST("/login")
    fun executeLogin(@Body map: HashMap<String?, String?>?): Call<LoginResult?>?

    @POST("/signup")
    fun executeSignup(@Body map: HashMap<String?, String?>?): Call<Void?>?*/

    @GET("compteurs")
    fun listCompteurs(): Call<List<CompteurRes>>
    @POST("editindex")
    fun editIndex(@Body data : EditIndexReq): Call<EditIndexReq>
    @POST("signin")
    fun signIn(@Body data : SigninReq): Call<User>
    @POST("signup")
    fun signUp(@Body data : User): Call<User>
    @POST("creercompteur")
    fun creerCompteur(@Body data : Compteur): Call<Compteur>
    @POST("recherchercompteur")
    fun rechercherCompteur(@Body data : CompteurRecherche): Call<List<CompteurRes>>
    @GET("users")
    fun listUsers(): Call<List<UserRes>>
    @DELETE("/deleteuser/{Id}")
    fun deleteUser(@Path("Id") userid: String): Call<UserRes>
    @POST("creeranomalie")
    fun creerAnomalie(@Body data : Anomalie): Call<Anomalie>
    @GET("anomalies")
    fun listAnomalies(): Call<List<AnomalieRes>>
    @DELETE("/deleteanomalie/{Id}")
    fun deleteAnomalie(@Path("Id") anomalieid: String): Call<AnomalieRes>
    @DELETE("/deletecompteur/{Id}")
    fun deleteCompteur(@Path("Id") compteurid: String): Call<CompteurRes>
    @POST("editanomalie")
    fun editAnomalie(@Body data : EditAnomalieReq): Call<EditAnomalieReq>
    @POST("editpassword")
    fun editPassword(@Body data : EditPassReq): Call<EditPassReq>
}