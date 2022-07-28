package com.example.parkpromobile.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface APIS {

    // 인기있는 프로 목록
    @GET("/coachings/sales/best/")
    @Headers(
        "accept: application/json",
        "content-type: application/json",
        "authorization: Token d8cf08777eb40706f2d056b8d2119e3f1a5de6a9"
    )
    fun getBestLessons(): Call<BestLessonResponse>

    // 관심 태그 목록
    @GET("/users/interest-tags/")
    @Headers(
        "accept: application/json",
        "content-type: application/json",
        "authorization: Token d8cf08777eb40706f2d056b8d2119e3f1a5de6a9"
    )
    fun getInterestTags(): Call<ArrayList<InterestTagResponse>>

    // 코칭 답변 목록
    @GET("/coachings/answered/")
    @Headers(
        "accept: application/json",
        "content-type: application/json",
        "authorization: Token d8cf08777eb40706f2d056b8d2119e3f1a5de6a9"
    )
    fun getCoachingsAnswered(
        @Query("tag_id") tagId: Int?,
        @Query("pro_id") proId: Int?,
        @Query("tag_exists") tagExists: Boolean?,
        @Query("page") page: Int?,
        @Query("size") size: Int?,
    ): Call<CoachingsAnsweredResponse>


    companion object { // static 처럼 공유객체로 사용가능함. 모든 인스턴스가 공유하는 객체로서 동작함.
        private const val BASE_URL = "http://parkpro-dev-api.gymtinc.com" // 주소

        fun create(): APIS {


            val gson: Gson = GsonBuilder().setLenient().create();

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
//                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(APIS::class.java)
        }
    }
}