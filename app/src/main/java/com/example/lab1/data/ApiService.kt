package com.example.lab1.data

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("http://10.0.2.2:5296/api/Vartotojai")
    fun getUsers(): Call<List<User>>

//    @GET("http://localhost:5296/api/Matavimai")
//    fun getMatavimai(): Call<List<Matavimai>>

    @GET("http://localhost:5296/api/Stiprumai")
    fun getStiprumai(): Call<List<Stiprumai>>
}