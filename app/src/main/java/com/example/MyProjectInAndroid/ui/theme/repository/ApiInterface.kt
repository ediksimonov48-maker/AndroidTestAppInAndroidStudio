package com.example.MyProjectInAndroid.ui.theme.repository

import com.example.MyProjectInAndroid.ui.theme.data.ResponseInDB
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("?")

    fun getMovieByImdbId(
        @Query("i") imdbId: String?,           // IMDb ID фильма
        @Query("apikey") apikey: String       // Ваш API ключ
    ): Call<ResponseInDB>

    companion object {
        var BASE_URL = "https://www.omdbapi.com/"

        fun create(): ApiInterface{
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val apiService = retrofit.create(ApiInterface::class.java)
            return apiService
        }
    }
}