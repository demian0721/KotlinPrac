package com.example.retrofitbinding.apis

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface API {
    @GET("room/")
    fun getRoom(): Call<List<RoomResponse>>

    companion object {
        val TAG: String = "API_LOG::"
        private const val BASE_URL = "https://stupy.shop/"
        lateinit var token: String

        fun create(): API {
            token =
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiI2MmVjNzVkODMxZGRjZWU1N2I2YTZkOTgiLCJpYXQiOjE2NjAxMTY1MjJ9.3oRGe8yNY-KQ0vesFDppcs8KjX7MbpzIix9eeYqDGc8"
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val headerInterceptor = Interceptor {
                val request = it.request()
                    .newBuilder()
                    .addHeader("Authorization", "Bearer $token")
                    .build()
                return@Interceptor it.proceed(request)
            }
            val client = OkHttpClient.Builder()
                .addInterceptor(headerInterceptor)
                .addInterceptor(httpLoggingInterceptor)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(API::class.java)
        }
    }
}