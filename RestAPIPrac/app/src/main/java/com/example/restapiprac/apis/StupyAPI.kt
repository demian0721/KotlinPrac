package com.example.restapiprac.apis

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface StupyAPI {
    // 방 목록 불러오기
    @GET("/room/")
    fun getStupyRooms(): Call<List<ResultGetRooms>>

    companion object {
        private const val BASE_URL_STUPY_API = "https://stupy.shop/"

        fun create(): StupyAPI {
            val token =
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiI2MmVjNzVkODMxZGRjZWU1N2I2YTZkOTgiLCJpYXQiOjE2NjAwMjc2MjN9.xqew-kHwiweMXmP4MQWn-l1kduQ2A8pTqdYwxQKVB8o"
            val gson: Gson = GsonBuilder().setLenient().create();
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val headerInterceptor = Interceptor {
                val request = it.request()
                    .newBuilder()
                    .addHeader("accept", "application/json")
                    .addHeader("content-type", "application/json")
                    .addHeader(
                        "authorization",
                        "Bearer $token"
                    )
                    .build()
                return@Interceptor it.proceed(request)
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(headerInterceptor)
                .addInterceptor(httpLoggingInterceptor)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL_STUPY_API)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(StupyAPI::class.java)
        }

        fun getRooms(
            onResponse: (a: List<ResultGetRooms>?) -> Int,
            onFailure: () -> Unit
        ) {
            val api = StupyAPI.create()
            api.getStupyRooms().enqueue(object : Callback<List<ResultGetRooms>> {
                override fun onResponse(
                    call: Call<List<ResultGetRooms>>,
                    response: Response<List<ResultGetRooms>>
                ) {

                    Log.d(">>>Stupy1", response.toString())
                    val res = response.body()

                    onResponse(res)
                }

                override fun onFailure(call: Call<List<ResultGetRooms>>, t: Throwable) {
                    // 실패
                    onFailure()
                }
            })

        }
    }
}