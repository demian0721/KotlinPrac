package com.example.hh99_stupy.apis

import android.util.Log
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

class HttpRequestHelper {

    companion object {
        val client: HttpClient = HttpClient(CIO) {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                })
            }
        }
        val baseUrl: String = "stupy.shop"
        val TAG: String = HttpRequestHelper::class.java.name
    }

    suspend fun getRoom(): List<RoomResponse>? =
        withContext(Dispatchers.IO) {
            val token =
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiI2MmVjNzVkODMxZGRjZWU1N2I2YTZkOTgiLCJpYXQiOjE2NjAxMTY1MjJ9.3oRGe8yNY-KQ0vesFDppcs8KjX7MbpzIix9eeYqDGc8"
            val response: HttpResponse = client.get {
                url {
                    protocol = URLProtocol.HTTPS
                    host = baseUrl
                    path("room/")
                }
                headers {
                    append("Content-Type", "application/json; charset=utf-8")
                    append("authorization", "Bearer $token")
                }
            }
            val responseStatus = response.status
            Log.d(TAG, "requestKtorIo: $responseStatus")

            if (responseStatus == HttpStatusCode.OK) {
                response.body()
            } else {
                Log.d(TAG, "Error!")
                null
            }
        }
}