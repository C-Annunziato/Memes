package com.example.memes

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {
    @GET("get_memes")
    fun getPhotos(): Call<Memes>
}

object MemesApi {

    private const val BASES_URL = "https://api.imgflip.com"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASES_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}
