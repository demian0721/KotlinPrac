package com.example.hh99_stupy.apis

import android.util.Log
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HttpRequestHelper {
    private val client: HttpClient = HttpClient(CIO)
//    private val BASE_URL: String = "https://stupy.shop/"

    companion object {
        val TAG: String = HttpRequestHelper::class.java.name
    }

    suspend fun getRoom(): List<RoomResponse>? =
        withContext(Dispatchers.IO) {
            val token = "d8cf08777eb40706f2d056b8d2119e3f1a5de6a9"
            val response: HttpResponse = client.get {
                url {
                    protocol = URLProtocol.HTTPS
                    host = "stupy.shop"
                    path("/room/")
                }
                contentType(ContentType.Application.Json)
                headers {
                    append("authorization", "Token $token")
                }
            }
            val responseStatus = response.status
            Log.d(TAG, "requestKtorIo: $responseStatus")

            if (responseStatus == HttpStatusCode.OK) {
                Log.d(TAG, response.body<List<RoomResponse>>().toString())
                response.body()
            } else {
                Log.d(TAG, "Error!")
                null
            }
        }
}