package com.messieyawo.ispaceroomcoroutineflowproject.data.api

import com.google.gson.GsonBuilder
import com.messieyawo.ispaceroomcoroutineflowproject.data.models.Genres
import com.messieyawo.ispaceroomcoroutineflowproject.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @GET("/genre")
    suspend fun fetchAllGenres(
    ): Response<Genres>


}